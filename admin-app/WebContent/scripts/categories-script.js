function init(text) {
	addToTitle(text);
	addModalEventListener('addCategoryDialog');
}

function showAddCategoryModal() {
	const modal = document.getElementById('addCategoryDialog');
	const categoriesUrl = '?action=categories';
	const attributesUrl = '?action=attributes';
	
	fetch(categoriesUrl)
	.then(response => response.json())
	.then(data => populateCategoriesSelect(data))
	.catch(error => console.log(`Greska: ${error}`));
	
	fetch(attributesUrl)
	.then(response => response.json())
	.then(data => populateAttributesSelect(data))
	.catch(error => console.log(`Greska: ${error}`));
	
	modal.showModal();
}

function populateCategoriesSelect(data) {
	const categoriesSelect = document.getElementById('categoryName');
	categoriesSelect.innerHTML = '';
	for (const category of data) {
		const option = document.createElement('option');
		option.text = category;
		option.value = category;
		categoriesSelect.add(option);
	}
}

function populateAttributesSelect(data) {
	const attributesSelect = document.getElementById('attributeName');
	attributesSelect.innerHTML = '';
	for (const attribute of data) {
		const option = document.createElement('option');
		option.text = attribute;
		option.value = attribute;
		attributesSelect.add(option);
	}
}