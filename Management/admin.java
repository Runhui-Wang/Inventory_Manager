package Management;
import javax.swing.*;
import java.awt.*;
import Mangement_Style.style;
import admin_panel.inventory_in;
import admin_panel.inventory_out;
import helper.Tool;
import admin_panel.AddStaffAccount;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class admin {
    final int calc_width = 1200;
    final int calc_height = 600;
    String title;
    JFrame admin_interface = new JFrame();
    admin(){
        initialize();
        admin_interface.setVisible(true);//visibility
        admin_interface.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Close method
        admin_interface.validate();//validation
    }

    void initialize(){
        admin_interface.setTitle(title);//set interface title
        Tool.setWindowPosCenter(calc_width, calc_height, admin_interface);

        JPanel ad_1 = new JPanel();
        ad_1.setBounds(5,5,150,calc_height-10);
        JLayeredPane ad_2 = new JLayeredPane();
        ad_2.setBounds(215-50, 5, 730, calc_height-10);
        //ad_2.setBounds(205,5,1000,calc_height-10);
        //ad_2.setBackground(Color.YELLOW);
        inventory_in pan_1 = new inventory_in(215, 0, 715, calc_height-10);
        inventory_out pan_2 = new inventory_out(215, 0, 715, calc_height-10);
        JButton inButton = new JButton("Add Inventory Entry");
        inButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ad_2.moveToFront(pan_1);
            }
        });
        
        ad_1.add(inButton);
        JButton outButton = new JButton("Delete Entry");
        outButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ad_2.moveToFront(pan_2);
            }
        });
        ad_1.add(outButton);
        JMenuBar admin_bar = new JMenuBar();
        JMenu user_man = new JMenu("USER MANAGEMENT");
        JMenu warehouse_man = new JMenu("Warehouse MANAGEMENT");
        JMenuItem ico1 = new JMenuItem("ADD User");
        ico1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddStaffAccount a=new AddStaffAccount();
			}
			
		});
        JMenuItem ico2 = new JMenuItem("DELETE User");
        user_man.add(ico1);
        user_man.add(ico2);
        admin_bar.add(user_man);
        admin_bar.add(warehouse_man);
        //admin_interface.add(admin_bar);
        admin_interface.setJMenuBar(admin_bar);







       
        ad_2.add(pan_1,(Integer)(JLayeredPane.PALETTE_LAYER));
        ad_2.add(pan_2,(Integer)(JLayeredPane.PALETTE_LAYER));
        
        ad_1.setBackground(Color.BLACK);
        admin_interface.add(ad_1);
        admin_interface.add(ad_2);
    }
}
