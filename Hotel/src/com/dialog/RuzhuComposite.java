package com.dialog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;

import com.dao.DBHelper;
import com.ui.MainUi;
import com.utils.ImgSizeUtil;

public class RuzhuComposite extends Composite {
	public Text text_3;
	public Text text_4;
	public Text text_5;
	public Text text_6;
	private ImgSizeUtil isu = new ImgSizeUtil();
	private String rid;
	private Label lblNewLabel;
	public Label label;
	public Label lblNewLabel_1;
	public Label label_1;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RuzhuComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(null);
		
		
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("华文新魏", 16, SWT.NORMAL));
		lblNewLabel.setBounds(286, 10, 105, 30);
		lblNewLabel.setText("客户信息");
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setBounds(57, 320, 61, 17);
		label_3.setText("联系方式：");
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(138, 317, 149, 23);
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setBounds(57, 361, 61, 17);
		label_4.setText("订单号：");
		
		text_4 = new Text(this, SWT.BORDER);
		text_4.setBounds(138, 358, 149, 23);
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setBounds(342, 361, 61, 17);
		label_5.setText("押    金：");
		
		text_5 = new Text(this, SWT.BORDER);
		text_5.setBounds(423, 358, 149, 23);
		
		Label label_6 = new Label(this, SWT.NONE);
		label_6.setBounds(342, 320, 61, 17);
		label_6.setText("入住时间：");
		
		text_6 = new Text(this, SWT.BORDER);
		text_6.setBounds(423, 317, 149, 23);
		
		Group group = new Group(this, SWT.BORDER);
		group.setBounds(92, 58, 452, 241);
		Image img =SWTResourceManager.getImage(MainUi.class, "/images/idcard.jpg");
		isu.imgSize(img,group);
		
		lblNewLabel_1 = new Label(group, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("华文楷体", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(80, 28, 70, 23);
		
		label = new Label(group, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("华文楷体", 11, SWT.NORMAL));
		label.setBounds(80, 60, 29, 23);
		
		label_1 = new Label(group, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("华文楷体", 12, SWT.NORMAL));
		label_1.setBounds(165, 206, 178, 23);

	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
