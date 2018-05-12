package com.dialog;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;

import com.ui.MainUi;
import com.utils.ImgSizeUtil;
import com.utils.LayoutUtil;

public class AboutUsDialog extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AboutUsDialog(Shell parent, int style) {
		super(parent, style);
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
//		shell.setImage(SWTResourceManager.getImage(AboutUsDialog.class, "/images/aboutus.jpg"));
		shell.setSize(583, 432);
		shell.setText("联系我们");
		shell.setLayout(new FormLayout());
		
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setBackgroundImage(SWTResourceManager.getImage(AboutUsDialog.class, "/images/aboutus.jpg"));
		Composite composite = new Composite(shell, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 393);
		fd_composite.right = new FormAttachment(0, 474);
		fd_composite.top = new FormAttachment(0, 79);
		fd_composite.left = new FormAttachment(0, 85);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblStarry = new Label(composite, SWT.NONE);
		lblStarry.setFont(SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		lblStarry.setText("如果在使用的过程中您遇到问题，您可以在工\r\n\r\n"
				+ "作日通过以下渠道将问题向我们反映。\r\n\r\n\r\n"
				+ "Starry Sky 酒店管理系统感谢您的理解和支持。\r\n\r\n\r\n"
				+ "微信公众号：Starry Sky Hotel\r\n\r\n"
				+ "联系方式：400-2564-4886\r\n\r\n"
				+ "电子邮箱：2533713320@qq.com");
		
		Label label = new Label(shell, SWT.NONE);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		label.setImage(SWTResourceManager.getImage(ChooseDialog.class, "/images/wrong.png"));

		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0);
		fd_label.right = new FormAttachment(100);
		fd_label.bottom = new FormAttachment(0, 38);
		fd_label.left = new FormAttachment(100, -45);
		label.setLayoutData(fd_label);

	}
}
