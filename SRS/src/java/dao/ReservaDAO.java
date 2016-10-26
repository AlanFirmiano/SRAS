/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.ConnectionFactory;

/**
 *
 * @author alanf
 */
public class ReservaDAO {
    public void salvarReserva(Reserva reserva){
        String sql = "INSERT INTO reserva(id_usuario,id_data,id_sala)"
                + "values(?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexÃ£o com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setInt(1, reserva.getUsuario().getId());
            pstm.setInt(2, reserva.getData().getId());
            pstm.setInt(3, reserva.getSala().getId());

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
    public void removerReservaPorId(int id){
        String sql = "Delete From reserva Where id_reserva=?";

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
    public void atualizarReserva(Reserva reserva){
        String sql = "Update reserva Set id_usuario=?, id_data=?, id_sala=? Where id_reserva=?";

        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // Cria uma conexÃ£o com o banco
            conn = ConnectionFactory.getConexao();

            // Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);

            // Adiciona o valor aos parametros da sql
            pstm.setInt(1, reserva.getUsuario().getId());
            pstm.setInt(2, reserva.getData().getId());
            pstm.setInt(3, reserva.getSala().getId());
            pstm.setInt(4, reserva.getId());

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
    public Reserva buscarReservaPorId(int id){
        String sql = "Select * From reserva Where id_reserva=?";

		

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

				Reserva reserva = new Reserva();

				// Recupera o nome do banco e atribui ele ao objeto
                                reserva.setId(rset.getInt("id_reserva"));
                                reserva.getUsuario().setId(rset.getInt("id_usuario"));
                                reserva.getData().setId(rset.getInt("id_data"));
                                reserva.getSala().setId(rset.getInt("id_sala"));
                                return reserva;
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
    
    public ArrayList<Reserva> listaReservasPorUsuario(int id){
        String sql = "Select * From reserva Where id_usuario=?";

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;
                ArrayList<Reserva> reservas=null;

		try {
			conn = ConnectionFactory.getConexao();

			pstm = conn.prepareStatement(sql);
                        pstm.setInt(1, id);
                        
			rset = pstm.executeQuery();
                        reservas=new ArrayList<>();
			// Enquanto existir dados no banco de dados
			while (rset.next()) {

				Reserva reserva = new Reserva();
				

				// Recupera o nome do banco e atribui ele ao objeto
				reserva.setId(rset.getInt("id_reserva"));
                                reserva.getData().setId(rset.getInt("id_data"));
                                reserva.getUsuario().setId(rset.getInt("id_usuario"));
                                reserva.getSala().setId(rset.getInt("id_sala"));
                                
				reservas.add(reserva);
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

		return reservas;
    }
    public ArrayList<Reserva> listaReservasPorSala(int id){
        String sql = "Select * From reserva Where id_sala=?";

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;
                ArrayList<Reserva> reservas=null;

		try {
			conn = ConnectionFactory.getConexao();

			pstm = conn.prepareStatement(sql);
                        pstm.setInt(1, id);
                        
			rset = pstm.executeQuery();
                        reservas=new ArrayList<>();
			// Enquanto existir dados no banco de dados
			while (rset.next()) {

				Reserva reserva = new Reserva();
				

				// Recupera o nome do banco e atribui ele ao objeto
				reserva.setId(rset.getInt("id_reserva"));
                                reserva.getData().setId(rset.getInt("id_data"));
                                reserva.getUsuario().setId(rset.getInt("id_usuario"));
                                reserva.getSala().setId(rset.getInt("id_sala"));
                                
				reservas.add(reserva);
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

		return reservas;
    }
    public List<Reserva> listaReservas(){
        String sql = "Select * From reserva";

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;
                List<Reserva> reservas=null;

		try {
			conn = ConnectionFactory.getConexao();

			pstm = conn.prepareStatement(sql);
                        
			rset = pstm.executeQuery();
                        reservas=new ArrayList<>();
			// Enquanto existir dados no banco de dados
			while (rset.next()) {

				Reserva reserva = new Reserva();
				

				// Recupera o nome do banco e atribui ele ao objeto
				reserva.setId(rset.getInt("id_reserva"));
                                reserva.getData().setId(rset.getInt("id_data"));
                                reserva.getUsuario().setId(rset.getInt("id_usuario"));
                                reserva.getSala().setId(rset.getInt("id_sala"));
                                
				reservas.add(reserva);
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

		return reservas;
    }
}
