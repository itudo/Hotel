package com.dialog;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;

import com.dao.DBHelper;
import com.ui.MainUi;
import com.utils.DateUtils;
import com.utils.ImgSizeUtil;
import com.utils.LayoutUtil;

public class CustemerDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Label label;
	private String roomId;
	private Date d;
	private SimpleDateFormat sdf;
	private Label label_10;

	public CustemerDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CustemerDialog(String roomId,Shell parent, int style) {
		super(parent, style);
		this.roomId = roomId;
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		LayoutUtil.centerShell(shell.getDisplay(), shell);
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(535, 442);
		shell.setText(getText());
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		Image image = SWTResourceManager.getImage(MainUi.class, "/images/roominfo2.png");
		ImgSizeUtil isu = new ImgSizeUtil();
		isu.imgSize(image, shell);

		
		Composite composite = new Composite(shell, SWT.NONE);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		label_1.setImage(SWTResourceManager.getImage(CustemerDialog.class, "/images/wrong.png"));
		label_1.setBounds(491, 0, 38, 34);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setText("客户信息");
		label_2.setFont(SWTResourceManager.getFont("华文新魏", 18, SWT.NORMAL));
		label_2.setBounds(0, 0, 109, 28);
		
		label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.NORMAL));
		label.setBounds(220, 58, 112, 38);
		//label.setText(roomId);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_3.setBounds(35, 128, 74, 17);
		label_3.setText("客户编号:");
		
		text = new Text(composite, SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		text.setBounds(115, 127, 112, 28);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_4.setBounds(59, 182, 38, 17);
		label_4.setText("性别:");
		
		text_1 = new Text(composite, SWT.READ_ONLY);
		text_1.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		text_1.setBounds(115, 182, 112, 28);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_5.setBounds(46, 229, 63, 17);
		label_5.setText("手机号:");
		
		text_2 = new Text(composite, SWT.READ_ONLY);
		text_2.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		text_2.setBounds(115, 229, 112, 28);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_6.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_6.setBounds(233, 183, 76, 17);
		label_6.setText("入住时间:");
		
		text_3 = new Text(composite, SWT.READ_ONLY);
		text_3.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		text_3.setBounds(315, 182, 170, 28);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_7.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_7.setBounds(233, 230, 76, 17);
		label_7.setText("退房时间:");
		
		text_4 = new Text(composite, SWT.READ_ONLY);
		text_4.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		text_4.setBounds(320, 229, 165, 28);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_8.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_8.setBounds(233, 128, 76, 17);
		label_8.setText("身份证号:");
		
		text_5 = new Text(composite, SWT.READ_ONLY);
		text_5.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		text_5.setBounds(320, 127, 165, 28);
		
		Group group = new Group(composite, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		group.setText("退房倒计时:");
		group.setBounds(60, 292, 361, 84);
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_9.setFont(SWTResourceManager.getFont("华文新魏", 27, SWT.NORMAL));
		label_9.setText("还 有");
		label_9.setBounds(66, 33, 90, 38);
		
		Label label_11 = new Label(group, SWT.NONE);
		label_11.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_11.setFont(SWTResourceManager.getFont("华文新魏", 27, SWT.NORMAL));
		label_11.setText("小时");
		label_11.setBounds(235, 33, 81, 38);
		
		label_10 = new Label(group, SWT.CENTER);
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_10.setFont(SWTResourceManager.getFont("Segoe Script", 26, SWT.NORMAL));
		label_10.setBounds(152, 28, 81, 46);
		
		infoInit();
		
	}
	public void infoInit() {
		String sql = "select cu.cid,cu.idnum,cu.cname,cu.gender,cu.tel,ch.orderid,to_char(ch.checkintime,'yyyy-MM-dd HH24:mi') as checkintime,to_char(ch.checkouttime,'yyyy-MM-dd HH24:mi') as checkouttime,ch.money from custermer cu,checkin ch where cu.idnum=ch.idnum and ch.rid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(roomId);
		DBHelper db = new DBHelper();
		try {
			List<Map<String,String>> list = db.select(sql, params);
			if(list != null && list.size() > 0) {
				for(Map<String,String> map:list) {
					text.setText(map.get("CID"));
					text_1.setText(map.get("GENDER"));
					text_2.setText(map.get("TEL"));
					text_3.setText(map.get("CHECKINTIME"));
					text_4.setText(map.get("CHECKOUTTIME"));
					text_5.setText(map.get("IDNUM"));
					label.setText(map.get("CNAME"));
					try {
						Date d = new Date();
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
				        String sysdate = sdf.format(d);
						int days = DateUtils.hoursBetween(sysdate, map.get("CHECKOUTTIME"));
						label_10.setText(String.valueOf(days));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
