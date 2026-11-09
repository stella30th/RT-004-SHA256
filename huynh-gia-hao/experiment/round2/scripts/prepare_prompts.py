#!/usr/bin/env python3
"""Create auditable, source-grounded experimental prompts. No model calls.
IMPORTANT: --p0-dir must contain the exact original CE###.txt P0 prompts.
"""
import argparse, hashlib, json, pathlib
TASKS = ['CE033','CE041','CE043','CE050','CE053','CE060','CE065','CE066','CE077','CE085','CE090']
CHECKLIST = '''\n\nEXPERIMENTAL ADDENDUM: SOURCE-FIRST ORACLE VERIFICATION (CHECKLIST ONLY)\nBefore returning the JUnit class, internally verify each item below. Do not print reasoning.\n1. Read method signatures, overloads, visibility, imports, and constructor preconditions.\n2. Identify exact accepted input domains; do not infer from conventional APIs.\n3. Follow branches and exception paths for the supplied input.\n4. Trace instance state and inter-method side effects across calls.\n5. Compute expected numbers, strings and boolean return values from the source.\n6. Check every assertion against the executed code path and final state.\n7. Check boundary and empty-state cases, including zero elements/targets.\n8. Use deterministic fixtures; do not modify SUT or use reference tests.\n9. Check Java 17, JUnit 4.13.2, package org.example and imports.\nReturn only one GeneratedTest_CE### Java source class.\n'''
# Diagnostic focus statements are disclosed explicitly, not considered a 'pure prompt' effect.
FOCUS = {
'CE033':'Check encryption/decryption rail iteration and character positions, including one-rail inputs.',
'CE041':'Check handling of invalid coordinates before array indexing; trace exception versus boolean return.',
'CE043':'Check the exact return type and value of lookup after removal.',
'CE050':'Check internal initialization and state conditions before computing the longest word.',
'CE053':'Check whether previous operations accumulate state and alter metrics.',
'CE060':'Check letter case in numeric conversion outputs.',
'CE065':'Check exact accepted enumeration or domain values in validation.',
'CE066':'Check board symbols, zero-target cases and the actual boolean meaning of move().',
'CE077':'Check whether cash balance is included in portfolio valuation.',
'CE085':'Check exact URL path extraction including query and fragment behavior.',
'CE090':'Check whether inventory updates add to, or replace, an existing quantity.'
}
def sha(s):return hashlib.sha256(s.encode('utf-8')).hexdigest()
def main():
 a=argparse.ArgumentParser();a.add_argument('--p0-dir',required=True);a.add_argument('--out',required=True);a.add_argument('--allow-missing',action='store_true');args=a.parse_args()
 inp=pathlib.Path(args.p0_dir); out=pathlib.Path(args.out); out.mkdir(parents=True,exist_ok=True)
 manifest=[];missing=[]
 for task in TASKS:
  p=inp/(task+'.txt')
  if not p.is_file():missing.append(task);continue
  base=p.read_text(encoding='utf-8-sig');
  if not base.strip():missing.append(task);continue
  for condition,txt in [('p0_resample',base),('checklist_only',base+CHECKLIST.replace('CE###',task)),('checklist_focus',base+CHECKLIST.replace('CE###',task)+'\nTASK-SPECIFIC DIAGNOSTIC FOCUS (NO ANSWER KEY): '+FOCUS[task]+'\n')]:
   dest=out/condition;dest.mkdir(parents=True,exist_ok=True);f=dest/(task+'.txt');f.write_text(txt,encoding='utf-8');manifest.append({'task_id':task,'condition':condition,'prompt_path':str(f),'prompt_sha256':sha(txt),'source':'frozen P0 prompt + disclosed addendum'})
 (out/'prompt_manifest.json').write_text(json.dumps({'prompts':manifest,'missing_baselines':missing},indent=2),encoding='utf-8')
 print('Prompts:',len(manifest),'Missing baseline tasks:',','.join(missing) or 'none')
 if missing and not args.allow_missing:raise SystemExit('INCOMPLETE: do not begin experiment until each original P0 prompt is supplied.')
if __name__=='__main__':main()
