package risiko;

import exceptions.*;
import exceptions.GiocatoreGiaRegistrato;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import exceptions.AttaccoFallito;
import risiko.CarteArmiPartita;
import risiko.Giocatore;
import risiko.IOObjectFileCarteArmiPartita;
import risiko.IOObjectFileGiocatorePartita;
import risiko.IOObjectFileObiettivo;
import risiko.IOObjectFileObiettivoPartita;
import risiko.IOObjectFileTerritorioDettagliato;
import risiko.IOObjectFileTerritorioPartita;
import risiko.Obiettivo;
import risiko.ObiettivoPartita;
import risiko.Territorio;
import risiko.TerritorioDettagliato;
import risiko.TerritorioPartita;
import risiko.TipoArma;
import risiko.TipoColore;
import risiko.TipoContinente;
import risiko.TipoPartita;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Annachiara
 */
public class GestionePartita {

    private static final int NUMERO_MIN_GIOCATORI = 3;
    private static final int NUMERO_MAX_GIOCATORI = 6;
    private final int n_giocatori;
    private final TipoPartita tipoPartita;
    private IOObjectFileGiocatorePartita iofGiocatorePartita;
    private IOObjectFileObiettivoPartita iofObiettivoPartita;
    private IOObjectFileCarteArmiPartita iofCarteArmiPartita;
    private IOObjectFileTerritorioPartita iofTerritorioPartita;
    private IOObjectFileTerritorioDettagliato iofTerritorioDettagliato;
    private IOObjectFileObiettivo iofObiettivo;
    private static final String SEPARATOR = ";";

    public GestionePartita(int n_giocatori, TipoPartita tipoPartita) {
        this.n_giocatori = n_giocatori;
        this.tipoPartita = tipoPartita;
        iofGiocatorePartita = new IOObjectFileGiocatorePartita("giocatoriPartita.txt", SEPARATOR);
        iofObiettivoPartita = new IOObjectFileObiettivoPartita("obiettiviPartita.txt", SEPARATOR);
        iofCarteArmiPartita = new IOObjectFileCarteArmiPartita("carteArmiPartita.txt", SEPARATOR);
        iofTerritorioPartita = new IOObjectFileTerritorioPartita("territoriPartita.txt", SEPARATOR);
        iofTerritorioDettagliato = new IOObjectFileTerritorioDettagliato("territori.txt", SEPARATOR);
        iofObiettivo = new IOObjectFileObiettivo("obiettivi.txt", SEPARATOR);
    }

