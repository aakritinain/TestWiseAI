const $ = (s) => document.querySelector(s);
const $$ = (s) => [...document.querySelectorAll(s)];
const esc = (v='') => String(v).replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
async function api(path, options={}) {
  const response = await fetch('/api'+path, {headers:{'Content-Type':'application/json',...(options.headers||{})}, ...options});
  if (!response.ok) { let msg='Request failed'; try { const body=await response.json(); msg=body.message||msg; } catch{} throw new Error(msg); }
  if(response.status===204) return null;
  return response.json();
}
function toast(message){const t=$('#toast');t.textContent=message;t.classList.remove('hidden');setTimeout(()=>t.classList.add('hidden'),2800);}
function showView(name){
  $$('.view').forEach(v=>v.classList.add('hidden'));$('#view-'+name).classList.remove('hidden');
  $$('.nav').forEach(b=>b.classList.toggle('active',b.dataset.view===name));
  $('#page-title').textContent=({overview:'Overview',tests:'Test cases',bugs:'Bug reports',assistant:'AI assistant'})[name];
  if(name==='overview') refreshDashboard(); if(name==='tests') refreshTests(); if(name==='bugs') refreshBugs();
}
$$('.nav').forEach(b=>b.addEventListener('click',()=>showView(b.dataset.view)));
$$('[data-go]').forEach(b=>b.addEventListener('click',()=>showView(b.dataset.go)));
$('#show-test-form').addEventListener('click',()=>$('#test-form').classList.toggle('hidden'));
$('#show-bug-form').addEventListener('click',()=>$('#bug-form').classList.toggle('hidden'));
$$('[data-cancel]').forEach(b=>b.addEventListener('click',()=>$('#'+b.dataset.cancel).classList.add('hidden')));
function pill(v){return `<span class="pill ${esc(v)}">${esc((v||'').replaceAll('_',' '))}</span>`;}
async function refreshDashboard(){
 try{
  const d=await api('/dashboard'); for(const k of ['totalTests','passedTests','failedTests','openBugs']) $('#'+k).textContent=d[k]??0;
  const tests=await api('/tests');
  $('#recent-tests').innerHTML=tests.length?`<div class="table-wrap"><table><thead><tr><th>TEST CASE</th><th>PRIORITY</th><th>STATUS</th></tr></thead><tbody>${tests.slice(-5).reverse().map(t=>`<tr><td><b>${esc(t.title)}</b></td><td>${pill(t.priority)}</td><td>${pill(t.status)}</td></tr>`).join('')}</tbody></table></div>`:'<div class="empty">No test cases yet. Add one to get started.</div>';
 }catch(e){toast(e.message);}
}
async function refreshTests(){
 try{const data=await api('/tests');$('#test-count').textContent=data.length+' items';
 $('#tests-list').innerHTML=data.length?`<table><thead><tr><th>TITLE</th><th>PRIORITY</th><th>STATUS</th><th>UPDATE STATUS</th><th></th></tr></thead><tbody>${data.slice().reverse().map(t=>`<tr><td><b>${esc(t.title)}</b><br><small>${esc(t.steps).slice(0,85)}</small></td><td>${pill(t.priority)}</td><td>${pill(t.status)}</td><td><select class="small-select" data-test-status="${t.id}">${['NOT_RUN','PASS','FAIL','BLOCKED'].map(s=>`<option ${s===t.status?'selected':''}>${s}</option>`).join('')}</select></td><td><button class="icon-btn" data-delete-test="${t.id}">Delete</button></td></tr>`).join('')}</tbody></table>`:'<div class="empty">No test cases yet. Click “New test case” to add one.</div>';
 $$('[data-test-status]').forEach(s=>s.addEventListener('change',async()=>{try{await api(`/tests/${s.dataset.testStatus}/status`,{method:'PATCH',body:JSON.stringify({status:s.value})});toast('Test status updated');refreshDashboard();}catch(e){toast(e.message);}}));
 $$('[data-delete-test]').forEach(b=>b.addEventListener('click',async()=>{if(confirm('Delete this test case?')){try{await api('/tests/'+b.dataset.deleteTest,{method:'DELETE'});refreshTests();toast('Test case deleted');}catch(e){toast(e.message);}}}));
 }catch(e){toast(e.message);}
}
async function refreshBugs(){
 try{const data=await api('/bugs');$('#bug-count').textContent=data.length+' items';
 $('#bugs-list').innerHTML=data.length?`<table><thead><tr><th>BUG</th><th>SEVERITY</th><th>STATUS</th><th>UPDATE STATUS</th><th></th></tr></thead><tbody>${data.slice().reverse().map(b=>`<tr><td><b>${esc(b.title)}</b><br><small>${esc(b.description).slice(0,90)}</small></td><td>${pill(b.severity)}</td><td>${pill(b.status)}</td><td><select class="small-select" data-bug-status="${b.id}">${['OPEN','IN_PROGRESS','RESOLVED','CLOSED'].map(s=>`<option ${s===b.status?'selected':''}>${s}</option>`).join('')}</select></td><td><button class="icon-btn" data-delete-bug="${b.id}">Delete</button></td></tr>`).join('')}</tbody></table>`:'<div class="empty">No bugs reported. Use “Report a bug” to document an issue.</div>';
 $$('[data-bug-status]').forEach(s=>s.addEventListener('change',async()=>{try{await api(`/bugs/${s.dataset.bugStatus}/status`,{method:'PATCH',body:JSON.stringify({status:s.value})});toast('Bug status updated');refreshDashboard();}catch(e){toast(e.message);}}));
 $$('[data-delete-bug]').forEach(b=>b.addEventListener('click',async()=>{if(confirm('Delete this bug report?')){try{await api('/bugs/'+b.dataset.deleteBug,{method:'DELETE'});refreshBugs();toast('Bug report deleted');}catch(e){toast(e.message);}}}));
 }catch(e){toast(e.message);}
}
$('#test-form').addEventListener('submit',async e=>{e.preventDefault();const f=new FormData(e.target);const body=Object.fromEntries(f.entries());try{await api('/tests',{method:'POST',body:JSON.stringify(body)});e.target.reset();e.target.classList.add('hidden');toast('Test case saved');refreshTests();refreshDashboard();}catch(err){toast(err.message);}});
$('#bug-form').addEventListener('submit',async e=>{e.preventDefault();const f=new FormData(e.target);const body=Object.fromEntries(f.entries());try{await api('/bugs',{method:'POST',body:JSON.stringify(body)});e.target.reset();e.target.classList.add('hidden');toast('Bug report saved');refreshBugs();refreshDashboard();}catch(err){toast(err.message);}});
async function submitAI(formId,resultId,path,fieldMap){const form=$('#'+formId),result=$('#'+resultId),btn=form.querySelector('button[type=submit],button:not([type])');const body=Object.fromEntries(new FormData(form).entries());btn.disabled=true;btn.textContent='Working…';try{const data=await api(path,{method:'POST',body:JSON.stringify(fieldMap?fieldMap(body):body)});result.textContent=data.suggestions||data.analysis||'No result returned';result.classList.remove('hidden');}catch(e){toast(e.message);}finally{btn.disabled=false;btn.textContent=formId==='ai-test-form'?'Generate scenarios →':'Analyze bug →';}}
$('#ai-test-form').addEventListener('submit',e=>{e.preventDefault();submitAI('ai-test-form','ai-test-result','/ai/test-cases');});
$('#ai-bug-form').addEventListener('submit',e=>{e.preventDefault();submitAI('ai-bug-form','ai-bug-result','/ai/analyze-bug');});
refreshDashboard();
