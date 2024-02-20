<%@page import="beans.CategoryBean"%>
<%@page import="dto.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" type="beans.UserBean" scope="session"/>
<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
<script type="application/javascript" src="scripts/categories-script.js"></script>
<jsp:include page="/WEB-INF/dialogs/add_category_dialog.jsp" flush="true"></jsp:include>
<jsp:include page="/WEB-INF/dialogs/pop_up.jsp" flush="true"></jsp:include>

<div class="v-cont table-cont">
	<button class="btn-style btn-small m-1 bg-accent icon btn-icon btn-add"
		onclick="showAddCategoryModal()">Nova kategorija</button>
	<div class="table-wrapper">
		<table id="categoriesTable">
			<tr>
				<th>Kategorija</th>
				<th>Atributi</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
			<%
				CategoryBean categoryBean = userBean.getCategoryBean();
				for (Category c : categoryBean.getAll()) {
			%>
				<tr id="<%= c.getId() %>">
					<td> <%= c.getName() %> </td>
					<td> <%= c.buildAttributes() %> </td>
					<td> 
						<a href="#" class="icon btn-link btn-change clr-accent f-3" onclick="showEditCategoryModal(event)"></a>
					</td>
					<td>
						<a href="#" class="icon btn-link btn-remove clr-red f-3" onclick="showRemoveDialog(event)"></a>
					</td>
				</tr>
			<% } %>
		</table>
	</div>

</div>
</div>
<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"></jsp:include>