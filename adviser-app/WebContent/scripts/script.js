const baseUrl = 'http://localhost:8080/adviser-app/RequestBay';

function init() {
	addModalEventListener('msgModal');
}

function rowIdToObj(rowId) {
	const data = rowId.split(';');
	return obj = {
			sender: data[0],
			receiver: data[1],
			timeSent: data[2]
	};
}

async function openMsg(event) {
	const rowId = event.currentTarget.id;
	const row = document.getElementById(rowId);
	const style = getComputedStyle(document.body);
	const modal = document.getElementById('msgModal');
	
	
	const payload = rowIdToObj(rowId);
	const usrUrl = baseUrl + `?action=get-user&username=${payload.sender}`;
	const msgUrl = baseUrl + '?action=get-msg';
	
	const sender = await getSender(usrUrl);
	const message = await getMessage(msgUrl, payload);
	
	populateInfo(sender, message);
	modal.showModal();
	
	if (row.style.backgroundColor || row.getAttribute('class') === 'msg-opened')
		return;
	
	row.style.backgroundColor = style.getPropertyValue('--clr-secondary-hover');
	reqMsgOpen(payload);
}

async function getSender(usrUrl) {
	return await fetch(usrUrl)
		.then(response => response.json())
		.catch(error => console.log(error));
}

async function getMessage(msgUrl, payload) {
	return await fetch(msgUrl, {
		method: 'POST',
		body: JSON.stringify(payload)
	})
		.then(response => response.json())
		.catch(error => console.log(error));
}

function populateInfo(sender, message) {
	const fromTitle = document.getElementById('from');
	const timeTitle = document.getElementById('timeSent');
	const msgContent = document.querySelector('.msg-content');
	
	fromTitle.innerText = `Od: ${sender.name} ${sender.surname}`;
	timeTitle.innerText = `Datum: ${(new Date(message.timeSent)).toLocaleString()}`;
	msgContent.innerText = message.content;
}

function reqMsgOpen(obj) {
	const url = baseUrl + '?action=open-msg';
	
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