package com.biz;

public class TimeBiz {
	public static int getMoney(int time,String money) {
		int day = 0;
		int hour = 0;
		int moneys = 0;
		if(time>24) {
			day = time/24;
			hour = time%24;
			moneys = day*Integer.parseInt(money) + hour*(Integer.parseInt(money)/20);
		} else {
			moneys = time*(Integer.parseInt(money)/20);
		}
		
		return moneys;
	}
}
