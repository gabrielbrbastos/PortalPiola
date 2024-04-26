package dao;

import entidades.Administrador;

import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdmDAO {

    private String url = "jdbc:mysql://localhost:3306/piolaportal?useTimezone=true&serverTimezone=UTC";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String user = "root";
    private String password = "1234!";

    private static final String INSERT_ADM = "insert into administrador(nome,email,senha,telefone) values(?,?,?,?)";
    private static final String SELECT_ADM = "select nome,email,telefone where id=?";
    private static final String SELECT_ADMID = "select email from administrador where id=?";
    private static final String SELECT_ID_SENHA = "select id from administrador where senha =?";
    private static final String SELECT_ALL_ADM = "select * from administrador";
    private static final String UPDATE_ADM = "update insert nome=?,email=?,senha=?,telefone=? where id=?";
    private static final String DELETE_ADM = "delete from administrador where id=?";
    private static final String SELECT_SENHA = "select senha from administrador";

    private static final String SELECT_ADM_BY_EMAIL = "select id,nome,email,telefone,senha from administrador where email =?";

    protected Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("ConexÃ£o estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getException());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void inserirAdm(Administrador adm) {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(INSERT_ADM);

            pstm.setString(1, adm.getNome());
            pstm.setString(2, adm.getEmail());
            pstm.setString(3, adm.getSenha());
            pstm.setString(4, adm.getTelefone());
            pstm.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public Administrador selecionarAdmnisitrador(int id) throws SQLException {
        Administrador adm = null;

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_ADM);) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                adm = new Administrador(id, nome, email, telefone);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return adm;
    }

    public String selecionarAdmnistradorPorId(int id) throws SQLException {
        String adm = null;

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_ADMID);) {
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

    public List<Administrador> allAdministrador() throws SQLException {
        List<Administrador> listaAdm = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_ADM);) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                listaAdm.add(new Administrador(id, nome, email, telefone));
            }
        }
        return listaAdm;
    }

    public List<String> selecionaSenha() throws SQLException {
        List<String> listaSenha = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_SENHA);) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String senha = rs.getString("senha");
                listaSenha.add(senha);
            }
        }
        return listaSenha;
    }

    public boolean updateAdministrador(Administrador adm) throws SQLException {
        boolean admUpdated;

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(UPDATE_ADM);) {

            pstm.setString(1, adm.getNome());
            pstm.setString(2, adm.getEmail());
            pstm.setString(3, adm.getSenha());
            pstm.setString(4, adm.getTelefone());

            admUpdated = pstm.executeUpdate() > 0;
        }
        return admUpdated;
    }

    public boolean deleteAdministrador(int id) throws SQLException {
        boolean admDeleted;

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(DELETE_ADM);) {
            pstm.setInt(1, id);
            admDeleted = pstm.executeUpdate() > 0;
        }
        return admDeleted;
    }

    public Object selecionarAdmnisitradorId(Administrador idpego) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Administrador procurarAdmPorEmail(String email) throws SQLException {
        Administrador adm = null;

        try (Connection connection = getConnection(); PreparedStatement pstm = connection.prepareStatement(SELECT_ADM_BY_EMAIL);) {
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("id");
                String telefone = rs.getString("telefone");
                adm = new Administrador(id, nome, email, telefone);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return adm;
    }
}
