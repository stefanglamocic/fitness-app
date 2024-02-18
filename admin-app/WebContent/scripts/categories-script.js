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
	const attribute = attrInput.value.trim();
	const url = '?action=add-attr';
	
	if(attribute === '')
		return;
	
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

async function insertCategory() {
	const url = '?action=add-cat';
	const categoryInput = document.getElementById('newCategory');
	const categoryName = categoryInput.value.trim();
	
	let categoryId = 
		await fetch(url, {
			method: 'POST',
			body: JSON.stringify({category: categoryName})
		})
		.then(response => response.json())
		.catch(error => console.log(error));
	
	return {categoryId, categoryName};
}

async function addCategory() {
	if (!isValid('categoryForm'))
		return;
	
	const url = '?action=new-category';
	const category = await insertCategory();
	const attrSelect = document.getElementById('attributeName');
	let attributes = [];
	
	for (const option of attrSelect.options) {
		if (option.selected){
			attributes.push(option.value);
			
			fetch(url, {
				method: 'POST',
				body: JSON.stringify({
					id: category.categoryId,
					attribute: option.value
				})
			})
			.catch(error => console.log(error));
		}
	}
	
	addRow(category, joinAttributes(attributes));
	
	closeModal('addCategoryDialog');
}

function addRow(category, attributes) {
	const table = document.getElementById('categoriesTable');
	const row = table.insertRow(-1);
	row.id = category.categoryId;
	
	addCell(row, category.categoryName);
	addCell(row, attributes);
	addCell(row, getChangeBtn());
	addCell(row, getRemoveBtn());
}

function joinAttributes(attributes) {
	let temp = attributes.join(', ');
	return temp;
}

function getChangeBtn(){
	return `<a href="#" class="icon btn-link btn-change clr-accent f-3"></a>`;
}

function getRemoveBtn(){
	return `<a href="#" class="icon btn-link btn-remove clr-red f-3"></a>`;
}