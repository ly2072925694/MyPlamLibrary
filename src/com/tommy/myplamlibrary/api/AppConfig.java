package com.tommy.myplamlibrary.api;

import android.os.Environment;

import com.tommy.myplamlibrary.entity.User;


public class AppConfig {
	
	public static String num = "";
	public static String name = "";
	
	public static String filePath = Environment.getExternalStorageDirectory() + "/Library.db";
	
	public static int LOGIN = -1;
	
	public static String UserId = "";
	public static String UserName = "";
	
	
	public void setLogin(User user) {
		
		UserId = user.getUserId();
		UserName = user.getUserName();
		LOGIN = 1;
	}
	
	public void setLogOut() {
		
		UserId = "";
		UserName = "";
		LOGIN = -1;
	}

}
