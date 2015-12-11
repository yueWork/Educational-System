import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.ResultSet;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.annotation.Retention;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Login extends JFrame {
	
	private String type = null;//学生 or 教师 or 管理员
	private int uid = -1;//学生:sid or 教师:pid or 管理员：6666
	private String password = null;
	public String msg = null;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					if(e.getItem().equals("学生"))
						type = "学生";
					else if (e.getItem().equals("教师")) {
						type = "教师";
					}else {
						type = "管理员";
					}
				}
	
			}
		});
		comboBox.addItem("学生");
		comboBox.addItem("教师");
		comboBox.addItem("管理员");
		comboBox.setBounds(124, 51, 101, 27);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(124, 94, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(124, 143, 134, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("用户名");
		label.setBounds(36, 100, 61, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setBounds(41, 149, 39, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("初始密码：0000");
		label_2.setBounds(128, 183, 130, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("提示消息");
		label_3.setBounds(264, 216, 142, 16);
		contentPane.add(label_3);
		
		JButton button = new JButton("登陆");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				uid = Integer.parseInt(textField.getText());
				password = textField_1.getText();
				String sql = null;
				if (type.equals("学生")) {
					sql = "select * from student where sid="+uid+" and password='"+password+"'";
					ConnectDatabase con = new ConnectDatabase();
					con.connect();
					try {
						con.pst = con.connection.prepareStatement(sql);
						con.ret = (ResultSet) con.pst.executeQuery();
						if(con.ret.next())
						{
							String sid = con.ret.getString(1);
							String sname = con.ret.getString(2);
							String sex = con.ret.getString(3);
							String age = con.ret.getString(4);
							String year = con.ret.getString(5);
							String gpa = con.ret.getString(6);
//							System.out.println(sid +":"+sname+":"+sex+":"+age+":"+year+":"+gpa);
							con.ret.close();
							
							Student student = new Student();
							student.show();
							
						}else {
							msg = "用户与密码不匹配";
							label_3.setText(msg);
						}
						con.close();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else if (type.equals("教师")) {
					sql = "select * from prof where pid="+uid+" and password='"+password+"'";
					ConnectDatabase con = new ConnectDatabase();
					con.connect();
					try {
						con.pst = con.connection.prepareStatement(sql);
						con.ret = (ResultSet) con.pst.executeQuery();
						if(con.ret.next())
						{
							String pid = con.ret.getString(1);
							String pname = con.ret.getString(2);
							String dname = con.ret.getString(3);
							System.out.println(pid +":"+pname+":"+dname);
							con.ret.close();
							
							Teacher teacher = new Teacher(Integer.parseInt(pid),pname,dname);
							teacher.show();
							
						}else {
							msg = "用户与密码不匹配";
							label_3.setText(msg);
							con.close();
							
						}	
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else if(type.equals("管理员")){
					if(uid == 6666 && password.equals("root"))
					{
						Administer admin = new Administer();
						admin.show();
					}else {
						msg = "用户与密码不匹配";
						label_3.setText(msg);
					}
					
				}else {
					msg = "出错";
					label_3.setText(msg);
				}
				
			}
		});
		button.setBounds(124, 211, 117, 29);
		contentPane.add(button);
	}
}
