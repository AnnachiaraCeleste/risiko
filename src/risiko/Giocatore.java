/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Annachiara
 */
public class Giocatore {

    private String nome;
    private Colors colore;
    private int nTruppe;
    private ArrayList<Territorio> territoriPosseduti;
    private int rinforzi;
    private Obiettivo obiettivo;
    private ArrayList<TipoArma> listaCarte;
    private final String password;

    public Giocatore(String nome, Colors colore, String password) {
        this.nome = nome;
        this.colore = colore;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Colors getColore() {
        return colore;
    }

    public void setColore(Colors colore) {
        this.colore = colore;
    }

    public int getnTruppe() {
        return nTruppe;
    }

    public void setnTruppe(int nTruppe) {
        this.nTruppe = nTruppe;
    }

    public String getPassword() {
        return password;
    }

    public Obiettivo getObiettivo() {
        return obiettivo;
    }

    public void setObiettivo(Obiettivo obiettivo) {
        this.obiettivo = obiettivo;
    }

    public boolean equals(Giocatore g) {
        return g.colore.equals(this.colore);
    }
    
    @Override
    public String toString() {
        return "Giocatore{" + "nome=" + nome + ", colore=" + colore + ", nTruppe=" + nTruppe + ", rinforzi=" + rinforzi + ", obiettivo=" + obiettivo + ", password=" + password + '}';
    }

    

}
