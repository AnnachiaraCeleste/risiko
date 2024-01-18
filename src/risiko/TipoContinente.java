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
public enum TipoContinente {
    ASIA(7),
    AFRICA(3),
    EUROPA(5),
    NORD_AMERICA(5),
    SUD_AMERICA(2),
    OCEANIA(2);

    private final int numeroArmateAssegnate;

    TipoContinente(int numeroArmateAssegnate) {
        this.numeroArmateAssegnate = numeroArmateAssegnate;
    }

    public int getNumeroArmateAssegnate() {
        return numeroArmateAssegnate;
    }
}

