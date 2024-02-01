/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Annachiara
 */
public class TerritorioNonPosseduto extends RisikoExceptions{
    
    private String nomeTerritorio;

    public TerritorioNonPosseduto(String nome) {
        this.nomeTerritorio = nome;
    }

    public String getNomeTerritorio() {
        return nomeTerritorio;
    }

   

    @Override
    public String toString() {
        return "Territorio Non Posseduto - il territorio su cui si vuole effettuare l'azione ("
                + nomeTerritorio + ") non è presente nella lista dei territori del giocatore";

    }
}
