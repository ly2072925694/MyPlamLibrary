package com.tommy.myplamlibrary.sqlliteutil;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;

import com.tommy.myplamlibrary.api.AppConfig;
import com.tommy.myplamlibrary.entity.Book;

public class Search {
	
	Context context = null;
	String key;
	SQLiteDatabase db = null;
	Handler handler = new Handler();

	public Search(Handler handler,Context context,String key){
		
		this.context = context;
		this.handler = handler;
		this.key = key;
		db=context.openOrCreateDatabase(AppConfig.filePath, Context.MODE_PRIVATE, null);
	}
	
	public void search(){
		
		Cursor c =  db.rawQuery("select * from tbl_book where bookName = ? or author = ? or publishCompany = ?", 
				new String[]{this.key,this.key,this.key});
		
		Message msg = new Message();
		
		List<Book> list = new ArrayList<Book>();
		
		while (c.moveToNext()) { 
			
			Book book = new Book();
			book.setAuthor(c.getString(c.getColumnIndex("author")));
			book.setBookName(c.getString(c.getColumnIndex("bookName")));
			book.setPublishCompany(c.getString(c.getColumnIndex("publishCompany")));
			list.add(book);
		}
		if(list.size() == 0) {
			msg.what = 0;
			msg.obj = "没有搜到数据";
			handler.sendMessage(msg);
		}else{
			msg.what = 1;
			msg.obj = list;
			handler.sendMessage(msg);
		}
		
	}
	
}
