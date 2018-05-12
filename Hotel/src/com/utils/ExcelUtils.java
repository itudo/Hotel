package com.utils;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class ExcelUtils {
	public static  void listInfo(String date,Table table) {
		String fileName = "日账单"+date;
		WritableWorkbook book;
		try {
			File file = new File("D:\\" + fileName+".xls");
			if(file.exists()){

				file.delete();   
				file = new File("D:\\" + fileName+".xls");
			}else{
			book = Workbook.createWorkbook(new File("D:\\" + fileName+".xls"));
			WritableSheet sheet = book.createSheet("sheet1", 0);
			jxl.write.WritableFont wf = new jxl.write.WritableFont(WritableFont.createFont("楷体"), 12, WritableFont.BOLD, false); 
			jxl.write.WritableFont swf = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD, false);
			
			//WritableFont font3=new WritableFont(WritableFont.createFont("楷体 _GB2312"),12,WritableFont.NO_BOLD );
			//TIMES是字体大小
			//BOLD是判断是否为斜体,选择true时为斜体
			jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(wf); 
			jxl.write.WritableCellFormat swcfF = new jxl.write.WritableCellFormat(swf);
			//把水平对齐方式指定为居中
			wcfF.setAlignment(jxl.format.Alignment.CENTRE);
			//把垂直对齐方式指定为居中
			wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			//把水平对齐方式指定为居中
			swcfF.setAlignment(jxl.format.Alignment.CENTRE);
			//把垂直对齐方式指定为居中
			swcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			//jxl.write.Label labelC = new jxl.write.Label(0, 0, "This is a Label cell",wcfF); 
			int rows = table.getItemCount();
			int cols = table.getColumnCount();
			TableColumn[] ti = table.getColumns();
			//将第一行的高度设为200
			sheet.setRowView(0,300);
			if (ti[0].getText().intern() == "" || ti[0].getText().intern() =="全选") {
				for (int i = 0; i < ti.length-1; i++) {
					Label label = new Label(i, 0, ti[i + 1].getText(),wcfF);
					sheet.addCell(label);
				}
				for (int i = 0; i < rows; i++) {
					TableItem item = table.getItem(i);
					for (int j = 0; j < cols-1; j++) {
						sheet.setColumnView(j, 22);
						// String col = item.getText(j);
						Label label = new Label(j, i + 1, item.getText(j+1),swcfF);
						sheet.addCell(label);
					}
				}
			}else{
				for (int i = 0; i < ti.length; i++) {
					Label label = new Label(i, 0, ti[i].getText(),wcfF);
					sheet.addCell(label);
				}
				for (int i = 0; i < rows; i++) {
					TableItem item = table.getItem(i);
					for (int j = 0; j < cols; j++) {
						sheet.setColumnView(j, 22);
						// String col = item.getText(j);
						Label label = new Label(j, i + 1, item.getText(j),swcfF);
						sheet.addCell(label);
					}
				}
			}
			book.write();
			book.close();
			Runtime.getRuntime().exec("cmd  /c  start  D:\\" + fileName+".xls");  
			//Runtime.getRuntime().exec("cmd  /c  start  F:\\reader.xls"); 
			/*
			 * TableItem item = table.getItem(tRow); //假设要取得的列数为tCol, 则该列的值为
			 * String col = item.getText(tCol) //3.Re:怎么在SWT的TABLE中取得表中的数据 [Re:
			 * mzjlxukuan] Copy to clipboard //Posted by: waistcoat //Posted on:
			 * 2006-05-07 20:53
			 * 
			 * //先定义好 DefaultTableModel. //比如 DefaultTableModel dtf = new
			 * DefaultTableModel(String rowData,String columTitle); Table table
			 * = new Table(dtf); //如果要取得某行,某列的值. 只需使用 String data =
			 * dft.getValueAt(table.getSelectedRow(),table.getSelectedColum());
			 */
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
