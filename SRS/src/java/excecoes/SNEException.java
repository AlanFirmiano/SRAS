/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excecoes;

/**
 *
 * @author alanf
 */
public class SNEException extends Exception{
    public SNEException(){
        super("Sala nao encontrada!");
    }
}
