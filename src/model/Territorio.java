/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import enums.TipoArma;
import java.util.Objects;

/**
 *
 * @author Annachiara
 */
public class Territorio implements Comparable<Territorio> {

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

    @Override
    public int compareTo(Territorio altroTerritorio) {
        return this.getNome().toUpperCase().compareTo(altroTerritorio.getNome().toUpperCase());
    }

    public boolean equals(Territorio t) {
        if (!Objects.equals(this.nome, t.nome)) {
            return false;
        }
        if (this.arma != t.arma) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> splitTerritori(String attributoConfini) {
        String[] attributes = attributoConfini.split(",");
        ArrayList<String> listaElementi = new ArrayList<>();
        for (int i = 0; i < attributes.length; i++) {
            listaElementi.add(attributes[i].trim());
        }//.trim()toglie li spazi all'inizio e alla fine
        return listaElementi;
    }

}
