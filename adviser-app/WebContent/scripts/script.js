const baseUrl = 'http://localhost:8080/adviser-app/RequestBay';

function openMsg(event) {
	const rowId = event.currentTarget.id;
	const row = document.getElementById(rowId);
	const style = getComputedStyle(document.body);
	
	row.style.backgroundColor = style.getPropertyValue('--clr-secondary-hover');
	reqMsgOpen(rowId);
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