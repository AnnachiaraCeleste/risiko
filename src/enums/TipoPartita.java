/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 * Enum TipoPartita: Classica(ha una durata della partita significativa);
 * Torneo(dovrebbe durare poco).
 *
 * @author Annachiara
 */
public enum TipoPartita {
    CLASSICA, TORNEO;
    
    public static String listOfCostants() {
        TipoPartita[] values = TipoPartita.values();
        String res = "";
        int i;
        for (i = 0; i < values.length - 1; i++) {
            res += values[i].name() + ", ";
        }
        res += values[i];
        return res;
    }
}
