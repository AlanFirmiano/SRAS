/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controles.SalaControle;
import controles.UsuarioControle;
import entidades.Reserva;
import entidades.Sala;
import entidades.Usuario;
import excecoes.SJCException;
import excecoes.SNEException;
import excecoes.UNEException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alanf
 */
@ManagedBean
@SessionScoped
public class SalaBean {
    
    public String nome;
    public String bloco;
    public String msg;
    public Sala sala;
    public boolean edit;
    public List<Sala> listaSala;
    public SalaBean(){
        
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }    
    
    public String telaCadastroSala(){
        this.limparCampus();
        return "cadastrarsala";
    }
    
    public String cadastrarSala(){
        if((nome==null || nome.equals("") || nome.contains("  "))||
        (bloco==null || bloco.equals("") || bloco.contains("  "))){
            this.setMsg("Preencha corretamente todos os campos!!!");
            return null;
        }
        Sala sala = new Sala();
        sala.setNome(nome);
        sala.setBloco(bloco);
        SalaControle controle = new SalaControle();
        
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = (HttpSession) request.getSession();
        String login = (String) session.getAttribute("Login_Usuario");
        try {
            Usuario aux = (new UsuarioControle().buscarUsuarioPorLogin(login));
            if(login.equals("Administrador"))
                try{
                    controle.salvarSala(sala);
                    this.limparCampus();
                    return "indexlogadm";
                }catch(SJCException e){
                    this.setMsg(e.getMessage());
                    return null;
                }
        } catch (UNEException ex) {
            Logger.getLogger(ReservaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void limparCampus(){
        this.setNome("");
        this.setBloco("");
        this.setSala(null);
    }
    
    public void butaoEditar(){
        if(nome.equals("Editar")){
            nome = "Salvar";
            bloco = "Cancelar";
            edit=false;
        }else{
            try{
                new SalaControle().atualizarSala(sala);
                
            } catch (SNEException ex) {
                
            } catch (SJCException ex) {
                this.setMsg("Sala já cadastrado");
            }
        }
    }
    
    public String butaoExcluir(){
        if(bloco.equals("Excluir")){
            try {
                new SalaControle().removerSala(sala.getId());
            } catch (SNEException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "index";
        }else{
            return null;
        }
    }
    public String detalhesSala(){
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = (HttpSession) request.getSession();
        String login = (String) session.getAttribute("Login_Usuario");
        try{
            Usuario usuario = new UsuarioControle().buscarUsuarioPorLogin(login);
            if(usuario.getNome().equals("Administrador")){
                return "detcasos";
            }
        }catch(UNEException ex){
            
        }
        return null;
    }
    public void listaSala(){
        listaSala = new SalaControle().listaSalas();
    }

    public List<Sala> getListaSala() {
        return listaSala;
    }

    public void setListaSala(List<Sala> listaSala) {
        this.listaSala = listaSala;
    }
}
