import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class Student extends JFrame {

	private JPanel contentPane;
	private static String sex="";
	private static String name;
	private static String age;
	private static String gpa;
	private static String year;
	private static JLabel name_label;
	private static JLabel sex_label;
	private static JLabel age_label;
	private static JLabel year_label;
	private static JLabel gpa_label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
					ConnectDatabase connect=new ConnectDatabase();
					connect.connect();
					String sql="select * from university.student where sid=1";
					PreparedStatement ps=connect.connection.prepareStatement(sql);
					ResultSet rs=(ResultSet)ps.executeQuery();
					while(rs.next()){
						name=rs.getString(2);
						sex=rs.getString(3);
						age=rs.getString(4);
						year=rs.getString(5);
						gpa=rs.getString(6);
						
						name=name.replace("\"", "");
						sex=sex.replace("\"", "");
						name_label.setText(name);
						sex_label.setText(sex);
						age_label.setText(age);
						year_label.setText(year);
						gpa_label.setText(gpa);
						System.out.println("age:"+age);
					}
					frame.setVisible(true);
					ImageIcon image=new ImageIcon("/Users/zyy/Documents/XcodeProject/github/Educational-System/img/background.jpg");
					JLabel imagelabel=new JLabel(image);
					frame.getLayeredPane().add(imagelabel, new Integer(Integer.MIN_VALUE));
					imagelabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Student() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		JLabel label = new JLabel("成绩查询");
		label.setBounds(362, 31, 61, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("课程查询");
		label_1.setBounds(362, 78, 61, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("学生选课");
		label_2.setBounds(362, 131, 61, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("学生基本信息");
		label_3.setFont(new Font("Helvetica", Font.BOLD, 18));
		label_3.setBounds(19, 26, 110, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("姓名");
		label_4.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		label_4.setForeground(new Color(0, 0, 0));
		label_4.setBounds(19, 68, 61, 16);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("性别");
		label_5.setBounds(19, 110, 61, 16);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("年龄");
		label_6.setBounds(19, 152, 61, 16);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("学年");
		label_7.setBounds(19, 194, 61, 16);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("成绩");
		label_8.setBounds(19, 236, 61, 16);
		contentPane.add(label_8);
		
		name_label = new JLabel(" ");		
		name_label.setBounds(92, 68, 147, 16);
		contentPane.add(name_label);
		
		sex_label = new JLabel(" ");		
		sex_label.setBounds(92, 110, 61, 16);
		contentPane.add(sex_label);
		
		age_label = new JLabel(" ");		
		age_label.setBounds(92, 152, 61, 16);
		contentPane.add(age_label);
		
		year_label = new JLabel(" ");		
		year_label.setBounds(92, 194, 61, 16);
		contentPane.add(year_label);
		
		gpa_label = new JLabel(" ");
		gpa_label.setBounds(92, 236, 77, 16);
		contentPane.add(gpa_label);
	}
}