package com.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
/**
 * 所有swt项目用的工具方法 
 */
public class SwtUtils {
	/**
	 * 显示普通对话框的工具类
	 * @param shell
	 * @param title
	 * @param message
	 */
	public static void showMessageBox(   Shell shell, String title, String message){
		MessageBox mb=new MessageBox(   shell, SWT.None);
		mb.setText(title);
		mb.setMessage(message);
		mb.open();
	}
}
