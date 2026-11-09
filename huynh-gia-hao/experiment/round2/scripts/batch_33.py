#!/usr/bin/env python3
"""Prepare, import, execute and summarize 33 ClassEval-T OAPR test generations.
No ChatGPT web automation. No model API requests. Standard library only.
"""
import argparse, csv, datetime, hashlib, json, pathlib, re, subprocess, sys, zipfile

TASKS = ('CE033','CE041','CE043','CE050','CE053','CE060','CE065','CE066','CE077','CE085','CE090')
CONDITIONS = ('p0_resample','checklist_only','checklist_focus')

def paths(base, condition):
    return base / 'prompts' / 'generated' / condition, base / 'raw_outputs' / 'chatgpt' / condition

def slots(condition):
    return [(r,t) for r in range(1,4) for t in TASKS]

def checkroot(base):
    if not (base/'scripts'/'run_suite.py').is_file():
        raise SystemExit('Cannot locate round2/scripts/run_suite.py; use --kit path to round2')

def prepare(base, condition):
    promptroot,_ = paths(base,condition)
    out=base/'batch_33'/condition
    out.mkdir(parents=True,exist_ok=True)
    missing=[t for t in TASKS if not (promptroot/(t+'.txt')).is_file()]
    if missing: raise SystemExit('Missing generated prompts: '+', '.join(missing))
    entries=[]
    with zipfile.ZipFile(out/'prompts_33.zip','w',zipfile.ZIP_DEFLATED) as z:
        for run,task in slots(condition):
            src=promptroot/(task+'.txt')
            name=f'run-{run:02}/{task}.txt'
            payload=src.read_bytes()
            z.writestr(name,payload)
            entries.append((name,hashlib.sha256(payload).hexdigest()))
    (out/'prompt_manifest.csv').write_text('file,sha256\n'+''.join(f'{a},{b}\n' for a,b in entries),encoding='utf-8')
    with (out/'ALL_33_PROMPTS.txt').open('w',encoding='utf-8') as f:
        f.write('OAPR prompt archive. Each task is a separate independent generation; do NOT submit all 33 in one chat expecting 33 independent runs.\n')
        f.write('Each repetition must be a fresh generation using the same frozen prompt.\n\n')
        for run,task in slots(condition):
            f.write(f'\n========== BEGIN PROMPT {condition} run-{run:02} {task} ==========\n')
            f.write((promptroot/(task+'.txt')).read_text(encoding='utf-8-sig'))
            f.write(f'\n========== END PROMPT {condition} run-{run:02} {task} ==========\n')
    (out/'HOW_TO_RETURN.txt').write_text(
        'PREFERRED: return a ZIP containing one Java file per run/task: run-01/CE033.java ... run-03/CE090.java.\n'
        'If providing one text file, use exactly the markers shown below; include COMPLETE Java source between them.\n'
        '===== BEGIN JAVA run-01 CE033 =====\n'
        '// BEGIN GeneratedTest_CE033\npackage org.example;\n...\n// END GeneratedTest_CE033\n'
        '===== END JAVA run-01 CE033 =====\n'
        'Do not concatenate runs as one JUnit class. Do not use an output as the input to a later run.\n'
        'A single chat generation for 33 items is NOT 33 independent samples and may truncate.\n',encoding='utf-8')
    print('Prepared 33 prompt slots at:',out)
    for name in ['prompts_33.zip','ALL_33_PROMPTS.txt','HOW_TO_RETURN.txt','prompt_manifest.csv']:
        print(' ',out/name)

def validate_java(text,task):
    if not re.search(r'^\s*package\s+org\.example\s*;',text,re.M):return 'NO_PACKAGE'
    if not re.search(r'public\s+class\s+GeneratedTest_'+task+r'\b',text):return 'CLASS_MISMATCH'
    if not re.search(r'@Test\b',text):return 'NO_TEST_METHOD'
    if not re.search(r'^\s*\}\s*(?://\s*END GeneratedTest_'+task+r')?\s*$',text,re.M):return 'CHECK_BRACES'
    if re.search(r'&\#x[0-9a-fA-F]+;|^\s*```|\\\s*$',text,re.M):return 'FORMAT_REVIEW'
    return 'READY_FOR_MAVEN'

