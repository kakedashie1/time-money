'use strict';


{
	const open = document.getElementById('open');
	const list = document.querySelector('.list');
	const close = document.getElementById('close');

	open.addEventListener('click', () => {
		
		list.classList.add('show');
		open.classList.add('hide');

	});
	
	close.addEventListener('click', () => {
			
			list.classList.remove('show');
			open.classList.remove('hide');

		});
}