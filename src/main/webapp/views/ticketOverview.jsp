<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<spring:url value="/css/style.css" var="urlCss" />
<spring:url value="/fifa" var="fifaUri"></spring:url>
<link rel="STYLESHEET" href="${urlCss}" type="text/css" />

<title>Fifa World Cup Qatar 2022: Tickets kopen</title>
</head>
<body>
<h1>Fifa World Cup Qatar 2022</h1>
<h2>Stadion: ${stadium.name}</h2>
<h3>${ticket.wedstrijd.toString()}</h3>
<h3>aantal tickets beschikbaar: ${ticket.tickets} </h3>
	
	<form:form method="POST" action="${fifaUri}/${ticket.wedstrijd.id }" modelAttribute="formInput">
       	<table>
            <tr>
	           <td><label>email: </label></td>
	           <td><form:input path="email"/></td>
	           <td><form:errors path="email" cssClass="error"/></td>
	         </tr>
	         <tr>
	         	<td><label>Aantal tickets:</label></td>
	            <td><form:input path="aantalTickets"/></td>
	            <td><form:errors path="aantalTickets" cssClass="error"/></td>
	         </tr>
	         <tr>
	            <td><label>voetbalCode1:</label></td>
	            <td><form:input path="voetbalCode1"/></td>
	            <td><form:errors path="voetbalCode1" cssClass="error"/></td>
	    	 </tr>
	    	 <tr>
	            <td><label>voetbalCode2:</label></td>
	            <td><form:input path="voetbalCode2"/></td>
	            <td><form:errors path="voetbalCode2" cssClass="error"/></td>
	         </tr>
	    </table>  
	<input type="submit" value="Koop" />
 </form:form>
    
</body>
</html>