/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import dao.SalaDAO;
import entidades.Sala;
import excecoes.SJCException;
import excecoes.SNEException;
import java.util.ArrayList;

/**
 *
 * @author alanf
 */
public class SalaControle {
    private SalaDAO dao = new SalaDAO();
    
    public void salvarSala(Sala sala)throws SJCException{
        try{
            this.buscarSalaPorNomeBloco(sala.getNome(),sala.getBloco());
            throw new SJCException();
        }catch(SNEException e){
            dao.salvarSala(sala);
        }
    }
    
    public Sala buscarSalaPorNomeBloco(String nome,String bloco)throws SNEException{
        Sala sala = dao.getSalaPorNomeBloco(nome,bloco);
        if(sala!=null)
            return sala;
        else
            throw new SNEException();
    }
    
    public Sala buscaSalaPorId(int id)throws SNEException{
        Sala sala = dao.getSalaPorId(id);
        if(sala!=null)
            return sala;
        else
            throw new SNEException();
    }
    
    public void atualizarSala(Sala sala)throws SNEException, SJCException{
        Sala aux = this.buscaSalaPorId(sala.getId());
        try{
            this.buscarSalaPorNomeBloco(sala.getNome(),sala.getBloco());
            if(aux.getNome().equals(sala.getNome()))
                dao.atualizarSala(sala);
            else
                throw new SJCException();
        }catch(SNEException e){
            dao.atualizarSala(sala);
        }
    }
    
    public void removerSala(int id)throws SNEException{
        this.buscaSalaPorId(id);
        dao.removerSalaPorId(id);
    }
    public ArrayList<Sala> listaSalas(){
        ArrayList<Sala> lista = dao.listaSalas();
        return lista;
    }
}
