import subprocess,hashlib,sys
root,commit,path=sys.argv[1:]
data=subprocess.check_output(['git','-C',root,'show',commit+':'+path])
print(hashlib.sha256(data).hexdigest()+' '+str(len(data)))
