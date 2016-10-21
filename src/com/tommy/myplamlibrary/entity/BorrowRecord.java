package com.tommy.myplamlibrary.entity;

public class BorrowRecord {
	
	public String BookName;
	public String BorrowTime;
	public String ReturnTime;
	public String ShouldTime;
	
	public BorrowRecord() {
		
	}
	public BorrowRecord(String bookName, String borrowTime, String returnTime,
			String shouldTime) {
		super();
		BookName = bookName;
		BorrowTime = borrowTime;
		ReturnTime = returnTime;
		this.ShouldTime = shouldTime;
	}
	public String getBookName() {
		return BookName;
	}
	
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getBorrowTime() {
		return BorrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		BorrowTime = borrowTime;
	}
	public String getReturnTime() {
		return ReturnTime;
	}
	public void setReturnTime(String returnTime) {
		ReturnTime = returnTime;
	}
	public String getShouldTime() {
		return ShouldTime;
	}
	public void setShouldTime(String shouldTime) {
		this.ShouldTime = shouldTime;
	}
}
