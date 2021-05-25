<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Inscription</title>
  <link type="text/css" rel="stylesheet" href="inc/form.css" />
</head>
<body>

<jsp:include page="header.jsp">
  <jsp:param name="title" value="ENI-Enchere"/>
</jsp:include>

  <h2 class="flex_col_center">Modifier mon profil</h2>
  <form method="post" action="UpdateProfile">
  <div class="flex_row_center">
    <div class="flex_col_center quart">
      <input class="hide" type="text" name="userId" value="<c:out value="${sessionScope.sessionUser.userId}"/>" readonly="readonly">
      <label>
        Pseudo<br/>
        <input type="text" name="userNickname" value="<c:out value="${sessionScope.sessionUser.userNickname}"/>" size="20" maxlength="60" />
      	<span class="error">${um.errors['userNickname']}</span>
      </label>
      <label>
        Prénom<br/>
        <input type="text" name="userFirstname" value="<c:out value="${sessionScope.sessionUser.userFirstname}"/>" size="20" maxlength="60" />
      	<span class="error">${um.errors['userFirstname']}</span>
      </label>
      <label>
        Telephone<br/>
        <input type="tel" name="userPhone" value="<c:out value="${sessionScope.sessionUser.userPhone}"/>" size="20" maxlength="60" />
        <span class="error">${um.errors['userPhone']}</span>
      </label>
      <label>
        Code postal<br/>
        <input type="text" name="userPostalCode" value="<c:out value="${sessionScope.sessionUser.userPostalCode}"/>" size="20" maxlength="60" />
      	<span class="error">${um.errors['userPostalCode']}</span>
      </label>
      <label>
        Ancien ou nouveau mot de passe<br/>
        <input type="password" name="userPassword" value="<c:out value="${sessionScope.sessionUser.userCity}"/>" size="20" maxlength="20" />
        <span class="error">${um.errors['userPassword']}</span>
      </label><br/>
    </div>
    <div class="flex_col_center quart">
      <label>
        Nom<br/>
        <input type="text" name="userName" value="<c:out value="${sessionScope.sessionUser.userName}"/>" size="20" maxlength="20" />
      	<span class="error">${um.errors['userName']}</span>
      </label>
      <label>
        Adresse email<br/>
        <input type="email" name="userEmail" value="<c:out value="${sessionScope.sessionUser.userEmail}"/>" size="20" maxlength="60" />
      	<span class="error">${um.errors['userEmail']}</span>
      </label>
      <label>
        Rue<br/>
        <input type="text" name="userStreet" value="<c:out value="${sessionScope.sessionUser.userStreet}"/>" size="20" maxlength="60" />
      	<span class="error">${um.errors['userStreet']}</span>
      </label>
      <label>
        Ville<br/>
        <input type="text" name="userCity" value="<c:out value="${sessionScope.sessionUser.userCity}"/>" size="20" maxlength="60" />
      	<span class="error">${um.errors['userCity']}</span>
      </label>
      <label>
        Confirmation ancien ou nouveau mot de passe<br/>
        <input type="password" name="userConfirmation" value="" size="20" maxlength="20" />
        <span class="error">${um.errors['userConfirmation']}</span>
      </label><br/>
    </div>
  </div>
  <input type="submit" value="Enregister" class="" />
  <p class="${empty um.errors ? 'succes' : 'erreur'}">${um.result}</p>
</form>

<form method="get" action="Suppression">
	<input class="hide" type="text" name="userId" value="<c:out value="${sessionScope.sessionUser.userId}"/>" readonly="readonly">
	<input type="submit" value="Supprimer mon compte" class="" />
</form>

</body>
</html>