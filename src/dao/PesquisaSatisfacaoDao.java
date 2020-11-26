/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.PesquisaSatisfacao;
import entities.Servico;
import entities.pag.ContaBancaria;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PesquisaSatisfacaoDao {
//    Servico servico, String nameContratada, String comentario, Double nota
     private final static String sqlCreateTable = "CREATE TABLE PesquisaSatisfacoes "
            + "(id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
            + "servico VARCHAR(100) NOT NULL," // TIPO SERVICO????
            + "nameContratada VARCHAR(100) NOT NULL,"
            + "comentario VARCHAR(100) NOT NULL,"
            + "nota FLOAT NOT NULL,"
            + "PRIMARY KEY (id))";
    private final String sqlC = "INSERT INTO PesquisaSatisfacoes (servico,nameContratada,comentario,nota) VALUES (?,?,?,?)";
    private final String sqlR = "SELECT * FROM PesquisaSatisfacoes";
    private final String sqlU = "UPDATE PesquisaSatisfacoes SET servico=?, nameContratada=?, comentario=?, nota=? WHERE id=?";
    private final String sqlD = "DELETE FROM PesquisaSatisfacoes WHERE id=?";
    private final String sqlRById = "SELECT * FROM PesquisaSatisfacoes WHERE id=?";
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    private PreparedStatement stmRById;
    private ContaBancaria conta = new ContaBancaria(); 

    public PesquisaSatisfacaoDao(ConexaoJavaDb conexao) throws DaoException, ConexaoException {
        try {
            Connection con = conexao.getConnection();
            try {
                Statement stm = con.createStatement();
                stm.execute(sqlCreateTable);
                System.out.println("Tabela criada com sucesso!");
            } catch (SQLException ex) {
                System.out.println("Tabela já existe!");
            }
            stmC = con.prepareStatement(sqlC, Statement.RETURN_GENERATED_KEYS);
            stmR = con.prepareStatement(sqlR);
            stmU = con.prepareStatement(sqlU);
            stmD = con.prepareStatement(sqlD);
            stmRById = con.prepareStatement(sqlRById);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao preparar statement: " + ex.getMessage());
        }
    }

    public long create(PesquisaSatisfacao l) throws DaoException {
        long id = 0;
        try {
            //    Servico servico, String nameContratada, String comentario, Double nota
            stmC.setServico(1, l.getServico());
            stmC.setString(1, l.getNameContratada());
            stmC.setString(1, l.getComentario());
            stmC.setDouble(1, l.getNota());

            int r = stmC.executeUpdate();
            ResultSet rs = stmC.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao criar registro: " + ex.getMessage());
        }
        return id;
    }

    public List<PesquisaSatisfacao> read() throws DaoException {
        List<PesquisaSatisfacao> PesquisaSatisfacoes = new ArrayList<>();
        try {
            ResultSet rs = stmR.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                Servico servico = rs.getString("servico");
                String nameContratada = rs.getString("nameContratada");
                String comentario = rs.getString("comentario");
                String nota = rs.getString("nota");
        
                PesquisaSatisfacao l = new PesquisaSatisfacao(id,servico,nameContratada,comentario,nota);
                PesquisaSatisfacoes.add(l);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao ler registros: " + ex.getMessage());
        }
        return PesquisaSatisfacoes;
    }

    public void update(PesquisaSatisfacao l) throws DaoException {
        try {
            stmU.setServico(1, l.getServico());
            stmU.setString(2, l.getNameContratada());
            stmU.setString(3, l.getComentario());
            stmU.setDouble(4, l.getNota());
            

            stmU.setLong(5, l.getId());
            int r = stmU.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao atualizar registro: " + ex.getMessage());
        }
    }

    public void delete(long id) throws DaoException {
        try {
            stmD.setLong(1, id);
            int r = stmD.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Falha ao apagar registro: " + ex.getMessage());
        }
    }

    public void close() throws DaoException {
        try {
            stmC.close();
            stmR.close();
            stmU.close();
            stmD.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao fechar DAO: " + ex.getMessage());
        }
    }

    public PesquisaSatisfacao readById(long id) throws DaoException {
        PesquisaSatisfacao l = null;

        try {
            stmRById.setLong(1, id);
            ResultSet rs = stmRById.executeQuery();
            if (rs.next()) {

                Servico servico = rs.getString("servico");
                String nameContratada = rs.getString("nameContratada");
                String comentario = rs.getString("comentario");
                String nota = rs.getString("nota");
                l = new PesquisaSatisfacao(id,servico,nameContratada,comentario,nota);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao buscar pelo id: " + ex.getMessage());
        }
        return l;
    }
}
