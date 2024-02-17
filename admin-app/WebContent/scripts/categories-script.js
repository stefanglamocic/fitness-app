function init(text) {
	addToTitle(text);
	addModalEventListener('addCategoryDialog');
}

function showAddCategoryModal() {
	const modal = document.getElementById('addCategoryDialog');
	const categoriesUrl = '?action=categories';
	const attributesUrl = '?action=attributes';
	
	fetch(attributesUrl)
	.then(response => response.json())
	.then(data => populateSelect(data, 'attributeName'))
	.catch(error => console.log(`Greska: ${error}`));
	
	modal.showModal();
}

function addOption(entry) {
	const option = document.createElement('option');
	option.text = entry;
	option.value = entry;
	
	return option;
}

function populateSelect(data, selectId) {
	const select = document.getElementById(selectId);
	select.innerHTML =  '';
	for (const entry of data) {
		const option = addOption(entry);
		select.add(option);
	}
}

function addAttribute() {
	const attrInput = document.getElementById('newAttribute');
	const attribute = attrInput.value;
	const url = '?action=add-attr';
	
	fetch(url, {
		method: 'POST',
		body: JSON.stringify({attribute})
	})
	.then(response => {
		if (response.ok) {
			const attrSelect = document.getElementById('attributeName');
			const option = addOption(attribute);
			attrSelect.add(option);
			attrSelect.options[attrSelect.options.length - 1].selected = true;
		}
	})
	.catch(error => console.log(`Greska: ${error}`));
	
	attrInput.value = "";
}