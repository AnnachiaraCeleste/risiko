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
public class Territorio {

    private final String nome;
    private final TipoArma arma;

    public Territorio(String nome, TipoArma tipoArma) {
        this.nome = nome;
        this.arma = tipoArma;
    }

    public String getNome() {
        return nome;
    }

    public TipoArma getArma() {
        return arma;
    }

    public static ArrayList<String> splitTerritori(String attributoConfini) {
        String[] attributes = attributoConfini.split(",");
        ArrayList<String> listaElementi = new ArrayList<>();
        for (int i = 0; i < attributes.length; i++) {
            listaElementi.add(attributes[i]);
        }
        return listaElementi;
    }

}
