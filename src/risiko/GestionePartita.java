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

    
    private int getLineTerritorioPartita(String nome) throws IOException {
        boolean continente_trovato = false;
        int line_continente = -1;
        ArrayList<TerritorioPartita> territoriPartita = iofTerritorioPartita.loadData();
        // RICERCA PER CHIAVE
        for (int i = 0; !continente_trovato&&i < territoriPartita.size(); i++) {
            if (territoriPartita.get(i).getNome().equals(nome)) {
                continente_trovato = true;
                line_continente = i;
            }
        }
        return line_continente;
    }
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
    public TerritorioPartita getTerritorioPartita(String nome) throws IOException{
        // uso il metodo che ritorna l'indice dell'arraylist
        int line_territorio = getLineTerritorioPartita(nome);
        if (line_territorio == -1) {
            //throw new TerritorioNonRegistrato(psw);
        }
        return iofTerritorioPartita.get(line_territorio);
    }
    ////////////////////////////////////////////////////////////////////////////
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
        for (int i = 0, j = 0; i < giocatori.size(); i++) {
            for (; j < territoriPartita.size() && j < (int) 42 / n_giocatori; j++) {
                TerritorioPartita territorio = new TerritorioPartita(territoriPartita.get(idxTerritori[j]).getNome(),
                        territoriPartita.get(idxTerritori[j]).getArma(), giocatori.get(i).getPassword(), 1);
                iofTerritorioPartita.add(territorio);
                giocatori.get(i).setRinforzi(numeroArmateIniziali() - 1);
            }
            if ((n_giocatori == 4 || n_giocatori == 5) && (i == 0 || i == 1)) {
                TerritorioPartita territorio = new TerritorioPartita(territoriPartita.get(idxTerritori[j]).getNome(),
                        territoriPartita.get(idxTerritori[j]).getArma(), giocatori.get(i).getPassword(), 1);
                iofTerritorioPartita.add(territorio);
                giocatori.get(i).setRinforzi(numeroArmateIniziali() - 1);
                j++;
            }

        }
    }

    public int numeroArmateIniziali() {
        return 20 + 5 * (NUMERO_MAX_GIOCATORI - n_giocatori);
    }

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
            iofObiettivoPartita.add(obiettivo);
        }
    }

//////////////////////////////FUNZIONI PARTITA//////////////////////////////
    public boolean controlloStatoObiettivoGiocatore(String psw) throws IOException {
        boolean statoObiettivo = false;
        boolean territorioNonPosseduto;
        int line_obiettivo = getLineObiettivoGiocatore(psw);
        Obiettivo obiettivo = (Obiettivo) iofObiettivoPartita.get(line_obiettivo);
        ArrayList<TerritorioPartita> territoriGiocatore = listaTerritoriGiocatorePartita(psw);
        switch (obiettivo.getTipoObiettivo()) {
            case ARMATE:
                Colors colore = null;
                for (int i = 0; i < Colors.values().length; i++) {
                    if (obiettivo.getObiettivo().contains(Colors.values()[i].name())) {
                        colore = Colors.values()[i];
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

    public ArrayList<TerritorioPartita> listaTerritoriColore(Colors colore) throws IOException {
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
    ////////////////////////////////////////////////////////////////////////////
    //inizio turno do ad ogni giocatore il numero di rinforzi in base a territorio
    //oppure a seconda delle carte ecc...
    //public int calcolaRinforziTourno(String psw){
        
    //}
    
    /**
     * 
     * @param psw
     * @param nomeTerritorio
     * @param armate
     * @return il numero di armate che possono ancora essere posizionate dal giocatore
     */
    public int faseRinforzo(String psw,String nomeTerritorio, int armate) throws IOException{
        if(getGiocatore(psw).getRinforzi()!=0&&getGiocatore(psw).getRinforzi()<=armate){            
            getTerritorioPartita(nomeTerritorio).setNumeroArmate(armate+getTerritorioPartita(nomeTerritorio).getNumeroArmate());
        }
        return getGiocatore(psw).getRinforzi();
    }
    /*
    public void calcolaRinforzi() {
        // Calcola il numero di armate di rinforzo
        int rinforziTerritori = territoriOccupati / 3;
        int rinforziContinenti = calcolaRinforziContinenti();
        int rinforziCarte = calcolaRinforziCarte();

        this.armate = rinforziTerritori + rinforziContinenti + rinforziCarte;
    }

    private int calcolaRinforziContinenti() {
        // Calcola i rinforzi extra per i continenti occupati
        int rinforziContinenti = 0;
        if (carteTerritoriPosseduti.contains("Australia") || carteTerritoriPosseduti.contains("Oceania")) {
            rinforziContinenti += 2;
        }
        // Aggiungi altri continenti secondo necessità
        return rinforziContinenti;
    }

    private int calcolaRinforziCarte() {
        // Calcola i rinforzi extra per le combinazioni di carte
        int rinforziCarte = 0;

        // Calcola le combinazioni valide
        int numeroCannoni = contaCarte("cannone");
        int numeroFanti = contaCarte("fante");
        int numeroCavalieri = contaCarte("cavaliere");

        // Calcola i rinforzi per le combinazioni
        if (numeroCannoni >= 3) {
            rinforziCarte += 4;
        }
        if (numeroFanti >= 3) {
            rinforziCarte += 6;
        }
        if (numeroCavalieri >= 3) {
            rinforziCarte += 8;
        }
        if (numeroCannoni >= 1 && numeroFanti >= 1 && numeroCavalieri >= 1) {
            rinforziCarte += 10;
        }

        // Aggiungi 2 armate per ciascuna carta territorio posseduta nella mano
        rinforziCarte += carteTerritoriPosseduti.size() * 2;

        return rinforziCarte;
    }

    */
}
