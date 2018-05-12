package com.dialog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.dao.DBHelper;
import com.ui.ForgetUi;
import com.ui.LoginUi;
import com.utils.CanvasUtils;
import com.utils.Encrypt;
import com.utils.SwtUtils;

public class ChangeDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_1;
	private Text text_2;
	private Text text;
	private Text text_3;
	private Text text_4;
	private Button button;
    private String str0="";
    private Label label_8;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ChangeDialog(Shell parent, int style) {
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
		com.utils.LayoutUtil.centerShell(shell.getDisplay(), shell);
		
		
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
		shell.setSize(706, 463);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		Image image=new Image(shell.getDisplay(),"src\\images\\xiugai.jpg");
		shell.setBackgroundImage(image);
		
		Composite composite = new Composite(shell, SWT.NONE);
		
		Label label = new Label(composite, SWT.CENTER);
		label.setText("修改密码");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(SWTResourceManager.getFont("华文新魏", 26, SWT.ITALIC));
		label.setBounds(282, 20, 237, 47);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("         姓   名：");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_1.setBounds(176, 89, 96, 23);
		
		text_1 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(310, 133, 209, 26);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("        原密码：");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_2.setBounds(176, 136, 96, 23);
		
		text_2 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_2.setBounds(310, 180, 209, 26);
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(310, 86, 209, 26);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("       新密码：");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_3.setBounds(176, 183, 96, 17);
		
		text_3 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_3.setBounds(310, 227, 209, 26);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("再次输入新密码：");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_4.setBounds(135, 230, 137, 17);
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setBounds(310, 276, 121, 26);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("请输入验证码：");
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_5.setBounds(151, 279, 121, 17);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setText("返回登录");
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_6.setFont(SWTResourceManager.getFont("华文新魏", 15, SWT.ITALIC));
		label_6.setBounds(419, 342, 100, 31);
		
		button = new Button(composite, SWT.NONE);

		
		button.setImage(SWTResourceManager.getImage("src\\images\\chabutton.jpg"));
		button.setBounds(218, 338, 104, 31);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		label_7.setImage(SWTResourceManager.getImage("src\\images\\wrong.png"));
		label_7.setBounds(656, 0, 44, 31);
		
		
		//验证码
		label_8 = new Label(composite, SWT.BORDER);
		
		//CanvasUtils can = new CanvasUtils();
		//yzmInit();
		label_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				CanvasUtils can = new CanvasUtils();
				yzmInit();
			}
		});
		label_8.setBounds(446, 271, 73, 38);
		
		//返回登录
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				ChangeDialog.this.shell.setVisible(false);
				LoginUi login = new LoginUi();
				login.open();
			}
		});
		//修改密码
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String aname=text.getText().trim();
				String pwd=text_1.getText().trim();
				String newpwd=text_2.getText().trim();
				String tonewpwd=text_3.getText().trim();
				String yzm = text_4.getText().trim();
				
				String str=text_4.getText().trim();
				if(text_2.equals(text_3)) {
					SwtUtils.showMessageBox(shell, "出错了", "两次密码输入不一致");
					return;
				}
				if(!str.equals(str0)){

					MessageDialog.openError(shell, "验证码错误", "验证码输入有误!");
					yzmInit();
					text_2.setText("");
					return;
				}
				
				if(pwd==null  || "".equals(pwd)){
					SwtUtils.showMessageBox(shell, "出错了", "密码不能为空");
					return;
				}
				String inewpwd=Encrypt.md5(newpwd);
				String sql="update admins set pwd=? where aname=?";
				List<Object> params=new ArrayList<Object>();
				params.add(inewpwd);
				params.add(aname);
				
				DBHelper db=new DBHelper();
				try {
					int list=db.doUpdate(sql, params);
					if(list>0){
						
						/*Map<String,String> data=new HashMap<String,String>();
					
						data.put("pwd", pwd);*/
						
						SwtUtils.showMessageBox(shell, "成功","修改密码成功");
						  
					}else{
						SwtUtils.showMessageBox(shell, "失败","修改密码失败,原密码与新密码不能一致");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		yzmInit();
	}
	private void yzmInit() {
		CanvasUtils can = new CanvasUtils();

		str0=can.paint();
		String filename0="src\\images\\yzm.jpg";
		try {
			FileInputStream stream=new FileInputStream(filename0); 
			ImageData imageData=new ImageData(stream); //读到原大小的图片
			//转换图片的大小 scaledTo 等比例缩小
			imageData=imageData.scaledTo(label_8.getBounds().width, label_8 .getBounds().height);
			Image image0=new Image(label_8.getDisplay(),imageData);
			label_8.setImage(image0);
			
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
	}
}
