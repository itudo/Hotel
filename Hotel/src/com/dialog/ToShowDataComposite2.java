package com.dialog;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.widgets.Button;

import com.biz.TakeOutBiz;
import com.dao.DBHelper;
import com.ui.TakeOut;

public class ToShowDataComposite2 extends Composite {
	public Table table;
	private Composite parent;
	private Shell shell;
	private int row = 1;
	private int clo = 1;
	private String name = "";
	private int count = 1;
	private Map<String,Integer> map = new TreeMap<String,Integer>();
	private Map<String,Integer> map1 = new TreeMap<String,Integer>();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ToShowDataComposite2(Composite parent, int style) {
		super(parent, style);
		this.parent = parent;
		shell = parent.getShell();
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis = table.getSelection();
				if(tis == null || tis.length <0) {
					return;
				}
				TableItem ti = tis[0];
				Composite c = ToShowDataComposite2.this.parent.getParent().getParent().getParent();
				if(c instanceof TakeOut) {
					TakeOut to = (TakeOut) c;
					String fname = ti.getText(1);
					String fprice = ti.getText(2);
					Integer price = map1.get(fname);
					Integer count = map.get(fname);
					if(count != null) {
						map.put(fname, ++count);
					} else {
						map.put(fname, 1);		
					}
					if(price != null) {
						map1.put(fname, Integer.parseInt(fprice)*count);
					} else {
						map1.put(fname, Integer.parseInt(fprice));
					}
				int pricetotal = showData(map,map1,to.table1);
				to.text_12.setText(String.valueOf(pricetotal));
				}		
			}
		});
		menuItem.setText("点餐");
		
		//表单选中
				table.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						TableItem[] tis = table.getSelection();
						if(tis == null || tis.length <0) {
							return;
						}
						TableItem ti = tis[0];
						//判断当前这块面板是否在Studentinfo中
						Composite c = ToShowDataComposite2.this.parent.getParent().getParent().getParent();
						if(c instanceof TakeOut) {
							//这样就可以操作到父面板的控件
							TakeOut to = (TakeOut) c;
							String ftid = ti.getText(0);
							String fname = ti.getText(1);
							String fprice = ti.getText(2);
							String fidname = ti.getText(3);
							String fid = fidname.split("_")[1];
							String tname = fidname.split("_")[0];
							String fdes = ti.getText(4);
							
							to.text_3.setText(ftid);
							to.text_4.setText(fname);
							to.text_5.setText(fprice);
							to.combo_2.setText(fidname);
							to.text_6.setText(fdes);
							to.text_8.setText(fid);
							to.text_9.setText(tname);
							String sql = "select tdes from foodtype1 where ftid = ?";
							List<Object> params = new ArrayList<Object>();
							params.add(fid);
							DBHelper db = new DBHelper();
							try {
								List<Map<String,String>> list = db.select(sql, params);
								if(list != null && list.size()>0) {
									for(Map<String,String> map:list) {
										String desc = map.get("TDES");
										to.text_10.setText(desc);
									}
								}
							} catch (SQLException e2) {
								e2.printStackTrace();
							}
							// 以流来取图片
							TakeOutBiz tob = new TakeOutBiz();
							try {
								ImageData imageData = tob.getHeadByFtid(ftid);
								if (imageData != null) {
									ImageData newid = imageData.scaledTo(
											to.label_9.getBounds().width,
											to.label_9.getBounds().height);

									Image image = new Image(shell.getDisplay(), newid);
									to.label_9.setImage(image);
								} else {
									to.label_9.setText("");
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
						}	
					}
				});
	}
	public void order() {
		TableItem[] tis = table.getItems();
		for(int i=0;i<tis.length;i++) {
			boolean select = tis[i].getChecked();
			if(select == true) {
				String ftid = tis[i].getText(0);
//				System.out.println(ftid);
			}
		}
	}
	public int  showData(Map<String,Integer> map,Map<String,Integer> map1,Table table) {
		//1.清空table中所有行
		table.removeAll();
		String[] values = new String[3];
		String[] price = new String[map.size()];
		int i = 0;
		int pricetotal = 0;
		for(Entry<String, Integer> data1:map1.entrySet()) {
			price[i] = String.valueOf(data1.getValue());
			i++;
			pricetotal += data1.getValue();
		}
		int j = 0;
		for(Entry<String, Integer> data:map.entrySet()) {
			values[0] = data.getKey();
			values[1] = String.valueOf(data.getValue());
			values[2] = price[j];
			j++;
			TableItem tableItem = new TableItem(table,SWT.None);
			tableItem.setText(values);
		}
		return pricetotal;
	}
	public void showData(List<Map<String,String>> dataList, List<String> columnsList,List<String> tableColumnList) {
		//1.清空table中所有行
		table.removeAll();
		//2清空table中所有列
		int columns = table.getColumns().length;
		TableColumn[] tcs = table.getColumns();
		for(int i=0;i<columns;i++) {
			tcs[i].dispose();
		}
		//3.循环tableColumnList,将队列加入table
		for(String tc:tableColumnList) {
			TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn.setWidth(100);
			tblclmnNewColumn.setText(tc);
		}
		//4.循环dataList，取出每个Map<String,String>
		for(Map<String,String> map:dataList) {
			String[] values = new String[columnsList.size()];
			int i = 0;
			  //5.循环每个columnsList,取出每个键名 key
			  for(String key:columnsList) {
				  //6.map.get(key)；取值，存到每一个数组中
					String value = map.get(key);
					values[i] = value;
					i++;
			  }
			  // 7.table.setText(数组)
			TableItem tableItem = new TableItem(table,SWT.None);
			tableItem.setText(values);
		}
		   
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	public void showData1(List<List<String>> list,List<String> tablecolumnList) {
		// 1.清空table中所有行
		table.removeAll();
		// 2清空table中所有列
		int columns = table.getColumns().length;
		TableColumn[] tcs = table.getColumns();
		for (int i = 0; i < columns; i++) {
			tcs[i].dispose();
		}
		// 3.循环tableColumnList,将队列加入table
		for (String tc : tablecolumnList) {
			TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
			tblclmnNewColumn.setWidth(100);
			tblclmnNewColumn.setText(tc);
		}
		// 4.循环dataList，取出每个Map<String,String>
		List<String> list1=new ArrayList<String>();
		for (List<String> l1: list) {
			list1 = l1;
			String[] values = new String[tablecolumnList.size()];
			int i = 0;
			// 5.循环每个columnsList,取出每个键名 key
			for (String l2 : list1) {
				// 6.map.get(key)；取值，存到每一个数组中
				values[i] = l2;
				i++;
			}
			// 7.table.setText(数组)
			TableItem tableItem = new TableItem(table, SWT.None);
			tableItem.setText(values);
		}

	}
}
