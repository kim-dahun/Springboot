/**
 * 
 */

 document.addEventListener('DOMContentLoaded',()=>{
	 
	 const btnToggle = document.querySelector('button#btnToggleReply');
	 const toggleBtnIcon = document.querySelector('img#toggleBtnIcon');
	 btnToggle.addEventListener('click',()=>{
		 
		 if(toggleBtnIcon.alt === 'toggle-off'){
			 
			 toggleBtnIcon.alt = 'toggle-on';
			 toggleBtnIcon.src = '/images/toggle-on.svg';
			 
		 } else {
			 
			 toggleBtnIcon.alt = 'toggle-off';
			 toggleBtnIcon.src = '/images/toggle-off.svg';
			 
		 }
		 
	 })
	 
	 
 })