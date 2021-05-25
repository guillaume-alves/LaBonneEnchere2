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
        Meilleur offre : ${article.getArticleBid().getBidPrice()} points
        				par ${article.getArticleBid().getBidUserId()}
      </label>
      <label>
        Mise à prix : ${article.getArticleStartPrice()} points
      </label>
      <label>
        Fin des enchères : ${article.getArticleBidEndDate()}
      </label>
      <label>
        Retrait : ${article.getArticleUser().getUserStreet()},
        		  ${article.getArticleUser().getUserPostalCode()}
        		  ${article.getArticleUser().getUserCity()}
      </label>
      <label>
        Vendeur : ${article.getArticleUser().getUserNickname()}
      </label>
    </div>
<%--        <input type="submit" value="Enregister" class="" />--%>
<%--        <input type="submit" value="Annuler" class="" />--%>
<%--      </div>--%>
<%--    </form>--%>
  </section>
</body>
</html>
