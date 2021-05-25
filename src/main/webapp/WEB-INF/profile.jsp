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

  <h2 class="flex_col_center">Profil</h2>
  <div class="flex_row_center">
    <div class="flex_col_center quart">
      <label>
        Pseudo<br/>
        <input type="text" name="nickname" value="<c:out value="${user.userNickname}"/>" size="20" maxlength="60" disabled="disabled"/>
      </label>
      <label>
        Prénom<br/>
        <input type="text" name="firstname" value="<c:out value="${user.userFirstname}"/>" size="20" maxlength="60" disabled="disabled"/>
      </label>
      <label>
        Telephone<br/>
        <input type="tel" name="telephone" value="<c:out value="${user.userPhone}"/>" size="20" maxlength="60" disabled="disabled"/>
      </label>
      <label>
        Code postal<br/>
        <input type="text" name="postalCode" value="<c:out value="${user.userPostalCode}"/>" size="20" maxlength="60" disabled="disabled"/>
      </label>
    </div>
    <div class="flex_col_center quart">
      <label>
        Nom<br/>
        <input type="text" name="username" value="<c:out value="${user.userName}"/>" size="20" maxlength="20" disabled="disabled"/>
      </label>
      <label>
        Adresse email<br/>
        <input type="email" name="email" value="<c:out value="${user.userEmail}"/>" size="20" maxlength="60" disabled="disabled"/>
      </label>
      <label>
        Rue<br/>
        <input type="text" name="street" value="<c:out value="${user.userStreet}"/>" size="20" maxlength="60" disabled="disabled"/>
      </label>
      <label>
        Ville<br/>
        <input type="text" name="city" value="<c:out value="${user.userCity}"/>" size="20" maxlength="60" disabled="disabled"/>
      </label>
    </div>
  </div>
  
  <div>
	   <c:if test="${sessionScope.sessionUser.userId == user.userId}">
	   	<a href="
			<c:url value="MyProfile">
			<c:param name="userId" value="${user.userId}"/>
			</c:url>"
			>Modifier mon profil</a>
	   </c:if>
   </div>
</body>
</html>