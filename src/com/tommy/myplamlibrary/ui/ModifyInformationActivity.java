package com.tommy.myplamlibrary.ui;



import com.tommy.myplamlibrary.R;
import com.tommy.myplamlibrary.api.ApiClient;
import com.tommy.myplamlibrary.api.AppConfig;
import com.tommy.myplamlibrary.sqlliteutil.ModifyBasicInfo;
import com.tommy.myplamlibrary.util.PubUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyInformationActivity extends Activity implements OnClickListener{
	
	private EditText phoneEdit;
	private EditText emailEdit;
	private Button btnClearPhone;
	private Button btnClearEmail;
	private TextView txtSave;
	private ImageView imgBack;
	
	
	Handler myHandler = new Handler() {
		
		public void handleMessage(android.os.Message msg) {
			
			if(msg.what == 0){
				
				
				
				Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_LONG).show();
				
			}else{
				
				//Toast.makeText(getApplicationContext(), "�޸ĳɹ�", Toast.LENGTH_LONG).show();
				Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_LONG).show();
				
				
				
				
			}
			
		};
		
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_edit_data);
		initView();
		
		
		//ApiClient.modifyInformation(myHandler, 
				//AppConfig.UserId,phoneEdit.getText().toString() , 
				//emailEdit.getText().toString());
		new ModifyBasicInfo(this, myHandler, phoneEdit.getText().toString(), emailEdit.getText().
				toString()).modifyBasicInfo() ;
		
	}
	
	private void  initView() {
		
		phoneEdit = (EditText) findViewById(R.id.edit_number_phone);
		phoneEdit.addTextChangedListener(mTextWatcher);
		
		emailEdit = (EditText) findViewById(R.id.edit_number_email);
		emailEdit.addTextChangedListener(mTextWatcher);
		
		btnClearEmail = (Button) findViewById(R.id.button_clear_email);
		btnClearEmail.setOnClickListener(this);
		
		btnClearPhone = (Button) findViewById(R.id.button_clear_phone);
		btnClearPhone.setOnClickListener(this);
		
		txtSave = (TextView) findViewById(R.id.save_edit_data);
		txtSave.setOnClickListener(this);
		
		imgBack = (ImageView) findViewById(R.id.back_edit_data);
		imgBack.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.button_clear_email:
			
			emailEdit.setText("");
			
			break;
		case R.id.button_clear_phone:
			
			phoneEdit.setText("");
			
			break;
			
		case R.id.save_edit_data:
			
			new ModifyBasicInfo(ModifyInformationActivity.this, 
					myHandler, phoneEdit.getText().toString(), emailEdit.getText().toString()).modifyBasicInfo();
			
			//ApiClient.modifyInformation(myHandler, 
					//AppConfig.UserId, phoneEdit.getText().toString(), emailEdit.getText().toString());
			
			break;
		case R.id.back_edit_data:
			finish();
			//Intent intent = new Intent(ModifyInformationActivity.this, TabMainActivity.class);
			//intent.putExtra("tag", "user");
			//startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	TextWatcher mTextWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			
			if(!PubUtils.isEmpty(emailEdit.getText().toString())){
				
				btnClearEmail.setVisibility(View.VISIBLE);
				
			}else if(PubUtils.isEmpty(emailEdit.getText().toString())) {
				
				btnClearEmail.setVisibility(View.INVISIBLE);
				
			}
			if(!PubUtils.isEmpty(phoneEdit.getText().toString())) {
				
				btnClearPhone.setVisibility(View.VISIBLE);
				
			}else if(PubUtils.isEmpty(phoneEdit.getText().toString())) {
				
				btnClearPhone.setVisibility(View.INVISIBLE);
				
			}
			
		}
	};

	

}
