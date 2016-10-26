/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import dao.UsuarioDAO;
import entidades.Usuario;
import excecoes.UJCException;
import excecoes.UNEException;

/**
 *
 * @author alanf
 */
public class UsuarioControle {
    private UsuarioDAO dao = new UsuarioDAO();
    
    public void salvarUsuario(Usuario usuario)throws UJCException{
        try{
            this.buscarUsuarioPorLogin(usuario.getLogin());
            throw new UJCException();
        }catch(UNEException e){
            dao.salvarUsuario(usuario);
        }
    }
    
    public Usuario buscarUsuarioPorLogin(String login)throws UNEException{
        Usuario usuario = dao.getUsuarioPorLogin(login);
        if(usuario!=null)
            return usuario;
        else
            throw new UNEException();
    }
    
    public Usuario buscaUsuarioPorId(int id)throws UNEException{
        Usuario usuario = dao.getUsuarioPorId(id);
        if(usuario!=null)
            return usuario;
        else
            throw new UNEException();
    }
    
    public void atualizarStrikes(Usuario usuario)throws UNEException, UJCException{
        Usuario aux = this.buscaUsuarioPorId(usuario.getId());
        try{
            this.buscarUsuarioPorLogin(usuario.getLogin());
            if(aux.getLogin().equals(usuario.getLogin()))
                dao.atualizarStrikes(usuario);
            else
                throw new UJCException();
        }catch(UNEException e){
            
        }
    }
    
    public void atualizarUsuario(Usuario usuario)throws UNEException, UJCException{
        Usuario aux = this.buscaUsuarioPorId(usuario.getId());
        try{
            this.buscarUsuarioPorLogin(usuario.getLogin());
            if(aux.getLogin().equals(usuario.getLogin()))
                dao.atualizarUsuario(usuario);
            else
                throw new UJCException();
        }catch(UNEException e){
            dao.atualizarUsuario(usuario);
        }
    }
    
    public void removerUsuario(String login)throws UNEException{
        Usuario user = this.buscarUsuarioPorLogin(login);
        dao.removerUsuarioPorEmail(user.getEmail());
    }
    
    public boolean efetuarLogin(String login,String senha){
        Usuario usuario = dao.efetuarLoginUsuario(login, senha);
        if(usuario!=null)
            return true;
        else
            return false;
    }
}
