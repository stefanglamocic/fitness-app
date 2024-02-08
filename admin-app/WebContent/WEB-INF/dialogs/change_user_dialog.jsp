<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<dialog class="modal" id="changeModal">
            <h2 class="modal-title">Izmjenite detalje o korisniku </h2>
            <form method="post" action="?action=add-user" class="form v-cont modal-form" id="changeUserForm">
                <div class="h-cont h-gap">
                    <div class="v-cont form-group">
                        <label class="form-label" for="name">Ime</label>
                        <input class="form-input" type="text" id="name" name="name" required>
                    </div>
                    <div class="v-cont form-group">
                        <label class="form-label" for="surname">Prezime</label>
                        <input class="form-input" type="text" id="surname" name="surname" required>
                    </div>
                </div>
				
				<div class="h-cont h-gap">
    
                    <div class="v-cont form-group fg-1">
                        <label for="userType" class="form-label">Tip korisnika</label>
                        <div class="custom-select">
                            <select id="userType" name="userType">
                                <option value="F" id="F">Obični korisnik</option>
                                <option value="S" id="S">Savjetnik</option>
                            </select>
                            <!-- <span class="arrow icon"></span> -->
                        </div>
                    </div>
                </div>
				
                <div class="v-cont form-group">
                    <label class="form-label" for="password">Lozinka</label>
                    <input class="form-input" type="password" id="password" name="password" required>
                </div>

                <div class="h-cont h-gap">
                    <div class="v-cont form-group">
                        <label class="form-label" for="city">Grad</label>
                        <input class="form-input" type="text" id="city" name="city">
                    </div>
                    <div class="v-cont form-group">
                        <label class="form-label" for="mail">E-mail</label>
                        <input class="form-input" type="email" id="mail" name="mail" required>
                    </div>
                </div>
            </form>
            <div class="h-cont btn-cont">
                <button class="btn-style btn-small m-2 bg-secondary" onclick="closeModal('changeModal')">Poništi</button>
                <button class="btn-style btn-small m-2 bg-accent" onclick="changeUser()">Izmjena</button>
            </div>
        </dialog>