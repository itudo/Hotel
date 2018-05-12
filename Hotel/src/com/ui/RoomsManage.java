package com.ui;

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
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import com.biz.HotelBiz;
import com.dao.DBHelper;
import com.dialog.FreeComposite;
import com.dialog.RuzhuComposite;
import com.dialog.ToShowDataComposite;

import org.eclipse.wb.swt.SWTResourceManager;

public class RoomsManage extends Composite {
	public StackLayout sl;
	public RuzhuComposite ru;
	public FreeComposite fu;
	private 	DBHelper db=new DBHelper();
	public Combo combo;//房间类型下拉栏
	public Combo combo_1;//状态类型下拉栏
	public Combo combo_2;//楼层类型下拉栏
	private ToShowDataComposite tsdc;
	public Composite composite_4;
	private Shell shell;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RoomsManage(Composite parent, int style) {
		super(parent, style);
		shell=parent.getShell();
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		
		Label lblNewLabel = new Label(composite, SWT.CENTER);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		lblNewLabel.setBounds(63, 33, 112, 23);
		lblNewLabel.setText("房间类型查询：");
		
		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		combo.setForeground(SWTResourceManager.getColor(0, 0, 0));
		combo.setItems(new String[] {"请选择类别:", "单人间", "双人间", "三人间", "商务间", "豪华间", "套间"});
		combo.setBounds(188, 31, 141, 25);
		combo.select(0);
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(385, 33, 80, 23);
		label.setText("楼层查询：");
		
		combo_1 = new Combo(composite, SWT.READ_ONLY);
		combo_1.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		combo_1.setItems(new String[] {"请选择楼层:", "一楼", "二楼", "三楼"});
		combo_1.setBounds(487, 31, 141, 25);
		combo_1.select(0);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		lblNewLabel_1.setText("状态查询：");
		lblNewLabel_1.setBounds(675, 33, 80, 19);
		
		combo_2 = new Combo(composite, SWT.READ_ONLY);
		combo_2.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		combo_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		combo_2.setItems(new String[] {"请选择状态:", "空闲", "入住", "预订", "未整理"});
		combo_2.setBounds(761, 31, 141, 25);
		combo_2.select(0);
		
		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("华文新魏", 13, SWT.NORMAL));
		button.setBounds(962, 29, 80, 27);
		button.setText("查 询");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		sl=new StackLayout();//堆栈布局
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		tsdc=new ToShowDataComposite(composite_3,SWT.NONE);
		tsdc.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		
		ru=new RuzhuComposite(composite_4,SWT.NONE);
		fu=new FreeComposite(composite_4,SWT.NONE);
		fu.text_2.setEnabled(false);
		fu.text_2.setEditable(true);
		fu.text_3.setEnabled(false);
		sl.topControl=fu;	//设置当前的显示的面板
			sashForm_1.setWeights(new int[] {5, 7});
			sashForm.setWeights(new int[] {75, 392});
			composite_4.setLayout(sl);	
		
			
												
		
		//查询
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rtname=combo.getText().trim();
				String locate=combo_1.getText().trim();
				String rstate=combo_2.getText().trim();
				StringBuffer sb=new StringBuffer("select rid,rtname,locate,rstate,rprice from room left join roomtype on room.rtid=roomtype.rtid where 1=1 ");
				List<Object> params = new ArrayList<Object>();
				if (!"请选择类别:".equals(rtname)) {
					sb.append(" and rtname=? ");
					params.add( rtname);
				}
				if (!"请选择楼层:".equals(locate)) {
					sb.append(" and locate=? ");
					params.add(locate);
				}
				if (!"请选择状态:".equals(rstate)) {
					sb.append(" and rstate=? ");
					params.add(rstate);
				}
				sb.append("order by rid");
			try {
				List<Map<String,String>> list=db.select(sb.toString(), params);
					

					List<String> columnsList=new ArrayList<String>();
					columnsList.add("RID");
					columnsList.add("RTNAME");
					columnsList.add("LOCATE");
					columnsList.add("RSTATE");
					columnsList.add("RPRICE");
					List<String> tablecolumnList=new ArrayList<String>();
					tablecolumnList.add("房间号");
					tablecolumnList.add("房间类型");
					tablecolumnList.add("所在楼层");
					tablecolumnList.add("房间状态");
					tablecolumnList.add("房间价格");
					tsdc.showData(list, columnsList, tablecolumnList);
					tsdc.layout();
					
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
		});
	}
}
