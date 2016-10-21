package com.tommy.myplamlibrary.ui;


import com.tommy.myplamlibrary.R;
import com.tommy.myplamlibrary.api.ApiClient;
import com.tommy.myplamlibrary.api.AppConfig;
import com.tommy.myplamlibrary.sqlliteutil.ModifyPassword;
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

public class ModifyPasswordActivity extends Activity implements OnClickListener{
	
	private ImageView imgBack;//
	private TextView txtSave;//
	private EditText edtPassword;//
	private EditText edtNewPassword;//
	private EditText edtNewPasswordAgain;//
	private Button btnClearPassword;//
	private Button btnClearNewPassword;//
	private Button btnClearNewPasswordAgain;//
	
	Handler myHandler = new Handler() {
		
		public void handleMessage(android.os.Message msg) {
			
			if(msg.what ==0) {
				edtNewPasswordAgain.setText("");
				edtNewPassword.setText("");
				edtPassword.setText("");
				Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
			}
			
		};
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_password);
		initView();
	}
	
	private void  initView() {
		
		imgBack = (ImageView) findViewById(R.id.back_edit_password);
		
		imgBack.setOnClickListener(this);
		
		txtSave = (TextView) findViewById(R.id.save_edit_password);
		
		txtSave.setOnClickListener(this);
		
		edtPassword = (EditText) findViewById(R.id.edit_number_passwrod);
		
		edtNewPassword = (EditText) findViewById(R.id.edit_password_new);
		
		edtNewPasswordAgain = (EditText) findViewById(R.id.edit_password_again);
		
		btnClearPassword = (Button) findViewById(R.id.button_clear_passwrod);
		btnClearPassword.setOnClickListener(this);
		
		btnClearNewPassword = (Button) findViewById(R.id.button_clear_passwrod_new);
		btnClearNewPassword.setOnClickListener(this);
		
		btnClearNewPasswordAgain = (Button) findViewById(R.id.button_clear_passwrod_again);
		btnClearNewPasswordAgain.setOnClickListener(this);
		
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
		public void afterTextChanged(Editable arg0) {
			
			if(PubUtils.isEmpty(edtNewPassword.getText().toString())) {
				
				btnClearPassword.setVisibility(View.VISIBLE);
				
			}else if(PubUtils.isEmpty(edtPassword.getText().toString())) {
				
				btnClearPassword.setVisibility(View.VISIBLE);
				
				
			}
			
			if(!PubUtils.isEmpty(edtNewPassword.getText().toString())) {
				
				btnClearNewPassword.setVisibility(View.VISIBLE);
				
			}else if(PubUtils.isEmpty(edtNewPassword.getText().toString())) {
				
				btnClearNewPassword.setVisibility(View.INVISIBLE);
				
			}
			
			if(!PubUtils.isEmpty(edtNewPasswordAgain.getText().toString())) {
				
				btnClearNewPasswordAgain.setVisibility(View.INVISIBLE);
				
			}else if(PubUtils.isEmpty(edtNewPasswordAgain.getText().toString())) {
				
				btnClearNewPasswordAgain.setVisibility(View.INVISIBLE);
				
			}
			
		}
	};
	
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.back_edit_password:
			finish();
			break;

		case R.id.save_edit_password:
			
			new ModifyPassword(ModifyPasswordActivity.this, myHandler, 
					this.edtPassword.getText().toString(), this.edtNewPassword.getText().toString(), 
					edtNewPasswordAgain.getText().toString()).modifyPassword();
			
			//ApiClient.modifyPassword(myHandler, AppConfig.UserId, edtNewPasswordAgain.getText().toString());
			
			break;
		case R.id.button_clear_passwrod:
			edtPassword.setText("");
			
			break;
		case R.id.button_clear_passwrod_new:
			
			edtNewPassword.setText("");
			
			break;
		case R.id.button_clear_passwrod_again:
			
			edtNewPasswordAgain.setText("");
			
			break;
			
		default:
			break;
		}
		
	}
		

}
