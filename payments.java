package jdbc;

import java.sql.*;

public class payments {
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
				stmt = conn.createStatement();
				String sql;
				sql = "SELECT * FROM payments";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					
					int invoiceNo = rs.getInt("invoiceNo");
					String userName = rs.getString("userName");
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					int reservationID = rs.getInt("reservationId");
					int foodID = rs.getInt("foodID");
					String paymentType = rs.getString("paymentType");
					int cardNo = rs.getInt("cardNo");
					int cvv = rs.getInt("cvv");
	                 String nameOnCard = rs.getString("nameOnCard");
					
					System.out.print("\ninvoiceNo: " + invoiceNo);
					System.out.print("userName: "+ userName);
					System.out.print("firstName: "+ firstName);
					System.out.print("lastName: "+ lastName);
					System.out.print("reservationID: "+ reservationID);
					System.out.print("foodID: "+ foodID);
					System.out.print("paymentType: "+ paymentType);
					System.out.print("cardNo: "+ cardNo);
					System.out.print("cvv: "+ cvv);
					System.out.println("nameOnCard :" + nameOnCard);
					
					


					
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


