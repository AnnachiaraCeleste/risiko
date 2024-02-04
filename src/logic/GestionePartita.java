package logic;

import model.CarteArmiPartita;
import model.TerritorioDettagliato;
import model.TerritorioPartita;
import model.ObiettivoPartita;
import model.Obiettivo;
import model.Giocatore;
import model.Territorio;
import exceptions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import data.IOObjectFileCarteArmiPartita;
import data.IOObjectFileGiocatorePartita;
import data.IOObjectFileObiettivo;
import data.IOObjectFileObiettivoPartita;
import data.IOObjectFileTerritorioDettagliato;
import data.IOObjectFileTerritorioPartita;
import enums.TipoArma;
import enums.TipoColore;
import enums.TipoContinente;
import enums.TipoPartita;


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

    private static final int NUMERO_MIN_GIOCATORI = 2;
    private static final int NUMERO_MAX_GIOCATORI = 6;
    private int n_giocatori;
    private TipoPartita tipoPartita;
    private IOObjectFileGiocatorePartita iofGiocatorePartita;
    private IOObjectFileObiettivoPartita iofObiettivoPartita;
    private IOObjectFileCarteArmiPartita iofCarteArmiPartita;
    private IOObjectFileTerritorioPartita iofTerritorioPartita;
    private IOObjectFileTerritorioDettagliato iofTerritorioDettagliato;
    private IOObjectFileObiettivo iofObiettivo;
    private static final String SEPARATOR = ";";

    public int getNUMERO_MIN_GIOCATORI() {
        return NUMERO_MIN_GIOCATORI;
    }

    public int getNUMERO_MAX_GIOCATORI() {
        return NUMERO_MAX_GIOCATORI;
    }

    public int getN_giocatori() {
        return n_giocatori;
    }

    public void setN_giocatori(int n_giocatori) {
        this.n_giocatori = n_giocatori;
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
        this.n_giocatori = n_giocatori;
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
                + "numero giocatori: " + n_giocatori + "\ntipo partita: " + tipoPartita + "\n"
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
    public void addGiocatore(Giocatore g) throws IOException, GiocatoreGiaRegistrato {
        int line_giocatore = getLineGiocatore(g.getPassword());
        int line_colore = getLineGiocatore(g.getColore());
        if (line_giocatore != -1 || line_colore != -1) {
            throw new GiocatoreGiaRegistrato(g.getPassword());
        }
        g.setRinforzi(numeroArmateIniziali());
        iofGiocatorePartita.add(g);

    }

    public void removeGiocatore(String psw) throws IOException, GiocatoreNonRegistrato, ObiettivoNonRegistrato {
        // ricerca del giocatore
        int line_giocatore = getLineGiocatore(psw);
        if (line_giocatore == -1) {
            throw new GiocatoreNonRegistrato(psw);
        }
        int line_obiettivo = getLineObiettivoPartita(psw);
        if (line_obiettivo == -1) {
            throw new ObiettivoNonRegistrato(psw);
        }

        // ora rimuovo il giocatore
        iofObiettivoPartita.remove(line_obiettivo);
        iofGiocatorePartita.remove(line_giocatore);
    }

    public Giocatore getGiocatore(String psw) throws IOException, GiocatoreNonRegistrato {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_giocatore = getLineGiocatore(psw);
        if (line_giocatore == -1) {
            throw new GiocatoreNonRegistrato(psw);
        }
        return iofGiocatorePartita.get(line_giocatore);
    }

    public void addCartaArmiPartita(CarteArmiPartita c) throws IOException, CartaGiaRegistrata {
        int line_carta = getLineCarteArmiPartita(c.getNome());
        if (line_carta != -1) {
            throw new CartaGiaRegistrata(c.getNome(), c.getArma());
        }
        iofCarteArmiPartita.add(c);
    }

    public void removeCartaArmiPartita(String psw, TipoArma a) throws IOException, CartaNonRegistrataPSWNome {
        int line_carta = getLineCarteArmiPartita(psw, a);
        if (line_carta == -1) {
            throw new CartaNonRegistrataPSWNome(psw, a);
        }
        iofCarteArmiPartita.remove(line_carta);
    }

    public void addTerritorioPartita(TerritorioPartita t) throws IOException, TerritorioGiaRegistrato {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_territorio = getLineTerritorioPartita(t.getNome());
        if (line_territorio != -1) {
            throw new TerritorioGiaRegistrato(t.getNome());
        }
        iofTerritorioPartita.add(t);
    }

    public TerritorioPartita getTerritorioPartita(String nome) throws IOException, TerritorioNonRegistrato {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_territorio = getLineTerritorioPartita(nome);
        if (line_territorio == -1) {
            throw new TerritorioNonRegistrato(nome);
        }
        return iofTerritorioPartita.get(line_territorio);
    }

    public void addObiettivoPartita(ObiettivoPartita o) throws IOException, ObiettivoGiaRegistrato {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_obiettivo = getLineObiettivoPartita(o.getPassword());
        if (line_obiettivo != -1) {
            throw new ObiettivoGiaRegistrato(o.getPassword());
        }
        iofObiettivoPartita.add(o);
    }

    /**
     *
     * @param psw
     * @return
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

    public void removeObiettivoPartita(String psw) throws IOException, ObiettivoNonRegistrato {
        // ricerca del giocatore
        int line_obiettivo = getLineObiettivoPartita(psw);
        if (line_obiettivo == -1) {
            throw new ObiettivoNonRegistrato(psw);
        }
        // ora rimuovo il giocatore
        iofObiettivoPartita.remove(line_obiettivo);
    }

    public TerritorioDettagliato getTerritorioDettagliato(String nome) throws IOException, TerritorioNonRegistrato {
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_territorio = getLineTerritorioDettagliato(nome);
        if (line_territorio == -1) {
            throw new TerritorioNonRegistrato(nome);
        }
        return iofTerritorioDettagliato.get(line_territorio);
    }

    ///////////////////////FASI SETTAGGIO PARTITA///////////////////////////////
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
            for (int k = 0; j < territoriPartita.size() && k < ((int) 42 / n_giocatori); k++, j++) {
                TerritorioPartita territorio = new TerritorioPartita(territoriPartita.get(idxTerritori[j]).getNome(),
                        territoriPartita.get(idxTerritori[j]).getArma(), giocatori.get(i).getPassword(), 1);
                addTerritorioPartita(territorio);
                giocatori.get(i).setRinforzi(giocatori.get(i).getRinforzi() - 1);
            }
            if ((n_giocatori == 4 || n_giocatori == 5) && (i == 0 || i == 1 && j < territoriPartita.size())) {
                TerritorioPartita territorio = new TerritorioPartita(territoriPartita.get(idxTerritori[j]).getNome(),
                        territoriPartita.get(idxTerritori[j]).getArma(), giocatori.get(i).getPassword(), 1);
                addTerritorioPartita(territorio);
                giocatori.get(i).setRinforzi(giocatori.get(i).getRinforzi() - 1);
                j++;
            }
        }

        ArrayList<TerritorioPartita> territoriAssegnati = iofTerritorioPartita.loadData();
        Collections.sort(territoriAssegnati);
        iofGiocatorePartita.saveData(giocatori);
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
    public void addObiettivi() throws IOException, ObiettivoGiaRegistrato {
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
                    listaObiettivi.get(idxObiettivo[i]).getTipoObiettivo(), giocatori.get(i).getPassword());
            addObiettivoPartita(obiettivo);
        }
    }

//////////////////////////////FUNZIONI PARTITA//////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////// FASE RINFORZO //////////////////////////////////
    /**
     * FASE RINFORZO: metodo che aggiunge un numero specifico di rinforzi a un
     * territorio specifico e riduce il numero di rinforzi disponibili per il
     * giocatore.
     *
     * @param psw del giocatore da ricercare
     * @param nomeTerritorio del territorio su cui aggiungere le rinforzi
     * @param rinforzi numero di rinforzi da aggiungere al territorio e da
     * rimuovere al numero di rinforzi disponibili
     * @return il numero rimanente di rinforzi che possono essere posizionate
     * dal giocatore
     * @throws IOException
     * @throws GiocatoreNonRegistrato
     * @throws TerritorioNonRegistrato
     * @throws TerritorioNonPosseduto
     * @throws NMaxTruppeRinforzoRaggiunto
     */
    public int faseRinforzo(String psw, String nomeTerritorio, int rinforzi) throws IOException,
            GiocatoreNonRegistrato, TerritorioNonRegistrato, TerritorioNonPosseduto, NMaxTruppeRinforzoRaggiunto {
        ArrayList<TerritorioPartita> territori = iofTerritorioPartita.loadData();
        ArrayList<Giocatore> giocatori = iofGiocatorePartita.loadData();
        boolean territorioPosseduto = false;
        for (int i = 0; i < listaTerritoriGiocatorePartita(psw).size(); i++) {
            if (!listaTerritoriGiocatorePartita(psw).get(i).equals(getTerritorioPartita(nomeTerritorio))) {
                territorioPosseduto = true;
                break;
            }
        }
        if (!territorioPosseduto) {
            throw new TerritorioNonPosseduto(nomeTerritorio);
        }
        if (giocatori.get(getLineGiocatore(psw)).getRinforzi() < rinforzi) {
            throw new NMaxTruppeRinforzoRaggiunto(rinforzi);
        }
        territori.get(getLineTerritorioPartita(nomeTerritorio)).setNumeroArmate(rinforzi + getTerritorioPartita(nomeTerritorio).getNumeroArmate());
        giocatori.get(getLineGiocatore(psw)).setRinforzi(getGiocatore(psw).getRinforzi() - rinforzi);
        iofTerritorioPartita.saveData(territori);
        iofGiocatorePartita.saveData(giocatori);
        return getGiocatore(psw).getRinforzi();
    }

    /**
     * CALCOLA RINFORZI: metodo che calcola il numero di rinforzi totale che un
     * giocatore riceverà nella fase di rinforzo.Il calcolo dipende dal numero
     * di territori occupati, territori occupati, i continenti conquistati e le
     * combinazioni di carte.
     *
     * @param psw del giocatore da ricercare
     * @throws IOException
     * @throws CartaNonRegistrataPSWNome
     */
    public void calcolaRinforzi(String psw) throws IOException, CartaNonRegistrataPSWNome {
        // Calcola il numero di armate di rinforzo
        ArrayList<TerritorioPartita> territoriOccupati = listaTerritoriGiocatorePartita(psw);
        int rinforziTerritori = territoriOccupati.size() / 3;
        int rinforziContinenti = 0;
        for (int i = 0; i < 6; i++) {
            ArrayList<TerritorioDettagliato> tContinente = listaTerritoriDettagliatiContinente(TipoContinente.values()[i]);
            boolean continenteConquistato = true;
            for (int j = 0; j < tContinente.size(); j++) {
                boolean territorioOccupato = false;
                for (int k = 0; k < territoriOccupati.size(); k++) {
                    if (territoriOccupati.get(k).equals(tContinente.get(j))) {
                        territorioOccupato = true;
                        break;
                    }
                }
                if (!territorioOccupato) {
                    continenteConquistato = false;
                    break;
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
        ArrayList<Giocatore> giocatori = iofGiocatorePartita.loadData();
        giocatori.get(getLineGiocatore(psw)).setRinforzi(rinforziTerritori + rinforziContinenti + rinforziCarte);
        iofGiocatorePartita.saveData(giocatori);
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
    private int calcolaRinforziCarte(String psw) throws IOException, CartaNonRegistrataPSWNome {
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
    private void rimuoviCarteRinforziUsate(String psw, TipoArma a, int n) throws IOException, CartaNonRegistrataPSWNome {
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
        boolean cartaUgualeTerritorio = false;
        for (int i = 0; !cartaUgualeTerritorio && i < carteGiocatore.size(); i++) {
            for (int j = 0; !cartaUgualeTerritorio && j < tgiocatore.size(); j++) {
                if (carteGiocatore.get(i).equals(tgiocatore.get(j))) {
                    nCarte++;
                    cartaUgualeTerritorio = true;
                }
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
     * @param psw identificativa del giocatore che effettua l'attacco
     * @param territorioAttaccante territorio di chi attacca
     * @param territorioDifensore territorio che deve difendersi
     * @throws IOException
     * @throws AttaccoFallito
     * @throws TerritorioNonRegistrato
     * @throws TerritorioNonPosseduto
     * @throws TerritorioNonConfinante
     * @throws NMinTruppeAttaccoRaggiunto
     */
    public void controlloFaseAttacco(String psw, String territorioAttaccante, String territorioDifensore) throws IOException, AttaccoFallito, TerritorioNonRegistrato, TerritorioNonPosseduto, TerritorioNonConfinante, NMinTruppeAttaccoRaggiunto {
        if (getLineTerritorioDettagliato(territorioAttaccante) == -1 || getLineTerritorioDettagliato(territorioDifensore) == -1) {
            throw new TerritorioNonRegistrato(territorioAttaccante + " " + territorioDifensore);
        }
        boolean territorioPosseduto = false;
        for (int i = 0; i < listaTerritoriGiocatorePartita(psw).size(); i++) {
            if (!listaTerritoriGiocatorePartita(psw).get(i).equals(getTerritorioPartita(territorioAttaccante))) {
                territorioPosseduto = true;
                break;
            }
        }
        if (!territorioPosseduto) {
            throw new TerritorioNonPosseduto(territorioAttaccante);
        }
        if (!Territorio.splitTerritori(getTerritorioDettagliato(territorioAttaccante).getSequenzaConfini()).contains(territorioDifensore)) {
            throw new TerritorioNonConfinante();
        } else if (getTerritorioPartita(territorioAttaccante).getNumeroArmate() == 1) {
            throw new NMinTruppeAttaccoRaggiunto();
        }
        for (int i = 0; i < listaTerritoriGiocatorePartita(psw).size(); i++) {
            if (listaTerritoriGiocatorePartita(psw).get(i).equals(getTerritorioPartita(territorioDifensore))) {
                throw new AttaccoFallito(territorioAttaccante, getTerritorioDettagliato(territorioAttaccante).getSequenzaConfini());
            }
        }
    }

    /**
     * FASE ATTACCO: metodo per l'attacco di un territorio: vengono lanciati i
     * dadi per ogni giocatore e vengono confrontati in ordine decrescente
     *
     * @param territorioAttaccante territorio di partenza dal quale si vuole
     * effettuare l'attacco
     * @param territorioDifensore territorio che subisce l'attacco
     * @throws IOException
     * @throws AttaccoFallito
     */
    public void faseAttacco(String psw, String territorioAttaccante, String territorioDifensore) throws IOException, AttaccoFallito, SpostamentoFallito, GiocatoreNonRegistrato, ObiettivoNonRegistrato, TerritorioNonRegistrato, TerritorioNonPosseduto, TerritorioNonConfinante, NMinTruppeAttaccoRaggiunto {
        // Attaccante lancia i dadi

        controlloFaseAttacco(psw, territorioAttaccante, territorioDifensore);
        int armateAttaccante, armateDifensore;
        ArrayList<TerritorioPartita> territori = iofTerritorioPartita.loadData();
        int idx_attacc = getLineTerritorioPartita(territorioAttaccante);
        int idx_diff = getLineTerritorioPartita(territorioDifensore);
        if (territori.get(idx_attacc).getNumeroArmate() > 3) {
            armateAttaccante = 3;
        } else if (territori.get(idx_attacc).getNumeroArmate() == 3) {
            armateAttaccante = 2;
        } else {
            armateAttaccante = 1;
        }
        int[] attaccanteDadi = lancioDadi(armateAttaccante);
        ordinamentoDadi(attaccanteDadi);
        stampaDadi("Attaccante", attaccanteDadi);//stampa dei dadi lanciati
        if (territori.get(idx_diff).getNumeroArmate() > 3) {
            armateDifensore = 3;
        } else {
            armateDifensore = territori.get(idx_diff).getNumeroArmate();
        }
        // Difensore dichiara il numero di dadi
        int[] difensoreDadi = lancioDadi(armateDifensore);
        ordinamentoDadi(difensoreDadi);
        stampaDadi("Difensore", difensoreDadi);//stampa dei dadi lanciati
        // Confronto dei punteggi dei dadi
        int confronti = Math.min(attaccanteDadi.length, difensoreDadi.length);//math min per gestire il confronto tra i dadi
        //controlloTruppeTerritorio: se true si blocca il ciclo senza controllare tutti i dadi
        for (int i = 0; !controlloTruppeTerritorio(territorioAttaccante, territorioDifensore) && i < confronti; i++) {
            if (attaccanteDadi[i] > difensoreDadi[i]) {
                territori.get(idx_diff).setNumeroArmate(territori.get(idx_diff).getNumeroArmate() - 1);
            } else if (attaccanteDadi[i] <= difensoreDadi[i]) {
                territori.get(idx_attacc).setNumeroArmate(territori.get(idx_attacc).getNumeroArmate() - 1);
            }
            iofTerritorioPartita.saveData(territori);
        }

    }

    /**
     * CONTROLLO TRUPPE TERRITORIO: metodo che controlla il numero di truppe
     * presenti su un territorio appena attaccato; se sono pari a 0 allora il
     * giocatore ha conquistato il territorio ed è necessario eseguire un
     * aggiornamento del territorio che viene assegnato al giocatore che ha
     * vinto
     *
     * @param territorioAttaccante territorio di partenza dal quale si vuole
     * effettuare l'attacco
     * @param territorioDifensore territorio che subisce l'attacco
     * @return true se il territorio è stato conquistato
     * @throws IOException
     */
    private boolean controlloTruppeTerritorio(String territorioAttaccante, String territorioDifensore) throws IOException, SpostamentoFallito, GiocatoreNonRegistrato, ObiettivoNonRegistrato, TerritorioNonRegistrato {
        ArrayList<TerritorioPartita> territori = iofTerritorioPartita.loadData();
        ArrayList<Giocatore> giocatori = iofGiocatorePartita.loadData();
        //indice attaccante
        int idx_attacc = getLineTerritorioPartita(territorioAttaccante);
        //indice difensore
        int idx_dif = getLineTerritorioPartita(territorioDifensore);
        boolean territorioConquistato = false;
        //se nArmate>3 sposto 3 carri armati
        //se il numero di armate del difensore =0 devo spostare alcune truppe
        if (territori.get(idx_dif).getNumeroArmate() == 0) {
            territorioConquistato = true;
            String pswRemove = territori.get(idx_dif).getPasswordGiocatore();
            //cambio la password del giocatore
            territori.get(idx_dif).setPasswordGiocatore(territori.get(idx_attacc).getPasswordGiocatore());
            //aumento il numero di territori conquistati dal giocatore in questo turno
            giocatori.get(getLineGiocatore(territori.get(idx_attacc).getPasswordGiocatore())).setNTerritoriConquistatiPerTurno(giocatori.get(getLineGiocatore(
                    territori.get(idx_attacc).getPasswordGiocatore())).getNTerritoriConquistatiPerTurno() + 1);
            //se il giocatore non ha più territori lo rimuvo dalla lista dei giocatori e il suo obiettivo
            iofGiocatorePartita.saveData(giocatori);
            if (listaTerritoriGiocatorePartita(pswRemove).isEmpty()) {
                removeGiocatore(pswRemove);
            }
            int nArmateVincenti;//numero di armate da assagnare al territorio
            if (territori.get(idx_attacc).getNumeroArmate() > 3) {
                nArmateVincenti = 3;
            } else if (territori.get(idx_attacc).getNumeroArmate() == 3) {
                nArmateVincenti = 2;
            } else {
                nArmateVincenti = 1;
            }
            iofTerritorioPartita.saveData(territori);
            faseSpostamento(territorioAttaccante, territorioDifensore, nArmateVincenti);
        }
        return territorioConquistato;
    }

    /**
     * PESCA CARTA: metodo che assegna ad un giocatore una carta arma utile per
     * i rinforzi
     *
     * @param psw del giocatore
     * @throws IOException
     * @throws exceptions.GiocatoreNonRegistrato
     * @throws exceptions.CartaGiaRegistrata
     * @throws exceptions.CartaNonRegistrataTerritorio
     */
    public void pescaCarta(String psw) throws IOException, GiocatoreNonRegistrato, CartaGiaRegistrata, CartaNonRegistrataTerritorio {
        if (getGiocatore(psw).getNTerritoriConquistatiPerTurno() > 0) {
            ArrayList<Giocatore> giocatori = iofGiocatorePartita.loadData();
            ArrayList<TerritorioDettagliato> territoriDet = iofTerritorioDettagliato.loadData();
            boolean trovato;
            int idxCarta;
            do {
                idxCarta = (int) (Math.random() * (42));
                trovato = getLineCarteArmiPartita(territoriDet.get(idxCarta).getNome()) == -1;
            } while (!trovato);
            addCartaArmiPartita(new CarteArmiPartita(territoriDet.get(idxCarta).getNome(), territoriDet.get(idxCarta).getArma(), psw));
            giocatori.get(getLineGiocatore(psw)).setNTerritoriConquistatiPerTurno(0);
            iofGiocatorePartita.saveData(giocatori);
        }
    }

    //////////////////////////////FASE SPOSTAMENTI//////////////////////////////
    /**
     * FASE SPOSTAMENTO: metodo per effettuare lo spostamento delle truppe sui
     * propri territori
     *
     * @param territorioPartenza territorio dal quale si prelevano le truppe da
     * spostare
     * @param territorioDestinazione territorio che riceve le armate
     * @param numeroArmateDaSpostare
     * @throws IOException
     * @throws SpostamentoFallito
     */
    public void faseSpostamento(String territorioPartenza, String territorioDestinazione, int numeroArmateDaSpostare) throws IOException, SpostamentoFallito, TerritorioNonRegistrato {
        if (!getTerritorioPartita(territorioPartenza).getPasswordGiocatore().equals(getTerritorioPartita(territorioDestinazione).getPasswordGiocatore())
                || getTerritorioPartita(territorioPartenza).equals(getTerritorioPartita(territorioDestinazione))
                || numeroArmateDaSpostare >= getTerritorioPartita(territorioPartenza).getNumeroArmate()
                || getTerritorioPartita(territorioPartenza).getNumeroArmate() == 1) {
            throw new SpostamentoFallito(territorioPartenza);
        }
        ArrayList<TerritorioPartita> territoriPartita = iofTerritorioPartita.loadData();
        territoriPartita.get(getLineTerritorioPartita(territorioPartenza)).setNumeroArmate(getTerritorioPartita(territorioPartenza).getNumeroArmate() - numeroArmateDaSpostare);
        territoriPartita.get(getLineTerritorioPartita(territorioDestinazione)).setNumeroArmate(getTerritorioPartita(territorioDestinazione).getNumeroArmate() + numeroArmateDaSpostare);
        iofTerritorioPartita.saveData(territoriPartita);
    }

    /**
     * CONTROLLO STATO OBIETTIVO GIOCATORE: metodo che controlla se il giocatore
     * ha completato il suo obiettivo a seconda del tipo di obiettivo che gli è
     * stato assegnato: una intera armata, due continenti, un numero di
     * territori elevato oppure per la versione torneo 7 territori da
     * conquistare.
     *
     * @param psw del giocatore
     * @throws IOException
     */
    public void controlloStatoObiettivoGiocatore(String psw) throws IOException, FinePartita, GiocatoreNonRegistrato, TerritorioNonRegistrato, ObiettivoNonRegistrato {
        boolean statoObiettivo = false;
        boolean territorioNonPosseduto;
        ObiettivoPartita obiettivo = getObiettivoPartita(psw);
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
                if (getLineGiocatore(colore)==-1 &&((colore.equals(iofGiocatorePartita.get(getLineGiocatore(psw)).getColore()) && listaTerritoriColore(colore).isEmpty())|| listaTerritoriGiocatorePartita(psw).size() > 23)) {
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
                            TerritorioPartita territorioGiocatore = territoriGiocatore.get(j);
                            boolean territorioPresente = false;
                            for (int t = 0; t < tContinente.size(); t++) {
                                if (territorioGiocatore.equals((Territorio) tContinente.get(t))) {
                                    territorioPresente = true;
                                    break;
                                }
                            }
                            if (!territorioPresente) {
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
                if (obiettivo.getObiettivo().contains("18") && territoriGiocatore.size() > 17) {
                    for (int i = 0; i < territoriGiocatore.size(); i++) {
                        if (territoriGiocatore.get(i).getNumeroArmate() > 1) {
                            nTerritoriValidi++;
                        }
                    }
                    if (nTerritoriValidi > 17) {
                        statoObiettivo = true;
                    }
                } else if (obiettivo.getObiettivo().contains("24") && territoriGiocatore.size() > 23) {
                    statoObiettivo = true;
                }
                break;
            case TORNEO:
                statoObiettivo = true;
                ArrayList<String> territoriDaConquistare = Territorio.splitTerritori(obiettivo.getObiettivo());
                for (int i = 0; i < territoriDaConquistare.size(); i++) {
                    boolean territorioTrovato = false;
                    for (int j = 0; j < territoriGiocatore.size(); j++) {
                        if (territoriDaConquistare.get(i).equals(territoriGiocatore.get(j).getNome())) {
                            territorioTrovato = true;
                        }
                    }
                    if (!territorioTrovato) {
                        statoObiettivo = false;
                        break;
                    }
                }
        }
        if (statoObiettivo) {
            throw new FinePartita(getGiocatore(psw).getNome(), obiettivo.getObiettivo());
        }
    }
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
     * ORDINAMENTO DADI: insertion sort dei dadi in ordine decrescente
     *
     * @param a array con i valori del lancio dei dadi
     */
    private void ordinamentoDadi(int[] a) {
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

    /**
     * LANCIO DADI: metodo per generare un numero preciso di interi
     *
     * @param numDadi quantità di numeri da generare
     * @return array di int
     */
    private int[] lancioDadi(int numDadi) {
        int[] risultati = new int[numDadi];
        for (int i = 0; i < numDadi; i++) {
            risultati[i] = (int) (Math.random() * (6)) + 1; // Dado a 6 facce
        }
        return risultati;
    }

    /**
     * STAMPA DADI: metodo per stampare a console i dadi lanciati
     *
     * @param giocatore che effettua il lancio
     * @param dadi array di interi
     */
    public void stampaDadi(String giocatore, int[] dadi) {
        System.out.print(giocatore + " ha lanciato: ");
        for (int i = 0; i < dadi.length; i++) {
            System.out.print(dadi[i] + "  ");
        }
        System.out.println();
    }

    public static void stampaListaTerritori(ArrayList<TerritorioPartita> lista) {
        System.out.println("***************************************");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
        System.out.println("***************************************");
    }

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
