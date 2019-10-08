package com.tp1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tp1.beans.Beansclient;
import com.tp1.forms.*;

public class Client extends HttpServlet {
	

    public static final String ATT_CLIENT = "client";
    public static final String ATT_FORM   = "form";

    public static final String VUE_SUCCES = "/WEB-INF/afficherClient.jsp";
    public static final String VUE_FORM   = "/creerClient.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        CreationClientForm form = new CreationClientForm();


        Beansclient client = form.creerClient( request );


        request.setAttribute( ATT_CLIENT, client );
        request.setAttribute( ATT_FORM, form );

        if ( form.getErrors().isEmpty() ) {

            this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
        } else {

            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }
    }
}
