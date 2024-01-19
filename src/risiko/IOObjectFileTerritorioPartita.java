/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import iofiles.IOObjectFile;

/**
 *
 * @author Annachiara
 */
public class IOObjectFileTerritorioPartita extends IOObjectFile<TerritorioPartita> {

    public IOObjectFileTerritorioPartita(String fileName, String separator) {
        super(fileName, separator);
    }

    @Override
    public String serialize(TerritorioPartita t) {
        return t.getNome() + separator + t.getArma() + separator + t.getNomeGiocatore() + separator + t.getNumeroArmate();
    }

    @Override
    public TerritorioPartita deserialize(String[] attributes) {
        return new TerritorioPartita(attributes[0], TipoArma.valueOf(attributes[1]), attributes[2], Integer.getInteger(attributes[3]));
    }

}
