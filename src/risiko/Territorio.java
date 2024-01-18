/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

/**
 *
 * @author Annachiara
 */
public class Territorio {
    private final String nome;
    private final TipoArma arma;

    public Territorio(String nome,TipoArma tipoArma) {
        this.nome = nome;
        this.arma=tipoArma;
    }

    public String getNome() {
        return nome;
    }

    public TipoArma getArma() {
        return arma;
    }
    
    
}
