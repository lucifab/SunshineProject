package jdbc; 
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class newuser {
    private static Object connection;
    public static void main (String[] args) throws SQLException {
        Scanner scanner=new Scanner(System.in);
                 
                Connection conn = null; 
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sunshinehotel","toneve","12345");
        } catch (SQLException ex) {
            Logger.getLogger(AddSession.class.getName()).log(Level.SEVERE, null, ex);
        }
                System.out.println("Connection to database was successfully established !");
                Statement st = conn.createStatement();
 
                System.out.println("Enter username: ");
                String username = scanner.toString();
                System.out.println("Enter firstName: ");
                String firstName=scanner.next();
                System.out.println("Enter lastName: ");
                String lastName =scanner.next();
                System.out.println("Enter userEmail: ");
                String userEmail = scanner.next();
                System.out.println("password: ?");
                String password =scanner.next();
                System.out.println("Address1: ");
                String Address1 = scanner.next();
                System.out.println("City");
                String City = scanner.next();
                System.out.println("State");
                String State = scanner.next();
                System.out.println("Country");
                String Country = scanner.next();
                System.out.println("PostalCode");
                String PostalCode = scanner.next();
                System.out.println("phoneNumber");
                int phoneNumber = scanner.nextInt();
                
                
                 
                PreparedStatement ps = connection.prepareStatement("INSERT INTO AddSession VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, username);
                ps.setString(2, firstName);
                ps.setString(3, lastName);
                ps.setString(4, userEmail);
                ps.setString(5, password);
                ps.setString(6, Address1);
                ps.setString(7, City);
                
                
                
                ps.setString(8, State);
                ps.setString(9, Country);
                ps.setString(10, PostalCode);
                ps.setInt(11, phoneNumber);
                
  
                ps.executeUpdate();
                System.out.println("All values were successfully added to the database !"); 
    }    
}
	


