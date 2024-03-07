<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="dto.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" class="beans.UserBean" scope="session"/>

<% 
	if (userBean == null || !userBean.isLoggedIn())
		response.sendRedirect("login.jsp");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Savjetnik &bull; Poruke</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:ital,wght@0,400;0,700;1,400;1,700&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
        integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css" />
    <link rel="stylesheet" type="text/css" href="styles/base.css">
    <link rel="stylesheet" type="text/css" href="styles/header.css">
    <link rel="stylesheet" type="text/css" href="styles/search_bar.css">
    <link rel="stylesheet" type="text/css" href="styles/buttons.css">
    <link rel="stylesheet" type="text/css" href="styles/table.css">
    <link rel="stylesheet" type="text/css" href="styles/modal.css">
    <link rel="stylesheet" type="text/css" href="styles/form.css">
    <link rel="stylesheet" type="text/css" href="styles/select.css">
    <script type="application/javascript" src="scripts/script.js"></script>
</head>
<body class="v-cont" onload="init('<%= userBean.getUser().getUsername() %>')">
<jsp:include page="/WEB-INF/modals/msg-modal.jsp" flush="true"></jsp:include>
	<div class="header">
		<input type="checkbox" id="nav-toggle" class="nav-toggle">
        <label for="nav-toggle" class="nav-toggle-lbl icon"></label>
        <ul class="header-menu">
            <li class="activated"><a href="#">Poruke</a></li>
        </ul>
        <div style="flex-grow: 1;"></div>
        <div class="search-cont">
            <div class="search-bar icon">
                <input id="search" type="search" placeholder="Pretraga..." oninput="searchKeyPressed(event)">
            </div>
        </div>
        <a href="logout.jsp" class="icon btn-link btn-logout f-3 clr-text"></a>
	</div>
	
	<div class="main-cont v-cont">
		<div class="v-cont table-cont">
			<div class="table-wrapper">
				<table id="table">
					<tr id="headerRow">
						<th>Pošiljalac</th>
						<th>Datum</th>
						<th>Vrijeme</th>
						<th>Sadržaj</th>
					</tr>
					<%
						DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
						DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
						for(Message m : userBean.getMessages()) {
					%>
						<tr id="<%= m.getMessageId() %>" onclick="openMsg(event)"
							<% if (m.getOpened()) {%>
								class="msg-opened"
							<% } %>	
						>
							<td><%= m.getSender() %></td>
							<td><%= m.getTime().format(dateFormatter) %></td>
							<td><%= m.getTime().format(timeFormatter) %></td>
							<td><%= m.getShortVersion() %></td>
						</tr>
					<% } %>
				</table>
			</div>
		</div>
	</div>
</body>
</html>