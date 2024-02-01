/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import enums.TipoObiettivo;
import enums.TipoPartita;
import java.io.IOException;
import userinterface.TestPartita;



/**
 *
 * @author Annachiara
 */
public class Risiko{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws exceptions.TerritorioNonRegistrato
     */
    public static void main(String[] args) throws IOException {

        GestionePartita gp = new GestionePartita();
        TestPartita tp = new TestPartita(gp);

        System.out.println("  _____    _____    _____   _____   _  __   ____  \n"
                + " |  __ \\  |_   _|  / ____| |_   _| | |/ /  / __ \\ \n"
                + " | |__) |   | |   | (___     | |   | ' /  | |  | |\n"
                + " |  _  /    | |    \\___ \\    | |   |  <   | |  | |\n"
                + " | | \\ \\   _| |_   ____) |  _| |_  | . \\  | |__| |\n"
                + " |_|  \\_\\ |_____| |_____/  |_____| |_|\\_\\  \\____/ ");

        if (!iofiles.ReadFile.getAllLines(gp.getIofGiocatorePartita().getFileName()).isEmpty()) {
            System.out.println("VUOI CONTINUARE LA PARTITA INIZIATA?");
            if (!tp.scelta()) {
                iofiles.WriteFile.clearAllLines(gp.getIofGiocatorePartita().getFileName());
                iofiles.WriteFile.clearAllLines(gp.getIofObiettivoPartita().getFileName());
                iofiles.WriteFile.clearAllLines(gp.getIofTerritorioPartita().getFileName());
                iofiles.WriteFile.clearAllLines(gp.getIofCarteArmiPartita().getFileName());
                tp.inizioPartita();
            } else {
                if (gp.getIofObiettivoPartita().get(1).getTipoObiettivo().equals(TipoObiettivo.TORNEO)) {
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

        for (int i = 0;i < gp.getN_giocatori(); i++) {
            String psw = tp.getPassword(i);
            System.out.println("E' IL TURNO DEL GIOCATORE: " + tp.ricercaGiocatore(psw));
            tp.turnoDiGioco(psw);
        }

    }

}
