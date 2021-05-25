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
  
  <div class="flex_col_center">
  <form method="post" action="UpdateProfile">
    
	<input class="hide" type="text" name="userId" value="<c:out value="${sessionScope.sessionUser.userId}"/>" readonly="readonly">
	
	<label>Pseudo :</label>
	<input type="text" name="userNickname" value="<c:out value="${sessionScope.sessionUser.userNickname}"/>" size="20" maxlength="60" />
	<span class="error">${um.errors['userNickname']}</span><br>
	
	<label>Prénom :</label> 
	<input type="text" name="userFirstname" value="<c:out value="${sessionScope.sessionUser.userFirstname}"/>" size="20" maxlength="60" />
	<span class="error">${um.errors['userFirstname']}</span><br>
	
	<label>Telephone :</label>
	<input type="tel" name="userPhone" value="<c:out value="${sessionScope.sessionUser.userPhone}"/>" size="20" maxlength="60" />
	<span class="error">${um.errors['userPhone']}</span><br>
	
	<label>Code postal :</label>
	<input type="text" name="userPostalCode" value="<c:out value="${sessionScope.sessionUser.userPostalCode}"/>" size="20" maxlength="60" />
	<span class="error">${um.errors['userPostalCode']}</span><br>
	
	<label>Ancien ou nouveau mot de passe :</label>
	<input type="password" name="userPassword" value="<c:out value="${sessionScope.sessionUser.userCity}"/>" size="20" maxlength="20" />
	<span class="error">${um.errors['userPassword']}</span><br><br><br>
      
	<label>Nom :</label>
	<input type="text" name="userName" value="<c:out value="${sessionScope.sessionUser.userName}"/>" size="20" maxlength="20" />
	<span class="error">${um.errors['userName']}</span><br>
	   
	<label>Adresse email :</label>
	<input type="email" name="userEmail" value="<c:out value="${sessionScope.sessionUser.userEmail}"/>" size="20" maxlength="60" />
	<span class="error">${um.errors['userEmail']}</span><br>
     
	<label>Rue :</label>
	<input type="text" name="userStreet" value="<c:out value="${sessionScope.sessionUser.userStreet}"/>" size="20" maxlength="60" />
	<span class="error">${um.errors['userStreet']}</span><br>
      
	<label>Ville :</label>
	<input type="text" name="userCity" value="<c:out value="${sessionScope.sessionUser.userCity}"/>" size="20" maxlength="60" />
	<span class="error">${um.errors['userCity']}</span><br>
	   
	<label>Confirmation ancien ou nouveau mot de passe :</label>
	<input type="password" name="userConfirmation" value="" size="20" maxlength="20" />
	<span class="error">${um.errors['userConfirmation']}</span><br>

	<input type="submit" value="Enregister" class="" />
  	<p class="${empty um.errors ? 'success' : 'error'}">${um.result}</p>
</form>
</div>

<div class="flex_col_center">
	<form method="get" action="Suppression">
		<input class="hide" type="text" name="userId" value="<c:out value="${sessionScope.sessionUser.userId}"/>" readonly="readonly">
		<input class="redbutton" type="submit" value="Supprimer mon compte" class="" />
	</form>
</div>
</body>
</html>