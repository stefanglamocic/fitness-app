function init(text) {
  addModalEventListener();
  addToTitle(text);
}

function addModalEventListener() {
  const modal = document.getElementById('modal');
  if (modal == null)
	  return;
  modal.addEventListener("click", e => {
    const modalDimensions = modal.getBoundingClientRect()
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

function closeModal(formName) {
	  document.getElementById(formName).reset();
	  modal.close();
}

function addToTitle(text) {
	const current = document.title;
	document.title = `${current} ${text}`;
}

function addUser() {
	  let formName = 'userForm';
	  let form = document.getElementById(formName);
	  const url = 'http://localhost:8080/admin-app/?action=add-user';

	  fetch(url, {
	    method: 'POST',
	    body: JSON.stringify(Object.fromEntries(new FormData(form)))
	  })
	  .then(response => response.json())
	  .then(user => addToTable(user))
	  .catch(error => console.log(`Greska ${error}`));

	  closeModal(formName);
}

	function addToTable(user) {
	  
}