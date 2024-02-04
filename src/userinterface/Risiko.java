/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import enums.TipoColore;
import enums.TipoObiettivo;
import enums.TipoPartita;
import exceptions.FinePartita;
import exceptions.GiocatoreNonRegistrato;
import exceptions.ObiettivoNonRegistrato;
import exceptions.TerritorioNonRegistrato;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import userinterface.TestPartita;
import logic.GestionePartita;
import model.ObiettivoPartita;
import model.Territorio;
import model.TerritorioPartita;

/**
 *
 * @author Annachiara
 */
public class Risiko {

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

            if (!iofiles.ReadFile.getAllLines(gp.getIofGiocatorePartita().getFileName()).isEmpty()) {
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
            Logger.getLogger(Risiko.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FinePartita ex) {
            Logger.getLogger(Risiko.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
