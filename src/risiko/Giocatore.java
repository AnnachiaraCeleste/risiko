/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.util.ArrayList;

/**
 *
 * @author Annachiara
 */
abstract class Giocatore {

    private String nome;
    private Colors colore;
    private int nTruppe;
    private ArrayList<Territorio> territoriPosseduti;
    private int rinforzi;
    //private Obiettivo obiettivo;
    //private ArrayList<Carta> listaCarte;

    public Giocatore(String nome, Colors colore) {
        this.nome = nome;
        this.colore = colore;
    }
}
