<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="flex_row_center">
	<c:forEach var="article" items="${list_articles}">
		<article class="article_area">
			<div class="img-ench">
				<img class="image" src="inc/item.png" alt="pc" width="200" />
			</div>

			<div class="ench">
				<a class="article_title"
					href="
						<c:url value="ArticleProfile">
						<c:param name="articleId" value="${article.getArticleId()}"/>
						</c:url>">${article.getArticleName()}</a>
				<p>Prix : ${article.getArticleEndPrice()}</p>
				<p>Fin de l'ench&egrave;re : ${article.getArticleBidEndDate()}</p>
				<p>
					Vendeur : <a
						href="
						<c:url value="Profile">
						<c:param name="userId" value="${article.getArticleUserId()}"/>
						</c:url>"><c:out
							value="${article.getArticleUser().getUserNickname()}" /></a>
				</p>
			</div>
		</article>
	</c:forEach>
</section>