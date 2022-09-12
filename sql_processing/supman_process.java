package sql_processing;

import java.sql.*;

import javax.swing.JComboBox;

import management_sql.connect;
public class supman_process {
    static Connection con = connect.sqlc;

	public static int writeSup(String name) {
		
		PreparedStatement preSql;
		int  num;
		String sqlStr="insert into supplier(`name`) values (?)";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, name);
		
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}
		

	}

public static int dellSup(String name) {
		
		PreparedStatement preSql;
		int  num;
		String sqlStr="delete from supplier where `name`=?";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, name);
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}
		

	}

public static int writeSupSun(String subname ,String sunname) {
	
	PreparedStatement preSql;
	int  num;
	String sqlStr="insert into product (`name`,`supname`) VALUES(?,?)";
	
	try {
		preSql=con.prepareStatement(sqlStr);
		preSql.setString(1, sunname);
		preSql.setString(2, subname);
	
		num=preSql.executeUpdate();
		return num;
		
	}catch(SQLException e) {
		return 3;
	}
	
}
	

	public static void readSup(JComboBox cmb1) {
		

		cmb1.removeAllItems();
		cmb1.addItem("--Select Supplier--");
		
		int star=0;
		PreparedStatement preSql;
		String sqlStr="select * from supplier";
		ResultSet rs;
		try {
			preSql=con.prepareStatement(sqlStr);
			rs = preSql.executeQuery();
			while(rs.next()) {

				if(star==0) {
					star++;
				}
				
				String tempname=rs.getString("name");
				cmb1.addItem(tempname);
			}
		
			cmb1.repaint();
		}catch(SQLException e) {
		
		}
		
	}

   public static void readSun(JComboBox cmb1,String sup) {

		cmb1.removeAllItems();
		cmb1.addItem("--Select Product--");
		
		int star=0;
		PreparedStatement preSql;
		String sqlStr="select * from product where supname=?";
		ResultSet rs;
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sup);
			rs = preSql.executeQuery();
			while(rs.next()) {
				if(star==0) {
					star++;
				}
				
				String tempname=rs.getString("name");
				cmb1.addItem(tempname);
			}
		
			cmb1.repaint();
		}catch(SQLException e) {
		
		}
		
	}
   
   public static int delSunStock(String sup,String sun) {
	   
		PreparedStatement preSql;
		int  num;
		String sqlStr="DELETE from product where `name`=? and supname=?;";
		
		try {
			preSql=con.prepareStatement(sqlStr);
			preSql.setString(1, sun);
			preSql.setString(2, sup);
			num=preSql.executeUpdate();
			return num;
			
		}catch(SQLException e) {
			return 3;
		}
	   
   }
}
