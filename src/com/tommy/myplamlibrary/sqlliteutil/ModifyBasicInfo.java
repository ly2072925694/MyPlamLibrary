package com.tommy.myplamlibrary.sqlliteutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

import com.tommy.myplamlibrary.api.AppConfig;

public class ModifyBasicInfo {
	Context context = null;
	String phone,email ;
	SQLiteDatabase db = null;
	Handler handler = new Handler();

	public ModifyBasicInfo(Context context,Handler handler,String phone,String email) {
		
		this.context = context;
		this.handler = handler;
		this.phone = phone;
		this.email = email;
		
		db=context.openOrCreateDatabase(AppConfig.filePath, Context.MODE_PRIVATE, null);
		
	}
	
	public void  modifyBasicInfo(){
		
		Message msg = new Message();
		
		
		try{
			
			String sql = "update tbl_basicInfo set phone = " + "'" + this.phone + "'" + " , email = '" + this.email + "'"
					+ " where userId = '" + AppConfig.UserId + "'";
			
			db.execSQL(sql);
			msg.what = 1;
			msg.obj = "修改成功";
			handler.sendMessage(msg);
		}catch(Exception e) {
			msg.what = 0;
			msg.obj = e.toString();
			handler.sendMessage(msg);
		}
		
		db.close();
		
	}
	
}
