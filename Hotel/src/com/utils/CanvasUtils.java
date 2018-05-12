package com.utils;
/*
Canvas 组件表示屏幕上一个空白矩形区域，应用程序可以在该区域内绘图，或者可以从该区域捕获用户的输入事件。
应用程序必须为 Canvas 类创建子类，以获得有用的功能（如创建自定义组件）。必须重写 paint 方法，以便在 canvas 上执行自定义图形。

Graphics 类是所有图形上下文的抽象基类，允许应用程序在组件（已经在各种设备上实现）以及闭屏图像上进行绘制。
*/ 
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class CanvasUtils extends Canvas{
	private String str = "";
    
	public String paint() {     //必须重写paint方法
		 int width = 86;
		  int height= 40;
		  int lines = 10;
		  BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   //生成图像缓冲区
		  Graphics g = img.getGraphics();    //得到Graphics的对象
		 
		  g.setColor(Color.white);          //设置背景色
		  g.fillRect(0, 0, width, height);  //画背景 ，填充指定的矩形。使用图形上下文的当前颜色填充该矩形
		 
		  //设置字体
		  g.setFont(new Font("宋体", Font.BOLD, 18));
		 
		  //随机数字
		  Random r = new Random();
		  for(int i=0;i<4;i++){
			   int a = r.nextInt(10);//取10以内的整数[0，9]
			   str += String.valueOf(a);
			   int y = 10+r.nextInt(20); //10~30范围内的一个整数，作为y坐标
			   Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
			   g.setColor(c);
			   g.drawString(""+a, 5+i*width/4, y);
		  }
		  //干扰线
		  for(int i=0;i<lines;i++){
		   Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		   g.setColor(c);
		   //在此图形上下文的坐标系中，使用当前颜色在点 (x1, y1) 和 (x2, y2) 之间画一条线。
		   g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
		  }
		 
		  g.dispose();//类似于流中的close()带动flush()---把数据刷到img对象当中
		  try {
			ImageIO.write(img, "JPG", new FileOutputStream("src\\images\\yzm.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
