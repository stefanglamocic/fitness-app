<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<% String currentPage = (String)session.getAttribute("page"); %>

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
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css" />
    <link rel="stylesheet" type="text/css" href="styles/base.css">
    <link rel="stylesheet" type="text/css" href="styles/header.css">
    <link rel="stylesheet" type="text/css" href="styles/search_bar.css">
    <link rel="stylesheet" type="text/css" href="styles/buttons.css">
    <link rel="stylesheet" type="text/css" href="styles/table.css">
    <link rel="stylesheet" type="text/css" href="styles/modal.css">
    <script type="application/javascript" src="scripts/script.js"></script>
</head>

<body class="v-cont" onload="init()">
    <div class="header">
        <input type="checkbox" id="nav-toggle" class="nav-toggle">
        <label for="nav-toggle" class="nav-toggle-lbl icon"></label>
        <ul class="header-menu">
            <li <% if ("first".equals(currentPage)) { %>
            	class="activated"
            	<% } %>><a href="?action=programs">Kategorije</a></li>
            <li <% if ("second".equals(currentPage)) { %>
            	class="activated"
            	<% } %>><a href="?action=users">Korisnici</a></li>
            <li <% if ("third".equals(currentPage)) { %>
            	class="activated"
            	<% } %>><a href="?action=stats">Statistika</a></li>
        </ul>
        <div style="flex-grow: 1;"></div>
        <a href="?action=logout" class="icon btn-link btn-logout"></a>
    </div>
    <div class="main-cont v-cont">
