package jdbc;
import java.sql.*;

public class feedback {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
static final String DB_URL = "jdbc:mysql://localhost:3306/sunshinehotel?autoReconnect=true&useSSl=false";

static final String USER = "root";
static final String PASS = "Park";

public static void main(String[] args)
{
	Connection conn = null;
	Statement stmt = null;
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Creating statement");
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT * FROM feedback";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			
			int feedbackNo = rs.getInt("feedbackNo");
			String username = rs.getString("userName");
			int reservationID = rs.getInt("reservationID");
			int roomNo = rs.getInt("roomNo");
			String rating = rs.getString("rating");
			String review = rs.getString("review");
			
			System.out.print("\nfeedbackNo: " + feedbackNo);
			System.out.print("\nusername: "+ username);
			
			System.out.print("\nreservationID: "+ reservationID);
			System.out.print("\nroomNo: " + roomNo);
			System.out.print("\nrating: " + rating);
			System.out.print("\nreview: "+ review);
			


			
		}
		rs.close();
		stmt.close();
		conn.close();
	}
	catch (SQLException se) {
		se.printStackTrace();
	}
	catch(Exception e) {
		e.printStackTrace();}
	finally {
		try {
			if(stmt!=null)
           stmt.close();
		}catch(SQLException se2)
		{}
		try {
			if(conn!=null)
				conn.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
	}
	System.out.println("its done");
}
			
		
		


}



