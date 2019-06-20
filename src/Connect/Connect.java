package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
public class Connect  {
	private static Connection con;
	public static Connection CreateMsAccess(String database) throws Exception {
			String url1 = "jdbc:postgresql://localhost/mydb";
			String user = "postgres";
			String password = "123";
			
	        con = DriverManager.getConnection(url1, user, password);
			return con;
	}
	
}

