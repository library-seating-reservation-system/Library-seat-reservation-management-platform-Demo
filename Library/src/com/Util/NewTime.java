package com.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTime {
	public String LoginTime(){
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	}
}
