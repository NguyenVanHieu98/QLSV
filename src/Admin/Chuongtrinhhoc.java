package Admin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
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

public class Chuongtrinhhoc extends JDialog implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	private JTextField tfvien,tfmamh,tftenmh,tfkyhoc,tftinchi;
	private JButton btF,btP,btN,btL;
	private JButton btTim, btThem,btLuu,btXoa,btSua,btThoat;
	private JLabel lblRecors;
	private DefaultTableModel dtm;
	private JTable table;
	private JPanel pCen=new JPanel(new BorderLayout());

	private Connection con;
	private ResultSet rsSV;
    private String search;

	public Chuongtrinhhoc(String b) {

		setTitle("CHƯƠNG TRÌNH HỌC SINH VIÊN");
	   
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 10, 312, 280);
		setSize(1000,650);		
		this.add(pCen,BorderLayout.CENTER);
		
		tieude();
		taogui();
		taotable();
		khoaText(true);
		LoadData2(b);
		 
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
					ResultSet.CONCUR_UPDATABLE).executeQuery("select c.vien, c.mamh, m.tenmh, c.kyhoc, m.tinchi from chuongtrinhhoc c, monhoc m where m.mamh = c.mamh and c.vien='"+tfvien.getText()+"' order by(kyhoc)");
			if(NumberOfRecords()<1) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy viện đào tạo.",
						"Thông Báo",JOptionPane.QUESTION_MESSAGE);
			}else {
			while(rsSV.next()){
				String[]row={
						rsSV.getString("vien"),
						rsSV.getString("mamh"),
						rsSV.getString("tenmh"),
						rsSV.getString("kyhoc"),
						rsSV.getString("tinchi"),
						
				};
				dtm.addRow(row);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	void LoadData2(String a){
		try {
			con=Connect.CreateMsAccess("QLSV");
			rsSV=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("select c.vien, c.mamh, m.tenmh, c.kyhoc, m.tinchi from chuongtrinhhoc c, monhoc m where m.mamh = c.mamh and c.vien='"+a+"' order by(kyhoc)");
			
			while(rsSV.next()){
				String[]row={
						rsSV.getString("vien"),
						rsSV.getString("mamh"),
						rsSV.getString("tenmh"),
						rsSV.getString("kyhoc"),
						rsSV.getString("tinchi"),
						
				};
				dtm.addRow(row);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	void tieude(){
		JLabel lTitle=new JLabel("CHƯƠNG TRÌNH HỌC SINH VIÊN",JLabel.CENTER);
		lTitle.setFont(new Font("Arial",Font.BOLD,28));
		lTitle.setForeground(Color.blue);
		this.add(lTitle,BorderLayout.NORTH);
	}
	void taogui(){
		Box bx = Box.createHorizontalBox();
		Box b=Box.createVerticalBox();Box b1=Box.createHorizontalBox();
		Box b2=Box.createHorizontalBox();Box b3=Box.createHorizontalBox();
		Box b4=Box.createHorizontalBox();Box b5=Box.createHorizontalBox();
		Box b6=Box.createHorizontalBox();


	
		
	
		JLabel l1,l2,l3,l4,l5;
		b1.add(Box.createHorizontalStrut(30));b1.add(l1=new JLabel("Viện đào tạo : ",JLabel.RIGHT));b1.add(tfvien=new JTextField(20));
		b2.add(Box.createHorizontalStrut(30));b2.add(l2=new JLabel("Mã môn học:",JLabel.RIGHT));b2.add(tfmamh=new JTextField(20));
		b3.add(Box.createHorizontalStrut(30));b3.add(l3=new JLabel("Tên môn học:",JLabel.RIGHT));b3.add(tftenmh=new JTextField(20));
		b5.add(Box.createHorizontalStrut(30));b5.add(l4=new JLabel("Kỳ học:",JLabel.RIGHT));b5.add(tfkyhoc=new JTextField(20));
	    b6.add(Box.createHorizontalStrut(30));b6.add(l5=new JLabel("Tín chỉ:",JLabel.RIGHT));b6.add(tftinchi=new JTextField(20));
	    
	    l1.setForeground(Color.blue);
		l2.setForeground(Color.blue);
		l3.setForeground(Color.blue);
		l4.setForeground(Color.blue);
		l5.setForeground(Color.blue);
		
		l2.setPreferredSize(l1.getPreferredSize());
		l3.setPreferredSize(l1.getPreferredSize());
		l4.setPreferredSize(l1.getPreferredSize());
		l5.setPreferredSize(l1.getPreferredSize());
		
		  
	
		
		Cursor cur=new Cursor(Cursor.HAND_CURSOR);

		b4.add(btF=new JButton("|<"));btF.addActionListener(this);btF.setCursor(cur);
		b4.add(btP=new JButton("<"));btP.addActionListener(this);btP.setCursor(cur);
		b4.add(lblRecors=new JLabel("1/10"));lblRecors.setForeground(Color.red);
		b4.add(btN=new JButton(">"));btN.addActionListener(this);btN.setCursor(cur);
		b4.add(btL=new JButton(">|"));btL.addActionListener(this);btL.setCursor(cur);
	
		
		b4.add(Box.createHorizontalStrut(60));
		b4.add(btTim=new JButton("Tìm viện"));btTim.addActionListener(this);btTim.setCursor(cur);
		b4.add(btThem=new JButton("THÊM"));btThem.addActionListener(this);btThem.setCursor(cur);
		b4.add(btLuu=new JButton("LƯU"));btLuu.addActionListener(this);
		btLuu.setEnabled(false);btLuu.setCursor(cur);
		b4.add(btSua=new JButton("SỬA"));btSua.addActionListener(this);btSua.setCursor(cur);
		b4.add(btXoa=new JButton("XÓA"));btXoa.addActionListener(this);btXoa.setCursor(cur);
		b4.add(btThoat= new JButton("THOÁT")); btThoat.addActionListener(this);btThoat.setCursor(cur);
		b4.add(Box.createHorizontalStrut(30));
		b.add(bx);
		b.add(Box.createHorizontalStrut(10));
		b.add(b1);b.add(Box.createVerticalStrut(10));
		b.add(b2);b.add(Box.createVerticalStrut(10));
		b.add(b3);b.add(Box.createVerticalStrut(10));
	    b.add(b5);b.add(Box.createVerticalStrut(10));
	    b.add(b6);b.add(Box.createVerticalStrut(10));


		b.add(b4);b.add(Box.createVerticalStrut(7));
		pCen.add(b,BorderLayout.NORTH);
		
	}
	void taotable(){
		String []header={"Viện đào tạo","Mã môn học","Tên môn học","Kỳ học","Tín chỉ"};
		dtm=new DefaultTableModel(header,0);
		table=new JTable(dtm);
		JPanel p=new JPanel(new BorderLayout());
		p.add(new JScrollPane(table));
		p.setBorder(BorderFactory.createTitledBorder("Chương trình học Sinh Viên"));
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
		tfvien.setEditable(!status);
		tfmamh.setEditable(!status);
		tftenmh.setEditable(!status);
		tfkyhoc.setEditable(!status);
		tftinchi.setEditable(!status);
		
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
			tfvien.setText(rsSV.getString("vien"));
			tfmamh.setText(rsSV.getString("mamh"));
			tftenmh.setText(rsSV.getString("tenmh"));
			tfkyhoc.setText(rsSV.getString("kyhoc"));
			tftinchi.setText(rsSV.getString("tinchi"));
			
		
			lblRecors.setText(rsSV.getRow()+"/"+NumberOfRecords());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	//điều kiện để đi tới đi lui(dịch chuyển các record)
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
			else if(o.equals(btTim)){
				if(btTim.getText().equalsIgnoreCase("Tìm viện")){
					khoaText(false);
					btTim.setText("OK");
					KhoaDichuyen(true);
					tfmamh.setEditable(false);
					tftenmh.setEditable(false);
					tfkyhoc.setEditable(false);
					tftinchi.setEditable(false);
					btThem.setEnabled(false);
					btLuu.setEnabled(false);
					btSua.setEnabled(false);
					btXoa.setEnabled(false);
					btThoat.setEnabled(false);
					table.setEnabled(false);
					Display2Text();
				}
				else{
					khoaText(true);
					btTim.setText("Tìm viện");
					KhoaDichuyen(false);
					btThem.setEnabled(true);
					btLuu.setEnabled(false);
					btSua.setEnabled(true);
					btXoa.setEnabled(true);
					btThoat.setEnabled(true);
					table.setEnabled(true);
					LoadData();
					Display2Text();
				}
			}
			else if(o.equals(btThem)){
				if(btThem.getText().equalsIgnoreCase("THÊM")){
					khoaText(false);
					btThem.setText("HỦY");
					tfvien.setEditable(false);
					tfmamh.setEditable(true);
					tftenmh.setEditable(false);
					tfkyhoc.setEditable(true);
					tftinchi.setEditable(false);
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
						rsSV.moveToInsertRow();
						rsSV.updateString("vien", tfvien.getText());
						rsSV.updateString("mamh", tfmamh.getText());
						rsSV.updateString("kyhoc", tfkyhoc.getText());
						
						rsSV.insertRow();
						rsSV.moveToCurrentRow();
						@SuppressWarnings("unused")
						String[]row={
								tfvien.getText(),
								tfmamh.getText(),
								tfkyhoc.getText(),
								};
						new Chuongtrinhhoc(search).setVisible(true);
						setVisible(false);
						
					 
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Không thể lưu mẫu tin."+e1.getMessage(),
								"Thông Báo",JOptionPane.QUESTION_MESSAGE);
					}
				}
				else {
				
					try {
					
						rsSV.updateString("vien", tfvien.getText());
						rsSV.updateString("mamh", tfmamh.getText());	
						rsSV.updateString("kyhoc", tfkyhoc.getText());
						
						
						rsSV.updateRow();	
						search = tfvien.getText();
						
						new Chuongtrinhhoc(search).setVisible(true);
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
					tfvien.setEditable(false);
					tfmamh.setEditable(true);
					tftenmh.setEditable(false);
					tfkyhoc.setEditable(true);
					tftinchi.setEditable(false);
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
						dtm.removeRow(1);
						rsSV.deleteRow();
						
						
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
