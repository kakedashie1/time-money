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


	const date = document.getElementById('date');
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
	const registModal = document.getElementById('regist-modal');
	const categoryModal = document.getElementById('category-modal');
	const categoryEdit = document.getElementById('category-edit');
	const categoryBackBtn = document.getElementById('category-back-btn');
	const categoryAddBtn = document.getElementById('category-add-btn');
	const categoryAddModal = document.getElementById('category-add-modal');
	const categoryRegistBackBtn = document.getElementById('category-regist-back-btn');
	const categoryErrorMessage = document.getElementById('categoryErrorMessage');
	const categoryRegistContent = document.getElementById('categoryRegistContent');
	const categoryRegistBtn = document.getElementById('category-regist-btn');
	const categoryRemoveBtn = document.getElementById('cateogry-remove-btn');
	const categoryRegistErr = document.getElementById('categoryRegistErr');
	const categoryEditModal = document.getElementById('category-edit-modal');
	const categoryEditBtn = document.querySelectorAll('category-edit-btn');
	const categoryEditBackBtn = document.getElementById('category-edit-back-btn');
	const categoryEditMode = document.getElementById('categoryEditMode');
	const categoryEditedMode = document.getElementById('categoryEditedMode');


	
	
	const day = date.textContent;
	
	const today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var d = today.getDate();
	
	const todate = year + "-" + month + "-" + d;

	document.addEventListener('DOMContentLoaded', function() {
		categoryRegistContent.value = "";
		
		if(day == todate) {
			
			registBtn.classList.remove('hidden');
		}
		
		console.log(todate);
		console.log(day);

	});

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
	if (categoryErrorMessage) {

		document.addEventListener('DOMContentLoaded', function() {
			categoryRegistContent.value = "";
			modal.classList.remove('hidden');
			mask.classList.remove('hidden');
			categoryAddModal.classList.remove('hidden');
			registModal.classList.add('hidden');


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

		}

		if (categoryRegistErr) {
			categoryRegistErr.textContent = '';

		}


	});



	mask.addEventListener('click', () => {

		categoryAddModal.classList.add('hidden');
		registModal.classList.remove('hidden');
		categoryModal.classList.add('hidden');
		categoryEditModal.classList.add('hidden');

		categoryRegistContent.value = "";
		modal.classList.add('hidden');
		mask.classList.add('hidden');

		if (categoryRegistErr) {
			categoryRegistErr.textContent = '';

		}
		if (startErr) {
			startErr.textContent = '';

		}
		if (endErr) {
			endErr.textContent = '';

		}

		if (categoryEditMode) {
			categoryEditMode.textContent = '';

		}
		if (categoryEditedMode) {
			categoryEditedMode.textContent = '';

		}






	});


	categoryEdit.addEventListener('click', () => {

		categoryModal.classList.remove('hidden');
		registModal.classList.add('hidden');

	});


	categoryBackBtn.addEventListener('click', () => {

		categoryModal.classList.add('hidden');
		registModal.classList.remove('hidden');

	});

	categoryAddBtn.addEventListener('click', () => {

		categoryModal.classList.add('hidden');
		categoryAddModal.classList.remove('hidden');

	});

	categoryRegistBackBtn.addEventListener('click', () => {

		categoryAddModal.classList.add('hidden');
		categoryModal.classList.remove('hidden');
		if (categoryRegistErr) {
			categoryRegistErr.textContent = '';

		}

	});

	categoryRegistBtn.addEventListener('click', () => {



	});


	//	categoryRemoveBtn.addEventListener('click', () => {
	//
	//		CheckDelete();
	//
	//	});


	if (categoryEditMode) {

		document.addEventListener('DOMContentLoaded', function() {
			modal.classList.remove('hidden');
			mask.classList.remove('hidden');
			categoryModal.classList.add('hidden');
			registModal.classList.add('hidden');
			categoryEditModal.classList.remove('hidden');
		});
	}
	if (categoryEditedMode) {

		document.addEventListener('DOMContentLoaded', function() {
			modal.classList.remove('hidden');
			mask.classList.remove('hidden');
			registModal.classList.add('hidden');
			categoryModal.classList.remove('hidden');
			
		});
	}

	categoryEditBackBtn.addEventListener('click', () => {

		categoryEditModal.classList.add('hidden');
		categoryModal.classList.remove('hidden');

				if (categoryEditMode) {
					categoryEditMode.textContent = '';
		
				}
				if (categoryEditedMode) {
					categoryEditedMode.textContent = '';
		
				}

	});




}

function CheckDelete() {
	if (confirm('削除しますか？')) {
		return true;
	} else {
		alert('キャンセルされました');
		return false;
	}
}


