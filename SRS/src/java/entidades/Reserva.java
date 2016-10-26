/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.SimpleDateFormat;

/**
 *
 * @author alanf
 */
public class Reserva {

    public int id;
    public Usuario usuario;
    public Data data;
    public Sala sala;

    public Reserva(){
        usuario = new Usuario();
        data = new Data();
        sala = new Sala();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
     public String getDataFormatada() {
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

}
