package com.dialog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.wb.swt.SWTResourceManager;

import com.biz.HotelBiz;
import com.dao.DBHelper;
import com.ui.MainUi;
import com.utils.ImgSizeUtil;
import com.utils.LayoutUtil;
import com.utils.SwtUtils;

public class RoomInfoDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private String roomId;
	private Label label;
    private Label label_4;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public RoomInfoDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}
	public RoomInfoDialog(String roomId,Shell parent, int style) {
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
		shell.setSize(536, 442);
		shell.setText("房间信息");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		Image image = SWTResourceManager.getImage(MainUi.class, "/images/roominfo.png");
		ImgSizeUtil isu = new ImgSizeUtil();
		isu.imgSize(image, shell);
		
		Composite composite = new Composite(shell, SWT.NONE);
		
		label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.NORMAL));
		label.setBounds(225, 53, 112, 38);
		label.setText(roomId);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_1.setBounds(53, 148, 84, 17);
		label_1.setText("房间类型:");
		
		text = new Text(composite, SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		text.setText("");
		text.setBounds(143, 147, 120, 27);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(53, 208, 84, 17);
		label_2.setText("房间价格:");
		
		text_1 = new Text(composite, SWT.READ_ONLY);
		text_1.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		text_1.setBounds(143, 207, 120, 27);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setBounds(53, 257, 84, 17);
		label_3.setText("房间位置:");
		
		text_2 = new Text(composite, SWT.READ_ONLY);
		text_2.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		text_2.setBounds(143, 256, 120, 27);
		
		label_4 = new Label(composite, SWT.BORDER);
		label_4.setBounds(304, 148, 185, 182);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(53, 310, 84, 17);
		label_5.setText("房间状态:");
		
		text_3 = new Text(composite, SWT.READ_ONLY);
		text_3.setFont(SWTResourceManager.getFont("华文新魏", 11, SWT.NORMAL));
		text_3.setBounds(143, 309, 120, 27);
		
		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rstate = text_3.getText().trim();
				if("入住".equals(rstate) || "预订".equals(rstate)) {
				  CustemerDialog cd = new CustemerDialog(roomId,shell,SWT.NONE);
				  cd.open();
				} else {
					SwtUtils.showMessageBox(shell, "查看信息出错", "房屋空闲,暂无客户");
					return;
				}
			}
		});
		button.setBounds(225, 367, 120, 27);
		button.setText("查看住户信息");
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setImage(SWTResourceManager.getImage(RoomInfoDialog.class, "/images/wrong.png"));
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		label_6.setBounds(492, 0, 38, 34);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("华文新魏", 18, SWT.NORMAL));
		label_7.setText("房间信息");
		label_7.setBounds(0, 0, 104, 27);

		infoInit();
	}
	public void infoInit() {
		String roomId = label.getText().trim();
		String sql = "select rt.rtname,rt.rprice,r.locate,r.rstate from roomtype rt,room r where rt.rtid = r.rtid and r.rid = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(roomId);
		DBHelper db = new DBHelper();
		try {
			List<Map<String,String>> list = db.select(sql, params);
			if(list != null && list.size() > 0) {
				for(Map<String,String> map:list) {
					text.setText(map.get("RTNAME"));
					text_1.setText(map.get("RPRICE"));
					text_2.setText(map.get("LOCATE"));
					text_3.setText(map.get("RSTATE"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}try {
			HotelBiz hb = new HotelBiz();
			ImageData imageData=hb.getHeadByRid(roomId);
			ImageData newid=imageData.scaledTo(this.label_4.getBounds().width,this.label_4.getBounds().height);
			Image image=new Image(shell.getDisplay(),newid);
			this.label_4.setImage(image);
		} catch (SQLException e1) {
			e1.printStackTrace();
			}
	}
}
