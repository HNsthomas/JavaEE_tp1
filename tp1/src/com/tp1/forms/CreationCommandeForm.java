package com.tp1.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.tp1.beans.Beansclient;
import com.tp1.beans.Beanscommande;

public final class CreationCommandeForm {
    private static final String CHAMP_DATE             = "dateCommande";
    private static final String CHAMP_MONTANT          = "montantCommande";
    private static final String CHAMP_MODE_PAIEMENT    = "modePaiementCommande";
    private static final String CHAMP_STATUT_PAIEMENT  = "statutPaiementCommande";
    private static final String CHAMP_MODE_LIVRAISON   = "modeLivraisonCommande";
    private static final String CHAMP_STATUT_LIVRAISON = "statutLivraisonCommande";



    private String              result;
    private Map<String, String> errors                = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getResultat() {
        return result;
    }

    public Beanscommande creerCommande( HttpServletRequest request ) {
        /*
         utilisation du client
         */
        CreationClientForm clientForm = new CreationClientForm();
        Beansclient client = clientForm.creerClient( request );

        /*
         * Et uitlisation des erreurs
         */
        errors = clientForm.getErrors();


        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        String montant = getValeurChamp( request, CHAMP_MONTANT );
        String modePaiement = getValeurChamp( request, CHAMP_MODE_PAIEMENT );
        String statutPaiement = getValeurChamp( request, CHAMP_STATUT_PAIEMENT );
        String modeLivraison = getValeurChamp( request, CHAMP_MODE_LIVRAISON );
        String statutLivraison = getValeurChamp( request, CHAMP_STATUT_LIVRAISON );

        Beanscommande commande = new Beanscommande();

        commande.setClient( client );

        commande.setDate( strDate );

        double valeurMontant = -1;
        try {
            valeurMontant = validationMontant( montant );
        } catch ( Exception e ) {
            setError( CHAMP_MONTANT, e.getMessage() );
        }
        commande.setMontant( valeurMontant );

        try {
            validationModePaiement( modePaiement );
        } catch ( Exception e ) {
            setError( CHAMP_MODE_PAIEMENT, e.getMessage() );
        }
        commande.setModePaiement( modePaiement );

        try {
            validationStatutPaiement( statutPaiement );
        } catch ( Exception e ) {
            setError( CHAMP_STATUT_PAIEMENT, e.getMessage() );
        }
        commande.setStatutPaiement( statutPaiement );

        try {
            validationModeLivraison( modeLivraison );
        } catch ( Exception e ) {
            setError( CHAMP_MODE_LIVRAISON, e.getMessage() );
        }
        commande.setModeLivraison( modeLivraison );

        try {
            validationStatutLivraison( statutLivraison );
        } catch ( Exception e ) {
            setError( CHAMP_STATUT_LIVRAISON, e.getMessage() );
        }
        commande.setStatutLivraison( statutLivraison );

        if ( errors.isEmpty() ) {
            result = "Succès de la création de la commande.";
        } else {
            result = "Échec de la création de la commande.";
        }
        return commande;
    }

    private double validationMontant( String montant ) throws Exception {
        double temp;
        if ( montant != null ) {
            try {
                temp = Double.parseDouble( montant );
                if ( temp < 0 ) {
                    throw new Exception( "Le montant doit être un nombre positif." );
                }
            } catch ( NumberFormatException e ) {
                temp = -1;
                throw new Exception( "Le montant doit être un nombre." );
            }
        } else {
            temp = -1;
            throw new Exception( "Merci d'entrer un montant." );
        }
        return temp;
    }

    private void validationModePaiement( String modePaiement ) throws Exception {
        if ( modePaiement != null ) {
            if ( modePaiement.length() < 2 ) {
                throw new Exception( "Le mode de paiement doit contenir au moins 2 caractères." );
            }
        } else {
            throw new Exception( "Merci d'entrer un mode de paiement." );
        }
    }

    private void validationStatutPaiement( String statutPaiement ) throws Exception {
        if ( statutPaiement != null && statutPaiement.length() < 2 ) {
            throw new Exception( "Le statut de paiement doit contenir au moins 2 caractères." );
        }
    }

    private void validationModeLivraison( String modeLivraison ) throws Exception {
        if ( modeLivraison != null ) {
            if ( modeLivraison.length() < 2 ) {
                throw new Exception( "Le mode de livraison doit contenir au moins 2 caractères." );
            }
        } else {
            throw new Exception( "Merci d'entrer un mode de livraison." );
        }
    }

    private void validationStatutLivraison( String statutLivraison ) throws Exception {
        if ( statutLivraison != null && statutLivraison.length() < 2 ) {
            throw new Exception( "Le statut de livraison doit contenir au moins 2 caractères." );
        }
    }

    /*
     * message de l'erreur dans la map
     */
    private void setError( String champ, String message ) {
        errors.put( champ, message );
    }

    /*
     rempli le champ sinon null
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}