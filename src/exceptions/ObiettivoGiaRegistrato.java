/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * eccezione da lanciare nel caso in cui si stia cercando di assegnare un
 * obiettivo ad un giocatore che lo possiede già
 *
 * @author Annachiara
 */
public class ObiettivoGiaRegistrato extends RisikoExceptions {

    private String psw;

    public ObiettivoGiaRegistrato(String psw) {
        this.psw = psw;
    }

    public String getPSW() {
        return psw;
    }

    @Override
    public String toString() {
        return "Obiettivo Già Registrato - il giocatore con PASSWORD: \""
                + psw + "\" ha già un obiettivo assegnato";

    }
}
