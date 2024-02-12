/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import exceptions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author Annachiara
 */
public class FaseAttacco extends GestionePartita {

    /**
     * SET TERRITORI TRUPPE PER FASE: metodo che esegue il set dei territori e
     * delle truppe coinvolte in questa fase e che controlla che i valori siano
     * accettabili per la fase di attacco
     *
     * @param psw specifica
     * @param territorioAttacco dal quale parte l'azione
     * @param territorioDifesa che subisce l'azione
     * @param truppe (sempre = 0)
     */
    @Override
    public void setTerritoriTruppePerFase(String psw, String territorioAttacco, String territorioDifesa, int truppe) {
        try {
            controlloFaseAttacco(psw, territorioAttacco, territorioDifesa);
            super.setTerritoriTruppePerFase(psw, territorioAttacco, territorioDifesa, 0);
        } catch (IOException | RisikoExceptions ex) {
            Logger.getLogger(FaseAttacco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * ESEGUI FASE: metodo per l'attacco di un territorio: vengono lanciati i
     * dadi per ogni giocatore e vengono confrontati in ordine decrescente, se
     * il giocatore conquista almento un territorio, alla fine della fase
     * pescherà una carta
     *
     * @param psw
     * @throws IOException
     * @throws AttaccoFallito
     * @throws SpostamentoFallito
     * @throws GiocatoreNonRegistrato
     * @throws ObiettivoNonRegistrato
     * @throws TerritorioNonRegistrato
     * @throws TerritorioNonPosseduto
     * @throws TerritorioNonConfinante
     * @throws NMinTruppeAttaccoRaggiunto
     * @throws CartaGiaRegistrata
     * @throws CartaNonRegistrataTerritorio
     */
    @Override
    public void eseguiFase(String psw) throws IOException, AttaccoFallito, SpostamentoFallito,
            GiocatoreNonRegistrato, ObiettivoNonRegistrato, TerritorioNonRegistrato,
            TerritorioNonPosseduto, TerritorioNonConfinante, NMinTruppeAttaccoRaggiunto,
            CartaGiaRegistrata, CartaNonRegistrataTerritorio, RisikoExceptions {
        int armateAttaccante, armateDifensore;
        Giocatore g = getGiocatore(psw);
        int idx_attacc = getLineTerritorioPartita(g.getTerritorioOrigine());
        TerritorioPartita tAttacc = iofTerritorioPartita.get(getLineTerritorioPartita(g.getTerritorioOrigine()));
        int idx_diff = getLineTerritorioPartita(g.getTerritorioDestinazione());
        TerritorioPartita tDif = iofTerritorioPartita.get(getLineTerritorioPartita(g.getTerritorioDestinazione()));
        if (tAttacc.getNumeroArmate() > 3) {
            armateAttaccante = 3;
        } else if (tAttacc.getNumeroArmate() == 3) {
            armateAttaccante = 2;
        } else {
            armateAttaccante = 1;
        }
        stampaFase(psw);
        int[] attaccanteDadi = lancioDadi(armateAttaccante);
        ordinamentoDadi(attaccanteDadi);
        stampaDadi("Attaccante", attaccanteDadi);//stampa dei dadi lanciati
        if (tDif.getNumeroArmate() > 3) {
            armateDifensore = 3;
        } else {
            armateDifensore = tDif.getNumeroArmate();
        }
        // Difensore dichiara il numero di dadi
        int[] difensoreDadi = lancioDadi(armateDifensore);
        ordinamentoDadi(difensoreDadi);
        stampaDadi("Difensore", difensoreDadi);//stampa dei dadi lanciati
        // Confronto dei punteggi dei dadi
        int confronti = Math.min(attaccanteDadi.length, difensoreDadi.length);//math min per gestire il confronto tra i dadi
        //controlloTruppeTerritorio: se true si blocca il ciclo senza controllare tutti i dadi
        for (int i = 0; !controlloTruppeTerritorio(g.getTerritorioOrigine(), g.getTerritorioDestinazione()) && i < confronti; i++) {
            if (attaccanteDadi[i] > difensoreDadi[i]) {
                tDif.setNumeroArmate(tDif.getNumeroArmate() - 1);
                iofTerritorioPartita.set(idx_diff, tDif);
            } else if (attaccanteDadi[i] <= difensoreDadi[i]) {
                tAttacc.setNumeroArmate(tAttacc.getNumeroArmate() - 1);
                iofTerritorioPartita.set(idx_attacc, tAttacc);
            }
        }
        pescaCarta(psw);
    }

    /**
     * CONTROLLO FASE DI ATTACCO: metodo per controllare la fase di attacco da
     * un territorio di un giocatore ad un'altro
     *
     * @param psw identificativa del giocatore che effettua l'attacco
     * @param territorioAttaccante territorio di chi attacca
     * @param territorioDifensore territorio che deve difendersi
     * @throws IOException
     * @throws AttaccoFallito
     * @throws TerritorioNonRegistrato
     * @throws TerritorioNonPosseduto
     * @throws TerritorioNonConfinante
     * @throws NMinTruppeAttaccoRaggiunto
     */
    public void controlloFaseAttacco(String psw, String territorioAttaccante, String territorioDifensore) throws IOException, AttaccoFallito, TerritorioNonRegistrato, TerritorioNonPosseduto, TerritorioNonConfinante, NMinTruppeAttaccoRaggiunto {
        if (getLineTerritorioDettagliato(territorioAttaccante) == -1 || getLineTerritorioDettagliato(territorioDifensore) == -1) {
            throw new TerritorioNonRegistrato(territorioAttaccante + " " + territorioDifensore);
        }
        boolean territorioPosseduto = false;
        for (int i = 0; i < listaTerritoriGiocatorePartita(psw).size(); i++) {
            if (!listaTerritoriGiocatorePartita(psw).get(i).equals(getTerritorioPartita(territorioAttaccante))) {
                territorioPosseduto = true;
                break;
            }
        }
        if (!territorioPosseduto) {
            throw new TerritorioNonPosseduto(territorioAttaccante);
        }
        if (!Territorio.splitTerritori(getTerritorioDettagliato(territorioAttaccante).getSequenzaConfini()).contains(territorioDifensore)) {
            throw new TerritorioNonConfinante();
        } else if (getTerritorioPartita(territorioAttaccante).getNumeroArmate() == 1) {
            throw new NMinTruppeAttaccoRaggiunto();
        }
        for (int i = 0; i < listaTerritoriGiocatorePartita(psw).size(); i++) {
            if (listaTerritoriGiocatorePartita(psw).get(i).equals(getTerritorioPartita(territorioDifensore))) {
                throw new AttaccoFallito(territorioAttaccante, getTerritorioDettagliato(territorioAttaccante).getSequenzaConfini());
            }
        }
    }

    /**
     * CONTROLLO TRUPPE TERRITORIO: metodo che controlla il numero di truppe
     * presenti su un territorio appena attaccato; se sono pari a 0 allora il
     * giocatore ha conquistato il territorio ed è necessario eseguire un
     * aggiornamento del territorio che viene assegnato al giocatore che ha
     * vinto
     *
     * @param territorioAttaccante territorio di partenza dal quale si vuole
     * effettuare l'attacco
     * @param territorioDifensore territorio che subisce l'attacco
     * @return true se il territorio è stato conquistato
     * @throws IOException
     */
    private boolean controlloTruppeTerritorio(String territorioAttaccante, String territorioDifensore)
            throws IOException, SpostamentoFallito, GiocatoreNonRegistrato, ObiettivoNonRegistrato, TerritorioNonRegistrato, RisikoExceptions {
        //indice attaccante
        int idx_attacc = getLineTerritorioPartita(territorioAttaccante);
        TerritorioPartita tA = iofTerritorioPartita.get(idx_attacc);
        //indice difensore
        int idx_dif = getLineTerritorioPartita(territorioDifensore);
        TerritorioPartita tD = iofTerritorioPartita.get(idx_dif);
        boolean territorioConquistato = false;
        //se nArmate>3 sposto 3 carri armati
        //se il numero di armate del difensore =0 e il territorio è stato conquist devo spostare alcune truppe
        if (tD.getNumeroArmate() == 0) {
            territorioConquistato = true;
            //cambio la password del giocatore
            tD.setPasswordGiocatore(tA.getPasswordGiocatore());
            //aumento il numero di territori conquistati dal giocatore in questo turno
            Giocatore g = iofGiocatorePartita.get(idx_attacc);
            g.setNTerritoriConquistatiPerTurno(g.getNTerritoriConquistatiPerTurno() + 1);
            iofGiocatorePartita.set(idx_attacc, g);
            int nArmateVincenti;//numero di armate da assagnare al territorio
            if (tA.getNumeroArmate() > 3) {
                nArmateVincenti = 3;
            } else if (tA.getNumeroArmate() == 3) {
                nArmateVincenti = 2;
            } else {
                nArmateVincenti = 1;
            }
            iofTerritorioPartita.set(idx_attacc, tA);
            iofTerritorioPartita.set(idx_dif, tD);
            FaseSpostamento faseSpostamento = new FaseSpostamento();
            faseSpostamento.setTerritoriTruppePerFase(g.getPassword(), territorioAttaccante, territorioDifensore, nArmateVincenti);
            faseSpostamento.eseguiFase(g.getPassword());

        }
        return territorioConquistato;
    }

    /**
     * PESCA CARTA: metodo che assegna ad un giocatore una carta arma utile per
     * i rinforzi
     *
     * @param psw del giocatore
     * @throws IOException
     * @throws exceptions.GiocatoreNonRegistrato
     * @throws exceptions.CartaGiaRegistrata
     * @throws exceptions.CartaNonRegistrataTerritorio
     */
    public void pescaCarta(String psw) throws IOException, GiocatoreNonRegistrato, CartaGiaRegistrata, CartaNonRegistrataTerritorio, TerritorioNonRegistrato {
        if (getGiocatore(psw).getNTerritoriConquistatiPerTurno() > 0) {
            Giocatore g = getGiocatore(psw);
            boolean trovato;
            int idxCarta;
            do {
                idxCarta = (int) (Math.random() * (42));
                trovato = getLineCarteArmiPartita(iofCarteArmiPartita.get(idxCarta).getNome()) == -1;
            } while (!trovato);
            TerritorioDettagliato td = getTerritorioDettagliato(iofTerritorioDettagliato.get(idxCarta).getNome());
            addCartaArmiPartita(new CarteArmiPartita(td.getNome(), td.getArma(), psw));
            g.setNTerritoriConquistatiPerTurno(0);
        }
    }

    /**
     * ORDINAMENTO DADI: insertion sort dei dadi in ordine decrescente
     *
     * @param a array con i valori del lancio dei dadi
     */
    private void ordinamentoDadi(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j - 1] < a[j]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }

    }

    /**
     * LANCIO DADI: metodo per generare un numero preciso di interi
     *
     * @param numDadi quantità di numeri da generare
     * @return array di int
     */
    private int[] lancioDadi(int numDadi) {
        int[] risultati = new int[numDadi];
        for (int i = 0; i < numDadi; i++) {
            risultati[i] = (int) (Math.random() * (6)) + 1; // Dado a 6 facce
        }
        return risultati;
    }

    /**
     * STAMPA DADI: metodo per stampare a console i dadi lanciati
     *
     * @param giocatore che effettua il lancio
     * @param dadi array di interi
     */
    public void stampaDadi(String giocatore, int[] dadi) {
        System.out.print(giocatore + " ha lanciato: ");
        for (int i = 0; i < dadi.length; i++) {
            System.out.print(dadi[i] + "  ");
        }
        System.out.println();
    }

    @Override
    public void stampaFase(String psw) throws IOException, GiocatoreNonRegistrato {
        Giocatore g = getGiocatore(psw);
        System.out.println("FASE ATTACCO: " + g.getTerritorioOrigine() + " --vs-- " + g.getTerritorioDestinazione());
    }

}
