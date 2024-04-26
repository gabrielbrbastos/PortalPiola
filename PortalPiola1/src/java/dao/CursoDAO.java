package dao;
import entidades.Curso;

import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CursoDAO {
	
	private String url = "jdbc:mysql://localhost:3306/piolaportal?useTimezone=true&serverTimezone=UTC";
	private String driver = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String password = "1234!";
	
	private static final String INSERT_CURSO_SQL = "insert into curso(nome, turno) values(?,?)";
	private static final String SELECT_CURSO_BY_ID = "select nome,turno where id=?";
	private static final String SELECT_ALL_CURSO = "select * from curso";
	private static final String UPDATE_CURSO = "update curso insert nome=?, turno=? where id=?";
	private static final String DELETE_CURSO = "delete from curso where id=?";
	
	protected Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Conex o estabelecida com sucesso!");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(ClassNotFoundException e) {
			System.out.println(e.getException());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public void inserirCurso(Curso curso) {
		PreparedStatement pstm = null;
		Connection conn = getConnection();
		
		try {
			pstm = conn.prepareStatement(INSERT_CURSO_SQL);
			
			pstm.setString(1, curso.getNome());
			pstm.setString(2, curso.getTurno());
			pstm.executeUpdate();
			
			conn.close();
		}catch(SQLException e) {
		}finally {
			try {
			if(pstm != null) {
				pstm.close();
			}
			if (conn != null) {
				conn.close();
			}
			}catch(SQLException e) {
			}
		}
	}
	
	public Curso procurarCurso(int id) throws SQLException {
		Curso curso = null;
		
		try (Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_CURSO_BY_ID);){
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				String nome = rs.getString("nome");
				String turno = rs.getString("turno");
				curso = new Curso(id, nome, turno);
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return curso;
	}
	
	public List<Curso> allCurso() throws SQLException{
		List<Curso> listaCurso = new ArrayList<>();
		
		try(Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_CURSO);){
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String turno = rs.getString("turno");
				listaCurso.add(new Curso(id, nome, turno));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return listaCurso;
	}
	
	public boolean updateCurso(Curso curso) throws SQLException{
		boolean cursoUpdated;
		
		try (Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(UPDATE_CURSO);){
			
			pstm.setString(1, curso.getNome());
			pstm.setString(2, curso.getTurno());
			
			cursoUpdated = pstm.executeUpdate() > 0;
		}
		return cursoUpdated;
	}
	
	public boolean deleteCurso(int id) throws SQLException {
		boolean cursoDeleted;
		
		try (Connection conn = getConnection();
				PreparedStatement pstm = conn.prepareStatement(DELETE_CURSO);){
			pstm.setInt(1, id);
			cursoDeleted = pstm.executeUpdate() > 0;
		}
		return cursoDeleted;
	}
}