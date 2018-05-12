package com.bean;

public class Financeinfo {
  private String checkouttime;
  private String type;
  private int money;
    

	public Financeinfo(int money,String checkouttime, String type) {
		super();
		this.checkouttime = checkouttime;
		this.type = type;
		this.money = money;
    }
	public String getCheckouttime() {
		return checkouttime;
	}
	public void setCheckouttime(String checkouttime) {
		this.checkouttime = checkouttime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}

  
  
	
}
