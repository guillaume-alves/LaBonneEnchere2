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

<h2 class="flex_col_center">Inscription</h2>
<form method="post" action="Inscription" class="flex_col_center">

    <div class="flex_row_center">
        <div class="flex_col_center">
            <label>
                Pseudo <span class="requis">*</span><br/>
                <input type="text" name="userNickname" value="<c:out value="${user.userNickname}"/>" size="20" maxlength="60" /><br>
                <span class="error">${um.errors['userNickname']}</span>
            </label><br>
            <label>
                Prénom <span class="requis">*</span><br/>
                <input type="text" name="userFirstname" value="<c:out value="${user.userFirstname}"/>" size="20" maxlength="60" /><br>
                <span class="error">${um.errors['userFirstname']}</span>
            </label><br>
            <label>
                Telephone <span class="requis">*</span><br/>
                <input type="tel" name="userPhone" value="<c:out value="${user.userPhone}"/>" size="20" maxlength="60" /><br>
                <span class="error">${um.errors['userPhone']}</span>
            </label><br>
            <label>
                Code postal <span class="requis">*</span><br/>
                <input type="text" name="userPostalCode" value="<c:out value="${user.userPostalCode}"/>" size="20" maxlength="60" /><br>
                <span class="error">${um.errors['userPostalCode']}</span>
            </label><br>
            <label>
                Mot de passe <span class="requis">*</span><br/>
                <input type="password" name="userPassword" value="" size="20" maxlength="20" /><br>
                <span class="error">${um.errors['userPassword']}</span>
            </label><br>
        </div>
        <div class="flex_col_center">
            <label>
                Nom <span class="requis">*</span><br/>
                <input type="text" name="userName" value="<c:out value="${user.userName}"/>" size="20" maxlength="20" /><br>
                <span class="error">${um.errors['userName']}</span>
            </label><br>
            <label>
                Adresse email <span class="requis">*</span><br/>
                <input type="email" name="userEmail" value="<c:out value="${user.userEmail}"/>" size="20" maxlength="60" /><br>
                <span class="error">${um.errors['userEmail']}</span>
            </label><br>
            <label>
                Rue <span class="requis">*</span><br/>
                <input type="text" name="userStreet" value="<c:out value="${user.userStreet}"/>" size="20" maxlength="60" /><br>
                <span class="error">${um.errors['userStreet']}</span>
            </label><br>
            <label>
                Ville <span class="requis">*</span><br/>
                <input type="text" name="userCity" value="<c:out value="${user.userCity}"/>" size="20" maxlength="60" /><br>
                <span class="error">${um.errors['userCity']}</span>
            </label><br>
            <label>
                Confirmation du mot de passe <span class="requis">*</span><br/>
                <input type="password" name="userConfirmation" value="" size="20" maxlength="20" /><br>
                <span class="error">${um.errors['userConfirmation']}</span>
            </label><br>
        </div>
    </div>
    <div class="flex_row_center">
        <a href="
            <c:url value="Accueil">
            </c:url>"
            >Annuler</a>
        <input type="submit" value="Créer" class="sansLabel" />
        <p class="${empty um.errors ? 'success' : 'error'}">${um.result}</p>
    </div>
</form>
</body>
</html>