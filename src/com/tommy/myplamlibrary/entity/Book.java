package com.tommy.myplamlibrary.entity;

public class Book {

	private String BookName;
	private String Author;
	private String PublishCompany;
	
	
	public Book() {
		
	}
	public Book(String bookName, String author , String publishCompany) {
		
		this.BookName = bookName;
		this.Author = author;
		this.PublishCompany = publishCompany;
		
	}
	
	
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getPublishCompany() {
		return PublishCompany;
	}
	public void setPublishCompany(String publishCompany) {
		PublishCompany = publishCompany;
	}
	
	
}
