/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import enums.TipoObiettivo;
import enums.TipoPartita;
import exceptions.ControlloObiettivo;
import java.io.IOException;
import logic.GestionePartita;

/**
 *
 * @author Annachiara
 */
public class Risiko {
/*
    public static void main(String[] args) {
        try {
            GestionePartita gp = new GestionePartita();
            TestPartita tp = new TestPartita(gp);

            System.out.println("  _____    _____    _____   _____   _  __   ____  \n"
                    + " |  __ \\  |_   _|  / ____| |_   _| | |/ /  / __ \\ \n"
                    + " | |__) |   | |   | (___     | |   | ' /  | |  | |\n"
                    + " |  _  /    | |    \\___ \\    | |   |  <   | |  | |\n"
                    + " | | \\ \\   _| |_   ____) |  _| |_  | . \\  | |__| |\n"
                    + " |_|  \\_\\ |_____| |_____/  |_____| |_|\\_\\  \\____/ ");
            System.out.println("\n\nPRONTO A METTERE IN GIOCO LA TUE ABILITA' STRATEGICHE?\nBUON DIVERTIMENTO!\nRicordati: la guerra non è un gioco");
            //Per poter inziziare la partita deve avere almeno due giocatori e due obiettivi
            if (gp.getIofGiocatorePartita().loadData().size() > 1 && gp.getIofObiettivoPartita().loadData().size() > 1) {
                System.out.println("\n\nVUOI CONTINUARE LA PARTITA INIZIATA?");
                if (!tp.scelta()) {
                    iofiles.WriteFile.clearAllLines(gp.getIofGiocatorePartita().getFileName());
                    iofiles.WriteFile.clearAllLines(gp.getIofObiettivoPartita().getFileName());
                    iofiles.WriteFile.clearAllLines(gp.getIofTerritorioPartita().getFileName());
                    iofiles.WriteFile.clearAllLines(gp.getIofCarteArmiPartita().getFileName());
                    tp.inizioPartita();
                } else {
                    if (gp.getIofObiettivoPartita().get(0).getTipoObiettivo().equals(TipoObiettivo.TORNEO)) {
                        gp.setTipoPartita(TipoPartita.TORNEO);
                    } else {
                        gp.setTipoPartita(TipoPartita.CLASSICA);
                    }
                    gp.setN_giocatori(gp.getIofGiocatorePartita().loadData().size());
                }
            } else {
                //per sicurezza si ripulisce il file dei giocatori
                iofiles.WriteFile.clearAllLines(gp.getIofGiocatorePartita().getFileName());
                tp.inizioPartita();
            }
            System.out.println(gp);
            for (;;) {
                for (int i = 0; i < gp.getN_giocatori(); i++) {
                    String psw = tp.getPassword(i);
                    System.out.println("================================================"
                            + "=============================================");
                    System.out.println("E' IL TURNO DEL GIOCATORE: " + tp.ricercaGiocatore(psw));
                    tp.controlloStatoPartita(psw);
                    tp.turnoDiGioco(psw);
                    tp.controlloStatoPartita(psw);

                }
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Risiko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch(ControlloObiettivo ex){
            System.out.println(ex);
        }

    }*/
}
