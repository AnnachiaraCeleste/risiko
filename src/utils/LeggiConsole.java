/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import enums.TipoColore;
import enums.TipoPartita;
import exceptions.TerritorioNonRegistrato;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Territorio;
import logic.GestionePartita;
import checkfunctions.*;
import exceptions.GiocatoreNonRegistrato;
import java.util.ArrayList;

/**
 *
 * @author Annachiara
 */
public class LeggiConsole {

    protected GestionePartita gp;

    public LeggiConsole(GestionePartita gestp) {
        gp = gestp;
    }

    /**
     * inserimento di un boolean
     *
     * @param messaggio
     * @param messaggioErrore
     * @return
     */
    public static boolean getBoolean(String messaggio, String messaggioErrore) {
        Scanner scan = new Scanner(System.in);
        System.out.println(messaggio);
        String txt;
        do {
            txt = scan.nextLine().toLowerCase();
            if (!txt.equals("false") && !txt.equals("true")) {
                System.out.println(messaggioErrore);
            }
        } while (!txt.equals("false") && !txt.equals("true"));
        if (txt.equals("false")) {
            return false;
        }
        return true;
    }

    protected String getTerritorioRinforzo(String psw, String messaggio, String messaggioErrore) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println(messaggio);
        String txt;
        do {
            txt = scan.nextLine();
            if (!checkTerritorioEsistente(txt)) {
                System.out.println(messaggioErrore + " Il territorio non esiste.");
            } else if (!checkTerritorioPosseduto(psw, txt)) {
                System.out.println(messaggioErrore + " Il territorio non è di tua proprietà.");
            }
        } while (!checkTerritorioEsistente(txt) || !checkTerritorioPosseduto(psw, txt));

