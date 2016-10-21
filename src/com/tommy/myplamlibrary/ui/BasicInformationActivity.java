package com.tommy.myplamlibrary.ui;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.tommy.myplamlibrary.R;
import com.tommy.myplamlibrary.api.ApiClient;
import com.tommy.myplamlibrary.api.AppConfig;
import com.tommy.myplamlibrary.entity.BasicInformation;
import com.tommy.myplamlibrary.sqlliteutil.GetBasicInfo;
import com.tommy.myplamlibrary.util.JsonUtil;
import com.tommy.myplamlibrary.util.ReadFileUtil;

public class BasicInformationActivity extends Activity implements OnClickListener{
	
	private ScrollView layoutBasic;//
	private TextView txtUserName;//
	private TextView txtUserId;//
	private TextView txtProfessional;//
	private TextView txtDepartment;//
	private TextView txtPhone;///
	private TextView txtEmail;//
	private TextView txtMax;//
	private TextView txtBorrow;//
	private ImageView imgBack;
	
	
	@SuppressLint("HandlerLeak")
	Handler myHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			
			if(msg.what == 0) {
				
				Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_LONG).show();
				
			}else{
				
				BasicInformation  basicModel = new BasicInformation();
				
				basicModel = (BasicInformation) msg.obj;
				
				txtEmail.setText(basicModel.getE_mail());
				
				txtBorrow.setText(basicModel.getLendedNum());
				
				txtDepartment.setText(basicModel.getDepartments());
				
				txtMax.setText(basicModel.getMax());
				
				txtPhone.setText(basicModel.getPhone());
				
				txtProfessional.setText(basicModel.getMajor());
				
				txtUserId.setText(basicModel.getUserId());
				
				txtUserName.setText(basicModel.getUserName());
				
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_basic_information);
		initView();
		//ApiClient.getBasicInformation(myHandler, AppConfig.UserId);
		new GetBasicInfo(myHandler, this).getBasic();
	}
	
	private  void initView(){
		
		layoutBasic = (ScrollView) findViewById(R.id.layout_basic);
		
		imgBack = (ImageView) findViewById(R.id.back_basic_information);
		imgBack.setOnClickListener(this);
		
		txtEmail = (TextView) findViewById(R.id.txt_email_basic_y);
		
		txtMax =(TextView) findViewById(R.id.txt_max_basic);
		
		txtPhone = (TextView) findViewById(R.id.txt_phone_num_basic);
		
		txtProfessional = (TextView) findViewById(R.id.txt_professional_basic_y);
		
		txtDepartment = (TextView) findViewById(R.id.txt_department_basic_y);
		
		txtUserId = (TextView) findViewById(R.id.txt_userid_basic);
		
		txtUserName = (TextView) findViewById(R.id.txt_username_basic);
		
		txtBorrow = (TextView) findViewById(R.id.txt_borrow_basic);
		
		
//		Message msg = new Message();
//		msg.what = 1;
//		msg.obj = JsonUtil.parseToBasicInfoList( ReadFileUtil.read(BasicInformationActivity.this, "basicInfo.txt") );
//		myHandler.sendMessage(msg);
		
		
		
		//new GetBasicInfo(myHandler, BasicInformationActivity.this).getBasic();
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.back_basic_information:
			finish();
			break;
		
		default:
			break;
		}
		
		
	}

}
