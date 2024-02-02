/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Territorio;
import java.util.ArrayList;
import enums.TipoArma;
import java.util.Objects;

/**
 *
 * @author Annachiara
 */
public class CarteArmiPartita extends Territorio {

    private String passwordGiocatore;

    public CarteArmiPartita(String nome, TipoArma arma, String password) {
        super(nome, arma);
        this.passwordGiocatore = password;
    }

    public String getPasswordGiocatore() {
        return passwordGiocatore;
    }

    public void setPasswordGiocatore(String passwordGiocatore) {
        this.passwordGiocatore = passwordGiocatore;
    }

    @Override
    public String toString() {
        return "{Territorio=" + getNome() + " Arma=" + getArma() + " pswGiocatore=" + passwordGiocatore + '}';
    }

    

    public boolean equals(CarteArmiPartita obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarteArmiPartita other = (CarteArmiPartita) obj;
        if (!Objects.equals(this.getNome(), other.getNome())) {
            return false;
        }
        return true;
    }

}
