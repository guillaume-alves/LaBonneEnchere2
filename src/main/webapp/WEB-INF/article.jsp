<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <link type="text/css" rel="stylesheet" href="inc/form.css">
  <title>Title</title>
</head>
<body>
  <h2 class="flex_col_center">Nouvelle Vente</h2>
  <section class="flex_row_center">
    <div>
      <img src="../image/pc.jpg" alt="image_bid">
    </div>
<%--    <form method="post" action="" class="flex_col_center">--%>
    <div class="flex_col_center">
      <label>
        Article : ${article.getArticleName()}
      </label>
      <label>
        Description : ${article.getArticleDescription()}
      </label>
       <label>
        Catégorie : ${article.getArticleCategory().getCategoryName()}
      </label>
      <label>
        Meilleur offre : ${article.getArticleEndPrice()} points
      </label>
      <label>
        Mise à prix : ${article.getArticleStartPrice()} points
      </label>
      <label>
        Fin de l'enchère : ${article.getArticleBidEndDate()}
      </label>
      <label>
        Retrait : ${article.getArticleUser().getUserStreet()},
        		  ${article.getArticleUser().getUserPostalCode()}
        		  ${article.getArticleUser().getUserCity()}
      </label>
      <label>
        Vendeur : ${article.getArticleUser().getUserNickname()}
      </label>
      
      <c:choose>
      <c:when test="${article.getArticleUserId() != sessionScope.sessionUser.userId && article.getArticleBidEndDate() >= now}">
        <c:if test="${!empty sessionScope.sessionUser}">
	      <form method="post" action="MakeBid">
		      <label>
		        Ma proposition :
		        <input type="number" name="bidPrice" value="<c:out value="${article.articleEndPrice}"/>" >
		        <span class="error">${am.errors['bidPrice']}</span>
		      </label>
		  <!-- user ID stored in session -->
		  <input class="hide" type="text" name="userId" value="<c:out value="${sessionScope.sessionUser.userId}"/>" readonly="readonly">
		  
		  <!-- user credit stored in session -->
		  <input class="hide" type="number" name="userCredit" value="<c:out value="${sessionScope.sessionUser.userCredit}"/>" >
		  
		  <!-- article current price -->
		  <input class="hide" type="number" name="articleEndPrice" value="<c:out value="${article.articleEndPrice}"/>" >
		  
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
  </section>
</body>
</html>
