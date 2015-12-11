import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends JFrame {

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
		
		JButton button = new JButton("登陆");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ConnectDatabase().connect();
				Teacher teacher = new Teacher();
				teacher.show();
			}
		});
		button.setBounds(124, 211, 117, 29);
		contentPane.add(button);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("学生");
		comboBox.addItem("老师");
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
	}
}
