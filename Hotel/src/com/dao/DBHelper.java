package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
	/**
	 * 帮助访问数据库
	 */
       //因为静态块在整个程序中运行时，只执行一次，而且是在程序加载到虚拟机
	static{
		  try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 执行增删改
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int doUpdate(String sql,List<Object> params)throws SQLException{
		Connection con=getCon();
		PreparedStatement stmt = con.prepareStatement(sql);  
        if(params!=null&&params.size()>0){
        	for(int i=0;i<params.size();i++){
        		stmt.setString(i+1, params.get(i).toString());
        	}
        }
        int r=stmt.executeUpdate();
        closeAll(null,stmt,con);
		return r;
		
	}
	
	/**
	 * 统计查询
	 * @throws SQLException 
	 */
	public double selectFunc(String sql,List<Object> params) throws SQLException{
		double result=0;
		Connection con=getCon();
		PreparedStatement stmt = con.prepareStatement(sql);  
        if(params!=null&&params.size()>0){
        	for(int i=0;i<params.size();i++){
        		stmt.setString(i+1, params.get(i).toString());
        	}
        }
        ResultSet rs=stmt.executeQuery();
        if(rs.next()){
        	result=rs.getDouble(1);
        }
        closeAll(rs,stmt,con);
		return result;
		
	}
	/**
	 * 通用的查询 查询结果为List<Map<String,String>> <br/>
	 * Map<String,String>对应的一条数据<br/>
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,String>> select (String sql,List<Object> params) throws SQLException{
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Connection con=getCon();
		PreparedStatement stmt = con.prepareStatement(sql);  
        if(params!=null&&params.size()>0){
        	for(int i=0;i<params.size();i++){
        		stmt.setString(i+1, params.get(i).toString());
        	}
        }
        ResultSet rs=stmt.executeQuery();
        ResultSetMetaData rsmd=rs.getMetaData();//通过元数据未知道有多少列
        int cc=rsmd.getColumnCount();
        List<String> columnNames=new ArrayList<String>();
        for(int i=0;i<cc;i++){
        	String cn=rsmd.getColumnName(i+1);
        	columnNames.add(cn);  //存好列名  得到每一列的名字
        }
        //循环所有结果集中的记录
        while(rs.next()){
        	//每一条数据就是一个Map对象
        	Map<String,String> map=new HashMap<String,String>();
        	//循环每一条记录所有的列名
        	for(int i=0;i<columnNames.size();i++){
        		String cn=columnNames.get(i); //列名
        		String value=rs.getString(cn); //根据列名取值
        		map.put(cn, value);  //存到map中，  键（列名）   值（值）
        	}
        	list.add(map);
        }
        closeAll(rs,stmt,con);
		return list;
		
	}
	/**
	 * 获取与数据库的连接
	 * @return
	 * @throws SQLException
	 */
	public Connection getCon() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a");

		return con;
	}
	/**
	 * 关闭与数据库的连接
	 * @param rs
	 * @param pstmt
	 * @param con
	 */
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	

}
