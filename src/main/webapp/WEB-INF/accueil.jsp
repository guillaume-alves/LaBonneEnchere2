<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="inc/form.css">
<title>Accueil</title>
</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param name="title" value="ENI-Enchere" />
	</jsp:include>

	<h2 class="flex_col_center">Liste des enchères</h2>
	<section class="search_area">
		<form method="post" action="Accueil" class="flex_row_center">
			<div class="research_zone">
				<div>
					<label for="article">Recherche :</label>
					<input type="text" id="article" name="articleNameSearch" value="" placeholder="Le nom de l'article contient" size="30"/>
				</div>
				
				<label for="selectCategory">Catégorie :</label>
				<select id="selectCategory" name="articleCategoryId" tabindex="30">
					<option value="noCategory">--Choisissez une catégorie--</option>
					<c:forEach var="category" items="${list_categories}">
						<option value="${category.categoryId}"><c:out value="${category.categoryName}" /></option>
					</c:forEach>
				</select><br>
			</div>
			
			<div>
				<input type="submit" value="Rechercher" />
			</div>
		</form>
	</section>

	<jsp:include page="enchere.jsp">
		<jsp:param name="article" value="${list_articles}" />
	</jsp:include>

	<script src="inc/script.js"></script>
</body>
</html>
