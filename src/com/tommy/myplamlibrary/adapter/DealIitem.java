package com.tommy.myplamlibrary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * 处理适配器的item
 * @author Tommy
 *
 */




public interface DealIitem {

	
	public View DealItem(Context context,Object obj,int position,View convertView,ViewGroup parent);

	/**
	 * context：
	 * obj:
	 * position:
	 * convertView:
	 * parent:
	 */
	
	
}
