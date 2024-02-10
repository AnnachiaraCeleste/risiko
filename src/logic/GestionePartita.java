/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.*;
import enums.*;
import exceptions.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author Annachiara
 */
public abstract class GestionePartita {

    protected static final int NUMERO_MIN_GIOCATORI = 2;
    protected static final int NUMERO_MAX_GIOCATORI = 6;
    protected int nGiocatori;
    protected TipoPartita tipoPartita;
    protected static final LocalDate DATA_PARTITA = LocalDate.now();
    protected IOObjectFileGiocatorePartita iofGiocatorePartita;
    protected IOObjectFileObiettivoPartita iofObiettivoPartita;
    protected IOObjectFileCarteArmiPartita iofCarteArmiPartita;
    protected IOObjectFileTerritorioPartita iofTerritorioPartita;
    protected IOObjectFileTerritorioDettagliato iofTerritorioDettagliato;
    protected IOObjectFileObiettivo iofObiettivo;
    protected static final String SEPARATOR = ";";

    public int getNUMERO_MIN_GIOCATORI() {
        return NUMERO_MIN_GIOCATORI;
    }

    public int getNUMERO_MAX_GIOCATORI() {
        return NUMERO_MAX_GIOCATORI;
    }

    public int getN_giocatori() {
        return nGiocatori;
    }

    public void setN_giocatori(int n_giocatori) {
        this.nGiocatori = n_giocatori;
    }

    public TipoPartita getTipoPartita() {
        return tipoPartita;
    }

    public void setTipoPartita(TipoPartita tipoPartita) {
        this.tipoPartita = tipoPartita;
    }

    public IOObjectFileGiocatorePartita getIofGiocatorePartita() {
        return iofGiocatorePartita;
    }

    public IOObjectFileObiettivoPartita getIofObiettivoPartita() {
        return iofObiettivoPartita;
    }

    public IOObjectFileCarteArmiPartita getIofCarteArmiPartita() {
        return iofCarteArmiPartita;
    }

    public IOObjectFileTerritorioPartita getIofTerritorioPartita() {
        return iofTerritorioPartita;
    }

    public GestionePartita() {
        iofGiocatorePartita = new IOObjectFileGiocatorePartita("giocatoriPartita.txt", SEPARATOR);
        iofObiettivoPartita = new IOObjectFileObiettivoPartita("obiettiviPartita.txt", SEPARATOR);
        iofCarteArmiPartita = new IOObjectFileCarteArmiPartita("carteArmiPartita.txt", SEPARATOR);
        iofTerritorioPartita = new IOObjectFileTerritorioPartita("territoriPartita.txt", SEPARATOR);
        iofTerritorioDettagliato = new IOObjectFileTerritorioDettagliato("territori.txt", SEPARATOR);
        iofObiettivo = new IOObjectFileObiettivo("obiettivi.txt", SEPARATOR);
    }

    public GestionePartita(int n_giocatori, TipoPartita tipoPartita) {
        this.nGiocatori = n_giocatori;
        this.tipoPartita = tipoPartita;
        iofGiocatorePartita = new IOObjectFileGiocatorePartita("giocatoriPartita.txt", SEPARATOR);
        iofObiettivoPartita = new IOObjectFileObiettivoPartita("obiettiviPartita.txt", SEPARATOR);
        iofCarteArmiPartita = new IOObjectFileCarteArmiPartita("carteArmiPartita.txt", SEPARATOR);
        iofTerritorioPartita = new IOObjectFileTerritorioPartita("territoriPartita.txt", SEPARATOR);
        iofTerritorioDettagliato = new IOObjectFileTerritorioDettagliato("territori.txt", SEPARATOR);
        iofObiettivo = new IOObjectFileObiettivo("obiettivi.txt", SEPARATOR);
    }

    @Override
    public String toString() {
        return "-----------------------------\n"
                + "DETTAGLI PARTITA:\n"
                + "-----------------------------\n"
                + "data: " + DATA_PARTITA.format(dtformatters.DateTimeIT.DATE)
                + "\nnumero giocatori: " + nGiocatori
                + "\ntipo partita: " + tipoPartita + "\n"
                + "-----------------------------\n";
    }

