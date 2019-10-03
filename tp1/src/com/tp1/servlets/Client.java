package com.tp1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp1.beans.Beansclient;

public class Client extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		String nom = request.getParameter( "nomClient" );
		String prenom = request.getParameter( "prenomClient" );
		String adresse = request.getParameter( "adresseClient" );
		String telephone = request.getParameter( "telephoneClient" );
		String email = request.getParameter( "emailClient" ); 
		
        String infoCreation;

        if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() ) {
            infoCreation = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerClient.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'un client.";
        } else {
            infoCreation = "Client créé avec succès !";
        }
        
        Beansclient client = new Beansclient();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setAdresse(adresse);
        client.setTelephone(telephone);
        client.setEmail(email);
        
        request.setAttribute("client", client);
        request.setAttribute("infoCreation", infoCreation);
        
        this.getServletContext().getRequestDispatcher( "/WEB-INF/affichageClient.jsp" ).forward( request, response );
	}
}
