<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
    <a href="${pageContext.request.contextPath}/Accueil" class="title"><h1><%=request.getParameter("title")%></h1></a>

<c:choose>
    <c:when test="${empty sessionScope.sessionUser}">
        <nav class="nav-bar">
            <a href="${pageContext.request.contextPath}/Connexion">S'inscrire - Se Connecter</a>
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
                <p class="success">You are connected with : ${sessionScope.sessionUser.userNickname}</p>
            </c:if>
        </nav>
    </c:otherwise>
</c:choose>
</header>

