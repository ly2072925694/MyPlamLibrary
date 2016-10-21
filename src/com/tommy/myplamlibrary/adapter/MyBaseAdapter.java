package com.tommy.myplamlibrary.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyBaseAdapter extends BaseAdapter{

	private List<?> dataList;//数据源，需要展示的数据
	private Context mcontext;//上下文相关
	private DealIitem dealItem;//dealItem
	
	public MyBaseAdapter(Context _context,List<?> _list, DealIitem _dealItem){
		
		this.mcontext = _context;
		this.dataList = _list;
		this.dealItem = _dealItem;
	}
	
	
	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		try{
			convertView = dealItem.DealItem(mcontext, dataList.get(position), position, convertView, parent);
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("------------------------>初始化出错");
		}
		return convertView;
	}

}
