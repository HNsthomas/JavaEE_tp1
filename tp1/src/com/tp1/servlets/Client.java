package com.tp1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp1.beans.Beansclient;

public class Client extends HttpServlet {
	
    	/* Déclaration des Constantes */
    	public static final String INF_NOM       = "nomClient";
    	public static final String INF_PRENOM    = "prenomClient";
    	public static final String INF_ADRESSE   = "adresseClient";
    	public static final String INF_TELEPHONE = "telephoneClient";
    	public static final String INF_EMAIL     = "emailClient";
 
    	public static final String ATT_CLIENT      = "client";
    	public static final String ATT_CREATION     = "infoCreation";
    	public static final String ATT_ERREUR      = "erreur";
 
    	public static final String VUE             = "/WEB-INF/affichageClient.jsp";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		String nom = request.getParameter( INF_NOM );
		String prenom = request.getParameter( INF_PRENOM );
		String adresse = request.getParameter( INF_ADRESSE );
		String telephone = request.getParameter( INF_TELEPHONE );
		String email = request.getParameter( INF_EMAIL ); 
		
        String infoCreation;
        Boolean erreur;

        if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() ) {
            infoCreation = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerClient.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'un client.";
            erreur = true;
        } else {
            infoCreation = "Client créé avec succès !";
            erreur = false;
        }
        
        Beansclient client = new Beansclient();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setAdresse(adresse);
        client.setTelephone(telephone);
        client.setEmail(email);
        
        request.setAttribute(ATT_CLIENT, client);
        request.setAttribute(ATT_CREATION, infoCreation);
        request.setAttribute(ATT_ERREUR, erreur);
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
}