    //////////////////////////////////GET LINE//////////////////////////////////
    /**
     * GET LINE GIOCATORE: Metodo che ricerca (per password) e restituisce la
     * riga del file in cui è presente un certo giocatore
     *
     * @param password identificativa
     * @return indice della riga del file
     * @throws IOException
     */
    public int getLineGiocatore(String password) throws IOException {
        boolean giocatore_trovato = false;
        int line_giocatore = -1;
        ArrayList<Giocatore> listaGiocatori = iofGiocatorePartita.loadData();
        // RICERCA PER CHIAVE
        for (int i = 0; !giocatore_trovato && i < listaGiocatori.size(); i++) {
            if (listaGiocatori.get(i).getPassword().equals(password)) {
                giocatore_trovato = true;
                line_giocatore = i;
            }
        }
        return line_giocatore;
    }

    /**
     * GET LINE GIOCATORE: Metodo che ricerca (per colore) e restituisce la riga
     * del file in cui è presente un certo giocatore
     *
     * @param colore identificativo
     * @return indice della riga del file
     * @throws IOException
     */
    public int getLineGiocatore(TipoColore colore) throws IOException {
        boolean giocatore_trovato = false;
        int line_giocatore = -1;
        ArrayList<Giocatore> listaGiocatori = iofGiocatorePartita.loadData();
        // RICERCA PER CHIAVE
        for (int i = 0; !giocatore_trovato && i < listaGiocatori.size(); i++) {
            if (listaGiocatori.get(i).getColore().equals(colore)) {
                giocatore_trovato = true;
                line_giocatore = i;
            }
        }
        return line_giocatore;
    }

    /**
     * GET LINE OBIETTIVO: Metodo che ricerca (per password) e restituisce la
     * riga del file in cui è presente l'obiettivo di un certo giocatore
     *
     * @param psw identificativa
     * @return indice della riga del file
     * @throws IOException
     */
    public int getLineObiettivoPartita(String psw) throws IOException {
        boolean obiettivo_trovato = false;
        int line_obiettivo = -1;
        ArrayList<ObiettivoPartita> listaObiettivi = iofObiettivoPartita.loadData();
        // RICERCA PER CHIAVE
        for (int i = 0; !obiettivo_trovato && i < listaObiettivi.size(); i++) {
            if (listaObiettivi.get(i).getPassword().equals(psw)) {
                obiettivo_trovato = true;
                line_obiettivo = i;
            }
        }
        return line_obiettivo;
    }

    /**
     * GET LINE TERRITORIO: Metodo che ricerca (per nome) e restituisce la riga
     * del file in cui è presente un certo territorio
     *
     * @param nome identificativo del territorio
     * @return indice della riga del file
     * @throws IOException
     */
    public int getLineTerritorioPartita(String nome) throws IOException {
        boolean territorio_trovato = false;
        int line_territorio = -1;
        ArrayList<TerritorioPartita> territoriPartita = iofTerritorioPartita.loadData();
        // RICERCA PER CHIAVE
        for (int i = 0; !territorio_trovato && i < territoriPartita.size(); i++) {
            if (territoriPartita.get(i).getNome().equals(nome)) {
                territorio_trovato = true;
                line_territorio = i;
            }
        }
        return line_territorio;
    }

    /**
     * GET LINE TERRITORIO DETTAGLIATO: Metodo che ricerca (per nome) e
     * restituisce la riga del file in cui è presente un certo territorio
     * dettagliato
     *
     * @param nome identificativo del territorio
     * @return indice della riga del file
     * @throws IOException
     */
    public int getLineTerritorioDettagliato(String nome) throws IOException {
        boolean territorio_trovato = false;
        int line_territorio = -1;
        ArrayList<TerritorioDettagliato> territoriDettagliati = iofTerritorioDettagliato.loadData();
        // RICERCA PER CHIAVE
        for (int i = 0; !territorio_trovato && i < territoriDettagliati.size(); i++) {
            if (territoriDettagliati.get(i).getNome().equals(nome)) {
                territorio_trovato = true;
                line_territorio = i;
            }
        }
        return line_territorio;
    }

