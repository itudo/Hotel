package com.dialog;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import com.biz.HotelBiz;
import com.dao.DBHelper;
import com.utils.SwtUtils;

public class FreeComposite extends Composite {
	public Text text;
	public Text text_3;
	public Text text_1;
	public Combo combo_1;
	public Combo combo_2;
	public Label label_3;
	public Label label_2;
	private Shell shell;
	public Text text_2;
	private String ridd;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public FreeComposite(Composite parent, int style) {
		super(parent, style);
		shell=parent.getShell();
		setLayout(null);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		lblNewLabel.setBounds(23, 52, 61, 17);
		lblNewLabel.setText("房间号：");
		
		text = new Text(this, SWT.BORDER);
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text.setBounds(90, 49, 145, 23);
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(10, 104, 74, 17);
		lblNewLabel_1.setText("房间类型：");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		lblNewLabel_2.setBounds(10, 157, 74, 17);
		lblNewLabel_2.setText("房间状态：");
		
		Label label = new Label(this, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		label.setBounds(10, 208, 74, 17);
		label.setText("房间价格：");
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("华文新魏", 10, SWT.NORMAL));
		text_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text_3.setText("100");
		text_3.setBounds(90, 207, 145, 23);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		label_1.setBounds(10, 261, 74, 17);
		label_1.setText("房间位置：");
		
		combo_1 = new Combo(this, SWT.READ_ONLY);
		combo_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		combo_1.setFont(SWTResourceManager.getFont("华文新魏", 10, SWT.NORMAL));
		combo_1.addModifyListener(new ModifyListener() {
			//根据房间类型来定价格
			public void modifyText(ModifyEvent arg0) {
				String rt=combo_1.getText().trim();
				ridd = "";
				if(rt.equals("单人间")){
					text_3.setText("200");
					ridd+="单";
				}else if(rt.equals("双人间")){
					text_3.setText("250");
					ridd+="双";
				}else if(rt.equals("三人间")){
					text_3.setText("300");
					ridd+="三";
				}else if(rt.equals("商务间")){
					text_3.setText("400");
					ridd+="商";
				}else if(rt.equals("豪华间")){
					text_3.setText("500");
					ridd+="豪";
				}else if(rt.equals("套间")){
					text_3.setText("600");
					ridd+="套";
				}
				text.setText(ridd);
			}
		});
		combo_1.setItems(new String[] {"单人间", "双人间", "三人间", "商务间", "豪华间", "套间"});
		combo_1.setBounds(90, 101, 145, 25);
		combo_1.select(0);
		
		combo_2 = new Combo(this, SWT.READ_ONLY);
		combo_2.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String floor = combo_2.getText().trim();
				String rid = ridd;
				if(floor.equals("一楼")) {
					rid+="10";
				}
				if(floor.equals("二楼")) {
					rid+="20";
				}
				if(floor.equals("三楼")) {
					rid+="30";
				}
				if(floor.equals("四楼")) {
					rid+="40";
				}
				if(floor.equals("五楼")) {
					rid+="50";
				}
				text.setText(rid);
			}
		});
		combo_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		combo_2.setFont(SWTResourceManager.getFont("华文新魏", 10, SWT.NORMAL));
		combo_2.setItems(new String[] {"一楼", "二楼", "三楼"});
		combo_2.setBounds(90, 258, 145, 25);
		combo_2.select(0);
		
		label_2 = new Label(this, SWT.BORDER);
		label_2.setBounds(289, 20, 313, 220);
		
		label_3 = new Label(this, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		label_3.setBounds(275, 261, 75, 17);
		label_3.setText("房间图片：");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(376, 258, 166, 23);
		
		Button button = new Button(this, SWT.NONE);
		button.setForeground(SWTResourceManager.getColor(0, 0, 0));
		button.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		
		//浏览
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//打开窗口
				FileDialog dialog = new FileDialog(shell,SWT.OPEN);   
				dialog.setFilterPath("c:\\");//设置初始路径   
				//过滤
				dialog.setFilterExtensions(new String[]{"*.*","*.jpg","*.png*"});   
				String fileName = dialog.open();//返回的全路径(路径+文件名)
				
				if(fileName==null||"".equals(fileName)){
					return;
				}
				text_1.setText(fileName);
				//以流来读取图片
				try {
					BufferedInputStream bis=new BufferedInputStream(new FileInputStream(new File(fileName)));
					ImageData imageData=new ImageData(bis);//读到图片原大小
					//转换图片的大小  scaledTo等比例缩小
					ImageData newimageData=imageData.scaledTo(label_2.getBounds().width, label_2.getBounds().height);
					Image image=new Image(shell.getDisplay(),newimageData);
					label_2.setImage(image);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(548, 256, 80, 27);
		button.setText("浏览");
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		
		btnNewButton.setBounds(135, 320, 80, 27);
		btnNewButton.setText("添 加");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		
		button_1.setBounds(386, 320, 80, 27);
		button_1.setText("更 新");
		
		text_2 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text_2.setFont(SWTResourceManager.getFont("华文新魏", 10, SWT.NORMAL));
		text_2.setText("空闲");
		text_2.setBounds(90, 157, 145, 23);
	
		
		//更新
		
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rid=text.getText().trim();
				String rtname=combo_1.getText().trim();
				String rstate=text_2.getText().trim();
				String rprice=text_3.getText().trim();
				String locate=combo_2.getText().trim();
				String picture=text_1.getText().trim();
				String rtid="";
				if(!"".equals(rtname)){
				if(rtname.equals("单人间")){
						rtid="1001";
					}else if(rtname.equals("双人间")){
						rtid="1002";
					}else if(rtname.equals("三人间")){
						rtid="1003";
						
					}else if(rtname.equals("商务间")){
						rtid="1004";
					}else if(rtname.equals("豪华间")){
						rtid="1005";
					}else if(rtname.equals("套间")){
						rtid="1006";
					}
				
				}
				HotelBiz hb=new HotelBiz();
				if((rid==null||"".equals(rid)) || (rtname==null||"".equals(rtname)) || (rstate==null||"".equals(rstate)) || (rprice==null||"".equals(rprice)) || (locate==null||"".equals(locate))){
					SwtUtils.showMessageBox(shell, "失败", "信息不能为空");
					return;
				}
			try {
				int result=hb.updateRoom(rid,rtid,rstate, locate, picture);
				if(result>0){
					SwtUtils.showMessageBox(shell, "成功", "更新房间成功");
					
				}
				else{
					SwtUtils.showMessageBox(shell, "失败", "更新房间失败");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
				
			}
		});
		//添加
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rid=text.getText().trim();
				String rtname=combo_1.getText().trim();
				String rstate=text_2.getText().trim();
				String rprice=text_3.getText().trim();
				String locate=combo_2.getText().trim();
				String picture=text_1.getText().trim();
				String rtid="";
				if(!"".equals(rtname)){
				if(rtname.equals("单人间")){
						rtid="1001";
					}else if(rtname.equals("双人间")){
						rtid="1002";
					}else if(rtname.equals("三人间")){
						rtid="1003";
					}else if(rtname.equals("商务间")){
						rtid="1004";
					}else if(rtname.equals("豪华间")){
						rtid="1005";
					}else if(rtname.equals("套间")){
						rtid="1006";
					}
				
				}
				HotelBiz hb=new HotelBiz();
				if((rid==null||"".equals(rid)) || (rtname==null||"".equals(rtname)) || (rstate==null||"".equals(rstate)) || (rprice==null||"".equals(rprice)) || (locate==null||"".equals(locate)) || (picture==null||"".equals(picture)) || (rtid==null||"".equals(rtid))  ){
					SwtUtils.showMessageBox(shell, "添加房间失败", "信息不能为空");
					return;
				}
				int k = Integer.parseInt(rid.substring(rid.length()-1,rid.length()));
				if(k>5) {
					SwtUtils.showMessageBox(shell, "添加房间失败", "房间名称不符合格式");
					return;
				}else if(rid.length()!=4) {
					SwtUtils.showMessageBox(shell, "添加房间失败", "房间名称不符合格式");
					return;
				}
				String sql = "select rid from room";
				DBHelper db = new DBHelper();
				try {
					List<Map<String,String>> list = db.select(sql, null);
					for(Map<String,String> map:list) {
						String ss = map.get("RID");
						if(ss.equals(rid)) {
							SwtUtils.showMessageBox(shell, "添加房间失败", "房间已存在");
							return;
						}
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			try {
				int result=hb.addRoom(rid, rtid,rstate, locate, picture);
				if(result>0){
					SwtUtils.showMessageBox(shell, "添加房间成功", "添加房间成功");
					
				}
				else{
					SwtUtils.showMessageBox(shell, "添加房间失败", "添加房间失败");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
