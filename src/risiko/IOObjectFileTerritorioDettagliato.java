/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import iofiles.IOObjectFile;
import java.util.ArrayList;

/**
 *
 * @author Annachiara
 */
public class IOObjectFileTerritorioDettagliato extends IOObjectFile<TerritorioDettagliato> {

    public IOObjectFileTerritorioDettagliato(String fileName, String separator) {
        super(fileName, separator);
    }

    public ArrayList<String> splitConfini(String attributoConfini) {
        String[] attributes = attributoConfini.split(",");
        ArrayList<String> listaElementi = new ArrayList<>();
        for (int i = 0; i < attributes.length; i++) {
            listaElementi.add(attributes[i]);
        }
        return listaElementi;
    }

    @Override
    public String serialize(TerritorioDettagliato t) {
        return t.getNome() + separator + t.getSequenzaConfini() + separator + t.getContinente() + separator + t.getArma();
    }

    @Override
    public TerritorioDettagliato deserialize(String[] attributes) {
        return new TerritorioDettagliato(attributes[0], splitConfini(attributes[1]),
                TipoContinente.valueOf(attributes[2]), TipoArma.valueOf(attributes[3]));
    }
}
