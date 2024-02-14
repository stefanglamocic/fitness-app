function init(text) {
	addToTitle(text);
	addModalEventListener('addCategoryDialog');
}

function showAddCategoryModal() {
	const modal = document.getElementById('addCategoryDialog');
	
	modal.showModal();
}