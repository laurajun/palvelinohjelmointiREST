<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@include  file="../htmlstart.html" %>
<table class="tabledb">
	<tr>
		<td><b>ID</b></td><td><b>Etunimi</b></td><td><b>Sukunimi</b></td><td><b>Puolue</b></td><td><b>Toiminnot</b></td>
	</tr>
<c:forEach var="candidate" items="${requestScope.candidatelist }">
<tr>
	<td>${candidate.id}</td>
	<td>${candidate.firstname}</td>
	<td>${candidate.lastname}</td>
	<td>${candidate.party}</td>
	<td><a href="/ShowSingleCandidate?id=${candidate.id}">Näytä</a></td>
</tr>
</c:forEach>
</table>
<%@include  file="../htmlend.html" %>