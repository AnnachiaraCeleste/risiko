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
public class IOObjectFileObiettivo extends IOObjectFile<Obiettivo> {

    public IOObjectFileObiettivo(String fileName, String separator) {
        super(fileName, separator);
    }

    @Override
    public String serialize(Obiettivo o) {
       return o.getObiettivo()+separator+o.getTipoObiettivo();
    }

    @Override
    public Obiettivo deserialize(String[] attributes) {
        return new Obiettivo(attributes[0],TipoObiettivo.valueOf(attributes[1]));
    }

}
