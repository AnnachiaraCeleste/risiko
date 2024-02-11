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
import exceptions.*;
import model.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import logic.*;

/**
 *
 * @author Annachiara
 */
public class TestPartita extends LeggiConsole {

    private Timer timer;
    private boolean timerScaduto;
    private FaseAttacco fa;
    private FaseSpostamento fs;
    private FaseRinforzo fr;
    private FineObiettivo fo;

    public TestPartita(GestionePartita gp) {
        super(gp);
        this.fa = new FaseAttacco();
        this.fs = new FaseSpostamento();
        this.fr = new FaseRinforzo();
        this.fo = new FineObiettivo();
    }

    //TIMER-------------------------------------------------------------------//
    private void avviaTimer() {
        timer = new Timer();
        timerScaduto = false;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Azioni da eseguire quando scade il tempo dell'attacco
                System.out.println("<<<<IL TIMER E' SCADUTO>>>>");
                timerScaduto = true;
                timer.cancel();  // Ferma il timer dopo l'esecuzione
                Thread.currentThread().interrupt();
            }
        }, 180000);
    }

    private void interrompiTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    /**
     * INSERISCI NUMERO GIOCATORI: metodo che chiede all'utente di inserire un
     * intero ed esegue gli opportuni controlli
     */
    public void inserisciNumeroGiocatori() {
        int num = getIntInRange("INSERISCI IL NUMERO DI GIOCATORI CHE PARTECIPANO ALLA PARTITA", "IL VALORE INSERITO NON E' ACCETTABILE", gp.getNUMERO_MIN_GIOCATORI() - 1, gp.getNUMERO_MAX_GIOCATORI() + 1);
        gp.setN_giocatori(num);
    }

    /**
     * INSERISCI TIPO PARTITA: metodo che permette all'utende di scegliere il
     * tipo di partita che vuole fare
     */
    public void inserisciTipoPartita() {
        try {
            TipoPartita t = getTipoPartita("INSERISCI IL TIPO DI PARTITA CHE VUOI EFFETTUARE (CLASSICA o TORNEO)", "IL VALORE INSERITO NON E' ACCETTABILE");
            gp.setTipoPartita(t);
        } catch (IOException ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * INIZIO PARTITA: metodo che permette al giocatore di scegliere il tipo
     * partita, il numero dei giocatori, permette di inserire i giocatori e di
     * default assegna gli obiettivi e i territori
     */
    public void inizioPartita() {
        inserisciTipoPartita();
        inserisciNumeroGiocatori();
        System.out.println(gp);
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
                faseRinforzoInizio(getPassword(i));
            }
        } catch (IOException | RisikoExceptions ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * TURNO DI GIOCO: metodo che gestisce il turno di gioco di un utente (fase
     * rinforzo, attacco, spostamento)
     *
     * @param psw del giocatore che esegue il turno
     */
    public void turnoDiGioco(String psw) {
        faseRinforzo(psw);
        faseAttacco(psw);
        faseSpostamento(psw);
    }

    /**
     * INSERISCI GIOCATORE: metodo per assegnare nome, password e colore armata
     * ad un giocatore
     */
    public void inserisciGiocatore() {
        String nome = getStringNonVuota("INSERISCI IL TUO NOME");
        try {
            TipoColore colore = getTipoColore("INSERISCI IL COLORE DELLA TUA ARMATA", "IL VALORE INSERITO NON E' ACCETTABILE");
            String psw = getPassword("INSERISCI LA TUA PASSWORD IDENTIFICATIVA", "IL VALORE INSERITO NON E' ACCETTABILE \n(più di 3 caratteri, almeno una maiuscola, una minuscola, un numero e un carattere speciale)");
            gp.addGiocatore(new Giocatore(nome, colore, psw));
            System.out.println(new Giocatore(nome, colore, psw));
        } catch (IOException | GiocatoreGiaRegistrato ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * RICERCA GIOCATORE: metodo per ricercare un giocatore grazie alla psw
     *
     * @param psw del giocatore
     * @return la stringa con nome, colore e password del giocatore
     */
    public String ricercaGiocatore(String psw) {
        try {
            return gp.getGiocatore(psw).toString();
        } catch (IOException | GiocatoreNonRegistrato ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * GET PASSWORD
     *
     * @param i posizione del giocatore
     * @return la password nella posizione i
     */
    public String getPassword(int i) {
        try {
            return gp.getIofGiocatorePartita().get(i).getPassword();
        } catch (IOException ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * SCELTA: metodo che permette di eseguire una scelta true o false
     *
     * @return true o false
     */
    public boolean scelta() {
        boolean scelta = utils.LeggiConsole.getBoolean("(effettua la tua scelta)", "IL VALORE INSERITO NON E' ACCETTABILE: inserisci \"true\" o \"false\"");
        return scelta;
    }

    /**
     * FASE RINFORZO INIZIO: dato un giocatore e i propri rinforzi del turno,
     * vengono effettuati i rinforzi finchè i rinforzi non finiscono
     *
     * @param psw del giocatore che esegue la fase
     */
    public void faseRinforzoInizio(String psw) {
        try {
            while (gp.getGiocatore(psw).getRinforziTurno() > 0) {
                stampaTerritori(gp.listaTerritoriGiocatorePartita(psw));
                stampaObiettivoPersonale(psw);
                System.out.println("Numero Rinforzi: " + gp.getGiocatore(psw).getRinforziTurno());
                String territorio = getTerritorioRinforzo(psw, "INSERISCI IL NOME DEL TERRITORIO AL QUALE VUOI AGGIUNGERE LE TRUPPE", "IL VALORE INSERITO NON E' ACCETTABILE");
                int truppeRinforzo = getTruppeRinforzo(psw, "INSERISCI IL NUMERO DI TRUPPE DA AGGIUNGERE AL TERRITORIO", "IL VALORE INSERITO NON E' ACCETTABILE. INSERISCI UN INTERO");
                fr.setTerritoriTruppePerFase(psw, territorio, " ", truppeRinforzo);
                fr.eseguiFase(psw);
            }
            gp.reimpostaTerritoriTruppe(psw);
        } catch (IOException | RisikoExceptions ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * FASE RINFORZO: fase rinforzo per le truppe rinforzo che si ottengono a
     * inizio partita a seconda per esempio delle carte o dei territori
     * conquistati
     *
     * @param psw del giocatore
     */
    public void faseRinforzo(String psw) {
        try {
            fr.calcolaTruppeRinforzo(psw);
            faseRinforzoInizio(psw);
        } catch (IOException | CartaNonRegistrataPSWNome | GiocatoreNonRegistrato ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * FASE ATTACCO: metodo per esequire a propria scelta un numero variabile di
     * attacchi in un tempo di tre minuti.
     *
     * @param psw del giocatore
     */
    public void faseAttacco(String psw) {
        System.out.println("VUOI CONTINUARE CON LA FASE DI ATTACCO (true) OPPURE PASSARE ALLO SPOSTAMENTO TRUPPE(false)?");
        if (scelta()) {
            avviaTimer();
            System.out.println("!!! IL TIMER E' PARTITO !!! (3 minuti)");
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
                        fa.setTerritoriTruppePerFase(psw, territorioAttaccante, territorioDifensore, 0);
                        fa.eseguiFase(psw);
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
                fa.pescaCarta(psw);
                gp.reimpostaTerritoriTruppe(psw);
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

    /**
     * FASE SPOSTAMENTO: metodo per effettuare lo spostamento di un numero
     * variabile truppe da un proprio territorio ad un'altro
     *
     * @param psw del giocatore
     */
    private void faseSpostamento(String psw) {
        System.out.println("VUOI CONTINUARE CON LA FASE DI SPOSTAMENTO(true) OPPURE PASSARE IL TURNO(false)?");
        if (scelta()) {
            try {
                avviaTimer();
                System.out.println("!!! IL TIMER E' PARTITO !!! (3 minuti)");
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
                        fs.setTerritoriTruppePerFase(psw, territorioPartenza, territorioDestinazione, truppe);
                        fs.eseguiFase(psw);
                        System.out.println("VUOI CONTINUARE A SPOSTARE LE TUE TRUPPE(true) OPPURE PASSARE IL TURNO(false)");
                    }
                } while (!timerScaduto && scelta());
                interrompiTimer();
                System.out.println("TERRITORI AGGIORNATI DOPO LA FASE DI SPOSTAMENTO:");
                stampaTerritori(gp.listaTerritoriGiocatorePartita(psw));
                System.out.println("\n");
            } catch (IOException | RisikoExceptions ex) {
                Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * CONTROLLO STATO PARTITA: metodo che controlla all'inizio e all'inizio e
     * alla fine del proprio turno se il giocatore ha portato a termine le
     * richieste del proprio obiettivo
     *
     * @param psw del giocatore
     * @throws ControlloObiettivo
     */
    public void controlloStatoPartita(String psw) throws ControlloObiettivo {
        try {
            fo.eseguiFase(psw);
        } catch (IOException | GiocatoreNonRegistrato | TerritorioNonRegistrato | ObiettivoNonRegistrato ex) {
            Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
