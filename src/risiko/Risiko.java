/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import com.sun.jdi.connect.Connector;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import exceptions.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Annachiara
 */
public class Risiko {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*
        System.out.println(TipoContinente.valueOf("AFRICA"));
       IOObjectFileTerritorioDettagliato file = new IOObjectFileTerritorioDettagliato("territori.txt", ";");
        ArrayList<TerritorioDettagliato> lista= file.loadData();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
        for (int i = 0; i < TipoColore.values().length; i++) {
            System.out.println(TipoColore.values()[i].name());
        }
        System.out.println(gp.listaTerritoriDettagliatiContinente(TipoContinente.AFRICA));
        
        System.out.println("\n\n\n\n\n\n");
        System.out.println(TipoContinente.values()[1].name());
       Obiettivo obiettivo= new Obiettivo("Devi conquistare la totalità del NORD_AMERICA e dell'AFRICA",TipoObiettivo.CONTINENTI);
        System.out.println(obiettivo.getObiettivo().contains(TipoContinente.values()[1].name()));
        
         
        int[] risultati = new int[3];
        for (int i = 0; i < 3; i++) {
            risultati[i] = (int) (Math.random() * (6)) + 1; // Dado a 6 facce
        }
        
    
        for (int i = 0; i <risultati.length; i++) {
            System.out.println(risultati[i]);
        }
         // Stampa l'intestazione della tabella
        System.out.printf("%-20s%-15s\n", "Nome Territorio", "Numero Armate");
        System.out.println("----------------------------");*/
        /*
        GestionePartita gp = new GestionePartita(1, TipoPartita.CLASSICA);
        try {
            
            
             gp.getTerritorioPartita("Argentina");
        gp.faseSpostamento("Argentina", "Gran Bretagna", 4);
        } catch (IOException ex) {
            Logger.getLogger(Risiko.class.getName()).log(Level.SEVERE, null, ex);       
        } catch (SpostamentoFallito ex) {
            Logger.getLogger(Risiko.class.getName()).log(Level.SEVERE, null, ex);
        }

        */
        //gp.faseAttacco("Argentina", "Brasile", 3, 1);
        System.out.println(" ___  ___  ________  ___                                     \n" +
"|\\  \\|\\  \\|\\   __  \\|\\  \\                                    \n" +
"\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\                                   \n" +
" \\ \\   __  \\ \\   __  \\ \\  \\                                  \n" +
"  \\ \\  \\ \\  \\ \\  \\ \\  \\ \\  \\                                 \n" +
"   \\ \\__\\ \\__\\ \\__\\ \\__\\ \\__\\                                \n" +
" ___\\|__|\\___|____|_________| _________  ________  ___       \n" +
"|\\  \\    /  /|\\  \\|\\   ___  \\|\\___   ___\\\\   __  \\|\\  \\      \n" +
"\\ \\  \\  /  / | \\  \\ \\  \\\\ \\  \\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\     \n" +
" \\ \\  \\/  / / \\ \\  \\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\    \n" +
"  \\ \\    / /   \\ \\  \\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\\\\\  \\ \\__\\   \n" +
"   \\ \\__/ /     \\ \\__\\ \\__\\\\ \\__\\   \\ \\__\\ \\ \\_______\\|__|   \n" +
"    \\|__|/       \\|__|\\|__| \\|__|    \\|__|  \\|_______|   ___ \n" +
"                                                        |\\__\\\n" +
"                                                        \\|__|");
    }
}
