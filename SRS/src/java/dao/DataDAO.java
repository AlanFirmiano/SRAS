/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.ConnectionFactory;

/**
 *
 * @author alanf
 */
public class DataDAO {
    public void salvarData(Data data){
        String sql = "INSERT INTO data(data_reserva,hora_inicio_reserva,hora_final_reserva) "
                + "values(?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexão com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setDate(1, new java.sql.Date(data.getData().getTime()));
            pstm.setString(2, data.getHora_inicial());
            pstm.setString(3, data.getHora_final());

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
 
   public void removerDataPorId(int id){
        String sql = "Delete From data Where id_data=?";

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
    
    public void atualizarData(Data data){
        String sql = "Update data Set data_reserva=?, hora_inicio_reserva=?, hora_final_reserva=? Where id_data=?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexão com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setDate(1, new java.sql.Date(data.getData().getTime()));
            pstm.setString(2, data.getHora_inicial());
            pstm.setString(3, data.getHora_final());
            pstm.setInt(4, data.getId());
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
 
    public Data getDataPorId(int id){
        String sql = "Select * From data Where id_data=?";

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
				Data data = new Data();
				
				// Recupera o nome do banco e atribui ele ao objeto
				data.setId(rset.getInt("id_data"));
                                data.setHora_inicial(rset.getString("hora_inicio_reserva"));
                                data.setData(rset.getDate("data_reserva"));
                                data.setHora_final(rset.getString("hora_final_reserva"));
				return data;
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
    
    public Data getDataPorTudo(Data data){
        String sql = "Select * From data Where data_reserva=? and hora_inicio_reserva=? and hora_final_reserva=?";

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.getConexao();

			pstm = conn.prepareStatement(sql);
                        
                        pstm.setDate(1, new java.sql.Date(data.getData().getTime()));
                        pstm.setString(2, data.getHora_inicial());
                        pstm.setString(3, data.getHora_final());
                        
			rset = pstm.executeQuery();

			// Enquanto existir dados no banco de dados
			while (rset.next()) {
				Data data1 = new Data();
				
				// Recupera o nome do banco e atribui ele ao objeto
				data1.setId(rset.getInt("id_data"));
                                data1.setHora_inicial(rset.getString("hora_inicio_reserva"));
                                data1.setData(rset.getDate("data_reserva"));
                                data1.setHora_final(rset.getString("hora_final_reserva"));
				return data1;
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
