/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import model.ObiettivoPartita;

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
                + "\" ha portato a termine il suo obiettivo \n(" + obiettivo + ")\n\n"+" .----------------.  .----------------.  .----------------.                                                             \n" +
"| .--------------. || .--------------. || .--------------. |                                                            \n" +
"| |  ____  ____  | || |      __      | || |     _____    | |                                                            \n" +
"| | |_   ||   _| | || |     /  \\     | || |    |_   _|   | |                                                            \n" +
"| |   | |__| |   | || |    / /\\ \\    | || |      | |     | |                                                            \n" +
"| |   |  __  |   | || |   / ____ \\   | || |      | |     | |                                                            \n" +
"| |  _| |  | |_  | || | _/ /    \\ \\_ | || |     _| |_    | |                                                            \n" +
"| | |____||____| | || ||____|  |____|| || |    |_____|   | |                                                            \n" +
"| |              | || |              | || |              | |                                                            \n" +
"| '--------------' || '--------------' || '--------------' |                                                            \n" +
" .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  .----------------. \n" +
"| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
"| | ____   ____  | || |     _____    | || | ____  _____  | || |  _________   | || |     ____     | || |              | |\n" +
"| ||_  _| |_  _| | || |    |_   _|   | || ||_   \\|_   _| | || | |  _   _  |  | || |   .'    `.   | || |      _       | |\n" +
"| |  \\ \\   / /   | || |      | |     | || |  |   \\ | |   | || | |_/ | | \\_|  | || |  /  .--.  \\  | || |     | |      | |\n" +
"| |   \\ \\ / /    | || |      | |     | || |  | |\\ \\| |   | || |     | |      | || |  | |    | |  | || |     | |      | |\n" +
"| |    \\ ' /     | || |     _| |_    | || | _| |_\\   |_  | || |    _| |_     | || |  \\  `--'  /  | || |     | |      | |\n" +
"| |     \\_/      | || |    |_____|   | || ||_____|\\____| | || |   |_____|    | || |   `.____.'   | || |     |_|      | |\n" +
"| |              | || |              | || |              | || |              | || |              | || |     (_)      | |\n" +
"| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
" '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ";

    }
}
