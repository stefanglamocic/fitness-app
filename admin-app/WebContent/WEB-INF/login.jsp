<%@page import="beans.LoginResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrator</title>
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
	<% String notification = (String) session.getAttribute("notification"); %>
<body class="v-cont  maina-center crossa-center bg-img">
    <div class="form-cont v-cont crossa-center">
        <p class="form-title m-4">Unesite korisničke podatke</p>
        <div style="flex-grow: 1;"></div>
        <form method="POST" action="?action=login" id="login" class="form v-cont full-width">
            <div class="v-cont form-group">
                <label class="form-label" for="username">
                    Korisničko ime 
                    	<% if(LoginResult.USERNAME_NOT_FOUND.toString().equals(notification)) { %>
                    	<span class="msg-span icon">
                    		<%= notification %>
                    	</span>
                    	<% } %>
                    </label>
                <input class="form-input" type="text" id="username" name="username">
            </div>

            <div class="v-cont form-group">
                <label class="form-label" for="password">
                    Lozinka
                    	<% if(LoginResult.WRONG_PASSWORD.toString().equals(notification)) {%>
                    		<span class="msg-span icon">
                    			<%= notification %>
                    		</span>
                    	<% } %>
                   </label>
                <input class="form-input" type="password" id="password" name="password">
            </div>
        </form>
        <button class="btn-style m-4 bg-accent btn-large 
            full-width" type="submit" form="login">Prijavi se</button>
    </div>
</body>
</html>