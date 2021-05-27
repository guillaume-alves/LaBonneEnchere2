<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <link type="text/css" rel="stylesheet" href="inc/form.css">
  <title>ENI-ENCHERE</title>
</head>
<body>
	
	<jsp:include page="header.jsp">
		<jsp:param name="title" value="ENI-Enchere"/>
  	</jsp:include>

<c:choose>
	<c:when test="${article.getArticleBidEndDate() >= now}">
		 <h2 class="flex_col_center">Détail Vente</h2>
	</c:when>
	<c:otherwise>
		 <h2 class="flex_col_center"><c:out value="${bid.getBidUser().getUserNickname()}"/> a remporté la vente</h2>
	</c:otherwise>
</c:choose>
 
  <section class="flex_row_center">
    	<c:choose>
			<c:when test="${article.articleCategoryId == 1}">
				<div class="img-ench">
					<img class="image" src="inc/laptop.png" alt="img" width="200" />
				</div>
			</c:when>
			<c:when test="${article.articleCategoryId == 2}">
				<div class="img-ench">
					<img class="image" src="inc/electro.png" alt="img" width="200" />
				</div>
			</c:when>
			<c:when test="${article.articleCategoryId == 3}">
				<div class="img-ench">
					<img class="image" src="inc/furnitures.png" alt="img" width="200" />
				</div>
			</c:when>
			<c:when test="${article.articleCategoryId == 4}">
				<div class="img-ench">
					<img class="image" src="inc/sport.png" alt="img" width="200" />
				</div>
			</c:when><c:when test="${article.articleCategoryId == 5}">
				<div class="img-ench">
					<img class="image" src="inc/toolbox.png" alt="img" width="200" />
				</div>
			</c:when>
		</c:choose>
    
    <div>
      <label>Article :</label>
      <input type="text" value="<c:out value="${article.getArticleName()}"/>" size="20" maxlength="60" readonly="readonly"/><br>
      
      <label>Description :</label>
      <textarea rows="4" cols="30" readonly="readonly"><c:out value="${article.getArticleDescription()}"/></textarea><br>
      
      <label>Catégorie :</label>
      <input type="text" value="<c:out value="${article.getArticleCategory().getCategoryName()}"/>" size="20" maxlength="60" readonly="readonly"/><br>
      
      <c:if test="${!empty bid.getBidUser().getUserId()}">
      <label>Meilleure offre :</label>
      <input type="text" value="<c:out value="${article.getArticleEndPrice()}"/> points" size="20" maxlength="60" readonly="readonly"/>
      <span>par</span>
      <a href="
      	<c:url value="Profile">
		<c:param name="userId" value="${bid.getBidUser().getUserId()}"/>
		</c:url>"
		><c:out value="${bid.getBidUser().getUserNickname()}"/></a> <br>
	  </c:if>
      
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
      	</c:if>
      </c:when>
      
      <c:when test="${article.getArticleUserId() == sessionScope.sessionUser.userId && article.getArticleBidEndDate() >= now}">
        <form method="get" action="CancelSell">
	        <!-- article ID -->
	        <input class="hide" type="text" name="articleId" value="<c:out value="${article.getArticleId()}"/>" readonly="readonly">
	        
	        <!-- userId of the current winning bidder -->
		  	<input class="hide" type="text" name="userOldId" value="<c:out value="${bid.getBidUser().getUserId()}"/>" size="20" maxlength="60" readonly="readonly"/><br>
	        
	        <input type="submit" value="Annuler la vente"/>
        </form> 
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
