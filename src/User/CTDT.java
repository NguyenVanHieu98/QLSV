package User;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Connect.Connect;

public class CTDT extends JDialog implements ActionListener,MouseListener{


	private static final long serialVersionUID = 1L;
	private JTextField tfmamh,tftenmh,tfkyhoc,tftinchi;
	private JButton btThoat;
	private DefaultTableModel dtm;
	private JTable table;
	private JPanel pCen=new JPanel(new BorderLayout());

	private Connection con;
	private ResultSet rsSV;
    private static String u;

	public CTDT(String m) {

		setTitle("CHƯƠNG TRÌNH HỌC SINH VIÊN");   
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 10, 312, 280);
		setSize(1000,650);		
		this.add(pCen,BorderLayout.CENTER);
		
		tieude();
		taogui();
		taotable();
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
					ResultSet.CONCUR_UPDATABLE).executeQuery("select  c.mamh, m.tenmh, c.kyhoc, m.tinchi from chuongtrinhhoc c, monhoc m, lop l, sinhvien s where m.mamh = c.mamh and c.vien = l.makhoa and l.malop = s.malop and s.mssv = '"+n+"' order by(kyhoc)");
			
			while(rsSV.next()){
				String[]row={
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
		Box b=Box.createVerticalBox();
		Box b2=Box.createHorizontalBox();Box b3=Box.createHorizontalBox();
		Box b4=Box.createHorizontalBox();Box b5=Box.createHorizontalBox();
		Box b6=Box.createHorizontalBox();

		JLabel l2,l3,l4,l5;
		b2.add(Box.createHorizontalStrut(30));b2.add(l2=new JLabel("Mã môn học:",JLabel.RIGHT));b2.add(tfmamh=new JTextField(20));
		b3.add(Box.createHorizontalStrut(30));b3.add(l3=new JLabel("Tên môn học:",JLabel.RIGHT));b3.add(tftenmh=new JTextField(20));
		b5.add(Box.createHorizontalStrut(30));b5.add(l4=new JLabel("Kỳ học:",JLabel.RIGHT));b5.add(tfkyhoc=new JTextField(20));
	    b6.add(Box.createHorizontalStrut(30));b6.add(l5=new JLabel("Tín chỉ:",JLabel.RIGHT));b6.add(tftinchi=new JTextField(20));    
	   
		l2.setForeground(Color.blue);
		l3.setForeground(Color.blue);
		l4.setForeground(Color.blue);
		l5.setForeground(Color.blue);
		l3.setPreferredSize(l2.getPreferredSize());
		l4.setPreferredSize(l2.getPreferredSize());
		l5.setPreferredSize(l2.getPreferredSize());
		Cursor cur=new Cursor(Cursor.HAND_CURSOR);

		b4.add(btThoat= new JButton("THOÁT")); btThoat.addActionListener(this);btThoat.setCursor(cur);
		
		b4.add(Box.createHorizontalStrut(60));
		b4.add(Box.createHorizontalStrut(30));
		b.add(bx);
		b.add(Box.createHorizontalStrut(10));
		b.add(b2);b.add(Box.createVerticalStrut(10));
		b.add(b3);b.add(Box.createVerticalStrut(10));
	    b.add(b5);b.add(Box.createVerticalStrut(10));
	    b.add(b6);b.add(Box.createVerticalStrut(10));

		b.add(b4);b.add(Box.createVerticalStrut(7));
		pCen.add(b,BorderLayout.NORTH);
		
	}
	void taotable(){
		String []header={"Mã môn học","Tên môn học","Kỳ học","Tín chỉ"};
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
		tfmamh.setEditable(!status);
		tftenmh.setEditable(!status);
		tfkyhoc.setEditable(!status);
		tftinchi.setEditable(!status);
		
	}
	
	//hàm hiển thị dữ liệu
	 void Display2Text() {
		try {
			tfmamh.setText(rsSV.getString("mamh"));
			tftenmh.setText(rsSV.getString("tenmh"));
			tfkyhoc.setText(rsSV.getString("kyhoc"));
			tftinchi.setText(rsSV.getString("tinchi"));
			
		
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
