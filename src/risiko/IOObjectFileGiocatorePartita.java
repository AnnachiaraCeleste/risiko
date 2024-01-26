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
public class IOObjectFileGiocatorePartita extends IOObjectFile<Giocatore> {

    public IOObjectFileGiocatorePartita(String fileName, String separator) {
        super(fileName, separator);
    }

    @Override
    public String serialize(Giocatore g) {
        return g.getNome() + separator + g.getColore() + separator + g.getPassword(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Giocatore deserialize(String[] attributes) {
        return new Giocatore(attributes[0], TipoColore.valueOf(attributes[1]), attributes[2]);
    }
}
