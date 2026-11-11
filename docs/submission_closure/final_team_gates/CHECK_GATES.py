#!/usr/bin/env python3
import json,sys,re
from pathlib import Path
p=Path(__file__).with_name('AUTHOR_FACTS.json')
if not p.exists():
 print('Missing AUTHOR_FACTS.json; copy AUTHOR_FACTS.example.json, obtain actual approvals, and fill it.');sys.exit(2)
d=json.loads(p.read_text(encoding='utf8'))
def get(path):
 x=d
 for q in path.split('.'):
  x=x.get(q) if isinstance(x,dict) else None
 return x
checks={
 'All authors approved record': bool(get('author_approval.approved')) and bool(get('author_approval.approval_date')),
 'Model provenance honestly resolved': (get('model_provenance.status')=='VERIFIED' and bool(get('model_provenance.session_evidence_location'))) or (get('model_provenance.status')=='LIMITATIONS_APPROVED' and bool(get('model_provenance.authors_accept_limits'))),
 'AI use author approved': bool(get('ai_use.authors_approved')) and get('ai_use.manuscript_ai_generated_text_used') is not None and bool(get('ai_use.acknowledgements_checked')) and (not get('ai_use.manuscript_ai_generated_text_used') or (bool(get('ai_use.affected_sections')) and bool(get('ai_use.ai_system_citations_verified')))),
 'Protocol disposition documented': bool(get('protocol.authors_approved')) and ((get('protocol.frozen_before_generation') is True and bool(get('protocol.pre_generation_commit')) and bool(get('protocol.pre_generation_timestamp'))) or (get('protocol.frozen_before_generation') is False and bool(get('protocol.retrospective_disclosed')))),
 'Funding fact signed': bool(get('funding.authors_approved')) and bool(get('funding.statement').strip()),
 'Competing interests signed': bool(get('competing_interests.authors_approved')) and bool(get('competing_interests.statement').strip()),
 'Anonymous accessible publication': bool(re.match(r'https://',get('artifact.publication_url') or '')) and bool(get('artifact.immutable_revision')) and all(bool(get('artifact.'+x)) for x in ['license_reviewed','anonymous_review_passed','reviewer_access_verified']),
 'CE023 disposition transparent': bool(get('ce023.historical_git_discrepancy_verified')) and bool(get('ce023.sensitivity_disclosed')),
 'Full manuscript review': all(bool(get('manuscript.'+x)) for x in ['all_tracker_items_reviewed','all_claims_edited_and_verified','ieee_pdf_validated','nier_page_limit_validated','anonymity_checked','all_author_approval'])
}
result={'submission_ready':all(checks.values()),'checks':checks,'unresolved':[k for k,v in checks.items() if not v]}
out=Path(__file__).parent
(out/'GATE_REPORT.json').write_text(json.dumps(result,indent=2)+'\n',encoding='utf8')
(out/'GATE_REPORT.md').write_text('# Submission gate report\n\n'+''.join(f'- [{"x" if v else " "}] {k}\n' for k,v in checks.items())+f'\n**SUBMISSION_READY: {result["submission_ready"]}**\n',encoding='utf8')
print(json.dumps(result,indent=2))
sys.exit(0 if result['submission_ready'] else 3)
