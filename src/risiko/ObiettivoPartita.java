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
public class ObiettivoPartita extends Obiettivo{
    private boolean stato;
    private String nomeProprietario;

    public ObiettivoPartita(String obiettivo,String nomeProprietario,boolean stato) {
        super(obiettivo);
        this.stato=stato;
        this.nomeProprietario=nomeProprietario;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public boolean getStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }
    
}
