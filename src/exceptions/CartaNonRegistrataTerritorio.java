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
public class CartaNonRegistrataTerritorio extends RisikoExceptions {

    private String nomeTerritorio;
   
    public CartaNonRegistrataTerritorio(String nome) {
        this.nomeTerritorio = nome;
    }

   
    public String getNomeTerritorio() {
        return nomeTerritorio;
    }

    @Override
    public String toString() {
        return "Carta Non Registrata - la carta del territorio: \""
                + nomeTerritorio + "\" non è presente nel registro delle carte";

    }
    
}
