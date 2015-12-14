import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Student extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private static String []column={"序号","课程编号","课程名","成绩","任课老师"};
	private String []courseInfo={"课程编号","课程名","班级","任课老师","院系","选课"};
//	private String []chosenCourse={"序号","课程编号","课程名","任课老师"};
	private static JLabel name_label;
	private static JLabel sex_label;
	private static JLabel age_label;
	private static JLabel year_label;
	private static JLabel gpa_label;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Student(String sid,String sname,String sex,String year,String gpa,String age) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel()
		{
			protected void paintComponent(Graphics g) {  
            	super.paintComponent(g);
            	ImageIcon img = new ImageIcon("/Users/zyy/Documents/XcodeProject/github/Educational-System/img/25.jpg");
            	g.drawImage(img.getImage(), 0, 0, null); 
  
            }  
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {
				JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
			    int selectedIndex = tabbedPane.getSelectedIndex();
			    switch (selectedIndex) {
			    case 0:
			    	System.out.println(00);
			     break;
			    case 1:
			    	String [][]test;
					int i=0;
					ConnectDatabase connect=new ConnectDatabase();
					connect.connect();
					String sql="select en.cno,c.cname,en.grade,s.pname from university.enroll en,university.course c,university.section s "+
							   "where en.dname=s.dname and en.cno=s.cno and en.sectno=s.sectno and en.dname=c.dname and en.cno=c.cno and sid="+sid;
					PreparedStatement ps;
					try {
						ps = connect.connection.prepareStatement(sql);
						ResultSet rs=(ResultSet)ps.executeQuery();					
						rs.last();
						System.out.println(rs.getRow());
						test=new String[rs.getRow()][5];
						rs.beforeFirst();
						while(rs.next()){
							test[i][0]=String.valueOf(i+1);
							test[i][1]=rs.getString(1);
							test[i][2]=rs.getString(2).replace("\"", "");
							test[i][3]=rs.getString(3);
							test[i][4]=rs.getString(4).replace("\"", "");
							i++;
						}
						rs.close();
						ps.close();
						
						DefaultTableModel mode=new DefaultTableModel(test,column);
						table.setModel(mode);
						table.getColumnModel().getColumn(0).setPreferredWidth(30);
						table.getColumnModel().getColumn(2).setPreferredWidth(180);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						
//						e1.printStackTrace();
					}
			    	System.out.println(01);
			     break;
			    case 2:
			    	System.out.println(10);
			    	break;
			    default:
			    	break;
			    }
			}
		});
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel infoPan=new JPanel();
		infoPan.setBackground(Color.WHITE);
		
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
		table = new JTable(model){
//			JComponent s;
			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
					Component c = super.prepareRenderer(renderer, row, column);
					if (c instanceof JComponent) {
					((JComponent) c).setOpaque(false);
					}
					return c;
					}
		};
		table.setOpaque(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane=new JScrollPane(table);
		Dimension dim=new Dimension(550, 290);
		gradePan.setLayout(new BorderLayout(0, 0));
		scrollPane.setPreferredSize(dim);
		table.setBounds(128, 195, gradePan.getWidth(), gradePan.getHeight());		
		gradePan.add(scrollPane);
		scrollPane.setViewportView(table);
		tabbedPane.add(gradePan,"Grade");
		
		JPanel chooseCoursePan=new JPanel();
		DefaultTableModel modelCourse=new DefaultTableModel(courseInfo,3);
		JTable tableCourse = new JTable(modelCourse);
		tableCourse.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPaneCourse=new JScrollPane(tableCourse);
		Dimension dimCourse=new Dimension(550, 290);
		chooseCoursePan.setLayout(new BorderLayout(0, 0));
		scrollPaneCourse.setPreferredSize(dimCourse);
		tableCourse.setBounds(128, 195, gradePan.getWidth(), gradePan.getHeight());		
		chooseCoursePan.add(scrollPaneCourse);
		scrollPaneCourse.setViewportView(tableCourse);
		tabbedPane.add(chooseCoursePan, "Choose Course");
//		JLabel lblNewLabel_1 = new JLabel("New label");
//		tabbedPane.addTab("New tab", null, lblNewLabel_1, null);
//		
//		JLabel lblNewLabel = new JLabel("New label");
//		tabbedPane.addTab("New tab", null, lblNewLabel, null);
//		
//		JLabel lblNewLabel_2 = new JLabel("New label");
//		tabbedPane.addTab("New tab", null, lblNewLabel_2, null);
		
		name_label.setText(sname);
		sex_label.setText(sex);
		age_label.setText(age);
		year_label.setText(year);
		gpa_label.setText(gpa);
		
//		JLabel lblNewLabel_1 = new JLabel("New label");
//		lblNewLabel_1.setIcon(new ImageIcon("/Users/zyy/Documents/XcodeProject/github/Educational-System/img/back.jpg"));
//		lblNewLabel_1.setBounds(0, 0, infoPan.getWidth(), infoPan.getHeight());
//		infoPan.add(lblNewLabel_1);
		Dimension s=infoPan.getSize();
		double x=s.getWidth();
		System.out.println("infoPan"+x);
		System.out.println("tabPane"+tabbedPane.getWidth());
		
		String [][]test;
		int i=0;
		ConnectDatabase connect=new ConnectDatabase();
		connect.connect();
		String sql="select en.cno,c.cname,en.grade,s.pname from university.enroll en,university.course c,university.section s "+
				   "where en.dname=s.dname and en.cno=s.cno and en.sectno=s.sectno and en.dname=c.dname and en.cno=c.cno and sid="+sid;
		PreparedStatement ps=connect.connection.prepareStatement(sql);
		ResultSet rs=(ResultSet)ps.executeQuery();
		
		rs.last();
		System.out.println(rs.getRow());
		test=new String[rs.getRow()][5];
		rs.beforeFirst();
		while(rs.next()){
			test[i][0]=String.valueOf(i+1);
			test[i][1]=rs.getString(1);
			test[i][2]=rs.getString(2).replace("\"", "");
			test[i][3]=rs.getString(3);
			test[i][4]=rs.getString(4).replace("\"", "");
			i++;
		}
		rs.close();
		ps.close();
//
		sql="select c.cno,c.cname,s.sectno,s.pname,s.dname from university.course c,"+
				"university.section s where c.cno=s.cno and c.dname=s.dname";
		ps=connect.connection.prepareStatement(sql);
		rs=(ResultSet)ps.executeQuery();
		rs.last();
		System.out.println(rs.getRow());
		Object [][]course=new Object[rs.getRow()][6];
		i=0;
		rs.beforeFirst();
		while(rs.next()){
			course[i][0]=rs.getString(1);
			course[i][1]=rs.getString(2).replace("\"", "");
			course[i][2]=rs.getString(3);
			course[i][3]=rs.getString(4).replace("\"", "");
			course[i][4]=rs.getString(5).replace("\"", "");
			course[i][5]="选课";
			i++;
		}
		rs.close();
		ps.close();
		
		DefaultTableModel mode=new DefaultTableModel(test,column);
//		DefaultTableModel modeCourse=new DefaultTableModel(course,courseInfo);
		
		tableCourse.setModel(new DefaultTableModel(){
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Object getValueAt(int row, int column) {
				return course[row][column];
			}
			
			public String getColumnName(int c) {
				   return courseInfo[c];
				  }
			@Override
			public int getRowCount() {
				return course.length;
			}
//
			@Override
			public int getColumnCount() {
				return 6;
			}

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				course[row][column] = aValue;
				fireTableCellUpdated(row, column);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 5) {
					return true;
				} else {
					return false;
				}
			}
		});
		tableCourse.getColumnModel().getColumn(5).setCellEditor(new MyButtonEditor(sid));
		tableCourse.getColumnModel().getColumn(5).setCellRenderer(new MyButtonRenderer());
		tableCourse.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableCourse.getColumnModel().getColumn(1).setPreferredWidth(180);
		tableCourse.getColumnModel().getColumn(2).setPreferredWidth(30);
		tableCourse.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableCourse.getColumnModel().getColumn(5).setMaxWidth(60);
		tableCourse.setRowSelectionAllowed(false);
		table.setModel(mode);
		table.setOpaque(false);
		JPanel panel = new JPanel(){
			protected void paintComponent(Graphics g) {  
            	super.paintComponent(g);
            	ImageIcon img = new ImageIcon("/Users/zyy/Documents/XcodeProject/github/Educational-System/img/25.jpg");
            	g.drawImage(img.getImage(), 0, 0, null); 
  
            }  
		};
		panel.setOpaque(false);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("返回");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login l = new Login();
				l.show();
				Student.this.dispose();
			}
		});
		panel.add(btnNewButton, BorderLayout.EAST);
		
