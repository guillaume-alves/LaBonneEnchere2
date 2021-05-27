<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="inc/form.css">
<title>ENI-ENCHERE</title>
</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param name="title" value="ENI-Enchere" />
	</jsp:include>

	<div class="flex_col_center">
		<form method="post" action="Connexion">
			<div>
				<label>Pseudo :</label> <input type="text" name="userNickname"
					value="<c:out value="${user.userNickname}"/>" size="20"
					maxlength="20" /><br> <span class="error">${um.errors['userNickname']}</span><br>

				<label>Mot de passe :</label> <input type="password"
					name="userPassword" value="" size="20" maxlength="20" /><br>
				<span class="error">${um.errors['userPassword']}</span><br>
			</div>
			<div class="connexion">
				<input type="submit" value="Connexion" /> <span class="error">${um.errors['connection']}</span>
				<p class="${empty um.errors ? 'success' : 'error'}">${um.result}</p>
			</div>
		</form>

		<a class="inscription"
			href="
		<c:url value="Inscription">
		</c:url>">Créer un compte</a>

	</div>
</body>
</html>
