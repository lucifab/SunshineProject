package jdbc;
import java.sql.*;

public class food {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/sunshinehotel?autoReconnect=true&useSSL=false";

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
				String sql;
				stmt = conn.createStatement();
				sql = "SELECT * FROM food";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					int foodid = rs.getInt("roomNo");
					
					int foodItem = rs.getInt("noOfBedrooms");
					
					double price = rs.getDouble("price");
					
					System.out.print("\nfoodid: " + foodid);
					System.out.print("\nfoodItem: "+ foodItem);
					
					System.out.print("\nprice: "+ price);
					


					
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
				


