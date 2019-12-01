import java.sql.*;

public class CreateStudents {
	public static void main(String args[]) {
		Connection con = null;
		Statement stmt = null;
		
		String createString = "create table Students " + "(ID int, " + "STUDENT varchar(32), " + "COURSE varchar(32))";
		
		//		 1. Load MYSQL driver (the driver jar file must be on CLASSPATH)
		//		try {
		//			Class.forName(Values.DRIVER_CLASS_MYSQL);
		//		} catch (ClassNotFoundException e) {
		//			System.err.println(e.getMessage());
		//			System.exit(0);
		//		}

		try {
			// 2. Get connection and statements objects
			con = DriverManager.getConnection(Values.URL, Values.USER_NAME, Values.PASS_WORD);
			stmt = con.createStatement();

			// 3. Execute JDBC commands
			stmt.execute("DROP TABLE IF EXISTS Students");
			stmt.executeUpdate(createString);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());

		} finally {
			try {
				// 4. Close the connection and statement
				if (stmt != null)
					stmt.close();

				if (con != null)
					con.close();
			} catch (Exception ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
		}
	}
}