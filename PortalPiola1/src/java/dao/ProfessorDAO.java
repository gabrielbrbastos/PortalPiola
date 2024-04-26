package dao;

import entidades.Professor;

import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ProfessorDAO {

    private String url = "jdbc:mysql://localhost:3306/piolaportal?useTimezone=true&serverTimezone=UTC";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String user = "root";
    private String password = "1234!";

    private static final String INSERT_PROFESSOR_SQL = "insert into professor(nome, email, telefone, senha) values(?,?,?,?)";
    private static final String SELECT_PROFESSOR_BY_ID = "select id,nome,email,telefone,senha from professor where id = ?";
    private static final String SELECT_ALL_PROFESSOR = "select * from professor order by nome";
    private static final String DELETE_PROFESSOR = "delete from professor where id = ?";
    private static final String UPDATE_PROFESSOR = "update professor insert nome=?, email=?, telefone=?, senha=? where id=?";
    private static final String SELECT_ID_SENHA = "select id from professor where senha =?";
    private static final String SELECT_PROFID = "select email from professor where id=?";
    private static final String SELECT_SENHA = "select senha from professor";
    private static final String SELECT_DISCIPLINA = "insert into profcurso select nome professor select nome curso ";
    
    private static final String SELECT_PROFESSOR_BY_EMAIL = "select id,nome,email,telefone,senha from professor where email =?";

    protected Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conex�o estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getException());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public void inserirProfessor(Professor prof) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(INSERT_PROFESSOR_SQL);
            

            pstm.setString(1, prof.getNome());
            pstm.setString(2, prof.getEmail());
            pstm.setString(3, prof.getTelefone());
            pstm.setString(4, prof.getSenha());
            pstm.executeUpdate();

            conn.close();
        } catch (SQLException e) {
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }

        }
    }

    public Professor procurarProfessor(int id) throws SQLException {
        Professor professor = null;

        try (Connection connection = getConnection(); PreparedStatement pstm = connection.prepareStatement(SELECT_PROFESSOR_BY_ID);) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                professor = new Professor(id, nome, email, telefone);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return professor;
    }

    public Professor procurarProfessorPorEmail(String email) throws SQLException {
        Professor professor = null;

        try (Connection connection = getConnection(); PreparedStatement pstm = connection.prepareStatement(SELECT_PROFESSOR_BY_EMAIL);) {
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("id");
                String telefone = rs.getString("telefone");
                professor = new Professor(id, nome, email, telefone);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return professor;
    }

    public List<Professor> allProf() throws SQLException {
        List<Professor> listaProf = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_PROFESSOR);) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                listaProf.add(new Professor(id, nome, email, telefone));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaProf;
    }

    public boolean updateProfessor(Professor prof) throws SQLException {
        boolean profUpdated;
        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(UPDATE_PROFESSOR);) {

            pstm.setString(1, prof.getNome());
            pstm.setString(2, prof.getEmail());
            pstm.setString(3, prof.getTelefone());
            pstm.setString(4, prof.getSenha());

            profUpdated = pstm.executeUpdate() > 0;
        }
        return profUpdated;
    }

    public boolean deleteProfessor(int id) throws SQLException {
        boolean profDeleted;
        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(DELETE_PROFESSOR);) {
            pstm.setInt(1, id);
            profDeleted = pstm.executeUpdate() > 0;
        }
        return profDeleted;
    }

    public String selecionarProfessorPorId(int id) throws SQLException {
        String adm = null;

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_PROFID);) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                adm = email;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return adm;
    }

    public int selecionarIdSenha(String senha) throws SQLException {
        int idsenha = 0;

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_ID_SENHA);) {
            pstm.setString(1, senha);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                idsenha = rs.getInt("id");
                System.out.println(idsenha);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idsenha;
    }

    public List<String> selecionaSenha() throws SQLException {
        List<String> listSenha = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_SENHA);) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String senha = rs.getString("senha");
                listSenha.add(senha);
            }
        }
        return listSenha;
    }

}
