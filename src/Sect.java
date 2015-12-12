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

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sect extends JFrame {

	private Teacher t;
	private int sectno;
	private int cno;
	private JPanel contentPane;
	private JTable table;
	private int count=0;
	final ArrayList<String> _sid = new ArrayList<String>();
	final ArrayList<String> _sname = new ArrayList<String>();
	final ArrayList<String> _dname = new ArrayList<String>();
    final ArrayList<String> _grade = new ArrayList<String>();


	/**
	 * Create the frame.
	 */
	public Sect(Teacher t,int sectno,int cno) {
		this.t =t;
		this.sectno = sectno;
		this.cno = cno;
		this.setCount();
		this.getData();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
							s[i][j] = _sid.get(i).toString();
							System.out.println("s;"+s[i][j]);
							break;
						case 1:
							s[i][j] = _sname.get(i);
							break;
						case 2:
							s[i][j]  = _dname.get(i);
							break;
						case 3:
							s[i][j] = _grade.get(i);
						}
					}
				}
				return s[row][col];
			}
			//设置表格的列名
			public final String[] columnName = {"学号", "姓名", "系名","成绩"};
			public String getColumnName(int column) {
				return columnName[column];
			}
			 public Class getColumnClass(int c) {
				 return getValueAt(0, c).getClass();
				 }
		};
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		table = new JTable(tModel);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSectno = new JLabel("sectno:");
		panel.add(lblSectno);
		
		JLabel lblSection = new JLabel(Integer.toString(this.sectno));
		panel.add(lblSection);
		
		JLabel lblCno = new JLabel("cno:");
		panel.add(lblCno);
		
		JLabel lblNewLabel = new JLabel(Integer.toString(this.cno));
		panel.add(lblNewLabel);
		
		JButton button = new JButton("返回");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				t.setVisible(true);
				Sect.this.dispose();
			}
		});
		panel.add(button);
		
	}
	public void setCount(){
		
		ConnectDatabase con = new ConnectDatabase();
		con.connect();
		try {
			String sqlCount = "select count(*) as rows from enroll,student where enroll.sid=student.sid and cno="+Sect.this.cno+" and sectno="+Sect.this.sectno;
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
			String sql = "select enroll.sid,sname,dname,grade from enroll,student where enroll.sid=student.sid and cno="+this.cno+" and sectno="+this.sectno;
			System.out.println(sql);
			con.pst = con.connection.prepareStatement(sql);
			con.ret = (ResultSet) con.pst.executeQuery();
			while(con.ret.next()){
				_sid.add(con.ret.getString(1));
				_sname.add(con.ret.getString(2));
				_dname.add(con.ret.getString(3));
				_grade.add(con.ret.getString(4));
			}
			con.ret.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
