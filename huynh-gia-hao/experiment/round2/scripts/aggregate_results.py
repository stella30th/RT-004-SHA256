#!/usr/bin/env python3
"""Aggregate recorded actual executions only; flag missing repetitions."""
import argparse, pathlib, json, csv, collections, math
TASKS=['CE033','CE041','CE043','CE050','CE053','CE060','CE065','CE066','CE077','CE085','CE090'];CONDS=['p0_resample','checklist_only','checklist_focus']
def main():
 p=argparse.ArgumentParser();p.add_argument('--results',required=True);p.add_argument('--out',required=True);p.add_argument('--repetitions',type=int,default=3);args=p.parse_args()
 rows=[]
 for f in pathlib.Path(args.results).rglob('result.json'):
  try:rows.append(json.loads(f.read_text(encoding='utf-8')))
  except Exception:pass
 out=pathlib.Path(args.out);out.parent.mkdir(parents=True,exist_ok=True)
 columns=['model','condition','run','task_id','combined_success','compile_success','execution_success','classification','source_sha256','log_path','report_xml']
 with out.open('w',newline='',encoding='utf-8-sig') as f:
  wr=csv.DictWriter(f,fieldnames=columns,extrasaction='ignore');wr.writeheader();wr.writerows(sorted(rows,key=lambda r:(r.get('model',''),r.get('condition',''),r.get('run',0),r.get('task_id',''))))
 observed={(r.get('model'),r.get('condition'),int(r.get('run',0)),r.get('task_id')) for r in rows}
 missing=[{'model':'chatgpt','condition':c,'run':i,'task_id':t} for c in CONDS for i in range(1,args.repetitions+1) for t in TASKS if ('chatgpt',c,i,t) not in observed]
 (out.with_suffix('.missing.json')).write_text(json.dumps(missing,indent=2),encoding='utf-8')
 print('Recorded executions:',len(rows),'Expected ChatGPT:',len(TASKS)*len(CONDS)*args.repetitions,'Missing:',len(missing));
 for c in CONDS:
  rs=[r for r in rows if r.get('model')=='chatgpt' and r.get('condition')==c];print(c, sum(int(r.get('combined_success',0)) for r in rs),'/',len(rs))
if __name__=='__main__':main()