    /**
     * GET LINE CARTE: Metodo che ricerca (per nome) e restituisce la riga del
     * file in cui è presente una certa carta con il nome del territorio
     * corrispondente.
     *
     * @param nome del territorio da ricercare
     * @return indice della riga del file
     * @throws IOException
     */
    public int getLineCarteArmiPartita(String nome) throws IOException {
        boolean carta_trovata = false;
        int line_carta = -1;
        ArrayList<CarteArmiPartita> cartePartita = iofCarteArmiPartita.loadData();
        // RICERCA PER CHIAVE
        for (int i = 0; !carta_trovata && i < cartePartita.size(); i++) {
            if (cartePartita.get(i).getNome().equals(nome)) {
                carta_trovata = true;
                line_carta = i;
            }
        }
        return line_carta;
    }

    /**
     * GET LINE CARTE ARMI: Metodo che ricerca (per password e per tipoArma) e
     * restituisce la riga del file in cui è presente una certa carta che
     * possiede una certa tipologia di arma e che appartiene ad un giocatore
     *
     * @param psw identificativa del giocatore
     * @param t tipologia di arma da ricercare
     * @return indice della riga del file
     * @throws IOException
     */
    public int getLineCarteArmiPartita(String psw, TipoArma t) throws IOException {
        boolean carta_trovata = false;
        int line_carta = -1;
        ArrayList<CarteArmiPartita> cartePartita = listaCarteGiocatorePartita(psw);
        // RICERCA PER CHIAVE
        for (int i = 0; !carta_trovata && i < cartePartita.size(); i++) {
            if (cartePartita.get(i).getArma().equals(t)) {
                carta_trovata = true;
                line_carta = i;
            }
        }
        return line_carta;
    }

    ///////////////////////// CRUD FUNCTIONS GIOCATORE /////////////////////////
    /**
     * ADD GIOCATORE: Metodo che dato un giocatore lo inserisce nel gioco
     *
     * @param g giocatore che si vuole inserire
     * @throws IOException
     * @throws GiocatoreGiaRegistrato
     */
    public void addGiocatore(Giocatore g) throws IOException, GiocatoreGiaRegistrato {
        int line_giocatore = getLineGiocatore(g.getPassword());
        int line_colore = getLineGiocatore(g.getColore());
        if (line_giocatore != -1 || line_colore != -1) {
            throw new GiocatoreGiaRegistrato(g.getPassword());
        }
        g.setTruppe(numeroArmateIniziali());
        iofGiocatorePartita.add(g);

    }

    /**
     * GET GIOCATORE: Metodo che data una certa password restituisce il
     * giocatore avente quella password
     *
     * @param psw identificativa del giocatore
     * @return giocatore avente una certa password
     * @throws IOException
     * @throws GiocatoreNonRegistrato
     */
    public Giocatore getGiocatore(String psw) throws IOException, GiocatoreNonRegistrato {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_giocatore = getLineGiocatore(psw);
        if (line_giocatore == -1) {
            throw new GiocatoreNonRegistrato(psw);
        }
        return iofGiocatorePartita.get(line_giocatore);
    }

    /**
     * ADD CARTA ARMI PARTITA: Metodo che aggiunge una carta alla partita
     *
     * @param c carta da inserire
     * @throws IOException
     * @throws CartaGiaRegistrata
     */
    public void addCartaArmiPartita(CarteArmiPartita c) throws IOException, CartaGiaRegistrata {
        int line_carta = getLineCarteArmiPartita(c.getNome());
        if (line_carta != -1) {
            throw new CartaGiaRegistrata(c.getNome(), c.getArma());
        }
        iofCarteArmiPartita.add(c);
    }

    /**
     * REMOVE CARTA ARMI PARTITA: Metodo che sottrae una carta a ad un certo
     * giocatore che viene identificato da una password
     *
     * @param psw identificativa del giocatore
     * @param a tipo arma
     * @throws IOException
     * @throws CartaNonRegistrataPSWNome
     */
    public void removeCartaArmiPartita(String psw, TipoArma a) throws IOException, CartaNonRegistrataPSWNome {
        int line_carta = getLineCarteArmiPartita(psw, a);
        if (line_carta == -1) {
            throw new CartaNonRegistrataPSWNome(psw, a);
        }
        iofCarteArmiPartita.remove(line_carta);
    }

