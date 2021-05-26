<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
    <a href="${pageContext.request.contextPath}/Accueil" class="title"><h1><%=request.getParameter("title")%></h1></a>
	
<c:choose>
    <c:when test="${empty sessionScope.sessionUser}">
        <nav class="nav-bar">
            <a href="
            <c:url value="Connexion">
            </c:url>"
            >S'inscrire - Se Connecter</a>
        </nav>
    </c:when>
    <c:otherwise>
        <nav class="nav-bar">
            <a href="
            <c:url value="Sell">
            </c:url>"
            >Vendre un article</a>
            <a> - </a>
            <a href="
			<c:url value="Profile">
			<c:param name="userId" value="${sessionScope.sessionUser.userId}"/>
			</c:url>"
			>Mon profil</a>
			<a> - </a>
			<a href="
			<c:url value="Deconnexion">
			</c:url>"
			>Deconnexion</a>
            
            <c:if test="${!empty sessionScope.sessionUser}">
                <p class="success">Login : ${sessionScope.sessionUser.userNickname}</p> 
            	<p class="info">Points : ${sessionScope.sessionUser.userCredit} pts</p> 
            </c:if>
            
            <c:if test="${!empty sessionScope.userMessage}">
            
            	<details>
            	<summary>Messages</summary>
            	<p class="info">Message : ${sessionScope.userMessage.result}</p>
            	<p class="info">Details : ${sessionScope.userMessage.getErrors()}</p>
            	<form method="get" action="CleanMessages">
            		<input class="hide" name="userId" value="${sessionScope.sessionUser.userId}" class="" />
            		<input type="submit" value="Clean messages and refresh points" class="" />
            	</form>
            	</details>
            </c:if>
        </nav>
    </c:otherwise>
</c:choose>
</header>


