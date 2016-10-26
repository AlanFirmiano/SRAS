/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.ConnectionFactory;

/**
 *
 * @author alanf
 */
public class SalaDAO {
    public void salvarSala(Sala sala){
        String sql = "INSERT INTO sala(nome_sala,bloco_sala) "
                + "values(?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexão com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setString(1, sala.getNome());
            pstm.setString(2, sala.getBloco());

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
 
   public void removerSalaPorId(int id){
        String sql = "Delete From sala Where id_sala=?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexão com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setInt(1, id);

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
    
    public void atualizarSala(Sala sala){
        String sql = "Update sala Set nome_sala=?, bloco_sala=? Where id_sala=?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexão com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setString(1, sala.getNome());
            pstm.setString(2, sala.getBloco());
            pstm.setInt(3, sala.getId());

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
 
    public Sala getSalaPorId(int id){
        String sql = "Select * From sala Where id_sala=?";

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
				Sala sala = new Sala();
				
				// Recupera o nome do banco e atribui ele ao objeto
				sala.setId(rset.getInt("id_sala"));
                                sala.setNome(rset.getString("nome_sala"));
                                sala.setBloco(rset.getString("bloco_sala"));
                                
				return sala;
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
       
    public Sala getSalaPorNomeBloco(String nome,String bloco){
        String sql = "Select * From sala Where nome_sala=? and bloco_sala=?";

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.getConexao();

			pstm = conn.prepareStatement(sql);
                        
                        pstm.setString(1, nome);
                        pstm.setString(2, bloco);
			rset = pstm.executeQuery();

			// Enquanto existir dados no banco de dados
			while (rset.next()) {

				Sala sala = new Sala();
				
				// Recupera o nome do banco e atribui ele ao objeto
                                sala.setId(rset.getInt("id_sala"));
                                sala.setNome(rset.getString("nome_sala"));
                                sala.setBloco(rset.getString("bloco_sala"));
                                
				return sala;
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
    public ArrayList<Sala> listaSalas(){
        String sql = "Select * From sala";

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;
                ArrayList<Sala> salas=null;

		try {
			conn = ConnectionFactory.getConexao();

			pstm = conn.prepareStatement(sql);
                        
			rset = pstm.executeQuery();
                        salas=new ArrayList<>();
			// Enquanto existir dados no banco de dados
			while (rset.next()) {

				Sala sala = new Sala();
				

				// Recupera o nome do banco e atribui ele ao objeto
				sala.setId(rset.getInt("id_sala"));
                                sala.setNome(rset.getString("nome_sala"));
                                sala.setBloco(rset.getString("bloco_sala"));
                                
				salas.add(sala);
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

		return salas;
    }
}
