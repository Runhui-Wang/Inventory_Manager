package admin_panel;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import helper.Tool;
import sql_processing.instock_process;
import sql_processing.supplier_process;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

public class inventory_in extends JPanel{
    final int WIDTH=730;
	final int HEIGHT=50;

	Object columns[] ={"ID","Supplier","Name","Time","Quantity","Price","Amount"};
	JTable tableL=null;
	JScrollPane jscrollpane;
	public static DefaultTableModel  model;
	
	public static JTextField stockPricIn;
	public static JTextField stockNumIn;
	public static   JComboBox  cmbSupName;
	public static   JComboBox cmbStockName;
    public inventory_in(int x, int y,int w, int h){
        this.setBounds(x,y,w,h);
        initialize();
    }
    void initialize(){
        this.setLayout(null);
		JPanel jpan1=new JPanel();
		jpan1.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
	
		jpan1.setBounds(0, 0, WIDTH, 50);
		
		
		this.add(jpan1);
	

		JButton JB1=new JButton("Enter");
		jpan1.add(JB1);
		
	
		JButton JB2=new JButton("Delete Entry");
		jpan1.add(JB2);
		

		JButton JB3=new JButton("Change Entry");
		jpan1.add(JB3);
		

		JButton JB4=new JButton("Find Entry");
		jpan1.add(JB4);
		

		

		JPanel jpan2=new JPanel();
		jpan2.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
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
		
		stockNumIn=new JTextField(8);
		jpan2.add(stockNumIn);
		
		
		
		JLabel JL4=new JLabel("Price");
		jpan2.add(JL4);
		
		stockPricIn=new JTextField(8);
		jpan2.add(stockPricIn);
		
		
		JLabel JL5=new JLabel("Order Num");
		jpan2.add(JL5);
		
		JTextField stockNum = new JTextField(8);
		jpan2.add(stockNum);
		
		
		
		this.add(jpan2);
		table();
		this.add(jscrollpane);
		supplier_process.readSup(inventory_in.cmbSupName);

		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(cmbSupName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Select Supplier", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(cmbStockName.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "Select Product", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(stockNumIn.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Quantity", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(stockPricIn.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Price", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					String sup=(String)cmbSupName.getSelectedItem();
					String sun=(String)cmbStockName.getSelectedItem();
					String num=stockNumIn.getText();
					String pri=stockPricIn.getText();
					int a=instock_process.writeStock(sup, sun, num, pri);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "Entry failed", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "Wrong Format", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "Success", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==4) {
						JOptionPane.showMessageDialog(null, "Invalid Entry", "Message",JOptionPane.WARNING_MESSAGE);
					}
					
					
					
					
					
					
				}
				
				
			}
			
		});

		cmbSupName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				supplier_process.readSun(cmbStockName, (String )cmbSupName.getSelectedItem());
			
				
			}
			
		});
		

		JB4.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			String num=stockNum.getText();
			ResultSet rs ;
			if(num.equals("")) {

				rs = instock_process.findStockallData();

			int a=Tool.addDataTable(rs, model,7 );
			if(a==0) {
				JOptionPane.showMessageDialog(null, "No related Info", "Message",JOptionPane.WARNING_MESSAGE);
			}
				
			}else {

				
				rs=instock_process.findStockoneData(num);
				ResultSet rs1 = instock_process.findStockoneData(num);
				try {
					if(rs1.next()) {
					String sup=	rs1.getString("supname");
					String sun=	rs1.getString("stockname");
					String nu=	rs1.getString("num");
					String pr=	rs1.getString("pric");
					

					
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
					
					
					
			
				
					stockPricIn.setText(pr);
					stockNumIn.setText(nu);
				
					
					
					
					
					
					}
					
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				
				
				
			int a=Tool.addDataTable(rs, model,7 );
			if(a==0) {
				JOptionPane.showMessageDialog(null, "No related Info", "Message",JOptionPane.WARNING_MESSAGE);
			}
				
			}
			
			
		}
			
		});
		
		JB2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String num=stockNum.getText();
				if(num.equals("")) {
					JOptionPane.showMessageDialog(null, "Enter number", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					int a=instock_process.dellStockData(num);
					if(a==0) {
						JOptionPane.showMessageDialog(null, "Check Number", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "Delete Successful", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "Check Format", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==4) {
						JOptionPane.showMessageDialog(null, "Insufficient Stock", "Message",JOptionPane.WARNING_MESSAGE);
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
				
				if(stockNum.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter ID", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					
					if(cmbSupName.getSelectedIndex()==0) {

						
						JOptionPane.showMessageDialog(null, "Select Supplier", "Message",JOptionPane.WARNING_MESSAGE);
						
					}else if(cmbStockName.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(null, "Select Product", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockNumIn.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "NULL quantity", "Message",JOptionPane.WARNING_MESSAGE);
					}else if(stockPricIn.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "NULL price", "Message",JOptionPane.WARNING_MESSAGE);
					}else {
						

						sup=(String) cmbSupName.getSelectedItem();
						sun=(String) cmbStockName.getSelectedItem();
						num=stockNumIn.getText();
						pric=stockPricIn.getText();
						ID=stockNum.getText();

						int a=instock_process.changeStockData(sup, sun, num, pric, ID);
						
						
						if(a==0) {
							JOptionPane.showMessageDialog(null, "Unchanged", "Message",JOptionPane.WARNING_MESSAGE);
						}
						if(a==1) {
							JOptionPane.showMessageDialog(null, "Success", "Message",JOptionPane.WARNING_MESSAGE);
						}
						
						if(a==3) {
							JOptionPane.showMessageDialog(null, "Check Format", "Message",JOptionPane.WARNING_MESSAGE);
						}
						if(a==4) {
							JOptionPane.showMessageDialog(null, "Insufficient Stock", "Message",JOptionPane.WARNING_MESSAGE);
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
	
	private void myUpdateUI() {
		SwingUtilities.updateComponentTreeUI(this);
	}
}

