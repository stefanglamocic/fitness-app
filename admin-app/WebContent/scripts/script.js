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

function closeModal() {
  modal.close();
}

function addToTitle(text) {
	const current = document.title;
	document.title = `${current} ${text}`;
}