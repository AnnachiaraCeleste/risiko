/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * eccezione da lanciare nel caso in cui il giocatore stia tentando di attaccare
 * un territorio non confinante, nel caso in cui stia cercando di attaccare da
 * un territorio che possiede un numero di armate troppo basso o che possiede
 * lui stesso, se sta provando ad attaccare con un numero di armate superiore
 * rispetto a quelle che possiede.
 *
 * @author Annachiara
 */
public class AttaccoFallito extends RisikoExceptions {

    private String territorio;
    private String elencoConfini;

    public String getTerritorio() {
        return territorio;
    }

    public String getElencoConfini() {
        return elencoConfini;
    }

    public AttaccoFallito(String territorio, String elencoConfini) {
        this.territorio = territorio;
        this.elencoConfini = elencoConfini;
    }

    @Override
    public String toString() {
        return "Attacco Fallito - l'attacco che è partito dal territorio \""
                + territorio + "\" non è andato a buon fine; Prova con uno tra i seguenti confini \n("+elencoConfini+").";

    }

}
