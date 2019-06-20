package Admin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Connect.Connect;

public class Bangdiem extends JDialog implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	private JTextField tfKH,tfMS,tfMM,tfML,tfDCC,tfDGK,tfDCK,tfDTB,tfDC;
	private JButton btF,btP,btN,btL;
	private JButton btThem,btLuu,btXoa,btSua,btThoat;
	private JLabel lblRecors;
	private DefaultTableModel dtm;
	private JTable table;
	private PopupMenu optionsMenu;
	private JPanel pCen=new JPanel(new BorderLayout());
	private Connection con;
	private ResultSet rsSV;
	private String rsdc;
	private Double rsdp;
	
	public Bangdiem() {
		setTitle("BẢNG ĐIỂM SINH VIÊN");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 10, 312, 280);
		setSize(1000,650);
		this.add(pCen,BorderLayout.CENTER);
		
		tieude();
		taogui();
		taotable();
		khoaText(true);
		LoadData();
		try {
			rsSV.first();
		
		} catch (Exception e) {
		}
	}


	void LoadData(){
		try {
			con=Connect.CreateMsAccess("QLSV");
			rsSV=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("select b.kyhoc, b.mssv, b.mamh, b.malop, b.dcc, b.dgk, b.dck, b.dtb, b.dc from bangdiem b order by kyhoc, mamh");
			while(rsSV.next()){
				String[]row={
						rsSV.getString("kyhoc"),
						rsSV.getString("mssv"),
						rsSV.getString("mamh"),
						rsSV.getString("malop"),
						rsSV.getString("dcc"),
						rsSV.getString("dgk"),
						rsSV.getString("dck"),
						rsSV.getString("dtb"),
						rsSV.getString("dc")
					
				};
				dtm.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	void tieude(){
		JLabel lTitle=new JLabel("BẢNG ĐIỂM SINH VIÊN",JLabel.CENTER);
		lTitle.setFont(new Font("Arial",Font.BOLD,28));
		lTitle.setForeground(Color.blue);
		this.add(lTitle,BorderLayout.NORTH);
	}
	void taogui(){
	
		Box b=Box.createVerticalBox();Box b1=Box.createHorizontalBox();
		Box b2=Box.createHorizontalBox();Box b3=Box.createHorizontalBox();
		Box b4=Box.createHorizontalBox();Box b5=Box.createHorizontalBox();
		Box b6=Box.createHorizontalBox();Box b7=Box.createHorizontalBox();
		Box b8=Box.createHorizontalBox();Box b9= Box.createHorizontalBox();
		Box b10= Box.createHorizontalBox();
		JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
		b1.add(Box.createHorizontalStrut(30));b1.add(l1=new JLabel("Kỳ học :",JLabel.RIGHT));b1.add(tfKH=new JTextField(20));
		b2.add(Box.createHorizontalStrut(30));b2.add(l2=new JLabel("Mã Số Sinh Viên:",JLabel.RIGHT));b2.add(tfMS=new JTextField(20));
		b3.add(Box.createHorizontalStrut(30));b3.add(l3=new JLabel("Mã Môn Học:",JLabel.RIGHT));b3.add(tfMM=new JTextField(20));
		b5.add(Box.createHorizontalStrut(30));b5.add(l4=new JLabel("Mã Lớp Học:",JLabel.RIGHT));b5.add(tfML=new JTextField(20));
		b6.add(Box.createHorizontalStrut(30));b6.add(l5=new JLabel("Điểm Chuyên Cần:",JLabel.RIGHT));b6.add(tfDCC=new JTextField(20));
		b7.add(Box.createHorizontalStrut(30));b7.add(l6=new JLabel("Điểm Giữa Kỳ:",JLabel.RIGHT));b7.add(tfDGK= new JTextField(20));
		b8.add(Box.createHorizontalStrut(30));b8.add(l7=new JLabel("Điểm Cuối Kỳ :",JLabel.RIGHT));b8.add(tfDCK = new JTextField(20));
		b9.add(Box.createHorizontalStrut(30));b9.add(l8=new JLabel("Điểm Trung Bình:",JLabel.RIGHT));b9.add(tfDTB = new JTextField(20));
		b10.add(Box.createHorizontalStrut(30));b10.add(l9=new JLabel("Điểm chữ:",JLabel.RIGHT));b10.add(tfDC = new JTextField(20));
		l1.setForeground(Color.blue);
		l2.setForeground(Color.blue);
		l3.setForeground(Color.blue);
		l4.setForeground(Color.blue);
		l5.setForeground(Color.blue);
		l6.setForeground(Color.blue);
		l7.setForeground(Color.blue);
		l8.setForeground(Color.blue);
		l9.setForeground(Color.blue);
		l1.setPreferredSize(l5.getPreferredSize());
		l2.setPreferredSize(l5.getPreferredSize());
		l3.setPreferredSize(l5.getPreferredSize());
		l4.setPreferredSize(l5.getPreferredSize());
		l6.setPreferredSize(l5.getPreferredSize());
		l7.setPreferredSize(l5.getPreferredSize());
		l8.setPreferredSize(l5.getPreferredSize());
		l9.setPreferredSize(l5.getPreferredSize());

	
		
		Cursor cur=new Cursor(Cursor.HAND_CURSOR);
		
		
		b4.add(btF=new JButton("|<"));btF.addActionListener(this);btF.setCursor(cur);
		b4.add(btP=new JButton("<"));btP.addActionListener(this);btP.setCursor(cur);
		b4.add(lblRecors=new JLabel("1/10"));lblRecors.setForeground(Color.red);
		b4.add(btN=new JButton(">"));btN.addActionListener(this);btN.setCursor(cur);
		b4.add(btL=new JButton(">|"));btL.addActionListener(this);btL.setCursor(cur);
		b4.add(Box.createHorizontalStrut(60));
		b4.add(Box.createHorizontalStrut(60));
	 
		b4.add(btThem=new JButton("THÊM"));btThem.addActionListener(this);btThem.setCursor(cur);
		b4.add(btLuu=new JButton("LƯU"));btLuu.addActionListener(this);
		btLuu.setEnabled(false);btLuu.setCursor(cur);
		b4.add(btSua=new JButton("SỬA"));btSua.addActionListener(this);btSua.setCursor(cur);
		b4.add(btXoa=new JButton("XÓA"));btXoa.addActionListener(this);btXoa.setCursor(cur);
		b4.add(btThoat= new JButton("THOÁT")); btThoat.addActionListener(this);btThoat.setCursor(cur);
		
		
		b.add(Box.createHorizontalStrut(10));
		b.add(b1);b.add(Box.createVerticalStrut(7));
		b.add(b2);b.add(Box.createVerticalStrut(7));
		b.add(b3);b.add(Box.createVerticalStrut(7));
	    b.add(b5);b.add(Box.createVerticalStrut(7));
	    b.add(b6);b.add(Box.createVerticalStrut(7));
	    b.add(b7);b.add(Box.createVerticalStrut(7));
	    b.add(b8);b.add(Box.createHorizontalStrut(7));
	    b.add(b9);b.add(Box.createHorizontalStrut(7));
	    b.add(b10);b.add(Box.createHorizontalStrut(7));
	 

		b.add(b4);b.add(Box.createVerticalStrut(7));
		pCen.add(b,BorderLayout.NORTH);
		
	}
	void taotable(){
		String []header={"Kỳ Học","Mã Số Sinh Viên","Mã Môn Học","Mã Lớp Học","Điểm Chuyên Cần","Điểm Giữa Kỳ","Điểm Cuối Kỳ","Điểm Trung Bình","Điểm Chữ"};
		dtm=new DefaultTableModel(header,0);
		table=new JTable(dtm);
		JPanel p=new JPanel(new BorderLayout());
		p.add(new JScrollPane(table));
		p.setBorder(BorderFactory.createTitledBorder("Bảng Điểm Theo Môn"));
		pCen.add(p,BorderLayout.CENTER);
		table.getTableHeader().setFont(new Font("Arial",Font.BOLD,13));
		table.getTableHeader().setForeground(Color.red);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){


			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					int selRow=table.getSelectedRow();
					rsSV.absolute(selRow+1);
					Display2Text();
					lblRecors.setText(rsSV.getRow()+"/"+NumberOfRecords());
				} catch (Exception e1) {
				}
			}
		});

		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					int selRow=table.getSelectedRow();
					rsSV.absolute(selRow+1);
					Display2Text();
				} catch (Exception e1) {
				}
			}			
		});
		table.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int selRow=table.getSelectedRow();
					rsSV.absolute(selRow+1);
					Display2Text();
				} catch (Exception e1) {
				}
			}
		});
	}

	void khoaText(boolean status){
		tfKH.setEditable(!status);
		tfMS.setEditable(!status);
		tfMM.setEditable(!status);
		tfML.setEditable(!status);
		tfDCC.setEditable(!status);
		tfDGK.setEditable(!status);
		tfDCK.setEditable(!status);
		tfDTB.setEditable(!status);
		tfDC.setEditable(!status);
		
	
	
	}
	void KhoaDichuyen(boolean status){
		btF.setEnabled(!status);
		btP.setEnabled(!status);
		btN.setEnabled(!status);
		btL.setEnabled(!status);
	}



	int NumberOfRecords()throws Exception{
		int bookmark=rsSV.getRow();
		rsSV.last();
		int somautin=rsSV.getRow();
		rsSV.absolute(bookmark);
		return somautin;
	}
	
	 void Display2Text() {
		try {
			tfKH.setText(rsSV.getString("kyhoc"));
			tfMS.setText(rsSV.getString("mssv"));
			tfMM.setText(rsSV.getString("mamh"));
			tfML.setText(rsSV.getString("malop"));
			tfDCC.setText(rsSV.getString("dcc"));
			tfDGK.setText(rsSV.getString("dgk"));
			tfDCK.setText(rsSV.getString("dck"));
			tfDTB.setText(rsSV.getString("dtb"));
			tfDC.setText(rsSV.getString("dc"));
			lblRecors.setText(rsSV.getRow()+"/"+NumberOfRecords());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void Tinhtoan() {
						
				Double dcc = Double.parseDouble(tfDCC.getText());
				Double dgk = Double.parseDouble(tfDGK.getText());
				Double dck = Double.parseDouble(tfDCK.getText());
				Double gk = (dcc+dgk)/2;
				Double dtb = gk*0.3 + dck*0.7 ;
				dtb = Math.round(dtb*100.0)/100.0;
				if((dcc<0)||(dcc>10)||(dgk<0)||(dgk>10)||(dck<0)||(dck>10)||(dtb<0)||(dtb>10)){
					JOptionPane.showConfirmDialog(null, "Bạn Nhập Điểm Không Hợp Lệ", "Thông Báo", JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE);
				}else {
					if(dtb<4) {
						rsdc = "F";
						rsdp = (double) 0;					
					}
					else if(dtb>=4 && dtb<5) {
						rsdc = "D";
						rsdp = (double) 1;		
					}
					else if(dtb>=5 && dtb<5.5) {
						rsdc = "D+";
						rsdp = (double) 1.5;		
					}
					else if(dtb>=5.5 && dtb<6.5) {
						rsdc = "C";
						rsdp = (double) 2;		
					}
					else if(dtb>=6.5 && dtb<7) {
						rsdc = "C+";
						rsdp = (double) 2.5;		
					}
					else if(dtb>=7 && dtb<8) {
						rsdc = "B";
						rsdp = (double) 3;		
					}
					else if(dtb>=8 && dtb<8.5) {
						rsdc = "B+";
						rsdp = (double) 3.5;		
					}
					else if(dtb>=8.5 && dtb<9) {
						rsdc = "A";
						rsdp = (double) 4;		
					}
					else if(dtb>=9 && dtb<=10) {
						rsdc = "A+";
						rsdp = (double) 4;		
					}
					String dxl = tfDC.getText();
					dxl = rsdc.toString();
					tfDTB.setText(String.valueOf(dtb));
					tfDC.setText(String.valueOf(dxl));
				}
				String dxl = tfDC.getText();
				dxl = rsdc.toString();
				tfDTB.setText(String.valueOf(dtb));
				tfDC.setText(String.valueOf(dxl));					 
	}
	
	@Override
	
	public void actionPerformed(ActionEvent e) {
 
		Object o=e.getSource();
		try {
             if(o.equals(btThoat)){
            	 new Display().setVisible(true);
            	 setVisible(false);
            
             }
			if(o.equals(btF)){
				rsSV.first();
				Display2Text();
			}
			else if(o.equals(btP)){
				if(!rsSV.isFirst()){
					rsSV.previous();
					Display2Text();
				}
			}
			else if(o.equals(btN)){
				if(!rsSV.isLast()){
					rsSV.next();
					Display2Text();
				}
			}
			else if(o.equals(btL)){
				rsSV.last();
				Display2Text();
			}
			else if(o.equals(btThem)){
				if(btThem.getText().equalsIgnoreCase("THÊM")){
					khoaText(false);
					btThem.setText("Hủy");
					KhoaDichuyen(true);
					tfDTB.setEditable(false);
					tfDC.setEditable(false);
					btLuu.setEnabled(true);
					btSua.setEnabled(false);
					btXoa.setEnabled(false);
					btThoat.setEnabled(false);
					table.setEnabled(false);
					
				}
				else{
					khoaText(true);
					btThem.setText("THÊM");
					KhoaDichuyen(false);
					btLuu.setEnabled(false);
					btSua.setEnabled(true);
					btXoa.setEnabled(true);
					btThoat.setEnabled(true);
					table.setEnabled(true);
					
				}
			}
			else if(o.equals(btLuu)){
				if(btThem.isEnabled()){
					try {
						Tinhtoan();
						PreparedStatement preparedStmt=(PreparedStatement) con.prepareStatement("insert into bangdiem (kyhoc,mssv,mamh,malop,dcc,dgk,dck,dtb,dc,dp) values ('"+tfKH.getText()+"','"+tfMS.getText()+"','"+tfMM.getText()+"','"+tfML.getText()+"','"+tfDCC.getText()+"','"+tfDGK.getText()+"','"+tfDCK.getText()+"','"+tfDTB.getText()+"','"+tfDC.getText()+"','"+rsdp+"')");
						
						preparedStmt.executeUpdate();
						
						new Bangdiem().setVisible(true);
						setVisible(false);
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Không thể lưu mẫu tin."+e1.getMessage(),
								"Thông Báo",JOptionPane.QUESTION_MESSAGE);
					}
				}
				else{
					try {
					
						Tinhtoan();
						PreparedStatement preparedStmt=(PreparedStatement) con.prepareStatement("update bangdiem set kyhoc ='"+tfKH.getText()+"', mssv='"+tfMS.getText()+"', mamh='"+tfMM.getText()+"', malop='"+tfML.getText()+"', dcc='"+tfDCC.getText()+"', dgk='"+tfDGK.getText()+"', dck='"+tfDCK.getText()+"', dtb='"+tfDTB.getText()+"', dc='"+tfDC.getText()+"', dp='"+rsdp+"' where mssv = '"+rsSV.getString("mssv")+"' and mamh = '"+rsSV.getString("mamh")+"' and malop = '"+rsSV.getString("malop")+"'");
						
						preparedStmt.executeUpdate();
						
						new Bangdiem().setVisible(true);
						setVisible(false);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Không thể lưu mẫu tin."+ e1.getMessage(),
								"Thông Báo",JOptionPane.QUESTION_MESSAGE);
					}
				}

				khoaText(true);
				btThem.setText("THÊM");
				btSua.setText("SỬA");
				KhoaDichuyen(false);
				btLuu.setEnabled(false);
				btSua.setEnabled(true);
				btThem.setEnabled(true);
				btXoa.setEnabled(true);
				btThoat.setEnabled(true);
				table.setEnabled(true);
				
			}
			else if(o.equals(btSua)){
				if(btSua.getText().equalsIgnoreCase("SỬA")){
					khoaText(false);
					btSua.setText("Hủy");
					KhoaDichuyen(true);
					tfDTB.setEditable(false);
					tfDC.setEditable(false);
					btLuu.setEnabled(true);
					btThem.setEnabled(false);
					btXoa.setEnabled(false);
					btThoat.setEnabled(false);
					table.setEnabled(false);
					Display2Text();
				}
				else{
					khoaText(true);
					btSua.setText("SỬA");
					KhoaDichuyen(false);
					btLuu.setEnabled(false);
					btThem.setEnabled(true);
					btXoa.setEnabled(true);
					btThoat.setEnabled(true);
					table.setEnabled(true);
					Display2Text();
				}
			}
			else if(o.equals(btXoa)){
				if(JOptionPane.showConfirmDialog(null,"Bạn Chắc Muốn Xóa Mẫu Tin Này Không?","Xóa Nha",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)
						==JOptionPane.YES_OPTION){
					try {
						PreparedStatement preparedStmt=(PreparedStatement) con.prepareStatement("delete from bangdiem where mssv = '"+rsSV.getString("mssv")+"' and mamh = '"+rsSV.getString("mamh")+"' and malop = '"+rsSV.getString("malop")+"'");
						
						preparedStmt.executeUpdate();
						
						new Bangdiem().setVisible(true);
						setVisible(false);
							
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Không thể Xóa mẫu tin.\n"+e1.getMessage(),
								"Thông Báo",JOptionPane.QUESTION_MESSAGE);
					}
				}
			}
				
		}catch (Exception e2) {
			e2.printStackTrace();
		}

			
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		optionsMenu.show(this, e.getX(), e.getY());
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


