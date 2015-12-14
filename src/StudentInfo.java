import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.ResultSet;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

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
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private String msg = null;
	/**
	 * Create the frame.
	 */
	public StudentInfo(Administer ad) {
		this.ad = ad;
		this.setCount();
		this.getData();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel()
		{  
			  
            protected void paintComponent(Graphics g) {  
            	super.paintComponent(g);
            	ImageIcon img = new ImageIcon("/Users/yue/Desktop/25.jpg");
            	g.drawImage(img.getImage(), 0, 0, null); 
  
            }  
  
        }; 
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
			
		    public boolean isCellEditable(int rowIndex, int columnIndex)
		    {
		    	if(columnIndex==0)
		    		return false;
		    	else
		    		return true;
		    }
			
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
//			public void setUpdate()
//			{
//				this.fireTableStructureChanged();
//				this.fireTableDataChanged();
//			}
		};
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(tModel);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		
		JPanel panel = new JPanel()
		{  
			  
            protected void paintComponent(Graphics g) {  
            	super.paintComponent(g);
            	ImageIcon img = new ImageIcon("/Users/yue/Desktop/25.jpg");
            	g.drawImage(img.getImage(), 0, 0, null); 
  
            }  
  
        }; 
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
		
		JPanel panel_1 = new JPanel()
		{  
			  
            protected void paintComponent(Graphics g) {  
            	super.paintComponent(g);
            	ImageIcon img = new ImageIcon("/Users/yue/Desktop/25.jpg");
            	g.drawImage(img.getImage(), 0, 0, null); 
  
            }  
  
        }; 
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel()
		{  
			  
            protected void paintComponent(Graphics g) {  
            	super.paintComponent(g);
            	ImageIcon img = new ImageIcon("/Users/yue/Desktop/25.jpg");
            	g.drawImage(img.getImage(), 0, 0, null); 
  
            }  
  
        }; 
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JLabel label_1 = new JLabel("学号：");
		panel_2.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(3);
		panel_2.add(textField);
		
		JLabel label_3 = new JLabel("姓名：");
		panel_2.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(3);
		panel_2.add(textField_1);
		
		JLabel label_2 = new JLabel("性别：");
		panel_2.add(label_2);
		
		textField_4 = new JTextField();
		panel_2.add(textField_4);
		textField_4.setColumns(3);
		
		JPanel panel_3 = new JPanel()
		{  
			  
            protected void paintComponent(Graphics g) {  
            	super.paintComponent(g);
            	ImageIcon img = new ImageIcon("/Users/yue/Desktop/25.jpg");
            	g.drawImage(img.getImage(), 0, 0, null); 
  
            }  
  
        }; 
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("年龄：");
		panel_3.add(lblNewLabel);
		
		textField_5 = new JTextField();
		panel_3.add(textField_5);
		textField_5.setColumns(3);
		
		JLabel lblNewLabel_1 = new JLabel("年级：");
		panel_3.add(lblNewLabel_1);
		
		textField_6 = new JTextField();
		panel_3.add(textField_6);
		textField_6.setColumns(3);
		
		JLabel lblNewLabel_2 = new JLabel("绩点：");
		panel_3.add(lblNewLabel_2);
		
		textField_7 = new JTextField();
		panel_3.add(textField_7);
		textField_7.setColumns(3);
		
		JLabel label_4 = new JLabel("消息提示");
		panel_2.add(label_4);
		
		JButton button_2 = new JButton("添加学生");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[][] datas = new String[6][2];
				if(!textField.getText().equals("")){
					String sid = textField.getText();
					datas[0][0] = "sid";
					datas[0][1] = sid;
				}
				if(!textField.getText().equals("")){
					String sname = textField_1.getText();
					datas[1][0] = "sname";
					datas[1][1] = "'"+sname+"'";
				}
				if(!textField.getText().equals("")){
					String sex = textField_4.getText();
					datas[2][0] = "sex";
					datas[2][1] = "'"+sex+"'";
				}
				if(!textField.getText().equals("")){
					String age = textField_5.getText();
					datas[3][0] = "age";
					datas[3][1] = age;
				}
				if(!textField.getText().equals("")){
					String year = textField_6.getText();
					datas[4][0] = "year";
					datas[4][1] = "'"+year+"'";
				}
				if(!textField.getText().equals("")){
					String gpa = textField_7.getText();
					datas[5][0] = "gpa";
					datas[5][1] = "'"+gpa+"'";
				}
				ConnectDatabase con = new ConnectDatabase();
				con.connect();		
				msg = con.insert("student", datas);
				System.out.println("ioii"+msg);
				label_4.setText(msg);
				con.close();

			}
		});
		panel_3.add(button_2);
	
		
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
