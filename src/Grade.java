import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTabbedPane;

public class Grade extends JFrame {

	private JFrame frame;

	private Object[][] data = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grade window = new Grade();
					
					ImageIcon mainBack=new ImageIcon("/Users/zyy/Documents/XcodeProject/github/Educational-System/img/background.jpg");
					JLabel imgMain=new JLabel(mainBack);
			
					window.getLayeredPane().add(imgMain,new Integer(Integer.MIN_VALUE));
					imgMain.setBounds(0, 0,window.getWidth(), window.getHeight());
					Container cn=window.getContentPane();
					cn.setLayout(null);
					((JPanel)cn).setOpaque(false);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Grade() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		tabbedPane.addTab("New tab", null, lblNewLabel, null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		tabbedPane.addTab("New tab", null, lblNewLabel_1, null);
		
//		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setIcon(new ImageIcon("/Users/zyy/Documents/XcodeProject/github/Educational-System/img/background.jpg"));
//		System.out.println(frame.getWidth());
//		lblNewLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
//		frame.getContentPane().add(lblNewLabel);
	}

	public class MyButtonRenderer implements TableCellRenderer {
		private JPanel panel;

		private JButton button;

		private int num;

		public MyButtonRenderer() {
			initButton();

			initPanel();

			panel.add(button, BorderLayout.CENTER);
		}

		private void initButton() {
			button = new JButton();

		}

		private void initPanel() {
			panel = new JPanel();

			panel.setLayout(new BorderLayout());
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			num = (Integer) value;

			button.setText(value == null ? "" : String.valueOf(value));

			return panel;
		}

	}

	public class MyButtonEditor extends AbstractCellEditor implements TableCellEditor {

		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = -6546334664166791132L;

		private JPanel panel;

		private JButton button;

		private int num;

		public MyButtonEditor() {

			initButton();

			initPanel();

			panel.add(this.button, BorderLayout.CENTER);
		}

		private void initButton() {
			button = new JButton();

			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int res = JOptionPane.showConfirmDialog(null, "Do you want to add 1 to it?", "choose one",
							JOptionPane.YES_NO_OPTION);

					if (res == JOptionPane.YES_OPTION) {
						num++;
					}
					// stopped!!!!
					fireEditingStopped();

				}
			});

		}

		private void initPanel() {
			panel = new JPanel();

			panel.setLayout(new BorderLayout());
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			num = (Integer) value;

			button.setText(value == null ? "" : String.valueOf(value));

			return panel;
		}

		@Override
		public Object getCellEditorValue() {
			return num;
		}

	}
}