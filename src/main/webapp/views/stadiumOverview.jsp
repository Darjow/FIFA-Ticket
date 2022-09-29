<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fifa World Cup Qatar 2022: Stadiumoverview van ${stadium.name}</title>
</head>
<body>
<spring:url value="/fifa/" var="fifaUri"></spring:url>

	<h1>Stadion: ${stadium.name}</h1>
	<table>
	<thead>	
		<tr>	<th>Nr</th>		<th colspan="3">Voetbalmatch</th>		<th>Datum</th>		<th>Aftrap</th>		<th>Tickets</th></tr>
	</thead>
	<tbody>
		<c:forEach items="${games}" var="ticket" varStatus="status">
			<tr><td><a href="${fifaUri}${ticket.wedstrijd.id}">${status.count}</a></td><td colspan="3">${ticket.wedstrijd.landen[0]}-${ticket.wedstrijd.landen[1]}</td><td>${ticket.wedstrijd.dag} november</td><td>${ticket.wedstrijd.uur}:00</td><td>${ticket.tickets}</td></tr>
			
		</c:forEach>
		</tbody>
	</table>
	<form action='logout' method='post'>
		<input type="submit" value="Log out" /> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>