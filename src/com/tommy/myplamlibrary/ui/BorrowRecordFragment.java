package com.tommy.myplamlibrary.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tommy.myplamlibrary.R;
import com.tommy.myplamlibrary.adapter.DealIitem;
import com.tommy.myplamlibrary.adapter.MyBaseAdapter;
import com.tommy.myplamlibrary.api.ApiClient;
import com.tommy.myplamlibrary.api.AppConfig;
import com.tommy.myplamlibrary.api.ResultMessage;
import com.tommy.myplamlibrary.entity.BorrowRecord;
import com.tommy.myplamlibrary.entity.User;
import com.tommy.myplamlibrary.sqlliteutil.GetBorrowRecord;
import com.tommy.myplamlibrary.util.DialogUtil;
import com.tommy.myplamlibrary.util.JsonUtil;
import com.tommy.myplamlibrary.util.PubUtils;

public class BorrowRecordFragment extends Fragment implements OnClickListener{

	
	View fragmentBorrowRecord = null;
	
	private ListView listView ;
	
	private List<BorrowRecord> listBorrow = new ArrayList<BorrowRecord>();
	
	private User user;
	
	private MyBaseAdapter borrowAdapter;
	
	LayoutInflater layoutInflater = null;
	
	Dialog mDialog = null;
	
	AppConfig mAppConfig = null;
	
	protected int maxId;
	
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			if(getActivity()==null){
				return;
			}
			if(mDialog.isShowing())
				mDialog.dismiss();
			switch (msg.what) {
			case ResultMessage.GET_BORROWRECORD_LIST_SUCCESS:
				
				listBorrow = (List<BorrowRecord>) msg.obj;
				
				if (listBorrow.size() != 0) {
					creatBookAdapter();
					listView.setAdapter(borrowAdapter);
					
				} else {
					Toast.makeText(getActivity(), "没有记录", Toast.LENGTH_SHORT).show();
				}
				break;
			case ResultMessage.GET_BORROWRECORD_LIST_FAILED:
				Toast.makeText(getActivity(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
				break;
			case ResultMessage.TIMEOUT:
				Toast.makeText(getActivity(), "连接超时", Toast.LENGTH_SHORT).show();
				break;
			
			default:
				break;
			}
		
		};
	};
	
	Handler myHandler = new Handler() {
		
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 0){
				Toast.makeText(getActivity().getApplicationContext(), msg.obj.toString(), Toast.LENGTH_LONG).show();
				
			}else {
				listBorrow = (List<BorrowRecord>) msg.obj;
				creatBookAdapter();
				listView.setAdapter(borrowAdapter);
			}
		};
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		
		fragmentBorrowRecord =  inflater.inflate(R.layout.fragment_borrow_record,container,false);
		
		this.layoutInflater = inflater;
		
		initView();
		//ApiClient.getBorrowRecord(mHandler, mAppConfig.UserId);
		
		return fragmentBorrowRecord;
		
	}
	
	
	private void initView() {
		
		listView = (ListView) fragmentBorrowRecord.findViewById(R.id.list_borrow_fragment);
		
		mDialog=DialogUtil.getLoadDialog(getActivity(), "查询中");		
		mDialog.setCancelable(true);
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				myHandler.removeCallbacksAndMessages(null);
				mDialog.dismiss();
			}
		});
		
		new GetBorrowRecord(getActivity(), myHandler).getBorrowRecord();
		
		///ApiClient.getBorrowRecord(myHandler, AppConfig.UserId);
		
		
//		try {
//			InputStreamReader isr = null;
//			try {
//				isr = new InputStreamReader(getActivity().getAssets().open("test.json"),"UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			BufferedReader br = new BufferedReader(isr);
//			String line;
//			StringBuilder builder = new  StringBuilder();
//			try {
//				while((line = br.readLine()) != null) {
//					builder.append(line);
//					
//				}
//				br.close();
//				isr.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			
////			JSONObject root = new JSONObject(builder.toString());
////			System.out.println(builder.toString());
////			listBorrow = JsonUtil.parseToBorrowRecordList(root.getJSONArray("borrowRecord")) ;
////			System.out.println("cat=" + root.getString("cat"));
////			JSONArray array = root.getJSONArray("borrowRecord");
////			for(int i=0 ;i<array.length();i++) {
////				
////				JSONObject lan = array.getJSONObject(i);
////				System.out.println("----------------------");
////				System.out.println("BookName=" + lan.getString("BookName"));
////				System.out.println("BorrowTime=" + lan.getString("BorrowTime"));
////				System.out.println("ReturnTime=" + lan.getString("ReturnTime"));
////				System.out.println("ShouldTime=" + lan.getString("ShouldTime"));
////				
////			}
////			
//			
//			
////			Message msg = new Message();
////			msg.what = 1;
////			msg.obj = listBorrow;
////			myHandler.sendMessage(msg);
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		listBorrow.add(b1);
//		listBorrow.add(b2);
//		listBorrow.add(b3);
//		listBorrow.add(b4);
//		
//		Message msg = new Message();
//		msg.what = 1;
//		msg.obj = listBorrow;
//		myHandler.sendMessage(msg);
		
	}
	
	
	@Override
	public void onClick(View arg0) {
	
	}
	
	
	public void creatBookAdapter() {
		
		borrowAdapter = new MyBaseAdapter(getActivity().getApplicationContext(),listBorrow, new DealIitem() {
			
			@Override
			public View DealItem(Context context, Object obj, int position,
					View convertView, ViewGroup parent) {
				
				Holder holder = null;
				
				if(convertView == null) {
					
					convertView = layoutInflater.inflate(R.layout.borrow_record_item, null);
					holder = new Holder();
					holder.tvName = (TextView) convertView.findViewById(R.id.txt_bookname_record);
					holder.imgTime = (ImageView) convertView.findViewById(R.id.img_lefttime);
					holder.tvBorrowTime = (TextView) convertView.findViewById(R.id.txt_borrowdate_record);
					holder.tvMaturtyTime = (TextView) convertView.findViewById(R.id.txt_maturitydate_record);
					
					
					holder.tvName.setText(listBorrow.get(position).getBookName());
					holder.tvBorrowTime.setText(listBorrow.get(position).getBorrowTime());
					
					if(PubUtils.isEmpty(listBorrow.get(position).getReturnTime())) {
						
						holder.tvMaturtyTime.setText(listBorrow.get(position).getShouldTime());
						holder.imgTime.setBackgroundResource(R.drawable.wait_return);
						
						
					}else {
						
						holder.tvMaturtyTime.setText(listBorrow.get(position).getReturnTime());
						holder.imgTime.setBackgroundResource(R.drawable.return_new);
						
					}
				}else {
					holder =  (Holder) convertView.getTag();
				}
				
				
				return convertView;
			}
		});
		
	}
	
	public class Holder {
		
		public TextView tvName;
		public ImageView imgTime;
		public TextView tvBorrowTime;
		public TextView tvMaturtyTime;
	}
	
	
	
		
}
