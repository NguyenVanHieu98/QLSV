package User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import Connect.Connect;


public class Thongtin extends JDialog implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	private JTextField tfMS,tfHT,tfgt,tfDC,tfMail,tfMSL;
	private JButton btLuu,btSua,btThoat;
	private JTable table;
	private JPanel pCen=new JPanel(new BorderLayout());

	private Connection con;
	private ResultSet rsSV;
	private static String u;

	public Thongtin(String m) {

		setTitle("THÔNG TIN SINH VIÊN");
	   
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 10, 312, 280);
		setSize(1000,650);		     		
		this.add(pCen,BorderLayout.CENTER);
		
		tieude();
		taogui();
		khoaText(true);
		LoadData(m);
		u = m;
		try {
			rsSV.first();
		
		} catch (Exception e) {
		}
	}

	//hàm để load dữ liệu lên form
	void LoadData(String n){
		try {
			con=Connect.CreateMsAccess("QLSV");
			rsSV=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("select * from sinhvien where mssv = '"+n+"'");
			while(rsSV.next()){
				@SuppressWarnings("unused")
				String[]row={
						rsSV.getString("mssv"),
						rsSV.getString("hoten"),
						rsSV.getString("gioitinh"),
						rsSV.getString("diachi"),
						rsSV.getString("email"),
						rsSV.getString("malop")
				};
				Display2Text();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	void Display2Text() {
		try {
			tfMS.setText(rsSV.getString("mssv"));
			tfHT.setText(rsSV.getString("hoten"));
			tfgt.setText(rsSV.getString("gioitinh"));
			tfDC.setText(rsSV.getString("diachi"));
			tfMail.setText(rsSV.getString("email"));
			tfMSL.setText(rsSV.getString("malop"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void tieude(){
		JLabel lTitle=new JLabel("THÔNG TIN SINH VIÊN",JLabel.CENTER);
		lTitle.setFont(new Font("Arial",Font.BOLD,28));
		lTitle.setForeground(Color.blue);
		this.add(lTitle,BorderLayout.NORTH);
	}
	void taogui(){
		Box bx = Box.createHorizontalBox();
		ImageIcon icon = new ImageIcon("D:\\icon.JPG");
		  JLabel lbl = new JLabel(icon);
		  bx.add(lbl);

		Box b=Box.createVerticalBox();Box b1=Box.createHorizontalBox();
		Box b2=Box.createHorizontalBox();Box b3=Box.createHorizontalBox();
		Box b4=Box.createHorizontalBox();Box b5=Box.createHorizontalBox();
		Box b6=Box.createHorizontalBox();Box b7=Box.createHorizontalBox();
	
		JLabel l1,l2,l3,l4,l5,l6;
		b1.add(Box.createHorizontalStrut(30));b1.add(l1=new JLabel("Mã Số Sinh Viên : ",JLabel.RIGHT));b1.add(tfMS=new JTextField(20));
		b2.add(Box.createHorizontalStrut(30));b2.add(l2=new JLabel("Họ Tên Sinh Viên:",JLabel.RIGHT));b2.add(tfHT=new JTextField(20));
		b3.add(Box.createHorizontalStrut(30));b3.add(l3=new JLabel("Giới Tính:",JLabel.RIGHT));b3.add(tfgt=new JTextField(20));
		b5.add(Box.createHorizontalStrut(30));b5.add(l4=new JLabel("Địa Chỉ Liên Lạc:",JLabel.RIGHT));b5.add(tfDC=new JTextField(20));
	    b6.add(Box.createHorizontalStrut(30));b6.add(l5=new JLabel("Email liên lạc:",JLabel.RIGHT));b6.add(tfMail=new JTextField(20));
	    b7.add(Box.createHorizontalStrut(30));b7.add(l6=new JLabel("Mã Số Lớp :",JLabel.RIGHT));b7.add(tfMSL=new JTextField(20));
	    l1.setForeground(Color.blue);
		l2.setForeground(Color.blue);
		l3.setForeground(Color.blue);
		l4.setForeground(Color.blue);
		l5.setForeground(Color.blue);
		l6.setForeground(Color.blue);
		l2.setPreferredSize(l1.getPreferredSize());
		l3.setPreferredSize(l1.getPreferredSize());
		l4.setPreferredSize(l1.getPreferredSize());
		l5.setPreferredSize(l1.getPreferredSize());
		l6.setPreferredSize(l1.getPreferredSize());
		
		Cursor cur=new Cursor(Cursor.HAND_CURSOR);
		
		b4.add(Box.createHorizontalStrut(60));
	 
		b4.add(btLuu=new JButton("LƯU"));btLuu.addActionListener(this);
		btLuu.setEnabled(false);btLuu.setCursor(cur);
		b4.add(btSua=new JButton("SỬA"));btSua.addActionListener(this);btSua.setCursor(cur);
		b4.add(btThoat= new JButton("THOÁT")); btThoat.addActionListener(this);btThoat.setCursor(cur);
		b4.add(Box.createHorizontalStrut(30));
		b.add(bx);
		b.add(Box.createHorizontalStrut(10));
		b.add(b1);b.add(Box.createVerticalStrut(10));
		b.add(b2);b.add(Box.createVerticalStrut(10));
		b.add(b3);b.add(Box.createVerticalStrut(10));
	    b.add(b5);b.add(Box.createVerticalStrut(7));
	    b.add(b6);b.add(Box.createVerticalStrut(7));
	    b.add(b7);b.add(Box.createVerticalStrut(7));

		b.add(b4);b.add(Box.createVerticalStrut(7));
		pCen.add(b,BorderLayout.NORTH);
		
	}

	void khoaText(boolean status){
		tfMS.setEditable(!status);
		tfHT.setEditable(!status);
		tfgt.setEditable(!status);
		tfDC.setEditable(!status);
		tfMail.setEditable(!status);
		tfMSL.setEditable(!status);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
 
		Object o=e.getSource();
		try {
             if(e.getSource().equals(btThoat)){
            	 new UserDisplay(u).setVisible(true);
            	 setVisible(false);
            
             }
			
			else if(o.equals(btLuu)){
					try {
					
						rsSV.updateString("mssv", tfMS.getText());
						rsSV.updateString("hoten", tfHT.getText());
						rsSV.updateString("gioitinh",tfgt.getText());
						rsSV.updateString("diachi", tfDC.getText());
						rsSV.updateString("email", tfMail.getText());
						rsSV.updateString("malop", tfMSL.getText());
						rsSV.updateRow();	
						
						new Thongtin(u).setVisible(true);
						setVisible(false);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Không thể lưu mẫu tin."+ e1.getMessage(),
								"Thông Báo",JOptionPane.QUESTION_MESSAGE);
					}

				khoaText(true);
				btSua.setText("SỬA");
				btLuu.setEnabled(false);
				btSua.setEnabled(true);
				btThoat.setEnabled(true);
				table.setEnabled(true);
				}	
			
			else if(o.equals(btSua)){
				if(btSua.getText().equalsIgnoreCase("SỬA")){
					khoaText(false);
					tfMS.setEditable(false);
					tfMSL.setEditable(false);
					btSua.setText("HỦY");
					btLuu.setEnabled(true);
					btThoat.setEnabled(false);
					table.setEnabled(false);
				}
				else{
					khoaText(true);
					btSua.setText("SỬA");
					btLuu.setEnabled(false);
					btThoat.setEnabled(true);
					table.setEnabled(true);
				}
			}	
		}catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
