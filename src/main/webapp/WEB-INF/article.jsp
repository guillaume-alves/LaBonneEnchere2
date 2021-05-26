<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

  <h2 class="flex_col_center">Nouvelle Vente</h2>
  <section class="flex_row_center">
    <div>
      <img src="../image/pc.jpg" alt="image_bid">
    </div>
<%--    <form method="post" action="" class="flex_col_center">--%>
    <div>
      <label>Article :</label>
      <input type="text" value="<c:out value="${article.getArticleName()}"/>" size="20" maxlength="60" readonly="readonly"/><br>
      
      <label>Description :</label>
      <textarea rows="4" cols="30"><c:out value="${article.getArticleDescription()}"/></textarea><br>
      
      <label>Catégorie :</label>
      <input type="text" value="<c:out value="${article.getArticleCategory().getCategoryName()}"/>" size="20" maxlength="60" readonly="readonly"/><br>
      
      <label>Meilleur offre :</label>
      <input type="text" value="<c:out value="${article.getArticleEndPrice()}"/> points" size="20" maxlength="60" readonly="readonly"/>
      <span>par</span>
      <input type="text" value="<c:out value="${bid.getBidUser().getUserNickname()}"/>" size="20" maxlength="60" readonly="readonly"/><br>
      
      <label>Mise à prix :</label>
      <input type="text" value="<c:out value="${article.getArticleStartPrice()}"/> points" size="20" maxlength="60" readonly="readonly"/><br>
      
      <label>Fin de l'enchère :</label>
      <input type="text" value="<c:out value="${article.getArticleBidEndDate()}"/>" size="20" maxlength="60" readonly="readonly"/><br>
  
      <fieldset>
      <legend>Retrait</legend>
      
      <label>Rue :</label>
      <input type="text" value="<c:out value="${article.getArticleUser().getUserStreet()}"/>" size="20" maxlength="60" readonly="readonly"/><br>
      
      <label>Code postal :</label>
      <input type="text" value="<c:out value="${article.getArticleUser().getUserPostalCode()}"/>" size="20" maxlength="60" readonly="readonly"/><br>
      
      <label>Ville :</label>
      <input type="text" value="<c:out value="${article.getArticleUser().getUserCity()}"/>" size="20" maxlength="60" readonly="readonly"/><br>
     
      </fieldset>
      
      <label>Vendeur :</label>
      <input type="text" value="<c:out value="${article.getArticleUser().getUserNickname()}"/>" size="20" maxlength="60" readonly="readonly"/><br>
      
      <div>
      <c:choose>
      <c:when test="${article.getArticleUserId() != sessionScope.sessionUser.userId && article.getArticleBidEndDate() >= now}">
        <c:if test="${!empty sessionScope.sessionUser}">
	      <form method="post" action="MakeBid">
		  <label>Ma proposition :</label>
		  <input type="number" name="bidPrice" value="<c:out value="${article.articleEndPrice}"/>" ><br>
		  <span class="error">${am.errors['bidPrice']}</span>
		      
		  <!-- user ID stored in session -->
		  <input class="hide" type="text" name="userId" value="<c:out value="${sessionScope.sessionUser.userId}"/>" readonly="readonly">
		  
		  <!-- user credit stored in session -->
		  <input class="hide" type="number" name="userCredit" value="<c:out value="${sessionScope.sessionUser.userCredit}"/>" readonly="readonly">
		  
		  <!-- userId of the current winning bidder -->
		  <input class="hide" type="text" name="userOldId" value="<c:out value="${bid.getBidUser().getUserId()}"/>" size="20" maxlength="60" readonly="readonly"/><br>
		  
		  <!-- article current price -->
		  <input class="hide" type="number" name="articleEndPrice" value="<c:out value="${article.articleEndPrice}"/>" readonly="readonly">
		  
		  <!-- article ID -->
		  <input class="hide" type="text" name="articleId" value="<c:out value="${article.getArticleId()}"/>" readonly="readonly">
	      
	      <input type="submit" value="Enchérir" class="" />
	      </form>
	      <p class="${empty bm.errors ? 'success' : 'error'}">${bm.result}</p>
      	</c:if>
      </c:when>
      
      <c:when test="${article.getArticleUserId() == sessionScope.sessionUser.userId && article.getArticleBidEndDate() >= now}">
        <input type="submit" value="Modifier"/>
      </c:when>
      
      <c:otherwise>
        <input type="submit" value="Vente terminée"/>
      </c:otherwise>
    </c:choose>
    </div>
   </div>
  </section>
</body>
</html>
