package com.tommy.myplamlibrary.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tommy.myplamlibrary.entity.BasicInformation;
import com.tommy.myplamlibrary.entity.BorrowRecord;

public class JsonUtil {
	
	public static List<BorrowRecord> parseToBorrowRecordList(JSONArray jsonArray){
		BorrowRecord borrowRecord;
		List<BorrowRecord> list = new ArrayList<BorrowRecord>();
		System.out.println("-----------" + jsonArray.length());
		
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				borrowRecord =new BorrowRecord();
				JSONObject jObj = jsonArray.getJSONObject(i);
				
				borrowRecord.BookName = jObj.getString("BookName");
				System.out.println(borrowRecord.BookName);
				
				borrowRecord.BorrowTime = jObj.getString("BorrowTime");
				System.out.println(borrowRecord.BorrowTime);
				
				borrowRecord.ReturnTime = jObj.getString("ReturnTime");
				System.out.println(borrowRecord.ReturnTime);
				
				borrowRecord.ShouldTime = jObj.getString("ShouldTime");
				System.out.println(borrowRecord.ShouldTime);
				System.out.println("------------------------");
				
				list.add(borrowRecord);
			} catch (JSONException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
		return list;
	}
	public static BasicInformation parseToBasicInfoList(String jsonStr) {
		
		List<BasicInformation> list = new ArrayList<BasicInformation>();
		
		BasicInformation basic = new BasicInformation();
		try {
			JSONObject jsonObject = new JSONObject(jsonStr);
			
			basic.UserId  = jsonObject.getString("UserId");
			System.out.println(basic.UserId);
			basic.Departments = jsonObject.getString("Departments");
			basic.E_mail = jsonObject.getString("E_mail");
			basic.LendedNum = jsonObject.getString("LendedNum");
			basic.Major = jsonObject.getString("Major");
			basic.Max = jsonObject.getString("Max");
			basic.Phone = jsonObject.getString("Phone");
			basic.UserName = jsonObject.getString("UserName");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return basic;
	}

}
