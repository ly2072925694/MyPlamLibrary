package com.tommy.myplamlibrary.util;

public class PubUtils {

	public static boolean isEmpty(String str){
		return null == str||str.length()==0||str.equals("")||"null".equals(str);
	}
	
	public static boolean isEmpty(Object obj){
		if(obj == null){
			return true;
			
		}
		String str = obj.toString();
		return str.length()==0||"null".equals(str)||str.equals("[]");
	}
	
}
