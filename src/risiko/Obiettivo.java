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
public class Obiettivo {

    private String obiettivo;

    public Obiettivo(String obiettivo) {
        this.obiettivo = obiettivo;
    }

    public String getObiettivo() {
        return obiettivo;
    }

    public void setObiettivo(String obiettivo) {
        this.obiettivo = obiettivo;
    }

    @Override
    public String toString() {
        return "Obiettivo{" + obiettivo + '}';
    }

}
