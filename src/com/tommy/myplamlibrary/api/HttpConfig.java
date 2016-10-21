package com.tommy.myplamlibrary.api;

public class HttpConfig {
	
private static final String NAMESPACE = "http://www.platform.com/";
	
	public static String getNameSpace(){
		return NAMESPACE;
	}
	
	private static String URL = "http://202.202.232.217:8572/hp/services/RegisterService?wsdl";
	
	private static String PROT = "8080";
	
	private static String IP = "";
	
	public static String getURL () {
		return URL;
	}
	
	public static void setURL(String uRL) {
		URL = uRL;
	}
	
	public static  String getPORT() {
		return PROT;
	}
	
	public static void setPORT(String pORT) {
		PROT = pORT;
	}
	
	public static String getIP() {
		return IP;
	}
	
	public static void setIP(String iP){
		IP = iP;
	}

}
