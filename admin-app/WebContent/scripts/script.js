function init() {
  addModalEventListener();
}

function addModalEventListener() {
  const modal = document.getElementById('modal');
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