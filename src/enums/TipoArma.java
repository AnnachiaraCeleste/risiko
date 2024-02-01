/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 * Classe TipoArma: FANTERIA, CAVALLERIA, ARTIGLIERIA.
 *
 * @author Annachiara
 */
public enum TipoArma {
    FANTERIA,
    CAVALLERIA,
    ARTIGLIERIA;

    public static String listOfCostants() {
        TipoArma[] values = TipoArma.values();
        String res = "";
        int i;
        for (i = 0; i < values.length - 1; i++) {
            res += values[i].name() + ", ";
        }
        res += values[i];
        return res;
    }

}
