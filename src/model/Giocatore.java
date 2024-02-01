/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enums.TipoColore;

/**
 *
 * @author Annachiara
 */
public class Giocatore {

    private String nome;
    private TipoColore colore;//serve per l'interfaccia
    private int rinforzi;
    private final String password;
    private int nTConquistatiPerTurno;

    public Giocatore(String nome, TipoColore colore, String password,int rinforzi,int tConquistati) {
        this.nome = nome;
        this.colore = colore;
        this.password = password;
        this.rinforzi=rinforzi;
        this.nTConquistatiPerTurno=tConquistati;
    }

    public Giocatore(Giocatore g) {
        this.nome = g.nome;
        this.colore = g.colore;
        this.rinforzi = g.rinforzi;
        this.password = g.password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoColore getColore() {
        return colore;
    }

    public void setColore(TipoColore colore) {
        this.colore = colore;
    }

    public int getRinforzi() {
        return rinforzi;
    }

    public void setRinforzi(int rinforzi) {
        this.rinforzi = rinforzi;
    }

    public String getPassword() {
        return password;
    }

    public int getNTerritoriConquistatiPerTurno() {
        return nTConquistatiPerTurno;
    }

    public void setNTerritoriConquistatiPerTurno(int nTerritoriConquistatiPerTurno) {
            this.nTConquistatiPerTurno = nTerritoriConquistatiPerTurno;
    }
    /**
     * EQUALS per colore
     * @param g
     * @return 
     */
    public boolean equals(Giocatore g) {
        return g.colore.equals(this.colore);
    }
    

    @Override
    public String toString() {
        return "Giocatore{" + "nome=" + nome + ", colore=" + colore + ", rinforzi=" + rinforzi + ", password=" + password + '}';
    }

}
