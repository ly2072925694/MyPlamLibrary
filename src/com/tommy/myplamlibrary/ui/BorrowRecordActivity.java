package com.tommy.myplamlibrary.ui;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tommy.myplamlibrary.R;
import com.tommy.myplamlibrary.adapter.DealIitem;
import com.tommy.myplamlibrary.adapter.MyBaseAdapter;
import com.tommy.myplamlibrary.api.ApiClient;
import com.tommy.myplamlibrary.api.AppConfig;
import com.tommy.myplamlibrary.entity.BorrowRecord;
import com.tommy.myplamlibrary.entity.User;
import com.tommy.myplamlibrary.sqlliteutil.GetBorrowRecord;
import com.tommy.myplamlibrary.ui.BorrowRecordFragment.Holder;
import com.tommy.myplamlibrary.util.DialogUtil;
import com.tommy.myplamlibrary.util.JsonUtil;
import com.tommy.myplamlibrary.util.PubUtils;
import com.tommy.myplamlibrary.util.ReadFileUtil;

public class BorrowRecordActivity extends Activity implements OnClickListener{
	
	private ListView listView ;//�����ļ�¼��listView
	private ImageView back;
	
	private List<BorrowRecord> listBorrow = new ArrayList<BorrowRecord>();//���ļ�¼����
	
	private User user;//�û�����
	
	private MyBaseAdapter borrowAdapter;
	
	LayoutInflater layoutInflater = null;
	
	Dialog mDialog = null;
	
	AppConfig mAppConfig = null;
	
	protected int maxId;
	
	Handler myHandler = new Handler() {
		
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 0){
				Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_LONG).show();
			}else {
				listBorrow = (List<BorrowRecord>) msg.obj;
				creatBookAdapter();
				listView.setAdapter(borrowAdapter);
			}
		};
	};
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.borrow_record);
		initView();
		new GetBorrowRecord(BorrowRecordActivity.this, myHandler).getBorrowRecord();
		//ApiClient.getBorrowRecord(myHandler, AppConfig.UserId);
		
	}
	
	private void initView() {
		
		listView = (ListView) findViewById(R.id.list_borrow_activity);
		back = (ImageView) findViewById(R.id.back_borrow_record_activity);
		back.setOnClickListener(this);
		
		layoutInflater = getLayoutInflater();
		
		mDialog=DialogUtil.getLoadDialog(this, "加载中。。。");		
		mDialog.setCancelable(true);
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				myHandler.removeCallbacksAndMessages(null);
				mDialog.dismiss();
			}
		});
		
//		Message msg = new Message();
//		msg.what = 1;
//		String s =  ReadFileUtil.read(BorrowRecordActivity.this, "borrow_record.txt");
//		JSONObject obj = null;
//		try {
//			obj = new JSONObject(s);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			msg.obj = JsonUtil.parseToBorrowRecordList(obj.getJSONArray("list"));
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		myHandler.sendMessage(msg);
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.back_borrow_record_activity:
			
			this.finish();
			//Intent intent  = new Intent(BorrowRecordActivity.this, MainActivity.class);
			//startActivity(intent);
			break;
		default:
			break;
		}
		
	}
	
	
	public void creatBookAdapter() {
		
		borrowAdapter = new MyBaseAdapter(BorrowRecordActivity.this,listBorrow, new DealIitem() {
			
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
