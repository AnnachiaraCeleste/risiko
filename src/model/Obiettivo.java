/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import enums.TipoObiettivo;

/**
 *
 * @author Annachiara
 */
public class Obiettivo {

    private String obiettivo;
    private TipoObiettivo tipoObiettivo;

    public TipoObiettivo getTipoObiettivo() {
        return tipoObiettivo;
    }

    public void setTipoObiettivo(TipoObiettivo tipoObiettivo) {
        this.tipoObiettivo = tipoObiettivo;
    }

    public Obiettivo(String obiettivo, TipoObiettivo tipoObiettivo) {
        this.obiettivo = obiettivo;
        this.tipoObiettivo = tipoObiettivo;
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
