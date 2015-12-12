import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Administer extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public Administer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("学生信息");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StudentInfo stu = new StudentInfo(Administer.this); 
				stu.show();
				Administer.this.setVisible(false);
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(93, 45, 117, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("教师信息");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProfInfo pro = new ProfInfo(Administer.this);
				pro.show();
				Administer.this.setVisible(false);
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(93, 101, 117, 29);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("课程信息");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CourseInfo course = new CourseInfo(Administer.this);
				course.show();
				Administer.this.setVisible(false);
			}
		});
		button_2.setBounds(93, 156, 117, 29);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("返回");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login l = new Login();
				l.show();
				Administer.this.dispose();
			}
		});
		button_3.setBounds(93, 224, 117, 29);
		contentPane.add(button_3);
	}
}