def import_txt(base,condition,src):
    text=src.read_text(encoding='utf-8-sig')
    regex=re.compile(r'^===== BEGIN JAVA (run-0[123]) (CE\d{3}) =====\s*\n(.*?)^===== END JAVA \1 \2 =====\s*$',re.M|re.S)
    found={}; status=[]
    _,rawroot=paths(base,condition)
    for m in regex.finditer(text):
        run,task,java=m.groups()
        key=(run,task)
        if task not in TASKS:status.append((run,task,'UNEXPECTED_TASK'));continue
        if key in found:status.append((run,task,'DUPLICATE'));continue
        found[key]=True
        verdict=validate_java(java,task)
        dst=rawroot/run/(task+'.java')
        if dst.exists():status.append((run,task,'EXISTS_SKIP'));continue
        if verdict != 'READY_FOR_MAVEN':
            bad=rawroot/run/(task+'.unverified.txt');bad.parent.mkdir(parents=True,exist_ok=True)
            bad.write_text(java,encoding='utf-8')
            status.append((run,task,verdict+'_AS_UNVERIFIED'));continue
        dst.parent.mkdir(parents=True,exist_ok=True)
        dst.write_text(java.strip()+'\n',encoding='utf-8')
        status.append((run,task,'IMPORTED'))
    out=base/'batch_33'/condition
    out.mkdir(parents=True,exist_ok=True)
    with (out/'import_log.csv').open('w',newline='',encoding='utf-8') as f:
        w=csv.writer(f);w.writerow(['run','task','status']);w.writerows(status)
    print(f'Parsed {len(found)} distinct blocks; imported {sum(s[2]=="IMPORTED" for s in status)}; detailed import_log.csv')
    if not found: print('No valid markers found. See HOW_TO_RETURN.txt and use exact delimiters.')

def import_zip(base,condition,src):
    _,rawroot=paths(base,condition)
    status=[];seen=set()
    with zipfile.ZipFile(src) as z:
        for item in z.infolist():
            if item.is_dir():continue
            m=re.search(r'(?:^|/)(run-0[123])/(CE\d{3})\.java$',item.filename.replace('\\','/'))
            if not m:continue
            run,task=m.groups(); key=(run,task)
            if task not in TASKS or key in seen:status.append((run,task,'UNEXPECTED_OR_DUPLICATE'));continue
            seen.add(key)
            java=z.read(item).decode('utf-8-sig',errors='replace')
            verdict=validate_java(java,task)
            dst=rawroot/run/(task+'.java')
            if dst.exists():status.append((run,task,'EXISTS_SKIP'));continue
            dst.parent.mkdir(parents=True,exist_ok=True)
            if verdict!='READY_FOR_MAVEN':
                (rawroot/run/(task+'.unverified.txt')).write_text(java,encoding='utf-8')
                status.append((run,task,verdict+'_AS_UNVERIFIED'));continue
            dst.write_text(java,encoding='utf-8'); status.append((run,task,'IMPORTED'))
    out=base/'batch_33'/condition;out.mkdir(parents=True,exist_ok=True)
    with (out/'import_log.csv').open('w',newline='',encoding='utf-8') as f:
        w=csv.writer(f);w.writerow(['run','task','status']);w.writerows(status)
    print(f'Parsed {len(seen)} slots; imported {sum(s[2]=="IMPORTED" for s in status)}; see import_log.csv')

