package com.biz;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.swt.graphics.ImageData;










import com.bean.Foodinfo;




import com.dao.DBHelper;

public class TakeOutBiz {

	private DBHelper db = new DBHelper();
	public int addFood(String fname, String fprice, String ftid,String fdes,String picpath) throws SQLException {
		Connection con=db.getCon();
		try {
			con.setAutoCommit(  false);
			String sql="insert into food1 values(seq_food.nextval,?,?,empty_blob(),?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, fname);
			pstmt.setString(2, fprice);
			pstmt.setString(3, fdes);
			pstmt.setString(4, ftid);
			pstmt.setString(5, picpath);
			pstmt.executeUpdate();
			
			sql=" select pic from food1 where fname=? for update   ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, fname);
			ResultSet rs=pstmt.executeQuery();
			
			if(   rs.next() ){
				oracle.sql.BLOB b=(oracle.sql.BLOB) rs.getBlob(1);   //获取  头象的那一个列  (   empty  )
				BufferedOutputStream bos=new BufferedOutputStream( b.getBinaryOutputStream() );     //通过空列获取输出流  (由程序向数据表输出数据)  
				BufferedInputStream bis=new BufferedInputStream(  new FileInputStream( new File(  picpath)));   //从picpath指定的本地磁盘读取数据(输入流)
				byte[] bs=new byte[1024];
				int length=-1;
				
				
				while(   (length=bis.read(bs, 0, bs.length))  !=-1){
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
	public int upDateFood(String ftid,String fname, String fprice,String fid, String fdes, String picpath) throws SQLException {
		Connection con=db.getCon();
		try {
			con.setAutoCommit(  false);
			String sql="update food1 set fname=?,fprice=?,ftid=?,fdes=? where fid=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, fname);
			pstmt.setString(2, fprice);
			pstmt.setString(3, ftid);
			pstmt.setString(4, fdes);
			pstmt.setString(5, fid);
			pstmt.executeUpdate();
			
			String sql1=" select pic from food1 where fid=? for update   ";
			if(picpath!=null && !"".equals(picpath)) {
				pstmt=con.prepareStatement(sql1);
				pstmt.setString(1, fid);
				ResultSet rs=pstmt.executeQuery();
				
				if(   rs.next() ){
					oracle.sql.BLOB b=(oracle.sql.BLOB) rs.getBlob(1);   //获取  头象的那一个列  (   empty  )
					BufferedOutputStream bos=new BufferedOutputStream( b.getBinaryOutputStream() );     //通过空列获取输出流  (由程序向数据表输出数据)  
					BufferedInputStream bis=new BufferedInputStream(  new FileInputStream( new File(  picpath)));   //从picpath指定的本地磁盘读取数据(输入流)
					byte[] bs=new byte[1024];
					int length=-1;
					
					
					while(   (length=bis.read(bs, 0, bs.length))  !=-1){
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
	 * 带条件与分页查询
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	
	public Map search(Map map)throws SQLException{
		String pages=map.get("pages").toString();
		String pagesize=map.get("pagesize").toString();
		Foodinfo food=(Foodinfo)map.get("food");
		long num=searchFunc(food);
		List<Map<String,String>> list=searchList(food,Integer.parseInt(pages),Integer.parseInt(pagesize),
				map.get("orderby").toString(),map.get("orderway").toString());
		map.put("num", num);
		map.put("list", list);
		return map;
	}

 
/**
 * 根据条件查询总共的记录数
 * @param studentinfo
 * @return
 * @throws SQLException
 */
	private long searchFunc(Foodinfo foodinfo) throws SQLException {
		String sql="select count(*) as num from food1 where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(foodinfo!=null){
			if(foodinfo.getFname()!=null &&!"".equals(foodinfo.getFname())){
				sql+="and fname like?";
				params.add("%"+foodinfo.getFname()+"%");
			}
			if(foodinfo.getFtid()!=null &&!"".equals(foodinfo.getFtid())){
				sql+="and ftid=?";
				params.add(foodinfo.getFtid());
			}
			
		}
		DBHelper db=new DBHelper();
		double num=db.selectFunc(sql, params);
		return (int)num;
	}
	
	/**
	 * 根据条件查记录
	 * @param studentinfo
	 * @param pages
	 * @param pagesize
	 * @param orderby
	 * @param orderway
	 * @return
	 * @throws SQLException
	 */
	  private List<Map<String, String>> searchList(Foodinfo foodinfo,int pages, int pagesize, String orderby, String orderway) throws SQLException {
			int start=(pages-1)*pagesize;
			int end=pages*pagesize+1;
			String sql="select * from (select rownum as rn,fid,fname,fprice,tname||'_'||food1.ftid as ftid,fdes from food1 left join foodtype1 on food1.ftid=foodtype1.ftid where 1=1";
			List<Object> params=new ArrayList<Object>();
			if(foodinfo!=null){
				if(foodinfo.getFname()!=null && !"".equals(foodinfo.getFname())){
					sql+=" and fname like ? ";
					params.add("%"+foodinfo.getFname()+"%");
				}			
				if(foodinfo.getFtid()!=null && !"".equals(foodinfo.getFtid())){
					sql+=" and food1.ftid=? ";
					params.add(foodinfo.getFtid());
				}
				if(foodinfo.getFprice()!=null && !"".equals(foodinfo.getFprice())){
					sql+=" and food1.fprice>=? ";
					params.add(foodinfo.getFprice());
				}
				if(foodinfo.getFprice1()!=null && !"".equals(foodinfo.getFprice1())){
					sql+=" and food1.fprice<=? ";
					params.add(foodinfo.getFprice1());
				}
			}
			
//			if(orderby!=null &&!"".equals(orderby)){
//				sql+=" order by "+orderby+" "+orderway+" ";
//				/*params.add(orderby);
//				params.add(orderway);*/
//			}
			sql+=") where rn>? and rn<?";
			params.add(start);
			params.add(end);
			
			
			DBHelper db=new DBHelper();
		    return db.select(sql, params);
		}
	  
	
	
	
	
	//获取头像
	public ImageData getHeadByFtid( String ftid) throws SQLException{
		ImageData imageData=null;
		String sql="select pic from food1 where fid=?";
		Connection con=db.getCon();
		try {
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString( 1, ftid);
			ResultSet rs=pstmt.executeQuery();
			
			if(   rs.next() ){
				oracle.sql.BLOB b=(oracle.sql.BLOB) rs.getBlob(1); 
				if(  b!=null){
				 imageData=new ImageData(    b.getBinaryStream()   );
				}
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
	public int addFoodtype(String tname, String tdes) throws SQLException {
		Connection con=db.getCon();
		try {
			con.setAutoCommit(  false);
			String sql="insert into foodtype1 values(seq_food.nextval,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
		
			pstmt.setString(1, tname);
			pstmt.setString(2, tdes);
			
			pstmt.executeUpdate();
			
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
	
	
	public int upDateFoodtype(String ftid, String tname, String tdes) throws SQLException {
		Connection con=db.getCon();
		try {
			con.setAutoCommit(  false);
			con.setAutoCommit(  false);
			String sql="update foodtype1 set tname=?,tdes=? where ftid=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, tname);
			pstmt.setString(2, tdes);
			pstmt.setString(3, ftid);
			
			pstmt.executeUpdate();

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
	public int addOrderInfo(String orderRoom, String orderMoney,String orderInfos) throws SQLException {
		Connection con=db.getCon();
		try {
			con.setAutoCommit(  false);
			String sql="insert into foodorder values(seq_foodorder_foid.nextval,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);	
			pstmt.setString(1, orderRoom);
			pstmt.setString(2, orderInfos);
			pstmt.setString(3, orderMoney);
			pstmt.executeUpdate();	
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
		
}
