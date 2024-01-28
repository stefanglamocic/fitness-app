<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<dialog class="modal" id="modal">
            <h2 class="modal-title">Dodajte novog korisnika</h2>
            <form method="post" action="?action=add-user" class="form v-cont modal-form" id="userForm">
                <div class="h-cont h-gap">
                    <div class="v-cont form-group">
                        <label class="form-label" for="name">Ime</label>
                        <input class="form-input" type="text" id="name" name="name">
                    </div>
                    <div class="v-cont form-group">
                        <label class="form-label" for="surname">Prezime</label>
                        <input class="form-input" type="text" id="surname" name="surname">
                    </div>
                </div>

                <div class="v-cont form-group">
                    <label class="form-label" for="username">Korisničko ime</label>
                    <input class="form-input" type="text" id="username" name="username">
                </div>
                <div class="v-cont form-group">
                    <label class="form-label" for="password">Lozinka</label>
                    <input class="form-input" type="password" id="password" name="password">
                </div>

                <div class="h-cont h-gap">
                    <div class="v-cont form-group">
                        <label class="form-label" for="city">Grad</label>
                        <input class="form-input" type="text" id="city" name="city">
                    </div>
                    <div class="v-cont form-group">
                        <label class="form-label" for="mail">E-mail</label>
                        <input class="form-input" type="email" id="mail" name="mail">
                    </div>
                </div>
            </form>
            <div class="h-cont btn-cont">
                <button type="submit" form="userForm" class="btn-style btn-small m-2 bg-secondary"
                	formmethod="dialog">Poništi</button>
                <button type="submit" form="userForm" class="btn-style btn-small m-2 bg-accent">Sačuvaj</button>
            </div>
        </dialog>
