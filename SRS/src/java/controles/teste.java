/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.Reserva;
import excecoes.RNEException;
import excecoes.SNEException;
import excecoes.UNEException;
import java.awt.TextField;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alanf
 */
public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReservaControle r = new ReservaControle();
        try {
            List<Reserva> lista = r.listaReservas();
            if(!lista.isEmpty()){
                lista.stream().forEach((aux) -> {
                    System.out.println(aux.getData().getHora_inicial()+"-"+aux.getData().getHora_final()
                            +"|"+aux.getData().getDataFormatada()+"|"+aux.getUsuario().getNome()+"|"
                            +aux.getSala().getNome()+"|"+aux.getSala().getBloco());
                });
            }
        } catch (RNEException | SNEException ex) {
            Logger.getLogger(teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
