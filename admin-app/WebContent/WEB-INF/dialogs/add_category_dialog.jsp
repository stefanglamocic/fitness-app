<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<dialog class="modal" id="addCategoryDialog">
	<h2 class="modal-title">Dodajte novu kategoriju</h2>
	<form class="form v-cont modal-form" method="post" >
		<div class="v-cont form-group">
			<label for="categoryName" class="form-label">Kategorija</label>
				<div class="custom-select">
					<select id="categoryName" name="categoryName">
                    </select>
                </div>
		</div>
		<div class="h-cont h-gap crossa-lbaseline">
			<div class="v-cont form-group fg-1">
				<label class="form-label" for="newCategory">Nova kategorija</label>
				<input class="form-input" type="text" name="newCategory" id="newCategory" placeholder="Dodajte novu kategoriju...">
			</div>
			<button class="btn-style btn-small m-2 bg-accent" >Dodaj</button>
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
				<label class="form-label" for="newAttribute">Novi atribut</label>
				<input class="form-input" type="text" name="newAttribute" id="newAttribute" placeholder="Dodajte novi atribut...">
			</div>
			<button class="btn-style btn-small m-2 bg-accent" >Dodaj</button>
		</div>
	</form>
	<div class="h-cont btn-cont">
		<button class="btn-style btn-small m-2 bg-secondary" onclick="closeModal('addCategoryDialog')">Poništi</button>
		<button class="btn-style btn-small m-2 bg-accent" >Sačuvaj</button>
	</div>
</dialog>