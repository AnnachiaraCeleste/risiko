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
public class NMaxTruppeRinforzoRaggiunto extends RisikoExceptions {

    private int nTruppe;

    public NMaxTruppeRinforzoRaggiunto(int nTruppe) {
        this.nTruppe = nTruppe;
    }

    public int getnTruppe() {
        return nTruppe;
    }

    @Override
    public String toString() {
        return "Numero Massimo Truppe Rinforzo Raggiunto - il numero di truppe ("
                + nTruppe + ") inserite per il rinforzo del territorio supera la quantità di rinforzi posseduti dal giocatore";

    }
}
