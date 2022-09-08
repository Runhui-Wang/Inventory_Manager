package sql_processing;

import java.sql.*;

import management_sql.connect;

public class login_process {
    static Connection curr = connect.sqlc;
    //Success = T, Fail =F
    public static boolean loginstat(String account, String password){
        PreparedStatement prestatement;
        ResultSet rs;
        String infoquery = "select * from account where Account = ? and Password = ?;";
        try {
            prestatement = curr.prepareStatement(infoquery);
            prestatement.setString(1, account);
            prestatement.setString(2, password);
            rs = prestatement.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Preprocess failed");
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    public static int loginaccess(String account, String password){
        PreparedStatement prestatement;
        ResultSet rs;
        String infoquery = "select * from account where Account = ? and Password = ?;";
        try {
            prestatement = curr.prepareStatement(infoquery);
            prestatement.setString(1, account);
            prestatement.setString(2, password);
            rs = prestatement.executeQuery();
            if(rs.next()){
                if(rs.getString("Access").equals(1)){
                    return 1;//Normal User
                }else{
                    return 2;//Admin
                }
                
            }else{
                return 0; //error
            }
        } catch (SQLException e) {
            System.out.println("Preprocess failed");
            System.out.println(e.getStackTrace());
            return 0;
        }
    }
}
