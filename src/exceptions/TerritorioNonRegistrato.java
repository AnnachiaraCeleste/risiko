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
public class TerritorioNonRegistrato extends RisikoExceptions{
    
    private String nome;

    public TerritorioNonRegistrato(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Territorio non Registrato - il territorio: \""
                + nome + "\" non è presente nel registro dei territori";

    }
}
