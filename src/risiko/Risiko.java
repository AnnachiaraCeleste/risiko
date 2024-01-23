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
         GestionePartita gp = new GestionePartita(5, TipoPartita.CLASSICA);
        System.out.println(TipoContinente.valueOf("AFRICA"));
       IOObjectFileTerritorioDettagliato file = new IOObjectFileTerritorioDettagliato("territori.txt", ";");
        ArrayList<TerritorioDettagliato> lista= file.loadData();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
        for (int i = 0; i < Colors.values().length; i++) {
            System.out.println(Colors.values()[i].name());
        }
        System.out.println(gp.listaTerritoriDettagliatiContinente(TipoContinente.AFRICA));
        
        System.out.println("\n\n\n\n\n\n");
        System.out.println(TipoContinente.values()[1].name());
       Obiettivo obiettivo= new Obiettivo("Devi conquistare la totalità del NORD_AMERICA e dell'AFRICA",TipoObiettivo.CONTINENTI);
        System.out.println(obiettivo.getObiettivo().contains(TipoContinente.values()[1].name()));
        
        int[] idxTerritori = new int[42];
        boolean trovato;
        for (int i = 0; i < idxTerritori.length; i++) {
            do {
                idxTerritori[i] = (int) (Math.random() * ( 41 + 1));
                trovato = false;
                for (int j = 0; j < i; j++) {
                    if (idxTerritori[j] == idxTerritori[i]) {
                        trovato = true;
                    }
                }
            } while (trovato);
        }
        for (int i = 0; i < idxTerritori.length; i++) {
            System.out.println(idxTerritori[i]);
        }
       
        
        
        
    }
}
