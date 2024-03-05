const baseUrl = 'http://localhost:8080/adviser-app/RequestBay';
var previousEventFunc = null;
var adviser = null;

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
	const replyBtn = document.getElementById('replyBtn');
	
	
	const payload = rowIdToObj(rowId);
	const usrUrl = baseUrl + `?action=get-user&username=${payload.sender}`;
	const msgUrl = baseUrl + '?action=get-msg';
	
	const sender = await getSender(usrUrl);
	const message = await getMessage(msgUrl, payload);
	
	resetBtnEvent(replyBtn);
	previousEventFunc = () => reply(sender, message);
	replyBtn.addEventListener('click', previousEventFunc);
	
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
	const replyMsg = document.getElementById('reply');
	const fileAtc = document.getElementById('attachment');
	
	replyMsg.value = '';
	fileAtc.vallue = '';
	
	modal.close();
}

async function reply(sender, message) {
	const getAdviserUrl = baseUrl + `?action=get-user&username=${message.receiver}`;
	const replyMsg = document.getElementById('reply');
	const fileAtc = document.getElementById('attachment');
	const mailPw = document.getElementById('mailPw');
	const replyUrl = baseUrl + '?action=reply';	
	
	if(!adviser) {
		adviser = await fetch(getAdviserUrl)
		.then(response => response.json())
		.catch(error => console.log(error));
	}
	
	const payload = {
			name: `${adviser.name} ${adviser.surname}`,
			from: adviser.mail,
			pw: mailPw.value,
			to: sender.mail,
			message: replyMsg.value
	};
	
	fetch(replyUrl, {
		method: 'POST',
	    body: JSON.stringify(payload)
	})
	.then(response => {
		if (!response.ok) {
			//handle error if the message isn't sent
		}
	})
	.catch(error => console.log(error));
	
	replyMsg.value = '';
	fileAtc.value = '';
}

function resetBtnEvent(btn) {
	if (previousEventFunc)
		btn.removeEventListener('click', previousEventFunc);
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