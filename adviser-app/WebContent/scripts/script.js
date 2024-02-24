const baseUrl = 'http://localhost:8080/adviser-app/RequestBay';

function init() {
	addModalEventListener('msgModal');
}

function openMsg(event) {
	const rowId = event.currentTarget.id;
	const row = document.getElementById(rowId);
	const style = getComputedStyle(document.body);
	const modal = document.getElementById('msgModal');
	
	modal.showModal();
	
	if (row.style.backgroundColor || row.getAttribute('class') === 'msg-opened')
		return;
	
	row.style.backgroundColor = style.getPropertyValue('--clr-secondary-hover');
	reqMsgOpen(rowId);
}

function populateInfo() {
	
}

function reqMsgOpen(rowId) {
	const url = baseUrl + '?action=open-msg';
	const data = rowId.split(';');
	const obj = {
			sender: data[0],
			receiver: data[1],
			timeSent: data[2]
	};
	
	fetch(url, {
		method: 'POST',
		body: JSON.stringify(obj)
	})
	.catch(error => console.log(error));
}

function closeModal(modalId) {
	const modal = document.getElementById(modalId);
	/*const form = modal.getElementsByTagName('form')[0];
	form.reset();*/
	modal.close();
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