package Management;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Mangement_Style.style;
import admin_panel.inventory_in;
import sql_processing.instock_process;
import sql_processing.login_process;
import sql_processing.supman_process;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
public class login {
    final int calc_width = 700;
    final int calc_height = 500;
    String title;
    JFrame user_interface = new JFrame();
    public static JTextField account;
    public static JPasswordField pass;
    /*
     * Initialize the JFrame that holds the login page
     * user_interface - variable name for JFrame
     */
    login(String title){
        this.title = title;
        user_interface.setVisible(true);//visibility
        user_interface.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Close method
        user_interface.validate();//validation
    }
    void initialize() {
        user_interface.setTitle(title);//set interface title
        Toolkit helper =Toolkit.getDefaultToolkit();
        Dimension inter_dim = helper.getScreenSize();
        int width = (inter_dim.width-calc_width)/2;
        int height = (inter_dim.height-calc_height)/2;
        user_interface.setBounds(calc_width, calc_height, width, height);

        style login_style = new style();
        FlowLayout a = new FlowLayout(FlowLayout.CENTER);
        
        user_interface.setLayout(a);
        
        

        /*ImageIcon login_image = new ImageIcon("D:/Resume_Project/Restaurant_Managing_System/Mangement_Style/Login_Image.jpg");
        JLabel login_background = new JLabel(login_image);
        login_background.setBounds(0, 0, login_image.getIconWidth(), login_image.getIconHeight());*/
        

        JPanel surrounds_title = new JPanel();
        JLabel login = new JLabel("Login Page");
        login.setFont(login_style.title);
        surrounds_title.setLayout(a);
        surrounds_title.setBounds(0,0,500,45);
        surrounds_title.setBackground(Color.cyan);
        surrounds_title.setOpaque(false);
        surrounds_title.add(login);

        user_interface.add(surrounds_title);


        JPanel textboxes = new JPanel();
        textboxes.setLayout(a);
        textboxes.setBounds(125,45,240,230);
        JLabel account_name = new JLabel("Account Name");
        JLabel password = new JLabel("Password");
        account_name.setFont(login_style.prompt);
        password.setFont(login_style.prompt);
        account = new JTextField(17);
        JPasswordField pass = new JPasswordField(17);
        account.setFont(login_style.account);
        pass.setFont(login_style.account);
        textboxes.add(account_name);
        textboxes.add(account);
        textboxes.add(password);
        textboxes.add(pass);
        JButton enterb = new JButton("Log In");
        enterb.setFont(login_style.button);
        enterb.setPreferredSize(new Dimension(180,35));
        enterb.setBackground(new Color(255,255,255));
        textboxes.add(enterb);
        user_interface.add(textboxes);

        //Add Action Listener to the button
        //ActionListener btn = new 
        enterb.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String acc = account.getText();
                char[] pasarr = pass.getPassword();
                String pas = new String(pasarr);
                boolean exists = login_process.loginstat(acc,pas);
                if(exists){
                    System.out.println("Log in Success!");
                    int priviledge = login_process.loginaccess(acc, pas);
                    if(priviledge==1){
                        JOptionPane.showMessageDialog(null, "Employee Account", "From Server", JOptionPane.WARNING_MESSAGE);
                        employee a = new employee();
                    }else if(priviledge==2){
                        JOptionPane.showMessageDialog(null, "Admin Account", "From Server", JOptionPane.WARNING_MESSAGE);
                        admin a = new admin();
                    }else{
                        System.out.println("FATAL ERROR!");
                    }

                }else{
                    System.out.println("Log in Failed!");
                    JOptionPane.showMessageDialog(null, "Wrong Credentials", "From Server", JOptionPane.WARNING_MESSAGE);
                }
                
            }
        });

        


        //user_interface.add(login_background);
        
        //user_interface.add(login);
    }
}
