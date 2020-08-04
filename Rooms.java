package jdbc;
import java.sql.*;

public class Rooms {
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://localhost:3306/sunshinehotel";

			static final String USER = "root";
			static final String PASS = "priya22";
			
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
						int roomNo = rs.getInt("roomNo");
						String roomType = rs.getString("roomType");
						int noOfBedrooms = rs.getInt("noOfBedrooms");
						int noOfWashrooms = rs.getInt("noOfWashroooms");
						double Price = rs.getDouble("Price");
						
						System.out.print("roomNo: " + roomNo);
						System.out.print("roomType: "+ roomType);
						System.out.print("noOfBedrooms: "+ noOfBedrooms);
						System.out.print("noOfWashrooms: "+ noOfWashrooms);
						System.out.print("Price: "+ Price);
						


						
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
					


