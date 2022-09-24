package sql_processing;
import java.sql.*;

import management_sql.connect;
public class instock_process {
    static Connection con = connect.sqlc;//connection
    public static int  writeStock(String sup,String product_name,String num1,String pri) {
		PreparedStatement preSql;

		String sqlStr="insert into instock(supname,stockname,intime,num,pric) values(?,?,now(),?,?)";
		int num=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			preSql.setString(2, product_name);
			preSql.setString(3, num1);
			preSql.setString(4, pri);
			
		
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}
	}
	public static ResultSet  findStockoneData(String num) {
		PreparedStatement preSql;
		String  data[]=new String [6];
		String sqlStr="select instock.id,instock.supname,instock.stockname,instock.intime,instock.num,instock.pric,product.stock from instock,product where product.supname=instock.supname and product.`name`=instock.stockname and id=?";
		ResultSet rs=null ;
		int count=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, num);
	
		
			rs = preSql.executeQuery();
			return rs;
			
		}catch(SQLException e) {
			return  rs;
		}
	}

	public static ResultSet  findStockallData() {
		PreparedStatement preSql;

		String  data []=new String [6];
		String sqlStr="select instock.id,instock.supname,instock.stockname,instock.intime,instock.num,instock.pric,product.stock from instock,product where product.supname=instock.supname and product.`name`=instock.stockname";
		ResultSet rs=null ;
		int count=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			return rs;
			
		}catch(SQLException e) {
			return  rs;
		}
	}
	public static int dellStockData(String id) {
		PreparedStatement preSql;

		String sqlStr="delete from instock where id=?";
		int num=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,id);
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			
			
			if(e.getMessage().equals("Insufficient Inventory")) {
				return 4;
			}else {
				return 3;
			}
			
		}
	}
	public static int changeStockData(String sup,String sun,String num,String pric,String id) {
	PreparedStatement preSql;
		
		

		String sqlStr="UPDATE instock set supname=? ,stockname=? ,num=?,pric=? where id=?";
		int num1=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1,sup);
			preSql.setString(2,sun);
			preSql.setString(3,num);
			preSql.setString(4,pric);
			preSql.setString(5,id);
			num1=preSql.executeUpdate();
			return num1;
			
		}catch(SQLException e) {
			if(e.getMessage().equals("Insufficient Inventory")) {
				return 4;
			}else {
				return 3;
			}
			
			
		}
	}
}
