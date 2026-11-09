#!/usr/bin/env python3
import pathlib,re,hashlib,json,argparse
TASKS=['CE033','CE041','CE043','CE050','CE053','CE060','CE065','CE066','CE077','CE085','CE090']
def main():
 ap=argparse.ArgumentParser();ap.add_argument('--workspace',required=True);ap.add_argument('--out',required=True);a=ap.parse_args();w=pathlib.Path(a.workspace);v={}
 for phase,expected in [('chatgpt-p0',94),('chatgpt-p1',11),('gemini-p0',94),('gemini-p1',8)]:
  src=w/'src'/('tests-'+phase); files=sorted(src.rglob('GeneratedTest_CE*.java')) if src.exists() else []
  v[phase]={'count':len(files),'expected_snapshot_count':expected,'missing':sorted(set(TASKS)-set(re.findall(r'CE\d{3}', ' '.join(str(x) for x in files)))) if phase=='chatgpt-p1' else [],'packages':sorted(set((re.search(r'package\s+([\w.]+)\s*;',f.read_text(errors='replace')) or [None,'MISSING'])[1] for f in files))}
 v['production_count']=len(list((w/'src/main/java/org/example').glob('*.java')))
 v['pom_sha256']=hashlib.sha256((w/'pom.xml').read_bytes()).hexdigest()
 output=pathlib.Path(a.out);output.parent.mkdir(parents=True,exist_ok=True);output.write_text(json.dumps(v,indent=2),encoding='utf-8');print(json.dumps(v,indent=2))
if __name__=='__main__':main()