    /**
     * ADD TERRITORIO PARTITA: Metodo che aggiunge un certo territorio t alla
     * partita
     *
     * @param t territorio
     * @throws IOException
     * @throws TerritorioGiaRegistrato
     */
    public void addTerritorioPartita(TerritorioPartita t) throws IOException, TerritorioGiaRegistrato {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_territorio = getLineTerritorioPartita(t.getNome());
        if (line_territorio != -1) {
            throw new TerritorioGiaRegistrato(t.getNome());
        }
        iofTerritorioPartita.add(t);
    }

    /**
     * GET TERRITORIO PARTITA: Metodo che restituisce il territorio della
     * partita sapendo il suo nome
     *
     * @param nome nome del territorio
     * @return territorio del nome richiesto
     * @throws IOException
     * @throws TerritorioNonRegistrato
     */
    public TerritorioPartita getTerritorioPartita(String nome) throws IOException, TerritorioNonRegistrato {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_territorio = getLineTerritorioPartita(nome);
        if (line_territorio == -1) {
            throw new TerritorioNonRegistrato(nome);
        }
        return iofTerritorioPartita.get(line_territorio);
    }

    /**
     * ADD OBIETTIVO PARTITA: Metodo che aggiunge alla partita un'obiettivo
     *
     * @param o obiettivo da aggiungere
     * @throws IOException
     * @throws ObiettivoGiaRegistrato
     */
    public void addObiettivoPartita(ObiettivoPartita o) throws IOException, ObiettivoGiaRegistrato {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_obiettivo = getLineObiettivoPartita(o.getPassword());
        if (line_obiettivo != -1) {
            throw new ObiettivoGiaRegistrato(o.getPassword());
        }
        iofObiettivoPartita.add(o);
    }

    /**
     * GET OBIETTIVO PARTITA: Metodo che restituisce l'obiettivo di una certa
     * persona sapendo la password
     *
     * @param psw identificativo giocatore
     * @return Obiettivo del giocatore avente una certa password
     * @throws IOException
     * @throws ObiettivoNonRegistrato
     */
    public ObiettivoPartita getObiettivoPartita(String psw) throws IOException, ObiettivoNonRegistrato {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_obiettivo = getLineObiettivoPartita(psw);
        if (line_obiettivo == -1) {
            throw new ObiettivoNonRegistrato(psw);
        }
        return iofObiettivoPartita.get(line_obiettivo);
    }

    /**
     * REMOVE OBIETTIVO PARTITA: Metodo che toglie l'obiettivo di un certo
     * giocatore avente una certa password
     *
     * @param psw identificativo giocatore
     * @throws IOException
     * @throws ObiettivoNonRegistrato
     */
    public void removeObiettivoPartita(String psw) throws IOException, ObiettivoNonRegistrato {
        // ricerca del giocatore
        int line_obiettivo = getLineObiettivoPartita(psw);
        if (line_obiettivo == -1) {
            throw new ObiettivoNonRegistrato(psw);
        }
        // ora rimuovo il giocatore
        iofObiettivoPartita.remove(line_obiettivo);
    }

    /**
     * GET TERRITORIO DETTAGLIATO: Metodo che ritorna il territorio detaggliato
     * sapendo il nome di esso
     *
     * @param nome del territorio che si vuole conoscere
     * @return territorio dettagliato avente il nome inserito
     * @throws IOException
     * @throws TerritorioNonRegistrato
     */
    public TerritorioDettagliato getTerritorioDettagliato(String nome) throws IOException, TerritorioNonRegistrato {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_territorio = getLineTerritorioDettagliato(nome);
        if (line_territorio == -1) {
            throw new TerritorioNonRegistrato(nome);
        }
        return iofTerritorioDettagliato.get(line_territorio);
    }

