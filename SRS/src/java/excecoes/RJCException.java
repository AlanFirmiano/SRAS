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
public class RJCException extends Exception {
    public RJCException(){
        super("Reserva já cadastrada!");
    }
}
