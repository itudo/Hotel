package com.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Combo;

import com.utils.ImgSizeUtil;

public class SystemSet extends Composite {
	private ImgSizeUtil isu = new ImgSizeUtil();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	/*public SystemSet(Composite parent, int style) {
		super(parent, SWT.BORDER);
		}*/
	public SystemSet(Composite parent, int style,Shell shell) {
		super(parent, SWT.BORDER);
		
		Combo combo = new Combo(this, SWT.NONE);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MainUi mui=new MainUi();
				if(combo.getText().equals("金属黑")){
				Image image = SWTResourceManager.getImage(MainUi.class, "/images/beijing1.png");
				shell.setBackgroundImage(image);
				}else if(combo.getText().equals("线条蓝")){
					Image image = SWTResourceManager.getImage(MainUi.class, "/images/beijing2.jpg");
					shell.setBackgroundImage(image);
				}else if(combo.getText().equals("蓝1")){
						Image image = SWTResourceManager.getImage(MainUi.class, "/images/beijing.jpg");
						shell.setBackgroundImage(image);
				}else if(combo.getText().equals("蓝2")){
							Image image = SWTResourceManager.getImage(MainUi.class, "/images/00.png");
							shell.setBackgroundImage(image);
				}else if(combo.getText().equals("星空")){
							Image image = SWTResourceManager.getImage(MainUi.class, "/images/beijing3.png");
							shell.setBackgroundImage(image);
						}
			}
		});
		combo.setItems(new String[] {"金属黑", "线条蓝", "蓝1", "蓝2","星空"});
		combo.setBounds(552, 96, 156, 44);
		combo.setText("请选择皮肤：");
		
		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("华文新魏", 17, SWT.NORMAL));
		label.setBounds(397, 92, 131, 44);
		label.setText("更换皮肤：");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
		/*MainUi mui=new MainUi();
		Image image = SWTResourceManager.getImage(MainUi.class, "/images/welcome.png");
		shell.setBackgroundImage(image);*/
	}
}
