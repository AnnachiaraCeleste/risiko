/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import enums.TipoArma;

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

    @Override
    public String toString() {
        return this.getNome() + " {truppe: " + numeroArmate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.numeroArmate;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TerritorioPartita other = (TerritorioPartita) obj;
        if (this.numeroArmate != other.numeroArmate) {
            return false;
        }
        return true;
    }

}
