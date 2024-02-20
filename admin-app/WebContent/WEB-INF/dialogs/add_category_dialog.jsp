<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<dialog class="modal" id="addCategoryDialog">
	<h2 class="modal-title"></h2>
	<form class="form v-cont modal-form" method="post" id="categoryForm">
		<div class="h-cont h-gap crossa-lbaseline">
			<div class="v-cont form-group fg-1">
				<label class="form-label" for="newCategory">Kategorija</label>
				<input class="form-input" type="text" name="newCategory" id="newCategory" required>
			</div>
		</div>
		
		<div class="v-cont form-group">
			<label for="attributeName" class="form-label">Atributi</label>
				<div class="custom-select">
					<select id="attributeName" name="attributeName"  multiple>
                    </select>
                </div>
		</div>
		<div class="h-cont h-gap crossa-lbaseline">
			<div class="v-cont form-group fg-1">
				<label class="form-label" for="newAttribute">Atribut</label>
				<input class="form-input" type="text" name="newAttribute" id="newAttribute" placeholder="Dodajte novi atribut...">
			</div>
			<button type="button" class="btn-style btn-small m-2 bg-accent" id="addAttributeBtn" onclick="addAttribute()">Dodaj</button>
		</div>
	</form>
	<div class="h-cont btn-cont">
		<button class="btn-style btn-small m-2 bg-secondary" id="btnCancel" onclick="closeModal('addCategoryDialog')">Poništi</button>
		<button class="btn-style btn-small m-2 bg-accent" id="btnSave">Sačuvaj</button>
	</div>
</dialog>