    /**
     * GET LINE GIOCATORE: Metodo che ricerca (per password) e restituisce la
     * riga del file in cui è presente un certo giocatore
     *
     * @param password identificativa
     * @return indice della riga del file
     * @throws IOException
     */
    private int getLineGiocatore(String password) throws IOException {
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
    private int getLineGiocatore(TipoColore colore) throws IOException {
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
    private int getLineObiettivoPartita(String psw) throws IOException {
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
    private int getLineTerritorioPartita(String nome) throws IOException {
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
    private int getLineTerritorioDettagliato(String nome) throws IOException {
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
    private int getLineCarteArmiPartita(String nome) throws IOException {
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
    private int getLineCarteArmiPartita(String psw, TipoArma t) throws IOException {
        boolean carta_trovata = false;
        int line_carta = -1;
        ArrayList<CarteArmiPartita> cartePartita = iofCarteArmiPartita.loadData();
        // RICERCA PER CHIAVE
        for (int i = 0; !carta_trovata && i < cartePartita.size(); i++) {
            if (cartePartita.get(i).getPasswordGiocatore().equals(psw) && cartePartita.get(i).getArma().equals(t)) {
                carta_trovata = true;
                line_carta = i;
            }
        }
        return line_carta;
    }

    ///////////////////////// CRUD FUNCTIONS GIOCATORE /////////////////////////
    public void addGiocatore(Giocatore g) throws IOException, GiocatoreGiaRegistrato {
        int line_giocatore = getLineGiocatore(g.getPassword());
        int line_colore = getLineGiocatore(g.getColore());
        if (line_giocatore != -1 || line_colore != -1) {
            throw new GiocatoreGiaRegistrato(g.getPassword());
        }
        // aggiungo l'auto all'arraylist
        iofGiocatorePartita.add(g);
    }

    public void removeGiocatore(String psw) throws IOException {
        // ricerca del giocatore
        int line_giocatore = getLineGiocatore(psw);
        if (line_giocatore == -1) {
            //throw new GiocatoreNonRegistrato();
        }

        // ora rimuovo il giocatore
        iofGiocatorePartita.remove(line_giocatore);
    }

    public Giocatore getGiocatore(String psw) throws IOException {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_giocatore = getLineGiocatore(psw);
        if (line_giocatore == -1) {
            //throw new GiocatoreNonRegistrato(psw);
        }
        return iofGiocatorePartita.get(line_giocatore);
    }

    public void addCartaArmiPartita(CarteArmiPartita c) throws IOException {
        int line_carta = getLineCarteArmiPartita(c.getNome());
        if (line_carta != -1) {
            // throw new GiocatoreGiaRegistrato(g.getNome());
        }
        // aggiungo l'auto all'arraylist
        iofCarteArmiPartita.add(c);
    }

    public void removeCartaArmiPartita(String psw, TipoArma a) throws IOException {
        int line_carta = getLineCarteArmiPartita(psw, a);
        if (line_carta != -1) {
            // throw new GiocatoreGiaRegistrato(g.getNome());
        }

        // ora rimuovo il giocatore
        iofCarteArmiPartita.remove(line_carta);
    }

    public CarteArmiPartita getCartaArmiPartita(String nome) throws IOException {
        int line_carta = getLineCarteArmiPartita(nome);
        if (line_carta != -1) {
            // throw new GiocatoreGiaRegistrato(g.getNome());
        }
        return iofCarteArmiPartita.get(line_carta);
    }

    public void addTerritorioPartita(TerritorioPartita t) throws IOException {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_territorio = getLineTerritorioPartita(t.getNome());
        if (line_territorio == -1) {
            //throw new TerritorioNonRegistrato(psw);
        }
        iofTerritorioPartita.add(t);
    }

    public TerritorioPartita getTerritorioPartita(String nome) throws IOException {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_territorio = getLineTerritorioPartita(nome);
        if (line_territorio == -1) {
            //throw new TerritorioNonRegistrato(psw);
        }
        return iofTerritorioPartita.get(line_territorio);
    }

    public void addObiettivoPartita(ObiettivoPartita o) throws IOException {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_obiettivo = getLineObiettivoPartita(o.getPassword());
        if (line_obiettivo == -1) {
            //throw new TerritorioNonRegistrato(psw);
        }
        iofObiettivoPartita.add(o);
    }

    public TerritorioDettagliato getTerritorioDettagliato(String nome) throws IOException {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_territorio = getLineTerritorioDettagliato(nome);
        if (line_territorio == -1) {
            //throw new TerritorioNonRegistrato(psw);
        }
        return iofTerritorioDettagliato.get(line_territorio);
    }

    ////////////////////////////////////////////////////////////////////////////
    /**
     * ADD TERRITORI PARTITA: metodo per assegnare ad ogni giocatore un numero
     * opportuno di territori in modo randomico ad inizio partita (viene
     * assegnato di default una unità di armate)
     *
     * @throws IOException
     */
    public void addTerritoriPartita() throws IOException {
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
        
        for (int i = 0,j=0; i < giocatori.size(); i++) {            
            for (int k=0; j < territoriPartita.size() && k<((int) 42 / n_giocatori); k++,j++) {
                TerritorioPartita territorio = new TerritorioPartita(territoriPartita.get(idxTerritori[j]).getNome(),
                        territoriPartita.get(idxTerritori[j]).getArma(), giocatori.get(i).getPassword(), 1);
                addTerritorioPartita(territorio);
                giocatori.get(i).setRinforzi(numeroArmateIniziali() - 1);
            }
            if ((n_giocatori == 4 || n_giocatori == 5) && (i == 0 || i == 1 && j < territoriPartita.size())) {
                TerritorioPartita territorio = new TerritorioPartita(territoriPartita.get(idxTerritori[j]).getNome(),
                        territoriPartita.get(idxTerritori[j]).getArma(), giocatori.get(i).getPassword(), 1);
                addTerritorioPartita(territorio);
                giocatori.get(i).setRinforzi(numeroArmateIniziali() - 1);
                j++;
            }
        }
        
        ArrayList<TerritorioPartita> territoriAssegnati=iofTerritorioPartita.loadData();
        Collections.sort(territoriAssegnati);
        iofTerritorioPartita.saveData(territoriAssegnati);
    }

    /**
     * NUMERO ARMATE INIZIALI: metodo per calcolare il numero di armate per
     * rafforzare i territori che devono essere assegnate ad ogni giocatore ad
     * inizio partita
     *
     * @return numero di armate
     */
    private int numeroArmateIniziali() {
        return 20 + 5 * (NUMERO_MAX_GIOCATORI - n_giocatori);
    }

    /**
     * ADD OBIETTIVI: metodo per assegnare ad ogni giocatore il proprio
     * obiettivo della partita
     *
     * @throws IOException
     */
    public void addObiettivi() throws IOException {
        int[] idxObiettivo = new int[n_giocatori];
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
                    listaObiettivi.get(idxObiettivo[i]).getTipoObiettivo(), giocatori.get(i).getPassword(), false);
            addObiettivoPartita(obiettivo);
        }
    }

    /**
     * PESCA CARTA: metodo che assegna ad un giocatore una carta arma utile per
     * i rinforzi
     *
     * @param psw del giocatore
     * @throws IOException
     */
    public void pescaCarta(String psw) throws IOException {
        if (getGiocatore(psw).getNTerritoriConquistatiPerTurno() > 0) {
            ArrayList<CarteArmiPartita> carte = iofCarteArmiPartita.loadData();
            ArrayList<TerritorioDettagliato> territoriDettagliati = iofTerritorioDettagliato.loadData();
            boolean trovato;
            int idxCarta;
            do {
                idxCarta = (int) (Math.random() * (42 + 1));
                trovato = false;
                if (getCartaArmiPartita(territoriDettagliati.get(idxCarta).getNome()) != null) {
                    trovato = true;
                }
            } while (trovato);
            getCartaArmiPartita(territoriDettagliati.get(idxCarta).getNome()).setPasswordGiocatore(psw);

        }
    }
//////////////////////////////FUNZIONI PARTITA//////////////////////////////

    /**
     * CONTROLLO STATO OBIETTIVO GIOCATORE: metodo che controlla se il giocatore
     * ha completato il suo obiettivo a seconda del tipo di obiettivo che gli è
     * stato assegnato: una intera armata, due continenti, un numero di
     * territori elevato oppure per la versione torneo 7 territori da
     * conquistare.
     *
     * @param psw del giocatore
     * @return true se ha completato quanto richiesto altrimenti false
     * @throws IOException
     */
    public boolean controlloStatoObiettivoGiocatore(String psw) throws IOException {
        boolean statoObiettivo = false;
        boolean territorioNonPosseduto;
        int line_obiettivo = getLineObiettivoPartita(psw);
        Obiettivo obiettivo = (Obiettivo) iofObiettivoPartita.get(line_obiettivo);
        ArrayList<TerritorioPartita> territoriGiocatore = listaTerritoriGiocatorePartita(psw);
        switch (obiettivo.getTipoObiettivo()) {
            case ARMATE:
                TipoColore colore = null;
                for (int i = 0; i < TipoColore.values().length; i++) {
                    if (obiettivo.getObiettivo().contains(TipoColore.values()[i].name())) {
                        colore = TipoColore.values()[i];
                        break;
                    }
                }
                if ((colore.equals(iofGiocatorePartita.get(getLineGiocatore(psw)).getColore()) && listaTerritoriColore(colore).isEmpty()) || listaTerritoriGiocatorePartita(psw).size() > 23) {
                    statoObiettivo = true;
                }
                break;
            case CONTINENTI:
                territorioNonPosseduto = false;
                for (int i = 0, k = 0; k != 1 && i < TipoContinente.values().length && !territorioNonPosseduto; i++) {
                    if (obiettivo.getObiettivo().contains(TipoContinente.values()[i].name())) {
                        ArrayList<TerritorioDettagliato> tContinente = listaTerritoriDettagliatiContinente(TipoContinente.values()[i]);
                        k++;
                        for (int j = 0; !territorioNonPosseduto && j < territoriGiocatore.size(); j++) {
                            if (!tContinente.contains((Territorio) territoriGiocatore.get(j))) {
                                territorioNonPosseduto = true;
                            }
                        }
                    }
                }
                if (!territorioNonPosseduto) {
                    statoObiettivo = true;
                }
                break;
            case NUMERO_TERRITORI:
                int nTerritoriValidi = 0;
                if (obiettivo.getObiettivo().contains("18") && listaTerritoriGiocatorePartita(psw).size() > 17) {
                    for (int i = 0; i < listaTerritoriGiocatorePartita(psw).size(); i++) {
                        if (listaTerritoriGiocatorePartita(psw).get(i).getNumeroArmate() > 1) {
                            nTerritoriValidi++;
                        }
                    }
                    if (nTerritoriValidi > 17) {
                        statoObiettivo = true;
                    }
                } else if (obiettivo.getObiettivo().contains("24") && listaTerritoriGiocatorePartita(psw).size() > 23) {
                    statoObiettivo = true;
                }
                break;
            case TORNEO:
                territorioNonPosseduto = false;
                ArrayList<String> territoriDaConquistare = Territorio.splitTerritori(obiettivo.getObiettivo());
                for (int i = 0; i < listaTerritoriGiocatorePartita(psw).size(); i++) {
                    for (int j = 0; j < territoriDaConquistare.size(); j++) {
                        if (!listaTerritoriGiocatorePartita(psw).contains(territoriDaConquistare.get(j))) {
                            territorioNonPosseduto = true;
                        }
                    }
                }
                if (!territorioNonPosseduto) {
                    statoObiettivo = true;
                }
                break;
        }
        return statoObiettivo;
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
        ArrayList<TerritorioPartita> carteGiocatore = new ArrayList<>();
        for (int i = 0; i < cartePartita.size(); i++) {
            if (cartePartita.get(i).getPasswordGiocatore().equals(password)) {
                carteGiocatore.add(carteGiocatore.get(i));
            }
        }
        Collections.sort(carteGiocatore);
        return cartePartita;
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////// FASE RINFORZO //////////////////////////////////
    /**
     * FASE RINFORZO: metodo che aggiunge un numero specifico di armate a un
     * territorio specifico e riduce il numero di rinforzi disponibili per il
     * giocatore.
     *
     * @param psw del giocatore da ricercare
     * @param nomeTerritorio del territorio su cui aggiungere le armate
     * @param armate numero di armate da aggiungere al territorio e da rimuovere
     * al numero di rinforzi disponibili
     * @return il numero rimanente di armate che possono essere posizionate dal
     * giocatore
     */
    public int faseRinforzo(String psw, String nomeTerritorio, int armate) throws IOException {
        getTerritorioPartita(nomeTerritorio).setNumeroArmate(armate + getTerritorioPartita(nomeTerritorio).getNumeroArmate());
        getGiocatore(psw).setRinforzi(getGiocatore(psw).getRinforzi() - armate);
        return getGiocatore(psw).getRinforzi();
    }

    /**
     * CALCOLA RINFORZI: metodo che calcola il numero di rinforzi totale che un
     * giocatore riceverà nella fase di rinforzo. Il calcolo dipende dal numero
     * di territori occupati, territori occupati, i continenti conquistati e le
     * combinazioni di carte.
     *
     * @param psw del giocatore da ricercare
     * @throws IOException
     */
    public void calcolaRinforzi(String psw) throws IOException {
        // Calcola il numero di armate di rinforzo
        ArrayList<TerritorioPartita> territoriOccupati = listaTerritoriGiocatorePartita(psw);
        int rinforziTerritori = territoriOccupati.size() / 3;
        int rinforziContinenti = 0;
        for (int i = 0; i < 6; i++) {
            ArrayList<TerritorioDettagliato> tContinente = listaTerritoriDettagliatiContinente(TipoContinente.values()[i]);
            boolean continenteConquistato = true;
            for (int j = 0; j < tContinente.size(); j++) {
                if (!territoriOccupati.contains(tContinente.get(j))) {
                    continenteConquistato = false;
                    break;  // Se almeno un territorio non è occupato, esce dal ciclo
                }
            }
            if (continenteConquistato) {
                switch (TipoContinente.values()[i]) {
                    case OCEANIA,SUD_AMERICA:
                        rinforziContinenti += TipoContinente.OCEANIA.getNumeroArmateAssegnate();
                        break;
                    case AFRICA:
                        rinforziContinenti += TipoContinente.AFRICA.getNumeroArmateAssegnate();
                        break;
                    case EUROPA, NORD_AMERICA:
                        rinforziContinenti += TipoContinente.EUROPA.getNumeroArmateAssegnate();
                        break;
                    case ASIA:
                        rinforziContinenti += TipoContinente.ASIA.getNumeroArmateAssegnate();
                        break;
                }
            }
        }
        int rinforziCarte = calcolaRinforziCarte(psw);
        getGiocatore(psw).setnTruppe(rinforziTerritori + rinforziContinenti + rinforziCarte);
    }

    /**
     * CALCOLA RINFORZI CARTE: metodo che calcola i rinforzi basati sulle
     * combinazioni di carte possedute dal giocatore. (combinazioni di
     * artiglieria, fanteria, cavalleria e carte territorio; Le carte utilizzate
     * per ottenere rinforzi vengono poi rimosse)
     *
     * @param psw del giocatore da ricercare
     * @return il numero di rinforzi ottenuti
     * @throws IOException
     */
    private int calcolaRinforziCarte(String psw) throws IOException {
        // Calcola i rinforzi extra per le combinazioni di carte
        int rinforziCarte = 0;
        ArrayList<CarteArmiPartita> carteGiocatore = listaCarteGiocatorePartita(psw);
        // Calcola le combinazioni valide
        int numeroArtiglieria = contaCartePerTipo(TipoArma.ARTIGLIERIA, psw);
        int numeroFanteria = contaCartePerTipo(TipoArma.FANTERIA, psw);
        int numeroCavalleria = contaCartePerTipo(TipoArma.CAVALLERIA, psw);
        // Aggiungi 2 armate per ciascuna carta territorio posseduta nella mano
        rinforziCarte += contaCartaUgaleTerritorioPosseduto(psw) * 2;
        // Calcola i rinforzi per le combinazioni
        if (numeroArtiglieria >= 3) {
            rinforziCarte += 4;
            rimuoviCarteRinforziUsate(psw, TipoArma.ARTIGLIERIA, 3);
        }
        if (numeroFanteria >= 3) {
            rinforziCarte += 6;
            rimuoviCarteRinforziUsate(psw, TipoArma.FANTERIA, 3);
        }
        if (numeroCavalleria >= 3) {
            rinforziCarte += 8;
            rimuoviCarteRinforziUsate(psw, TipoArma.CAVALLERIA, 3);
        }
        if (numeroArtiglieria >= 1 && numeroFanteria >= 1 && numeroCavalleria >= 1) {
            rinforziCarte += 10;
            rimuoviCarteRinforziUsate(psw, TipoArma.ARTIGLIERIA, 1);

            rimuoviCarteRinforziUsate(psw, TipoArma.FANTERIA, 1);
            rimuoviCarteRinforziUsate(psw, TipoArma.CAVALLERIA, 1);
        }
        return rinforziCarte;
    }

    /**
     * RIMUOVI CARTE RINFORZI USATE: Questo metodo rimuove un numero specificato
     * di carte di un certo tipo utilizzate per ottenere dei rinforzi
     *
     * @param psw del giocatore da ricercare
     * @param a tipo di arma da eliminare
     * @param n numero di carte da rimuovere
     * @throws IOException
     */
    private void rimuoviCarteRinforziUsate(String psw, TipoArma a, int n) throws IOException {
        for (int i = 0; i < n; i++) {
            removeCartaArmiPartita(psw, a);
        }
    }

    /**
     * CONTA CARTE UGUALE TERRITORIO POSSEDUTO: metodo che conta il numero di
     * carte territorio che un giocatore ha, e che corrispondono a territori da
     * lui posseduti.
     *
     * @param psw del giocatore da ricercare
     * @return numero di carte che soddisfa la condizione spiegate
     * @throws IOException
     */
    private int contaCartaUgaleTerritorioPosseduto(String psw) throws IOException {
        ArrayList<CarteArmiPartita> carteGiocatore = listaCarteGiocatorePartita(psw);
        ArrayList<TerritorioPartita> tgiocatore = listaTerritoriGiocatorePartita(psw);
        int nCarte = 0;
        for (int i = 0; i < carteGiocatore.size(); i++) {
            if (tgiocatore.contains(carteGiocatore.get(i))) {
                nCarte++;
            }
        }
        return nCarte;
    }

    /**
     * CONTA CARTE PER TIPO: metodo che conta il numero di carte per tipoCarta
     * possedute da un giocatore
     *
     * @param t elenco tipoCarta da ricercare
     * @param psw del giocatore da ricercare
     * @return numero di carte del tipo richiesto da un certo giocatore
     * @throws IOException
     */
    private int contaCartePerTipo(TipoArma t, String psw) throws IOException {
        ArrayList<CarteArmiPartita> carteGiocatore = listaCarteGiocatorePartita(psw);
        int nTipoCarte = 0;
        for (int i = 0; i < carteGiocatore.size(); i++) {
            if (carteGiocatore.get(i).getArma().equals(t)) {
                nTipoCarte++;
            }
        }
        return nTipoCarte;
    }

    ////////////////////////////////////FASE ATTACCO////////////////////////////
    /**
     * CONTROLLO FASE DI ATTACCO: metodo per controllare la fase di attacco da
     * un territorio di un giocatore ad un'altro
     *
     * @param territorioAttaccante territorio di chi attacca
     * @param territorioDifensore territorio che deve difendersi
     * @param armateAttaccante numero di armate dell'attaccante
     * @throws IOException
     * @throws AttaccoFallito
     */
    public void controlloFaseAttacco(String territorioAttaccante, String territorioDifensore, int armateAttaccante) throws IOException, AttaccoFallito {
        ArrayList<String> confini = Territorio.splitTerritori(getTerritorioDettagliato(territorioAttaccante).getSequenzaConfini());
        if (!confini.contains(territorioDifensore) || getTerritorioPartita(territorioAttaccante).getNumeroArmate() == 1
                || listaTerritoriGiocatorePartita(getTerritorioPartita(territorioAttaccante).getPasswordGiocatore()).contains(territorioDifensore)
                || armateAttaccante > getTerritorioPartita(territorioAttaccante).getNumeroArmate()) {
            throw new AttaccoFallito(territorioAttaccante, getTerritorioDettagliato(territorioAttaccante).getSequenzaConfini());
        }
    }

    public void faseAttacco(String territorioAttaccante, String territorioDifensore, int armateAttaccante, int armateDifensore) throws IOException, AttaccoFallito {
        // Attaccante lancia i dadi
        controlloFaseAttacco(territorioAttaccante, territorioDifensore, armateAttaccante);
        if (armateAttaccante > 3 || armateDifensore > 3) {
            armateAttaccante = 3;
            armateDifensore = 3;
        }
        int[] attaccanteDadi = lancioDadi(armateAttaccante);
        ordinamentoDadi(attaccanteDadi);
        stampaDadi("Attaccante", attaccanteDadi);
        // Difensore dichiara il numero di dadi
        int[] difensoreDadi = lancioDadi(armateDifensore);
        ordinamentoDadi(difensoreDadi);
        stampaDadi("Difensore", difensoreDadi);
        // Confronto dei punteggi dei dadi
        int confronti = Math.min(attaccanteDadi.length, difensoreDadi.length);//math min per gestire il confronto tra i dadi
        for (int i = 0; i < confronti; i++) {
            if (attaccanteDadi[i] > difensoreDadi[i]) {
                getTerritorioPartita(territorioDifensore).setNumeroArmate(getTerritorioPartita(territorioDifensore).getNumeroArmate() - 1);
                controlloTruppeTerritorio(territorioDifensore, territorioAttaccante);
            } else if (attaccanteDadi[i] <= difensoreDadi[i]) {
                getTerritorioPartita(territorioAttaccante).setNumeroArmate(getTerritorioPartita(territorioDifensore).getNumeroArmate() - 1);
                controlloTruppeTerritorio(territorioAttaccante, territorioDifensore);
            }
        }
        //bisogna salvare le modifiche sul fileeeee!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    }

    private void controlloTruppeTerritorio(String territorioConquistato, String territorioAttaccante) throws IOException {
        int nArmatePerdenti = getTerritorioPartita(territorioConquistato).getNumeroArmate();
        if (nArmatePerdenti == 0) {
            String pswRemove = getTerritorioPartita(territorioConquistato).getPasswordGiocatore();
            getTerritorioPartita(territorioConquistato).setPasswordGiocatore(getTerritorioPartita(territorioAttaccante).getPasswordGiocatore());
            Giocatore g = getGiocatore(getTerritorioPartita(territorioAttaccante).getPasswordGiocatore());
            g.setNTerritoriConquistatiPerTurno(g.getNTerritoriConquistatiPerTurno() + 1);
            getTerritorioPartita(territorioAttaccante).setNumeroArmate(getTerritorioPartita(territorioAttaccante).getNumeroArmate() - 1);
            getTerritorioPartita(territorioConquistato).setNumeroArmate(1);
            if (listaCarteGiocatorePartita(pswRemove).isEmpty()) {
                removeGiocatore(pswRemove);
            }
            //per semplificare il gioco viene assegnato al territorio conquistato una armata da quello vincitore
            if (controlloStatoObiettivoGiocatore(getTerritorioPartita(territorioAttaccante).getPasswordGiocatore())) {
                //eccezione che ferma il gioco perchè il giocatore ha raggiunto il suo obiettivo
            }
        }
    }

    private static void ordinamentoDadi(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j - 1] < a[j]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }

    }

    private int[] lancioDadi(int numDadi) {
        int[] risultati = new int[numDadi];
        for (int i = 0; i < numDadi; i++) {
            risultati[i] = (int) (Math.random() * (6)) + 1; // Dado a 6 facce
        }
        return risultati;
    }

    public static void stampaDadi(String giocatore, int[] dadi) {
        System.out.print(giocatore + " ha lanciato: ");
        for (int i = 0; i < dadi.length; i++) {
            System.out.print(dadi[i]+"  ");
        }
        System.out.println();
    }

    //////////////////////////////FASE SPOSTAMENTI//////////////////////////////
    public void faseSpostamento(TerritorioPartita territorioPartenza, TerritorioPartita territorioDestinazione, int numeroArmateDaSpostare) throws IOException {
        if (numeroArmateDaSpostare < territorioPartenza.getNumeroArmate()) {
            territorioPartenza.setNumeroArmate(territorioPartenza.getNumeroArmate() - numeroArmateDaSpostare);
            territorioDestinazione.setNumeroArmate(territorioDestinazione.getNumeroArmate() + numeroArmateDaSpostare);
        } else {
            // Gestisci il caso in cui il numero di armate da spostare non è valido
        }
    }
    //------------------------------------------------------------------------//
    //-----------------------------FUNZIONI UTILS-----------------------------//

}
