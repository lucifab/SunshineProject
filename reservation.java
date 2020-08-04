package jdbc;
import java.sql.*;

public class reservation {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/sunshinehotel";

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
				sql = "SELECT * FROM Rooms";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					
					
					int reservationID = rs.getInt("reservationID");
					String userName = rs.getString("userName");
					int foodID = rs.getInt("foodID");
					String PackageName = rs.getString("PackageName");
					int roomNo = rs.getInt("roomNo");
					Date checkIn= rs.getDate("checkIn");
					Date reservationDate = rs.getDate("reservationDate");
	                 int adults = rs.getInt("adults");
	                 int children = rs.getInt("children");
					
					System.out.print("reservationID: " + reservationID);
					System.out.print("userName: "+ userName);
					System.out.print("foodID: "+ foodID);
					System.out.print("PackageName: "+ PackageName);
					System.out.print("roomNo: "+ roomNo);
					System.out.print("checkIn: "+ checkIn);
					System.out.print("reservationDate: "+ reservationDate);
					System.out.print("adults: "+ adults);
					System.out.print("children: "+ children);
					
					


					
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
				


