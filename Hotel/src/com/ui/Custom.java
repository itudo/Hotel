package com.ui;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;

import com.bean.Foodinfo;
import com.biz.TakeOutBiz;
import com.dao.DBHelper;
import com.dialog.CustomTreeComposite;
import com.dialog.ToShowDataComposite;
import com.dialog.ToShowDataComposite1;
import com.printer.GoodsInfo;
import com.printer.Printer;
import com.printer.SalesTicket;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Custom extends Composite {
	public Composite composite_6;
	public Text text_1;
	public Text text_2;
	public Text text_3;
	public Text text_5;
	public Text text_6;
	public Text text_7;
	private CustomTreeComposite ctc; //结账树的面板
	private ToShowDataComposite composite_7;
	private ToShowDataComposite1 composite_8;
	public Text text;
	public Composite composite_3;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Custom(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		composite.setLayout(null);
		
		
		Label lblNewLabel_6 = new Label(composite, SWT.NONE);
		lblNewLabel_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_6.setFont(SWTResourceManager.getFont("华文新魏", 16, SWT.NORMAL));
		lblNewLabel_6.setBounds(53, 64, 69, 21);
		lblNewLabel_6.setText("房间号");

		
		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.VERTICAL);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite_2, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		
		group.setText("食品消费查询");
		group.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		
		
		composite_3 = new Composite(sashForm_1, SWT.BORDER);
		
		Label label = new Label(composite_3, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label.setBounds(423, 10, 69, 17);
		label.setText("总  计：");
		
		text = new Text(composite_3, SWT.READ_ONLY);
		text.setBounds(496, 10, 73, 23);
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group_2 = new Group(composite_4, SWT.NONE);
		group_2.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		group_2.setText("订房消费查询");
		group_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		 composite_8 = new ToShowDataComposite1(group, SWT.NONE);
		 composite_7 = new ToShowDataComposite(group_2, SWT.NONE);
		ctc=new CustomTreeComposite( composite ,SWT.NONE,this,composite_7,composite_8);
		sashForm_1.setWeights(new int[] {240, 47, 173});
		ctc.setBounds(47, 104, 86, 269);
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ctc.showTree();
			}
		});
		button.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		button.setBounds(53, 418, 80, 27);
		button.setText("刷  新");
		ctc.layout();
		Composite composite_5 = new Composite(sashForm, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group_1 = new Group(composite_5, SWT.BORDER);
		group_1.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		group_1.setText("账单");
		
		Label label_1 = new Label(group_1, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		label_1.setBounds(27, 51, 73, 17);
		label_1.setText("    房间号：");
		
		text_1 = new Text(group_1, SWT.READ_ONLY);
		text_1.setBounds(126, 50, 73, 23);
		
		Label lblNewLabel = new Label(group_1, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		lblNewLabel.setBounds(27, 95, 73, 17);
		lblNewLabel.setText("    顾客号：");
		
		Label lblNewLabel_1 = new Label(group_1, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(27, 143, 73, 17);
		lblNewLabel_1.setText("    性    别：");
		
		Label lblNewLabel_3 = new Label(group_1, SWT.NONE);
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_3.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		lblNewLabel_3.setBounds(27, 195, 73, 17);
		lblNewLabel_3.setText("食品消费：");
		
		Label lblNewLabel_4 = new Label(group_1, SWT.NONE);
		lblNewLabel_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_4.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		lblNewLabel_4.setBounds(27, 248, 73, 17);
		lblNewLabel_4.setText("住房消费：");
		
		Label lblNewLabel_5 = new Label(group_1, SWT.NONE);
		lblNewLabel_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_5.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		lblNewLabel_5.setBounds(27, 299, 73, 17);
		lblNewLabel_5.setText("    总消费：");
		
		Button button_1 = new Button(group_1, SWT.NONE);
		
		button_1.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		button_1.setBounds(126, 370, 80, 27);
		button_1.setText("打  印");
		
		text_2 = new Text(group_1, SWT.READ_ONLY);
		text_2.setBounds(126, 94, 73, 23);
		
		text_3 = new Text(group_1, SWT.READ_ONLY);
		text_3.setBounds(126, 142, 73, 23);
		
		text_5 = new Text(group_1, SWT.READ_ONLY);
		text_5.setBounds(126, 194, 73, 23);
		
		text_6 = new Text(group_1, SWT.READ_ONLY);
		text_6.setBounds(126, 247, 73, 23);
		
		text_7 = new Text(group_1, SWT.READ_ONLY);
		text_7.setBounds(126, 298, 73, 23);
		sashForm.setWeights(new int[] {141, 522, 218});
		
		
		//打印
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DBHelper db=new DBHelper();
				String zhutotal="";
				List<GoodsInfo> goods=new ArrayList<GoodsInfo>();
				String rid=text_1.getText();
//				System.out.println(rid);
				String sql="select rprice,money from (select rprice,rid from roomtype,room where roomtype.rtid=room.rtid) a"
						+ " inner join (select money,rid from checkin) b on a.rid=b.rid and a.rid=?";
				List<Object> params=new ArrayList<Object>();
				params.add(rid);
				try {
					List<Map<String,String>> list=db.select(sql, params);
					for(Map<String,String> map:list){
						String zhu=map.get("RPRICE");
						zhutotal=map.get("MONEY");
						goods.add(new GoodsInfo(rid,zhu,"1",zhutotal));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				int pricetotal = 0;
				String sql1="select ftotal,price from foodorder where rid=?";
				List<Object> params1=new ArrayList<Object>();
				params1.add(rid);
				try {
					List<Map<String,String>> list1=db.select(sql1, params1);
					List<List<String>> list2=new ArrayList<List<String>>();
					
//					System.out.println(list1);
					for(Map<String,String> map:list1){
					String f=map.get("FTOTAL");
					String p = map.get("PRICE");
					String[] total=f.split("_");
					String s="";
					for(int i=0;i<total.length;i++){
						String[] foodtotal=total[i].split("-");
						//for(int j=0;j<foodtotal.length;j++){
							// s=foodtotal[j];
							String sql2 = "select fprice from food1 where fname = ?";
							List<Object> params2 = new ArrayList<Object>();
							params2.add(foodtotal[0]);
							List<Map<String,String>> list3 = db.select(sql2, params2);
							for(Map<String,String> map2:list3) {
								String price = map2.get("FPRICE");
								 goods.add(new GoodsInfo(foodtotal[0],price,foodtotal[1],foodtotal[2]));
								 pricetotal+=Integer.parseInt(foodtotal[2]);
							}
							
						//}
						/*System.out.print(s);
						System.out.println();*/
					}	
					
				}
					
					} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Date dd = new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");  
		        String day =sdf.format(dd);  
		        int i = Integer.parseInt(zhutotal) +pricetotal;
				SalesTicket stk=new SalesTicket(goods,"Starry Sky",day,"4",String.valueOf(i),String.valueOf(i),"0");
				Printer p=new Printer(stk);
				p.printer();
			}
		});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
