<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>ENI-ENCHERE</title>
    <link type="text/css" rel="stylesheet" href="inc/form.css" />
</head>
<body>

<jsp:include page="header.jsp">
    <jsp:param name="title" value="ENI-Enchere"/>
</jsp:include>

<h2 class="flex_col_center">Inscription</h2>

<form method="post" action="Inscription" class="">
    <div class="flex_row_center">
        <div>
            <label>Pseudo :</label><span class="required">*</span>
            <input type="text" name="userNickname" value="<c:out value="${user.userNickname}"/>" size="20" maxlength="60" /><br>
            <span class="error">${um.errors['userNickname']}</span><br>
            
            <label>Prénom :</label><span class="required">*</span>
            <input type="text" name="userFirstname" value="<c:out value="${user.userFirstname}"/>" size="20" maxlength="60" /><br>
            <span class="error">${um.errors['userFirstname']}</span><br>
            
            <label>Telephone :</label><span class="required">*</span>
            <input type="tel" name="userPhone" value="<c:out value="${user.userPhone}"/>" size="20" maxlength="60" /><br>
            <span class="error">${um.errors['userPhone']}</span><br>
            
            <label> Code postal :</label><span class="required">*</span>
            <input type="text" name="userPostalCode" value="<c:out value="${user.userPostalCode}"/>" size="20" maxlength="60" /><br>
            <span class="error">${um.errors['userPostalCode']}</span><br>
            
            <label>Mot de passe : </label><span class="required">*</span>
            <input type="password" name="userPassword" value="" size="20" maxlength="20" /><br>
            <span class="error">${um.errors['userPassword']}</span>  
 		</div>
 		
 		<div>
            <label>Nom :</label><span class="required">*</span>
            <input type="text" name="userName" value="<c:out value="${user.userName}"/>" size="20" maxlength="20" /><br>
            <span class="error">${um.errors['userName']}</span><br/>
       
            <label>Adresse email :</label><span class="required">*</span>
            <input type="email" name="userEmail" value="<c:out value="${user.userEmail}"/>" size="20" maxlength="60" /><br>
            <span class="error">${um.errors['userEmail']}</span> <br>
           
            <label>Rue :</label><span class="required">*</span>
            <input type="text" name="userStreet" value="<c:out value="${user.userStreet}"/>" size="20" maxlength="60" /><br>
            <span class="error">${um.errors['userStreet']}</span><br>
            
            <label>Ville :</label><span class="required">*</span>
            <input type="text" name="userCity" value="<c:out value="${user.userCity}"/>" size="20" maxlength="60" /><br>
            <span class="error">${um.errors['userCity']}</span><br>
            
            <label>Confirmation du mot de passe :</label><span class="required">*</span>
            <input type="password" name="userConfirmation" value="" size="20" maxlength="20" /><br>
            <span class="error">${um.errors['userConfirmation']}</span><br>
        </div>
    </div>
    <div class="flex_row_center">
        <a href="
            <c:url value="Accueil">
            </c:url>"
            >Annuler</a>
        <input type="submit" value="Créer" class="sansLabel" />
        
        <p class="${empty um.errors ? 'success' : 'error'}"><c:out value="${um.result}"/></p>
    </div>
</form>
<div class="flex_col_center">
	<p class="required">* champs requis</p>
</div>
</body>
</html>