//		
//		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setIcon(new ImageIcon("/Users/zyy/Documents/XcodeProject/github/Educational-System/img/back.jpg"));
//		lblNewLabel.setBounds(0, 0, 650, 378);
//		contentPane.add(lblNewLabel);
	}
	public class MyButtonRenderer implements TableCellRenderer {
		private JPanel panel;
		private JButton button;
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
			button.setText(value == null ? "" : (String)value);
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
		private String sid;
		private String val;
		private int row;
		private JTable table;
		private String grade="0";
		private String dname;
		private String sectno;
		private String cno;
		public MyButtonEditor(String sid) {
			this.sid=sid;
			initButton();

			initPanel();

			panel.add(this.button, BorderLayout.CENTER);
		}

		public void initButton() {
			button = new JButton();
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int res = JOptionPane.showConfirmDialog(null, "Do you want to choose this course?", "choose course",
							JOptionPane.YES_NO_OPTION);

					if (res == JOptionPane.YES_OPTION) {
						ConnectDatabase connect=new ConnectDatabase();
						connect.connect();
						grade="0";
						dname="'\""+(String)table.getValueAt(row, 4)+"\"'";
						cno=(String)table.getValueAt(row, 0);
						sectno=(String)table.getValueAt(row, 2);
						String sql="insert into university.enroll (sid,grade,dname,cno,sectno) values ("+sid+","+grade+","+dname+
								","+cno+","+sectno+")";
						System.out.println(sql);
						Statement ps;
						try {
							ps = connect.connection.createStatement();
							ps.execute(sql);
							ps.close();
//							ResultSet rs=(ResultSet)ps.executeQuery();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "You had chosen this course");
//							int error = JOptionPane.showConfirmDialog(null, "You had chosen this course?", "",
//									JOptionPane.YES_NO_OPTION);
//							e1.printStackTrace();
						}
						
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
//			num = (Integer) value;
			val=(String)value;
			this.row=row;
			this.table=table;
			button.setText(value == null ? "" : (String)value);

			return panel;
		}

		@Override
		public Object getCellEditorValue() {
			return val;
		}

	}
	class MyTableModel extends AbstractTableModel {
		  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String headName[];
		  private Object obj[][];
		  
		  public MyTableModel() {
		   super();
		  }
		  
		  public MyTableModel(String[] headName, Object[][] obj) {
		   this();
		   this.headName = headName;
		   this.obj = obj;
		  }

		  public int getColumnCount() {
		   return headName.length;
		  }

		  public int getRowCount() {
		   return obj.length;
		  }

		  public Object getValueAt(int r, int c) {
		   return obj[r][c];
		  }

		  public String getColumnName(int c) {
		   return headName[c];
		  }

		  public Class<?> getColumnClass(int columnIndex) {
		   return obj[0][columnIndex].getClass();
		  }
		  public boolean isCellEditable(int rowIndex, int columnIndex) {
		   if (columnIndex == 3 || columnIndex == 4) {
		    return false;
		   }
		   return true;
		  }

		 }
}
