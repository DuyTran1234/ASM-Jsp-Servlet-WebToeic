package DB;
import java.sql.*;

public class Connect {
	public static Connection connectDB() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/web_toeic?useUnicode=true&characterEncoding=utf-8";
		String user = "root";
		String password = "tranvietduy0504";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
