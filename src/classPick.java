import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;

public class classPick extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private static String []column={"序号","课程编号","课程名","成绩","任课老师"};
	private static JLabel name_label;
	private static JLabel sex_label;
	private static JLabel age_label;
	private static JLabel year_label;
	private static JLabel gpa_label;
	private static String sex="";
	private static String name;
	private static String age;
	private static String gpa;
	private static String year;
	String []names={"A","B"};
	private static String [][]test={{"1","1","math","3","Te"},{"2","2","chinese","3","ted"}};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					classPick frame = new classPick();
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
					rs.close();
					ps.close();
					
					sql="";
					DefaultTableModel mode=new DefaultTableModel(test,column);
					table.setModel(mode);
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
	public classPick() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(18, 15, 614, 348);
		contentPane.add(tabbedPane);
		
		JPanel infoPan=new JPanel();
		
		JLabel label_4 = new JLabel("姓名");
		label_4.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		label_4.setForeground(new Color(0, 0, 0));
		label_4.setBounds(19, 68, 61, 16);
		infoPan.add(label_4);
		
		JLabel label_5 = new JLabel("性别");
		label_5.setBounds(19, 110, 61, 16);
		infoPan.add(label_5);
		
		JLabel label_6 = new JLabel("年龄");
		label_6.setBounds(19, 152, 61, 16);
		infoPan.add(label_6);
		
		JLabel label_7 = new JLabel("学年");
		label_7.setBounds(19, 194, 61, 16);
		infoPan.add(label_7);
		
		JLabel label_8 = new JLabel("成绩");
		label_8.setBounds(19, 236, 61, 16);
		infoPan.add(label_8);
		
		name_label = new JLabel(" ");		
		name_label.setBounds(92, 68, 147, 16);
		infoPan.add(name_label);
		
		sex_label = new JLabel(" ");		
		sex_label.setBounds(92, 110, 61, 16);
		infoPan.add(sex_label);
		
		age_label = new JLabel(" ");		
		age_label.setBounds(92, 152, 61, 16);
		infoPan.add(age_label);
		
		year_label = new JLabel(" ");		
		year_label.setBounds(92, 194, 61, 16);
		infoPan.add(year_label);
		
		gpa_label = new JLabel(" ");
		gpa_label.setBounds(92, 236, 77, 16);
		infoPan.add(gpa_label);
		
		tabbedPane.add(infoPan,"Student Infomation");
		infoPan.setLayout(null);
		
		JPanel gradePan=new JPanel();
		DefaultTableModel model=new DefaultTableModel(column,3);
		table = new JTable(model);
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setSize(200, 100);
		scrollPane.setLocation(25, 44);		
		table.setBounds(128, 195, 150, 99);		
		gradePan.add(scrollPane);
		scrollPane.setViewportView(table);
		tabbedPane.add(gradePan,"Grade");
		
		JPanel chooseCoursePan=new JPanel();
		tabbedPane.add(chooseCoursePan, "Choose Course");
		
//		JPanel 
	}

}
