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
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class CourseInfo extends JFrame {

	private Administer ad;
	private int count=0;
	final ArrayList<String> _cno = new ArrayList<String>();
	final ArrayList<String> _cname = new ArrayList<String>();
	final ArrayList<String> _dname = new ArrayList<String>();
	private JPanel contentPane;
	private JTable table;
	private String msg = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;


	/**
	 * Create the frame.
	 */
	public CourseInfo(Administer ad) {
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
			public int getColumnCount() {return 3;}
			
			public Object getValueAt(int row, int col) {
				Object[][] s = new String[rowCount()][3];
				for(int i=0; i<rowCount(); i++) {
					for(int j=0; j<3; j++) {
						switch(j) {
						case 0:
							s[i][j] = _cno.get(i);
							break;
						case 1:
							s[i][j] = _cname.get(i);
							break;
						case 2:
							s[i][j]  = _dname.get(i);
							break;
						}
					}
				}
				return s[row][col];
			}
			//设置表格的列名
			public final String[] columnName = {"课程号", "课程名", "所在系别"};
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
		
		JLabel label = new JLabel("课程信息");
		panel.add(label);
		
		JButton button = new JButton("返回");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CourseInfo.this.ad.setVisible(true);
				CourseInfo.this.dispose();
			}
		});
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JLabel label_1 = new JLabel("课程号：");
		panel_1.add(label_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(3);
		
		JLabel label_2 = new JLabel("课程名：");
		panel_1.add(label_2);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(3);
		
		JLabel label_3 = new JLabel("系名：");
		panel_1.add(label_3);
		
		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(3);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel label_4 = new JLabel("    消息提示");
		panel_2.add(label_4, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("添加课程");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[][] datas = new String[3][2];
				if(!textField.getText().equals("")){
					String cno = textField.getText();
					datas[0][0] = "cno";
					datas[0][1] = cno;
				}
				if(!textField.getText().equals("")){
					String cname = textField_1.getText();
					datas[1][0] = "cname";
					datas[1][1] = "'"+cname+"'";
				}
				if(!textField.getText().equals("")){
					String dname = textField_2.getText();
					datas[2][0] = "dname";
					datas[2][1] = "'"+dname+"'";
				}
				ConnectDatabase con = new ConnectDatabase();
				con.connect();		
				msg = con.insert("course", datas);
				label_4.setText(msg);
				con.close();

			}
		});
		panel_2.add(btnNewButton, BorderLayout.SOUTH);
	}
	
	public void setCount(){
		
		ConnectDatabase con = new ConnectDatabase();
		con.connect();
		try {
			String sqlCount = "select count(*) as rows from course";
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
			String sql = "select * from course";
			System.out.println(sql);
			con.pst = con.connection.prepareStatement(sql);
			con.ret = (ResultSet) con.pst.executeQuery();
			while(con.ret.next()){
				_cno.add(con.ret.getString(1));
				_cname.add(con.ret.getString(2));
				_dname.add(con.ret.getString(3));
			}
			con.ret.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
