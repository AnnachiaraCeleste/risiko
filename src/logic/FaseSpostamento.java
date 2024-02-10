/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import exceptions.RisikoExceptions;
import exceptions.SpostamentoFallito;
import exceptions.TerritorioNonPosseduto;
import exceptions.TerritorioNonRegistrato;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Giocatore;
import model.TerritorioPartita;

/**
 *
 * @author Annachiara
 */
public class FaseSpostamento extends GestionePartita {


    @Override
    public void setTerritoriTruppePerFase(String psw, String territorioPartenza, String territorioDestinazione, int numeroArmateDaSpostare) {
        try {
            if (!getTerritorioPartita(territorioPartenza).getPasswordGiocatore().equals(psw)) {
                throw new TerritorioNonPosseduto(territorioPartenza);
            }
            if (!getTerritorioPartita(territorioDestinazione).getPasswordGiocatore().equals(psw)) {
                throw new TerritorioNonPosseduto(territorioDestinazione);
            }
            if (getTerritorioPartita(territorioPartenza).equals(getTerritorioPartita(territorioDestinazione))
                    || numeroArmateDaSpostare >= getTerritorioPartita(territorioPartenza).getNumeroArmate()
                    || getTerritorioPartita(territorioPartenza).getNumeroArmate() == 1) {
                throw new SpostamentoFallito(territorioPartenza);
            }
           super.setTerritoriTruppePerFase(psw, territorioPartenza, territorioDestinazione, nGiocatori);
        } catch (IOException | TerritorioNonRegistrato | TerritorioNonPosseduto | SpostamentoFallito ex) {
            Logger.getLogger(FaseSpostamento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * ESEGUI FASE: fase spostamento metodo per effettuare lo spostamento delle
     * truppe sui propri territori, dopo averglieli assegnati in modo corretto
     *
     * @param psw specifica del giocatore
     * @throws IOException
     * @throws exceptions.SpostamentoFallito
     * @throws exceptions.TerritorioNonRegistrato
     */
    @Override
    public void eseguiFase(String psw) throws IOException, RisikoExceptions {
        stampaFase(psw);
        ArrayList<TerritorioPartita> territoriPartita = iofTerritorioPartita.loadData();
        Giocatore g = getGiocatore(psw);
        territoriPartita.get(getLineTerritorioPartita(g.getTerritorioOrigine())).setNumeroArmate(getTerritorioPartita(g.getTerritorioOrigine()).getNumeroArmate() - g.getTruppe());
        territoriPartita.get(getLineTerritorioPartita(g.getTerritorioDestinazione())).setNumeroArmate(getTerritorioPartita(g.getTerritorioDestinazione()).getNumeroArmate() + g.getTruppe());
        iofTerritorioPartita.saveData(territoriPartita);
    }

    @Override
    public String stampaFase(String psw) throws IOException, RisikoExceptions {
        Giocatore g= getGiocatore(psw);
        return "FASE SPOSTAMENTO: "+g.getTerritorioOrigine()+"---"+g.getTruppe()+"-->"+g.getTerritorioDestinazione();
    }

}
