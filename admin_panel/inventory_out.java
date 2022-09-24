package admin_panel;


import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import helper.Tool;
import sql_processing.outstock_process;
import sql_processing.staff_process;
import sql_processing.supplier_process;


public class inventory_out extends JPanel{
    final int WIDTH=730;
	final int HEIGHT=50;

	
	
	Object columns[] ={"Order Num","Supplier","Product","Retrieval Time","Quantity","Price","Stock","Client"};
	JTable tableL=null;
	JScrollPane jscrollpane;
	public static DefaultTableModel  model;
	
	public static JTextField stockPricOut;
	public static JTextField stockNumOut;
	public static JTextField stockUser;
	public static   JComboBox  cmbSupName;
	public static   JComboBox cmbStockName;
	
	
	public inventory_out(int x,int y,int width,int height) {
		this.setBounds(x, y, width, height);
		init();
	}
	
	
	void init() {
		this.setLayout(null);

		JPanel jpan1=new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		jpan1.setBounds(0, 0, WIDTH, 50);
		
		
		this.add(jpan1);
	
	
		JButton JB1=new JButton("Retrieve");
		jpan1.add(JB1);
		

		JButton JB2=new JButton("Delete Retrieval");
		jpan1.add(JB2);
		

		JButton JB3=new JButton("Change Retrieval");
		jpan1.add(JB3);
		

		JButton JB4=new JButton("Find Retrieval");
		jpan1.add(JB4);
		
		
		
		JPanel jpan2=new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.LEFT,12,15));
	
		jpan2.setBounds(0, 60, WIDTH, 100);

		
		JLabel JL1=new JLabel("Supplier");
		jpan2.add(JL1);

		
		
	
		cmbSupName=new JComboBox();    
		cmbSupName.addItem("--Select Supplier--");
		jpan2.add(cmbSupName);
		
		
		JLabel JL2=new JLabel("Product");
		jpan2.add(JL2);
		
		
		cmbStockName=new JComboBox();    
		cmbStockName.addItem("--Select Product--");
		jpan2.add(cmbStockName);
		
		
		
		JLabel JL3=new JLabel("Quantity");
		jpan2.add(JL3);
		
		stockNumOut=new JTextField(8);
		jpan2.add(stockNumOut);
		
		
		
		JLabel JL4=new JLabel("Price");
		jpan2.add(JL4);
		
		stockPricOut=new JTextField(8);
		jpan2.add(stockPricOut);
		
		//购买人
		JLabel JL5=new JLabel("Client");
		jpan2.add(JL5);
		
		stockUser=new JTextField(8);
		jpan2.add(stockUser);
		
		
		
		
		JLabel JL6=new JLabel("Order Num");
		jpan2.add(JL6);
		
		JTextField stockNum = new JTextField(8);
		jpan2.add(stockNum);
		

		
		this.add(jpan2);
		table();
		this.add(jscrollpane);
		

		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				supplier_process.readSun(cmbStockName, (String )cmbSupName.getSelectedItem());
			}
		});
		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cmbSupName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "PLease select supplier", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(cmbStockName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Select Product", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(stockNumOut.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Quantity", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(stockPricOut.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Price", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(stockUser.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please enter username", "Message",JOptionPane.WARNING_MESSAGE);
					
				}
				else{
					String sup=(String)cmbSupName.getSelectedItem();
					String sun=(String)cmbStockName.getSelectedItem();
					String num=stockNumOut.getText();
					String pri=stockPricOut.getText();
					String usrname=stockUser.getText();
					
					
					
					int a=outstock_process.writeStock(sup, sun, num, pri, usrname);
					
					
					
					
					
					if(a==0) {
						JOptionPane.showMessageDialog(null, "Failed", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "Please enter required fields", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "Success", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==4) {
						JOptionPane.showMessageDialog(null, "Insuffient", "Message",JOptionPane.WARNING_MESSAGE);
					}
					
					
					
					
					
				}
				
				
			}
			
		});
	
		JB4.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String num=stockNum.getText();
			ResultSet rs ;
			if(num.equals("")) {
				rs = outstock_process.findStockallData();
			int a=Tool.addDataTable(rs, model,8 );
			if(a==0) {
				JOptionPane.showMessageDialog(null, "No valid Info found", "Message",JOptionPane.WARNING_MESSAGE);
			}
				
			}else {
				
				rs=outstock_process.findStockoneData(num);
				ResultSet rs1 = outstock_process.findStockoneData(num);
				try {
					if(rs1.next()) {
					String sup=	rs1.getString("supname");
					String sun=	rs1.getString("stockname");
					String nu=	rs1.getString("num");
					String pr=	rs1.getString("pric");
					String user=rs1.getString("user");

					
			
					
					for(int i=0;i<cmbSupName.getItemCount();i++) {
						String a=(String) cmbSupName.getItemAt(i);
						if(a.equals(sup)) {
							cmbSupName.setSelectedIndex(i);
							cmbSupName.repaint();
							for(int j=0;j<cmbStockName.getItemCount();j++) {
								String c=(String) cmbStockName.getItemAt(j);
								if(c.equals(sun)) {
									cmbStockName.setSelectedIndex(j);
									cmbStockName.repaint();
								}
									
							}
						}
							
					}
					
					
					
			
				
					stockPricOut.setText(pr);
					stockNumOut.setText(nu);
					stockUser.setText(user);
				
					
					
					
					
					
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			int a=Tool.addDataTable(rs, model,8 );
			if(a==0) {
				JOptionPane.showMessageDialog(null, "No info", "Message",JOptionPane.WARNING_MESSAGE);
			}
				
			}
			
			
		}
			
		});
		
		
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String num=stockNum.getText();
				if(num.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Enter a valid number", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					
					
					int c=staff_process.showTimeOut(num);
					if(c==1) {
						int a=outstock_process.dellStockData(num);
						if(a==0) {
							JOptionPane.showMessageDialog(null, "Check Existstence", "Message",JOptionPane.WARNING_MESSAGE);
						}
						if(a==1) {
							JOptionPane.showMessageDialog(null, "Delete Success", "Message",JOptionPane.WARNING_MESSAGE);
						}
						if(a==3) {
							JOptionPane.showMessageDialog(null, "Check Format", "Message",JOptionPane.WARNING_MESSAGE);
						}
					
					}else {
						System.out.println(c);
						JOptionPane.showMessageDialog(null, "Runtime Error", "Message",JOptionPane.WARNING_MESSAGE);
					}
					
					
					
				}
				
				
				
				
			}
			
		});
		

		JB3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String sup=null;
				String sun=null;
				String num=null;
				String pric=null;
				String ID=null;
				String user=null;
				if(stockNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter ID #", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					
					if(cmbSupName.getSelectedIndex()==0) {

						JOptionPane.showMessageDialog(null, "Select Supplier", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(cmbStockName.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "Select product", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockNumOut.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Quantity cant be null", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockPricOut.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Price cant be null", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockUser.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Username cant be null", "Message",JOptionPane.WARNING_MESSAGE);
						
						
					}else{
						
						int c=staff_process.showTimeOut(stockNum.getText());
						if(c==1) {
							//Write 
							sup=(String) cmbSupName.getSelectedItem();
							sun=(String) cmbStockName.getSelectedItem();
							num=stockNumOut.getText();
							pric=stockPricOut.getText();
							ID=stockNum.getText();
							user=stockUser.getText();
							int a=outstock_process.changeStockData(sup, sun, num, pric,user, ID);
							if(a==0) {
								JOptionPane.showMessageDialog(null, "Nothing changed", "Message",JOptionPane.WARNING_MESSAGE);
							}
							if(a==1) {
								JOptionPane.showMessageDialog(null, "Success!", "Message",JOptionPane.WARNING_MESSAGE);
							}
							
							if(a==3) {
								JOptionPane.showMessageDialog(null, "Check Format", "Message",JOptionPane.WARNING_MESSAGE);
							}
							if(a==4) {
								JOptionPane.showMessageDialog(null, "Insufficient Stock", "Message",JOptionPane.WARNING_MESSAGE);
							}
							
							
							
						}else {
							System.out.println(c);
							JOptionPane.showMessageDialog(null, "Runtime Error", "Message",JOptionPane.WARNING_MESSAGE);
						}
						
				
					
						
					}
					
					
					
				}
				
			}
			
			
			
			
			
		});
		

		
	}
	
	
	void table() {
		
		tableL=getTable();
		jscrollpane=new JScrollPane(tableL);
		tableL.setPreferredSize(new Dimension(WIDTH-30,10000));
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jscrollpane.setBounds(0, 170, WIDTH-30, 360);
		
	}
	
	JTable getTable() {
		if(tableL==null) {
			tableL=new JTable();
			model=new DefaultTableModel() {
				public boolean isCellEditable(int row, int column)
				{
				return false;
				}
			};
			
		
		model.setColumnIdentifiers(columns);
		tableL.setModel(model);
		
		tableL.getTableHeader().setReorderingAllowed(false);
		tableL.getTableHeader().setResizingAllowed(false);

			
			
		}
		
		
		
		return tableL;
	}
}
