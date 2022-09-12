package helper;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
public class Tool {
    public static void setWindowPosCenter(int WIDTH,int HEIGHT,JFrame jframe) {
		
		Toolkit kit =Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		int width=screenSize.width;
		int height=screenSize.height;
		int x=(width-WIDTH)/2;
		int y=(height-HEIGHT)/2;
		jframe.setBounds(x, y, WIDTH, HEIGHT);
		
	}
	//Function for forms
	public static  int addDataTable(ResultSet rs ,DefaultTableModel  model,int index) {
		
		int count=0;
		model.setNumRows(0);
		
		String  data[]=new String [index];
		try {
			while(rs.next()) {
				count++;
				for(int i=0;i<data.length;i++) {
					data[i]=rs.getString(i+1);
					
				}
				model.addRow(data);
				
				
			}
			rs.close();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return count;
		}
		
		
		

		
	}
}
