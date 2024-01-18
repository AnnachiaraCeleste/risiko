/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Annachiara
 */
public class Risiko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println(TipoContinente.valueOf("AFRICA"));
       IOObjectFileTerritorioDettagliato file = new IOObjectFileTerritorioDettagliato("territori.txt", ";");
        ArrayList<TerritorioDettagliato> lista= file.loadData();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
        
    }
}
