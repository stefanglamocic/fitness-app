<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>


<dialog class="modal" id="modal">
	<h2 class="modal-title">Dodajte novog korisnika</h2>
	<div class="h-cont btn-cont">
		<button class="btn-style btn-small m-2 bg-secondary"
			onclick="closeModal()">Poništi</button>
		<button class="btn-style btn-small m-2 bg-accent">Sačuvaj</button>
	</div>
</dialog>

<!-- page body -->
<div class="v-cont table-cont">
	<button class="btn-style btn-small m-1 bg-accent icon btn-icon btn-add"
		onclick="showModal()">Dodaj korisnika</button>
	<div class="table-wrapper">
		<table>
			<tr>
				<th>Ime</th>
				<th>Prezime</th>
				<th>Korisničko ime</th>
				<th>Tip korisnika</th>
				<th>Grad</th>
				<th>E-mail</th>
				<th>Aktiviran</th>
			</tr>
		</table>
	</div>
</div>
</div>

<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"></jsp:include>