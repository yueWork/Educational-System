import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class ConnectDatabase {
	public String url = "jdbc:mysql://localhost:3306/university";
	public String name = "com.mysql.jdbc.Driver";
	public String username = "root";
	public String password = "adminzyy22339456732";
	public java.sql.PreparedStatement pst = null;
	public Connection connection = null;
	public ResultSet ret = null;
	public java.sql.Statement statement = null;
	void connect(){
		try
		{
			Class.forName(name); // 制定连接类型
			connection = DriverManager.getConnection(url, username, password);// 获取连接
			System.out.println("mysql connection is ok");
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void close()
	{
		try {
			connection.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	String insert(String table_name,String datas[][])//插入数据
	{
		String msg = null;
		String sql = "insert into " + table_name +"(";
		for(int i=0;i<datas.length;i++)
		{
			sql = sql + datas[i][0] + ",";
		}
		sql = sql.substring(0, sql.length()-1);
		sql = sql + ")values(";
		for(int i=0;i<datas.length;i++)
		{
			sql = sql + datas[i][1] + ",";
		}
		sql = sql.substring(0, sql.length()-1);
		sql = sql +");";
		System.out.println(sql);
		try {
			pst = connection.prepareStatement(sql);
			pst.executeUpdate(); 
			pst.close();
			msg = "成功";
			return msg;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("失败");
			msg = "失败";
			return msg;
		}
	}

}
