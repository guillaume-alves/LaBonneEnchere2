<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="flex_row_center">
  <c:forEach var="article" items="${list_articles}">
    <form method="POST" action="article?articleId=${article.getArticleId()}" class="article_col">
      <article class="article_area">
        <div>
          <img src="../image/pc.jpg" alt="pc" width="200"/>
        </div>

        <div>
          <h2>${article.getArticleName()}</h2>
          <p>Prix : ${article.getArticleStartPrice()}</p>
          <p>Fin de l'ench&egrave;re : ${article.getArticleBidEndDate()}</p>
          <p>Vendeur : ${article.getArticleUserId()}</p>
        </div>
      </article>
      <input type="submit" value="Détail">
    </form>

  </c:forEach>
</section>