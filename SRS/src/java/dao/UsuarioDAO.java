/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entidades.Usuario;
import util.ConnectionFactory;

/**
 *
 * @author alanf
 */
public class UsuarioDAO {
    public void salvarUsuario(Usuario usuario){
        String sql = "INSERT INTO usuario(nome_usuario,matricula_usuario,curso_usuario,"
                + "telefone_usuario,email_usuario,login_usuario,senha_usuario,strikes_usuario) "
                + "values(?,?,?,?,?,?,?,0)";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexão com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getMatricula());
            pstm.setString(3, usuario.getCurso());
            pstm.setString(4, usuario.getTelefone());
            pstm.setString(5, usuario.getEmail());
            pstm.setString(6, usuario.getLogin());
            pstm.setString(7, usuario.getSenha());

            // Executa a sql para inserir os dados
            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            // Fecha as coneções
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
 
   public void removerUsuarioPorEmail(String email){
        String sql = "Delete From usuario Where email_usuario=?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexão com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setString(1, email);

            // Executa a sql para inserir os dados
            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            // Fecha as coneções
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
    
    public void atualizarUsuario(Usuario usuario){
        String sql = "Update usuario Set nome_usuario=?, matricula_usuario=?, curso_usuario=?,"
                + "telefone_usuario=?, email_usuario=?, login_usuario=?, senha_usuario=?,"
                + "strikes_usuario=? Where id_usuario=?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexão com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getMatricula());
            pstm.setString(3, usuario.getCurso());
            pstm.setString(4, usuario.getTelefone());
            pstm.setString(5, usuario.getEmail());
            pstm.setString(6, usuario.getLogin());
            pstm.setString(7, usuario.getSenha());
            pstm.setInt(8, usuario.getStrikes());
            pstm.setInt(9, usuario.getId());

            // Executa a sql para inserir os dados
            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            // Fecha as coneções
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
 public void atualizarStrikes(Usuario usuario){
        String sql = "Update usuario Set strikes_usuario=? Where id_usuario=?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexão com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setInt(1, usuario.getStrikes());
            pstm.setInt(2, usuario.getId());

            // Executa a sql para inserir os dados
            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            // Fecha as coneções
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
    public Usuario getUsuarioPorId(int id){
        String sql = "Select * From usuario Where id_usuario=?";

		

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.getConexao();

			pstm = conn.prepareStatement(sql);
                        
                        pstm.setInt(1, id);
                        
			rset = pstm.executeQuery();

			// Enquanto existir dados no banco de dados
			while (rset.next()) {
				Usuario usuario = new Usuario();
				
				// Recupera o nome do banco e atribui ele ao objeto
				usuario.setId(rset.getInt("id_usuario"));
                                usuario.setNome(rset.getString("nome_usuario"));
                                usuario.setMatricula(rset.getString("matricula_usuario"));
                                usuario.setCurso(rset.getString("curso_usuario"));
                                usuario.setTelefone(rset.getString("telefone_usuario"));
                                usuario.setEmail(rset.getString("email_usuario"));
                                usuario.setLogin(rset.getString("login_usuario"));
                                usuario.setSenha(rset.getString("senha_usuario"));
                                usuario.setStrikes(rset.getInt("strikes_usuario"));
                                
				return usuario;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				if (rset != null) {

					rset.close();
				}

				if (pstm != null) {

					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return null;
    }
       
    public Usuario getUsuarioPorLogin(String login){
        String sql = "Select * From usuario Where login_usuario=?";

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.getConexao();

			pstm = conn.prepareStatement(sql);
                        
                        pstm.setString(1, login);
                        
			rset = pstm.executeQuery();

			// Enquanto existir dados no banco de dados
			while (rset.next()) {

				Usuario usuario = new Usuario();
				
				// Recupera o nome do banco e atribui ele ao objeto
                                usuario.setId(rset.getInt("id_usuario"));
                                usuario.setNome(rset.getString("nome_usuario"));
                                usuario.setMatricula(rset.getString("matricula_usuario"));
                                usuario.setCurso(rset.getString("curso_usuario"));
                                usuario.setTelefone(rset.getString("telefone_usuario"));
                                usuario.setEmail(rset.getString("email_usuario"));
                                usuario.setLogin(rset.getString("login_usuario"));
                                usuario.setSenha(rset.getString("senha_usuario"));
                                usuario.setStrikes(rset.getInt("strikes_usuario"));
                                
				return usuario;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				if (rset != null) {

					rset.close();
				}

				if (pstm != null) {

					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return null;
    }
    
    public Usuario efetuarLoginUsuario(String login,String senha){
        String sql = "Select * From usuario Where login_usuario=? and senha_usuario=?";

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.getConexao();

			pstm = conn.prepareStatement(sql);
                        
                        pstm.setString(1, login);
                        pstm.setString(2, senha);
                        
			rset = pstm.executeQuery();

			// Enquanto existir dados no banco de dados
			while (rset.next()) {

				Usuario usuario = new Usuario();
				
				// Recupera o nome do banco e atribui ele ao objeto
				usuario.setId(rset.getInt("id_usuario"));
                                usuario.setNome(rset.getString("nome_usuario"));
                                usuario.setMatricula(rset.getString("matricula_usuario"));
                                usuario.setCurso(rset.getString("curso_usuario"));
                                usuario.setTelefone(rset.getString("telefone_usuario"));
                                usuario.setEmail(rset.getString("email_usuario"));
                                usuario.setLogin(rset.getString("login_usuario"));
                                usuario.setSenha(rset.getString("senha_usuario"));
                                usuario.setStrikes(rset.getInt("strikes_usuario"));
                                
				return usuario;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				if (rset != null) {

					rset.close();
				}

				if (pstm != null) {

					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return null;
    }
}
