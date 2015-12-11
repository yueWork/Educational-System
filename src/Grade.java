import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public class Grade extends JFrame {

	private JPanel contentPane;
	private JTable table;
	String []column={"序号","课程编号","课程名","成绩","任课老师"};
	String []names={"A","B"};
	String [][]test={{"A1","B1"},{"A2","B2"}};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grade frame = new Grade();
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
	public Grade() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		DefaultTableModel model=new DefaultTableModel(column,10);
		table = new JTable(model);
//		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setSize(400, 220);
		scrollPane.setLocation(25, 44);
		
//		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//		tableModel.addRow(new Object[]{"col1","col2","coln"});
		table.setBounds(128, 195, 204, 99);
		
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		JLabel label = new JLabel("学生成绩");
		label.setBounds(19, 16, 61, 16);
		contentPane.add(label);
	}
}
