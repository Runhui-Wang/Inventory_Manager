package sql_processing;
import java.sql.*;

import management_sql.connect;
public class outstock_process {
    static Connection con = connect.sqlc;
    public static int  writeStock(String sup,String product_name,String num1,String pri,String user) {
		PreparedStatement preSql;

		String sqlStr="insert into outstock(supname,stockname,outtime,num,pric,user) values(?,?,now(),?,?,?)";
		int num=0;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			preSql.setString(2, product_name);
			preSql.setString(3, num1);
			preSql.setString(4, pri);
			preSql.setString(5, user);
			
		
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			
			if(e.getMessage().equals("Not Enough in Inventory")) {
				return 4;
			}else {
				return 3;
			}
		}
		
		
		
	}
	
		public static ResultSet  findStockoneData(String num) {
			PreparedStatement preSql;

			String  data[]=new String [6];
			String sqlStr="select outstock.id,outstock.supname,outstock.stockname,outstock.outtime,outstock.num,outstock.pric ,product.stock ,outstock.`user` from outstock ,product where product.supname=outstock.supname and product.`name`=outstock.stockname and id=?";
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

			
			String sqlStr="select outstock.id,outstock.supname,outstock.stockname,outstock.outtime,outstock.num,outstock.pric,product.stock ,outstock.`user` from outstock ,product where product.supname=outstock.supname and product.`name`=outstock.stockname";
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


			String sqlStr="delete from outstock where id=?";
			int num=0;
			try {
				preSql=con.prepareStatement(sqlStr);
				preSql.setString(1,id);
				num=preSql.executeUpdate();
				return num;
				
			}catch(SQLException e) {
				return 3;
			}
		}
		

		public static int changeStockData(String sup,String sun,String num,String pric,String usre,String id) {
			PreparedStatement preSql;
				
				

				String sqlStr="UPDATE outstock set supname=? ,stockname=? ,num=?,pric=?,`user`=? where id=?";
				int num1=0;
				try {
					preSql=con.prepareStatement(sqlStr);
					preSql.setString(1,sup);
					preSql.setString(2,sun);
					preSql.setString(3,num);
					preSql.setString(4,pric);
					preSql.setString(5,usre);
					preSql.setString(6,id);
					num1=preSql.executeUpdate();
					return num1;
					
				}catch(SQLException e) {
					if(e.getMessage().equals("Insufficient")) {
						return 4;
					}else {
						return 3;
					}
				}
			}
}
