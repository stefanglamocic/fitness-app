<%@page import="dto.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" type="beans.UserBean" scope="session"/>
<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>

<jsp:include page="/WEB-INF/dialogs/user_dialog.jsp" flush="true"></jsp:include>
<jsp:include page="/WEB-INF/dialogs/pop_up.jsp" flush="true"></jsp:include>
<!-- page body -->
<div class="v-cont table-cont">
	<button class="btn-style btn-small m-1 bg-accent icon btn-icon btn-add"
		onclick="showModal()">Dodaj korisnika</button>
	<div class="table-wrapper">
		<table id="usersTable">
			<tr>
				<th>Ime</th>
				<th>Prezime</th>
				<th>Korisničko ime</th>
				<th>Tip korisnika</th>
				<th>Grad</th>
				<th>E-mail</th>
				<th>Aktiviran</th>
			</tr>
			<% 
				List<User> users = userBean.getUsersNotOfTypeAdmin();
				for (User u : users) {
			%>
				<tr id="<%= u.getUsername() %>">
					<td> <%= u.getName() %></td>
					<td> <%= u.getSurname() %></td>
					<td> <%= u.getUsername() %> </td>
					<td> <%= u.getUserType() %> </td>
					<td> <%= u.getCity() %> </td>
					<td> <%= u.getMail() %> </td>
					<td>
						<input type="checkbox" class="btn-check" id="<%= u.getUsername() %>Ch-b"
							<% if (u.getActivated()) {%>
								checked
							<% } %>>
                        <label for="<%= u.getUsername() %>Ch-b" class="icon clr-text check-lbl"></label>
					</td>
					<td><a href="#" class="icon btn-link btn-change clr-accent f-3" id="<%= u.getUsername() %>C"></a></td>
                    <td><a href="#" class="icon btn-link btn-remove clr-red f-3" id="<%= u.getUsername() %>R"></a></td>
				</tr>
			<% } %>
		</table>
	</div>
</div>
</div>

<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"></jsp:include>