package dao;

import conexao.ConexaoException;
import conexao.ConexaoJavaDb;
import entities.Usuario;
import entities.pag.ContaBancaria;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private final static String sqlCreateTable = "CREATE TABLE usuarios "
            + "(id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
            + "nome VARCHAR(100) NOT NULL,"
            + "email VARCHAR(100) NOT NULL,"
            + "senha VARCHAR(100) NOT NULL,"
            + "idTipoPessoa INT NOT NULL FOREIGN KEY REFERENCES tipoPessoa"          
            + "PRIMARY KEY (id))";
    private final String sqlC = "INSERT INTO usuarios (nome,email, senha,idTipoPessoa ) VALUES (?,?,?,?)";
    private final String sqlR = "SELECT * FROM usuarios";
    private final String sqlU = "UPDATE usuarios SET nome=?, email=?, senha=?, idTipoPessoa=? WHERE id=?";
    private final String sqlD = "DELETE FROM usuarios WHERE id=?";
    private final String sqlRById = "SELECT * FROM usuarios WHERE id=?";
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    private PreparedStatement stmRById;
    private ContaBancaria conta = new ContaBancaria(); 

    public UsuarioDao(ConexaoJavaDb conexao) throws DaoException, ConexaoException {
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

    public long create(Usuario l) throws DaoException {
        long id = 0;
        try {
            stmC.setString(1, l.getNome());

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

    public List<Usuario> read() throws DaoException {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            ResultSet rs = stmR.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String idTipoPessoa = rs.getString("idTipoPessoa");
     
              
                Usuario l = new Usuario(id, nome,email, senha,idTipoPessoa);
                usuarios.add(l);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao ler registros: " + ex.getMessage());
        }
        return usuarios;
    }

    public void update(Usuario l) throws DaoException {
        try {
            stmU.setString(1, l.getNome());
            stmU.setString(2, l.getEmail());
            stmU.setString(3, l.getSenha());
            stmU.setString(4, l.getTipoPessoa());
            
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

    public Usuario readById(long id) throws DaoException {
        Usuario l = null;

        try {
            stmRById.setLong(1, id);
            ResultSet rs = stmRById.executeQuery();
            if (rs.next()) {

                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String idTipoPessoa = rs.getString("idTipoPessoa");
                
                l = new Usuario(id, nome,email, senha,idTipoPessoa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao buscar pelo id: " + ex.getMessage());
        }
        return l;
    }

}
