package bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConnection() {
	
		Connection conn = null;
	
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/piolaproject";
			conn = DriverManager.getConnection(url, "root", "1234");
			System.out.println("Conexao estabelecida com sucesso!");
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		
		}catch(ClassNotFoundException b) {
			System.out.println(b.getException());
			
		}catch(Exception c) {
			System.out.println(c.getMessage());
			
		}return conn;
	
	}

}
