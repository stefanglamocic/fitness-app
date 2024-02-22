<%@page import="util.LoginResult"%>
<%@page import="beans.UserBean"%>
<%@page import="dao.UserDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" class="beans.UserBean" scope="session"/>

<% 
	LoginResult loginResult = null;
	if (request.getParameter("submit") != null) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		loginResult = userBean.login(username, password);
		if (loginResult == LoginResult.SUCCESS)
			response.sendRedirect("messages.jsp");
	}
%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Savjetnik</title>
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:ital,wght@0,400;0,700;1,400;1,700&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="styles/base.css">
    <link rel="stylesheet" href="styles/buttons.css">
    <link rel="stylesheet" href="styles/form.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css" />
    <style>
    	.msg-span {
    		color: red;
    	}
    	
    	.msg-span::before {
    		content: var(--icn-error);
    	}
    </style>
</head>
<body class="v-cont  maina-center crossa-center bg-img">
    <div class="form-cont v-cont crossa-center">
        <p class="form-title m-4">Unesite korisničke podatke</p>
        <div style="flex-grow: 1;"></div>
        <form method="POST" action="login.jsp" id="login" class="form v-cont full-width">
            <div class="v-cont form-group">
                <label class="form-label" for="username">
                    Korisničko ime 
                    <%
                    	if(loginResult != null && 
                    		(loginResult == LoginResult.NO_USER || 
                    			loginResult == LoginResult.WRONG_USERTYPE || loginResult == LoginResult.NOT_ACTIVATED)) {
                    %>
                    	<span class="msg-span icon"><%= loginResult.getMessage() %></span>
                    <% } %>
                    </label>
                <input class="form-input" type="text" id="username" name="username">
            </div>

            <div class="v-cont form-group">
                <label class="form-label" for="password">
                    Lozinka
                    <% 
                    	if (loginResult != null && loginResult == LoginResult.WRONG_PASSWORD) {
                    %>
                    	<span class="msg-span icon"><%= loginResult.getMessage() %></span>
                    <% } %>
                   </label>
                <input class="form-input" type="password" id="password" name="password">
            </div>
        </form>
        <button class="btn-style m-4 bg-accent btn-large 
            full-width" type="submit" form="login" name="submit">Prijavi se</button>
    </div>
</body>
</html>