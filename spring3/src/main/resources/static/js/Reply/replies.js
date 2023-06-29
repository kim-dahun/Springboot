/**
 * 
 */

 document.addEventListener('DOMContentLoaded',()=>{
	 
	 let collapse = new bootstrap.Collapse('div#replyToggleDiv',{toggle: false});
	 const btnToggle = document.querySelector('button#btnToggleReply');
	 const toggleBtnIcon = document.querySelector('img#toggleBtnIcon');
	 const replylist = document.querySelector('div#replylist');
	 const id = document.querySelector('input#id');
	 const replyCount = document.querySelector('span#replyCount');
	 const btnReplyCreate = document.querySelector('button#btnReplyCreate');
	 const replyText = document.querySelector('textarea#replyText');
	 let countRe = 1;
	 const btnaddview = document.querySelector('button#btnaddview');
	 let Modifycheck = false;
	 
	 btnaddview.addEventListener('click',()=>{
		 
		 if(countRe*3 )
		 
		 countRe = countRe+1;
		 refreshReplyCount();
		 loadReplylist();
	 })
	 
	 btnToggle.addEventListener('click',()=>{
		 refreshReplyCount();
		 loadReplylist();
		 if(toggleBtnIcon.alt === 'toggle-off'){
			 
			 toggleBtnIcon.alt = 'toggle-on';
			 toggleBtnIcon.src = '/images/toggle-on.svg';
			 
			 
			 // 댓글 갯수 리프레쉬 및 댓글 목록 불러오기
			 
		 } else {
			 
			 toggleBtnIcon.alt = 'toggle-off';
			 toggleBtnIcon.src = '/images/toggle-off.svg';
			 
			 
		 }
		 collapse.toggle();
	 })
	 
	 const refreshReplyCount = async ()=>{
		 const pid = id.value;
		 
		 const reqUrl = `/api/reply/count/${pid}`;
		 
		 try{
			 
			 let response = await axios.get(reqUrl);
			 let data = response.data;
			 replyCount.innerHTML = '';
			 replyCount.innerHTML = `${data}`;
			 console.log(data);
			 
		 } catch(error){
			 console.log(error);
		 }
		 
		 
	 }
	 
	 const loadReplylist = async ()=>{
		 
		 const pid = id.value;
		 
		 const reqUrl = `/api/reply/all/${pid}`;
		 try{
			 
			 let response = await axios.get(reqUrl);
			 let data = response.data;
			 console.log(data);
			 makeReplylist(data,countRe);
			 
		 }catch(error){
			 console.log(error);
			 
		 }
		 
		 
	 }
	 
	 const makeReplylist = (data,countRe)=>{
		 
		 replylist.innerHTML = '';
		 let html = '';
		 
		 let count = 3 * countRe;
		 let start = 0;
		 for(let x of data){
			 
			 if(start>count){
				 
				 break;
				 
			 } else {
				 
				 start = start + 1;
				 
			 }
			 let date = new Date(x.modifiedTime);
			 
			 let datetimes = date.toLocaleString();
			 
			 html += `<div id="a${x.id}" class="my-2 form row">
			 			
						<div class="col-10">
						<input readonly="readonly" class="form-control" d-pid="${x.post.id}" d-id="${x.id}" type="text" id="${x.id}" name="${x.id}" value="작성자 : ${x.writer} / 최종 수정 시간 : ${datetimes}" />
						
						
						<textarea style="height: 85px;" readonly="readonly" class="form-control">${x.replyText}</textarea>
						
						</div>
						<div class="my-1 col-2">
						<button data-id="${x.id}" class="form-control btnDelete btn btn-outline-danger">삭제</button>
						<button data-id="${x.id}" class="form-control btnUpdate btn btn-outline-success">수정</button>
					</div>
					</div>
					`;
			 
		 }
		 replylist.innerHTML = html;
		 const btnUpdate = document.querySelectorAll('button.btnUpdate');
		 const btnDelete = document.querySelectorAll('button.btnDelete');
		 for(let x of btnDelete){
			 
			 console.log(x.getAttribute('data-id'));
			 x.addEventListener('click',deleteReply);
			 
		 }
		 
		 for(let x of btnUpdate){
			 
			 console.log(x.getAttribute('data-id'));
			 // 이벤트 추가
			 x.addEventListener('click',UpdateReply);
		 }
		 
		 
	 }
	 
	 
	 
	 
	 btnReplyCreate.addEventListener('click',async ()=>{
		 
		 let content = replyText.value;
		 
		 if(content == ''){
			 
			 alert('댓글 내용은 필수입력입니다.');
			 return;
			 
		 }
		 
		 let userid = '아직은 테스트';
		 let postid = id.value;
		 const data = {
			 
			 postid : postid,
			 userid : userid,
			 content : content
			 
		 }
		 
		 const reqUrl = '/api/reply/write';
		 
		 try{
			 
			 let response = await axios.post(reqUrl,data);
			 replyText.value = '';
			 countRe = 1;
			 refreshReplyCount();
			 loadReplylist();
			 
		 } catch(error){
			 
			 console.log(error);
			 
		 }
		 
	 })
	 
	 const deleteReply = async (e)=>{
		 
		 if(Modifycheck==true){
			 
			 alert('현재 수정 작업이 진행중입니다. 완료 후 클릭해주세요.');
			 return;
			 
		 }
		 
		 let replyid = e.target.getAttribute('data-id');
		 
		 let result = confirm(`No. ${replyid} 댓글을 삭제하시겠습니까??`);
		 
		 if(!result){
			 
			 return;
			 
		 }
		 
		 console.log(replyid);
		 
		 const reqUrl = `/api/reply/delete/${replyid}`;
		 
		 axios.post(reqUrl)
		 .then((response)=>{
			 
			 console.log(response);
			 countRe = 1;
			 refreshReplyCount();
			 loadReplylist();
			 
		 })
		 .catch((error)=>{
			 console.log(error);
			 
		 })
		 
	 }
	 
	 const UpdateReply = async (e) => {
		 if(Modifycheck){
			 
			 alert('이미 다른 댓글을 수정 중입니다. 수정 완료 후 진행해주세요.');
			 return;
			 
		 }
		 
		 Modifycheck = true;
		 
		 let commentid = e.target.getAttribute('data-id');
		 
		 let updateReplyMine = document.querySelector('div#a'+commentid);
		 
		 const reqUrl = `/api/reply/find/${commentid}`;
		 let x = '';
		 try{
			 let response = await axios.get(reqUrl);
			 
			 x = response.data;
			 
			 let datetimes = new Date(x.modifiedTime);
			 datetimes = datetimes.toLocaleString();
			 
			  updateReplyMine.innerHTML = '';
		 	  updateReplyMine.innerHTML = `
		 
		 			<div class="col-10">
						<input readonly="readonly" class="form-control" d-pid="${x.post.id}" d-id="${x.id}" type="text" id="${x.id}" name="${x.id}" value="작성자 : ${x.writer} / 수정 중인 댓글입니다." />
						
						
						<textarea id="a${x.id}" style="height: 85px;" class="form-control">${x.replyText}</textarea>
						
						</div>
						<div class="my-1 col-2">
						<button data-id="${x.id}" id="btnCancel" class="form-control btnCancel btn btn-outline-danger">수정 취소</button>
						<button data-id="${x.id}" id="btnModify" class="form-control btnModify btn btn-outline-success">수정 완료</button>
					</div>
		 `	
			
			const btnCancel = document.querySelector('button#btnCancel');
			const btnModify = document.querySelector('button#btnModify');
			
			btnCancel.addEventListener('click',()=>{
				
				refreshReplyCount();
				loadReplylist();
				Modifycheck=false;
				
			})
			
			
			
			btnModify.addEventListener('click', ModifyContent);
			 
		} catch(error){
			console.log(error);
		}
		 
		 
		
		 
	 }
	 
	 const ModifyContent = async (e) => {
		 
		 
		 let idv = e.target.getAttribute('data-id');
		 let conts = document.querySelector('textarea#a'+idv).value;
		 console.log(conts);
		 if(conts==''){
			 
			 alert('내용은 반드시 입력해주세요.');
			 return;
			 
		 }
		 
		 const reqUrl = `/api/reply/modify`;
		 const data = {
			 
			 id : idv,
			 replyText : conts
			 
		 }
		 
		 try{
			 
			 let response = await axios.post(reqUrl,data);
			 console.log(response.data);
			 refreshReplyCount();
			 loadReplylist();
		 } catch(error){
			 
			 console.log(error);
			 
		 } finally{
			 
			 Modifycheck = false;
			 
		 }
		 
	 }
	 
	 
 })