<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage du Client</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    <body>
        <div>
        
        <p>Création nouveau Client : ${ infoCreation }</p></br>
        
        <p>Nom : ${ client.nom } </p>
        <p>Prénom : ${ client.prenom } </p>
        <p>Adresse : ${ client.adresse } </p>
        <p>Mail : ${ client.email } </p>
        <p>Téléphone : ${ client.telephone } </p>
        

        </div>
    </body>
</html>