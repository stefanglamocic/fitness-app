<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<dialog class="modal" id="msgModal">
	<div class="v-cont crossa-center">
		<h2 class="modal-title">Poruka</h2>
		<h3 class="info" id="from"></h3>
		<h3 class="info" id="timeSent"></h3>
		<hr class="hr-line">
		<p class="msg-content"></p>
		<hr class="hr-line">
		<button class="btn-style btn-small m-2 bg-accent" onclick="closeModal('msgModal')">OK</button>
	</div>
</dialog>