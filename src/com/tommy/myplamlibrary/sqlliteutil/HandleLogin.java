package com.tommy.myplamlibrary.sqlliteutil;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import com.tommy.myplamlibrary.api.AppConfig;
import com.tommy.myplamlibrary.entity.User;

public class HandleLogin {
	
	Context context = null;
	String userId,password;
	SQLiteDatabase db = null;
	Handler handler = new Handler();
	public HandleLogin(Handler handler, Context context,String userId,String password) {
		this.password = password;
		this.userId = userId;
		this.context = context;
		db=context.openOrCreateDatabase(AppConfig.filePath, Context.MODE_PRIVATE, null);
		this.handler = handler;
	}

	public void login() {
		Cursor c =  db.rawQuery("select * from tbl_reader", null);
		
		Message msg = new Message();
		List<User> list = new ArrayList<User>();
		while (c.moveToNext()) {  
			User user  = new User();
	        user.UserId= c.getString(c.getColumnIndex("userId"));
	        user.UserName= c.getString(c.getColumnIndex("userName"));  
	        user.Password = c.getString(c.getColumnIndex("password"));  
	        list.add(user);
	        if(user.UserId.equals( this.userId) && user.Password.equals(this.password)){
	    		AppConfig.LOGIN = 1;
	    		AppConfig.UserId = this.userId;
	    		AppConfig.UserName = c.getString(c.getColumnIndex("userName"));
	    		msg.what = 1;
	    		msg.obj = "登录成功";
	    		handler.sendMessage(msg);
	    		break;
	        }
	    }  
		if(AppConfig.LOGIN == -1){
			msg.what = 0;
			msg.obj = "用户名或密码错误";
			handler.sendMessage(msg);
		}
		
		c.close();
		db.close();
		
	}
	
	
}
