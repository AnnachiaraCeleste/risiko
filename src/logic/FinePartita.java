/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import enums.*;
import exceptions.*;
import java.io.IOException;
import java.util.ArrayList;
import model.*;


/**
 *
 * @author Annachiara
 */
public class FinePartita extends GestionePartita {

    /**
     * CONTROLLO STATO OBIETTIVO GIOCATORE: metodo che controlla se il giocatore
     * ha completato il suo obiettivo a seconda del tipo di obiettivo che gli è
     * stato assegnato: una intera armata, due continenti, un numero di
     * territori elevato oppure per la versione torneo 7 territori da
     * conquistare.
     *
     * @param psw del giocatore
     * @throws IOException
     * @throws ControlloObiettivo
     * @throws GiocatoreNonRegistrato
     * @throws TerritorioNonRegistrato
     * @throws ObiettivoNonRegistrato
     */
    @Override
    public void eseguiFase(String psw) throws IOException, GiocatoreNonRegistrato, TerritorioNonRegistrato, ObiettivoNonRegistrato, ControlloObiettivo {
        boolean statoObiettivo = false;
        ObiettivoPartita obiettivo = getObiettivoPartita(psw);
        ArrayList<TerritorioPartita> territoriGiocatore = listaTerritoriGiocatorePartita(psw);
        switch (obiettivo.getTipoObiettivo()) {
            case ARMATE:
                TipoColore colore = null;
                for (int i = 0; i < TipoColore.values().length; i++) {
                    if (obiettivo.getObiettivo().contains(TipoColore.values()[i].name())) {
                        colore = TipoColore.values()[i];
                        break;
                    }
                }
                if (getLineGiocatore(colore) == -1 && listaTerritoriGiocatorePartita(psw).size() > 23) {
                    statoObiettivo = true;
                } else if (listaTerritoriColore(colore).isEmpty() && getLineGiocatore(colore) != -1) {
                    statoObiettivo = true;
                }
                break;
            case CONTINENTI:
                statoObiettivo = true;
                for (int i = 0, k = 0; k < 2 && i < TipoContinente.values().length; i++) {
                    if (obiettivo.getObiettivo().contains(TipoContinente.values()[i].name())) {
                        ArrayList<TerritorioDettagliato> tContinente = listaTerritoriDettagliatiContinente(TipoContinente.values()[i]);
                        k++;
                        boolean territorioTrovato = false;
                        for (int j = 0; !territorioTrovato && j < territoriGiocatore.size(); j++) {
                            for (int l = 0; l < tContinente.size(); l++) {
                                if (tContinente.get(l).equals((Territorio) territoriGiocatore.get(j))) {
                                    territorioTrovato = true;
                                }
                            }
                            if (!territorioTrovato) {
                                statoObiettivo = false;
                                break;
                            }
                        }
                    }
                }
                break;
            case NUMERO_TERRITORI:
                int nTerritoriValidi = 0;
                if (obiettivo.getObiettivo().contains("18") && territoriGiocatore.size() > 17) {
                    for (int i = 0; i < territoriGiocatore.size(); i++) {
                        if (territoriGiocatore.get(i).getNumeroArmate() > 1) {
                            nTerritoriValidi++;
                        }
                    }
                    if (nTerritoriValidi > 17) {
                        statoObiettivo = true;
                    }
                } else if (obiettivo.getObiettivo().contains("24") && territoriGiocatore.size() > 23) {
                    statoObiettivo = true;
                }
                break;
            case TORNEO:
                statoObiettivo = true;
                ArrayList<String> territoriDaConquistare = Territorio.splitTerritori(obiettivo.getObiettivo());
                for (int i = 0; i < territoriDaConquistare.size(); i++) {
                    boolean territorioTrovato = false;
                    for (int j = 0; j < territoriGiocatore.size(); j++) {
                        if (territoriDaConquistare.get(i).equals(territoriGiocatore.get(j).getNome())) {
                            territorioTrovato = true;
                        }
                    }
                    if (!territorioTrovato) {
                        statoObiettivo = false;
                        break;
                    }
                }
        }
        if (statoObiettivo) {
            throw new ControlloObiettivo(getGiocatore(psw).getNome(), obiettivo.getObiettivo());
        }
    }


    @Override
    public void setTerritoriTruppePerFase(String psw, String territorioOrigine, String territorioDestinazione, int truppe) {
        //metodo da implementare per necessità, non servirebbe
    }

    @Override
    public String stampaFase(String psw) throws IOException, RisikoExceptions {
       return "";//metodo da implementare per necessità, non servirebbe
    }

}
