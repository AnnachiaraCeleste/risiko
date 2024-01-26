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
public class IOObjectFileObiettivoPartita extends IOObjectFile<ObiettivoPartita> {
    
    public IOObjectFileObiettivoPartita(String fileName, String separator) {
        super(fileName, separator);
    }
    
    @Override
    public String serialize(ObiettivoPartita o) {
        return o.getObiettivo() + separator +o.getTipoObiettivo()+separator+o.getPassword()+separator+ o.getStato();
    }
    
    @Override
    public ObiettivoPartita deserialize(String[] attributes) {
        return new ObiettivoPartita(attributes[0], TipoObiettivo.valueOf(attributes[1]),attributes[2],Boolean.parseBoolean(attributes[3]));
    }
    
}
