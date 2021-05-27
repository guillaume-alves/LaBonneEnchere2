<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="inc/form.css">
<title>Mes achats</title>
</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param name="title" value="ENI-Enchere" />
	</jsp:include>

	<h2 class="flex_col_center">Mes achats</h2>

	<jsp:include page="enchere.jsp">
		<jsp:param name="article" value="${list_articles}" />
	</jsp:include>

	<script src="inc/script.js"></script>
</body>
</html>
