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
                + "\" ha portato a termine il suo obiettivo \n(" + obiettivo + ")\n\n"
                +"                                                                                                                                                                                   \n" +
"                                                                                                                                                                                   \n" +
"HHHHHHHHH     HHHHHHHHH               AAA               IIIIIIIIII     VVVVVVVV           VVVVVVVVIIIIIIIIIINNNNNNNN        NNNNNNNNTTTTTTTTTTTTTTTTTTTTTTT     OOOOOOOOO      !!! \n" +
"H:::::::H     H:::::::H              A:::A              I::::::::I     V::::::V           V::::::VI::::::::IN:::::::N       N::::::NT:::::::::::::::::::::T   OO:::::::::OO   !!:!!\n" +
"H:::::::H     H:::::::H             A:::::A             I::::::::I     V::::::V           V::::::VI::::::::IN::::::::N      N::::::NT:::::::::::::::::::::T OO:::::::::::::OO !:::!\n" +
"HH::::::H     H::::::HH            A:::::::A            II::::::II     V::::::V           V::::::VII::::::IIN:::::::::N     N::::::NT:::::TT:::::::TT:::::TO:::::::OOO:::::::O!:::!\n" +
"  H:::::H     H:::::H             A:::::::::A             I::::I        V:::::V           V:::::V   I::::I  N::::::::::N    N::::::NTTTTTT  T:::::T  TTTTTTO::::::O   O::::::O!:::!\n" +
"  H:::::H     H:::::H            A:::::A:::::A            I::::I         V:::::V         V:::::V    I::::I  N:::::::::::N   N::::::N        T:::::T        O:::::O     O:::::O!:::!\n" +
"  H::::::HHHHH::::::H           A:::::A A:::::A           I::::I          V:::::V       V:::::V     I::::I  N:::::::N::::N  N::::::N        T:::::T        O:::::O     O:::::O!:::!\n" +
"  H:::::::::::::::::H          A:::::A   A:::::A          I::::I           V:::::V     V:::::V      I::::I  N::::::N N::::N N::::::N        T:::::T        O:::::O     O:::::O!:::!\n" +
"  H:::::::::::::::::H         A:::::A     A:::::A         I::::I            V:::::V   V:::::V       I::::I  N::::::N  N::::N:::::::N        T:::::T        O:::::O     O:::::O!:::!\n" +
"  H::::::HHHHH::::::H        A:::::AAAAAAAAA:::::A        I::::I             V:::::V V:::::V        I::::I  N::::::N   N:::::::::::N        T:::::T        O:::::O     O:::::O!:::!\n" +
"  H:::::H     H:::::H       A:::::::::::::::::::::A       I::::I              V:::::V:::::V         I::::I  N::::::N    N::::::::::N        T:::::T        O:::::O     O:::::O!!:!!\n" +
"  H:::::H     H:::::H      A:::::AAAAAAAAAAAAA:::::A      I::::I               V:::::::::V          I::::I  N::::::N     N:::::::::N        T:::::T        O::::::O   O::::::O !!! \n" +
"HH::::::H     H::::::HH   A:::::A             A:::::A   II::::::II              V:::::::V         II::::::IIN::::::N      N::::::::N      TT:::::::TT      O:::::::OOO:::::::O     \n" +
"H:::::::H     H:::::::H  A:::::A               A:::::A  I::::::::I               V:::::V          I::::::::IN::::::N       N:::::::N      T:::::::::T       OO:::::::::::::OO  !!! \n" +
"H:::::::H     H:::::::H A:::::A                 A:::::A I::::::::I                V:::V           I::::::::IN::::::N        N::::::N      T:::::::::T         OO:::::::::OO   !!:!!\n" +
"HHHHHHHHH     HHHHHHHHHAAAAAAA                   AAAAAAAIIIIIIIIII                 VVV            IIIIIIIIIINNNNNNNN         NNNNNNN      TTTTTTTTTTT           OOOOOOOOO      !!! \n" +
"                                                                                                                                                                                   \n" +
"                                                                                                                                                                                   \n" +
"                                                                                                                                                                                   \n" +
"                                                                                                                                                                                   \n" +
"                                                                                                                                                                                   \n" +
"                                                                                                                                                                                   \n" +
"                                                                                                                                                                                   ";

    }
}
