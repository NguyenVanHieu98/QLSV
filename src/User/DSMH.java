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
import javax.swing.table.DefaultTableModel;

import Connect.Connect;

public class DSMH extends JDialog implements ActionListener,MouseListener{


	private static final long serialVersionUID = 1L;
	private JTextField tfMS,tfHT,tfgt;
	private JButton btThoat;
	private JLabel lblRecors;
	private DefaultTableModel dtm;
	private JTable table;
	private PopupMenu optionsMenu;
	private JPanel pCen=new JPanel(new BorderLayout());

	private Connection con;
	private ResultSet rsSV;
	private static String u;

	public DSMH(String m) {

		setTitle("THÔNG TIN MÔN HỌC");
	   
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 10, 312, 280);
		setSize(1000,650);   
		
		this.add(pCen,BorderLayout.CENTER);
		
		tieude();
		taogui();
		taotable();
		khoaText(true);
		LoadData();
		u = m;
		 
		try {
			rsSV.first();
		
		} catch (Exception e) {
		}
	}

	//ham de load du lieu len form
	void LoadData(){
		try {
			con=Connect.CreateMsAccess("QLSV");
			rsSV=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery("select * from monhoc where mamh != 'Mặc định' order by(mamh)");
			while(rsSV.next()){
				String[]row={
						rsSV.getString("mamh"),
						rsSV.getString("tenmh"),
						rsSV.getString("tinchi"),						
					
				};
				dtm.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	void tieude(){
		JLabel lTitle=new JLabel("THÔNG TIN MÔN HỌC",JLabel.CENTER);
		lTitle.setFont(new Font("Arial",Font.BOLD,28));
		lTitle.setForeground(Color.blue);
		this.add(lTitle,BorderLayout.NORTH);
	}
	void taogui(){
		Box bt =Box.createHorizontalBox();
		Box b=Box.createVerticalBox();Box b1=Box.createHorizontalBox();
		Box b2=Box.createHorizontalBox();Box b3=Box.createHorizontalBox();
		Box b4=Box.createHorizontalBox();
		JLabel l1,l2,l3;
		b1.add(Box.createHorizontalStrut(30));b1.add(l1=new JLabel("Mã Môn Học :",JLabel.RIGHT));b1.add(tfMS=new JTextField(20));
		b2.add(Box.createHorizontalStrut(30));b2.add(l2=new JLabel("Tên Môn Học :",JLabel.RIGHT));b2.add(tfHT=new JTextField(20));
		b3.add(Box.createHorizontalStrut(30));b3.add(l3=new JLabel("Số Tín Chỉ:",JLabel.RIGHT));b3.add(tfgt=new JTextField(20));
		l1.setForeground(Color.blue);
		l2.setForeground(Color.blue);
		l3.setForeground(Color.blue);
		l1.setPreferredSize(l2.getPreferredSize());
		l3.setPreferredSize(l2.getPreferredSize());
		
		Cursor cur=new Cursor(Cursor.HAND_CURSOR);
		b4.add(btThoat= new JButton("THOÁT")); btThoat.addActionListener(this);btThoat.setCursor(cur);
		
		b.add(bt);
		b.add(Box.createHorizontalStrut(10));
		b.add(b1);b.add(Box.createVerticalStrut(10));
		b.add(b2);b.add(Box.createVerticalStrut(10));
		b.add(b3);b.add(Box.createVerticalStrut(10));

		b.add(b4);b.add(Box.createVerticalStrut(7));
		pCen.add(b,BorderLayout.NORTH);
		
	}
	void taotable(){
		String []header={"Mã Môn Học","Tên Môn Học","Số Tín Chỉ"};
		dtm=new DefaultTableModel(header,0);
		table=new JTable(dtm);
		JPanel p=new JPanel(new BorderLayout());
		p.add(new JScrollPane(table));
		p.setBorder(BorderFactory.createTitledBorder("Danh Sách Môn Học"));
		pCen.add(p,BorderLayout.CENTER);
		table.getTableHeader().setFont(new Font("Arial",Font.BOLD,13));
		table.getTableHeader().setForeground(Color.red);

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
		tfMS.setEditable(!status);
		tfHT.setEditable(!status);
		tfgt.setEditable(!status);
	}


	int NumberOfRecords()throws Exception{
		int bookmark=rsSV.getRow();
		rsSV.last();
		int somautin=rsSV.getRow();
		rsSV.absolute(bookmark);
		return somautin;
	}
	
	//ham hien thi du lieu
	 void Display2Text() {
		try {
			tfMS.setText(rsSV.getString("mamh"));
			tfHT.setText(rsSV.getString("tenmh"));
			tfgt.setText(rsSV.getString("tinchi"));		
			lblRecors.setText(rsSV.getRow()+"/"+NumberOfRecords());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
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






