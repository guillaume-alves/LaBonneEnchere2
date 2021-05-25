<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
  <link type="text/css" rel="stylesheet" href="inc/form.css" />
</head>
<body>
  <jsp:include page="header.jsp">
    <jsp:param name="title" value="ENI-Enchere"/>
  </jsp:include>

  <h2 class="flex_col_center">Nouvelle Vente</h2>
  <section>
    <div>
      <img src="" alt="image_bid">
    </div>
    
    <form method="post" action="Sell" class="flex_col_center">
      <label>
        Article :
        <input type="text" name="articleName" value="<c:out value="${article.articleName}"/>" >
        <span class="error">${am.errors['articleName']}</span>
      </label>
      <label>
        Description :
        <textarea name="articleDescription" rows="4" cols="50"><c:out value="${article.articleDescription}"/></textarea>
        <span class="error">${am.errors['articleNDescription']}</span>
      </label>
	 	  
	  <label for="category">Catégorie :
      	<select name="articleCategoryId" tabindex="30">
        	<c:forEach var="category" items="${list_categories}">
	  			<option value="${category.categoryId}"><c:out value="${category.categoryName}"/></option>
	  		</c:forEach>
      	</select>
      </label>
	  
      <label>
        Photo de l'article :
        <button>Uploader</button>
      </label>
      
      <label>
        Mise à prix :
        <input type="number" name="articleStartPrice" value="<c:out value="${article.articleStartPrice}"/>" >
        <span class="error">${am.errors['articleStartPrice']}</span>
      </label>
      
      <label>
        Début des enchères
        <input type="date" name="articleBidStartDate" value="<c:out value="${article.articleBidStartDate}"/>" >
        <span class="error">${am.errors['articleBidStartDate']}</span>
      </label>
      
      <label>
        Fin des enchères
        <input type="date" name="articleBidEndDate" value="<c:out value="${article.articleBidEndDate}"/>" >
        <span class="error">${am.errors['articleBidEndDate']}</span>
      </label>
      
      <div class="flex_col_center">
        <p>Retrait</p>
      
	      <!-- Seller Id -->
	      <input class="hide" type="text" name="articleUserId" value="<c:out value="${sessionScope.sessionUser.userId}"/>" readonly="readonly">
	      
	      <label>
	      	Rue :
	        <input type="text" name="street" value="<c:out value="${sessionScope.sessionUser.userStreet}"/>" readonly="readonly">
	      </label>
	      
	      <label>
	        Code Postal :
	        <input type="text" name="postal_code" value="<c:out value="${sessionScope.sessionUser.userPostalCode}"/>" readonly="readonly">
	      </label>
	      
	      <label>
	        Ville :
	        <input type="text" name="city" value="<c:out value="${sessionScope.sessionUser.userCity}"/>" readonly="readonly">
	      </label>
      </div>
      
      <div>
        <input type="submit" value="Enregister" class="" />
        <input type="submit" value="Annuler" class="" />
      </div>
    </form>
  </section>
</body>
</html>
