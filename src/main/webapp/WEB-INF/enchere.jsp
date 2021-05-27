<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="flex_row_center">
	<c:forEach var="article" items="${list_articles}">
		<article class="article_area">
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
				</c:when>
				<c:when test="${article.articleCategoryId == 5}">
					<div class="img-ench">
						<img class="image" src="inc/toolbox.png" alt="img" width="200" />
					</div>
				</c:when>
				<c:when test="${article.articleCategoryId == 6}">
					<div class="img-ench">
						<img class="image" src="inc/multimedia.png" alt="img" width="200" />
					</div>
				</c:when>
				<c:when test="${article.articleCategoryId == 7}">
					<div class="img-ench">
						<img class="image" src="inc/book.png" alt="img" width="200" />
					</div>
				</c:when>
				<c:when test="${article.articleCategoryId == 8}">
					<div class="img-ench">
						<img class="image" src="inc/dress.png" alt="img" width="200" />
					</div>
				</c:when>
				<c:when test="${article.articleCategoryId == 9}">
					<div class="img-ench">
						<img class="image" src="inc/gardening.png" alt="img" width="200" />
					</div>
				</c:when>
				<c:when test="${article.articleCategoryId == 10}">
					<div class="img-ench">
						<img class="image" src="inc/car.png" alt="img" width="200" />
					</div>
				</c:when>
				<c:when test="${article.articleCategoryId == 11}">
					<div class="img-ench">
						<img class="image" src="inc/toys.png" alt="img" width="200" />
					</div>
				</c:when>
				<c:when test="${article.articleCategoryId == 12}">
					<div class="img-ench">
						<img class="image" src="inc/amphora.png" alt="img" width="200" />
					</div>
				</c:when>
			</c:choose>

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