def inventory(base,condition):
    _,rawroot=paths(base,condition)
    resultroot=base/'outputs'/'runs'/'chatgpt'/condition
    rows=[]
    for run,task in slots(condition):
        r=f'run-{run:02}'; src=rawroot/r/(task+'.java');res=resultroot/r/task/'result.json';unv=rawroot/r/(task+'.unverified.txt')
        item={'condition':condition,'run':run,'task_id':task,'java_present':int(src.is_file()),'unverified_present':int(unv.is_file()),'classification':'','tests':'','failures':'','errors':'','success':'','source_sha256':'','notes':''}
        if res.is_file():
            try:
                data=json.loads(res.read_text(encoding='utf-8'))
                for dst,field in [('classification','classification'),('tests','tests'),('failures','failures'),('errors','errors'),('success','combined_success'),('source_sha256','source_sha256')]:item[dst]=data.get(field,'')
                if not src.is_file():item['notes']='RESULT_WITHOUT_SOURCE'
                elif hashlib.sha256(src.read_bytes()).hexdigest()!=item['source_sha256']:item['notes']='HASH_MISMATCH_RESULT_STALE'
            except (ValueError,OSError) as exc:item['classification']='BAD_RESULT_JSON';item['notes']=str(exc)
        elif unv.is_file():item['classification']='NEEDS_MANUAL_REVIEW'
        elif src.is_file():item['classification']='NOT_RUN'
        else:item['classification']='MISSING_OUTPUT'
        rows.append(item)
    out=base/'batch_33'/condition;out.mkdir(parents=True,exist_ok=True)
    with (out/'results_33.csv').open('w',encoding='utf-8',newline='') as f:
        w=csv.DictWriter(f,fieldnames=list(rows[0]));w.writeheader();w.writerows(rows)
    with (out/'missing_33.txt').open('w',encoding='utf-8') as f:
        for x in rows:
            if x['classification']!='PASS':f.write(f"run-{x['run']:02} {x['task_id']}: {x['classification']} {x['notes']}\n")
    from collections import Counter
    counts=Counter(x['classification'] for x in rows)
    print('SLOTS=33',', '.join(f'{k}={v}' for k,v in sorted(counts.items())))
    print('RESULTS:',out/'results_33.csv')
    print('Non-PASS/missing:',out/'missing_33.txt')

def run(base,condition,workspace,model_name,force):
    _,rawroot=paths(base,condition)
    resultroot=base/'outputs'/'runs'/'chatgpt'/condition
    executable=base/'scripts'/'run_suite.py'
    attempted=0
    for trial,task in slots(condition):
        runfolder=f'run-{trial:02}';java=rawroot/runfolder/(task+'.java');res=resultroot/runfolder/task/'result.json'
        if not java.is_file():print(f'[MISSING] {runfolder} {task}');continue
        if res.is_file() and not force:print(f'[SKIP EXISTING RESULT] {runfolder} {task}');continue
        cmd=[sys.executable,str(executable),'--workspace',str(workspace),'--java',str(java),'--task',task,'--condition',condition,'--run',str(trial),'--out',str(base/'outputs'/'runs'),'--model','chatgpt','--model-identifier',model_name,'--interface','ChatGPT Web UI','--access-time-utc','UNVERIFIED']
        print(f'[RUN] {runfolder} {task}',flush=True)
        proc=subprocess.run(cmd,cwd=str(base.parents[2]))
        attempted+=1
        print(f'[EXIT {proc.returncode}] {runfolder} {task}',flush=True)
    print('Executed Maven suites:',attempted)
    inventory(base,condition)

def main():
    p=argparse.ArgumentParser(description=__doc__)
    p.add_argument('action',choices=['prepare','import-txt','import-zip','run','report'])
    p.add_argument('--kit',type=pathlib.Path,default=pathlib.Path(__file__).resolve().parents[1])
    p.add_argument('--condition',choices=CONDITIONS,default='checklist_only')
    p.add_argument('--input',type=pathlib.Path)
    p.add_argument('--workspace',type=pathlib.Path)
    p.add_argument('--model-identifier',default='ChatGPT 6 Sol (user-reported)')
    p.add_argument('--force',action='store_true',help='Explicitly overwrite existing run logs/results (NOT recommended)')
    args=p.parse_args();base=args.kit.resolve();checkroot(base)
    if args.action=='prepare':prepare(base,args.condition)
    elif args.action=='import-txt':
        if not args.input: p.error('--input required')
        import_txt(base,args.condition,args.input.resolve())
    elif args.action=='import-zip':
        if not args.input:p.error('--input required')
        import_zip(base,args.condition,args.input.resolve())
    elif args.action=='run':
        workspace=(args.workspace or (base.parents[2]/'workspace-template')).resolve()
        if not workspace.is_dir():p.error('workspace missing: '+str(workspace))
        run(base,args.condition,workspace,args.model_identifier,args.force)
    else:inventory(base,args.condition)

if __name__=='__main__':main()
