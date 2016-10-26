/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Espera;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.ConnectionFactory;

/**
 *
 * @author alanf
 */
public class EsperaDAO {
    public void salvarEspera(Espera espera){
        String sql = "INSERT INTO espera(id_usuario,id_data,id_sala)"
                + "values(?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexÃ£o com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setInt(1, espera.getUsuario().getId());
            pstm.setInt(2, espera.getData().getId());
            pstm.setInt(3, espera.getSala().getId());

            // Executa a sql para inserir os dados
            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            // Fecha as coneÃ§Ãµes
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
    public void removerEsperaPorId(int id){
        String sql = "Delete From espera Where id_espera=?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexÃ£o com o banco
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

            // Fecha as coneÃ§Ãµes
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
    public void atualizarEspera(Espera espera){
        String sql = "Update espera Set id_usuario=?, id_data=?, id_sala=? Where id_espera=?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexÃ£o com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setInt(1, espera.getUsuario().getId());
            pstm.setInt(2, espera.getData().getId());
            pstm.setInt(3, espera.getSala().getId());
            pstm.setInt(4, espera.getId());

            // Executa a sql para inserir os dados
            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            // Fecha as coneÃ§Ãµes
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
    public Espera buscarEsperaPorId(int id){
        String sql = "Select * From espera Where id_espera=?";

		

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

				Espera espera = new Espera();

				// Recupera o nome do banco e atribui ele ao objeto
                                espera.setId(rset.getInt("id_espera"));
                                espera.getUsuario().setId(rset.getInt("id_usuario"));
                                espera.getData().setId(rset.getInt("id_data"));
                                espera.getSala().setId(rset.getInt("id_sala"));
                                return espera;
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
    
    public ArrayList<Espera> listaEsperasPorUsuario(int id){
        String sql = "Select * From espera Where id_usuario=?";

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;
                ArrayList<Espera> esperas=null;

		try {
			conn = ConnectionFactory.getConexao();

			pstm = conn.prepareStatement(sql);
                        pstm.setInt(1, id);
                        
			rset = pstm.executeQuery();
                        esperas=new ArrayList<>();
			// Enquanto existir dados no banco de dados
			while (rset.next()) {

				Espera espera = new Espera();
				

				// Recupera o nome do banco e atribui ele ao objeto
				espera.setId(rset.getInt("id_espera"));
                                espera.getData().setId(rset.getInt("id_data"));
                                espera.getUsuario().setId(rset.getInt("id_usuario"));
                                espera.getSala().setId(rset.getInt("id_sala"));
                                
				esperas.add(espera);
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

		return esperas;
    }
    public ArrayList<Espera> listaEsperasPorSala(int id){
        String sql = "Select * From espera Where id_sala=?";

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;
                ArrayList<Espera> esperas=null;

		try {
			conn = ConnectionFactory.getConexao();

			pstm = conn.prepareStatement(sql);
                        pstm.setInt(1, id);
                        
			rset = pstm.executeQuery();
                        esperas=new ArrayList<>();
			// Enquanto existir dados no banco de dados
			while (rset.next()) {

				Espera espera = new Espera();
				

				// Recupera o nome do banco e atribui ele ao objeto
				espera.setId(rset.getInt("id_espera"));
                                espera.getData().setId(rset.getInt("id_data"));
                                espera.getUsuario().setId(rset.getInt("id_usuario"));
                                espera.getSala().setId(rset.getInt("id_sala"));
                                
				esperas.add(espera);
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

		return esperas;
    }
    public ArrayList<Espera> listaEsperas(){
        String sql = "Select * From espera";

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;
                ArrayList<Espera> esperas=null;

		try {
			conn = ConnectionFactory.getConexao();

			pstm = conn.prepareStatement(sql);
                        
			rset = pstm.executeQuery();
                        esperas=new ArrayList<>();
			// Enquanto existir dados no banco de dados
			while (rset.next()) {

				Espera espera = new Espera();
				

				// Recupera o nome do banco e atribui ele ao objeto
				espera.setId(rset.getInt("id_espera"));
                                espera.getData().setId(rset.getInt("id_data"));
                                espera.getUsuario().setId(rset.getInt("id_usuario"));
                                espera.getSala().setId(rset.getInt("id_sala"));
                                
				esperas.add(espera);
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

		return esperas;
    }
}
