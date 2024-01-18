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
public class IOObjectFileCarteArmiPartita extends IOObjectFile<CarteArmiPartita> {

    public IOObjectFileCarteArmiPartita(String fileName, String separator) {
        super(fileName, separator);
    }

    @Override
    public String serialize(CarteArmiPartita c) {
        return c.getNome() + separator + c.getNomeGiocatore() + separator + c.getArma();
    }

    @Override
    public CarteArmiPartita deserialize(String[] attributes) {
        return new CarteArmiPartita(attributes[1], attributes[2], TipoArma.valueOf(attributes[3]));

    }

}
