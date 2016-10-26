/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import dao.EsperaDAO;
import entidades.Espera;
import excecoes.DJCException;
import excecoes.DNEException;
import excecoes.ENEException;
import excecoes.SJCException;
import excecoes.SNEException;
import excecoes.UNEException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alanf
 */
public class EsperaControle {
    EsperaDAO daoR = new EsperaDAO();
    DataControle conD = new DataControle();
    UsuarioControle conU = new UsuarioControle();
    SalaControle conS = new SalaControle();
    
    public void salvarEspera(Espera espera)throws ENEException, UNEException{
        espera.setUsuario(conU.buscarUsuarioPorLogin(espera.getUsuario().getLogin()));
        try{
            espera.setData(conD.buscarDataPorId(espera.getData()));
        }catch(DNEException e){
            try {
                conD.salvarData(espera.getData());
                espera.setData(conD.buscarDataPorId(espera.getData()));
            } catch (DJCException | DNEException ex) {
                
            }
        }
        try{
            espera.setSala(conS.buscarSalaPorNomeBloco(espera.getSala().getNome(), espera.getSala().getBloco()));
        }catch(SNEException e){
            try {
                conS.salvarSala(espera.getSala());
                espera.setSala(conS.buscarSalaPorNomeBloco(espera.getSala().getNome(), espera.getSala().getBloco()));
            } catch (SJCException | SNEException ex) {
                
            }
        }
        daoR.salvarEspera(espera);
    }
    
    public void removerEspera(int id)throws ENEException{
        this.buscarEsperaPorId(id);
        daoR.removerEsperaPorId(id);
    }
    
    public void atualizarEspera(Espera espera)throws ENEException{
        this.buscarEsperaPorId(espera.getId());
        daoR.atualizarEspera(espera);
    }
    
    public Espera buscarEsperaPorId(int id)throws ENEException{
        Espera espera = daoR.buscarEsperaPorId(id);
        if(espera!=null)
            return espera;       
        else
            throw new ENEException();
    }
    
    public ArrayList<Espera> listaEsperas()throws ENEException, SNEException{
        ArrayList<Espera> lista = daoR.listaEsperas();
        if(lista.size()>0){
            for(Espera aux:lista){
                aux.setSala(conS.buscaSalaPorId(aux.getSala().getId()));
                try {
                    aux.setUsuario(conU.buscaUsuarioPorId(aux.getUsuario().getId()));
                    aux.setData(conD.buscarDataPorId(aux.getData()));
                
                } catch (UNEException ex) {
                    Logger.getLogger(EsperaControle.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DNEException ex) {
                    Logger.getLogger(EsperaControle.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return lista;
        }else
            throw new ENEException();
    }
    public ArrayList<Espera> listaEsperaPorSala(int id)throws DNEException, UNEException, SNEException{
        ArrayList<Espera> lista = daoR.listaEsperasPorSala(id);
        if(lista.size()>0){
            for(Espera aux:lista){
                aux.setData(conD.buscarDataPorId(aux.getData()));
                aux.setUsuario(conU.buscaUsuarioPorId(aux.getUsuario().getId()));
                aux.setSala(conS.buscaSalaPorId(aux.getSala().getId()));
            }
            return lista;
        }else
            throw new DNEException();
    }
    
    public void RemoverEspera(Espera espera)throws DNEException{
        try {
            this.buscarEsperaPorId(espera.getId());
            daoR.removerEsperaPorId(espera.getId());
        } catch (ENEException ex) {
            Logger.getLogger(EsperaControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
