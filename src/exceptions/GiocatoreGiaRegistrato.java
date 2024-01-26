/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * eccezione da lanciare nel caso in cui il giocatore da inserire è già stato
 * registrato
 *
 * @author Annachiara
 */
public class GiocatoreGiaRegistrato extends RisikoExceptions {

    private String psw;

    public GiocatoreGiaRegistrato(String psw) {
        this.psw = psw;
    }

    public String getPSW() {
        return psw;
    }

    @Override
    public String toString() {
        return "Giocatore Già Registrato - il giocatore con PASSWORD: \""
                + psw + "\" è già presente nel registro dei giocatori";

    }
}
