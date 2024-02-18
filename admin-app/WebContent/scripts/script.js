function init(text) {
  addToTitle(text);
}

function addToTitle(text) {
	const current = document.title;
	document.title = `${current} ${text}`;
}

function showModal(id) {
	const modal = document.getElementById(id);
	modal.showModal();
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

function closeModal(modalId) {
	const modal = document.getElementById(modalId);
	const form = modal.getElementsByTagName('form')[0];
	form.reset();
	modal.close();
}

function addCell(row, content) {
	  let cell = row.insertCell(-1);
	  cell.innerHTML = content;
}