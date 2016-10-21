package com.tommy.myplamlibrary.util;

import com.tommy.myplamlibrary.entity.BasicInformation;
import com.tommy.myplamlibrary.entity.Book;
import com.tommy.myplamlibrary.entity.BorrowRecord;
import com.tommy.myplamlibrary.entity.User;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CreateSql {
	private Context context = null;
	public CreateSql(Context context ){
		this.context = context;
		init();
	}
	
	private void init() {
		SQLiteDatabase db=context.openOrCreateDatabase("/sdcard/library.db", Context.MODE_PRIVATE, null);
	    //创建person表  
		db.execSQL("CREATE TABLE user " +
				"(id VARCHAR PRIMARY KEY," +
				" name VARCHAR" +
				", passWord VARCHAR)"); 
		db.execSQL("CREATE TABLE book " +
				"(id INTEGER PRIMARY KEY AUTOINCREMENT" +
				", author VACHAR" +
				", publishCompany VACHAR," +
				" bookName VACHAR)"); 
		db.execSQL("CREATE TABLE basic " +
				"(userId VARCHAR PRIMARY KEY ," +
				" UserName VARCHAR" +
				", Major VARCHAR" +
				",Departments VARCHAR" +
				",Phone VARCHAR" +
				",E_mail VARCHAR" +
				",Max VARCHAR" +
				",LendedNum VARCHAR)"); 
		db.execSQL("CREATE TABLE borrow " +
				"(userId VARCHAR" +
				",BookName VARCHAR" +
				", BorrowTime VARCHAR" +
				", ReturnTime VARCHAR" +
				",ShouldTime,VARCHAR)"); 
		for(int i=0;i<5;i++){
		    //插入数据  
		      
			BasicInformation person = new BasicInformation();  
		    person.Departments = "john"+i;  
		    person.E_mail = "john"+i; 
		    person.LendedNum="john"+i;
		    person.Major="john"+i;
		    person.Max="john"+i;
		    person.Phone="john"+i;
		    person.UserId="john"+i;
		    person.UserName="john"+i;
		    db.execSQL("INSERT INTO basic VALUES (?, ?, ?, ?, ?, ?, ?, ?)", 
		    		new Object[]{
		    		person.UserId , 
				    person.UserName,
				    person.Major,
				    person.Departments,
				    person.Phone,
				    person.E_mail ,
				    person.Max,
				    person.LendedNum});  
		}
		for(int i=0;i<5;i++){
		    //插入数据  
		      
			Book person = new Book();  
		    person.setAuthor("book"+i);  
		    person.setBookName("book"+i);  
		    person.setPublishCompany("book"+i);  
		    
		    db.execSQL("INSERT INTO book VALUES (?, ?, ?)", 
		    		new Object[]{
		    		person.getAuthor() , 
				    person.getPublishCompany(), 
				    person.getBookName() });  
		} 
		for(int i=0;i<5;i++){
		    //插入数据  
		      
			User person = new User();  
		    person.UserId="user"+i;  
		    person.UserName=("user"+i);  
		    person.Password=("user"+i);  
		    
		    db.execSQL("INSERT INTO user VALUES (?, ?, ?)", 
		    		new Object[]{
		    		person.UserId ,  
				    person.UserName ,
				    person.Password});  
		} 
		for(int i=0;i<5;i++){
		    //插入数据  
		      
			BorrowRecord person = new BorrowRecord();  
		    person.BookName="borrow"+i;  
		    person.BorrowTime=("borrow"+i);  
		    person.ReturnTime=("borrow"+i);
		    person.ShouldTime="borrow"+i;  
		    
		    db.execSQL("INSERT INTO book VALUES (?, ?, ?,?,?)", 
		    		new Object[]{
		    		"user"+i,
		    		person.BookName ,  
				    person.BorrowTime ,
				    person.ReturnTime,
				    person.ShouldTime});  
		} 
		
//	    //更新数据  
//	    db.update("person", cv, "name = ?", new String[]{"john"});  
//	      
//	    Cursor c = db.rawQuery("SELECT * FROM person WHERE age >= ?", new String[]{"33"});  
//	    while (c.moveToNext()) {  
//	        int _id = c.getInt(c.getColumnIndex("_id"));  
//	        String name = c.getString(c.getColumnIndex("name"));  
//	        int age = c.getInt(c.getColumnIndex("age"));  
//	        Log.i("db", "_id=>" + _id + ", name=>" + name + ", age=>" + age);  
//	    }  
//	    c.close();  

		
//	    //关闭当前数据库  
//	    db.close();  
	}
	
	

}
