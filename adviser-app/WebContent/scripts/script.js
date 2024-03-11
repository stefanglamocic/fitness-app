const baseUrl = 'http://localhost:8080/adviser-app/RequestBay';
var previousEventFunc = null;
var adviser = null;
var messages = null;

function init(username) {
	addModalEventListener('msgModal');
	getAllMessages(username);
}

async function getAllMessages(username) {
	const url = baseUrl + '?action=get-all-msg&username=' + username;
	
	messages = await fetch(url)
	.then(response => response.json())
	.catch(error => console.log(error));
}

function searchKeyPressed(event) {
	const searchTerm = document.getElementById('search').value;
	const table = document.getElementById('table');
	const msgRows = Array.from(document.querySelectorAll('tr')).filter(r => r.id !== 'headerRow');
	
	msgRows.forEach(r => r.style.display = "");
	
	let filteredMessages = messages
		.filter(m => !m.content.toLowerCase().includes(searchTerm));
	
	filteredMessages.forEach(msg => document.getElementById(msg.messageId).style.display = "none");
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
	
	let file = fileAtc.files[0],
		binary;
	
	if(file) {
		binary = await readFileAsBinary(file);
	}
	
	const payload = {
			name: `${adviser.name} ${adviser.surname}`,
			from: adviser.mail,
			pw: mailPw.value,
			to: sender.mail,
			message: replyMsg.value,
			attachmentName: adviser.username + file?.name ?? '',
			attachment: binary ?? ''
	};
	
	fetch(replyUrl, {
		method: 'POST',
	    body: JSON.stringify(payload)
	})
	.then(response => {
		if (response.status === 535) {
			alert('Pogrešna lozinka za mail!');
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

function readFileAsBinary(file) {
	return new Promise((resolve, reject) => {
		const reader = new FileReader();
		reader.onload = function(event) {
			const fileContents = event.target.result;
			resolve(fileContents);
		};
		
		reader.readAsBinaryString(file);
	});
}