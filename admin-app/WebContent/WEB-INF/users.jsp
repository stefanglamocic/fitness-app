<%@page import="dto.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" type="beans.UserBean" scope="session"/>
<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>

<jsp:include page="/WEB-INF/dialogs/user_dialog.jsp" flush="true"></jsp:include>
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
				<th>Grad</th>
				<th>E-mail</th>
				<th>Aktiviran</th>
			</tr>
			<% 
				List<User> users = userBean.getFitnessAppUsers();
				for (User u : users) {
			%>
				<tr>
					<td> <%= u.getName() %></td>
					<td> <%= u.getSurname() %></td>
					<td> <%= u.getUsername() %> </td>
					<td> <%= u.getCity() %> </td>
					<td> <%= u.getMail() %> </td>
					<td> <%= u.getActivated() %> </td>
					<td><a href="#" class="icon btn-link btn-change clr-accent f-3"></a></td>
                    <td><a href="#" class="icon btn-link btn-remove clr-red f-3"></a></td>
				</tr>
			<% } %>
		</table>
	</div>
</div>
</div>

<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"></jsp:include>