package jdbc;
import java.sql.*;

public class packages {static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
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
		sql = "SELECT * FROM packages";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String PackageName = rs.getString("PackageName");
			int roomNo = rs.getInt("roomNo");
			int foodID = rs.getInt("foodId");
			double Discount= rs.getDouble("Discount");
			System.out.print("\nPackageName: "+ PackageName);
			System.out.print("\nroomNo: " + roomNo);
			
			System.out.print("\nfoodID: "+ foodID);
			
			System.out.print("\nDiscount: "+ Discount);
			


			
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
		



