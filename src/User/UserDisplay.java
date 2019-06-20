package User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import Login.Login;

public  class UserDisplay extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btthoat;
    private JMenuBar menubar;
    private JMenu menutt,menuctdt,menuBangDiem;
	private JMenuItem itemTt,itemdsmh,itemcth,itemBangDiem;
	private JPanel pCen=new JPanel(new BorderLayout());
	private static String u;
public UserDisplay(String a){
			setTitle("CHUONG TRINH QUAN LY SINH VIEN");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setBounds(200, 10, 312, 280);
			setSize(1000,650);
		    taogui();
		    this.add(pCen,BorderLayout.CENTER);
			tieude();
			taomenu();
			u = a;
			}
		void taogui() {

			Cursor cr = new Cursor(Cursor.HAND_CURSOR);
		    Box b = Box.createVerticalBox();
			ImageIcon icon = new ImageIcon("D:\\BKHN1.JPG");
			  JLabel lbl = new JLabel(icon);
			  b.add(lbl);
			  pCen.add(b,BorderLayout.NORTH);
			  b.add(btthoat = new JButton("Đăng xuất"));btthoat.addActionListener(this);btthoat.setCursor(cr);
			 			}
		void taomenu(){
			this.setJMenuBar(menubar = new JMenuBar());
			menubar.add(menutt = new JMenu("Thông tin"));menutt.setMnemonic('T');
			menubar.add(menuctdt = new JMenu("Chương trình đào tạo"));menuctdt.setMnemonic('C');	
			menubar.add(menuBangDiem =new JMenu("Bảng Điểm"));menuBangDiem.setMnemonic('B');	
			menutt.add(itemTt = new JMenuItem("Open",'O'));
			menutt.addSeparator();	
			menuctdt.add(itemdsmh = new JMenuItem("Danh sách môn học",'D'));
			menuctdt.addSeparator();
			menuctdt.add(itemcth = new JMenuItem("Chương trình đào tạo sinh viên",'C'));
			menuctdt.addSeparator();
			menuBangDiem.add(itemBangDiem = new JMenuItem("Open",'O'));
			menuBangDiem.addSeparator();
			menuBangDiem.addSeparator();
			itemTt.addActionListener(this);
			itemdsmh.addActionListener(this);
			itemcth.addActionListener(this);
			itemBangDiem.addActionListener(this);
				
		}
      void tieude(){
				JLabel lTitle=new JLabel("QUẢN LÝ TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI ",JLabel.LEFT);
				lTitle.setFont(new Font("Arial",Font.BOLD,26));
				lTitle.setForeground(Color.blue);
				this.add(lTitle,BorderLayout.NORTH);
			}
		@Override
			public void actionPerformed(ActionEvent e){	
				Object o=e.getSource();
				try {
					if(o.equals(btthoat)){
						Login window = new Login();
					     window.frame.setVisible(true);
					     setVisible(false);
					}
					else if (o.equals(itemTt)) {
						new Thongtin(u). setVisible(true);
						setVisible(false);
					}
					else if (o.equals(itemdsmh)) {
						new DSMH(u).setVisible(true);
						setVisible(false);
					}
					else if (o.equals(itemcth)) {
						new CTDT(u).setVisible(true);
						setVisible(false);
					}
						
					else if (o.equals(itemBangDiem)) {
						new BDSV(u).setVisible(true);
						setVisible(false);
					}	
					
				} catch (Exception e2) {
					e2.getMessage();
				}
	}
}

