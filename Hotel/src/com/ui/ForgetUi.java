package com.ui;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.aliyuncs.exceptions.ClientException;
import com.biz.Sendsms;
import com.dao.DBHelper;
import com.swtdesigner.SWTResourceManager;
import com.utils.DateUtils;
import com.utils.Encrypt;
import com.utils.LayoutUtil;
import com.utils.PwdUtil;
import com.utils.SendMessageUtil;
import com.utils.SwtUtils;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;

public class ForgetUi {

	protected Shell shell;
	private Text text_2;
	private Text text_4;
	private Text text_5;
	private Composite composite;
	private Composite composite_1;
	private Composite composite_2;
	private Composite composite_3;
	private Composite composite_4;
	private Composite composite_5;
	private Composite composite_6;
	private Composite composite_7;
	private Composite composite_8;

	private int i=60;
	private Label label_2;
	private Label label_f;
	private Button btnNewButton_1;
	private Label label;
	private Text txtQ;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ForgetUi window = new ForgetUi();
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
		
		label_f = new Label(shell, SWT.NONE);
		
		
		label_f.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_f.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.ITALIC));
		label_f.setBounds(514, 155, 88, 23);
		label_f.setText("获取验证码");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_1.setBounds(300, 209, 61, 23);
		label_1.setText("验证码：");
		
		text_5 = new Text(shell, SWT.BORDER | SWT.CENTER);
		text_5.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		text_5.setBounds(388, 206, 120, 30);
		
		label_2 = new Label(shell, SWT.BORDER | SWT.CENTER);
		label_2.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		label_2.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("Tempus Sans ITC", 14, SWT.NORMAL));
		label_2.setBounds(529, 207, 37, 23);
	

		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_3.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setText("  密码强度:");
		label_3.setBounds(275, 307, 85, 17);
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setBounds(388, 313, 66, 11);
		
		composite = new Composite(sashForm, SWT.NONE);
		
		composite_1 = new Composite(sashForm, SWT.NONE);
		
		composite_2 = new Composite(sashForm, SWT.NONE);
		sashForm.setWeights(new int[] {1, 1, 1});
		
		SashForm sashForm_1 = new SashForm(shell, SWT.NONE);
		sashForm_1.setBounds(460, 313, 66, 11);
		
		composite_3 = new Composite(sashForm_1, SWT.NONE);
		
		composite_4 = new Composite(sashForm_1, SWT.NONE);
		
		composite_5 = new Composite(sashForm_1, SWT.NONE);
		sashForm_1.setWeights(new int[] {1, 1, 1});
		
		SashForm sashForm_2 = new SashForm(shell, SWT.NONE);
		sashForm_2.setBounds(529, 313, 61, 11);
		
		composite_6 = new Composite(sashForm_2, SWT.NONE);
		
		composite_7 = new Composite(sashForm_2, SWT.NONE);
		
		composite_8 = new Composite(sashForm_2, SWT.NONE);
		sashForm_2.setWeights(new int[] {1, 1, 1});
		
		label = new Label(shell, SWT.NONE);
		label.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label.setBounds(303, 104, 59, 17);
		label.setText("姓   名：");
		
		txtQ = new Text(shell, SWT.BORDER);
		txtQ.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		txtQ.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_BLACK));
		txtQ.setBounds(388, 101, 202, 30);
		
		
		label_f.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {// 开始倒计时

				String receiver=text_4.getText().trim();
				Sendsms sms=new Sendsms();
				sms.sendsms(receiver);
				try {

					time();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				
			}
		});
		
		/*composite_7 = new Composite(shell, SWT.NONE);
		composite_7.setBounds(570, 178, 20, 11);*/
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
		shell.setSize(704, 487);
		shell.setText("Starry Sky酒店管理系統");
		
		//设置小图标
		Image image1=new Image(shell.getDisplay(),"src\\images\\logo.png");
		shell.setImage(image1);

		//设置背景图片
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		Image image=new Image(shell.getDisplay(),"src\\images\\zhuce.jpg");
		shell.setBackgroundImage(image);
		
		Label lblNewLabel = new Label(shell, SWT.CENTER);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblNewLabel.setFont(SWTResourceManager.getFont("华文新魏", 23, SWT.ITALIC));
		lblNewLabel.setText("找回密码");
		lblNewLabel.setBounds(346, 36, 256, 44);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_1.setBounds(264, 256, 97, 17);
		label_1.setText("设置新密码：");
		
		text_2 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_2.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		
		text_2.setBounds(388, 253, 214, 30);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label_3.setBounds(300, 158, 61, 17);
		label_3.setText("手机号:");
		
		text_4 = new Text(shell, SWT.BORDER | SWT.CENTER);
		text_4.setFont(org.eclipse.wb.swt.SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		text_4.setBounds(388, 155, 120, 30);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setForeground(org.eclipse.wb.swt.SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label_5.setFont(SWTResourceManager.getFont("华文新魏", 16, SWT.ITALIC));
		label_5.setBounds(529, 349, 91, 27);
		label_5.setText("返回登录");
		
		btnNewButton_1 = new Button(shell, SWT.NONE);
		
		btnNewButton_1.setImage(SWTResourceManager.getImage("src\\images\\forbutton.png"));
		btnNewButton_1.setBounds(376, 338, 111, 44);
		
		//点击发送验证码倒计时
			
		
		//密码难度判断
		text_2.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String pwd = text_2.getText().trim();
				 int score = PwdUtil.getPwdLevel(pwd);
				 PwdUtil.setPwdLevel(composite, score, 10,234,0,0);
				 PwdUtil.setPwdLevel(composite_1, score,20,255,23,23);
				 PwdUtil.setPwdLevel(composite_2, score,30,255,70,70);
				 PwdUtil.setPwdLevel(composite_3, score,40,0,234,0);
				 PwdUtil.setPwdLevel(composite_4, score,50,23,255,23);
				 PwdUtil.setPwdLevel(composite_5, score,60,70,255,70);
				 PwdUtil.setPwdLevel(composite_6, score,70,0,0,234);
				 PwdUtil.setPwdLevel(composite_7, score,80,23,23,234);
				 PwdUtil.setPwdLevel(composite_8, score,90,70,70,255);
			}
		});
		//返回登录
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				ForgetUi.this.shell.setVisible(false);
				LoginUi login = new LoginUi();
				login.open();
			}
		});
		
		
		//找回密码
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name=txtQ.getText().trim();
				String pwd=text_2.getText().trim();
				String tel=text_4.getText().trim();
				if(name==null  || "".equals(name)){
					SwtUtils.showMessageBox(shell, "出错了", "用户名不能为空");
	 				return;
				}
				if(pwd==null  || "".equals(pwd)){
					SwtUtils.showMessageBox(shell, "出错了", "密码不能为空");
					return;
				}
				pwd=Encrypt.md5(pwd);
				String sql="update admins set pwd=? where aname=?";
				List<Object> params=new ArrayList<Object>();
				params.add(pwd);
				params.add(name);
	
				
				DBHelper db=new DBHelper();
				try {
					int result=db.doUpdate(sql, params);
					if(result>0){
						SwtUtils.showMessageBox(shell, "成功","找回密码成功");
						    //隐藏自己
						    ForgetUi.this.shell.setVisible(false);
						    //成功,则跳到登录界面
					    	LoginUi login=new LoginUi();
					    	login.open();
						}else{
							SwtUtils.showMessageBox(shell, "失败","找回密码失败");
						 
						}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void time() throws ParseException{
		//倒计时
		SimpleDateFormat sdf = new SimpleDateFormat("ss");

		Date d = sdf.parse("30");
		final Date dEnd = sdf.parse("00");
		
		String d1=DateUtils.formatDate(d, "ss");
		label_2.setText(d1);
		final Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(d);
		final Timer timer = new Timer();

		timer.schedule(new TimerTask() {

			public void run() {
				
				rightNow.add(Calendar.SECOND, -1); 
				final Date d = rightNow.getTime();
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						label_2.setText(DateUtils.formatDate(d, "ss"));
					}
				});
			
				if(d.compareTo(dEnd)==0){
			timer.cancel();
		
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
						label_f.setText("重新发送");
				}
			});
		}
			}

		}, 1000, 1000);
		
	}
}
