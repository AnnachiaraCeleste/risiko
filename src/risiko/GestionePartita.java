package risiko;

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
}
