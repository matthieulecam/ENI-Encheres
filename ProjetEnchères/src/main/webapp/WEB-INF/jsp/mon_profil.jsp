<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mon Profil</title>
    <link rel="stylesheet" href="styles/styleprofile.css">
    
</head>

<body>
     <%@ include file = "navbar.jsp" %>
     <section class="main">
    <div class="informations"><h2>Mon profil</h2>
    <p>Pseudo : ${user.pseudo}</p>
    <p>Nom : ${user.nom}</p>
    <p>Prénom : ${user.prenom}</p>
    <p>Email : ${user.email}</p>
    <p>Téléphone : ${user.telephone}</p>
    <p>Rue : ${user.rue}</p>
    <p>Ville : ${user.ville}</p>
    <p> Code postal : ${user.code_postal}</p>
    <h3>Crédit : ${user.credit}</h3> 
    <a class ="supprimer-compte" href="${pageContext.request.contextPath}/ServletSuppressionCompte" onclick="return confirm('Êtes-vous sûr de vouloir supprimer votre compte ?')">Supprimer mon compte</a>
    <a class="modifier-profil" href="${pageContext.request.contextPath}/ServletModificationProfil">Modifier mon profil </a>
</div>
    </section>
    
    	<c:if test="${not empty requestScope.succesModif}">
    	<p style="color : green">${requestScope.succesModif}</p>
    </c:if>
    <c:if test="${not empty requestScope.erreurModif}">
    	<p style="color : red">${requestScope.erreurModif}</p>
    </c:if>
    
</body>
</html>
