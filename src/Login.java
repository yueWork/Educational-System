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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.SwingConstants;

public class Login extends JFrame {
	
	public static final String url = "/Users/yue/Desktop/26.png";
	
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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel()
		{  
			  
            protected void paintComponent(Graphics g) {  
            	super.paintComponent(g);
            	ImageIcon img = new ImageIcon(url);
            	g.drawImage(img.getImage(), 0, 0, null); 
  
            }  
  
        };  
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel()
		{  
			  
            protected void paintComponent(Graphics g) {  
            	super.paintComponent(g);
            	ImageIcon img = new ImageIcon(url);
            	g.drawImage(img.getImage(), 0, 0, null); 
  
            }  
  
        }; 
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(173, 89, 134, 28);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(173, 138, 134, 28);
		panel.add(textField_1);
		
		JLabel label = new JLabel("用户名:");
		label.setBounds(112, 95, 61, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setBounds(116, 144, 39, 16);
		panel.add(label_1);
		JLabel label_2 = new JLabel("初始密码：0000");
		label_2.setBounds(177, 178, 130, 16);
		panel.add(label_2);
		
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
		comboBox.setBounds(173, 50, 101, 27);
		panel.add(comboBox);
		
		JLabel label_4 = new JLabel("教务系统");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_4.setBounds(64, 6, 317, 39);
		panel.add(label_4);
		
		JPanel panel_2 = new JPanel(); 
        panel_2.setOpaque(false);
        JLabel label_3 = new JLabel("提示消息");
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
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
							sname=sname.replace("\"", "");
							sex=sex.replace("\"", "");
							Student student = new Student(sid, sname, sex, year, gpa, age);
							student.show();
							Login.this.dispose();
							
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
							con.close();
							Teacher teacher = new Teacher(Integer.parseInt(pid),pname,dname);
							teacher.show();
							Login.this.dispose();
							
						}else {
							msg = "用户与密码不匹配";
							label_3.setText(msg);
							
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
						Login.this.dispose();
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
		panel_2.add(button);
		
		
		panel_2.add(label_3);
		
	}
}
