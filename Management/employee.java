package Management;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import admin_panel.inventory_in;
import admin_panel.inventory_out;
import helper.Tool;
import sql_processing.instock_process;
import sql_processing.outstock_process;
import sql_processing.supman_process;

public class employee {
    String buton[] ={"Enter","Retrieve"};
	String butonName[] ={"stockIn","stockOut"};
	
	final int WIDTH=900;
	final int HEIGHT=600;
	public JFrame jframe=new JFrame();
	
	public employee() {
		

		init();
		jframe.setVisible(true); 
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
	}
	
	void init() {
		
		
		jframe.setLayout(null);
		jframe.setTitle("WareHouse Management");
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
		JPanel jpanel1=new JPanel();
		JLayeredPane jpanel2 = new JLayeredPane();
		jpanel1.setBounds(5, 5, 150, HEIGHT-10);
		jpanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		


		inventory_in inpan=new inventory_in(0, 0, 665+50, HEIGHT-10);
		jpanel2.add(inpan, (Integer) (JLayeredPane.PALETTE_LAYER));
		

		inventory_out outpan=new inventory_out(0, 0, 665+50, HEIGHT-10);
		jpanel2.add(outpan, (Integer) (JLayeredPane.PALETTE_LAYER));

		
		

		jpanel2.setBounds(215-50, 5, 680+50, HEIGHT-10);
		
		
		jframe.add(jpanel2);
		jframe.add(jpanel1);
		
		
		
		
		for(int i=0;i<buton.length;i++) {
			JButton bu=new JButton(buton[i]);
			jpanel1.add(bu);
			bu.setName(butonName[i]);
			bu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					
					JButton jbl=(JButton)e.getSource();
					if(jbl.getName().equals(butonName[0])) {
						jpanel2.moveToFront(inpan);
						supman_process.readSup(inventory_in.cmbSupName);
						
					
						
					}
					
					if(jbl.getName().equals(butonName[1])) {
						jpanel2.moveToFront(outpan);
						supman_process.readSup(inventory_out.cmbSupName);
						
					}
			
					
					
					
					
				}
				
			});
			
			
			
		}
		
		
		
		

		
		
		
		
		
	}


}
