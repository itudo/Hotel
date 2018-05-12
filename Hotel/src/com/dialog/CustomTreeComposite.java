package com.dialog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;








import com.dao.DBHelper;
import com.ui.Custom;


public class CustomTreeComposite extends Composite {
	public Tree tree;
	private Custom cu;
	private ToShowDataComposite tsdc;
	private ToShowDataComposite1 tsdc1;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CustomTreeComposite(Composite parent, int style) {
		super(parent, style);
	}
	public CustomTreeComposite(Composite parent, int style,Custom cu,ToShowDataComposite tsdc,ToShowDataComposite1 tsdc1) {
		super(parent, style);
		this.cu=cu;
		this.tsdc=tsdc;
		this.tsdc1=tsdc1;
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		 tree = new Tree(this, SWT.BORDER);
		 tree.addSelectionListener(new SelectionAdapter() {
		 	@Override
		 	public void widgetSelected(SelectionEvent e) {
		 		
					//1.取出当前选定的数的节点的text
					TreeItem [] tis=tree.getSelection();
					if(tis==null||tis.length<=0){
						return;
					}
					cu.text.setText("0");
					cu.text_1.setText("");
					cu.text_2.setText("");
					cu.text_3.setText("");
					cu.text_5.setText("0");
					cu.text_6.setText("0");
					cu.text_7.setText("0");
					TreeItem ti=tis[0];
					String rid=ti.getText();
//					System.out.println(rid);
					List<Object> params=new ArrayList<Object>();
					DBHelper db=new DBHelper();
					String sql=" select rtname,rprice,cname,checkintime,checkouttime from"
							+ "(select  checkintime,checkouttime,cname,rid from checkin,custermer where checkin.idnum=custermer.idnum) a left join"
							+ "(select rtname,rprice,rid  from roomtype,room where roomtype.rtid=room.rtid ) b on a.rid = b.rid where b.rid =?";
					params.add(rid);
					try {
						List<Map<String,String>> list=db.select(sql, params);
						
						if(list!=null&&list.size()>0){
						List<String> columnsList=new ArrayList<String>();						    
						columnsList.add("RTNAME");
						columnsList.add("RPRICE");					
						columnsList.add("CNAME");
						columnsList.add("CHECKINTIME");
						columnsList.add("CHECKOUTTIME");
					
						List<String> tablecolumnList = new ArrayList<String>();
						tablecolumnList.add("房间类型");
						tablecolumnList.add("房间单价");
						tablecolumnList.add("客户名");
						tablecolumnList.add("入住时间");
						tablecolumnList.add("退房时间");
						 if(CustomTreeComposite.this.tsdc!=null){
							CustomTreeComposite.this.tsdc.showData(list,columnsList, tablecolumnList);
							 }
						 }
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
					String sql1="select ftotal,price from foodorder where rid=?";
					List<Object> params1=new ArrayList<Object>();
					params1.add(rid);
					try {
						List<Map<String,String>> list1=db.select(sql1, params1);
						List<List<String>> list2=new ArrayList<List<String>>();
						
//						System.out.println(list1);
						for(Map<String,String> map:list1){
						String f=map.get("FTOTAL");
						String p = map.get("PRICE");
						String[] total=f.split("_");
						for(int i=0;i<total.length;i++){
							String[] foodtotal=total[i].split("-");
							List<String> list=new ArrayList<String>();
							for(int j=0;j<foodtotal.length;j++){
								String s=foodtotal[j];
								list.add(s);		
							}
							list2.add(list);	
						}			
					}
						List<String> tablecolumnList1 = new ArrayList<String>();
						tablecolumnList1.add("食品名");
						tablecolumnList1.add("数量");
						tablecolumnList1.add("金额");
						
						CustomTreeComposite.this.tsdc1.showData1(list2,tablecolumnList1);					 

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
						String sql2="select  cname,gender,money from custermer,checkin"
								+ " where  custermer.idnum=checkin.idnum and rid=?";
						String sql3="select price from foodorder where rid=?";
						List<Object> params3=new ArrayList<Object>();
						List<Object> params4=new ArrayList<Object>();
						params3.add(rid);
						params4.add(rid);
						try{
						List<Map<String,String>> list3=db.select(sql2, params3);
						List<Map<String,String>> list4=db.select(sql3, params4);
						String price="0";
						String money="0";
						for(Map<String,String> map2:list4){
							price=map2.get("PRICE");
							cu.text_5.setText(price);
							cu.text.setText(price);
						} 
							for(Map<String,String> map1:list3){
								cu.text_1.setText(rid);
								String cname=map1.get("CNAME");
								cu.text_2.setText(cname);
								String gender=map1.get("GENDER");
								cu.text_3.setText(gender);
								money=map1.get("MONEY");
								cu.text_6.setText(money);								
							}
							int total=Integer.parseInt(price)+Integer.parseInt(money);
							cu.text_7.setText(total+"");
							cu.layout();
							/*for(Map<String, String> map:list3){
								for(Map.Entry<String, String> en:map.entrySet()){
								System.out.println(en.getKey()+"\t"+en.getValue());
								}
							}*/
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}	
		 	
		 });
		 showTree();
	
	}

	public void showTree(){
		String sql="select  rid from room where rstate='入住'";
		DBHelper db=new DBHelper();
		try {
			List<Map<String,String>> list=db.select(sql,null);
			if(list!=null&&list.size()>0){
				tree.removeAll();
				for(Map<String,String>map:list){
					TreeItem ti=new TreeItem(tree,SWT.NONE);
					ti.setText(map.get("RID"));
					ti.setData(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
