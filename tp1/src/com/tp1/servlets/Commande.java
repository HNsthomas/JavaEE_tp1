package com.tp1.servlets;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.tp1.beans.Beansclient;
import com.tp1.beans.Beanscommande;

public class Commande extends HttpServlet {
	
	/* Déclaration des Constantes */
	public static final String INF_NOM       = "nomClient";
	public static final String INF_PRENOM    = "prenomClient";
	public static final String INF_ADRESSE   = "adresseClient";
	public static final String INF_TELEPHONE = "telephoneClient";
	public static final String INF_EMAIL     = "emailClient";
	
    public static final String INF_DATE             = "strDate";
    public static final String INF_MONTANT          = "montantCommande";
    public static final String INF_MODE_PAIEMENT    = "modePaiementCommande";
    public static final String INF_STATUT_PAIEMENT  = "statutPaiementCommande";
    public static final String INF_MODE_LIVRAISON   = "modeLivraisonCommande";
    public static final String INF_STATUT_LIVRAISON = "statutLivraisonCommande";
 
    public static final String ATT_COMMANDE           = "commande";
    public static final String ATT_CREATION            = "infoCreation";
    public static final String ATT_ERREUR             = "erreur";
 
    public static final String VUE                    = "/WEB-INF/affichageCommande.jsp";


    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        String nom = request.getParameter( INF_NOM );
        String prenom = request.getParameter( INF_PRENOM );
        String adresse = request.getParameter( INF_ADRESSE );
        String telephone = request.getParameter( INF_TELEPHONE );
        String email = request.getParameter( INF_EMAIL );


        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        
        double montant;
        try {
            montant = Double.parseDouble( request.getParameter(INF_MONTANT) );
        } catch ( NumberFormatException e ) {

            montant = -1;
        }
        String modePaiement = request.getParameter( INF_MODE_PAIEMENT );
        String statutPaiement = request.getParameter( INF_STATUT_PAIEMENT );
        String modeLivraison = request.getParameter( INF_MODE_LIVRAISON );
        String statutLivraison = request.getParameter( INF_STATUT_LIVRAISON );

        String infoCreation;
        Boolean erreur;

        if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() || montant == -1
                || modePaiement.isEmpty() || modeLivraison.isEmpty() ) {
            infoCreation = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerCommande.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'une commande.";
            erreur = true;
        } else {
            infoCreation = "Commande créée avec succès !";
            erreur = false;
        }


        Beansclient client = new Beansclient();
        client.setNom( nom );
        client.setPrenom( prenom );
        client.setAdresse( adresse );
        client.setTelephone( telephone );
        client.setEmail( email );

        Beanscommande commande = new Beanscommande();
        commande.setClient( client );
        commande.setDate( strDate );
        commande.setMontant( montant );
        commande.setModePaiement( modePaiement );
        commande.setStatutPaiement( statutPaiement );
        commande.setModeLivraison( modeLivraison );
        commande.setStatutLivraison( statutLivraison );

        request.setAttribute( ATT_COMMANDE, commande );
        request.setAttribute( ATT_CREATION, infoCreation );
        request.setAttribute( ATT_ERREUR, erreur);

 
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