    /**
     * NUMERO ARMATE INIZIALI: metodo per calcolare il numero di armate per
     * rafforzare i territori che devono essere assegnate ad ogni giocatore ad
     * inizio partita
     *
     * @return numero di armate
     */
    private int numeroArmateIniziali() {
        return 20 + 5 * (NUMERO_MAX_GIOCATORI - nGiocatori);
    }

    //////////////////////////FUNZIONI INIZIO PARTITA///////////////////////////
    /*
    Queste due funzioni non sono presenti in una propria classe specifica 
    perchè non dipendono da un singolo giocatore
     */
    /**
     * ADD TERRITORI PARTITA: metodo per assegnare ad ogni giocatore un numero
     * opportuno di territori in modo randomico ad inizio partita (viene
     * assegnato di default una unità di armate)
     *
     * @throws IOException
     */
    public void addTerritoriPartita() throws IOException, TerritorioGiaRegistrato {
        int[] idxTerritori = new int[42];
        boolean trovato;
        for (int i = 0; i < idxTerritori.length; i++) {
            do {
                idxTerritori[i] = (int) (Math.random() * (41 + 1));
                trovato = false;
                for (int j = 0; j < i; j++) {
                    if (idxTerritori[j] == idxTerritori[i]) {
                        trovato = true;
                    }
                }
            } while (trovato);
        }
        ArrayList<Giocatore> giocatori = iofGiocatorePartita.loadData();
        ArrayList<TerritorioDettagliato> territoriPartita = iofTerritorioDettagliato.loadData();

        for (int i = 0, j = 0; i < giocatori.size(); i++) {
            for (int k = 0; j < territoriPartita.size() && k < ((int) 42 / nGiocatori); k++, j++) {
                TerritorioPartita territorio = new TerritorioPartita(territoriPartita.get(idxTerritori[j]).getNome(),
                        territoriPartita.get(idxTerritori[j]).getArma(), giocatori.get(i).getPassword(), 1);
                addTerritorioPartita(territorio);
                giocatori.get(i).setTruppe(giocatori.get(i).getTruppe() - 1);
            }
            if ((nGiocatori == 4 || nGiocatori == 5) && (i == 0 || i == 1 && j < territoriPartita.size())) {
                TerritorioPartita territorio = new TerritorioPartita(territoriPartita.get(idxTerritori[j]).getNome(),
                        territoriPartita.get(idxTerritori[j]).getArma(), giocatori.get(i).getPassword(), 1);
                addTerritorioPartita(territorio);
                giocatori.get(i).setTruppe(giocatori.get(i).getTruppe() - 1);
                j++;
            }
        }

        ArrayList<TerritorioPartita> territoriAssegnati = iofTerritorioPartita.loadData();
        Collections.sort(territoriAssegnati);
        iofGiocatorePartita.saveData(giocatori);
        iofTerritorioPartita.saveData(territoriAssegnati);
    }

