package com.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dao.DBHelper;

public class FinanceBiz {
	 private DBHelper db = new DBHelper();

     public void finance(List<Map<String,String>> list){
    	 for(Map<String,String> map:list){
    		String checkouttime= map.get("CHECKOUTTIME");
    		String rid= map.get("RID");
    		String money= map.get("MONEY");
    		String price= map.get("PRICE");
    		String total= map.get("TOTAL");
    		try { 
    		        Connection con=db.getCon();
    	 			con.setAutoCommit(  false);
    	 			String sql="insert into total values(?,?,?,?,?)";
    	 			PreparedStatement pstmt=con.prepareStatement(sql);
    	 			pstmt.setString(1, checkouttime);
    	 			pstmt.setString(2, rid);
    	 			pstmt.setString(3, money);
    	 			pstmt.setString(4, price);
    	 			pstmt.setString(5,total);
    	 			pstmt.executeUpdate();
    	 			
    	 		} catch (SQLException e) {
    				e.printStackTrace();
    			}
    	 }
    	
 }
}
