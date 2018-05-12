package com.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import com.bean.Foodinfo;
import com.biz.TakeOutBiz;
import com.dao.DBHelper;
import com.dialog.ToShowDataComposite2;
import com.swtdesigner.SWTResourceManager;






import com.utils.SwtUtils;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class TakeOut extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	public Text text_3;
	public Text text_4;
	public Text text_5;
	public Text text_6;
	public Text text_7;
	private Combo combo_1;
	public Combo combo_2;
    private	Combo combo;
	private Shell shell;
	public Label label_9;
	private Button button_1;
	private Button button_2;
	private Combo combo_3;
	private Button button_4;
	
	private int pages = 1; // 当前第几页
//	private int pagesize = 10; // 每页几条
	
	private int totalsize = 0; // 总记录数
	private int totalpages = 0; // 总页数
	private Event event;
	
	private Label label_12;
	private Label label_10;
	
	private Button button_3;//下一页
	
	private ToShowDataComposite2 tsdc;
	private DBHelper db = new DBHelper();
	public Text text_8;
	public Text text_9;
	public Text text_10;
	public Table table1;
	public Text text_12;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TakeOut(Composite parent, int style) {
		super(parent, style);
		shell = parent.getShell();
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite, SWT.NONE);
		group.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		group.setFont(SWTResourceManager.getFont("华文新魏", 16, SWT.NORMAL));
		group.setToolTipText("请输入菜名关键词");
		group.setText("菜单查询");
		group.setLayout(new FormLayout());
		
		Label label = new Label(group, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.bottom = new FormAttachment(0, 40);
		fd_label.right = new FormAttachment(0, 96);
		fd_label.top = new FormAttachment(0, 6);
		fd_label.left = new FormAttachment(0, 7);
		label.setLayoutData(fd_label);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label.setText("    菜品名\r\n(可模糊查询):");
		
		text = new Text(group, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.bottom = new FormAttachment(0, 40);
		fd_text.right = new FormAttachment(0, 274);
		fd_text.top = new FormAttachment(0, 3);
		fd_text.left = new FormAttachment(0, 110);
		text.setLayoutData(fd_text);
		text.setToolTipText("请输入菜名关键词");
		text.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		
		Label label_1 = new Label(group, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.bottom = new FormAttachment(0, 40);
		fd_label_1.right = new FormAttachment(0, 394);
		fd_label_1.top = new FormAttachment(0, 18);
		fd_label_1.left = new FormAttachment(0, 350);
		label_1.setLayoutData(fd_label_1);
		label_1.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setText("类别:");
		
		Label label_2 = new Label(group, SWT.NONE);
		FormData fd_label_2 = new FormData();
		fd_label_2.bottom = new FormAttachment(0, 35);
		fd_label_2.right = new FormAttachment(0, 692);
		fd_label_2.top = new FormAttachment(0, 18);
		fd_label_2.left = new FormAttachment(0, 612);
		label_2.setLayoutData(fd_label_2);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_2.setText("价格范围:");
		
		text_1 = new Text(group, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.right = new FormAttachment(0, 754);
		fd_text_1.top = new FormAttachment(0, 17);
		fd_text_1.left = new FormAttachment(0, 721);
		text_1.setLayoutData(fd_text_1);
		
		Label label_3 = new Label(group, SWT.NONE);
		FormData fd_label_3 = new FormData();
		fd_label_3.right = new FormAttachment(0, 793);
		fd_label_3.top = new FormAttachment(0, 18);
		fd_label_3.left = new FormAttachment(0, 760);
		label_3.setLayoutData(fd_label_3);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setText("——");
		
		text_2 = new Text(group, SWT.BORDER);
		FormData fd_text_2 = new FormData();
		fd_text_2.right = new FormAttachment(0, 832);
		fd_text_2.top = new FormAttachment(0, 17);
		fd_text_2.left = new FormAttachment(0, 799);
		text_2.setLayoutData(fd_text_2);
		
		Button button = new Button(group, SWT.NONE);
		button.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		FormData fd_button = new FormData();
		fd_button.right = new FormAttachment(0, 1105);
		fd_button.top = new FormAttachment(0, 1);
		fd_button.left = new FormAttachment(0, 1025);
		button.setLayoutData(fd_button);
		button.setText("查询");
		
		Composite composite_5 = new Composite(group, SWT.NONE);
		FormData fd_composite_5 = new FormData();
		fd_composite_5.bottom = new FormAttachment(0, 33);
		fd_composite_5.right = new FormAttachment(0, 497);
		fd_composite_5.top = new FormAttachment(0, 8);
		fd_composite_5.left = new FormAttachment(0, 400);
		composite_5.setLayoutData(fd_composite_5);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		combo_1 = new Combo(composite_5, SWT.READ_ONLY);
		combo_1.select(0);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setToolTipText("请输入菜名关键词");
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		
		tsdc = new ToShowDataComposite2(sashForm_1,SWT.NONE);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setLayout(null);
		
		combo = new Combo(composite_2, SWT.NONE);
		
		combo.setBounds(36, 36, 88, 25);
		
		label_12 = new Label(composite_2, SWT.SHADOW_NONE | SWT.CENTER);
		label_12.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_12.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_12.setBounds(0, 101, 158, 25);
		
		label_10 = new Label(composite_2, SWT.SHADOW_NONE | SWT.CENTER);
		label_10.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_10.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_10.setBounds(0, 132, 158, 25);
		
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group_3 = new Group(composite_4, SWT.NONE);
		group_3.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 16, SWT.NORMAL));
		group_3.setText("欢迎点餐");
		
		table1 = new Table(group_3, SWT.BORDER | SWT.FULL_SELECTION);
		table1.setBounds(10, 32, 195, 164);
		table1.setHeaderVisible(true);
		table1.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table1, SWT.NONE);
		tableColumn.setWidth(71);
		tableColumn.setText("菜名");
		
		TableColumn tableColumn_1 = new TableColumn(table1, SWT.NONE);
		tableColumn_1.setWidth(49);
		tableColumn_1.setText("菜量");
		
		TableColumn tableColumn_2 = new TableColumn(table1, SWT.NONE);
		tableColumn_2.setWidth(71);
		tableColumn_2.setText("菜价");
		
		Label label_11 = new Label(group_3, SWT.NONE);
		label_11.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_11.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_11.setBounds(236, 38, 80, 17);
		label_11.setText("送餐房间:");
		
		Label label_13 = new Label(group_3, SWT.NONE);
		label_13.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_13.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_13.setBounds(236, 84, 80, 17);
		label_13.setText("点餐金额:");
		
		text_12 = new Text(group_3, SWT.BORDER);
		text_12.setBounds(331, 81, 87, 23);
		
		Button button_6 = new Button(group_3, SWT.NONE);
		button_6.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 15, SWT.NORMAL));
		button_6.setBounds(303, 132, 80, 27);
		button_6.setText("点餐");
		
		combo_3 = new Combo(group_3, SWT.NONE);
		combo_3.setBounds(330, 35, 88, 25);
		sashForm_1.setWeights(new int[] {421, 139, 426});
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setLayout(null);
		
		Group group_1 = new Group(composite_3, SWT.NONE);
		group_1.setBounds(0, 0, 766, 221);
		group_1.setFont(SWTResourceManager.getFont("华文新魏", 16, SWT.NORMAL));
		group_1.setText("菜品添加");
		
		Label label_4 = new Label(group_1, SWT.NONE);
		label_4.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setBounds(31, 38, 34, 17);
		label_4.setText("编号:");
		
		text_3 = new Text(group_1, SWT.BORDER | SWT.READ_ONLY);
		text_3.setEnabled(false);
		text_3.setEditable(true);
		text_3.setBounds(85, 38, 112, 23);
		
		Label label_5 = new Label(group_1, SWT.NONE);
		label_5.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(31, 85, 50, 17);
		label_5.setText("菜品名:");
		
		text_4 = new Text(group_1, SWT.BORDER);
		text_4.setBounds(85, 82, 112, 23);
		
		Label label_6 = new Label(group_1, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_6.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_6.setBounds(31, 133, 40, 17);
		label_6.setText("价格:");
		
		text_5 = new Text(group_1, SWT.BORDER);
		text_5.setBounds(85, 130, 112, 23);
		
		Label label_7 = new Label(group_1, SWT.NONE);
		label_7.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_7.setForeground(SWTResourceManager.getColor(255, 255, 255));
		label_7.setBounds(232, 38, 40, 17);
		label_7.setText("类别:");
		
		combo_2 = new Combo(group_1, SWT.READ_ONLY);
		combo_2.setBounds(290, 32, 99, 25);
		
		Label label_8 = new Label(group_1, SWT.NONE);
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_8.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_8.setBounds(232, 85, 34, 17);
		label_8.setText("描述:");
		
		text_6 = new Text(group_1, SWT.BORDER | SWT.MULTI);
		text_6.setBounds(290, 79, 99, 68);
		
		label_9 = new Label(group_1, SWT.BORDER);
		label_9.setBounds(483, 34, 203, 112);
		
		button_1 = new Button(group_1, SWT.NONE);
		button_1.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		button_1.setBounds(91, 182, 131, 27);
		button_1.setText("清空编号并添 加");
		
		button_2 = new Button(group_1, SWT.NONE);
		button_2.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		button_2.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(0, 0, 0));
		button_2.setBounds(311, 182, 80, 27);
		button_2.setText("更 新");
		
		text_7 = new Text(group_1, SWT.BORDER);
		text_7.setBounds(483, 183, 131, 23);
		
		Button btnNewButton_3 = new Button(group_1, SWT.NONE);
		btnNewButton_3.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 10, SWT.NORMAL));
		btnNewButton_3.setText("浏 览");
		btnNewButton_3.setBounds(637, 182, 65, 27);
		
		Group group_2 = new Group(composite_3, SWT.NONE);
		group_2.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 16, SWT.NORMAL));
		group_2.setText("菜类添加");
		group_2.setBounds(835, 0, 342, 221);
		
		Label lblNewLabel = new Label(group_2, SWT.NONE);
		lblNewLabel.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		lblNewLabel.setBounds(51, 39, 48, 17);
		lblNewLabel.setText("编 号：");
		
		Label lblNewLabel_1 = new Label(group_2, SWT.NONE);
		lblNewLabel_1.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		lblNewLabel_1.setBounds(51, 84, 42, 17);
		lblNewLabel_1.setText("类 别：");
		
		Label lblNewLabel_2 = new Label(group_2, SWT.NONE);
		lblNewLabel_2.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		lblNewLabel_2.setBounds(51, 123, 42, 17);
		lblNewLabel_2.setText("描 述：");
		
		text_8 = new Text(group_2, SWT.BORDER | SWT.READ_ONLY);
		text_8.setEnabled(false);
		text_8.setEditable(true);
		text_8.setBounds(115, 39, 87, 23);
		
		text_9 = new Text(group_2, SWT.BORDER);
		text_9.setBounds(115, 78, 87, 23);
		
		text_10 = new Text(group_2, SWT.BORDER);
		text_10.setBounds(115, 107, 176, 44);
		
		button_4 = new Button(group_2, SWT.NONE);
	
		
		button_4.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		button_4.setBounds(62, 172, 140, 27);
		button_4.setText("清空编号并添 加");
		
		Button button_5 = new Button(group_2, SWT.NONE);
		
		button_5.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		button_5.setBounds(230, 172, 80, 27);
		button_5.setText("修 改");
		sashForm.setWeights(new int[] {83, 196, 220});
		//点餐
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String orderRoom = combo_3.getText();
				if(orderRoom==null||"请选择房间:".equals(orderRoom)) {
					SwtUtils.showMessageBox(shell,"点餐失败","请选择点餐房间");
					return;
				}
				String orderMoney = text_12.getText();
				if(orderMoney==null||"".equals(orderMoney)) {
					SwtUtils.showMessageBox(shell,"点餐失败","请点餐");
					return;
				}
				TableItem[] ti = table1.getItems();
				String[] orderInfo = new String[ti.length];
				String orderInfos = "";
				for(int i=0;i<ti.length;i++) {
					orderInfo[i] = ti[i].getText(0)+"-"+ti[i].getText(1)+"-"+ti[i].getText(2)+"_";
					orderInfos += orderInfo[i];
				}
				TakeOutBiz tob = new TakeOutBiz();
				try {
					int r = tob.addOrderInfo(orderRoom,orderMoney,orderInfos);
					if(r>0) {
						SwtUtils.showMessageBox(shell,"点餐成功","用户点餐成功");
						table1.removeAll();
						/*int columns=table1.getColumns().length;
						TableColumn[] tcs=table1.getColumns();
						for(int i=1;i<columns;i++){
							tcs[i].dispose();
						}*/
					} else {
						SwtUtils.showMessageBox(shell,"点餐失败","用户点餐失败");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//修改菜类
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String ftid = text_8.getText().trim();
				String tname = text_9.getText().trim();
				String tdes = text_10.getText().trim();
				TakeOutBiz tob = new TakeOutBiz();
				
				if(tname==null || "".equals(tname)){
	     	    	SwtUtils.showMessageBox(shell, "出错了", "类别名不能为空！");
	     	    	return;
	     	    }				
				if(tdes==null||"".equals(tdes)){
					SwtUtils.showMessageBox(shell, "出错了", "未填写描述");
					return;
				}
				try {
					int r = tob.upDateFoodtype(ftid,tname, tdes);
					if(r>0) {
						SwtUtils.showMessageBox(shell,"修改成功","菜类修改成功");
						initUi();
					} else {
						SwtUtils.showMessageBox(shell, "修改失败", "菜类修改失败");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//菜类添加
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_8.setText("");
				String tname=text_9.getText().toString();
				String tdes=text_10.getText().toString();
                
				TakeOutBiz tob = new TakeOutBiz();
				
				if(tname==null || "".equals(tname)){
	     	    	SwtUtils.showMessageBox(shell, "出错了", "类别名为空！");
	     	    	return;
	     	    }
				String sql="select tname from foodtype1";
				DBHelper db=new DBHelper();
				try {
					List<Map<String,String>> list=db.select(sql, null);
					for(Map<String,String> map:list){
						String tname1=map.get("TNAME");
						if(tname1.equals(tname)){
							SwtUtils.showMessageBox(shell, "出错了", "该类别名已经存在");
							return;
						}
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				if(tdes==null||"".equals(tdes)){
					SwtUtils.showMessageBox(shell, "出错了", "未填写描述");
					return;
				}
				try {
					int r = tob.addFoodtype(tname,tdes);
					if(r>0) {
						SwtUtils.showMessageBox(shell,"成功","添加成功");
						text_8.setText("");
						text_9.setText("");
						text_10.setText("");
						initUi();
					} else {
						SwtUtils.showMessageBox(shell, "失败", "添加失败");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//按页数查
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
		
				String pages="1";
				if(combo.getText()!=null && !"".equals(combo.getText())){
					pages=combo.getText();
				}
				String fname=text.getText();
				Foodinfo food=new Foodinfo();
				food.setFname(fname);
				search(food,pages,7,"fname","desc");
					
			}
		});
		
		//更新菜品
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String fid = text_3.getText().trim();
				String fname = text_4.getText().trim();
				String fprice = text_5.getText().trim();
				String ftid = combo_2.getText().split("_")[1];
				String fdes = text_6.getText().trim();
				String picpath = text_7.getText().trim();
				
				if(fname==null || "".equals(fname)){
	     	    	SwtUtils.showMessageBox(shell, "出错了", "菜名为空！");
	     	    	return;
	     	    }			
				
				if(fdes==null||"".equals(fdes)){
					SwtUtils.showMessageBox(shell, "出错了", "未填写描述");
					return;
				}
				if(fprice==null||"".equals(fprice)){
					SwtUtils.showMessageBox(shell, "出错了", "未填写价格");
					return;
				}
				TakeOutBiz tob = new TakeOutBiz();
				try {
					int r = tob.upDateFood(ftid,fname, fprice,fid,fdes,picpath);
					if(r>0) {
						SwtUtils.showMessageBox(shell,"成功","更新成功");
						
					} else {
						SwtUtils.showMessageBox(shell, "失败", "更新失败");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		//浏览
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog=new FileDialog(parent.getShell(), SWT.OPEN);
				dialog.setFilterPath("f:");  //设置初始路径
				dialog.setFilterExtensions(new String[]{"*.jpg","*.png","*.*"});//过滤
				String fileName=dialog.open();//返回的全路径（路径+文件名）
				if(fileName==null  ||"".equals(fileName)){
					return;
				}
				text_7.setText(fileName);
				
				//以流来读取图片
				
				try {
					FileInputStream stream=new FileInputStream(fileName);
					ImageData imageData=new ImageData(   stream);//读到原大小的图片
					//转换图片的大小 scaledTo等比例大小
					imageData=imageData.scaledTo(label_9.getBounds().width, label_9.getBounds().height);
					Image image=new Image(parent.getDisplay(), imageData);
					label_9.setImage(image);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		//添加菜品
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_3.setText("");
				String fname = text_4.getText().trim();
				String fprice = text_5.getText().trim();
				if(combo_2.getText()==null||"请选择类别:".equals(combo_2.getText())){
					SwtUtils.showMessageBox(shell, "出错了", "未选择类别");
					return;
				}
				String ftid = combo_2.getText().split("_")[1];
				String fdes = text_6.getText().trim();
				String picpath = text_7.getText().trim();
				TakeOutBiz tob = new TakeOutBiz();
			
				if(fname==null || "".equals(fname)){
	     	    	SwtUtils.showMessageBox(shell, "出错了", "菜名为空！");
	     	    	return;
	     	    }
				String sql="select fname from food1";
				DBHelper db=new DBHelper();
				try {
					List<Map<String,String>> list=db.select(sql, null);
					for(Map<String,String> map:list){
						String fname1=map.get("FNAME");
						if(fname1.equals(fname)){
							SwtUtils.showMessageBox(shell, "出错了", "菜名不能相同");
							return;
						}
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				if(fdes==null||"".equals(fdes)){
					SwtUtils.showMessageBox(shell, "出错了", "未填写描述");
					return;
				}
				if(fprice==null||"".equals(fprice)){
					SwtUtils.showMessageBox(shell, "出错了", "未填写价格");
					return;
				}
				if(picpath==null || "".equals(picpath)){
					SwtUtils.showMessageBox(shell, "出错了", "未添加图片");
	     	    	return;
	     	    }
	     		
				
				try {
					int r = tob.addFood(fname, fprice, ftid,fdes,picpath);
					if(r>0) {
						SwtUtils.showMessageBox(shell,"成功","添加成功");
						text_3.setText("");
						text_4.setText("");
						text_5.setText("");
						text_6.setText("");
						text_7.setText("");
						
					} else {
						SwtUtils.showMessageBox(shell, "失败", "添加失败");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//菜品名查询
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String fname=text.getText().trim();
				Foodinfo food=new Foodinfo();
				food.setFname(fname);
				if(combo_1.getText().trim()!=null && !"请选择类别:".equals(combo_1.getText().trim())&&!"".equals(combo_1.getText().trim())){
					String[] ftid1=combo_1.getText().trim().split("_");
					String ftid=ftid1[1];
					food.setFtid(ftid);
				}
				if(text_1.getText().trim()!=null && !"".equals(text_1.getText().trim())){
					food.setFprice(text_1.getText().trim());
				}
				if(text_2.getText().trim()!=null && !"".equals(text_2.getText().trim())){
					food.setFprice1(text_2.getText().trim());
				}
				search(food,"1",7,"fname","desc");
				Map map=new HashMap();
				map.put("food", food);
				map.put("pages", 1);
				map.put("pagesize", 7);
				map.put("orderby", "fname");
				map.put("orderway", "desc");
				
				
				TakeOutBiz tb=new TakeOutBiz();
				try {
					Map result=tb.search(map);
					List<Map<String,String>> list=(List<Map<String,String>>) result.get("list");
				    List<String> columnsList=new ArrayList<String>();
				    
				    columnsList.add("FID");
				    columnsList.add("FNAME");
				    columnsList.add("FPRICE");
				    columnsList.add("FTID");
				    columnsList.add("FDES");
					
					List<String> tableColumnList = new ArrayList<String>();
					tableColumnList.add("菜号");
					tableColumnList.add("菜名");
					tableColumnList.add("菜价");
					tableColumnList.add("菜类");
					tableColumnList.add("描述");
					
				    TakeOut.this.tsdc.showData(list, columnsList, tableColumnList);
					int pages=Integer.parseInt(map.get("pages").toString());
				    int pagesize=Integer.parseInt(map.get("pagesize").toString());
				    int num=Integer.parseInt(map.get("num").toString());
						    
				    int total=num%pagesize==0?num/pagesize:num/pagesize+1;
					label_12.setText("当前第"+pages+"页/共"+total+"页");  
					label_10.setText( "每页"+pagesize+"条/共"+num+"记录"); 
					combo.removeAll();
					for(int i=1;i<=total;i++){
						combo.add(i+"");
						}
						combo.setText(pages+"");
						
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		initUi();
		initroom() ;
	}
	
	protected void search( Foodinfo food ,String pages, int pagesize,String orderby, String orderway) {
		Map map=new HashMap();
		
		map.put("pages", pages);
		map.put("pagesize", pagesize);
		map.put("orderby", orderby);
		map.put("orderway", orderway);
	
		
		TakeOutBiz tb=new TakeOutBiz();
		
		try {
			Map result=tb.search(map);//pages pagesize orderby orderway student(sname) num list
		   
		    List<Map<String,String>> list=(List<Map<String,String>>) result.get("list");
		    List<String>columnsList=new ArrayList<String>();
		    columnsList.add("FID");
		    columnsList.add("FNAME");
		    columnsList.add("FPRICE");
		    
		    columnsList.add("FTID");
		    columnsList.add("FDES");
		  
		    
		    List<String> tableColumnList=new ArrayList<String>();
		    tableColumnList.add("编号");
		    tableColumnList.add("名称");
		    tableColumnList.add("价格");
		    
		    tableColumnList.add("类型");
		    tableColumnList.add("描述");
		   
		    tsdc.showData(list, columnsList, tableColumnList);
		   
		    int ps=Integer.parseInt(map.get("pagesize").toString());
		    int num=Integer.parseInt(map.get("num").toString());
		    
		    int total=num%ps==0?num/ps:num/ps+1;
		   
			label_12.setText("当前第"+pages+"页/共"+total+"页");  
		    label_10.setText("每页"+pagesize+"条/共"+num+"记录");
			combo.removeAll();
			for(int i=1;i<=total;i++){
				combo.add(i+"");
			}
		     combo.setText(pages+"");
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
	}


	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	public void initUi() {
		String sql = "select * from foodtype1";
		db = new DBHelper();
		try {
			List<Map<String, String>> list = db.select(sql, null);
			if (list != null && list.size() > 0) {
				combo_1.removeAll();
				combo_2.removeAll();
				combo_1.add("请选择类别:");
				combo_2.add("请选择类别:");
				for (Map<String, String> data : list) {
					String tname = data.get("TNAME");
					String ftid = data.get("FTID");
					combo_1.add(tname+"_"+ftid);
					combo_2.add(tname+"_"+ftid);
				}
			}
			combo_1.select(0);
			combo_2.select(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void initroom() {
		
		String sql = "select rid from room where rstate = '入住' order by rid";
		db = new DBHelper();
		try {
			List<Map<String,String>> list = db.select(sql, null);
			combo_3.removeAll();
			combo_3.add("请选择房间:");
			if(list != null&& list.size() >0 ) {
				for(Map<String,String> map:list) {
					String rid = map.get("RID");
					combo_3.add(rid);
				}
			}
			combo_3.select(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		combo_3.setText("请选择房间:");
	}
}
