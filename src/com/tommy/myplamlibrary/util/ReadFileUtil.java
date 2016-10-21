package com.tommy.myplamlibrary.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class ReadFileUtil {
	
	
	
	
	public ReadFileUtil(Context context) {
		
	}
	
	public static String read(Context context, String filePath){
		InputStreamReader isr = null;
		
		
		try {
			isr = new InputStreamReader(context.getAssets().open(filePath),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(isr);
		String line;
		StringBuilder builder = new  StringBuilder();
		
		try {
			while((line = br.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			isr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			JSONObject root = new JSONObject(builder.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = builder.toString();
		return s;
		
	}
	
	
}
