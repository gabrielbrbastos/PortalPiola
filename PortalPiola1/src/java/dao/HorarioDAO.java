package dao;

import entidades.Horario;

import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;

public class HorarioDAO {

    private String url = "jdbc:mysql://localhost:3306/piolaportal?useTimezone=true&serverTimezone=UTC";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String user = "root";
    private String password = "1234!";

    private static final String INSERT_HORARIO = "insert into horario(disciplina, turno, diadeaula, horainicio, horafim, professor) values(?,?,?,?,?,?)";
    private static final String SELECIONAR_HORARIO = "select disciplina,turno,diadeaula,horainicio,horafim from where professor=?";
    private static final String ALL_HORARIO = "select * from horario";
    private static final String UPDATE_HORARIO = "update horario set diadeaula=?, horainicio=?, horafim=? where id=?";
    private static final String DELETE_HORARIO = "delete from horario where id=?";

    private static final String SELECIONAR_HORARIO_PROFESSOR = "select * from horario where professor=?";

    protected Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(driver);
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

    public void inserirHorario(Horario h) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(INSERT_HORARIO);

            pstm.setString(1, h.getNome());
            pstm.setString(2, h.getTurno());
            pstm.setString(3, h.getDiadeaula());
            pstm.setString(4, h.getHorainicio());
            pstm.setString(5, h.getHorafim());
            pstm.setString(6, h.getProfessor());

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

    public Horario selecionarHorario(int id) throws SQLException {
        Horario h = null;

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECIONAR_HORARIO);) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String disciplina = rs.getString("disciplina");
                String turno = rs.getString("turno");
                String professor = rs.getString("professor");
                String diadeaula = rs.getString("diadeaula");
                String horainicio = rs.getString("inicio");
                String horafim = rs.getString("fim");
                h = new Horario(id, disciplina, turno, professor, diadeaula, horainicio, horafim);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return h;
    }

    public ArrayList<Horario> allHorario() throws SQLException {
        ArrayList<Horario> listaHorario = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(ALL_HORARIO);) {
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String disciplina = rs.getString("disciplina");
                String turno = rs.getString("turno");
                String professor = rs.getString("professor");
                String diadeaula = rs.getString("diadeaula");
                String horainicio = rs.getString("horainicio");
                String horafim = rs.getString("horafim");

                listaHorario.add(new Horario(id, disciplina, turno, professor, diadeaula, horainicio, horafim));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaHorario;
    }

    public ArrayList<Horario> horarioProfessor(String nome) throws SQLException {
        ArrayList<Horario> listaHorario = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECIONAR_HORARIO_PROFESSOR);) {
            pstm.setString(1, nome);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String disciplina = rs.getString("disciplina");
                String turno = rs.getString("turno");
                String professor = rs.getString("professor");
                String diadeaula = rs.getString("diadeaula");
                String horainicio = rs.getString("horainicio");
                String horafim = rs.getString("horafim");

                listaHorario.add(new Horario(id, disciplina, turno, professor, diadeaula, horainicio, horafim));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaHorario;
    }

    public boolean updateHorario(Horario h) throws SQLException {
        boolean horarioUpdated;

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(UPDATE_HORARIO);) {
            pstm.setString(1, h.getDiadeaula());
            pstm.setString(2, h.getHorainicio());
            pstm.setString(3, h.getHorafim());

            horarioUpdated = pstm.executeUpdate() > 0;
            conn.close();
        }
        return horarioUpdated;
    }

    public boolean deletarHorario(String diadeaula) throws SQLException {
        boolean horarioDeleted;

        try (Connection conn = getConnection(); PreparedStatement pstm = conn.prepareStatement(DELETE_HORARIO);) {
            pstm.setString(1, diadeaula);
            horarioDeleted = pstm.executeUpdate() > 0;
            conn.close();
        }
        return horarioDeleted;
    }

}
