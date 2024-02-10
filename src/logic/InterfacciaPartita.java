/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import exceptions.RisikoExceptions;
import java.io.IOException;

/**
 *
 * @author Annachiara
 */
public interface InterfacciaPartita {

    /**
     * ESEGUI FASE: metodo astratto da implementare nelle classi che
     * ereditano.Tutte le classi che saranno implementate hanno in comune un
     * giocatore con una psw specifica che in un momento della partita esegue le
     * tre fasi di gioco (rinforzo, attacco, spostamento)
     *
     * @param psw specifica del giocatore
     * @throws java.io.IOException
     * @throws exceptions.RisikoExceptions
     */
    void eseguiFase(String psw) throws IOException, RisikoExceptions;

    /**
     * STAMPA FASE: metodo per stampare in maniera riassunta la fase che viene
     * effettuata
     *
     * @param psw del giocatore che esegue la fase
     * @throws IOException
     * @throws RisikoExceptions
     */
    void stampaFase(String psw) throws IOException, RisikoExceptions;
}