    /**
     * ADD OBIETTIVI: metodo per assegnare ad ogni giocatore il proprio
     * obiettivo della partita
     *
     * @throws IOException
     */
    public void addObiettivi() throws IOException, ObiettivoGiaRegistrato {
        int[] idxObiettivo = new int[nGiocatori];
        int x_max;
        int x_min;
        if (tipoPartita.equals(TipoPartita.CLASSICA)) {
            x_max = 13;
            x_min = 0;
        } else {
            x_max = 23;
            x_min = 14;
        }
        boolean trovato;
        for (int i = 0; i < idxObiettivo.length; i++) {
            do {
                idxObiettivo[i] = (int) (Math.random() * (x_max - x_min + 1)) + x_min;
                trovato = false;
                for (int j = 0; j < i; j++) {
                    if (idxObiettivo[j] == idxObiettivo[i]) {
                        trovato = true;
                    }
                }
            } while (trovato);
        }
        ArrayList<Giocatore> giocatori = iofGiocatorePartita.loadData();
        ArrayList<Obiettivo> listaObiettivi = iofObiettivo.loadData();
        for (int i = 0; i < giocatori.size(); i++) {
            ObiettivoPartita obiettivo = new ObiettivoPartita(listaObiettivi.get(idxObiettivo[i]).getObiettivo(),
                    listaObiettivi.get(idxObiettivo[i]).getTipoObiettivo(), giocatori.get(i).getPassword());
            addObiettivoPartita(obiettivo);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //---------------------METODI ASTRATTI DA IMPLEMENTARE--------------------//
    /**
     * ESEGUI FASE: metodo astratto da implementare nelle classi che
     * ereditano.Tutte le classi che saranno implementate hanno in comune un
     * giocatore con una psw specifica che in un momento della partita esegue le
     * tre fasi di gioco (rinforzo, attacco, spostamento)
     *
     * @param psw specifica del giocatore
     * @throws java.io.IOException
     * @throws exceptions.RisikoExceptions
     */
    public abstract void eseguiFase(String psw) throws IOException, RisikoExceptions;

    /**
     * SET TERRITORI TRUPPE PER FASE: metodo implementato per supportare i
     * metodi astratti esegui fase
     *
     * @param psw specifica del giocatore
     * @param territorioOrigine territorio di tipoAttacco o Rinforzo di partenza
     * @param territorioDestinazione territorio di tipoDifesa o che viene
     * rinforzato
     * @param truppe intero di truppe che vengono prese in considerazione
     * durante l'azione
     * @throws IOException
     */
    public void setTerritoriTruppePerFase(String psw, String territorioOrigine, String territorioDestinazione, int truppe) throws IOException{
            ArrayList<Giocatore> giocatori = iofGiocatorePartita.loadData();
            giocatori.get(getLineGiocatore(psw)).setTerritorioOrigine(territorioOrigine);
            giocatori.get(getLineGiocatore(psw)).setTerritorioDestinazione(territorioDestinazione);
            giocatori.get(getLineGiocatore(psw)).setTruppe(truppe);
            iofGiocatorePartita.saveData(giocatori);
        
    }
    /**
     * REIMPOSTA TERRITORI E TRUPPE: metodo creato per reimpostare a default
     * ""/0 i terriori per le fasi e le truppe.
     *
     * @param psw specifica del giocatore
     * @throws IOException
     */
    protected void reimpostaTerritoriTruppe(String psw) throws IOException {
        ArrayList<Giocatore> giocatori = iofGiocatorePartita.loadData();
        giocatori.get(getLineGiocatore(psw)).setTerritorioOrigine(" ");
        giocatori.get(getLineGiocatore(psw)).setTerritorioDestinazione(" ");
        giocatori.get(getLineGiocatore(psw)).setTruppe(0);
        iofGiocatorePartita.saveData(giocatori);
    }
    /**
     * STAMPA FASE: metodo per stampare in maniera riassunta la fase che viene effettuata
     * @param psw del giocatore che esegue la fase
     * @return il dettaglio della fase
     * @throws IOException
     * @throws RisikoExceptions 
     */
    public abstract String stampaFase(String psw)throws IOException, RisikoExceptions;

    //------------------------------------------------------------------------//
    //-----------------------------FUNZIONI UTILS-----------------------------//
    /**
     * LISTA TERRITORI PARTITA: restituisce la lista di tutti i territori della
     * partita
     *
     * @return un ArrayList di TerritoriPartita
     * @throws IOException
     */
    public ArrayList<TerritorioPartita> listaTerritoriPartita() throws IOException {
        return iofTerritorioPartita.loadData();
    }

    /**
     * LISTA TERRITORI DETTAGLIATI PER CONTINENTE: restituisce la lista di
     * territori di un dato continente
     *
     * @param continente tipo di continente da ricercare
     * @return una arraylist di TerritoriDettagliati
     * @throws IOException
     */
    public ArrayList<TerritorioDettagliato> listaTerritoriDettagliatiContinente(TipoContinente continente) throws IOException {
        ArrayList<TerritorioDettagliato> territoriDettagliati = iofTerritorioDettagliato.loadData();
        ArrayList<TerritorioDettagliato> territoriContinente = new ArrayList<>();
        for (int i = 0; i < territoriDettagliati.size(); i++) {
            if (territoriDettagliati.get(i).getContinente().equals(continente)) {
                territoriContinente.add(territoriDettagliati.get(i));
            }
        }
        Collections.sort(territoriContinente);
        return territoriContinente;
    }

    /**
     * LISTA TERRITORI PER COLORE: restituisce la lista di territori che ha un
     * un dato dipo di colore dell'armata
     *
     * @param colore colore armata
     * @return un arraylist di territori partita di una certa armata
     * @throws IOException
     */
    public ArrayList<TerritorioPartita> listaTerritoriColore(TipoColore colore) throws IOException {
        ArrayList<Giocatore> giocatori = iofGiocatorePartita.loadData();
        String psw = "";
        for (int i = 0; i < giocatori.size(); i++) {
            if (giocatori.get(i).getColore().equals(colore)) {
                psw = giocatori.get(i).getPassword();
                break;
            }
        }
        ArrayList<TerritorioPartita> territoriColore = listaTerritoriGiocatorePartita(psw);
        return territoriColore;
    }

    /**
     * LISTA TERRITORI PER GIOCATORE PARTITA: metodo che ricerca la lista di
     * territori posseduti da un certo giocatore
     *
     * @param password del giocatore da ricercare
     * @return un arraylist di territori partita di un dato giocatore
     * @throws IOException
     */
    public ArrayList<TerritorioPartita> listaTerritoriGiocatorePartita(String password) throws IOException {
        ArrayList<TerritorioPartita> territoriPartita = iofTerritorioPartita.loadData();
        ArrayList<TerritorioPartita> territoriGiocatore = new ArrayList<>();
        for (int i = 0; i < territoriPartita.size(); i++) {
            if (territoriPartita.get(i).getPasswordGiocatore().equals(password)) {
                territoriGiocatore.add(territoriPartita.get(i));
            }
        }
        Collections.sort(territoriGiocatore);
        return territoriGiocatore;
    }

    /**
     * LISTA CARTE GIOCATORE PARTITA: metodo che ricerca la lista di carte
     * possedute da un certo giocatore
     *
     * @param password del giocatore da ricercare
     * @return un arraylist di carte armi partita di un dato giocatore
     * @throws IOException
     */
    public ArrayList<CarteArmiPartita> listaCarteGiocatorePartita(String password) throws IOException {
        ArrayList<CarteArmiPartita> cartePartita = iofCarteArmiPartita.loadData();
        ArrayList<CarteArmiPartita> carteGiocatore = new ArrayList<>();
        for (int i = 0; i < cartePartita.size(); i++) {
            if (cartePartita.get(i).getPasswordGiocatore().equals(password)) {
                carteGiocatore.add(cartePartita.get(i));
            }
        }
        Collections.sort(carteGiocatore);
        return carteGiocatore;
    }

    /**
     * STAMPA LISTA TERRITORI: Metodo che stampa tutti i territori
     *
     * @param lista lista di tutti i territori
     */
    public static void stampaListaTerritori(ArrayList<TerritorioPartita> lista) {
        System.out.println("***************************************");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
        System.out.println("***************************************");
    }

    /**
     * STAMPA LISTA TERRITORI FASE ATTACCO: Metodo che stampa la lista di
     * territori con il suo proprietario
     *
     * @param listaT lista dei territori
     * @param listaG lista dei giocatori
     */
    public static void stampaListaTerritoriFaseAttacco(ArrayList<TerritorioPartita> listaT, ArrayList<TerritorioPartita> listaG) {
        System.out.println("**************************************************************************");
        System.out.println("(P=proprietario; T=truppe)");
        for (int i = 0, j = 0; i < listaT.size(); i++) {
            TerritorioPartita t = listaT.get(i);
            if (j < listaG.size()) {
                if (t.equals(listaG.get(j))) {
                    System.out.println("--->" + t.getNome() + " {P: " + t.getPasswordGiocatore() + "; T: " + t.getNumeroArmate() + '}');
                    j++;
                } else {
                    System.out.println(t.getNome() + " {P: " + t.getPasswordGiocatore() + "; T: " + t.getNumeroArmate() + '}');
                }
            } else {
                System.out.println(t.getNome() + " {P: " + t.getPasswordGiocatore() + "; T: " + t.getNumeroArmate() + '}');
            }
        }
        System.out.println("**************************************************************************");
    }

}
