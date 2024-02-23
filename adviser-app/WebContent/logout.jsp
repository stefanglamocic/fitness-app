<jsp:useBean id="userBean" class="beans.UserBean" scope="session"/>

<%
	if (userBean != null)
		userBean.setLoggedIn(false);
	response.sendRedirect("login.jsp");
%>