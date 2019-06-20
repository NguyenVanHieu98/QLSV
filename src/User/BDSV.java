package User;
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
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Connect.Connect;


public class BDSV extends JDialog implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	private JTextField tfKH,tfMM,tfML,tfDCC,tfDGK,tfDCK,tfDTB,tfDC;
	private JButton btF,btP,btN,btL;
	private JButton btThoat;
	private JLabel lblRecors, CPA;
	private DefaultTableModel dtm;
	private JTable table;
	private PopupMenu optionsMenu;
	private JPanel pCen=new JPanel(new BorderLayout());
	private Connection con;
	private ResultSet rsSV;
	private static String u;
	private double a,b,c,d;
	
	
	public BDSV(String m) {
		setTitle("BẢNG ĐIỂM SINH VIÊN");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 10, 312, 280);
		setSize(1000,650);
		this.add(pCen,BorderLayout.CENTER);
		
		tieude();
		taogui();
		taotable();
		TinhCPA(m);
		khoaText(true);
		LoadData(m);
		u = m;
		try {
			rsSV.first();
		
		} catch (Exception e) {
		}
	}


	void LoadData(String n){
		try {
			con=Connect.CreateMsAccess("QLSV");
			rsSV=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("select b.kyhoc, b.mamh, b.malop, b.dcc, b.dgk, b.dck, b.dtb, b.dc from bangdiem b where mssv = '"+n+"' order by kyhoc, mamh");
			while(rsSV.next()){
				String[]row={
						rsSV.getString("kyhoc"),
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
		JLabel l1,l2,l3,l4,l5,l6,l7,l8;
		b1.add(Box.createHorizontalStrut(10));b1.add(l1=new JLabel("Kỳ học :",JLabel.RIGHT));b1.add(tfKH=new JTextField(20));
		b2.add(Box.createHorizontalStrut(10));b2.add(l2=new JLabel("Mã Môn Học:",JLabel.RIGHT));b2.add(tfMM=new JTextField(20));
		b3.add(Box.createHorizontalStrut(10));b3.add(l3=new JLabel("Mã Lớp Học:",JLabel.RIGHT));b3.add(tfML=new JTextField(20));
		b5.add(Box.createHorizontalStrut(10));b5.add(l4=new JLabel("Điểm Chuyên Cần:",JLabel.RIGHT));b5.add(tfDCC=new JTextField(20));
		b6.add(Box.createHorizontalStrut(10));b6.add(l5=new JLabel("Điểm Giữa Kỳ:",JLabel.RIGHT));b6.add(tfDGK= new JTextField(20));
		b7.add(Box.createHorizontalStrut(10));b7.add(l6=new JLabel("Điểm Cuối Kỳ :",JLabel.RIGHT));b7.add(tfDCK = new JTextField(20));
		b8.add(Box.createHorizontalStrut(10));b8.add(l7=new JLabel("Điểm Trung Bình:",JLabel.RIGHT));b8.add(tfDTB = new JTextField(20));
		b9.add(Box.createHorizontalStrut(10));b9.add(l8=new JLabel("Điểm chữ:",JLabel.RIGHT));b9.add(tfDC = new JTextField(20));
		l1.setForeground(Color.blue);
		l2.setForeground(Color.blue);
		l3.setForeground(Color.blue);
		l4.setForeground(Color.blue);
		l5.setForeground(Color.blue);
		l6.setForeground(Color.blue);
		l7.setForeground(Color.blue);
		l8.setForeground(Color.blue);
		l1.setPreferredSize(l4.getPreferredSize());
		l2.setPreferredSize(l4.getPreferredSize());
		l3.setPreferredSize(l4.getPreferredSize());
		l5.setPreferredSize(l4.getPreferredSize());
		l6.setPreferredSize(l4.getPreferredSize());
		l7.setPreferredSize(l4.getPreferredSize());
		l8.setPreferredSize(l4.getPreferredSize());

	
		
		Cursor cur=new Cursor(Cursor.HAND_CURSOR);
		
		b4.add(CPA=new JLabel("CPA: "));CPA.setForeground(Color.red);
		b4.add(Box.createHorizontalStrut(60));b4.add(Box.createHorizontalStrut(60));
		b4.add(Box.createHorizontalStrut(20));
		b4.add(btF=new JButton("|<"));btF.addActionListener(this);btF.setCursor(cur);
		b4.add(btP=new JButton("<"));btP.addActionListener(this);btP.setCursor(cur);
		b4.add(lblRecors=new JLabel("1/10"));lblRecors.setForeground(Color.red);
		b4.add(btN=new JButton(">"));btN.addActionListener(this);btN.setCursor(cur);
		b4.add(btL=new JButton(">|"));btL.addActionListener(this);btL.setCursor(cur);
		b4.add(Box.createHorizontalStrut(60));
		b4.add(Box.createHorizontalStrut(60));
	 
		b4.add(btThoat= new JButton("THOÁT")); btThoat.addActionListener(this);btThoat.setCursor(cur);
		b4.add(Box.createHorizontalStrut(60));b4.add(Box.createHorizontalStrut(60));
		b4.add(Box.createHorizontalStrut(60));b4.add(Box.createHorizontalStrut(60));
		
		b.add(Box.createHorizontalStrut(10));
		b.add(b1);b.add(Box.createVerticalStrut(7));
		b.add(b2);b.add(Box.createVerticalStrut(7));
		b.add(b3);b.add(Box.createVerticalStrut(7));
	    b.add(b5);b.add(Box.createVerticalStrut(7));
	    b.add(b6);b.add(Box.createVerticalStrut(7));
	    b.add(b7);b.add(Box.createVerticalStrut(7));
	    b.add(b8);b.add(Box.createHorizontalStrut(7));
	    b.add(b9);b.add(Box.createHorizontalStrut(7));
	 

		b.add(b4);b.add(Box.createVerticalStrut(7));
		pCen.add(b,BorderLayout.NORTH);
		
	}
	void taotable(){
		String []header={"Kỳ Học","Mã Môn Học","Mã Lớp Học","Điểm Chuyên Cần","Điểm Giữa Kỳ","Điểm Cuối Kỳ","Điểm Trung Bình","Điểm Chữ"};
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
	
	void TinhCPA(String n){
		try {
			con=Connect.CreateMsAccess("QLSV");
			rsSV=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("select b.dp , m.tinchi from bangdiem b, monhoc m where b.mssv = '"+n+"' and m.mamh = b.mamh ");
			while(rsSV.next()){
				        a = rsSV.getDouble("dp");
				        b = rsSV.getInt("tinchi");
				        c = b + c;
				        d = a*b +d;
				        			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CPA.setText("CPA: " +(double)Math.round((d/c)*100)/100);
	}
	
	 void Display2Text() {
		try {
			tfKH.setText(rsSV.getString("kyhoc"));
			tfML.setText(rsSV.getString("mamh"));
			tfMM.setText(rsSV.getString("malop"));
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
	
	@Override	
	public void actionPerformed(ActionEvent e) {
 
		try {
             if(e.getSource().equals(btThoat)){
            	 new UserDisplay(u).setVisible(true);
            	 setVisible(false);
            
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


