/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 * Enum TipoColore: elenco dei colori per le armate
 *
 * @author Annachiara
 */
public enum TipoColore {
    RED,
    GREEN,
    BLUE,
    YELLOW,
    MAGENTA,
    CYAN,
    WHITE,
    BLACK;
    
    public static String listOfCostantsColore() {
        TipoColore[] values = TipoColore.values();
        String res = "";
        int i;
        for (i = 0; i < values.length - 1; i++) {
            res += values[i].name() + ", ";
        }
        res += values[i];
        return res;
    }
}
