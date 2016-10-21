package com.tommy.myplamlibrary.sqlliteutil;

import java.util.ArrayList;
import java.util.List;

import com.tommy.myplamlibrary.api.AppConfig;
import com.tommy.myplamlibrary.entity.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

public class ModifyPassword {
	
	
	Context context = null;
	String password,newPassword,newPasswordAgain;
	SQLiteDatabase db = null;
	Handler handler = new Handler();
	
	public ModifyPassword(Context context,Handler handler,String password,String newPassword,String newPasswordAgain) {
		this.context = context;
		this.handler = handler;
		this.password = password;
		this.newPassword = newPassword;
		this.newPasswordAgain = newPasswordAgain;
		db=context.openOrCreateDatabase(AppConfig.filePath, Context.MODE_PRIVATE, null);
	}
	
	
	public void modifyPassword() {
		
		Message msg = new Message();
		
		
		if(this.newPassword.equals(this.newPasswordAgain)) {
			
			if(passwordIsCorrect(password)){
				
				db.execSQL("update tbl_reader set password = ? where userId = ?", 
						new Object[]{newPassword,AppConfig.UserId});
				msg.what = 1;
				msg.obj = "修改密码成功";
				handler.sendMessage(msg);
			}
			else {
				msg.what = 0;
				msg.obj  ="密码不正确";
				handler.sendMessage(msg);
			}
		}else{
			msg.what  =0;
			msg.obj = "修改密码失败";
			handler.sendMessage(msg);
			
		}
	}
	
	private boolean passwordIsCorrect(String password) {
		
		Cursor c =  db.rawQuery("select * from tbl_reader", null);
		
		
		List<User> list = new ArrayList<User>();
		while (c.moveToNext()) {  
			User user  = new User();
	        user.UserId= c.getString(c.getColumnIndex("userId"));
	        user.UserName= c.getString(c.getColumnIndex("userName"));  
	        user.Password = c.getString(c.getColumnIndex("password"));  
	        list.add(user);
	        if(user.UserId.equals(AppConfig.UserId) && user.Password.equals(password)){
	    		return true;
	        }
	    }  
		return false;
	}
	
	
	

}
