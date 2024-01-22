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
    private String password;

    public ObiettivoPartita(String obiettivo,TipoObiettivo tipoObiettivo,String password,boolean stato) {
        super(obiettivo,tipoObiettivo);
        this.stato=stato;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }
    
}
