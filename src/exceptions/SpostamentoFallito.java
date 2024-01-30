/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 ***
 * eccezione da lanciare nel caso in cui il giocatore stia tentando di spostare
 * le proprie truppe su un territorio che non gli appartiene o su un territorio
 * non confinante, oppure se vuole spostare un numero di armate superiori
 * rispetto a quelle preseni sul territorio di partenza
 *
 * @author Annachiara
 */
public class SpostamentoFallito extends RisikoExceptions {

    private String territorio;

    public String getTerritorio() {
        return territorio;
    }

    public SpostamentoFallito(String territorio) {
        this.territorio = territorio;
    }

    @Override
    public String toString() {
        return "Spostamento Fallito - lo spostamento che è partito dal territorio \""
                + territorio + "\" non è andato a buon fine;";

    }
}
