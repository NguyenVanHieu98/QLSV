package Admin;

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
public  class Display extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String a;
	private JButton btthoat;
    private JMenuBar menubar;
    private JMenu menukhoa,menuLH,menuFileHS, menuFileMH,menuBangDiem,menuChuongtrinhhoc,menuVien;
	private JMenuItem itemKhoa,itemLH,itemHS,itemMonHoc,itemBangDiem,itemChuongtrinhhoc,itemVien;
	private JPanel pCen=new JPanel(new BorderLayout());
public Display(){
			setTitle("CHUONG TRINH QUAN LY SINH VIEN");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setBounds(200, 10, 312, 280);
			setSize(1000,650);
		    taogui();
		    this.add(pCen,BorderLayout.CENTER);
			tieude();
			taomenu();
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
			menubar.add(menukhoa = new JMenu("Bảng Khoa"));menukhoa.setMnemonic('B');
			menubar.add(menuLH = new JMenu("Bảng Lớp Học"));menuLH.setMnemonic('B');
			menubar.add(menuFileHS = new JMenu("Bảng Sinh Viên"));menuFileHS.setMnemonic('B');
			menubar.add(menuFileMH = new JMenu("Bảng Môn Học"));menuFileMH.setMnemonic('B');
			menubar.add(menuBangDiem =new JMenu("Bảng Điểm"));menuBangDiem.setMnemonic('B');
			menubar.add(menuChuongtrinhhoc = new JMenu("Chương trình học"));menuChuongtrinhhoc.setMnemonic('C');
			menubar.add(menuVien = new JMenu("Viện đào tạo"));menuVien.setMnemonic('V');
			menukhoa.add(itemKhoa = new JMenuItem("Open",'O'));
			menukhoa.addSeparator();
			
			menuLH.add(itemLH = new JMenuItem("Open",'O'));
			menuLH.addSeparator();
			
			menuFileHS.add(itemHS = new JMenuItem("Open",'O'));
			menuFileHS.addSeparator();
			
			menuFileMH.add(itemMonHoc = new JMenuItem("Open",'O'));
			menuFileMH.addSeparator();
			
			menuChuongtrinhhoc.add(itemChuongtrinhhoc = new JMenuItem("Open",'O'));
			menuChuongtrinhhoc.addSeparator();
			
			menuBangDiem.add(itemBangDiem = new JMenuItem("Open",'O'));
			menuBangDiem.addSeparator();
		
			menuVien.add(itemVien = new JMenuItem("Open",'O'));
			menuVien.addSeparator();
			
			itemKhoa.addActionListener(this);
			itemLH.addActionListener(this);
		    itemHS.addActionListener(this);
			itemMonHoc.addActionListener(this);
			itemBangDiem.addActionListener(this);
			itemChuongtrinhhoc.addActionListener(this);
			itemVien.addActionListener(this);
			
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
					else if (o.equals(itemKhoa)) {
						new Khoa().setVisible(true);
						setVisible(false);
					}
					else if (o.equals(itemLH)) {
						new Lop().setVisible(true);
						setVisible(false);
					}
					else if (o.equals(itemHS)) {
						new Sinhvien().setVisible(true);
						setVisible(false);
					}
						else if (o.equals(itemMonHoc)) {
							new Monhoc().setVisible(true);
							setVisible(false);
						}
					     else if (o.equals(itemBangDiem)) {
							new Bangdiem().setVisible(true);
							setVisible(false);
						}
				
					else if (o.equals(itemChuongtrinhhoc)) {
							new Chuongtrinhhoc(a).setVisible(true);
							setVisible(false);
					}
					else if (o.equals(itemVien)) {
						new Vien().setVisible(true);
						setVisible(false);
				}
					
				} catch (Exception e2) {
					e2.getMessage();
				}
	}

}

