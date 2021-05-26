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
  <section class="flex_col_center">
    <div>
      <img src="" alt="image_bid">
    </div>
    
    <div class="">
    <form method="post" action="Sell">
      <label> Article : </label>
      <input type="text" name="articleName" value="<c:out value="${article.articleName}"/>" size="20" maxlength="60">
      <span class="error">${am.errors['articleName']}</span><br>
     
      <label>Description :</label>
      <textarea name="articleDescription" rows="4" cols="30"><c:out value="${article.articleDescription}"/></textarea>
      <span class="error">${am.errors['articleDescription']}</span><br>
      
	  <label for="category">Catégorie :</label>
      	<select name="articleCategoryId" tabindex="30">
        	<c:forEach var="category" items="${list_categories}">
	  			<option value="${category.categoryId}"><c:out value="${category.categoryName}"/></option>
	  		</c:forEach>
      	</select><br>
 
      <label>Photo de l'article :</label>
      <button>Uploader</button><br>

      <label>Mise à prix :</label>
      <input type="number" name="articleStartPrice" value="<c:out value="${article.articleStartPrice}"/>" size="20" maxlength="60">
      <span class="error">${am.errors['articleStartPrice']}</span><br>
          
      <label>Début des enchères :</label>
      <input type="date" name="articleBidStartDate" value="<c:out value="${article.articleBidStartDate}"/>" >
      <span class="error">${am.errors['articleBidStartDate']}</span><br>
      
      <label>Fin des enchères :</label>
	  <input type="date" name="articleBidEndDate" value="<c:out value="${article.articleBidEndDate}"/>" >
      <span class="error">${am.errors['articleBidEndDate']}</span><br>

      <fieldset>
      <legend>Retrait</legend>
	     
	    <label>Rue :</label>
	    <input type="text" name="street" value="<c:out value="${sessionScope.sessionUser.userStreet}"/>" readonly="readonly"><br>

		<label>Code Postal : </label>
	    <input type="text" name="postal_code" value="<c:out value="${sessionScope.sessionUser.userPostalCode}"/>" readonly="readonly"><br>
	 
	    <label>Ville :</label>
	    <input type="text" name="city" value="<c:out value="${sessionScope.sessionUser.userCity}"/>" readonly="readonly"><br> 
	  </fieldset>
	 
      <div>
      	<!-- Seller Id -->
	    <input class="hide" type="text" name="articleUserId" value="<c:out value="${sessionScope.sessionUser.userId}"/>" readonly="readonly"><br>
        <input type="submit" value="Enregister" class="" />
        <input type="submit" value="Annuler" class="" />
      </div>
      
   </form>
   </div>
  </section>
</body>
</html>
