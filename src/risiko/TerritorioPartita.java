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
public class TerritorioPartita extends CarteArmiPartita {

    private int numeroArmate;

    public TerritorioPartita(String nome, TipoArma arma,String password, int numeroArmate ) {
        super(nome,arma,password);
        this.numeroArmate = numeroArmate;
    }

    public int getNumeroArmate() {
        return numeroArmate;
    }

    public void setNumeroArmate(int numeroArmate) {
        this.numeroArmate = numeroArmate;
    }

}
