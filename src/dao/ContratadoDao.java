package dao;

import entities.Contratado;
import entities.Contratante;
import entities.pag.ContaBancaria;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratadoDao {
//    public Contratado(String nome, String cpf, String tipoPessoa, String endereco, String telefone, String email, Date dataNascimento, String cnpj, Double notaMedia, ContaBancaria contaBancaria) {

    private final static String sqlCreateTable = "CREATE TABLE contratados "
            + "(id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
            + "nome VARCHAR(100) NOT NULL,"
            + "cpf VARCHAR(100) NOT NULL,"
            + "tipoPessoa VARCHAR(100) NOT NULL,"
            + "endereco VARCHAR(100) NOT NULL,"
            + "telefone VARCHAR(100) NOT NULL,"
            + "email VARCHAR(100) NOT NULL,"
            + "dataNascimento DATE NOT NULL,"
            + "cnpj VARCHAR(100) NOT NULL,"
            + "notaMedia FLOAT NOT NULL,"
            + "contaBancaria VARCHAR(100) NOT NULL," //tipo ContaBancaria????
            + "PRIMARY KEY (id))";
    private final String sqlC = "INSERT INTO contratados (nome, cpf, tipoPessoa, endereco, telefone, email, dataNascimento,cnpj, notaMedia, contaBancaria) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String sqlR = "SELECT * FROM contratados";
    private final String sqlU = "UPDATE contratados SET nome=?, cpf=?, tipoPessoa=?, endereco=?, telefone=?, email=?, dataNascimento=?, cnpj=?, notaMedia=?, contaBancaria=? WHERE id=?";
    private final String sqlD = "DELETE FROM contratados WHERE id=?";
    private final String sqlRById = "SELECT * FROM contratados WHERE id=?";
    private PreparedStatement stmC;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    private PreparedStatement stmRById;
    private ContaBancaria conta = new ContaBancaria(); 

    public ContratadoDao(ConexaoJavaDb conexao) throws DaoException, ConexaoException {
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

    public long create(Contratado l) throws DaoException {
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

    public List<Contratado> read() throws DaoException {
        List<Contratado> contratados = new ArrayList<>();
        try {
            ResultSet rs = stmR.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String tipoPessoa = rs.getString("tipoPessoa");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                Date dataNascimento = rs.getDate("dataNascimento");
                Double cnpj = rs.getDouble("cnpj");
                Double notaMedia = rs.getDouble("notaMedia");
                ContaBancaria contaBancaria = conta.getConta();
               
              
              
                Contratado l = new Contratado(id, nome, cpf, tipoPessoa, endereco, telefone, email, dataNascimento, cnpj, notaMedia, contaBancaria);
                contratados.add(l);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao ler registros: " + ex.getMessage());
        }
        return contratados;
    }

    public void update(Contratado l) throws DaoException {
        try {
            stmU.setString(1, l.getNome());
            stmU.setString(2, l.getcpf());
            stmU.setString(3, l.getTipoPessoa());
            stmU.setString(4, l.getEndereco());
            stmU.setString(5, l.getTelefone());
            stmU.setString(6, l.getEmail());
            stmU.setDate(7, (Date) l.getDataNascimento());
            stmU.setString(8, l.getCnpj());
            stmU.setDouble(9, l.getNotaMedia());
            stmU.setContaBancaria(10, l.getContaBancaria());
            

            stmU.setLong(11, l.getId());
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

    public Contratado readById(long id) throws DaoException {
        Contratado l = null;

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
                Double cnpj = rs.getDouble("cnpj");
                Double notaMedia = rs.getDouble("notaMedia");
                ContaBancaria contaBancaria = conta.getConta();
                l = new Contratado(id, nome, cpf, tipoPessoa, endereco, telefone, email, dataNascimento, cnpj, notaMedia, contaBancaria);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Falha ao buscar pelo id: " + ex.getMessage());
        }
        return l;
    }

}
