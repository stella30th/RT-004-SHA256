param([Parameter(Mandatory=$true)][string]$Repo,[string]$UpstreamCommit='')
$ErrorActionPreference='Stop'
$Repo=(Resolve-Path -LiteralPath $Repo).Path
$dst=Join-Path $Repo 'docs/submission_closure/final_team_gates/upstream_ce023'
New-Item -ItemType Directory -Path $dst -Force | Out-Null
$tmp=Join-Path $env:TEMP 'classeval_t_upstream_readonly'
if(!(Test-Path -LiteralPath (Join-Path $tmp '.git'))){git clone --filter=blob:none --no-checkout https://github.com/wLinHoo/ClassEval-T.git $tmp; if($LASTEXITCODE -ne 0){throw 'Cannot clone upstream'}}
git -C $tmp fetch origin; if($LASTEXITCODE -ne 0){throw 'Cannot fetch upstream'}
if(-not $UpstreamCommit){$UpstreamCommit=(git -C $tmp rev-parse origin/HEAD).Trim()}
$full=(git -C $tmp rev-parse "$UpstreamCommit^{commit}").Trim()
if($LASTEXITCODE -ne 0){throw 'Invalid upstream commit'}
$paths=@(git -C $tmp ls-tree -r --name-only $full | Where-Object {$_ -match '(?i)Classroom\.java$'})
$report=@("# CE023 upstream inspection", "", "- Upstream repository: https://github.com/wLinHoo/ClassEval-T", "- Inspected commit: $full", "- This is not proof of the historical generation-time commit.", "")
$py=Join-Path $dst 'hash_upstream.py'
@'
import subprocess,hashlib,sys
root,commit,path=sys.argv[1:]
data=subprocess.check_output(['git','-C',root,'show',commit+':'+path])
print(hashlib.sha256(data).hexdigest()+' '+str(len(data)))
'@ | Set-Content -LiteralPath $py -Encoding UTF8
foreach($path in $paths){
  $details=(& python $py $tmp $full $path)
  if($LASTEXITCODE -ne 0){throw "Hash failed for $path"}
  $report+= "- Candidate: ``$path``; SHA256 and bytes: ``$details``"
}
if($paths.Count -eq 0){$report+='- No `Classroom.java` path found by filename. Check benchmark structure manually.'}
$report | Set-Content -LiteralPath (Join-Path $dst 'UPSTREAM_DISCOVERY.md') -Encoding UTF8
Write-Host "Upstream commit checked: $full"
$report | ForEach-Object {Write-Host $_}
Write-Host 'Inspected upstream does not establish historical generation-time input without matching session evidence.'
