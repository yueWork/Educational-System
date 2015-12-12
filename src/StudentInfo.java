import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.ResultSet;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Administer ad;
	
	private int count=0;
	final ArrayList<String> _sid = new ArrayList<String>();
	final ArrayList<String> _sname = new ArrayList<String>();
	final ArrayList<String> _sex = new ArrayList<String>();
    final ArrayList<String> _age = new ArrayList<String>();
    final ArrayList<String> _year = new ArrayList<String>();
    final ArrayList<String> _gpa = new ArrayList<String>();
	/**
	 * Create the frame.
	 */
	public StudentInfo(Administer ad) {
		this.ad = ad;
		this.setCount();
		this.getData();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		TableModel tModel = new AbstractTableModel() {
			//拿到有多少条记录
			public int rowCount() {

				return count;
				
			}
			//表格的行数
			public int getRowCount() {return rowCount();}
			//表格的列数
			public int getColumnCount() {return 6;}
			
//		    public boolean isCellEditable(int rowIndex, int columnIndex)
//		    {
//		    	if(columnIndex==0)
//		    		return false;
//		    	else
//		    		return true;
//		    }
			
			public Object getValueAt(int row, int col) {
				Object[][] s = new String[rowCount()][6];
				for(int i=0; i<rowCount(); i++) {
					for(int j=0; j<6; j++) {
						switch(j) {
						case 0:
							s[i][j] = _sid.get(i);
							break;
						case 1:
							s[i][j] = _sname.get(i);
							break;
						case 2:
							s[i][j]  = _sex.get(i);
							break;
						case 3:
							s[i][j] = _age.get(i);
						case 4:
							s[i][j] = _year.get(i);
							break;
						case 5:
							s[i][j] = _gpa.get(i);
							break;
						}
					}
				}
				return s[row][col];
			}
			//设置表格的列名
			public final String[] columnName = {"学号", "姓名", "性别","年龄","年级","绩点"};
			public String getColumnName(int column) {
				return columnName[column];
			}
			 public Class getColumnClass(int c) {
				 return getValueAt(0, c).getClass();
				 }
		};
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(tModel);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("学生信息");
		panel.add(label);
		
		JButton button = new JButton("返回");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StudentInfo.this.ad.setVisible(true);
				StudentInfo.this.dispose();
			}
		});
		panel.add(button);
	}
	
	public void setCount(){
		
		ConnectDatabase con = new ConnectDatabase();
		con.connect();
		try {
			String sqlCount = "select count(*) as rows from student";
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
		try {
			String sql = "select * from student";
			System.out.println(sql);
			con.pst = con.connection.prepareStatement(sql);
			con.ret = (ResultSet) con.pst.executeQuery();
			while(con.ret.next()){
				_sid.add(con.ret.getString(1));
				_sname.add(con.ret.getString(2));
				_sex.add(con.ret.getString(3));
				_age.add(con.ret.getString(4));
				_year.add(con.ret.getString(5));
				_gpa.add(con.ret.getString(6));
			}
			con.ret.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
