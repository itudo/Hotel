package com.dialog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import com.biz.HotelBiz;
import com.dao.DBHelper;
import com.ui.RoomsManage;

public class ToShowDataComposite extends Composite {
	private Table table;
	private Composite parent;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ToShowDataComposite(Composite parent, int style) {
		super(parent, style);
		this.parent=parent;

		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				if(tis==null||tis.length<0){
					return;
		}
				TableItem ti=tis[0];
				String rstate=ti.getText(3);//根据状态来决定面板的跳转
				
			Composite c=ToShowDataComposite.this.parent.getParent().getParent().getParent().getParent();
				if(c instanceof RoomsManage){
					RoomsManage rzu=(RoomsManage)c;
					if("空闲".equals(rstate)||"未整理".equals(rstate)){
						rzu.sl.topControl=rzu.fu;
					}
					if("入住".equals(rstate)||"预订".equals(rstate)){
						rzu.sl.topControl=rzu.ru;
					}
					rzu.composite_4.layout();										
					String rid=ti.getText(0);//房间编号
					rzu.fu.text.setText(rid);										
					String rtname=ti.getText(1);//房间类型
					rzu.fu.combo_1.setText(rtname);									
					rzu.fu.text_2.setText(rstate);//房间状态										
					String rprice=ti.getText(4);
					rzu.fu.text_3.setText(rprice);//房间价格
					String locate=ti.getText(2);
					rzu.fu.combo_2.setText(locate);//房间位置
					rzu.fu.text_1.setText("");
					HotelBiz hb=new HotelBiz();
					try {
						ImageData imageData=hb.getHeadByRid(rid);
						ImageData newid=imageData.scaledTo(rzu.fu.label_2.getBounds().width,rzu.fu.label_2.getBounds().height);
						Image image=new Image(c.getDisplay(),newid);
						rzu.fu.label_2.setImage(image);
					} catch (SQLException e1) {
						e1.printStackTrace();
						}
					
//入住面板得到客户信息
					String sql="select cu.idnum,cu.cname,cu.gender,cu.tel,ch.orderid,to_char(ch.checkintime,'yyyy-MM-dd') as checkintime,ch.money from custermer cu,checkin ch where cu.idnum=ch.idnum and ch.rid=? ";
					List<Object> params = new ArrayList<Object>();
					params.add(rid);
					DBHelper db = new DBHelper();					
					try {
						List<Map<String,String>> list = db.select(sql, params);
						if(list != null && list.size() > 0) {
							for(Map<String,String> map:list) {
								rzu.ru.label.setText(map.get("GENDER"));
								rzu.ru.text_3.setText(map.get("TEL"));
								rzu.ru.text_4.setText(map.get("ORDERID"));
								rzu.ru.text_5.setText(map.get("MONEY"));
								rzu.ru.text_6.setText(map.get("CHECKINTIME"));
								rzu.ru.label_1.setText(map.get("IDNUM"));
								rzu.ru.lblNewLabel_1.setText(map.get("CNAME"));
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					}
				
				}			
		});
		
	}

	/**
	 * @param dataList:要显示的数据
	 * @param colunmsList：map中键的取出顺序集合
	 * @param talecolunmList：table中的各列的列名
	 */
	public void showData(List<Map<String,String>> dataList,List<String> columnsList,List<String> tablecolumnList){
		//1、清空table中所有行
		table.removeAll();
		//2、清空table中所有列
		int columns=table.getColumns().length;
		TableColumn[] tcs=table.getColumns();
		for(int i=0;i<columns;i++){
			tcs[i].dispose();
		}
		//3、循环tablecolumnList，将列加入到table
		for(String tc:tablecolumnList){
			TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn.setWidth(100);
			tblclmnNewColumn.setText(tc);
		}		
		//4、循环dataList，取出每个Map<String,String>
		for(Map<String,String> map:dataList){
			String[] values=new String[columnsList.size()];
			int i=0;
			//5、循环columnList，取出每个键名  key
			for(String key:columnsList){
				//6、map.get(key) ;取值，存到一个数组中去
				String value=map.get(key);
				values[i]=value;
				i++;			
			}
			//7、将行加入

			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(values);
			
		}
				
	}

}
