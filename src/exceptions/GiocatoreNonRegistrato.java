/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * eccezione da lanciare nel caso in cui il giocatore da ricercare non sia stato
 * registrato
 *
 * @author Annachiara
 */
public class GiocatoreNonRegistrato extends RisikoExceptions {

    private String psw;

    public GiocatoreNonRegistrato(String psw) {
        this.psw = psw;
    }

    public String getPSW() {
        return psw;
    }

    @Override
    public String toString() {
        return "Giocatore non Registrato - il giocatore con PASSWORD: \""
                + psw + "\" non è presente nel registro dei giocatori";

    }
}
