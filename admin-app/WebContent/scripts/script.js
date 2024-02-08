function init(text) {
  addModalEventListener('modal');
  addModalEventListener('pop-up');
  addModalEventListener('changeModal');
  addToTitle(text);
  addRemoveEventListeners();
  addChangeEventListeners();
}

function addModalEventListener(id) {
  const modal = document.getElementById(id);
  if (modal == null)
	  return;
  modal.addEventListener("click", e => {
	  if (['SELECT', 'OPTION'].includes(e.target.tagName))
		  return;
    const modalDimensions = modal.getBoundingClientRect();
    if (
      e.clientX < modalDimensions.left ||
      e.clientX > modalDimensions.right ||
      e.clientY < modalDimensions.top ||
      e.clientY > modalDimensions.bottom
    ) {
      modal.close();
    }
  });
}

function showModal() {
  modal.showModal();
}

function closeModal(modalId) {
	const modal = document.getElementById(modalId);
	const form = modal.getElementsByTagName('form')[0];
	form.reset();
	modal.close();
}

function addToTitle(text) {
	const current = document.title;
	document.title = `${current} ${text}`;
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
    	'><label for="' + id + '" class="icon clr-text check-lbl"></label>';
}

function addToTable(user) {
	  const table = document.getElementById('usersTable');
	  const row = table.insertRow(-1);//append row at the end
	  row.id = user.username;

	  addCell(row, user.name);
	  addCell(row, user.surname);
	  addCell(row, user.username);
	  addCell(row, user.userType);
	  addCell(row, user.city);
	  addCell(row, user.mail);
	  addCell(row, getCheckBox(user.username, user.activated));
	  addCell(row, getChangeButton(user.username));
	  addCell(row, getRemoveButton(user.username));
}

function addCell(row, content) {
	  let cell = row.insertCell(-1);
	  cell.innerHTML = content;
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
	changeForm.querySelector('#userType').value = user.typeAbbr;
}

async function changeUser() {
	const changeModal = document.getElementById('changeModal');
	const changeForm = document.querySelector('#changeUserForm');
	const modalTitle = changeModal.querySelector('.modal-title').innerText;
	const username = modalTitle.split(' ').pop();
	
	const url = `?action=get-user&username=${username}`;
	let user = await fetch(url).then(response => response.json());
	
	user.name = changeForm.querySelector('#name').value;
	user.surname = changeForm.querySelector('#surname').value;
	user.password = changeForm.querySelector('#password').value;
	user.city = changeForm.querySelector('#city').value;
	user.mail = changeForm.querySelector('#mail').value;
	user.userType = changeForm.querySelector('#userType').value;
	
	console.log(user);
}