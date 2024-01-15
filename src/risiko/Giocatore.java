/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

/**
 *
 * @author Annachiara
 */
abstract class Giocatore {

    private String nome;
    private TipoColoreArmata coloreArmata;
    private int n_armate;
    private int n_territori;

    public Giocatore(String nome, TipoColoreArmata colore) {
        this.nome = nome;
        this.coloreArmata = colore;
    }
}
