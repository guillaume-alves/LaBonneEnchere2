<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <link type="text/css" rel="stylesheet" href="inc/form.css">
  <title>Title</title>
</head>
<body>

<jsp:include page="header.jsp">
  <jsp:param name="title" value="ENI-Enchere"/>
</jsp:include>

<form method="post" action="Connexion" class="flex_col_center">
  <div class="flex_col_center">
    <label>
      Pseudo
      <input type="text" name="userNickname" value="<c:out value="${user.userNickname}"/>" size="20" maxlength="20" /><br>
   	  <span class="error">${um.errors['userNickname']}</span>
    </label><br>
  </div>
  
  <div class="flex_col_center">
    <label>
      Mot de passe
      <input type="password" name="userPassword" value="" size="20" maxlength="20" /><br>
      <span class="error">${um.errors['userPassword']}</span>
    </label><br>
  </div>
  
  <div class="search">
    <div class="">
      <input type="submit" value="Connexion"/>
    </div>
    <div class="flex_col_center">
      <div class="search">
        <label>
          <input type="checkbox">
          Se souvenir de moi
        </label>
      </div>
      <div>
        <a href="">Mot de passe oublié</a>
      </div>
    </div>
  </div>

<span class="error">${um.errors['connection']}</span>
<p class="${empty um.errors ? 'success' : 'error'}">${um.result}</p>
</form>

<a href="${pageContext.request.contextPath}/Inscription" class="flex_col_center">Créer un compte</a>

</body>
</html>
