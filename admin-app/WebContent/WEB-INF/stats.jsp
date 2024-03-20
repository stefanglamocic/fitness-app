<%@page import="java.util.List"%>
<%@page import="util.Logging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="userBean" type="beans.UserBean" scope="session"/>
<jsp:include page="/WEB-INF/common/header.jsp" flush="true"></jsp:include>
	<%
		List<String> logs = Logging.readLogs();
		if (logs.size() == 0) {
	%>
		<div style="font-size: 1.8rem; color: var(--clr-primary); margin: .6rem">
			Trenutno nema dostupne statistike!
		</div>
	<%
		}
	%>
	<%
		for (int i = 0; i < logs.size(); i++) {
	%>
		<div class="pre <%= i % 2 == 0 ? "even-div" : "odd-div" %>">
			<span class="date"> <%= logs.get(i).substring(0, 20) %> </span>
			<%= logs.get(i).substring(20) %>
		</div>
	<% } %>
<jsp:include page="/WEB-INF/common/footer.jsp" flush="true"></jsp:include>