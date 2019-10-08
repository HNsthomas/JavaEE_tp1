package com.tp1.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp1.beans.Beanscommande;
import com.tp1.forms.*;

public class Commande extends HttpServlet {
	
    public static final String ATT_COMMANDE = "commande";
    public static final String ATT_FORM     = "form";

    public static final String VUE_SUCCES   = "/WEB-INF/afficherCommande.jsp";
    public static final String VUE_FORM     = "/creerCommande.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        CreationCommandeForm form = new CreationCommandeForm();


        Beanscommande commande = form.creerCommande( request );


        request.setAttribute( ATT_COMMANDE, commande );
        request.setAttribute( ATT_FORM, form );

        if ( form.getErrors().isEmpty() ) {

            this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
        } else {

            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }
    }
}
