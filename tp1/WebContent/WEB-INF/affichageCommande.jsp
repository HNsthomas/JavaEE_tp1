<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage de la commande</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    <body>
        <div>
        
        <p>Création nouvelle Commande : ${ infoCreation }</p></br>
        
        <p>Nom : ${ commande.client.nom } </p>
        <p>Prénom : ${ commande.client.prenom } </p>
        <p>Adresse : ${ commande.client.adresse } </p>
        <p>Mail : ${ commande.client.email } </p>
        <p>Téléphone : ${ commande.client.telephone } </p>
        
        <p>Date : ${ commande.date } </p>
        <p>Mode de Paiement : ${ commande.modePaiement } </p>
        <p>Montant : ${ commande.montant } </p>
        <p>Statut du Paiement : ${ commande.statutPaiement } </p>
        <p>Mode de Livraison: ${ commmande.modeLivraison } </p>
        <p>Statut de la Livraison: ${ commande.statutLivraison }</p>
        

        </div>
    </body>
</html>