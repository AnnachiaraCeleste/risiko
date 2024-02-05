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
import logic.GestionePartita;
import exceptions.*;
import model.*;
import ioconsole.*;
import java.awt.event.ActionListener;
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
                System.out.println("<<<<IL TIMER E' SCADUTO>>>>>");
                timerScaduto = true;
                timer.cancel();  // Ferma il timer dopo l'esecuzione
            }
        }, 300000);
    }

    private void interrompiTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    /**
    *INSERISCI NUMERO GIOCATORI: metodo che chiede all'utente di inserire un intero ed esegue gli opportuni controlli
    */
    public void inserisciNumeroGiocatori() {
        int num = ioconsole.LeggiConsole.getIntInRange("INSERISCI IL NUMERO DI GIOCATORI CHE PARTECIPANO ALLA PARTITA", "IL VALORE INSERITO NON E' ACCETTABILE", gp.getNUMERO_MIN_GIOCATORI() - 1, gp.getNUMERO_MAX_GIOCATORI() + 1);
        gp.setN_giocatori(num);
    }
    /**
    * INSERISCI TIPO PARTITA: metodo che permette all'utende di scegliere il tipo di partita che vuole fare
    */
    public void inserisciTipoPartita() {
        try {
            TipoPartita t = getTipoPartita("INSERISCI IL TIPO DI PARTITA CHE VUOI EFFETTUARE", "IL VALORE INSERITO NON E' ACCETTABILE");
            gp.setTipoPartita(t);
        } catch (IOException ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * INIZIO PARTITA: metodo che permette al giocatore di scegliere il tipo partita, il numero dei giocatori, 
     * permette di inserire i giocatori e di default assegna gli obiettivi e i territori
     */
    public void inizioPartita() {
        inserisciTipoPartita();
        inserisciNumeroGiocatori();
        for (int i = 0; i < gp.getN_giocatori(); i++) {
            inserisciGiocatore();
        }
        try {
            gp.addObiettivi();
            gp.addTerritoriPartita();
            for (int i = 0; i < gp.getN_giocatori(); i++) {
                String psw = getPassword(i);
                System.out.println("================================================"
                        + "=============================================");
                System.out.println("E' IL TURNO DEL GIOCATORE: " + ricercaGiocatore(psw));
                faseRinforzo(getPassword(i));
            }
        } catch (IOException | RisikoExceptions ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void turnoDiGioco(String psw) {
        faseRinforzo(psw);
        faseAttacco(psw);
        faseSpostamento(psw);
    }

    public void inserisciGiocatore() {
        String nome = ioconsole.LeggiConsole.getStringNonVuota("INSERISCI IL TUO NOME");
        try {
            TipoColore colore = getTipoColore("INSERISCI IL COLORE DELLA TUA ARMATA", "IL VALORE INSERITO NON E' ACCETTABILE");
            String psw = getPassword("INSERISCI LA TUA PASSWORD IDENTIFICATIVA", "IL VALORE INSERITO NON E' ACCETTABILE \n(più di 8 caratteri, almeno una maiuscola, una minuscola, un numero e un carattere speciale)");
            gp.addGiocatore(new Giocatore(nome, colore, psw, 0, 0));
            System.out.println(new Giocatore(nome, colore, psw, 0, 0));

        } catch (IOException | GiocatoreGiaRegistrato ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String ricercaGiocatore(String psw) {
        try {
            return gp.getGiocatore(psw).toString();
        } catch (IOException | GiocatoreNonRegistrato ex) {
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
            gp.calcolaRinforzi(psw);
            while (gp.getGiocatore(psw).getRinforzi() > 0) {
                stampaTerritori(gp.listaTerritoriGiocatorePartita(psw));
                stampaObiettivoPersonale(psw);
                System.out.println("Numero Rinforzi: " + gp.getGiocatore(psw).getRinforzi());
                String territorio = getTerritorioRinforzo(psw, "INSERISCI IL NOME DEL TERRITORIO AL QUALE VUOI AGGIUNGERE LE TRUPPE", "IL VALORE INSERITO NON E' ACCETTABILE");
                int truppeRinforzo = getTruppeRinforzo(psw, "INSERISCI IL NUMERO DI TRUPPE DA AGGIUNGERE AL TERRITORIO", "IL VALORE INSERITO NON E' ACCETTABILE. INSERISCI UN INTERO");
                gp.faseRinforzo(psw, territorio, truppeRinforzo);
            }
        } catch (IOException | RisikoExceptions ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void faseAttacco(String psw) {
        System.out.println("VUOI CONTINUARE CON LA FASE DI ATTACCO (true) OPPURE PASSARE ALLO SPOSTAMENTO TRUPPE(false)?");
        if (scelta()) {
            avviaTimer();
            System.out.println("!!! IL TIMER E' PARTITO !!! (5 minuti)");
            boolean scelta = false;
            try {
                do {
                    if (!timerScaduto) {
                        stampaTerritoriFaseAttacco(gp.listaTerritoriPartita(), gp.listaTerritoriGiocatorePartita(psw));
                        stampaObiettivoPersonale(psw);
                    }
                    if (timerScaduto) {
                        break;
                    }
                    String territorioAttaccante = getTerritorioAttaccante(psw, "INSERISCI IL TERRITORIO DAL QUALE VUOI ATTACCARE", "IL VALORE INSERITO NON E' ACCETTABILE");
                    if (timerScaduto) {
                        break;
                    }
                    String territorioDifensore = getTerritorioDifensore(psw, territorioAttaccante, "INSERISCI IL TERRITORIO CHE VUOI ATTACCARE", "IL VALORE INSERITO NON E' ACCETTABILE");
                    if (!timerScaduto) {
                        gp.faseAttacco(psw, territorioAttaccante, territorioDifensore);
                    }
                    if (!timerScaduto) {
                        System.out.println("VUOI CONTINUARE AD ATTACCARE(true) OPPURE PASSARE ALLA FASE SUCCESSIVA(false)");
                        scelta = scelta();
                    }
                } while (!timerScaduto && scelta);
                interrompiTimer();
                System.out.println("TERRITORI AGGIORNATI DOPO LA FASE DI ATTACCO:");
                stampaTerritori(gp.listaTerritoriPartita());
                System.out.println("\n");
                gp.pescaCarta(psw);
            } catch (IOException ex) {
                Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RisikoExceptions ex) {
                System.out.println(ex);
            }
        }
    }

    public void stampaTerritori(ArrayList<TerritorioPartita> lista) {
        GestionePartita.stampaListaTerritori(lista);
    }

    public void stampaTerritoriFaseAttacco(ArrayList<TerritorioPartita> lista1, ArrayList<TerritorioPartita> lista2) {
        GestionePartita.stampaListaTerritoriFaseAttacco(lista1, lista2);
    }

    public void stampaObiettivoPersonale(String psw) {
        try {
            System.out.println(gp.getObiettivoPartita(psw));
        } catch (IOException | ObiettivoNonRegistrato ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void faseSpostamento(String psw) {
        System.out.println("VUOI CONTINUARE CON LA FASE DI SPOSTAMENTO(true) OPPURE PASSARE IL TURNO(false)?");
        try {
            if (scelta()) {
                avviaTimer();
                System.out.println("!!! IL TIMER E' PARTITO !!! (5 minuti)");
                boolean scelta;
                do {
                    stampaTerritori(gp.listaTerritoriGiocatorePartita(psw));
                    stampaObiettivoPersonale(psw);
                    if (timerScaduto) {
                        break;
                    }
                    String territorioPartenza = getTerritorioPartenza(psw, "INSERISCI IL TERRITORIO DAL QUALE VUOI SPOSTARE LE TRUPPE", "IL VALORE INSERITO NON E' ACCETTABILE");
                    if (timerScaduto) {
                        break;
                    }
                    String territorioDestinazione = getTerritorioDestinazione(psw, territorioPartenza, "INSERISCI IL TERRITORIO SU CUI VUOI SPOSTARE LE TRUPPE", "IL VALORE INSERITO NON E' ACCETTABILE");
                    if (timerScaduto) {
                        break;
                    }
                    int truppe = getTruppeSpostamento(psw, territorioPartenza, "INSERISCI IL NUMERO DI TRUPPE CHE VUOI SPOSTARE DAL TERRITORIO", "IL VALORE INSERITO NON E' ACCETTABILE");
                    if (!timerScaduto) {
                        gp.faseSpostamento(territorioPartenza, territorioDestinazione, truppe);
                    }
                    System.out.println("VUOI CONTINUARE A SPOSTARE LE TUE TRUPPE(true) OPPURE PASSARE IL TURNO(false)");
                    scelta = scelta();
                } while (!timerScaduto && scelta);
                interrompiTimer();
                System.out.println("TERRITORI AGGIORNATI DOPO LA FASE DI SPOSTAMENTO:");
                stampaTerritori(gp.listaTerritoriGiocatorePartita(psw));
                System.out.println("\n");
            }
        } catch (IOException | RisikoExceptions ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void controlloStatoPartita(String psw) throws FinePartita {
        try {
            gp.controlloStatoObiettivoGiocatore(psw);
        } catch (IOException | GiocatoreNonRegistrato | TerritorioNonRegistrato | ObiettivoNonRegistrato ex) {

            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
