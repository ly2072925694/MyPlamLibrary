package com.tommy.myplamlibrary.ui;


import com.tommy.myplamlibrary.R;
import com.tommy.myplamlibrary.api.ApiClient;
import com.tommy.myplamlibrary.api.AppConfig;
import com.tommy.myplamlibrary.sqlliteutil.HandleLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{

	private EditText edit_userid;
	private EditText edit_userpwd;
	private RelativeLayout layout;
	private Button bt_submit;
	private String Tag;
	
	Handler myHandler = new Handler() {
		
		public void handleMessage(android.os.Message msg) {
			
			if(msg.what == 1){
				
				AppConfig.LOGIN = 1;
	    		AppConfig.UserId = edit_userid.getText().toString();
				
				Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_LONG).show();
//				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//				startActivity(intent);
				finish();
			}else{
				Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_LONG).show();
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		initView();
	}
	
	private void initView() {
		
		edit_userid = (EditText) findViewById(R.id.edit_userid);
		
		edit_userpwd = (EditText) findViewById(R.id.edit_password);
		
		bt_submit = (Button) findViewById(R.id.login_button);
		
		bt_submit.setOnClickListener(this);
		
		layout = (RelativeLayout) findViewById(R.id.layout_back);
		
		layout.setOnClickListener(this);
		
		
	}
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.login_button:
			//ApiClient.login(myHandler, edit_userid.getText().toString(), edit_userpwd.getText().toString());
			
			new HandleLogin(myHandler,LoginActivity.this, edit_userid.getText().toString(), edit_userpwd.getText().toString()).login();
			break;
			
		case R.id.layout_back:
			
			finish();
			
			break;

		default:
			break;
		}
		
		
	}
	
	

}
