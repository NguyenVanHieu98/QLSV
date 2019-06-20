package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import Admin.Display;
import Connect.Connect;
import User.UserDisplay;

import java.awt.Font;
import javax.swing.JPasswordField;

public class Login {

 public JFrame frame;
 private JTextField textUser;
 private JPasswordField txtPass;
 private Connection con;
 private ResultSet rsSV;
 private String tk;

 
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     Login window = new Login();
     window.frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }
 
 int NumberOfRecords()throws Exception{
		int bookmark=rsSV.getRow();
		rsSV.last();
		int somautin=rsSV.getRow();
		rsSV.absolute(bookmark);
		return somautin;
	}
 @SuppressWarnings("deprecation")
void loaddata(){
		try {
			con=Connect.CreateMsAccess("QLSV");
			rsSV=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("select * from login where tk = '"+textUser.getText()+"' and mk = '"+txtPass.getText()+"'");
			if(NumberOfRecords()<1) {
				JOptionPane.showMessageDialog(null, "Tài khoản, mật khẩu không chính xác.",
						"Thông Báo",JOptionPane.QUESTION_MESSAGE);
			}else {
				if(textUser.getText().equals("admin")) {
					new Display().setVisible(true);
					frame.setVisible(false);
				}
				else {
					tk = textUser.getText();
					new UserDisplay(tk).setVisible(true);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
 public Login() {
  taogui();
 }

 private void taogui() {
  frame = new JFrame();
  frame.setBounds(200, 10, 312, 280);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setSize(1000,650);
  frame.getContentPane().setLayout(null);
  
  JLabel lblLogin = new JLabel("Login");
  lblLogin.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 50));
  lblLogin.setBounds(450, 10, 200, 80);
  frame.getContentPane().add(lblLogin);

  JLabel lblNewLabel = new JLabel("Username");
  lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 26));
  lblNewLabel.setBounds(150, 110, 400, 40);
  frame.getContentPane().add(lblNewLabel);
  
  textUser = new JTextField();
  textUser.setBounds(300, 110, 400, 40);
  frame.getContentPane().add(textUser);
  textUser.setColumns(10);

  JLabel lblNewLabel_1 = new JLabel("Password");
  lblNewLabel_1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 26));
  lblNewLabel_1.setBounds(150, 210, 400, 40);
  frame.getContentPane().add(lblNewLabel_1);

  txtPass = new JPasswordField();
  txtPass.setBounds(300, 210, 400, 40);
  frame.getContentPane().add(txtPass);



  JLabel lblMessager = new JLabel("");
  lblMessager.setFont(new Font("Tahoma", Font.PLAIN, 12));
  lblMessager.setBounds(21, 208, 265, 26);
  frame.getContentPane().add(lblMessager);

  JButton btnLogin = new JButton("Login");
  btnLogin.setBounds(300, 300, 100, 50);
  frame.getContentPane().add(btnLogin);
  
  JButton btnReset = new JButton("Reset");
  btnReset.setBounds(600, 300, 100, 50);
  frame.getContentPane().add(btnReset);


  btnLogin.addActionListener(new ActionListener() {
	  @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		  try {
		    if(textUser.getText().equals("") && txtPass.getText().equals("")) {
		    	JOptionPane.showMessageDialog(null, "Vui lòng nhâp tài khoản và mật khẩu.",
						"Thông Báo",JOptionPane.QUESTION_MESSAGE);
		    	
		    }else if (textUser.getText().equals("") ) {
		     
		    	JOptionPane.showMessageDialog(null, "Vui lòng nhâp tài khoản.",
						"Thông Báo",JOptionPane.QUESTION_MESSAGE);
		    
		    }else if(txtPass.getText().equals("") ) {
		    	JOptionPane.showMessageDialog(null, "Vui lòng nhâp mật khẩu.",
						"Thông Báo",JOptionPane.QUESTION_MESSAGE);
		    	
		    }else{
		    	
					loaddata();
		    }
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		    
		   }
		  });
  
  btnReset.addActionListener(new ActionListener() {
	 public void actionPerformed(ActionEvent e) {
			textUser.setText("");
			txtPass.setText("");
			lblMessager.setText("");
				    
			}
		});	
  
	}
}
  