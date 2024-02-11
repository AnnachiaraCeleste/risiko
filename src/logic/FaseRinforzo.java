/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import enums.TipoArma;
import enums.TipoContinente;
import static enums.TipoContinente.NORD_AMERICA;
import static enums.TipoContinente.SUD_AMERICA;
import exceptions.CartaNonRegistrataPSWNome;
import exceptions.GiocatoreNonRegistrato;
import exceptions.NMaxTruppeRinforzoRaggiunto;
import exceptions.RisikoExceptions;
import exceptions.TerritorioNonPosseduto;
import exceptions.TerritorioNonRegistrato;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CarteArmiPartita;
import model.Giocatore;
import model.TerritorioDettagliato;
import model.TerritorioPartita;

/**
 *
 * @author Annachiara
 */
public class FaseRinforzo extends GestionePartita {

    /**
     * SET TERRITORI TRUPPE PER FASE
     *
     * @param psw del giocatore che svolge l'azione
     * @param territorioPartenza territorio su cui si vogliono aggiungere le
     * truppe
     * @param territorioDestinazione null
     * @param numeroArmateDaSpostare armate coinvolte nella fase
     */
    @Override
    public void setTerritoriTruppePerFase(String psw, String territorioPartenza, String territorioDestinazione, int numeroArmateDaSpostare) {
        try {
            if (!getTerritorioPartita(territorioPartenza).getPasswordGiocatore().equals(psw)) {
                throw new TerritorioNonPosseduto(territorioPartenza);
            }
            if (numeroArmateDaSpostare > getGiocatore(psw).getRinforziTurno()) {
                throw new NMaxTruppeRinforzoRaggiunto(numeroArmateDaSpostare);
            }
            super.setTerritoriTruppePerFase(psw, territorioPartenza, " ", numeroArmateDaSpostare);
        } catch (IOException | TerritorioNonRegistrato | TerritorioNonPosseduto | GiocatoreNonRegistrato | NMaxTruppeRinforzoRaggiunto ex) {
            Logger.getLogger(FaseRinforzo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * FASE RINFORZO: metodo che aggiunge un numero specifico di rinforzi a un
     * territorio specifico e riduce il numero di rinforzi disponibili per il
     * giocatore.
     *
     * @param psw del giocatore da ricercare
     * @throws IOException
     * @throws RisikoExceptions
     */
    @Override
    public void eseguiFase(String psw) throws IOException, RisikoExceptions {
        stampaFase(psw);
        ArrayList<TerritorioPartita> territori = iofTerritorioPartita.loadData();
        ArrayList<Giocatore> giocatori = iofGiocatorePartita.loadData();
        Giocatore g = getGiocatore(psw);
        territori.get(getLineTerritorioPartita(g.getTerritorioOrigine())).setNumeroArmate(g.getTruppe() + getTerritorioPartita(g.getTerritorioOrigine()).getNumeroArmate());
        giocatori.get(getLineGiocatore(psw)).setRinforziTurno(getGiocatore(psw).getRinforziTurno() - g.getTruppe());
        iofTerritorioPartita.saveData(territori);
        iofGiocatorePartita.saveData(giocatori);
    }

    /**
     * CALCOLA TRUPPE RINFORZO: metodo che calcola il numero di rinforzi totale
     * che un giocatore riceverà nella fase di rinforzo.Il calcolo dipende dal
     * numero di territori occupati, territori occupati, i continenti
     * conquistati e le combinazioni di carte.
     *
     * @param psw del giocatore da ricercare
     * @throws IOException
     * @throws CartaNonRegistrataPSWNome
     * @throws GiocatoreNonRegistrato
     */
    public void calcolaTruppeRinforzo(String psw) throws IOException, CartaNonRegistrataPSWNome, GiocatoreNonRegistrato {
        // Calcola il numero di armate di rinforzo
        ArrayList<TerritorioPartita> territoriOccupati = listaTerritoriGiocatorePartita(psw);
        int rinforziTerritori = territoriOccupati.size() / 3;
        int rinforziContinenti = 0;
        for (int i = 0; i < 6; i++) {
            ArrayList<TerritorioDettagliato> tContinente = listaTerritoriDettagliatiContinente(TipoContinente.values()[i]);
            boolean continenteConquistato = true;
            for (int j = 0; j < tContinente.size(); j++) {
                boolean territorioOccupato = false;
                for (int k = 0; k < territoriOccupati.size(); k++) {
                    if (territoriOccupati.get(k).equals(tContinente.get(j))) {
                        territorioOccupato = true;
                        break;
                    }
                }
                if (!territorioOccupato) {
                    continenteConquistato = false;
                    break;
                }
            }
            //se il continente è tutto conquistato gli assegno le armate continenete
            if (continenteConquistato) {
                switch (TipoContinente.values()[i]) {
                    case OCEANIA,SUD_AMERICA:
                        rinforziContinenti += TipoContinente.OCEANIA.getNumeroArmateAssegnate();
                        break;
                    case AFRICA:
                        rinforziContinenti += TipoContinente.AFRICA.getNumeroArmateAssegnate();
                        break;
                    case EUROPA, NORD_AMERICA:
                        rinforziContinenti += TipoContinente.EUROPA.getNumeroArmateAssegnate();
                        break;
                    case ASIA:
                        rinforziContinenti += TipoContinente.ASIA.getNumeroArmateAssegnate();
                        break;
                }
            }
        }
        int rinforziCarte = calcolaRinforziCarte(psw);
        ArrayList<Giocatore> giocatori = iofGiocatorePartita.loadData();
        giocatori.get(getLineGiocatore(psw)).setRinforziTurno(rinforziTerritori + rinforziContinenti + rinforziCarte);
        iofGiocatorePartita.saveData(giocatori);
    }

    /**
     * CALCOLA RINFORZI CARTE: metodo che calcola i rinforzi basati sulle
     * combinazioni di carte possedute dal giocatore. (combinazioni di
     * artiglieria, fanteria, cavalleria e carte territorio; Le carte utilizzate
     * per ottenere rinforzi vengono poi rimosse)
     *
     * @param psw del giocatore da ricercare
     * @return il numero di rinforzi ottenuti
     * @throws IOException
     */
    private int calcolaRinforziCarte(String psw) throws IOException, CartaNonRegistrataPSWNome {
        // Calcola i rinforzi extra per le combinazioni di carte
        int rinforziCarte = 0;
        ArrayList<CarteArmiPartita> carteGiocatore = listaCarteGiocatorePartita(psw);
        // Calcola le combinazioni valide
        int numeroArtiglieria = contaCartePerTipo(TipoArma.ARTIGLIERIA, psw);
        int numeroFanteria = contaCartePerTipo(TipoArma.FANTERIA, psw);
        int numeroCavalleria = contaCartePerTipo(TipoArma.CAVALLERIA, psw);
        // Aggiungi 2 armate per ciascuna carta territorio posseduta nella mano
        rinforziCarte += contaCartaUgaleTerritorioPosseduto(psw) * 2;
        // Calcola i rinforzi per le combinazioni
        if (numeroArtiglieria >= 3) {
            rinforziCarte += 4;
            rimuoviCarteRinforziUsate(psw, TipoArma.ARTIGLIERIA, 3);
        }
        if (numeroFanteria >= 3) {
            rinforziCarte += 6;
            rimuoviCarteRinforziUsate(psw, TipoArma.FANTERIA, 3);
        }
        if (numeroCavalleria >= 3) {
            rinforziCarte += 8;
            rimuoviCarteRinforziUsate(psw, TipoArma.CAVALLERIA, 3);
        }
        if (numeroArtiglieria >= 1 && numeroFanteria >= 1 && numeroCavalleria >= 1) {
            rinforziCarte += 10;
            rimuoviCarteRinforziUsate(psw, TipoArma.ARTIGLIERIA, 1);
            rimuoviCarteRinforziUsate(psw, TipoArma.FANTERIA, 1);
            rimuoviCarteRinforziUsate(psw, TipoArma.CAVALLERIA, 1);
        }
        return rinforziCarte;
    }

    /**
     * RIMUOVI CARTE RINFORZI USATE: Questo metodo rimuove un numero specificato
     * di carte di un certo tipo utilizzate per ottenere dei rinforzi
     *
     * @param psw del giocatore da ricercare
     * @param a tipo di arma da eliminare
     * @param n numero di carte da rimuovere
     * @throws IOException
     */
    private void rimuoviCarteRinforziUsate(String psw, TipoArma a, int n) throws IOException, CartaNonRegistrataPSWNome {
        for (int i = 0; i < n; i++) {
            removeCartaArmiPartita(psw, a);
        }
    }

    /**
     * CONTA CARTE UGUALE TERRITORIO POSSEDUTO: metodo che conta il numero di
     * carte territorio che un giocatore ha, e che corrispondono a territori da
     * lui posseduti.
     *
     * @param psw del giocatore da ricercare
     * @return numero di carte che soddisfa la condizione spiegate
     * @throws IOException
     */
    private int contaCartaUgaleTerritorioPosseduto(String psw) throws IOException {
        ArrayList<CarteArmiPartita> carteGiocatore = listaCarteGiocatorePartita(psw);
        ArrayList<TerritorioPartita> tgiocatore = listaTerritoriGiocatorePartita(psw);
        int nCarte = 0;
        boolean cartaUgualeTerritorio = false;
        for (int i = 0; !cartaUgualeTerritorio && i < carteGiocatore.size(); i++) {
            for (int j = 0; !cartaUgualeTerritorio && j < tgiocatore.size(); j++) {
                if (carteGiocatore.get(i).equals(tgiocatore.get(j))) {
                    nCarte++;
                    cartaUgualeTerritorio = true;
                }
            }
        }
        return nCarte;
    }

    /**
     * CONTA CARTE PER TIPO: metodo che conta il numero di carte per tipoCarta
     * possedute da un giocatore
     *
     * @param t elenco tipoCarta da ricercare
     * @param psw del giocatore da ricercare
     * @return numero di carte del tipo richiesto da un certo giocatore
     * @throws IOException
     */
    private int contaCartePerTipo(TipoArma t, String psw) throws IOException {
        ArrayList<CarteArmiPartita> carteGiocatore = listaCarteGiocatorePartita(psw);
        int nTipoCarte = 0;
        for (int i = 0; i < carteGiocatore.size(); i++) {
            if (carteGiocatore.get(i).getArma().equals(t)) {
                nTipoCarte++;
            }
        }
        return nTipoCarte;
    }

    @Override
    public void stampaFase(String psw) throws IOException, RisikoExceptions {
        Giocatore g = getGiocatore(psw);
        System.out.println("FASE SPOSTAMENTO: " + g.getTerritorioOrigine() + " +" + g.getTruppe());
    }

}
