/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import controles.UsuarioControle;
import entidades.Usuario;
import excecoes.UJCException;
import excecoes.UNEException;
import javax.swing.JOptionPane;
import util.Criptografia;

/**
 *
 * @author alanf
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {
    
    private String nome;
    private String email;
    private String matricula;
    private String curso;
    private String telefone;
    private String login;
    private String senha;
    private int strikes;
    private String msg;
    private Usuario usuario;
    private boolean edit;
    
    public UsuarioBean() {
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int getStrikes() {
        return strikes;
    }

    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    public String cadastrarUsuario(){
        if((nome==null || nome.equals("") || nome.contains("  "))||
        (matricula==null || matricula.equals("") || matricula.contains("  ")) ||
        (curso==null || curso.equals("") || curso.contains("  ")) ||
        (telefone==null || telefone.equals("") || telefone.contains(" ")) ||        
        (email==null || email.equals("") || email.contains(" ")) ||
        (login==null || login.equals("") || login.contains("  ")) ||
        (senha==null || senha.equals("") || senha.contains(" "))){
            this.setMsg("Preencha corretamente todos os campos!!!");
            return null;
        }else if(!email.contains("@") || !email.contains(".com")){
            this.setMsg("Endereco de email invalido!!!");
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setMatricula(matricula);
        usuario.setCurso(curso);
        usuario.setTelefone(telefone);
        usuario.setEmail(email);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setStrikes(0);
        UsuarioControle controle = new UsuarioControle();
        try{
            controle.salvarUsuario(usuario);
            this.limparCampus();
            return "index";
        }catch(UJCException e){
            this.setMsg(e.getMessage());
            return null;
        }
    }
    
    public String entrarUsuario(){
        if (new UsuarioControle().efetuarLogin(this.getLogin(), this.getSenha())==true) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("Login_Usuario", this.getLogin());
            String login = (String) session.getAttribute("Login_Usuario");
            this.limparCampus();
            if(login.equals("Administrador")){
                return "faces/indexlogadm";
            }else
                return "faces/indexlog";
        } else {
            this.setMsg("Login ou Senha inválido!!!");
            
            return "login";
        }
    }
    public String telaCadastro(){
        this.limparCampus();
        return "cadastrarusuario";
    }
    public void limparCampus(){
        this.setEmail("");
        this.setNome("");
        this.setMatricula("");
        this.setCurso("");
        this.setTelefone("");
        this.setLogin("");
        this.setSenha("");
        this.setMsg("");
        this.setUsuario(null);
    }
    
    
    public void irMinhaConta(){
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = (HttpSession) request.getSession();
        String login = (String) session.getAttribute("Login_Usuario");
        try{
            usuario = new UsuarioControle().buscarUsuarioPorLogin(login);
            
            nome="Editar";
            matricula="Excluir";
            edit=true;
        }catch(UNEException ex){
            
        }
    }
    
    public void butaoEditar(){
        if(nome.equals("Editar")){
            nome="Salvar";
            matricula="Cancelar";
            edit=false;
        }else{
            try{
                new UsuarioControle().atualizarUsuario(usuario);
                this.irMinhaConta();
            } catch (UNEException ex) {
                
            } catch (UJCException ex) {
                this.setMsg("Email já cadastrado");
            }
        }
    }
    
    public String butaoExcluir(){
        if(matricula.equals("Excluir")){
            try {
                new UsuarioControle().removerUsuario(usuario.getLogin());
                return "index";
            } catch (UNEException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }else{
            this.irMinhaConta();
            return null;
        }
    }
    
}
