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
    private final String password;//identificcativa
    private int truppe;//numero di truppe o rinforzi
    private int nTConquistatiPerTurno;//territori che vengono conquistati (se !=0 allora si pesca una carta)???valutare se è necessario
    //Territori per gestire gli eventi della partita
    //sono le scelte che fa il giocatore nel momento delle fasi
    private int rinforziTurno;//truppe di rinforzo assegnate a inizio turno
    private String territorioOrigine;
    private String territorioDestinazione;

    public Giocatore(String nome, TipoColore colore, String password, int truppe, int tConquistati, String tOrigine, String tDestinazione, int rinforziTurno) {
        this.nome = nome;
        this.colore = colore;
        this.password = password;
        this.truppe = truppe;
        this.nTConquistatiPerTurno = tConquistati;
        this.territorioOrigine = tOrigine;
        this.territorioDestinazione = tDestinazione;
        this.rinforziTurno = rinforziTurno;
    }

    public Giocatore(String nome, TipoColore colore, String password) {
        this.nome = nome;
        this.colore = colore;
        this.password = password;
        this.truppe = 0;
        this.nTConquistatiPerTurno = 0;
        this.territorioOrigine = " ";
        this.territorioDestinazione = " ";
        this.rinforziTurno = 0;
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

    public int getTruppe() {
        return truppe;
    }

    public void setTruppe(int truppe) {
        this.truppe = truppe;
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

    public String getTerritorioOrigine() {
        return territorioOrigine;
    }

    public void setTerritorioOrigine(String territorioOrigine) {
        this.territorioOrigine = territorioOrigine;
    }

    public String getTerritorioDestinazione() {
        return territorioDestinazione;
    }

    public void setTerritorioDestinazione(String territorioDestinazione) {
        this.territorioDestinazione = territorioDestinazione;
    }

    public int getRinforziTurno() {
        return rinforziTurno;
    }

    public void setRinforziTurno(int rinforziTurno) {
        this.rinforziTurno = rinforziTurno;
    }

    /**
     * EQUALS per colore
     *
     * @param g
     * @return
     */
    public boolean equals(Giocatore g) {
        return g.colore.equals(this.colore);
    }

    @Override
    public String toString() {
        return "Giocatore{" + "nome=" + nome + ", colore=" + colore + ", password=" + password + '}';
    }

}
