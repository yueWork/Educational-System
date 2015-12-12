
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.ResultSet;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.BorderLayout;

public class Teacher extends JFrame {

	private int pid = -1;
	private String pname = null;
	private String dname = null;
	private JPanel contentPane;
	private JTable table;
	private int count=0;
	final ArrayList<String> _dname = new ArrayList<String>();
    final ArrayList<String> _cno = new ArrayList<String>();
    final ArrayList<String> _sectno = new ArrayList<String>();
    final ArrayList<String> into = new ArrayList<String>();
    


	/**
	 * Create the frame.
	 */
	public Teacher(int pid,String pname,String dname) {
		
		this.pid = pid;
		this.pname = pname;
		this.dname = dname;
		this.setCount();
		this.getData();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		TableModel tModel = new AbstractTableModel() {
			//拿到有多少条记录
			public int rowCount() {
				
				return count;
				
			}
			//表格的行数
			public int getRowCount() {return rowCount();}
			//表格的列数
			public int getColumnCount() {return 4;}
			
			public Object getValueAt(int row, int col) {
				Object[][] s = new String[rowCount()][4];
				for(int i=0; i<rowCount(); i++) {
					for(int j=0; j<4; j++) {
						switch(j) {
						case 0:
							s[i][j] = _dname.get(i).toString();
							System.out.println("s;"+s[i][j]);
							break;
						case 1:
							s[i][j] = _cno.get(i);
							break;
						case 2:
							s[i][j]  = _sectno.get(i);
							break;
						case 3:
							s[i][j]  = into.get(0);
							break;
						}
					}
				}
				return s[row][col];
			}
			//设置表格的列名
			public final String[] columnName = {"开课系名", "课程号", "班号","详情"};
			public String getColumnName(int column) {
				return columnName[column];
			}
			 public Class getColumnClass(int c) {
				 return getValueAt(0, c).getClass();
				 }
		};
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("教师姓名：");
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel(pname);
		panel.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("所在系别：");
		panel.add(label_1);
		
		JLabel lblNewLabel_1 = new JLabel(dname);
		panel.add(lblNewLabel_1);
		
		JButton button = new JButton("返回");
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(tModel);
		JScrollPane scrollpane = new JScrollPane(table);
		panel_1.add(scrollpane, BorderLayout.CENTER);
		scrollpane.setViewportView(table);
		
		JLabel label_2 = new JLabel("授课列表");
		panel_1.add(label_2, BorderLayout.NORTH);
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
				int col = ((JTable) e.getSource()).columnAtPoint(e.getPoint()); // 获得列位置
				System.out.println(col);
				if(col==3)
				{
					Sect sect = new Sect
							(Teacher.this,Integer.parseInt(table.getValueAt(row, 2).toString()),Integer.parseInt(table.getValueAt(row, 1).toString()));
					sect.show();
					Teacher.this.setVisible(false);
				}
				
				
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login l = new Login();
				l.show();
				Teacher.this.dispose();
			}
		});
	}
	public void setCount()
	{
		ConnectDatabase con = new ConnectDatabase();
		con.connect();
		try {
			String sqlCount = "select count(*) as rows from section where pname='"+pname+"';";
			con.pst = con.connection.prepareStatement(sqlCount);
			con.ret = (ResultSet) con.pst.executeQuery();
			con.ret.next();
			count = con.ret.getInt("rows");
			con.ret.close();
			con.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		System.out.println(count);
	}
	public void getData()
	{
		ConnectDatabase con = new ConnectDatabase();
		con.connect();
		into.add("进入");
		try {
			String sql = "select * from section where pname='"+pname+"'";
			con.pst = con.connection.prepareStatement(sql);
			con.ret = (ResultSet) con.pst.executeQuery();
			while(con.ret.next()){
				_dname.add(con.ret.getString(1));
				_cno.add(con.ret.getString(2));
				_sectno.add(con.ret.getString(3));
//				System.out.println(_dname +":"+_cno+":"+_sectno);
			}
			con.ret.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("data");
		System.out.println(_dname.get(0)+"___"+_cno+"-----"+_sectno);
	}
}

