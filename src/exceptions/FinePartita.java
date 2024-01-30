/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import risiko.ObiettivoPartita;

/**
 *
 * @author Annachiara
 */
public class FinePartita extends RisikoExceptions{

    private String giocatore;
    private String obiettivo;

    public String getGiocatore() {
        return giocatore;
    }

    public String getObiettivo() {
        return obiettivo;
    }

    public FinePartita(String giocatore, String obiettivo) {
        this.giocatore = giocatore;
        this.obiettivo = obiettivo;
    }

    @Override
    public String toString() {
        return "ECCOCI ARRIVATI ALLA FINE: Il giocatore \"" + giocatore
                + "\" ha portato a termine il suo obiettivo \n(" + obiettivo + ")\n\n"
                + " ___  ___  ________  ___                                     \n"
                + "|\\  \\|\\  \\|\\   __  \\|\\  \\                                    \n"
                + "\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\                                   \n"
                + " \\ \\   __  \\ \\   __  \\ \\  \\                                  \n"
                + "  \\ \\  \\ \\  \\ \\  \\ \\  \\ \\  \\                                 \n"
                + "   \\ \\__\\ \\__\\ \\__\\ \\__\\ \\__\\                                \n"
                + " ___\\|__|\\___|____|_________| _________  ________  ___       \n"
                + "|\\  \\    /  /|\\  \\|\\   ___  \\|\\___   ___\\\\   __  \\|\\  \\      \n"
                + "\\ \\  \\  /  / | \\  \\ \\  \\\\ \\  \\|___ \\  \\_\\ \\  \\|\\  \\ \\  \\     \n"
                + " \\ \\  \\/  / / \\ \\  \\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\\\\\  \\ \\  \\    \n"
                + "  \\ \\    / /   \\ \\  \\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\\\\\  \\ \\__\\   \n"
                + "   \\ \\__/ /     \\ \\__\\ \\__\\\\ \\__\\   \\ \\__\\ \\ \\_______\\|__|   \n"
                + "    \\|__|/       \\|__|\\|__| \\|__|    \\|__|  \\|_______|   ___ \n"
                + "                                                        |\\__\\\n"
                + "                                                        \\|__|";

    }
}
