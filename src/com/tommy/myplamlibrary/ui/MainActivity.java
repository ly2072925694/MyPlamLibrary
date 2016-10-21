package com.tommy.myplamlibrary.ui;

import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tommy.myplamlibrary.R;
import com.tommy.myplamlibrary.api.AppConfig;
import com.tommy.myplamlibrary.api.BaseApplication;

public class MainActivity extends FragmentActivity implements OnClickListener{

	SlidingMenu menu = null;
	Button btnOpenSliding = null;
	
	HomeFragment mHomeFragment = null;
	BorrowRecordFragment mBorrowRecordFragment = null;
	
	View mHomeLayout = null;
	View mBorrowRecordLayout = null;
	
	private ImageView mHomeImage = null;
	private ImageView mBorrowRecordImage = null;
	
	private TextView mHomeText;
	private TextView mBorrowRecordText;
	
	private FragmentManager fragmentManager;
	private static boolean isExit = false;
	
	private TextView borrowNum = null;
	private TextView userName = null;
	
	private static Handler mHandler = new Handler() { 
		   
        @Override 
        public void handleMessage(Message msg) { 
        	super.handleMessage(msg); 
            isExit = false; 
        } 
    }; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		if(BaseApplication.isNetworkConnected(this)) {
			setContentView(R.layout.activity_main);
		}else {
			setContentView(R.layout.activity_main);
			Toast.makeText(getApplicationContext(), "网络连接错误", Toast.LENGTH_LONG).show();
		}
		initView();
		
		fragmentManager = getSupportFragmentManager();
		setTabSelection(0);
	}
	
	void initSlidingMenu(){
		menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);  
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);  
        menu.setShadowWidthRes(R.dimen.shadow_width);  
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);  
        menu.setFadeDegree(0.35f);  
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);  
        menu.setMenu(R.layout.activity_left_sliding);
        
        addEventForSliding();
        
	}
	
	void initView(){
		initSlidingMenu();
		
		btnOpenSliding = (Button) findViewById(R.id.header_btn_back);
		btnOpenSliding.setOnClickListener(this);
		
		mHomeLayout = findViewById(R.id.product_layout);
		mBorrowRecordLayout = findViewById(R.id.me_layout);
		
		mHomeImage = (ImageView) findViewById(R.id.product_image);
		mBorrowRecordImage = (ImageView) findViewById(R.id.me_image);
		
		mHomeText = (TextView) findViewById(R.id.product_text);
		mBorrowRecordText = (TextView) findViewById(R.id.me_text);
		
		mHomeLayout.setOnClickListener(this);
		mBorrowRecordLayout.setOnClickListener(this);
		
		borrowNum = (TextView) findViewById(R.id.borrow_num);
		userName = (TextView) findViewById(R.id.txt_username_person);
		
	}
	
	private void setTabSelection(int index) {
		clearSelection();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		hideFragments(transaction);
		
		switch (index) {
		case 0:
			mHomeImage.setImageResource(R.drawable.ic_main_home);
			mHomeText.setTextColor(getResources().getColor(R.color.blue));
			if (mHomeFragment == null) {
				mHomeFragment = new HomeFragment();
				transaction.add(R.id.content, mHomeFragment);
			} else {
				transaction.show(mHomeFragment);
			}
			break;
		case 1:
			mBorrowRecordImage.setImageResource(R.drawable.ic_main_record);
			mBorrowRecordText.setTextColor(getResources().getColor(R.color.blue));
			if (mBorrowRecordFragment == null) {
				mBorrowRecordFragment = new BorrowRecordFragment();
				transaction.add(R.id.content, mBorrowRecordFragment);
			} else {
				transaction.show(mBorrowRecordFragment);
			}
			break;
		}
		transaction.commit();
	}
	
	private void clearSelection() {
		
		mHomeImage.setImageResource(R.drawable.ic_main_home);
		mHomeText.setTextColor(Color.parseColor("#D6D6D6"));
		mBorrowRecordImage.setImageResource(R.drawable.ic_main_record);
		mBorrowRecordText.setTextColor(Color.parseColor("#D6D6D6"));
	}
	
	private void hideFragments(FragmentTransaction transaction) {
		List<Fragment> fragmentList=fragmentManager.getFragments();
		if(fragmentList!=null){
			for(Fragment f :fragmentList){
				transaction.hide(f);
			}
		}
		if (mHomeFragment != null) {
			transaction.hide(mHomeFragment);
		}
		
		if (mBorrowRecordFragment != null) {
			transaction.hide(mBorrowRecordFragment);
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.header_btn_back:
			this.menu.showMenu();
			break;
		
		case R.id.product_layout:
			setTabSelection(0);
			break;
		case R.id.me_layout:
			if(AppConfig.LOGIN == -1) {
				Intent intent = new Intent(MainActivity.this,LoginActivity.class);
				startActivity(intent);
			}else{
				setTabSelection(1);
			}
			break;
		default:
			break;
		}
	}
	
	void addEventForSliding(){
		RelativeLayout borrowRecordLayout ,basicNewsLayout,editDataLayout,resetPasswordLayout;
		
		borrowRecordLayout = (RelativeLayout) findViewById(R.id.borrow_record);
		basicNewsLayout = (RelativeLayout) findViewById(R.id.basic_news);
		editDataLayout = (RelativeLayout) findViewById(R.id.edit_data);
		resetPasswordLayout = (RelativeLayout) findViewById(R.id.reset_password);
		
		borrowRecordLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(AppConfig.LOGIN == 1) {
					Intent intent = new Intent(MainActivity.this,BorrowRecordActivity.class);
					startActivity(intent);
				}else {
					Intent intent = new Intent(MainActivity.this,LoginActivity.class);
					startActivity(intent);
				}
				
			}
		});
		basicNewsLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(AppConfig.LOGIN == 1) {
					Intent intent = new Intent(MainActivity.this,BasicInformationActivity.class);
					startActivity(intent);
				}else {
					Intent intent = new Intent(MainActivity.this,LoginActivity.class);
					startActivity(intent);
				}
			}
		});
		resetPasswordLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				if(AppConfig.LOGIN == 1) {
					Intent intent = new Intent(MainActivity.this,ModifyPasswordActivity.class);
					startActivity(intent);
				}else {
					Intent intent = new Intent(MainActivity.this,LoginActivity.class);
					startActivity(intent);
				}
				
			}
		});
		editDataLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(AppConfig.LOGIN == 1) {
					Intent intent = new Intent(MainActivity.this,ModifyInformationActivity.class);
					startActivity(intent);
				}else {
					Intent intent = new Intent(MainActivity.this,LoginActivity.class);
					startActivity(intent);
				}
			}
		});
	}
	
	
	@Override 
    public boolean onKeyDown(int keyCode, KeyEvent event) { 
        if (keyCode == KeyEvent.KEYCODE_BACK) { 
            exit(); 
            return true; 
        } 
        return super.onKeyDown(keyCode, event); 
    } 
    
    private void exit() { 
        if (!isExit) { 
            isExit = true; 
            Toast.makeText(getApplicationContext(), "再按一次退出", 
                    Toast.LENGTH_SHORT).show(); 
            mHandler.sendEmptyMessageDelayed(0, 2000); 
        } else {              
            this.finish(); 
        } 
    } 
	
}
