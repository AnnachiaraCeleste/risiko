package risiko;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    private TipoPartita tipoPartita;
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

    private int getLineObiettivoGiocatore(String psw) throws IOException {
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

    /*
    private int getLineTerritorioPartita(TipoContinente continente, int indiceControllo) throws IOException {
        boolean continente_trovato = false;
        int line_continente = -1;
        ArrayList<TerritorioPartita> listaTerritoriPartita = iofTerritorioPartita.loadData();
        ArrayList<TerritorioDettagliato> listaTerritoriContinente = listaTerritoriDettagliatiContinente(continente);
        // RICERCA PER CHIAVE
        for (int i = indiceControllo; !continente_trovato && i < listaTerritoriPartita.size(); i++) {
            if (listaTerritoriPartita.get(i).get.equals(password)) {
                continente_trovato = true;
                line_continente = i;
            }
        }
        return line_continente;
    }
     */
    ///////////////////////// CRUD FUNCTIONS GIOCATORE /////////////////////////
    public void addGiocatore(Giocatore g) throws IOException {
        int line_giocatore = getLineGiocatore(g.getPassword());
        if (line_giocatore != -1) {
            // throw new GiocatoreGiaRegistrato(g.getNome());
        }
        // aggiungo l'auto all'arraylist
        iofGiocatorePartita.add(new Giocatore(g));
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

    //////////////////////////////FUNZIONI PARTITA//////////////////////////////
    public boolean controlloStatoObiettivoGiocatore(String psw) throws IOException {
        boolean statoObiettivo = true;
        int line_obiettivo = getLineObiettivoGiocatore(psw);
        Obiettivo obiettivo = (Obiettivo) iofObiettivoPartita.get(line_obiettivo);
        ArrayList<TerritorioPartita> territoriGiocatore = listaTerritoriGiocatorePartita(psw);
        switch (obiettivo.getTipoObiettivo()) {
            case ARMATE:;
            case CONTINENTI:
                for (int i = 0, k = 0; i < TipoContinente.values().length; i++) {
                    if (obiettivo.getObiettivo().contains(TipoContinente.values()[i].name())) {
                        ArrayList<TerritorioDettagliato> tContinente = listaTerritoriDettagliatiContinente(TipoContinente.values()[i]);
                        for (int j = 0; statoObiettivo && j < territoriGiocatore.size(); j++) {
                            if (!tContinente.contains((Territorio) territoriGiocatore.get(j))) {
                                statoObiettivo = false;
                            }
                        }
                    }
                    if (k == 1) {
                        break;
                    }
                }
            case NUMERO_TERRITORI:
            case TORNEO:
        }
        return statoObiettivo;
    }

    public ArrayList<TerritorioDettagliato> listaTerritoriDettagliatiContinente(TipoContinente continente) throws IOException {
        ArrayList<TerritorioDettagliato> territoriDettagliati = iofTerritorioDettagliato.loadData();
        ArrayList<TerritorioDettagliato> territoriContinente = new ArrayList<>();
        for (int i = 0; i < territoriDettagliati.size(); i++) {
            if (territoriDettagliati.get(i).getContinente().equals(continente)) {
                territoriContinente.add(territoriDettagliati.get(i));
            }
        }
        return territoriContinente;
    }

    public ArrayList<TerritorioPartita> listaTerritoriGiocatorePartita(String password) throws IOException {
        ArrayList<TerritorioPartita> territoriPartita = iofTerritorioPartita.loadData();
        ArrayList<TerritorioPartita> territoriGiocatore = new ArrayList<>();
        for (int i = 0; i < territoriPartita.size(); i++) {
            if (territoriPartita.get(i).getPasswordGiocatore().equals(password)) {
                territoriGiocatore.add(territoriPartita.get(i));
            }
        }
        return territoriGiocatore;
    }
}
