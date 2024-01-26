/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

/**
 *
 * @author Annachiara
 */
public class Giocatore {

    private String nome;
    private TipoColore colore;
    private int nTruppe;
    private int rinforzi;
    private final String password;
    private int nTerritoriConquistatiPerTurno;

    public Giocatore(String nome, TipoColore colore, String password) {
        this.nome = nome;
        this.colore = colore;
        this.password = password;
    }

    public Giocatore(Giocatore g) {
        this.nome = g.nome;
        this.colore = g.colore;
        this.nTruppe = g.nTruppe;
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

    public int getnTruppe() {
        return nTruppe;
    }

    public void setnTruppe(int nTruppe) {
        this.nTruppe = nTruppe;
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
        return nTerritoriConquistatiPerTurno;
    }

    public void setNTerritoriConquistatiPerTurno(int nTerritoriConquistatiPerTurno) {
            this.nTerritoriConquistatiPerTurno = nTerritoriConquistatiPerTurno;
    }

    public boolean equals(Giocatore g) {
        return g.colore.equals(this.colore);
    }

    @Override
    public String toString() {
        return "Giocatore{" + "nome=" + nome + ", colore=" + colore + ", nTruppe=" + nTruppe + ", rinforzi=" + rinforzi + ", password=" + password + '}';
    }

}
