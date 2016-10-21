package com.tommy.myplamlibrary.sqlliteutil;

import java.util.ArrayList;
import java.util.List;

import com.tommy.myplamlibrary.api.AppConfig;
import com.tommy.myplamlibrary.entity.BasicInformation;
import com.tommy.myplamlibrary.entity.BorrowRecord;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

public class GetBorrowRecord {
	Context context = null;
	SQLiteDatabase db = null;
	Handler handler = new Handler();
	
	public GetBorrowRecord(Context context,Handler handler) {
		
		this.context = context;
		this.handler = handler;
		db=context.openOrCreateDatabase(AppConfig.filePath, Context.MODE_PRIVATE, null);
	}
	
	public void getBorrowRecord() {
		
		Cursor c =  db.rawQuery("select * from tbl_borrowRecord", null);
		Message msg = new Message();
		
		List<BorrowRecord> list = new ArrayList<BorrowRecord>();
		while (c.moveToNext()) { 
			BorrowRecord record = new BorrowRecord();
			
			if(c.getString(c.getColumnIndex("userId")).equals(AppConfig.UserId)) {
				record.BookName = c.getString(c.getColumnIndex("bookName"));
				record.BorrowTime = c.getString(c.getColumnIndex("borrowTime"));
				record.ReturnTime = c.getString(c.getColumnIndex("returnTime"));
				record.ShouldTime = c.getString(c.getColumnIndex("shouldTime"));
				
				list.add(record);
				
			}
		}
		
		
		if(list.size() == 0) {
			
			msg.what = 0;
			msg.obj = "没有数据";
			handler.sendMessage(msg);
			
		}else {
			msg.what = 1;
			msg.obj = list;
			handler.sendMessage(msg);
		}
		
		c.close();
		db.close();
	}
	
	

}
