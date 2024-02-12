/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import enums.TipoArma;
import exceptions.GiocatoreNonRegistrato;
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

    /**
     * SET TERRITORI TRUPPE PER FASE
     *
     * @param psw del giocatore
     * @param territorioPartenza territorio dal quale si vuole spostare le
     * truppe
     * @param territorioDestinazione territorio su cui spostare le truppe
     * @param numeroArmateDaSpostare numero di truppe da spostare
     */
    @Override
    public void setTerritoriTruppePerFase(String psw, String territorioPartenza, String territorioDestinazione, int numeroArmateDaSpostare) throws GiocatoreNonRegistrato {
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
            super.setTerritoriTruppePerFase(psw, territorioPartenza, territorioDestinazione, numeroArmateDaSpostare);
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
        Giocatore g = getGiocatore(psw);
        TerritorioPartita tO= iofTerritorioPartita.get(getLineTerritorioPartita(g.getTerritorioOrigine()));
        TerritorioPartita tD=iofTerritorioPartita.get(getLineTerritorioPartita(g.getTerritorioDestinazione()));
        tO.setNumeroArmate(getTerritorioPartita(g.getTerritorioOrigine()).getNumeroArmate() - g.getTruppe());
        iofTerritorioPartita.set(getLineTerritorioPartita(g.getTerritorioOrigine()), tO);
        tD.setNumeroArmate(getTerritorioPartita(g.getTerritorioDestinazione()).getNumeroArmate() + g.getTruppe());
        iofTerritorioPartita.set(getLineTerritorioPartita(g.getTerritorioOrigine()),tD );
    }

    @Override
    public void stampaFase(String psw) throws IOException, RisikoExceptions {
        Giocatore g = getGiocatore(psw);
        System.out.println("FASE SPOSTAMENTO: " + g.getTerritorioOrigine() + "---" + g.getTruppe() + "-->" + g.getTerritorioDestinazione());
    }

}
