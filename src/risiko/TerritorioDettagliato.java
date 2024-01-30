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
public class TerritorioDettagliato extends Territorio {

    private final ArrayList<String> confini;
    private final TipoContinente continente;

    public TerritorioDettagliato(String nome, ArrayList<String> confini, TipoContinente continente, TipoArma arma) {
        super(nome, arma);
        this.confini = confini;
        this.continente = continente;
    }

    public TipoContinente getContinente() {
        return continente;
    }

    

    public void aggiungiConfine(String confine) {
        confini.add(confine);
    }

    public ArrayList<String> getConfini() {
        return confini;
    }

    public String getSequenzaConfini() {
        String listaConfini = "";
        for (int i = 0; i < this.confini.size(); i++) {
            listaConfini += confini.get(i);
            if (i < this.confini.size() - 1) {
                listaConfini += (",");
            }
        }
        return listaConfini;
    }

    @Override
    public String toString() {
        return "\nTERRITORIO: " + this.getNome().toUpperCase() + "\nconfini: " + confini + "\ncontinente: " + continente + "\ntipo arma: " +this.getArma()+ "\n";
    }

}
