<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<a href="${pageContext.request.contextPath}/Accueil" class="title"><h1><%=request.getParameter("title")%></h1></a>

	<c:choose>
		<c:when test="${empty sessionScope.sessionUser}">
			<nav class="nav-bar">
				<a
					href="
            <c:url value="Connexion">
            </c:url>">S'inscrire
					- Se Connecter</a>
			</nav>
		</c:when>
		<c:otherwise>
			<nav class="nav-bar">
				<a href="
            <c:url value="Sell">
            </c:url>">Vendre
					un article</a> <a> - </a> <a
					href="
			<c:url value="Profile">
			<c:param name="userId" value="${sessionScope.sessionUser.userId}"/>
			</c:url>">Mon
					profil</a> <a> - </a> <a
					href="
			<c:url value="Deconnexion">
			</c:url>">Deconnexion</a>

				<c:if test="${!empty sessionScope.sessionUser}">
					<p class="success">
						Login :
						<c:out value="${sessionScope.sessionUser.userNickname}" />
					</p>
					<p class="info">
						Points :
						<c:out value="${sessionScope.sessionUser.userCredit}" />
						pts
					</p>
				</c:if>

				<c:if test="${!empty sessionScope.userMessage}">

					<details>
						<summary>Messages</summary>
						<p class="info">
							<c:out value="${sessionScope.userMessage.result}" />
						</p>
						<p class="info">
							<c:out value="${sessionScope.userMessage.getErrors()}" />
						</p>
						<c:forEach items="${sessionScope.userMessage.errors}"
							var="message">
							<c:out value="${message.value}" />
						</c:forEach>
						<form method="get" action="CleanMessages">
							<input class="hide" name="userId"
								value="<c:out value="${sessionScope.sessionUser.userId}"/>"
								class="" /> <input type="submit" value="Clean messages"
								class="" />
						</form>
					</details>
				</c:if>
			</nav>
		</c:otherwise>
	</c:choose>
</header>


