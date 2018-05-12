package com.bean;

import java.sql.Blob;

public class Foodinfo {
   private String  fid;
   private String fname;
   private String fprice;
   private String fprice1;
   private Blob pic;
   private String fdes;
   private String ftid;
   private String sheadStr;
   
public Foodinfo() {
	super();
}

public Foodinfo(String fid, String fname, String fprice, Blob pic, String fdes,
		String ftid, String sheadStr) {
	super();
	this.fid = fid;
	this.fname = fname;
	this.fprice = fprice;
	this.pic = pic;
	this.fdes = fdes;
	this.ftid = ftid;
	this.sheadStr = sheadStr;
	
}


		public Foodinfo(String fid, String fname, String fprice, String fprice1,
		Blob pic, String fdes, String ftid, String sheadStr) {
	super();
	this.fid = fid;
	this.fname = fname;
	this.fprice = fprice;
	this.fprice1 = fprice1;
	this.pic = pic;
	this.fdes = fdes;
	this.ftid = ftid;
	this.sheadStr = sheadStr;
}

		public String getFprice1() {
	return fprice1;
}

public void setFprice1(String fprice1) {
	this.fprice1 = fprice1;
}

		@Override
public String toString() {
	return "Foodinfo [fid=" + fid + ", fname=" + fname + ", fprice=" + fprice
			+ ", pic=" + pic + ", fdes=" + fdes + ", ftid=" + ftid
			+ ", sheadStr=" + sheadStr + "]";
}

		public String getFid() {
			return fid;
		}
		
		public void setFid(String fid) {
			this.fid = fid;
		}
		
		public String getFname() {
			return fname;
		}
		
		public void setFname(String fname) {
			this.fname = fname;
		}
		
		public String getFprice() {
			return fprice;
		}
		
		public void setFprice(String fprice) {
			this.fprice = fprice;
		}
		
		public Blob getPic() {
			return pic;
		}
		
		public void setPic(Blob pic) {
			this.pic = pic;
		}
		
		public String getFdes() {
			return fdes;
		}
		
		public void setFdes(String fdes) {
			this.fdes = fdes;
		}
		
		public String getFtid() {
			return ftid;
		}
		
		public void setFtid(String ftid) {
			this.ftid = ftid;
		}
		
		public String getSheadStr() {
			return sheadStr;
		}
		
		public void setSheadStr(String sheadStr) {
			this.sheadStr = sheadStr;
		}
		   
   
   
}
