<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="inc/form.css">
    <title>Accueil</title>
</head>
<body>

  <jsp:include page="header.jsp">
      <jsp:param name="title" value="ENI-Enchere"/>
  </jsp:include>

      <h2 class="flex_col_center">Liste des enchères</h2>
      <section class="search_area">
          <form method="post" class="flex_row_center">
              <div class="research_zone">
                  <div>
                      <label for="article">Filtres :</label>
                      <input type="text" id="article" name="article" value="" placeholder="Le nom de l'article contient"/>
                  </div>
                  <div>
                      <label for="categories">Catégorie :</label>
                      <select id="categories">
                          <option value="">Sélectionner une catégorie</option>
                      </select>
                  </div>
				<br>
                 <c:if test="${!empty(sessionScope.sessionUser)}">
                  <div class="flex_row_center">
                      <div>
                          <div>
                              <label>
                                  <input type="radio" id="achats_radio" name="bid_check" checked>
                                  Achats
                              </label>
                          </div>
                          <div id="bid" class="">
                              <div>
                                  <label>
                                      <input type="checkbox" id="bid_open">
                                      enchères ouvertes
                                  </label>
                              </div>
                              <div>
                                  <label>
                                      <input type="checkbox" id="bid_current">
                                      enchères en cours
                                  </label>
                              </div>
                              <div>
                                  <label>
                                      <input type="checkbox" id="bid_win">
                                      enchères remportées
                                  </label>
                              </div>
                          </div>
                      </div>

                      <div>
                          <div>
                              <label>
                                  <input type="radio" id="ventes_radio" name="bid_check">
                                  Mes ventes
                              </label>
                          </div>
                          <div id="sell" class="disabledButton">
                              <div>
                                  <label>
                                      <input type="checkbox" id="my_open">
                                      mes ventes en cours
                                  </label>
                              </div>
                              <div>
                                  <label>
                                      <input type="checkbox" id="my_not_start">
                                      ventes non débutées
                                  </label>
                              </div>
                              <div>
                                  <label>
                                      <input type="checkbox" id="my_end">
                                      ventes terminées
                                  </label>
                              </div>
                          </div>
                      </div>
                  </div>
                  </c:if>
                  
              </div>
<input type="submit" value="Rechercher"/>
              
          </form>
      </section>

    <jsp:include page="enchere.jsp">
        <jsp:param name="article" value="${list_articles}"/>
    </jsp:include>

  <script src="inc/script.js"></script>
</body>
</html>
