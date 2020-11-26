package dao;

import entities.Contratante;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratanteDao {
    private final static String sqlCreateTable = "CREATE TABLE contratantes "
            + "(id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
            + "nome VARCHAR(100) NOT NULL,"
            + "cpf VARCHAR(100) NOT NULL,"
            + "tipoPessoa VARCHAR(100) NOT NULL,"
            + "endereco VARCHAR(100) NOT NULL,"
            + "telefone VARCHAR(100) NOT NULL,"
            + "email VARCHAR(100) NOT NULL,"
            + "dataNascimento DATE NOT NULL,"
            + "notaMedia FLOAT NOT NULL,"
            + "PRIMARY KEY (id))";
    private final String sqlC = "INSERT INTO contratantes (nome, cpf, tipoPessoa, endereco, telefone, email, dataNascimento, notaMedia) VALUES (?,?,?,?,?,?,?,?)";
    private final String sqlR = "SELECT * FROM contratantes";
    private final String sqlU = "UPDATE contratantes SET nome=?, cpf=?, tipoPessoa=?, endereco=?, telefone=?, email=?, dataNascimento=?, notaMedia=? WHERE id=?";
    private final String sqlD = "DELETE FROM contratantes WHERE id=?";
    private final String sqlRById = "SELECT * FROM contratantes WHERE id=?";
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    private PreparedStatement stmRById;
    public ContratanteDao(ConexaoJavaDb conexao) throws DaoException, ConexaoException {
        try {
            Connection con = conexao.getConnection();
            try {
                Statement stm = con.createStatement();
                stm.execute(sqlCreateTable);
                System.out.println("Tabela criada com sucesso!");
            } catch( SQLException ex ) {
                System.out.println("Tabela já existe!");
            }
            stmC = con.prepareStatement(sqlC, Statement.RETURN_GENERATED_KEYS);
            stmR = con.prepareStatement(sqlR);
            stmU = con.prepareStatement(sqlU);
            stmD = con.prepareStatement(sqlD);
            stmRById = con.prepareStatement(sqlRById);
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao preparar statement: " + ex.getMessage());
        }
    }

    public long create(Contratante l) throws DaoException {
        long id = 0;
        try {
            stmC.setString(1, l.getNome());
            stmC.setString(2, l.getcpf());
            stmC.setString(3, l.getTipoPessoa());
            stmC.setString(4, l.getEndereco());
            stmC.setString(5, l.getTelefone());
            stmC.setString(6, l.getEmail());
            stmC.setDate(7, (Date) l.getDataNascimento());
            stmC.setDouble(8, l.getNotaMedia());

            int r = stmC.executeUpdate();
            ResultSet rs = stmC.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao criar registro: " + ex.getMessage());
        }
        return id;
    }
    public List<Contratante> read() throws DaoException {
        List<Contratante> contratantes = new ArrayList<>();
        try {
            ResultSet rs = stmR.executeQuery();
            while(rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String tipoPessoa = rs.getString("tipoPessoa");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                Date dataNascimento = rs.getDate("dataNascimento");
                Double notaMedia = rs.getDouble("notaMedia");
               
      
                Contratante l = new Contratante(id, nome, cpf, tipoPessoa, endereco, telefone, email, dataNascimento, notaMedia);
                contratantes.add(l);
            }
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao ler registros: " + ex.getMessage());
        }
        return contratantes;
    }
    public void update(Contratante l) throws DaoException {
        try {
            stmU.setString(1, l.getNome());
            stmU.setString(2, l.getcpf());
            stmU.setString(3, l.getTipoPessoa());
            stmU.setString(4, l.getEndereco());
            stmU.setString(5, l.getTelefone());
            stmU.setString(6, l.getEmail());
            stmU.setDate(7, (Date) l.getDataNascimento());
            stmU.setDouble(8, l.getNotaMedia());

            stmU.setLong(9, l.getId());
            int r = stmU.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao atualizar registro: " + ex.getMessage());
        }
    }
    public void delete(long id) throws DaoException {
        try {
            stmD.setLong(1, id);
            int r = stmD.executeUpdate();
        } catch(SQLException ex) {
            throw new DaoException("Falha ao apagar registro: " + ex.getMessage());
        }
    }
    public void close() throws DaoException {
        try {
            stmC.close();
            stmR.close();
            stmU.close();
            stmD.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao fechar DAO: " + ex.getMessage());
        }
    }

    public Contratante readById(long id) throws DaoException {
        Contratante l = null;

        try {
            stmRById.setLong(1, id);
            ResultSet rs = stmRById.executeQuery();
            if (rs.next()) {

                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String tipoPessoa = rs.getString("tipoPessoa");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                Date dataNascimento = rs.getDate("dataNascimento");
                Double notaMedia = rs.getDouble("notaMedia");
               
                
                l = new Contratante(id, nome, cpf, tipoPessoa, endereco, telefone, email, dataNascimento, notaMedia);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao buscar pelo id: " + ex.getMessage());
        }
        return l;
    }

}


