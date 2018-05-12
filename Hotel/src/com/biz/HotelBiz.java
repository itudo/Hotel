package com.biz;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.swt.graphics.ImageData;

import com.dao.DBHelper;
public class HotelBiz {
	private DBHelper db=new DBHelper();
	/**
	 * 添加入住信息
	 * @param aname
	 * @param gender
	 * @param idnum
	 * @param tel
	 * @param rid
	 * @param money
	 * @param in
	 * @param out
	 * @return
	 * @throws SQLException
	 */
	public int addInformation(String cname,String gender,String idnum,String tel,String rid,String money,Date in,Date out) throws SQLException{
	
		Connection con=db.getCon();
		try {
			con.setAutoCommit(false);
			String sql="insert into custermer values(seq_custermer.nextval,?,?,?,?)";			
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(2,cname);
			pstmt.setString(3, gender);
			pstmt.setString(1, idnum);
			pstmt.setString(4, tel);
			pstmt.executeUpdate();

			String sql1="insert into checkin (idnum,rid,orderid,checkintime,checkouttime,money) values(?,?,seq_checkin_orderid.nextval,to_date(?,'yyyy-MM-dd  HH24:mi:ss'),to_date(?,'yyyy-MM-dd  HH24:mi:ss'),?)";
			PreparedStatement pstmt1=con.prepareStatement(sql1);		
			pstmt1.setString(1, idnum);
			pstmt1.setString(2, rid);
			pstmt1.setString(5, money);
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateIn=df.format(in);
			pstmt1.setString(3, dateIn);
			String dateOut=df.format(out);
			pstmt1.setString(4, dateOut);		
			
			pstmt1.executeUpdate();			
			 con.commit();
			 return 1;
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();//只要有一条语句没有执行成功，则操作回滚
			return 0;
					
		} finally{
			try {
				con.setAutoCommit(true);//恢复现场
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 添加房间信息
	 * @param rid
	 * @param rtid
	 * @param rstate
	 * @param locate
	 * @param rtname
	 * @param rprice
	 * @param picture
	 * @return
	 * @throws SQLException
	 */
	public int addRoom(String rid,String rtid,String rstate,String locate,String picture) throws SQLException {			
				Connection con=db.getCon();
				try {
				con.setAutoCommit(false);				
				String sql="insert into room values(?,?,?,?,empty_blob())";			
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1,rid);
				pstmt.setString(2,rtid);
				pstmt.setString(3, rstate);
				pstmt.setString(4, locate);			
				pstmt.executeUpdate();
				String sql2="select picture from room where rid=? for update";//for  update :锁定这一行数据
				PreparedStatement pstmt2=con.prepareStatement(sql2);
				pstmt2=con.prepareStatement(sql2);
				pstmt2.setString(1, rid);
				ResultSet rs=pstmt2.executeQuery();
				
				if(rs.next()){
					oracle.sql.BLOB b=(oracle.sql.BLOB)rs.getBlob(1);//获取  头象的那一个列  (   empty  )
					BufferedOutputStream bos=new BufferedOutputStream( b.getBinaryOutputStream() );     //通过空列获取输出流  (由程序向数据表输出数据)  
					BufferedInputStream bis=new BufferedInputStream(  new FileInputStream( new File(  picture)));   //从picpath指定的本地磁盘读取数据(输入流)			
					byte[] bs=new byte[1024];
					int length=-1;
					while((length=bis.read(bs, 0, bs.length))!=-1){
						bos.write(bs, 0, length);
						bos.flush();
					}
					
					 bos.flush();		
					 bos.close();
					 bis.close();
				}		 
					 con.commit();
					 return 1;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			} finally{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
	}
	
	public int deleteRoom(String roomId,String idnum ){
		DBHelper db=new DBHelper();
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		String sql1 = "delete from custermer where idnum=?";
		String sql2 = "delete from checkin where rid=?";
		String sql3 = "delete from foodorder where rid = ?";
		String sql4 = "update room set rstate = '未整理' where rid = ?";
		try {
			con=db.getCon();
			con.setAutoCommit(false);//关闭隐式事物提交：一次提交一条，但现在的要求是一次提交两条
			pstmt=con.prepareStatement(sql1);
			pstmt.setString(1,idnum);
			pstmt.executeUpdate();
			pstmt=con.prepareStatement(sql2);
			pstmt.setString(1,roomId);
			pstmt.executeUpdate();
			pstmt=con.prepareStatement(sql3);
			pstmt.setString(1,roomId);
			pstmt.executeUpdate();
			pstmt=con.prepareStatement(sql4);
			pstmt.setString(1,roomId);
			pstmt.executeUpdate();
			con.commit();
			 return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();//只要有一条语句没有执行成功，则操作回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;			
		} finally{
				try {
					con.setAutoCommit(true);//恢复现场
				} catch (SQLException e) {
					e.printStackTrace();
				}
				db.closeAll(rs, pstmt, con);
		}
	}
	/**
	 * 更新房间信息
	 * @param rid
	 * @param rtid
	 * @param rstate
	 * @param locate
	 * @param rtname
	 * @param rprice
	 * @param picture
	 * @return
	 * @throws SQLException 
	 */
	public int updateRoom(String rid, String rtid,String rstate,String locate, String picture) throws SQLException {
		Connection con=db.getCon();
		try {
			con.setAutoCommit(false);
			String sql="update room set rtid=?,rstate=?,locate=? where rid=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, rtid);
			pstmt.setString(2, rstate);
			pstmt.setString(3, locate);
			pstmt.setString(4, rid);
			pstmt.executeUpdate();
			
			if(picture!=null&&!"".equals(picture)){
				sql="select picture from room where rid=? for update";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, rid);
				ResultSet rs=pstmt.executeQuery();

				if(rs.next()){
					oracle.sql.BLOB b=(oracle.sql.BLOB)rs.getBlob(1);//获取  头象的那一个列  (   empty  )
					BufferedOutputStream bos=new BufferedOutputStream( b.getBinaryOutputStream() );     //通过空列获取输出流  (由程序向数据表输出数据)  
					BufferedInputStream bis=new BufferedInputStream(  new FileInputStream( new File(  picture)));   //从picpath指定的本地磁盘读取数据(输入流)			
					byte[] bs=new byte[1024];
					int length=-1;
					while((length=bis.read(bs, 0, bs.length))!=-1){
						bos.write(bs, 0, length);
						bos.flush();
				}
					
					 bos.flush();		
					 bos.close();
					 bis.close();
				}
		}
			 con.commit();
			 return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
					
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据sid来获得头像
	 * @param sid
	 * @return
	 * @throws SQLException
	 */
	public ImageData getHeadByRid(String rid) throws SQLException {
		ImageData imageData=null;
		String sql="select picture from room where rid=? ";
		
		Connection con=db.getCon();	
		try {	
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, rid);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				oracle.sql.BLOB b=(oracle.sql.BLOB)rs.getBlob(1);
				imageData=new ImageData(b.getBinaryStream());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		return imageData;
		
	}	
	
}
