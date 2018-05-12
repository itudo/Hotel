package com.dialog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.biz.HotelBiz;
import com.dao.DBHelper;
import com.ui.ManOrder;
import com.utils.LayoutUtil;
import com.utils.SwtUtils;

public class ChooseDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private String roomId;
	public ChooseDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ChooseDialog(String roomId,Shell parent, int style) {
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
		shell.setSize(380, 150);
		shell.setText(getText());
		
		Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label.setFont(SWTResourceManager.getFont("华文新魏", 14, SWT.NORMAL));
		label.setBounds(107, 27, 190, 39);
		label.setText("请选择要进行的操作:");
		
		Button button = new Button(shell, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		button.setBounds(47, 72, 86, 40);
		button.setText("整 理");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("华文新魏", 12, SWT.NORMAL));
		button_1.setBounds(238, 72, 80, 40);
		button_1.setText("退 房");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		label_1.setImage(SWTResourceManager.getImage(ChooseDialog.class, "/images/wrong.png"));
		label_1.setBounds(336, 0, 38, 34);

		//整理操作
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sql = " select rstate from room  where rid = ?";
				DBHelper db = new DBHelper();
				List<Object> params = new ArrayList<Object>();
				params.add(roomId);
				try {
					List<Map<String,String>> list = db.select(sql, params);
					for(Map<String,String> map:list) {
						String name = map.get("RSTATE");
						if(name.equals("未整理")) {
							String sql1 = "update room set rstate = '空闲' where rid = ?";
							List<Object> params1 = new ArrayList<Object>();
							params1.add(roomId);
							db = new DBHelper();
							try {
								int r1 = db.doUpdate(sql1, params1);
								if(r1>0) {
									SwtUtils.showMessageBox(shell, "整理成功", "整理房间成功");
									shell.dispose();
								} else {
									SwtUtils.showMessageBox(shell, "整理失败", "整理房间失败");
									shell.dispose();
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						} else if(name.equals("入住") || name.equals("预订")) {
							SwtUtils.showMessageBox(shell, "整理失败", "房间未退房，不可整理");
						}else if(name.equals("空闲")) {
							SwtUtils.showMessageBox(shell, "整理失败", "房间空闲，无需整理");
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//退房操作
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DBHelper db = new DBHelper();
				String sql = " select idnum from checkin where rid = ?";								
				List<Object> params = new ArrayList<Object>();
				params.add(roomId);
				String sql1 = " select rstate from room where rid = ?";
				List<Object> params1 = new ArrayList<Object>();
				params1.add(roomId);
				String idnum="";
				String rstate="";
				try {
					List<Map<String,String>> list = db.select(sql, params);
					if(list != null && list.size()>0){
						for(Map<String,String> map:list){
							idnum = map.get("IDNUM");
						}
					}
					List<Map<String,String>> list1 = db.select(sql1, params1);
					if(list1 != null && list1.size()>0){
						for(Map<String,String> map:list1){
							rstate = map.get("RSTATE");
						}
					}
					if(rstate.equals("未整理")||rstate.equals("空闲")){
						SwtUtils.showMessageBox(shell, "退房失败", "此房间空闲或未整理，不能退房");
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				HotelBiz hb=new HotelBiz();
				int r=hb.deleteRoom(roomId, idnum);
				if(r>0){
					SwtUtils.showMessageBox(shell, "成功", "用户退房成功");
					shell.dispose();
				}else {
					SwtUtils.showMessageBox(shell, "出错了", "用户退房失败");
					shell.dispose();
					}
				/*try {
					List<Map<String,String>> list = db.select(sql, params);
					if(list != null && list.size()>0) {
						for(Map<String,String> map:list) {
							String idnum = map.get("IDNUM");
							String sql1 = "delete from custermer where idnum=?";
							List<Object> params1 = new ArrayList<Object>();
							params1.add(idnum);
							DBHelper db1 = new DBHelper();
							try {
								int r1 = db.doUpdate(sql1, params1);
								if (r1 > 0) {
									String sql2 = "delete from checkin where rid=?";
									List<Object> params2 = new ArrayList<Object>();
									params2.add(roomId);
									DBHelper db2 = new DBHelper();
									try {
										int r2 = db.doUpdate(sql2, params2);
										if (r2 > 0) {
											String sql3 = "delete from foodorder where rid = ?";
											List<Object> params3 = new ArrayList<Object>();
											params3.add(roomId);
											db = new DBHelper();
											int r3 = db.doUpdate(sql3, params3);
											if(r3 > 0) {
												String sql4 = "update room set rstate = '未整理' where rid = ?";
												List<Object> params4 = new ArrayList<Object>();
												params4.add(roomId);
												db = new DBHelper();
												int r4 = db.doUpdate(sql4, params4);
												if(r4>0) {
													SwtUtils.showMessageBox(shell, "成功", "用户退房成功");
													shell.dispose();
												} else {
													SwtUtils.showMessageBox(shell, "出错了", "用户退房失败");
													shell.dispose();
												}
											} else {
												SwtUtils.showMessageBox(shell, "出错了", "用户退房失败");
												shell.dispose();
											}
										} else {
											SwtUtils.showMessageBox(shell, "出错了", "用户退房失败");
											shell.dispose();
										}
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								} else {
									SwtUtils.showMessageBox(shell, "出错了", "用户退房失败");
									shell.dispose();
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					} else {
						SwtUtils.showMessageBox(shell, "出错了", "此房间没有用户入住或预订");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();					
				} */
			}
		});
	}
}
