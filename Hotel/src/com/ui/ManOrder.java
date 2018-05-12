package com.ui;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.biz.HotelBiz;
import com.biz.TimeBiz;
import com.dao.DBHelper;
import com.dialog.ChooseDialog;
import com.dialog.FreeComposite;
import com.dialog.RoomInfoDialog;
import com.swtdesigner.SWTResourceManager;
import com.utils.DateUtils;
import com.utils.SwtUtils;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class ManOrder extends Composite {
	
	private Shell shell;
	public Button button_1;
	public Text text;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	public DateTime dateTime;
	public DateTime dateTime_1;
	private Composite composite_1;
	public Composite composite_dan;
	private TabFolder tabFolder;
	private Composite composite_5;
	private Label label_5;
	private FreeComposite fc;
	private String rid;
	private DBHelper db = new DBHelper();
	private String moneys;
	private Label label_10;
	private String s = "";
	private Label label_6;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ManOrder(Composite parent, int style) {
		super(parent, style);
		this.shell=parent.getShell();
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		composite.setLayout(new FormLayout());
		
		Label labeltime = new Label(composite, SWT.NONE);
		labeltime.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		labeltime.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		FormData fd_labeltime = new FormData();
		fd_labeltime.left = new FormAttachment(0, 8);
		labeltime.setLayoutData(fd_labeltime);
		labeltime.setText("入住时间：");
		
		dateTime = new DateTime(composite, SWT.BORDER);
		fd_labeltime.top = new FormAttachment(dateTime, 0, SWT.TOP);
		fd_labeltime.right = new FormAttachment(dateTime, -6);
		dateTime.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		dateTime.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_dateTime = new FormData();
		fd_dateTime.left = new FormAttachment(0, 104);
		dateTime.setLayoutData(fd_dateTime);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.left = new FormAttachment(labeltime, 0, SWT.LEFT);
		fd_lblNewLabel.right = new FormAttachment(labeltime, 0, SWT.RIGHT);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("退房时间：");
		
		dateTime_1 = new DateTime(composite, SWT.BORDER);
		fd_lblNewLabel.top = new FormAttachment(dateTime_1, 0, SWT.TOP);
		dateTime_1.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		dateTime_1.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_BLACK));
		FormData fd_dateTime_1 = new FormData();
		fd_dateTime_1.top = new FormAttachment(dateTime, 24);
		fd_dateTime_1.left = new FormAttachment(0, 104);
		dateTime_1.setLayoutData(fd_dateTime_1);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.left = new FormAttachment(0, 10);
		fd_lblNewLabel_1.top = new FormAttachment(0, 52);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText(" 客 户 名：");
		
		text = new Text(composite, SWT.BORDER);
		fd_lblNewLabel_1.right = new FormAttachment(text, -14);
		text.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		text.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text.setText("张三");
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(0, 260);
		fd_text.top = new FormAttachment(0, 49);
		fd_text.left = new FormAttachment(0, 104);
		text.setLayoutData(fd_text);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		FormData fd_lblNewLabel_2 = new FormData();
		fd_lblNewLabel_2.right = new FormAttachment(labeltime, 0, SWT.RIGHT);
		fd_lblNewLabel_2.left = new FormAttachment(0, 18);
		lblNewLabel_2.setLayoutData(fd_lblNewLabel_2);
		lblNewLabel_2.setText("性     别：");
		
		Label label_1 = new Label(composite, SWT.NONE);
		fd_lblNewLabel_2.top = new FormAttachment(label_1, 22);
		label_1.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		FormData fd_label_1 = new FormData();
		fd_label_1.left = new FormAttachment(0);
		label_1.setLayoutData(fd_label_1);
		label_1.setText(" 身份证号：");
		
		text_2 = new Text(composite, SWT.BORDER);
		fd_label_1.right = new FormAttachment(text_2, -14);
		fd_label_1.top = new FormAttachment(text_2, 1, SWT.TOP);
		text_2.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		text_2.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text_2.setText("14092619961120001");
		FormData fd_text_2 = new FormData();
		fd_text_2.left = new FormAttachment(0, 104);
		fd_text_2.top = new FormAttachment(text, 16);
		text_2.setLayoutData(fd_text_2);
		
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		FormData fd_label_2 = new FormData();
		fd_label_2.left = new FormAttachment(labeltime, -8, SWT.LEFT);
		fd_label_2.right = new FormAttachment(lblNewLabel_1, 0, SWT.RIGHT);
		label_2.setLayoutData(fd_label_2);
		label_2.setText(" 联系方式：");
		
		text_3 = new Text(composite, SWT.BORDER);
		fd_label_2.top = new FormAttachment(text_3, 1, SWT.TOP);
		text_3.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		text_3.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text_3.setText("1816944195");
		
		FormData fd_text_3 = new FormData();
		fd_text_3.right = new FormAttachment(0, 260);
		fd_text_3.top = new FormAttachment(0, 167);
		fd_text_3.left = new FormAttachment(0, 104);
		text_3.setLayoutData(fd_text_3);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		FormData fd_label_3 = new FormData();
		fd_label_3.top = new FormAttachment(label_2, 21);
		fd_label_3.left = new FormAttachment(0);
		label_3.setLayoutData(fd_label_3);
		label_3.setText("  房 间 号：");
		
		text_4 = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		fd_label_3.right = new FormAttachment(text_4, -14);
		text_4.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		text_4.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_BLACK));
		FormData fd_text_4 = new FormData();
		fd_text_4.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_text_4.left = new FormAttachment(0, 104);
		fd_text_4.top = new FormAttachment(0, 207);
		text_4.setLayoutData(fd_text_4);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		label_4.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_label_4 = new FormData();
		fd_label_4.top = new FormAttachment(label_3, 22);
		fd_label_4.right = new FormAttachment(lblNewLabel_1, -2, SWT.RIGHT);
		fd_label_4.left = new FormAttachment(labeltime, 0, SWT.LEFT);
		label_4.setLayoutData(fd_label_4);
		label_4.setText("  押   金：");
		
		text_5 = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		fd_dateTime.top = new FormAttachment(text_5, 22);
		text_5.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		text_5.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_BLACK));
		FormData fd_text_5 = new FormData();
		fd_text_5.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_text_5.left = new FormAttachment(label_4, 16);
		fd_text_5.top = new FormAttachment(0, 248);
		text_5.setLayoutData(fd_text_5);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnNewButton.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 15, SWT.NORMAL));
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.left = new FormAttachment(0, 52);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("入 住");
		
		Button button = new Button(composite, SWT.NONE);
		fd_btnNewButton.top = new FormAttachment(button, 0, SWT.TOP);
		fd_btnNewButton.right = new FormAttachment(button, -28);
		button.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 15, SWT.NORMAL));
		FormData fd_button = new FormData();
		fd_button.right = new FormAttachment(100, -38);
		fd_button.left = new FormAttachment(0, 170);
		fd_button.top = new FormAttachment(dateTime_1, 15);
		button.setLayoutData(fd_button);
		button.setText("预 定");
		
		Combo combo = new Combo(composite, SWT.NONE);
		combo.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		combo.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_BLACK));
		FormData fd_combo = new FormData();
		fd_combo.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_combo.left = new FormAttachment(0, 104);
		fd_combo.top = new FormAttachment(text_2, 18);
		combo.setLayoutData(fd_combo);
		combo.setItems(new String[] {"男", "女"});
		combo.setText("请选择性别:");
		
		composite_5 = new Composite(composite, SWT.NONE);
		fd_text_2.right = new FormAttachment(composite_5, -6);
		FormData fd_composite_5 = new FormData();
		fd_composite_5.top = new FormAttachment(0, 88);
		fd_composite_5.left = new FormAttachment(0, 266);
		composite_5.setLayoutData(fd_composite_5);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite_6 = new Composite(composite, SWT.NONE);
		fd_composite_5.bottom = new FormAttachment(composite_6, -48);
		fd_composite_5.right = new FormAttachment(composite_6, 0, SWT.RIGHT);
		
		Label label = new Label(composite_5, SWT.NONE);
		FormData fd_composite_6 = new FormData();
		fd_composite_6.bottom = new FormAttachment(0, 189);
		fd_composite_6.right = new FormAttachment(0, 291);
		fd_composite_6.top = new FormAttachment(0, 163);
		fd_composite_6.left = new FormAttachment(0, 266);
		composite_6.setLayoutData(fd_composite_6);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		label_5 = new Label(composite_6, SWT.NONE);
		
		composite_1 = new Composite(sashForm, SWT.BORDER);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		tabFolder = new TabFolder(composite_1, SWT.NONE);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("单人间");
		
		composite_dan = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_dan);
		roomInit(composite_dan,"单人间");
		composite_dan.setLayout(null);
		
		TabItem tabItem_1 = new TabItem(tabFolder, 0);
		tabItem_1.setText("双人间");
		
		Composite composite_shuang = new Composite(tabFolder, SWT.NONE);
		tabItem_1.setControl(composite_shuang);
		roomInit(composite_shuang,"双人间");
		
		TabItem tabItem_2 = new TabItem(tabFolder, 0);
		tabItem_2.setText("三人间");
		
		Composite composite_san = new Composite(tabFolder, SWT.NONE);
		tabItem_2.setControl(composite_san);
		roomInit(composite_san,"三人间");
		
		TabItem tabItem_3 = new TabItem(tabFolder, 0);
		tabItem_3.setText("商务间");
		
		Composite composite_shang = new Composite(tabFolder, SWT.NONE);
		tabItem_3.setControl(composite_shang);
		
		TabItem tabItem_4 = new TabItem(tabFolder, 0);
		tabItem_4.setText("豪华间");
		
		Composite composite_hao = new Composite(tabFolder, SWT.NONE);
		tabItem_4.setControl(composite_hao);
		roomInit(composite_hao,"豪华间");
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("套间");
		
		Composite composite_tao = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_tao);
		roomInit(composite_tao,"套间");
		

		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.top = new FormAttachment(btnNewButton, 51);
		fd_btnNewButton_1.left = new FormAttachment(btnNewButton, 0, SWT.LEFT);
		//手机号判断
		label_5.setImage(SWTResourceManager.getImage(MainUi.class, "/images/gou.png"));
		composite_6.setVisible(false);
		text_3.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String idnum = text_3.getText().trim();
				Pattern p = Pattern.compile("^((18[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
				Matcher m = p.matcher(idnum);
				if (m.matches() == false) {
					composite_6.setVisible(false);
				} else {
					composite_6.setVisible(true);
					composite_6.layout();
				}
			}
		});
		//身份证判断
		label.setImage(SWTResourceManager.getImage(MainUi.class, "/images/gou.png"));
		composite_5.setVisible(false);
		
		button_1 = new Button(composite, SWT.NONE);
		button_1.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 15, SWT.NORMAL));
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				roomInit(composite_dan,"单人间");
				roomInit(composite_dan,"单人间");
				roomInit(composite_shuang,"双人间");
				roomInit(composite_san,"三人间");
				roomInit(composite_shang,"商务间");
				roomInit(composite_hao,"豪华间");
				roomInit(composite_tao,"套间");
			}
		});
		FormData fd_button_1 = new FormData();
		fd_button_1.bottom = new FormAttachment(100, -10);
		fd_button_1.left = new FormAttachment(button, 0, SWT.LEFT);
		fd_button_1.right = new FormAttachment(100, -38);
		button_1.setLayoutData(fd_button_1);
		button_1.setText("刷 新");
		
		Combo combo_1 = new Combo(composite, SWT.NONE);
		fd_dateTime.right = new FormAttachment(combo_1, -6);
		combo_1.setItems(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"});
		FormData fd_combo_1 = new FormData();
		fd_combo_1.top = new FormAttachment(dateTime, 0, SWT.TOP);
		fd_combo_1.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_combo_1.left = new FormAttachment(0, 210);
		combo_1.setLayoutData(fd_combo_1);
		
		Combo combo_2 = new Combo(composite, SWT.NONE);
		fd_dateTime_1.right = new FormAttachment(combo_2, -6);
		combo_2.setItems(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"});
		FormData fd_combo_2 = new FormData();
		fd_combo_2.top = new FormAttachment(combo_1, 22);
		fd_combo_2.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_combo_2.left = new FormAttachment(0, 210);
		combo_1.setText("12");
		combo_2.setLayoutData(fd_combo_2);
		combo_2.setText("12");
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setLayout(null);
		
		Composite composite_4 = new Composite(composite_3, SWT.NONE);
		composite_4.setBounds(10, 121, 64, 282);
		
		label_10 = new Label(composite_4, SWT.WRAP);
		label_10.setBounds(0, 0, 64, 282);
		label_10.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		label_10.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 17, SWT.NORMAL));
		//滚动字幕
		int y=label_10.getLocation().y;
		
		Label label_7 = new Label(composite_3, SWT.NONE);
		label_7.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_RED));
		label_7.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 15, SWT.NORMAL));
		label_7.setBounds(0, 90, 83, 21);
		label_7.setText("超时房间");
		
		label_6 = new Label(composite_3, SWT.NONE);
		label_6.setBounds(10, 20, 73, 64);
		label_6.setImage(SWTResourceManager.getImage("src\\images\\laba.png"));
		sashForm.setWeights(new int[] {305, 799, 90});
		text_2.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String idnum = text_2.getText().trim();
				if(idnum.length()==18) {
					Pattern p = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
					Matcher m = p.matcher(idnum);
					if (m.matches() == false) {
						SwtUtils.showMessageBox(shell, "格式错误", "身份证不符合指定格式");
						composite_5.setVisible(false);
					} else {
						String sql = "select * from custermer";
						DBHelper db = new DBHelper();
						String l = "";
						try {
							List<Map<String,String>> list = db.select(sql, null);
							if (list != null && list.size() > 0) {
								for (Map<String, String> data : list) {
									String idnum1 = data.get("IDNUM");
									if(idnum.equals(idnum1)) {		
										SwtUtils.showMessageBox(shell, "错误入住", "此身份证已被使用");
										l+="0";
									} else {
										l+="1";
									}
								}
								if(l.contains("0")) {
									composite_5.setVisible(false);
								} else {
									composite_5.setVisible(true);
								}
							} 
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				} else if(idnum.length() < 18){
					composite_5.setVisible(false);
					composite_5.layout();
				} else {
					SwtUtils.showMessageBox(shell, "格式错误", "身份证超出指定长度");
				}
			}
		});
		
		//单人入住
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String cname=text.getText();//姓名
				if(cname == null && "".equals("cname")) {
					SwtUtils.showMessageBox(shell, "入住失败", "姓名不能为空");
					return;
				}
				String gender=combo.getText();//性别
				if(gender.equals("请选择性别:")) {
					SwtUtils.showMessageBox(shell, "入住失败", "请选择性别");
					return;
				}
				String idnum=text_2.getText();//身份证号
				if(composite_5.getVisible() == false) {
					composite_5.setVisible(false);
					SwtUtils.showMessageBox(shell, "入住失败", "身份证格式错误");
					return;
				}
				String tel=text_3.getText();//联系方式
				if(tel == null && "".equals("tel")) {
					SwtUtils.showMessageBox(shell, "入住失败", "手机号不能为空");
					return;
				}
				rid=text_4.getText();//房间号
				if(rid == null && "".equals("rid")) {
					SwtUtils.showMessageBox(shell, "入住失败", "请选择要入住的房间");
					return;
				}
				//房间号判断
				if(rid.length()==4) {
					String sql = "select * from room where rid = ?";
					List<Object> params = new ArrayList<Object>();
					params.add(rid);
					DBHelper db = new DBHelper();
					try {
						List<Map<String,String>> list = db.select(sql, params);
						if (list != null && list.size() > 0) {
							for (Map<String, String> data : list) {
								String roomid1 = data.get("RSTATE");
								if(roomid1.equals("入住")) {
									SwtUtils.showMessageBox(shell, "错误入住", "此房间已有住户，不可入住");
									return;
								} else if(roomid1.equals("预订")) {
									SwtUtils.showMessageBox(shell, "错误入住", "此房间已被预订，不可入住");
									return;
								} else if(roomid1.equals("未整理")) {
									SwtUtils.showMessageBox(shell, "错误入住", "此房间未整理，不可入住");
									return;
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					text_5.setData(rid);
				} else {
					SwtUtils.showMessageBox(shell, "房间错误", "不存在的房间");
					return;
				}
				String money=text_5.getText();//押金
				String time = combo_1.getText().trim();
				String time1 = combo_2.getText().trim();
				int year=dateTime.getYear()-1900;//3890-1900
				int month=dateTime.getMonth();
				int day=dateTime.getDay();	
				Date in=new Date(year,month,day,Integer.parseInt(time),00,00);//入住时间 
				int year1=dateTime_1.getYear()-1900;//3890-1900
				int month1=dateTime_1.getMonth();
				int day1=dateTime_1.getDay();	
				Date out=new Date(year1,month1,day1,Integer.parseInt(time1),00,00);//退房时间
				int hoursBetweem = 0;
				try {
					hoursBetweem = DateUtils.hoursBetween(in, out);
					moneys = String.valueOf(TimeBiz.getMoney(hoursBetweem, money));
					if(hoursBetweem<=0) {
						SwtUtils.showMessageBox(shell, "错误入住", "入住天数不可少于一小时");
						return;
					}
				} catch (ParseException e2) {
					e2.printStackTrace();
				}	
				try {
					Date d = new Date();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
					Date dd=sdf.parse(sdf.format(d));  
					if(DateUtils.hoursBetween(dd, in)<0) {
						SwtUtils.showMessageBox(shell, "错误入住", "入住时间不得小于当前时间");
						return;
					}
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				HotelBiz hb=new HotelBiz();	
				if(cname==null||"".equals(cname)){
					SwtUtils.showMessageBox(shell, "入住错误", "客户姓名不能为空");
					return;
				}				
				try {
					int result=hb.addInformation(cname,gender,idnum,tel,rid,moneys,in,out);
					if(result>0){	
						String sql = "update room set rstate = '入住' where rid = ?";
						List<Object> params = new ArrayList<Object>();
						params.add(rid);
						db = new DBHelper();
						int r = db.doUpdate(sql, params);
						if(r>0) {							
							MessageDialog.openConfirm(shell, "入住成功", "退房时需结账:"+moneys+"元");
							composite_5.setVisible(false);composite_6.setVisible(false);
							if(rid.contains("单")) {
							roomInit(composite_dan,"单人间");
						    } else if(rid.contains("双")) {
							roomInit(composite_shuang,"双人间");
						    } else if(rid.contains("三")) {
							roomInit(composite_san,"三人间");
						    } else if(rid.contains("商")) {
							roomInit(composite_shang,"商务间");
						    } else if(rid.contains("豪")) {
							roomInit(composite_hao,"豪华间");
						    } else if(rid.contains("套")) {
							roomInit(composite_tao,"套间");
						    }
						}
					}
					else{
						SwtUtils.showMessageBox(shell, "失败", "客户入住失败");
						}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String cname=text.getText();//姓名
				if(cname == null && "".equals("cname")) {
					SwtUtils.showMessageBox(shell, "入住失败", "姓名不能为空");
					return;
				}
				String gender=combo.getText();//性别
				if(gender.equals("请选择性别:")) {
					SwtUtils.showMessageBox(shell, "入住失败", "请选择性别");
					return;
				}
				String idnum=text_2.getText();//身份证号
				if(composite_5.getVisible() == false) {
					SwtUtils.showMessageBox(shell, "入住失败", "身份证格式错误");
					composite_5.setVisible(false);
					return;
				}
				String tel=text_3.getText();//联系方式
				if(tel == null && "".equals("tel")) {
					SwtUtils.showMessageBox(shell, "入住失败", "手机号不能为空");
					return;
				}
				rid=text_4.getText();//房间号
				if(rid == null && "".equals("rid")) {
					SwtUtils.showMessageBox(shell, "入住失败", "请选择要入住的房间");
					return;
				}
				//房间号判断
				if(rid.length()==4) {
					String sql = "select * from room where rid = ?";
					List<Object> params = new ArrayList<Object>();
					params.add(rid);
					DBHelper db = new DBHelper();
					try {
						List<Map<String,String>> list = db.select(sql, params);
						if (list != null && list.size() > 0) {
							for (Map<String, String> data : list) {
								String roomid1 = data.get("RSTATE");
								if(roomid1.equals("入住")) {
									SwtUtils.showMessageBox(shell, "错误入住", "此房间已有住户，不可入住");
									return;
								} else if(roomid1.equals("预订")) {
									SwtUtils.showMessageBox(shell, "错误入住", "此房间已被预订，不可入住");
									return;
								} else if(roomid1.equals("未整理")) {
									SwtUtils.showMessageBox(shell, "错误入住", "此房间未整理，不可入住");
									return;
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					text_5.setData(rid);
				} else {
					SwtUtils.showMessageBox(shell, "房间错误", "不存在的房间");
					return;
				}
				String money=text_5.getText();//押金
				String time = combo_1.getText().trim();
				String time1 = combo_2.getText().trim();
				int year=dateTime.getYear()-1900;//3890-1900
				int month=dateTime.getMonth();
				int day=dateTime.getDay();	
				Date in=new Date(year,month,day,Integer.parseInt(time),00,00);//入住时间 
				int year1=dateTime_1.getYear()-1900;//3890-1900
				int month1=dateTime_1.getMonth();
				int day1=dateTime_1.getDay();	
				Date out=new Date(year1,month1,day1,Integer.parseInt(time1),00,00);//退房时间
				int hoursBetweem = 0;
				try {
					hoursBetweem = DateUtils.hoursBetween(in, out);
					moneys = String.valueOf(TimeBiz.getMoney(hoursBetweem, money));
					if(hoursBetweem<=0) {
						SwtUtils.showMessageBox(shell, "错误入住", "入住天数不可少于一小时");
						return;
					}    
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				try {
					Date d = new Date();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
					Date dd=sdf.parse(sdf.format(d));  
					System.out.println(DateUtils.hoursBetween(dd, in));
					if(DateUtils.hoursBetween(dd, in)<0) {
						SwtUtils.showMessageBox(shell, "错误入住", "入住时间不得小于当前时间");
						return;
					}
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				HotelBiz hb=new HotelBiz();	
				if(cname==null||"".equals(cname)){
					SwtUtils.showMessageBox(shell, "入住错误", "客户姓名不能为空");
					return;
				}				
				try {
					int result=hb.addInformation(cname,gender,idnum,tel,rid,moneys,in,out);
					if(result>0){	
						String sql = "update room set rstate = '预订' where rid = ?";
						List<Object> params = new ArrayList<Object>();
						params.add(rid);
						db = new DBHelper();
						int r = db.doUpdate(sql, params);
						if(r>0) {							
							MessageDialog.openConfirm(shell, "入住成功", "退房时需结账:"+moneys+"元");
							composite_5.setVisible(false);composite_6.setVisible(false);
							if(rid.contains("单")) {
							roomInit(composite_dan,"单人间");
						    } else if(rid.contains("双")) {
							roomInit(composite_shuang,"双人间");
						    } else if(rid.contains("三")) {
							roomInit(composite_san,"三人间");
						    } else if(rid.contains("商")) {
							roomInit(composite_shang,"商务间");
						    } else if(rid.contains("豪")) {
							roomInit(composite_hao,"豪华间");
						    } else if(rid.contains("套")) {
							roomInit(composite_hao,"套间");
						    }
						}
					}
					else{
						SwtUtils.showMessageBox(shell, "失败", "客户入住失败");
						}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//label_10.setText("单101单102单104单105单106单201");
		Timer timer=new Timer(true);
		String sql = "select rid,to_char(checkouttime,'yyyy-MM-dd HH24:mi') as checkouttime from checkin ";
		DBHelper db = new DBHelper();
		
		class myTask1 extends TimerTask{
		@Override
		public void run() {
			try {
				List<Map<String,String>> list = db.select(sql, null);
				if(list!=null&&list.size()>0) {
					s="";
					for(Map<String,String> map:list) {
						String date = map.get("CHECKOUTTIME");
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
						Date d = new Date();
						String dates = sdf.format(d);
						try {
							int time = DateUtils.hoursBetween(dates, date); 
							if(time<=0) {
								s+=map.get("RID");
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							label_10.setText(s);
						}
					});
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  }
		}
		timer.schedule(new myTask1(), 0,10000);
		Timer timer1=new Timer(true);
		class myTask extends TimerTask{
		@Override
		public void run() {
				// TODO 自动生成方法存根
				shell.getDisplay().syncExec(new Runnable(){
				public void run(){
				int mx=label_10.getLocation().x;
				int my=label_10.getLocation().y;
				label_10.setLocation(mx, my-1);
				if(my==-150) {
					label_10.setLocation(mx,my+500);
				}
				}
			});
		  }
		}
		timer1.schedule(new myTask(), 0,15);
	}
	
	//房间初始化
	public void roomInit(Composite composite,String type) {
		Control[] cons=composite.getChildren();
		for(  Control c:cons){
			c.dispose();
		}
		int row = 1;
		int clo = 1;
		int count = 150;
		List<Object> params = new ArrayList<Object>();
		String sql = "select r.* from room r,roomtype rt where r.rtid = rt.rtid and rt.rtname = ? order by r.rid ";
		params.add(type);
		db = new DBHelper();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try {
			list = db.select(sql, params);
			if (list != null && list.size() > 0) {
				for (Map<String, String> date : list) {
					String rstate = date.get("RSTATE");
					String rid = date.get("RID");
					Label label = new Label(composite, SWT.NONE);
					label.setBounds((row-1)*count+60, (clo-1)*count+10, 105, 120);
					label.setData(rid);
					String roomId = (String) label.getData();
					if("空闲".equals(rstate)) {
						label.setImage(SWTResourceManager.getImage(MainUi.class, "/images/fang_3.png"));
					}
					if("入住".equals(rstate)) {
						label.setImage(SWTResourceManager.getImage(MainUi.class, "/images/fang_2.png"));
					}
					if("预订".equals(rstate)) {
						label.setImage(SWTResourceManager.getImage(MainUi.class, "/images/fang_1.png"));
					}
					if("未整理".equals(rstate)) {
						label.setImage(SWTResourceManager.getImage(MainUi.class, "/images/fang_4.png"));
					}
					label.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDoubleClick(MouseEvent e) {
							//System.out.println(label.getData());
							RoomInfoDialog ri = new RoomInfoDialog(roomId,shell,SWT.NONE);
							ri.open();
						}
					});
					label.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent e) {
							if(e.button == 1) {
								text_4.setText((String) label.getData());
								String rid = text_4.getText().trim();
								if(rid.contains("单")) {
									text_5.setText(200+"");
								} else if(rid.contains("双")) {
									text_5.setText(250+"");
								} else if(rid.contains("三")) {
									text_5.setText(300+"");
								} else if(rid.contains("商")) {
									text_5.setText(400+"");
								} else if(rid.contains("豪")) {
									text_5.setText(500+"");
								} else if(rid.contains("套")) {
									text_5.setText(600+"");
								}
							} else if(e.button == 3) {
								ChooseDialog chd = new ChooseDialog(roomId,shell,SWT.NONE);
								chd.open();
							}
						}
					});
					row++;
					if((row%5)==1) {
						row = 1;
						clo++;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