        return txt;
    }

    protected int getTruppeRinforzo(String psw, String messaggio, String messaggioErrore) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println(messaggio);
        String txt;
        do {
            txt = scan.nextLine();
            if (!checkfunctions.NumParser.checkIntPositivo(txt) || !checkTruppeRinforzo(psw, txt)) {
                System.out.println(messaggioErrore);
            }
        } while (!checkfunctions.NumParser.checkIntPositivo(txt) || !checkTruppeRinforzo(psw, txt));
        return Integer.parseInt(txt);
    }

    private boolean checkTruppeRinforzo(String psw, String txt) {
        try {
            return gp.getGiocatore(psw).getRinforzi() >= Integer.parseInt(txt);
        } catch (IOException | GiocatoreNonRegistrato ex) {
            return false;
        }
    }

    private boolean checkTerritorioEsistente(String txt) throws IOException {
        return gp.getLineTerritorioPartita(txt) != -1;
    }

    private boolean checkTerritorioPosseduto(String psw, String txt) throws IOException {
        try {
            for (int i = 0; i < gp.listaTerritoriGiocatorePartita(psw).size(); i++) {
                if (gp.listaTerritoriGiocatorePartita(psw).get(i).equals(gp.getTerritorioPartita(txt))) {
                    return true;
                }
            }
        } catch (TerritorioNonRegistrato ex) {
            Logger.getLogger(LeggiConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    protected String getTerritorioAttaccante(String psw, String messaggio, String messaggioErrore) throws IOException, TerritorioNonRegistrato {
        Scanner scan = new Scanner(System.in);
        System.out.println(messaggio);
        String txt;
        do {
            txt = scan.nextLine();
            if (!checkTerritorioEsistente(txt)) {
                System.out.println(messaggioErrore + " Il territorio non esiste.");
            } else if (!checkTerritorioPosseduto(psw, txt)) {
                System.out.println(messaggioErrore + " Il territorio non è posseduto");
            } else if (!checkArmateAttacco(txt)) {
                System.out.println(messaggioErrore + " Il territorio non ha abbastanza armate per eseguire l'attacco");
            } else if (checkTerritoriCircostantiNonPosseduti(psw, txt)) {
                System.out.println(messaggioErrore + " I confini di questo territorio sono già tuoi possedimenti");
            }
        } while (!checkTerritorioEsistente(txt) || !checkTerritorioPosseduto(psw, txt) || !checkArmateAttacco(txt) || checkTerritoriCircostantiNonPosseduti(psw, txt));
        return txt;
    }

    private boolean checkArmateAttacco(String txt) throws IOException {
        try {
            return gp.getTerritorioPartita(txt).getNumeroArmate() > 1;
        } catch (TerritorioNonRegistrato ex) {
            return false;
        }
    }

    protected String getTerritorioDifensore(String psw, String tAttacc, String messaggio, String messaggioErrore) throws IOException, TerritorioNonRegistrato {
        Scanner scan = new Scanner(System.in);
        String txt;
        do {
            System.out.println(messaggio);
            txt = scan.nextLine();
            if (!checkTerritorioEsistente(txt)) {
                System.out.println(messaggioErrore + " Il territorio non esiste.");
            } else if (checkTerritorioPosseduto(psw, txt) || !checkTerritorioConfinante(tAttacc, txt)) {
                System.out.println(messaggioErrore + " " + gp.getTerritorioDettagliato(tAttacc).getSequenzaConfini());
            }
        } while (!checkTerritorioEsistente(txt) || checkTerritorioPosseduto(psw, txt) || !checkTerritorioConfinante(tAttacc, txt));

        return txt;
    }

    private boolean checkTerritorioConfinante(String tAttacc, String txt) throws IOException {
        try {
            return Territorio.splitTerritori(gp.getTerritorioDettagliato(tAttacc).getSequenzaConfini()).contains(txt);
        } catch (TerritorioNonRegistrato ex) {
            return false;
        }
    }

    private boolean checkTerritoriCircostantiNonPosseduti(String psw, String txt) throws IOException {
        try {

            ArrayList<String> tConfinanti = Territorio.splitTerritori(gp.getTerritorioDettagliato(txt).getSequenzaConfini());
            // Verifica se tutti i territori circostanti sono posseduti dal giocatore
            for (int i = 0; i < tConfinanti.size(); i++) {
                if (!checkTerritorioPosseduto(psw, tConfinanti.get(i))) {
                    return false; // Almeno un territorio circostante non è posseduto
                }
            }
            return true; // Tutti i territori circostanti sono posseduti
        } catch (TerritorioNonRegistrato ex) {
            return false;
        }
    }

    protected TipoColore getTipoColore(String messaggio, String messaggioErrore) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println(messaggio);
        String txt;
        do {
            txt = scan.nextLine();
            if (!EnumParser.checkTipoColore(txt) || gp.getLineGiocatore(TipoColore.valueOf(txt)) != -1) {
                System.out.println(messaggioErrore + " (" + TipoColore.listOfCostantsColore() + ")");
            }
        } while (!EnumParser.checkTipoColore(txt) || gp.getLineGiocatore(TipoColore.valueOf(txt)) != -1);
        return TipoColore.valueOf(txt);
    }

    protected String getPassword(String messaggio, String messaggioErrore) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println(messaggio);
        String txt;
        do {
            txt = scan.nextLine();
            if (!StringParser.checkPassword(txt) || gp.getLineGiocatore(txt) != -1) {
                System.out.println(messaggioErrore);
            }
        } while (!StringParser.checkPassword(txt) || gp.getLineGiocatore(txt) != -1);

        return txt;
    }

    protected TipoPartita getTipoPartita(String messaggio, String messaggioErrore) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println(messaggio);
        String txt;
        do {
            txt = scan.nextLine();
            if (!EnumParser.checkTipoPartita(txt)) {
                System.out.println(messaggioErrore + " (" + TipoPartita.listOfCostants() + ")");
            }
        } while (!EnumParser.checkTipoPartita(txt));
        return TipoPartita.valueOf(txt);
    }

    protected String getTerritorioPartenza(String psw, String messaggio, String messaggioErrore) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println(messaggio);
        String txt;
        do {
            txt = scan.nextLine();
            if (!checkTerritorioEsistente(txt)) {
                System.out.println(messaggioErrore + " Il territorio non esiste.");
            } else if (!checkTerritorioPosseduto(psw, txt)) {
                System.out.println(messaggioErrore + " Il territorio non è posseduto");
            } else if (!checkArmateAttacco(txt)) {
                System.out.println(messaggioErrore + " Il territorio non ha abbastanza armate per eseguire lo spostamento");
            }
        } while (!checkTerritorioEsistente(txt) || !checkTerritorioPosseduto(psw, txt) || !checkArmateAttacco(txt));
        return txt;
    }

    protected String getTerritorioDestinazione(String psw, String tPartenza, String messaggio, String messaggioErrore) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println(messaggio);
        String txt;
        do {
            txt = scan.nextLine();
            if (!checkTerritorioEsistente(txt)) {
                System.out.println(messaggioErrore + " Il territorio non esiste.");
            } else if (!checkTerritorioPosseduto(psw, txt)) {
                System.out.println(messaggioErrore + " Il territorio non è posseduto");
            } else if (txt.equals(tPartenza)) {
                System.out.println(messaggioErrore + " Il territorio inserito è uguale a quello dal quale vuoi spostare le armate");
            }
        } while (!checkTerritorioEsistente(txt) || !checkTerritorioPosseduto(psw, txt) || txt.equals(tPartenza));
        return txt;
    }

    protected int getTruppeSpostamento(String psw, String tPartenza, String messaggio, String messaggioErrore) throws IOException, TerritorioNonRegistrato {
        Scanner scan = new Scanner(System.in);
        System.out.println(messaggio);
        String txt;
        do {
            txt = scan.nextLine();
            if (!checkfunctions.NumParser.checkIntPositivo(txt)) {
                System.out.println(messaggioErrore + " Inserisci un intero positivo.");
            } else if (Integer.parseInt(txt) >= gp.getTerritorioPartita(tPartenza).getNumeroArmate()) {
                System.out.println(messaggioErrore + " Stai cercando di spostare più truppe di quante ce ne sono sul territorio.");
            }
        } while (!checkfunctions.NumParser.checkIntPositivo(txt) || Integer.parseInt(txt) >= gp.getTerritorioPartita(tPartenza).getNumeroArmate());
        return Integer.parseInt(txt);
    }

}
