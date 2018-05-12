package com.biz;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.bean.Financeinfo;
import com.dao.DBHelper;

public class TestBarChart {
    ChartPanel frame;

    public TestBarChart() {
	CategoryDataset dataset = getDataSet();
	JFreeChart chart = ChartFactory.createBarChart3D(
		"日账单", // 图表标题
		"时间", // 目录轴的显示标签
		"金额", // 数值轴的显示标签
		dataset, // 数据集
		PlotOrientation.VERTICAL, // 图表方向：水平、垂直
		true, // 是否显示图例(对于简单的柱状图必须是false)
		false, // 是否生成工具
		false // 是否生成URL链接
		);
	// 从这里开始
	CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
	CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
	domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
	domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
	ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
	rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
	chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
	chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
	// 到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题
	frame = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame
    }

    private static CategoryDataset getDataSet() {
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	List<Financeinfo> list = queryData();
	for (Financeinfo msales : list) {
	    dataset.addValue(msales.getMoney(), msales.getType(), msales.getCheckouttime());
	}
	return dataset;
    }

    public ChartPanel getChartPanel() {
	return frame;
    }

    public static List<Financeinfo> queryData() {
	Connection conn = null;
	String sql;
	DBHelper db = new DBHelper();
	List<Financeinfo> list = new ArrayList<Financeinfo>();
	try {

	    conn = db.getCon();
	    Statement stmt = conn.createStatement();
	    sql = "select sum(money) as 金额 ,to_char(checkouttime,'yyyy-MM-dd') as 时间,'住房消费' as 类型  from checkin  group by checkouttime union all select sum(price) as 金额 ,to_char(checkin.checkouttime,'yyyy-MM-dd') as 时间,'食品消费' as 类型 from foodorder,checkin where foodorder.rid = checkin.rid group by checkouttime";
	    ResultSet rs = stmt.executeQuery(sql);
	    while (rs.next()) {
		list.add(new Financeinfo(rs.getInt(1),rs.getString(2),rs.getString(3)));
	    }
	} catch (SQLException e) {
	    System.out.println("MySQL操作错误");
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		conn.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return list;
    }
}