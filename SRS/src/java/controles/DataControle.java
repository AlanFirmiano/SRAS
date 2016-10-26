/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import dao.DataDAO;
import entidades.Data;
import excecoes.DJCException;
import excecoes.DNEException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alanf
 */
public class DataControle {
    private DataDAO dao = new DataDAO();
    
    public void salvarData(Data data)throws DJCException{
        try{
            this.buscarDataPorId(data);
            throw new DJCException();
        }catch(DNEException e){
            dao.salvarData(data);
        }
        
    }
    
    public Data buscarDataPorId(Data data)throws DNEException{
        Data aux = dao.getDataPorId(data.getId());
        if(aux!=null)
            return aux;
        else
            throw new DNEException();
    }
   
    public Data buscarDataPorTudo(Data data)throws DNEException{
        Data aux = dao.getDataPorTudo(data);
        if(aux!=null)
            return aux;
        else
            throw new DNEException();
    } 
    public void atualizarData(Data data){
        try {
            this.buscarDataPorId(data);
            dao.atualizarData(data);
        } catch (DNEException ex) {
            
        }   
    }
    
    public void removerData(Data data)throws DNEException{
        this.buscarDataPorId(data);
        dao.removerDataPorId(data.getId());
    }

}
