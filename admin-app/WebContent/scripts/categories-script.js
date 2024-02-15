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
	.then(data => populateSelect(data, 'categoryName'))
	.catch(error => console.log(`Greska: ${error}`));
	
	fetch(attributesUrl)
	.then(response => response.json())
	.then(data => populateSelect(data, 'attributeName'))
	.catch(error => console.log(`Greska: ${error}`));
	
	modal.showModal();
}

function populateSelect(data, selectId) {
	const select = document.getElementById(selectId);
	select.innerHTML =  '';
	for (const entry of data) {
		const option = document.createElement('option');
		option.text = entry;
		option.value = entry;
		select.add(option);
	}
}