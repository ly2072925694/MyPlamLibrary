package com.tommy.myplamlibrary.sqlliteutil;

import java.util.ArrayList;
import java.util.List;

import com.tommy.myplamlibrary.api.AppConfig;
import com.tommy.myplamlibrary.entity.BasicInformation;
import com.tommy.myplamlibrary.entity.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

public class GetBasicInfo {
	
	Context context = null;
	String userId,password;
	SQLiteDatabase db = null;
	Handler handler = new Handler();
	
	public GetBasicInfo(Handler handler,Context context){
		
		this.context = context;
		this.handler = handler;
		db=context.openOrCreateDatabase(AppConfig.filePath, Context.MODE_PRIVATE, null);
		
	}
	
	public void getBasic() {
		
		Cursor c =  db.rawQuery("select * from tbl_basicInfo", null);
		
		Message msg = new Message();
		//List<BasicInformation> list = new ArrayList<BasicInformation>();
		while (c.moveToNext()) { 
			BasicInformation basic = new BasicInformation();
			if(c.getString(c.getColumnIndex("userId")).equals(AppConfig.UserId)) {
				basic.UserId = c.getString(c.getColumnIndex("userId"));
				basic.UserName = c.getString(c.getColumnIndex("userName"));
				basic.E_mail = c.getString(c.getColumnIndex("email"));
				basic.LendedNum = c.getString(c.getColumnIndex("lendedNum"));
				basic.Major = c.getString(c.getColumnIndex("major"));
				basic.Max = c.getString(c.getColumnIndex("max"));
				basic.Phone = c.getString(c.getColumnIndex("phone"));
				basic.Departments = c.getString(c.getColumnIndex("department"));
				msg.what = 1;
				msg.obj = basic;
				handler.sendMessage(msg);
				c.close();
				db.close();
				return;
			}
		}
		
		msg.what = 0;
		msg.obj = "获取失败";
		handler.sendMessage(msg);
		
		c.close();
		db.close();
		
		
	}
	

}
