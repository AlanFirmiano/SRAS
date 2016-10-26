/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import dao.ReservaDAO;
import entidades.Espera;
import entidades.Reserva;
import entidades.Sala;
import excecoes.DJCException;
import excecoes.DNEException;
import excecoes.ENEException;
import excecoes.RNEException;
import excecoes.SJCException;
import excecoes.SNEException;
import excecoes.StrikesException;
import excecoes.UNEException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alanf
 */
public class ReservaControle {
    ReservaDAO daoR = new ReservaDAO();
    EsperaControle conE = new EsperaControle();
    DataControle conD = new DataControle();
    UsuarioControle conU = new UsuarioControle();
    SalaControle conS = new SalaControle();
    
    public void salvarReserva(Reserva reserva){
        try {
            reserva.setUsuario(conU.buscarUsuarioPorLogin(reserva.getUsuario().getLogin()));
        } catch (UNEException ex) {
            Logger.getLogger(ReservaControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            if(reserva.getUsuario().getStrikes()<3)
                throw new StrikesException();
        }catch(StrikesException st){
            try{
                reserva.setData(conD.buscarDataPorId(reserva.getData()));
            }catch(DNEException e){
                try {
                    conD.salvarData(reserva.getData());
                } catch (DJCException ex) {
                    Logger.getLogger(ReservaControle.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    reserva.setData(conD.buscarDataPorTudo(reserva.getData()));
                } catch (DNEException ex) {
                    Logger.getLogger(ReservaControle.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            try{
                reserva.setSala(conS.buscarSalaPorNomeBloco(reserva.getSala().getNome(), reserva.getSala().getBloco()));
            }catch(SNEException e){
                try {
                    conS.salvarSala(reserva.getSala());
                    reserva.setSala(conS.buscarSalaPorNomeBloco(reserva.getSala().getNome(), reserva.getSala().getBloco()));
                } catch (SJCException | SNEException ex) {
                
                }
            }
            daoR.salvarReserva(reserva);
        }
        
            
    }
    
    public void removerReserva(int id)throws RNEException{
        this.buscarReservaPorId(id);
        daoR.removerReservaPorId(id);
    }
    
    public void atualizarReserva(Reserva reserva)throws RNEException{
        this.buscarReservaPorId(reserva.getId());
        daoR.atualizarReserva(reserva);
    }
    
    public Reserva buscarReservaPorId(int id)throws RNEException{
        Reserva reserva = daoR.buscarReservaPorId(id);
        if(reserva!=null)
            return reserva;       
        else
            throw new RNEException();
    }
    
    public List<Reserva> listaReservas()throws RNEException, SNEException{
        List<Reserva> lista = daoR.listaReservas();
        if(lista.size()>0){
            for(Reserva aux:lista){
                aux.setSala(conS.buscaSalaPorId(aux.getSala().getId()));
                try {
                    aux.setUsuario(conU.buscaUsuarioPorId(aux.getUsuario().getId()));
                    aux.setData(conD.buscarDataPorId(aux.getData()));
                
                } catch (UNEException ex) {
                    Logger.getLogger(ReservaControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DNEException ex) {
                    Logger.getLogger(ReservaControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }else
            throw new RNEException();
    }
    public ArrayList<Reserva> listaReservaPorSala(int id)throws DNEException, UNEException, SNEException{
        ArrayList<Reserva> lista = daoR.listaReservasPorSala(id);
        if(lista.size()>0){
            for(Reserva aux:lista){
                aux.setData(conD.buscarDataPorId(aux.getData()));
                aux.setUsuario(conU.buscaUsuarioPorId(aux.getUsuario().getId()));
                aux.setSala(conS.buscaSalaPorId(aux.getSala().getId()));
            }
            return lista;
        }else
            throw new DNEException();
    }
    
    public void RemoverReserva(Reserva reserva)throws DNEException{
        try {
            this.buscarReservaPorId(reserva.getId());
            daoR.removerReservaPorId(reserva.getId());
            try {
                ArrayList<Espera> lista = conE.listaEsperaPorSala(reserva.getSala().getId());
                for(Espera aux : lista){
                    if(aux.getData().getHora_inicial().equals(reserva.getData().getHora_inicial()) &&
                            aux.getData().getData().equals(reserva.getData().getData())){
                        Reserva nres = new Reserva();
                        nres.setUsuario(aux.getUsuario());
                        nres.setSala(aux.getSala());
                        nres.setData(aux.getData());
                        this.salvarReserva(nres);
                        try {
                            conE.removerEspera(aux.getId());
                        } catch (ENEException ex) {
                            Logger.getLogger(ReservaControle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } catch (UNEException | SNEException ex) {
                Logger.getLogger(ReservaControle.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (RNEException ex) {
            Logger.getLogger(ReservaControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
