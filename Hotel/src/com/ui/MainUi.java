package com.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.dialog.AboutUsDialog;
import com.dialog.ChangeDialog;
import com.utils.ImgSizeUtil;
import com.utils.LayoutUtil;

public class MainUi {
	private String name;
	protected Shell shell;
	private ImgSizeUtil isu = new ImgSizeUtil();
	private StackLayout sl;
	private ManOrder mo;

	private RoomsManage rm;
	private TakeOut to;
	private Custom cu;
	private Finance fi;
	private SystemSet ss;
	private Welcome wl;
	
	private Date d;
	private SimpleDateFormat sdf;
	
	public MainUi() {}
	public MainUi(String name) {
		this.name = name;
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainUi window = new MainUi();
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
	
		shell.setSize(1200, 700);
		shell.setText("Starry Sky酒店管理系统");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		Image image = SWTResourceManager.getImage(MainUi.class, "/images/00.png");
		isu.imgSize(image, shell);
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		//设置小图标
		Image image1=new Image(shell.getDisplay(),"src\\images\\logo.png");
		shell.setImage(image1);
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean r=MessageDialog.openQuestion(shell, "切换用户？", "确定切换吗？");
				if(r){
					MainUi.this.shell.setVisible(false);
					
					LoginUi lgu=new LoginUi();
					lgu.open();
				}
				
			}
		});
		menuItem.setText("切换用户");
		
		//修改密码
		MenuItem menuItem_2 = new MenuItem(menu, SWT.NONE);
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChangeDialog msd = new ChangeDialog(shell,SWT.NONE);
				msd.open();
			}
		});
		
		menuItem_2.setText("修改密码");
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AboutUsDialog aud = new AboutUsDialog(shell,SWT.NONE);
				aud.open();
			}
		});
		mntmNewItem.setText("关于我们");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean r=MessageDialog.openQuestion(shell, "退出系统？", "确定退出吗？");
				if(r){
					System.exit(0);
				}
			}
		});
		menuItem_1.setText("退出系统");
		
		SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(167, 20, 49, 63);
		Image img1 =SWTResourceManager.getImage(MainUi.class, "/images/01.png");
		isu.imgSize(img1, label_1);
		Image img2 =SWTResourceManager.getImage(MainUi.class, "/images/02.png");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBounds(270, 20, 55, 49);
		Image img3 =SWTResourceManager.getImage(MainUi.class, "/images/03.png");
		isu.imgSize(img3, label_3);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setBounds(370, 14, 56, 59);
		Image img4 =SWTResourceManager.getImage(MainUi.class, "/images/04.png");
		isu.imgSize(img4, label_4);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setBounds(470, 14, 63, 63);
		Image img5 =SWTResourceManager.getImage(MainUi.class, "/images/05.png");
		isu.imgSize(img5, label_5);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setBounds(570, 20, 53, 50);
		Image img6 =SWTResourceManager.getImage(MainUi.class, "/images/06.png");
		isu.imgSize(img6, label_6);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setBounds(670, 20, 51, 52);
		Image img7 =SWTResourceManager.getImage(MainUi.class, "/images/07.png");
		isu.imgSize(img7, label_7);
		
		Label label_01 = new Label(composite, SWT.NONE);
		label_01.setFont(SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		label_01.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_01.setBounds(157, 90, 76, 23);
		label_01.setText("散客开单");
		
		Label label_03 = new Label(composite, SWT.NONE);
		label_03.setFont(SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		label_03.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_03.setText("住房管理");
		label_03.setBounds(262, 90, 76, 28);
		
		Label label_04 = new Label(composite, SWT.CENTER);
		label_04.setFont(SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		label_04.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_04.setText("便利点餐");
		label_04.setBounds(365, 90, 76, 23);
		
		Label label_05 = new Label(composite, SWT.NONE);
		label_05.setFont(SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		label_05.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_05.setText("消费结账");
		label_05.setBounds(470, 93, 76, 23);
		
		Label label_06 = new Label(composite, SWT.NONE);
		label_06.setFont(SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		label_06.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_06.setText("财务管理");
		label_06.setBounds(570, 93, 76, 23);
		
		Label label_07 = new Label(composite, SWT.NONE);
		label_07.setFont(SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		label_07.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_07.setText("系统设置");
		label_07.setBounds(670, 93, 76, 23);
		
		Label lblStarrySky = new Label(composite, SWT.NONE);
		lblStarrySky.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblStarrySky.setFont(SWTResourceManager.getFont("Script MT Bold", 26, SWT.NORMAL));
		lblStarrySky.setBounds(782, 20, 163, 63);
		lblStarrySky.setText("Starry Sky");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setFont(SWTResourceManager.getFont("华文新魏", 26, SWT.ITALIC));
		label_2.setBounds(951, 46, 223, 37);
		label_2.setText("酒店管理系统");
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setImage(SWTResourceManager.getImage("src\\images\\tubiao.png"));
		label_8.setBounds(33, 10, 82, 100);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		
		wl = new Welcome(composite_1,SWT.BORDER);
		wl.setFont(SWTResourceManager.getFont("楷体", 15, SWT.NORMAL));
		mo = new ManOrder(composite_1,SWT.BORDER);	
		rm = new RoomsManage(composite_1,SWT.BORDER);
		to = new TakeOut(composite_1,SWT.BORDER);
		cu = new Custom(composite_1,SWT.BORDER);
		fi = new Finance(composite_1,SWT.BORDER);
		ss = new SystemSet(composite_1,SWT.BORDER,shell);
		sl = new StackLayout();   //堆栈布局
		sl.topControl = wl;     //设置当前应显示的面板
		composite_1.setLayout(sl);
		
		Composite composite_2 = new Composite(sashForm, SWT.BORDER);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_2, SWT.NONE);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setFont(SWTResourceManager.getFont("仿宋", 14, SWT.NORMAL));
		composite_3.setLayout(null);
		
		Label lblNull = new Label(composite_3, SWT.NONE);
		lblNull.setAlignment(SWT.CENTER);
		lblNull.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNull.setFont(SWTResourceManager.getFont("华文新魏", 16, SWT.NORMAL));
		lblNull.setBounds(0, 10, 569, 30);
		lblNull.setText("欢迎光临Starry Sky 酒店管理系统："+name+"，祝您工作顺利哟！");
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setLayout(null);
		
		Label label = new Label(composite_4, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(SWTResourceManager.getFont("Segoe Script", 20, SWT.NORMAL));
		label.setBounds(141, 0, 516, 30);
		sashForm_1.setWeights(new int[] {595, 582});
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					d = new Date();
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							label.setText(sdf.format(d));
						}
					});
				}
			}
		}).start();
		sashForm.setWeights(new int[] {115, 483, 38});
		
		label_1.addMouseListener(new MouseAdapter() {
			
			public void mouseDown(MouseEvent e) {
				sl.topControl = mo; 
				composite_1.layout();
			}
		});
		label_1.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				label_1.setBounds(163, 17, 55, 70);
			}
		});
		label_1.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {
				label_1.setBounds(167, 20, 49, 63);
			}
		});
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				sl.topControl = rm; 
				composite_1.layout();
			}
		});
		label_3.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				label_3.setBounds(267, 17, 61, 55);
			}
		});
		label_3.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {
				label_3.setBounds(270, 20, 55, 49);
			}
		});
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				to.initUi();
				to.initroom();
				sl.topControl = to; 	
				composite_1.layout();
			}
		});
		label_4.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				label_4.setBounds(367, 11, 61, 65);
			}
		});
		label_4.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {
				label_4.setBounds(370, 14, 56, 59);
			}
		});
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				sl.topControl = cu; 
				composite_1.layout();
			}
		});
		label_5.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				label_5.setBounds(467, 11, 69, 69);
			}
		});
		label_5.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {
				label_5.setBounds(470, 14, 63, 63);
			}
		});
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				fi.initUi();
				sl.topControl = fi; 
				composite_1.layout();
			}
		});
		label_6.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				label_6.setBounds(567, 17, 59, 56);
			}
		});
		label_6.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {
				label_6.setBounds(570, 20, 53, 50);
			}
		});
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				sl.topControl =ss; 
				composite_1.layout();
			}
		});
		label_7.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				label_7.setBounds(667, 17, 57, 59);
			}
		});
		label_7.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {
				label_7.setBounds(670, 20, 51, 52);
			}
		});
		
		//滚动字幕
		int x=lblNull.getLocation().x;
		Timer timer=new Timer(true);
		class myTask extends TimerTask{
		@Override
		public void run() {
				// TODO 自动生成方法存根
				shell.getDisplay().syncExec(new Runnable(){
				public void run(){
				int mx=lblNull.getLocation().x;
				int my=lblNull.getLocation().y;
				lblNull.setLocation(mx-1, my);
				if(mx==-500){
					
					lblNull.setLocation(x+480, my);
				}
				}
			});
		  }
		}
		timer.schedule(new myTask(), 0,20);
	}
}
