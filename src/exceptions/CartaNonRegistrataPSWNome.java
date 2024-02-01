/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import enums.TipoArma;

/**
 * eccezione da lanciare nel caso in cui si stia ricercando una carta
 * appartenente ad un giocatore che non è stata registrata
 *
 * @author Annachiara
 */
public class CartaNonRegistrataPSWNome extends RisikoExceptions {

    private String psw;
    private TipoArma arma;

    public CartaNonRegistrataPSWNome(String psw, TipoArma arma) {
        this.psw = psw;
        this.arma = arma;
    }

    public String getPSW() {
        return psw;
    }

    public TipoArma getArma() {
        return arma;
    }

    @Override
    public String toString() {
        return "Carta Non Registrata - la carta appartenente al giocatore: \""
                + psw + "\" e arma: \"" + arma + "\" non è presente nel registro delle carte";

    }
}
