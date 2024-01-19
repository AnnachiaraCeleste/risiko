/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.util.ArrayList;

/**
 *
 * @author Annachiara
 */
public class CarteArmiPartita extends Territorio {

    private String nomeGiocatore;

    public CarteArmiPartita(String nome, TipoArma arma,String nomeGiocatore) {
        super(nome, arma);
        this.nomeGiocatore = nomeGiocatore;
    }

    public String getNomeGiocatore() {
        return nomeGiocatore;
    }

    public void setNomeGiocatore(String nomeGiocatore) {
        this.nomeGiocatore = nomeGiocatore;
    }

}
