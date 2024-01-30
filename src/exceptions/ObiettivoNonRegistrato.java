/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * eccezione da lanciare nel caso in cui non sia possibile ricercare l'obiettivo
 *
 * @author Annachiara
 */
public class ObiettivoNonRegistrato extends RisikoExceptions {

    private String psw;

    public ObiettivoNonRegistrato(String psw) {
        this.psw = psw;
    }

    public String getPSW() {
        return psw;
    }

    @Override
    public String toString() {
        return "Obiettivo non Registrato - l'obiettivo con PASSWORD: \""
                + psw + "\" non è presente nel registro degli obiettivi";

    }
}
