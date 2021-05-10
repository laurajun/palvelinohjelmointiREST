<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@include  file="../htmlstart.html" %>
<table border="0" class="tabledb">
	<tr>
		<td><b>ID</b></td>
		<td><b>Etunimi</b></td>
		<td><b>Sukunimi</b></td>
		<td><b>Puolue</b></td>
		<td><b>Ehdokasnumero</b></td>
		<td><b>Osoite</b></td>
		<td><b>Kommentti</b></td>
		<td><b>Kuva</b></td>
		<td><b>Toiminnot</b></td>
	</tr>
<tr>
	<td>${requestScope.candidate.id}</td>
	<td>${requestScope.candidate.firstname}</td>
	<td>${requestScope.candidate.lastname}</td>
	<td>${requestScope.candidate.party}</td>
	<td>${requestScope.candidate.candidateNr}</td>
	<td>${requestScope.candidate.streetAddr}<br>${requestScope.candidate.zipCode} ${requestScope.candidate.city}</td>
	<td>${requestScope.candidate.notes}</td>
	<td><form action="./rest/vaalikoneservice/fileupload" method="post" enctype="multipart/form-data"><input type="file" name="file" accept="image/*" /><br><input type="hidden" name="id" value="${requestScope.candidate.id}"><input type="submit" value="Upload" /></form></td>
	<td><a href="/UpdateCandidate?id=${requestScope.candidate.id}">Muokkaa</a><br><a href="/DeleteCandidate?id=${requestScope.candidate.id}" onclick="return window.confirm('Oletko varma?');">Poista</a></td>
</tr>
</table>
<%@include  file="../htmlend.html" %>