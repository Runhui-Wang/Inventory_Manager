package sql_processing;
import java.sql.*;

import management_sql.connect;

public class AddAccount {
    static Connection con = connect.sqlc;
    public static int  writeAccount(String account,String password,String name,String address,String emain) {
		PreparedStatement preSql;//Processed statement
		String sqlStr="insert into users(account,`password`,sname,saddress,semail) values(?,?,?,?,?)";//Sql Statement for new account
		int num=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, account);
			preSql.setString(2, password);
			preSql.setString(3, name);
			preSql.setString(4, address);
			preSql.setString(5, emain);
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			//catch exception
			return 3;
		}
	}
}
