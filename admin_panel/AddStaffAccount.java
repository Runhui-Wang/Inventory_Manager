package admin_panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import helper.Tool;
import sql_processing.AddAccount;


public class AddStaffAccount {

	
	final int WIDTH=220;
	final int HEIGHT=280;
	
	JFrame jframe=new JFrame();
	
	
	public AddStaffAccount(){
		
		init();
		jframe.setVisible(true); 
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.validate();
		
	}
	
	
	void init() {
		
		jframe.setTitle("Add Account");
		jframe.setLayout(new FlowLayout(FlowLayout.CENTER));
		Tool.setWindowPosCenter(WIDTH, HEIGHT, jframe);
	
		JLabel JL=new JLabel("Add Account");
		jframe.add(JL);
		
		JLabel JL1=new JLabel("Employee Number:");
		jframe.add(JL1);
		JTextField JT1=new JTextField(10);
		jframe.add(JT1);
		
		JLabel JL5=new JLabel("Password:");
		jframe.add(JL5);
		JPasswordField JT5=new JPasswordField(10);
		jframe.add(JT5);
		
		
		JLabel JL6=new JLabel("Reenter Password:"); 
		jframe.add(JL6);
		JPasswordField JT6=new JPasswordField(10);
		jframe.add(JT6);
		

		
		
		
		JLabel JL2=new JLabel("Name:");
		jframe.add(JL2);
		JTextField JT2=new JTextField(10);
		jframe.add(JT2);
		
		JLabel JL3=new JLabel("Address:");
		jframe.add(JL3);
		JTextField JT3=new JTextField(10);
		jframe.add(JT3);
		
		
		JLabel JL4=new JLabel("Email:");
		jframe.add(JL4);
		JTextField JT4=new JTextField(10);
		jframe.add(JT4);

		JButton JB=new JButton("Add");
		jframe.add(JB);
		
		JButton JB1=new JButton("Reset");
		jframe.add(JB1);

		JB1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JT1.setText("");
				JT2.setText("");
				JT3.setText("");
				JT4.setText("");
				JT5.setText("");
				JT6.setText("");
			}
	
			
		});
		
		

		JB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String account=JT1.getText();
				String password=new String(JT5.getPassword());
				String okpassword=new String(JT6.getPassword());
				String name=JT2.getText();
				String address=JT3.getText();
				String emain=JT4.getText();
				
				if(account.equals("")) {
					JOptionPane.showMessageDialog(null, "Empty Field", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(password.equals("")) {
					JOptionPane.showMessageDialog(null, "Empty Field", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(okpassword.equals("")) {
					JOptionPane.showMessageDialog(null, "Empty Field", "Message",JOptionPane.WARNING_MESSAGE);
				}else if(!okpassword.equals(password)) {
					JOptionPane.showMessageDialog(null, "Passwords do not match", "Message",JOptionPane.WARNING_MESSAGE);
				}else {
					
					int a=AddAccount.writeAccount(account, okpassword, name, address, emain);
					
					if(a==0) {
						JOptionPane.showMessageDialog(null, "Failed", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==1) {
						JOptionPane.showMessageDialog(null, "Success", "Message",JOptionPane.WARNING_MESSAGE);
					}
					if(a==3) {
						JOptionPane.showMessageDialog(null, "Repeated Emp #", "Message",JOptionPane.WARNING_MESSAGE);
					}
					
					
					
					
					
				}

			}
	
			
		});
		
		
		
		
		
		
	}
}
