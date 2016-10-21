package com.tommy.myplamlibrary.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.os.Handler;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tommy.myplamlibrary.entity.BasicInformation;
import com.tommy.myplamlibrary.entity.BorrowRecord;


public class ApiClient {
	
	protected static final boolean DEBUG = true;
	private static AsyncHttpClient mClient=new AsyncHttpClient();

	/*
	 * 获得借阅记录
	 */
	public static void getBorrowRecord(final Handler handler,String userId) {
		
		String url = "http://10.0.2.2:8080/LibraryAPI/getBorrowRecordServlet?";
		
		RequestParams params = new RequestParams();
		params.put("userId", userId);
		
		//System.out.println("+++Id"+userId+"   );
		mClient.post( url, params,

				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						// 获取返回的字符串
						String result = new String(responseBody);
						
						List<BorrowRecord> list = new ArrayList<BorrowRecord>();
						
						String[] objs = result.split(";");
						for(int i =0;i<objs.length;i++ ) {
							
							String[] items = objs[i].split(",");
							
							BorrowRecord borrowRecord = new BorrowRecord();
							borrowRecord.BookName = items[0];
							borrowRecord.BorrowTime = items[1];
							borrowRecord.ReturnTime = items[2];
							borrowRecord.ShouldTime = items[3];
							list.add(borrowRecord);
						}
						
						handler.obtainMessage(1, list).sendToTarget();
						
						
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						if(error!=null){
							System.out.println("error.getMessage-->>"+error.getMessage());
						}
						handler.obtainMessage(0, "网络错误").sendToTarget();
					}
				});
	}
	
	/**
	 * 获得基本信息
	 * @param handler
	 * @param userId
	 */
	public static void getBasicInformation(final Handler handler,String userId) {
		
		String url = "http://10.0.2.2:8080/LibraryAPI/getBasicInfoServlet?";
		
		RequestParams params = new RequestParams();
		params.put("userId", userId);
		
		mClient.post(url, params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				
				String result = new String(responseBody);
				
				BasicInformation basic = new BasicInformation();
				String[] arr =  result.split(",");
				basic.UserName = arr[0];
				basic.UserId = arr[1];
				basic.Phone = arr[2];
				basic.Major = arr[3];
				basic.Departments = arr[4];
				basic.E_mail =arr[5];
				basic.Max = arr[6];
				basic.LendedNum = arr[7];
				
				handler.obtainMessage(1, basic).sendToTarget();
				
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				
				if(error!=null){
					System.out.println("error.getMessage-->>"+error.getMessage());
				}
				handler.obtainMessage(0, "网络错误").sendToTarget();
				
			}
		}); 
	}
	/**
	 * 修改基本信息
	 * @param handler
	 * @param userId
	 * @param phone
	 * @param email
	 */
	public static void modifyInformation(final Handler handler,String userId,String phone,String email) {
		
		String url = "http://10.0.2.2:8080/LibraryAPI/modifyBasicInfoServlet?";
		
		RequestParams params = new RequestParams();
		params.put("userId", userId);
		params.put("phone", phone);
		params.put("email",email);
		
		mClient.post(url, params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				
				String result = new String(responseBody);
				handler.obtainMessage(1, result).sendToTarget();
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				
				if(error!=null){
					System.out.println("error.getMessage-->>"+error.getMessage());
				}
				handler.obtainMessage(0, "网络错误").sendToTarget();
			}
		});
		
	}
	/**
	 * 修改密码
	 * @param handler
	 * @param userId
	 * @param newPwd
	 */
	public static void modifyPassword(final Handler handler,String userId,String newPassword){
		
		String url = "http://10.0.2.2:8080/LibraryAPI/modifyPasswordServlet?";
		
		RequestParams params = new RequestParams();
		params.put("userId",userId);	
		params.put("newPassword", newPassword);	
		
		
		mClient.post(url, params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				
				String result = new String(responseBody);
				handler.obtainMessage(1, result).sendToTarget();
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				if(error!=null){
					System.out.println("error.getMessage-->>"+error.getMessage());
				}
				handler.obtainMessage(0, "网络错误").sendToTarget();
				
			}
		});
	}
	
	/**
	 * 用户登录
	 */
	public static void login(final Handler handler,String userId,String password) {
		
		String url = "http://10.0.2.2:8080/LibraryAPI/loginServlet?";
		
		RequestParams params = new RequestParams();
		params.put("userId", userId);
		params.put("password", password);
		//params.put("El", "15683214816");
		
		mClient.post(url, params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				String s = new String(responseBody);
				handler.obtainMessage(1, s).sendToTarget();
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				if(error!=null){
					System.out.println("error.getMessage-->>"+error.getMessage());
				}
				handler.obtainMessage(0, "网络错误").sendToTarget();
			}
		});
	}
}
