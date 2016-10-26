/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alanf
 */
public class Data {
    public int id;
    public Date data;
    public String hora_inicial;
    public String hora_final;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public String getDataFormatada() {
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }

    public void setDataFormatada(String data) {
        try {
            this.data = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getHora_inicial() {
        return hora_inicial;
    }

    public void setHora_inicial(String hora_inicial) {
        this.hora_inicial = hora_inicial;
    }

    public String getHora_final() {
        return hora_final;
    }
    
    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }
}
