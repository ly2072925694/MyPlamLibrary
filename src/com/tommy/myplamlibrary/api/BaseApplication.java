package com.tommy.myplamlibrary.api;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class BaseApplication extends Application {
	
	public static boolean isNetworkConnected(Context context){
		if(context != null){
			ConnectivityManager mConnectivityMannager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityMannager.getActiveNetworkInfo();
			if(mNetworkInfo != null){
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
	

}
