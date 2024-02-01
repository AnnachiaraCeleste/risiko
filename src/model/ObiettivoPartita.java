/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Obiettivo;
import enums.TipoObiettivo;

/**
 *
 * @author Annachiara
 */
public class ObiettivoPartita extends Obiettivo{
    private String password;

    public ObiettivoPartita(String obiettivo,TipoObiettivo tipoObiettivo,String password) {
        super(obiettivo,tipoObiettivo);
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
