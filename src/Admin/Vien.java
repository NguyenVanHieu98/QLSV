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
public class Vien extends JDialog implements ActionListener,MouseListener{
	private static final long serialVersionUID = 1L;
	private JTextField  tfMV,tfTV;
	private JButton btF,btP,btN,btL;
	private JButton btThem,btLuu,btXoa,btSua,btThoat;
	private JLabel lblRecors;
	private DefaultTableModel dtm;
	private JTable table;
	private PopupMenu optionsMenu;
	private JPanel pCen=new JPanel(new BorderLayout());
	private Connection con;
	private ResultSet rsSV;	
	public Vien() {
		setTitle("THÔNG TIN VIỆN ĐÀO TẠO");
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

	//hàm để load dữ liệu lên form
	void LoadData(){
		try {
			con=Connect.CreateMsAccess("QLSV");
			rsSV=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("select distinct vien, tenvien from chuongtrinhhoc");
			while(rsSV.next()){
				String[]row={
						rsSV.getString("vien"),
						rsSV.getString("tenvien"),
						
						};
					dtm.addRow(row);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	void tieude(){
		JLabel lTitle=new JLabel("THÔNG TIN VIỆN ĐÀO TẠO",JLabel.CENTER);
		lTitle.setFont(new Font("Arial",Font.BOLD,28));
		lTitle.setForeground(Color.blue);
		this.add(lTitle,BorderLayout.NORTH);
	}
  public void taogui() {
	    Box bt =Box.createHorizontalBox();
	  	Box b=Box.createVerticalBox();Box b1=Box.createHorizontalBox();
		Box b2=Box.createHorizontalBox();
		Box b4=Box.createHorizontalBox();
		JLabel l1,l2;
		b1.add(Box.createHorizontalStrut(35));b1.add(l1=new JLabel("Mã Viện Đào Tạo :",JLabel.RIGHT));b1.add(tfMV = new JTextField(20));
		b2.add(Box.createHorizontalStrut(35));b2.add(l2=new JLabel("Tên Viện :",JLabel.RIGHT));b2.add(tfTV=new JTextField(20));
		l1.setForeground(Color.blue);
		l2.setForeground(Color.blue);
		
		l2.setPreferredSize(l1.getPreferredSize());
		
		Cursor cur=new Cursor(Cursor.HAND_CURSOR);
		b4.add(btF=new JButton("|<"));btF.addActionListener(this);btF.setCursor(cur);
		b4.add(btP=new JButton("<"));btP.addActionListener(this);btP.setCursor(cur);
		b4.add(lblRecors=new JLabel("1/10"));lblRecors.setForeground(Color.red);
		b4.add(btN=new JButton(">"));btN.addActionListener(this);btN.setCursor(cur);
		b4.add(btL=new JButton(">|"));btL.addActionListener(this);btL.setCursor(cur);
		b4.add(Box.createHorizontalStrut(60));
		b4.add(btThem=new JButton("THÊM"));btThem.addActionListener(this);btThem.setCursor(cur);
		b4.add(btLuu=new JButton("LƯU"));btLuu.addActionListener(this);
		btLuu.setEnabled(false);btLuu.setCursor(cur);
		b4.add(btSua=new JButton("SỬA"));btSua.addActionListener(this);btSua.setCursor(cur);
		b4.add(btXoa=new JButton("XÓA"));btXoa.addActionListener(this);btXoa.setCursor(cur);
		b4.add(btThoat= new JButton("THOÁT")); btThoat.addActionListener(this);btThoat.setCursor(cur);
		b.add(bt);
		b.add(Box.createHorizontalStrut(10));
		b.add(b1);b.add(Box.createVerticalStrut(10));
		b.add(b2);b.add(Box.createVerticalStrut(10));
		b.add(b4);b.add(Box.createVerticalStrut(10));
		pCen.add(b,BorderLayout.NORTH);
		
}
	void taotable(){
		String []header={"Mã Viện","Tên Viện"};
		dtm=new DefaultTableModel(header,0);
		table=new JTable(dtm);
		JPanel p=new JPanel(new BorderLayout());
		p.add(new JScrollPane(table));
		p.setBorder(BorderFactory.createTitledBorder("Danh Sách Viện Đào Tạo"));
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
		tfMV.setEditable(!status);
		tfTV.setEditable(!status);
	
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
	
	//hàm hiển thị dữ liệu
	 void Display2Text() {
		try {
			tfMV.setText(rsSV.getString("vien"));
			tfTV.setText(rsSV.getString("tenvien"));
			
		;
			
			lblRecors.setText(rsSV.getRow()+"/"+NumberOfRecords());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
@Override
	//điều kiện đi tới đi lui(dịch chuyển record)
	public void actionPerformed(ActionEvent e) {
 
		Object o=e.getSource();
		try {
             if(e.getSource().equals(btThoat)){
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
					btThem.setText("HỦY");
					KhoaDichuyen(true);
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
						PreparedStatement preparedStmt=(PreparedStatement) con.prepareStatement("insert into chuongtrinhhoc (vien, mamh, kyhoc, tenvien) values ('"+tfMV.getText()+"', 'Mặc định','','"+tfTV.getText()+"')");
						
						preparedStmt.executeUpdate();
						new Vien().setVisible(true);
						setVisible(false);
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Không thể lưu mẫu tin."+e1.getMessage(),
								"Thông Báo",JOptionPane.QUESTION_MESSAGE);
					}
				}
				else {
					try {
						
						PreparedStatement preparedStmt=(PreparedStatement) con.prepareStatement("update chuongtrinhhoc set vien = '"+tfMV.getText()+"', tenvien='"+tfTV.getText()+"' where vien='"+rsSV.getString("vien")+"'");
						
						preparedStmt.executeUpdate();
						
						new Vien().setVisible(true);
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
					tfMV.setEditable(true);
					btSua.setText("HỦY");
					KhoaDichuyen(true);
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
						PreparedStatement preparedStmt=(PreparedStatement) con.prepareStatement("delete from chuongtrinhhoc where vien ='"+tfMV.getText()+"'");
						
						preparedStmt.executeUpdate();
						new Vien().setVisible(true);
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
	  optionsMenu.show(this, e.getButton(),e.getClickCount());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//e.equals(btThoat);
		
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


