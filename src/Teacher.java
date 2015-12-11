import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.nio.channels.SelectableChannel;
import java.sql.SQLException;

import javax.print.attribute.standard.PrinterName;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;

import javax.swing.JLabel;
import javax.swing.JTable;

public class Teacher extends JFrame {

	private int pid = -1;
	private String pname = null;
	private String dname = null;
	private JPanel contentPane;
	private JTable table;


	/**
	 * Create the frame.
	 */
	public Teacher(int pid,String pname,String dname) {
		
		this.pid = pid;
		this.pname = pname;
		this.dname = dname;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("教师姓名：");
		label.setBounds(31, 6, 61, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("所在系别：");
		label_1.setBounds(31, 34, 74, 16);
		contentPane.add(label_1);
		
		JLabel lblNewLabel = new JLabel(pname);
		lblNewLabel.setBounds(87, 6, 190, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(dname);
		lblNewLabel_1.setBounds(117, 34, 190, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel label_2 = new JLabel("授课列表");
		label_2.setBounds(31, 81, 61, 16);
		contentPane.add(label_2);
		
		table = new JTable();
		table.setBounds(73, 220, 234, -137);
		contentPane.add(table);
		
		ConnectDatabase con = new ConnectDatabase();
		DefaultTableModel model=new DefaultTableModel();
		table.setModel(model);
		String sql = "select * from section where pname="+pname;
		try {
			con.pst = con.connection.prepareStatement(sql);
			con.ret = (ResultSet) con.pst.executeQuery();
			while(con.ret.next()){
				String _dname = con.ret.getString(1);
				String cno = con.ret.getString(2);
				String sectno = con.ret.getString(3);
				String []data = {_dname,cno,sectno};
				model.addRow(data);
				System.out.println(_dname +":"+cno+":"+sectno);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
