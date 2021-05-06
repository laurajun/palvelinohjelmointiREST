<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@include  file="../htmlstart.html" %>
<form action="/UpdateCandidate" method="post">
<table border="0" class="tabledb">
	<tr>
		<td><b>ID</b></td>
		<td><b>Etunimi</b></td>
		<td><b>Sukunimi</b></td>
		<td><b>Puolue</b></td>
		<td><b>Ehdokasnumero</b></td>
		<td><b>Katuosoite</b></td>
		<td><b>Postinumero</b></td>
		<td><b>Kaupunki</b></td>
		<td><b>Kommentti</b></td>
		<td><b>Toiminnot</b></td>
	</tr>
<tr>
	<td><input type="hidden" name="id" value="${requestScope.candidate.id}">${requestScope.candidate.id}</td>
	<td><input type='text' name='firstname' value='${requestScope.candidate.firstname}'></td>
	<td><input type='text' name='lastname' value='${requestScope.candidate.lastname}'></td>
	<td><input type='text' name='party' value='${requestScope.candidate.party}'></td>
	<td><input type='text' name='candidatenumber' value='${requestScope.candidate.candidateNr}'></td>
	<td><input type='text' name='streetaddress' value='${requestScope.candidate.streetAddr}'></td>
	<td><input type='text' name='zipcode' value='${requestScope.candidate.zipCode}'></td>
	<td><input type='text' name='city' value='${requestScope.candidate.city}'></td>
	<td><input type='textarea' name='why' value='${requestScope.candidate.notes}'></td>
	<td><input type='submit' name='ok' value='Päivitä'></td>
</tr>
</table>
</form>
<%@include  file="../htmlend.html" %>