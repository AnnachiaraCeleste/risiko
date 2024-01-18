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
public class TerritorioPartita extends Territorio {

    private Giocatore proprietario;
    private int numeroArmate;

    public TerritorioPartita(String nome, TipoArma arma,Giocatore proprietario, int numeroArmate) {
        super(nome,arma);
        this.proprietario = proprietario;
        this.numeroArmate = numeroArmate;
    }

    public Giocatore getProprietario() {
        return proprietario;
    }

    public void setProprietario(Giocatore proprietario) {
        this.proprietario = proprietario;
    }

    public int getNumeroArmate() {
        return numeroArmate;
    }

    public void setNumeroArmate(int numeroArmate) {
        this.numeroArmate = numeroArmate;
    }

}
