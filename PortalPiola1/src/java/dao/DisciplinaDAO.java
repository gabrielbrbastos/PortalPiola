package dao;

import entidades.Disciplina;

import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;

public class DisciplinaDAO {

    private String url = "jdbc:mysql://localhost:3306/piolaportal?useTimezone=true&serverTimezone=UTC";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String user = "root";
    private String password = "1234!";

    private static final String INSERIR_DISCIPLINA = "insert into disciplina(nome, turno) values(?,?)";
    private static final String SELECIONAR_DISCIPLINA = "select nome,turno from where id=?";
    private static final String SELECIONAR_ALL_DISCIPLINA = "select * from disciplina";
    private static final String UPDATE_DISCIPLINA = "update disciplina insert nome=?, turno=? where id=?";
    private static final String DELETE_DISCIPLINA = "delete from disciplina where id=?";

    protected Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conex o estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getException());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void inserirDisciplina(Disciplina dis) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        System.out.println(dis.getNome());
        try {
            pstm = conn.prepareStatement(INSERIR_DISCIPLINA);

            pstm.setString(1, dis.getNome());
            pstm.setString(2, dis.getTurno());

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

    public Disciplina selecionarDisciplina(int id) throws SQLException {
        Disciplina dis = null;

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECIONAR_DISCIPLINA);) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String turno = rs.getString("turno");

            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dis;
    }

    public List<Disciplina> allDisciplina() throws SQLException {
        List<Disciplina> listaDis = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECIONAR_ALL_DISCIPLINA);) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String turno = rs.getString("turno");
                
                Disciplina disc = new Disciplina(nome,turno);
                listaDis.add(disc);

            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaDis;
    }

    public boolean updateDisciplina(Disciplina dis) throws SQLException {
        boolean disciplinaUpdated;

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(UPDATE_DISCIPLINA);) {

            pstm.setString(1, dis.getNome());
            pstm.setString(2, dis.getTurno());

            disciplinaUpdated = pstm.executeUpdate() > 0;
            conn.close();
        }
        return disciplinaUpdated;
    }

    public boolean deleteDisciplina(int id) throws SQLException {
        boolean disciplinaDeleted;

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(DELETE_DISCIPLINA);) {
            pstm.setInt(1, id);
            disciplinaDeleted = pstm.executeUpdate() > 0;
            conn.close();
        }
        return disciplinaDeleted;
    }

}
