/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import enums.TipoArma;

/**
 * eccezione da lanciare nel caso in cui si stia cercando di aggiungere una
 * carta già registrata
 *
 * @author Annachiara
 */
public class CartaGiaRegistrata extends RisikoExceptions {

    private String nome;
    private TipoArma arma;

    public CartaGiaRegistrata(String nome, TipoArma arma) {
        this.nome = nome;
        this.arma = arma;
    }

    public String getNome() {
        return nome;
    }

    public TipoArma getArma() {
        return arma;
    }

    @Override
    public String toString() {
        return "Carta Già Registrata - la carta con territorio: \""
                + nome + "\" e arma: \"" + arma + "\" è già presente nel registro delle carte";

    }
}
