package management_sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class connect {
    public static Connection sqlc = null; 
    public connect(String curl, String account, String password, String database){
        //Initialize Database Connection
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Initialized!");
        }catch(Exception e){
            System.out.println("INITIALIZATION FAILED! CHECK CREDENTIALS");
        }
        
        try {
            sqlc =
               DriverManager.getConnection(curl,account,password);
            System.out.println("Connected!");
        
           
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("FAILED_CONNECTION");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

    /*
     * Check for Status of server
     */
    public static void closeDB(ResultSet rs, PreparedStatement stm){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stm!=null){
            try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}