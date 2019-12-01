import java.sql.*;

public class InsertAndQueryStudents {
	public static void main(String args[]) {
		Connection con = null;
		Statement stmt = null;

		//		 1. Load MYSQL driver (the driver jar file must be on CLASSPATH)
		//		try {
		//			Class.forName(Values.DRIVER_CLASS_MYSQL);
		//		} catch (java.lang.ClassNotFoundException e) {
		//			System.err.println(e.getMessage());
		//			System.exit(0);
		//		}

		try {
			// 2. Get connection and statements objects
			con = DriverManager.getConnection(Values.URL, Values.USER_NAME, Values.PASS_WORD);
			stmt = con.createStatement();

			// 3. Execute JDBC commands
			stmt.executeUpdate("insert into Students " + "values('John-Mark', 00101, 3.99, 2006, 3)");
			stmt.executeUpdate("insert into Students " + "values('Chung-Wei', 00049, 3.65, 2005, 2)");
			stmt.executeUpdate("insert into Students " + "values('Karazim', 00150, 2.89, 2006, 3)");
			stmt.executeUpdate("insert into Students " + "values('Ramirez', 00101, 2.09, 2006, 1)");
			stmt.executeUpdate("insert into Students " + "values('Francis', 00125, 2.99, 2006, 3)");

			ResultSet rs = stmt.executeQuery("select NAME, GPA from Students");

			System.out.println("Student Name and GPA:");
			while (rs.next()) {
				String s = rs.getString("NAME");
				float f = rs.getFloat("GPA");
				System.out.println(s + " \t  " + f);
			}
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