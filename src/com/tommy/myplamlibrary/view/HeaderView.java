package com.tommy.myplamlibrary.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.tommy.myplamlibrary.R;
import com.tommy.myplamlibrary.ui.MainActivity;

public class HeaderView extends RelativeLayout implements OnClickListener{
	protected String title;
	protected boolean showBack;
	protected boolean showButton;
	protected TextView txtTitle;
	protected Button btnBack;
	protected Button btnEmpty;
	protected Button btnMessage;
	protected Context context;
	private Drawable btnDrawable;
	private Drawable backDrawable;
	public HeaderView(Context context) {
		super(context);
		this.context=context;
		initViews(null);
	}

	public HeaderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context=context;
		initViews(attrs);
	}

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		initViews(attrs);
	}
	@SuppressWarnings("deprecation")
	protected void initViews(AttributeSet attrs) {
		TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.header, 0, 0);
		View header = LayoutInflater.from(getContext()).inflate(R.layout.view_header, this);
		txtTitle=(TextView) header.findViewById(R.id.header_txt_title);
		btnBack=(Button) header.findViewById(R.id.header_btn_back);
		//btnEmpty=(Button) header.findViewById(R.id.header_btn_empty);
		btnMessage=(Button) header.findViewById(R.id.header_btn_message);
		if (arr != null) {
			title = arr.getString(R.styleable.header_header_title);
			showBack=arr.getBoolean(R.styleable.header_show_back,true);
			showButton=arr.getBoolean(R.styleable.header_show_button,false);
			btnDrawable=arr.getDrawable(R.styleable.header_button_bg);
			backDrawable=arr.getDrawable(R.styleable.header_back_icon);
			txtTitle.setText(title==null?"":title);
			if(!showBack){
				//btnEmpty.setVisibility(View.GONE);
				btnBack.setVisibility(View.GONE);
			}
			if(backDrawable != null){
				btnBack.setBackgroundDrawable(backDrawable);
			}
			if(showButton){
				btnMessage.setVisibility(View.VISIBLE);
				if(btnDrawable!=null){
					btnMessage.setBackgroundDrawable(btnDrawable);
				}
			}
			arr.recycle();
        }
		
		btnBack.setOnClickListener(this);
		//btnEmpty.setOnClickListener(this);
		btnMessage.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.header_btn_back:
			if(backDrawable == null || backDrawable == 
			this.getResources().getDrawable(R.drawable.back_p)){
				((Activity)context).finish();
			}
			break;
		default:
			break;
		}
		
	}
}
