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
		<h2 class="modal-title">Odgovor</h2>
        <textarea id="reply" class="form-input" placeholder="Vaša poruka..."></textarea>
        <div class="h-cont maina-end f-width g-2" style="margin-bottom: 2rem;">
			<label for="attachment" class="lbl-to-btn btn-style btn-small bg-secondary icon btn-icon btn-upload">Prikači datoteku</label>
			<input type="file" id="attachment" name="attachment" accept="image/*, .pdf, .doc" hidden>
			<button id="replyBtn" class="btn-style btn-small bg-accent icon btn-icon btn-mail">Odgovori</button>
        </div>
		<button class="btn-style btn-small m-2 bg-accent" onclick="closeModal('msgModal')">OK</button>
	</div>
</dialog>