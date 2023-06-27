/**
 * modify.html
 */

 document.addEventListener('DOMContentLoaded',()=>{
	 
	 const btndelete = document.querySelector('button#btndelete');
	 const id = document.querySelector('input#id');
	 const form = document.querySelector('form#updateform')
	 
	 btndelete.addEventListener('click',(e)=>{
		 
		 e.preventDefault();
		 
		 let check = confirm('정말로 삭제하시겠습니까?');
		 if(!check){
			 return;
		 }
		 
		 form.method = 'post';
		 form.action = `/post/delete?id=${id.value}`;
		 form.submit();
		 
		 
	 })
	 
 })