'use strict';


{

	//	ユーザログイン
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

	//


	//ログ登録処理


	const modal = document.getElementById('modal');
	const mask = document.getElementById('mask');
	const timeBtn = document.getElementById('time-btn');
	const closeBtn = document.getElementById('close-btn');
	const backBtn = document.getElementById('back');
	const registBtn = document.getElementById('regist-btn');
	const registTitle = document.getElementById('regist-title');
	const errrorMessage = document.getElementById("errorMessage");
	const startErr = document.getElementById('startErr');
	const categoryErr = document.getElementById('categoryErr');
	const endErr = document.getElementById('endErr');

	registBtn.addEventListener('click', (e) => {
		e.preventDefault();
		modal.classList.remove('hidden');
		mask.classList.remove('hidden');

	});

	if (errrorMessage) {

		document.addEventListener('DOMContentLoaded', function() {
			
			modal.classList.remove('hidden');
			mask.classList.remove('hidden');
		});
	}

	closeBtn.addEventListener('click', () => {
		
		modal.classList.add('hidden');
		mask.classList.add('hidden');

		if (startErr) {
			startErr.textContent = '';
			
		}

		if (categoryErr) {
			categoryErr.textContent = '';
			
		}


		if (endErr) {
			endErr.textContent = '';
			document.getElementById("errorMessage").textContent = "";
		}
	});



	mask.addEventListener('click', () => {

		closeBtn.click();
	});


}