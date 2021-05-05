<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include  file="../htmlstart.html" %>
<% if (session.getAttribute("username") != null) { %>


        <h2>Tervetuloa Vaalikoneeseen</h2>
        <br><br>
        <a href="/ListAllCandidates">Listaa kaikki ehdokkaat</a><br><br>
        <a href="addCandidate.html">Lisää uusi ehdokas</a><br><br>
        <a href="/logout">Logout</a>
<% } else {%>
    <p> Not logged in </p>
<% } %>
<%@include  file="../htmlend.html" %>