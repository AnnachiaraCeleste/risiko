/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import enums.TipoColore;
import enums.TipoPartita;

/**
 *
 * @author Annachiara
 */
public class EnumParser {
    public static boolean checkTipoColore(String value){
         try {
             TipoColore.valueOf(value);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
    public static boolean checkTipoPartita(String value){
         try {
             TipoPartita.valueOf(value);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
