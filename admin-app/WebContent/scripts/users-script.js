function init(text) {
  addModalEventListener('modal');
  addModalEventListener('pop-up');
  addModalEventListener('changeModal');
  addToTitle(text);
  addRemoveEventListeners();
  addChangeEventListeners();
}

function addUser() {
	  let formName = 'userForm';
	  let form = document.getElementById(formName);
	  
	  for (let e of form.querySelectorAll('[required]')) {
		  if (!e.reportValidity())
			  return;
	  }
	  
	  const url = 'http://localhost:8080/admin-app/?action=add-user';

	  fetch(url, {
	    method: 'POST',
	    body: JSON.stringify(Object.fromEntries(new FormData(form)))
	  })
	  .then(response => response.json())
	  .then(user => addToTable(user))
	  .catch(error => console.log(`Greska ${error}`));

	  closeModal('modal');
}

function getChangeButton(name) {
	  return '<a href="#"' 
	    + 'class="icon btn-link btn-change clr-accent f-3" '
	    + 'id=' + name + 'C onclick="openChangeUserModal(event)"></a>';
}

function getRemoveButton(name) {
	  return '<a href="#"' 
	    + 'class="icon btn-link btn-remove clr-red f-3" '
	    + 'id=' + name + 'R onclick="openRemoveUserModal(event)"></a>';
}

function getCheckBox(name, checked) {
	let check = checked ? 'checked' : '';
	let id = `${name}Ch-b`;
	return '<input type="checkbox" class="btn-check" id="' + id + '" ' + check +
    	' onclick="accActivation(event)"><label for="' + id + '" class="icon clr-text check-lbl"></label>';
}

function addToTable(user) {
	  const table = document.getElementById('usersTable');
	  const row = table.insertRow(-1);//append row at the end
	  row.id = user.username;

	  addCell(row, user.name);
	  addCell(row, user.surname);
	  addCell(row, user.username);
	  addCell(row, getUserType(user));
	  addCell(row, user.city);
	  addCell(row, user.mail);
	  addCell(row, getCheckBox(user.username, user.activated));
	  addCell(row, getChangeButton(user.username));
	  addCell(row, getRemoveButton(user.username));
}

function addRemoveEventListeners() {
	  let removeButtons = document.querySelectorAll('.btn-remove');
	  removeButtons.forEach(e => 
	    e.addEventListener('click', openRemoveUserModal));
}

function addChangeEventListeners() {
	document.querySelectorAll('.btn-change')
    .forEach(b => b.addEventListener('click', openChangeUserModal));
}

function openRemoveUserModal(event) {
	let rowId = event.currentTarget.parentNode.parentNode.id;
	const title = 'Brisanje korisnika';
	const content = `Da li ste sigurni da želite obrisati korisnika ${rowId}?`;
		
	let dialog = getConfirmDialog(title, content);
	  
	 document.querySelector('#cancelBtn').addEventListener('click', closePopUp);
	 document.querySelector('#confirmBtn').addEventListener('click', () => removeUser(rowId));
	  
	 dialog.showModal();
}

function removeUser(username) {
	const url = '?action=remove-user';
	
	fetch(url, {
		method: 'POST',
		body: JSON.stringify({username})
	})
	.then(response => {
		if (response.ok)
			document.getElementById(username).remove();
	})
	.catch(error => console.log(`Greska: ${error}`));
	
	closePopUp();
}

function openChangeUserModal(event) {
	let rowId = event.currentTarget.parentNode.parentNode.id;
	const changeModal = document.getElementById('changeModal');
	const url = `?action=get-user&username=${rowId}`;
	
	changeModal.querySelector('.modal-title').innerText = `Izmjenite detalja o korisniku ${rowId}`;
	
	fetch(url)
	.then(response => response.json())
	.then(user => fillOutForm(user))
	.catch(error => console.log(`Greska: ${error}`))
	
	changeModal.showModal();
}

function fillOutForm(user) {
	const changeForm = document.getElementById('changeUserForm');
	
	changeForm.querySelector('#name').value = user.name;
	changeForm.querySelector('#surname').value = user.surname;
	changeForm.querySelector('#password').value = user.password;
	changeForm.querySelector('#city').value = user.city;
	changeForm.querySelector('#mail').value = user.mail;
	changeForm.querySelector('#userType').value = user.userType;
}

async function changeUser() {
	const changeUrl = '?action=change-user';
	const changeModal = document.getElementById('changeModal');
	const changeForm = document.querySelector('#changeUserForm');
	const modalTitle = changeModal.querySelector('.modal-title').innerText;
	const username = modalTitle.split(' ').pop();
	
	const getUrl = `?action=get-user&username=${username}`;
	let user = await fetch(getUrl).then(response => response.json());
	
	user.name = changeForm.querySelector('#name').value;
	user.surname = changeForm.querySelector('#surname').value;
	user.password = changeForm.querySelector('#password').value;
	user.city = changeForm.querySelector('#city').value;
	user.mail = changeForm.querySelector('#mail').value;
	user.userType = changeForm.querySelector('#userType').value;
	
	fetch(changeUrl, {
		method: 'POST',
		body: JSON.stringify(user)
	})
	.then(response => {
		if (response.ok) {
			changeUserRow(user);
			closeModal('changeModal');
		}
	})
	.catch(error => console.log(`Greska: ${error}`));
}

function changeUserRow(user) {
	const row = document.getElementById(user.username);
	const cells = row.cells;
	
	cells[0].innerText = user.name;
	cells[1].innerText = user.surname;
	cells[2].innerText = user.username;
	cells[4].innerText = user.city;
	cells[5].innerText = user.mail;
	
	let userType = getUserType(user);
	cells[3].innerText = userType;
}

function getUserType(user) {
	let userType = '';
	switch(user.userType) {
		case 'F':
			userType = 'Obični korisnik';
			break;
		case 'S':
			userType = 'Savjetnik';
	}
	
	return userType;
}

function accActivation(event) {
	const rowId = event.currentTarget.parentNode.parentNode.id;
	const checkBox = document.getElementById(rowId + 'Ch-b');
	const checked = checkBox.checked;
	const url = `?action=user-acc&activate=${checked}&username=${rowId}`;
	
	fetch(url)
	.catch(error => console.log(`Greska: ${error}`));
}