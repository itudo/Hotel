package com.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.BackingStoreException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.dao.DBHelper;
import com.swtdesigner.SWTResourceManager;












import com.ui.LoginUi;
import com.utils.CanvasUtils;
import com.utils.Encrypt;
import com.utils.LayoutUtil;
import com.utils.RegistrationUtils;
import com.utils.SwtUtils;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;


public class LoginUi {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
    private Button button;
    private Label label_3;
    private String str0="";
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginUi window = new LoginUi();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		
		createContents();
		shell.open();
		shell.layout();
		LayoutUtil.centerShell(display, shell);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(700, 466);
		shell.setText("Starry Sky酒店管理系统");
		
		//设置小图标
		Image image1=new Image(shell.getDisplay(),"src\\images\\logo.png");
		shell.setImage(image1);
		
		//设置背景颜色
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		Image image=new Image(shell.getDisplay(),"src\\images\\denglu.jpg");
		shell.setBackgroundImage(image);
		
		Group group = new Group(shell, SWT.BORDER);
		group.setFont(com.swtdesigner.SWTResourceManager.getFont("华文新魏", 20, SWT.NORMAL));
		group.setText("欢迎登录");
		group.setForeground(com.swtdesigner.SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		group.setBounds(10, 31, 346, 331);
		
		Label lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(com.swtdesigner.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		lblNewLabel.setBounds(10, 68, 68, 25);
		lblNewLabel.setText("账    号：");
		
		Label label = new Label(group, SWT.NONE);
		label.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(com.swtdesigner.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label.setBounds(10, 119, 61, 17);
		label.setText("密    码：");
		
		text = new Text(group, SWT.BORDER);
		text.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 10, SWT.NORMAL));
		text.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setBounds(84, 65, 208, 30);
		
		text_1 = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text_1.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 10, SWT.NORMAL));
		text_1.setBounds(85, 116, 207, 30);
		
		button = new Button(group, SWT.CHECK);
		button.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button.setFont(com.swtdesigner.SWTResourceManager.getFont("华文新魏", 10, SWT.NORMAL));
		button.setBounds(84, 190, 86, 25);
		button.setText("记住密码");
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(com.swtdesigner.SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		label_1.setBounds(10, 162, 61, 22);
		label_1.setText("验证码：");
		
		text_2 = new Text(group, SWT.BORDER);
		text_2.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 10, SWT.NORMAL));
		text_2.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_2.setBounds(84, 159, 97, 25);
		
		Button button_1 = new Button(group, SWT.NONE);
		
		
		button_1.setImage(com.swtdesigner.SWTResourceManager.getImage("src\\images\\logbutton.jpg"));
		button_1.setBounds(111, 221, 118, 40);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setForeground(com.swtdesigner.SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		label_2.setFont(com.swtdesigner.SWTResourceManager.getFont("华文新魏", 16, SWT.ITALIC));
		label_2.setBounds(10, 289, 127, 28);
		label_2.setText("忘记密码");
		
		label_3 = new Label(group, SWT.BORDER);
		
		label_3.setBounds(206, 152, 86, 40);
		CanvasUtils can = new CanvasUtils();
		yzmInit();
//		String yzmr = yzmInit();
//		label_3.setImage(com.swtdesigner.SWTResourceManager.getImage("src\\images\\yzm.jpg"));
		//看不清刷新验证码
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				CanvasUtils can = new CanvasUtils();
				yzmInit();
			}
		});
		//登录
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name=text.getText().trim();
				String pwd=text_1.getText().trim();
				String yzm = text_2.getText().trim();
				//System.out.println(yzm);
				String str=text_2.getText().trim();
				if(!str.equals(str0)){

					MessageDialog.openError(shell, "验证码错误", "验证码输入有误!");
					yzmInit();
					text_2.setText("");
					return;
				}
				if(name==null  || "".equals(name)){
					SwtUtils.showMessageBox(shell, "出错了", "用户名不能为空");
					return;
				}
				if(pwd==null  || "".equals(pwd)){
					SwtUtils.showMessageBox(shell, "出错了", "密码不能为空");
					return;
				}
				String newpwd=Encrypt.md5(pwd);
				String sql="select * from admins where aname=? and pwd=?";
				List<Object> params=new ArrayList<Object>();
				params.add(name);
				params.add(newpwd);
				
				try {
					DBHelper db=new DBHelper();
					try {
						List<Map<String,String>> list=db.select(sql, params);
						if(list!=null&&list.size()>0){
							//判断"记住密码"是否勾选，勾了则将用户名和密码记录注册表
							//没勾 则从注册表中删除用户名和密码
							boolean flag=button.getSelection();
							Map<String,String> data=new HashMap<String,String>();
							data.put("name", name);
							data.put("pwd", pwd);
							if(flag){
								RegistrationUtils.saveRegistration(data);
							}else{
								RegistrationUtils.delRegistration(data);
							}
							SwtUtils.showMessageBox(shell, "成功","登录操作成功");
							  //切换面板
							  LoginUi.this.shell.setVisible(false);
						  MainUi mainUi =new MainUi(name);
						  mainUi.open();
							  
						}else{
							SwtUtils.showMessageBox(shell, "失败","登录操作失败,用户名或密码错误");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} catch (BackingStoreException e1) {
					e1.printStackTrace();
					SwtUtils.showMessageBox(shell, "失败","登录失败");

				}
			}
		});
		//跳转到注册
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				LoginUi.this.shell.setVisible(false);
				ForgetUi reg = new ForgetUi();
				reg.open();
			}
		});
		init();
	}
	 
		private void init() {
	     //读取注册表，看是否存了用户名和密码  如果存了   则显示到界面
			Map<String,String> m=RegistrationUtils.findRegistration();
			if(m!=null){
				text.setText(m.get("name"));
				text_1.setText(m.get("pwd"));
				button.setSelection(true);
			}
      }
		//刷新验证码
		private void yzmInit() {
			CanvasUtils can = new CanvasUtils();

			str0=can.paint();
			String filename0="src\\images\\yzm.jpg";
			try {
				FileInputStream stream=new FileInputStream(filename0); 
				ImageData imageData=new ImageData(stream); //读到原大小的图片
				//转换图片的大小 scaledTo 等比例缩小
				imageData=imageData.scaledTo(label_3.getBounds().width, label_3 .getBounds().height);
				Image image0=new Image(label_3.getDisplay(),imageData);
				label_3.setImage(image0);
				
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
		}
}
