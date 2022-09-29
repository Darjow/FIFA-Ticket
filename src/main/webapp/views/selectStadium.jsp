<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fifa World Cup Qatar 2022: Selecteer een stadium</title>
</head>
<body>


	<c:if test="${not empty bought}">
		<p>${bought}</p>
	</c:if>
	
		<c:if test="${not empty uitverkocht}">
		<p>${uitverkocht}</p>
	</c:if>
	
	
	<h1>Fifa World Cup Qatar 2022</h1>
	<form:form method="POST" action = "/fifa" modelAttribute="stadium">
		<table>
		<tr>
			<td>Stadium</td>
			<td>
				<form:select path="name" multiple="false">
					<form:options items = "${stadiumList}"/>
				</form:select>  
			</td>
		</tr>
		</table>
		<br>
		<input type="submit" value="Voer uit" /> 
	</form:form>
	
	<form action='logout' method='post'>
		<input type="submit" value="Log out" /> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>