/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controles.UsuarioControle;
import entidades.Reserva;
import controles.ReservaControle;
import entidades.Usuario;
import excecoes.DNEException;
import excecoes.RNEException;
import excecoes.SNEException;
import excecoes.UNEException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author alanf
 */
@ManagedBean
@SessionScoped
@ViewScoped
public class ReservaBean {
    //sala
    public String nome;
    public String bloco;
    //data
    public Date data;
    public String hora_inicial;
    public String hora_final;
    public String msg;
    
    public List<Reserva> listaReserva;
    public Reserva reserva;
    public boolean edit;
    public boolean edit2;
    public String bnc;
    public String bedit;
    public List<Reserva> listaReservaPorSala;
    
    
    public ReservaBean() {
    
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Reserva> getListaReserva() {
        return listaReserva;
    }

    public void setListaReserva(List<Reserva> listaReserva) {
        this.listaReserva = listaReserva;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public List<Reserva> getListaReservaPorSala() {
        return listaReservaPorSala;
    }

    public void setListaReservaPorSala(List<Reserva> listaReservaPorSala) {
        this.listaReservaPorSala = listaReservaPorSala;
    }
    
    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isEdit2() {
        return edit2;
    }

    public void setEdit2(boolean edit2) {
        this.edit2 = edit2;
    }

    public String getBnc() {
        return bnc;
    }

    public void setBnc(String bnc) {
        this.bnc = bnc;
    }

    public String getBedit() {
        return bedit;
    }

    public void setBedit(String bedit) {
        this.bedit = bedit;
    }
    
    public String cadastrarReserva() throws ParseException{
        Reserva aux = new Reserva();
        
        aux.getData().setData(data);
        aux.getData().setHora_inicial(hora_inicial);
        aux.getData().setHora_final(hora_final);
        aux.getSala().setNome(nome);
        aux.getSala().setBloco(bloco);
        
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = (HttpSession) request.getSession();
        String login = (String) session.getAttribute("Login_Usuario");
        try {
            aux.setUsuario(new UsuarioControle().buscarUsuarioPorLogin(login));
        } catch (UNEException ex) {
            Logger.getLogger(ReservaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new ReservaControle().salvarReserva(aux);
        
        return "indexlog";
    }
    
    public String telaDetalhesReserva(){
        setEdit(true);
        setEdit2(true);
        setBnc("Encerrar");
        setBedit("Editar");
        
        return "indexlog";
    }
    
    public void butaoEdit(){
        
    }
    
    public String butaoEncerrar() throws DNEException{
        if(getBnc().equals("Encerrar")){
            setBnc("Salvar");
            setEdit2(false);
        return null;
        }else{
            new ReservaControle().RemoverReserva(reserva);
            return "indexlog";
        }
    }
    
    public String butaoExcuir() throws RNEException{
        new ReservaControle().removerReserva(reserva.getId());
        return "indexlog";
    }
    public void listaReserva(){
        try {
            this.listaReserva = new ReservaControle().listaReservas();
        } catch (RNEException | SNEException ex) {
            Logger.getLogger(ReservaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}
