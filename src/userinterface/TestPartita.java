/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import enums.TipoPartita;
import enums.TipoColore;
import utils.LeggiConsole;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.GestionePartita;
import exceptions.*;
import model.*;
import ioconsole.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Annachiara
 */
public class TestPartita extends LeggiConsole {

    private Timer timer;
    private boolean timerScaduto;

    public TestPartita(GestionePartita gestp) {
        super(gestp);
    }

    //TIMER-------------------------------------------------------------------//
    private void avviaTimer() {
        timer = new Timer();
        timerScaduto = false;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Azioni da eseguire quando scade il tempo dell'attacco
                System.out.println("Tempo scaduto per l'attacco!");
                timerScaduto = true;
                try {
                    System.out.println("TERRITORI AGGIORNATI DOPO LA FASE DI ATTACCO:");
                    stampaListaTerritori(gp.listaTerritoriPartita());
                    System.out.println("\n");
                } catch (IOException ex) {
                    Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 180000);
    }

    private void interrompiTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    public void inizioPartita() {
        inserisciTipoPartita();
        inserisciNumeroGiocatori();
        for (int i = 0; i < gp.getN_giocatori(); i++) {
            inserisciGiocatore();
        }
        try {
            gp.addObiettivi();
            gp.addTerritoriPartita();
        } catch (IOException ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);

        } catch (RisikoExceptions ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void turnoDiGioco(String psw) {
        faseRinforzo(psw);
        System.out.println("VUOI CONTINUARE CON LA FASE DI ATTACCO (true) OPPURE PASSARE ALLO SPOSTAMENTO TRUPPE(false)?");
        if (scelta()) {
            avviaTimer();
            faseAttacco(true, psw);
            interrompiTimer();
        }

    }

    public String ricercaGiocatore(String psw) {
        try {
            return gp.getGiocatore(psw).toString();
        } catch (IOException ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GiocatoreNonRegistrato ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getPassword(int i) {
        try {
            return gp.getIofGiocatorePartita().get(i).getPassword();
        } catch (IOException ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean scelta() {
        boolean scelta = utils.LeggiConsole.getBoolean("(effettua la tua scelta)", "IL VALORE INSERITO NON E' ACCETTABILE: inserisci \"true\" o \"false\"");
        return scelta;
    }

    public void faseRinforzo(String psw) {
        try {
            while (gp.getGiocatore(psw).getRinforzi() > 0) {
                stampaListaTerritori(gp.listaTerritoriGiocatorePartita(psw));
                System.out.println("Numero Rinforzi: " + gp.getGiocatore(psw).getRinforzi());
                String territorio = getTerritorioRinforzo(psw, "INSERISCI IL NOME DEL TERRITORIO AL QUALE VUOI AGGIUNGERE LE TRUPPE", "IL VALORE INSERITO NON E' ACCETTABILE");
                int truppeRinforzo = getTruppeRinforzo(psw, "INSERISCI IL NUMERO DI TRUPPE DA AGGIUNGERE AL TERRITORIO", "IL VALORE INSERITO NON E' ACCETTABILE. INSERISCI UN INTERO");
                gp.faseRinforzo(psw, territorio, truppeRinforzo);
            }
        } catch (IOException ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RisikoExceptions ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void faseAttacco(boolean scelta, String psw) {
        if (scelta) {
            try {
                stampaListaTerritori(gp.listaTerritoriGiocatorePartita(psw));
                do {
                    String territorioAttaccante = getTerritorioAttaccante(psw, "INSERISCI IL TERRITORIO DAL QUALE VUOI ATTACCARE", "IL VALORE INSERITO NON E' ACCETTABILE");
                    String territorioDifensore = getTerritorioDifensore(psw, territorioAttaccante, "INSERISCI IL TERRITORIO CHE VUOI ATTACCARE", "IL VALORE INSERITO NON E' ACCETTABILE");
                    if(!timerScaduto){
                    gp.faseAttacco(psw, territorioAttaccante, territorioDifensore);
                    }
                } while (!timerScaduto);
            } catch (IOException ex) {
                Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RisikoExceptions ex) {
                System.out.println(ex);
            }

        }
    }

    public void inserisciNumeroGiocatori() {
        int num = ioconsole.LeggiConsole.getIntInRange("INSERISCI IL NUMERO DI GIOCATORI CHE PARTECIPANO ALLA PARTITA", "IL VALORE INSERITO NON E' ACCETTABILE", gp.getNUMERO_MIN_GIOCATORI() - 1, gp.getNUMERO_MAX_GIOCATORI() + 1);
        gp.setN_giocatori(num);
    }

    public void inserisciTipoPartita() {
        try {
            TipoPartita t = getTipoPartita("INSERISCI IL TIPO DI PARTITA CHE VUOI EFFETTUARE", "IL VALORE INSERITO NON E' ACCETTABILE");
            gp.setTipoPartita(t);
        } catch (IOException ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inserisciGiocatore() {
        String nome = ioconsole.LeggiConsole.getStringNonVuota("INSERISCI IL TUO NOME");
        try {
            TipoColore colore = getTipoColore("INSERISCI IL COLORE DELLA TUA ARMATA", "IL VALORE INSERITO NON E' ACCETTABILE");
            String psw = getPassword("INSERISCI LA TUA PASSWORD IDENTIFICATIVA", "IL VALORE INSERITO NON E' ACCETTABILE \n(più di 8 caratteri, almeno una maiuscola, una minuscola, un numero e un carattere speciale)");
            gp.addGiocatore(new Giocatore(nome, colore, psw, 0, 0));
            System.out.println(new Giocatore(nome, colore, psw, 0, 0));

        } catch (IOException ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GiocatoreGiaRegistrato ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void stampaListaTerritori(ArrayList<TerritorioPartita> lista) {
        gp.listaTerritori(lista);
    }
}
