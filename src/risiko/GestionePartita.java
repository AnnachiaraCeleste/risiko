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
        ArrayList<TerritorioPartita> territoriPartita = iofTerritorioPartita.loadData();
        ArrayList<TerritorioDettagliato> listaTerritoriContinente = listaTerritoriDettagliatiContinente(continente);
        // RICERCA PER CHIAVE
        for (int i = indiceControllo; !continente_trovato && i < territoriPartita.size(); i++) {
            if (territoriPartita.get(i).get.equals(password)) {
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
    public void faseRinforzo(String psw){
        
    }
}
