package com.tommy.myplamlibrary.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import com.tommy.myplamlibrary.R;
import com.tommy.myplamlibrary.adapter.DealIitem;
import com.tommy.myplamlibrary.adapter.MyBaseAdapter;
import com.tommy.myplamlibrary.entity.Book;
import com.tommy.myplamlibrary.sqlliteutil.Search;

public class BookSearchActivity extends Activity implements OnItemClickListener,OnClickListener{
	
	
	private ImageView imgSearch;
	private EditText edtMessage;
	//private BookSearchPresenter bookPresenter;
	private ImageView imgBack;
	private ListView listView;
	//private List<BookModel> listBooks = new ArrayList<BookModel>();
	//private MyBaseAdapter bookAdapter;
	
	private List<Book> listBooks = new ArrayList<Book>();
	
	private MyBaseAdapter bookAdapter;
	
	Handler myHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if(msg.what ==0) {
				
				
				Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_LONG).show();
				
			}else {
				
				listBooks = (List<Book>) msg.obj;
				
				creatBookAdapter();
				
				listView.setAdapter(bookAdapter);
				
			}
			
			
			
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_search);
		
		initView();
		
	}
	
	private void initView() {
		
		imgSearch = (ImageView) findViewById(R.id.img_search);
		imgSearch.setOnClickListener((android.view.View.OnClickListener) this);
		
		edtMessage = (EditText) findViewById(R.id.edt_msg);
		
		imgBack = (ImageView) findViewById(R.id.img_back);
		imgBack.setOnClickListener(this);
		
		listView = (ListView) findViewById(R.id.library_list);
		listView.setOnItemClickListener(this);
		
	}
	

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.img_search:
			
			new Search(myHandler,BookSearchActivity.this,this.edtMessage.getText().toString()).search();
			
			break;
		case R.id.img_back:
			finish();
			//Intent intent = new Intent (BookSearchActivity.this, MainActivity.class);
			//startActivity(intent);

		default:
			break;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	}
	
	
	public void creatBookAdapter() {
		
		bookAdapter = new MyBaseAdapter(getApplicationContext(), listBooks, new DealIitem() {
			
			@Override
			public View DealItem(Context context, Object obj, int position,
					View convertView, ViewGroup parent) {
				Holder holder = null;
				if(convertView == null) {
					
					convertView = getLayoutInflater().inflate(R.layout.library_book, null);
					holder = new Holder();
					holder.tvName = (TextView) convertView.findViewById(R.id.txt_book_name);
					holder.tvWriter = (TextView) convertView.findViewById(R.id.txt_author);
					holder.tvPublish = (TextView) convertView.findViewById(R.id.txt_publish);
					
					holder.tvName.setText(listBooks.get(position).getBookName());
					holder.tvWriter.setText(listBooks.get(position).getAuthor());
					holder.tvPublish.setText(listBooks.get(position).getPublishCompany());
				}else {
					
					holder = (Holder) convertView.getTag();
				}
				
				return convertView;
			}
		}); 
			
		
	}
	
	
	public class Holder{
		public TextView tvName;
		public TextView tvWriter;
		public TextView tvPublish;
	}

}
