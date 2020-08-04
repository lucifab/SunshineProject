package jdbc;
import java.sql.*;

public class cancellation {
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
		conn = DriverManager.getConnection( DB_URL, USER, PASS);
		System.out.println("Creating statement");
		stmt = conn.createStatement();
		String sql;
		sql = "SELECT * FROM cancelation";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			int CancelationID = rs.getInt("CancelationID");
			int reservationID = rs.getInt("reservationId");
			int roomNo = rs.getInt("roomNo");
			int invoiceNo = rs.getInt("invoiceNo");
			String userName = rs.getString("userName");
			Date reservationDate = rs.getDate("reservationDate");
			Date cancelationDate = rs.getDate("cancelationDate");
			
			System.out.print("\nCancelationID: " + CancelationID);
			System.out.print("\nreservationID: "+ reservationID);
			System.out.print("\nroomNo: "+  roomNo);
			System.out.print("\ninvoiceNo: "+ invoiceNo);
			
			System.out.print("\nuserName: "+ userName);
			
			System.out.print("\nreservationDate: "+ reservationDate);
			System.out.print("\ncancelationDate: "+ cancelationDate);
			
			


			
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





