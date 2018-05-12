package com.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import com.biz.TestBarChart;
import com.dao.DBHelper;
import com.dialog.ToShowDataComposite1;
import com.dialog.zhexianComposite;
import com.dialog.zhuzhuangComposite;
import com.utils.ExcelUtils;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Finance extends Composite {
	private ToShowDataComposite1 tsdc;
	private DBHelper db = new DBHelper();
	private Combo combo;
	private Combo combo_1;
	private Label lblNewLabel_2;
	private Label label_3;//柱状图
	private Text text;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Finance(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		
		combo = new Combo(composite, SWT.NONE);
		combo.setTouchEnabled(true);
		combo.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		combo.setBounds(125, 40, 88, 25);
		
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label.setAlignment(SWT.CENTER);
		label.setBounds(242, 43, 32, 25);
		label.setText("年");
		
		combo_1 = new Combo(composite, SWT.NONE);
		combo_1.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		combo_1.setBounds(305, 40, 88, 25);
		combo_1.select(0);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_1.setAlignment(SWT.CENTER);
		label_1.setBounds(406, 40, 25, 25);
		label_1.setText("月");
		
		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		
		button.setBounds(566, 35, 80, 27);
		button.setText("查 询");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("华文新魏", 15, SWT.NORMAL));
		label_2.setBounds(737, 37, 73, 22);
		label_2.setText("总金额：");
		
		Group group_3 = new Group(composite, SWT.NONE);
		group_3.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		group_3.setText("导出");
		group_3.setBounds(1008, 10, 101, 84);
		
		lblNewLabel_2 = new Label(group_3, SWT.CENTER);
		
		
		lblNewLabel_2.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		lblNewLabel_2.setBounds(10, 27, 61, 17);
		lblNewLabel_2.setText("月账单");
		
		label_3 = new Label(group_3, SWT.CENTER);
		
		label_3.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_3.setBounds(10, 50, 61, 17);
		label_3.setText("柱状图");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(836, 37, 73, 23);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.BORDER);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite_3, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("华文新魏", 15, SWT.NORMAL));
		tsdc = new ToShowDataComposite1(group,SWT.NONE);
		group.setText("月账单");
		group.setLayout(new FillLayout(SWT.HORIZONTAL));
		sashForm_1.setWeights(new int[] {600});
		sashForm.setWeights(new int[] {102, 365});

		
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				JFrame frame = new JFrame("日账单柱状图");
				// frame.setLayout(new GridLayout(2,2,10,10));
				frame.add(new TestBarChart().getChartPanel()); // 添加柱形图
				frame.setBounds(50, 50, 800, 600);
				frame.setVisible(true);
			}
		});
	
		
		//导出Excel
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				String date = combo.getText()+combo_1.getText();
				ExcelUtils.listInfo(date,tsdc.table);
			}
		});
		
		//查询
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String year=combo.getText().trim();
				String month=combo_1.getText().trim();
				String d = year+"-"+month;
				String sql="select to_char(checkin.checkouttime,'yyyy-MM-dd') as checkouttime,room.rid,checkin.money,foodorder.price,(checkin.money+foodorder.price)as total from checkin,room,foodorder where checkin.rid=room.rid and checkin.rid=foodorder.rid and to_char(checkin.checkouttime,'yyyy-MM')=?";
				List<Object> params = new ArrayList<Object>();
				
					params.add(d);

			try {
				List<Map<String,String>> list=db.select(sql, params);
					int a=totalUi(list);
                    text.setText(String.valueOf(a));
					List<String> columnsList=new ArrayList<String>();
					columnsList.add("CHECKOUTTIME");
					columnsList.add("RID");
					columnsList.add("MONEY");
					columnsList.add("PRICE");
					columnsList.add("TOTAL");
					List<String> tablecolumnList=new ArrayList<String>();
					tablecolumnList.add("日期");
					tablecolumnList.add("房间号");
					tablecolumnList.add("住房消费");
					tablecolumnList.add("食品消费");
					tablecolumnList.add("总金额");
					tsdc.showData(list, columnsList, tablecolumnList);
					tsdc.layout();
					
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
		});	
		initUi();	

	}
	public void initUi() {
		// 1. 查询 时间,显示到 三个combo中
		String sql1 = "select distinct(to_char(checkouttime,'yyyy')) as year from checkin";
		String sql2 = "select distinct(to_char(checkouttime,'MM')) as month from checkin";
		db = new DBHelper();
		try {
			List<Map<String, String>> list1 = db.select(sql1, null);
			List<Map<String, String>> list2 = db.select(sql2, null);
			if (list1 != null && list1.size() > 0) {
				combo.removeAll();
				for (Map<String, String> data : list1) {
					String year =data.get("YEAR");
					combo.add(year);
					combo.select(0);
				}
			}
			if (list2 != null && list2.size() > 0) {
				combo_1.removeAll();
				for (Map<String, String> data : list2) {
					String month =data.get("MONTH");
					combo_1.add(month);
					combo_1.select(0);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public int totalUi(List<Map<String,String>> list){
		 int s1=0;
		for(Map<String,String> map: list){
			int s2=Integer.parseInt(map.get("TOTAL"));
			s1+=s2; 
		}
		return s1;
	}
}
