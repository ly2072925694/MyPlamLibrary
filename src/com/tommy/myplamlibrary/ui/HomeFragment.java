package com.tommy.myplamlibrary.ui;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tommy.myplamlibrary.R;
import com.tommy.myplamlibrary.api.BaseApplication;

public class HomeFragment extends Fragment implements OnClickListener,OnPageChangeListener{

	
	View fragmentHome = null;
	
	private int imagesIds[];//ͼƬID
	private String[] titles;//��ʾͼƬ�ϵ���
	private ArrayList<ImageView> images;//ͼ��ؼ�����
	private ArrayList<View> dots;//ѭ������ͼƬ�ϵĵ������
	private TextView title,txtScroll;//ͼ����⣬����
	private ViewPager mViewPager;//����ѭ����ʾͼƬ
	private PagerAdapter adapter;//ѭ����ʾ��������
	private int oldPosition = 0 ;//��¼��һ�ε�ļ�¼
	private int currentItem = 0;//��ǰҳ��
	
	private RelativeLayout searchLayout;//����ͼ��Ĳ���
	
	private TextView txtSearchHome = null;
	private ImageView imgSearchHome = null;
	
	
	private ScheduledExecutorService scheduledExecutorService;//��ʱ����ִ������
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		
		if(BaseApplication.isNetworkConnected(getActivity())) {
			fragmentHome =  inflater.inflate(R.layout.fragment_home,container,false);
			initView();
			
			return fragmentHome;
		}
		else{
			fragmentHome =  inflater.inflate(R.layout.activity_networkerror,container,false);
			return fragmentHome;
		}
		
	}
	
	void initView(){
		
		txtSearchHome = (TextView) fragmentHome.findViewById(R.id.txt_search_home);
		imgSearchHome = (ImageView) fragmentHome.findViewById(R.id.img_search_home);
		txtSearchHome.setOnClickListener(this);
		imgSearchHome.setOnClickListener(this);
		
		dots = new ArrayList<View> ();
		dots.add(fragmentHome.findViewById(R.id.dot_0));
		dots.add(fragmentHome.findViewById(R.id.dot_1));
		dots.add(fragmentHome.findViewById(R.id.dot_2));
		dots.add(fragmentHome.findViewById(R.id.dot_3));
		dots.add(fragmentHome.findViewById(R.id.dot_4));
		
		title = (TextView) fragmentHome.findViewById(R.id.title);
		
		// ͼƬID
		imagesIds = new int[] {
				R.drawable.slide,
				R.drawable.bx,
				R.drawable.af,
				R.drawable.eld
				
		};
				// ͼƬ����
		titles = new String[] {
				"图书馆",
				"风景",
				"花",
				"美景"
		};
		
		
		title.setText(titles[0]);
		
		txtScroll = (TextView) fragmentHome.findViewById(R.id.txt_intro);
		txtScroll.setMovementMethod(new ScrollingMovementMethod());
		
		mViewPager = (ViewPager) fragmentHome.findViewById(R.id.vp);
		
		setBanner();
		
		//mViewPager.setAdapter(adapter);
		//mViewPager.setOnPageChangeListener(this);
//		TxtSearch = fragmentHome.findViewById(R.id.txt_search_home);
//		TxtSearch.setOnclickListener(this);
//		ImgSearch = fragmentHome.findViewById(R.id.img_search_home);
//		ImgSearch.setOnclckLitener(this);
		
		
	}
	
	
	/**
	 * ���ö����ֲ�ͼƬ
	 */
	private void setBanner() {
		
		
		images = new ArrayList<ImageView>();
		
		// ��ʾ��ͼƬ
		
		for (int i = 0; i < imagesIds.length; i++) {
			ImageView imageView = new ImageView(getActivity());
			imageView.setBackgroundResource(imagesIds[i]);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			images.add(imageView);
			
		}
		title.setText(titles[0]);
		
		adapter = new ViewPagerAdapter();
		mViewPager.setAdapter(adapter);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				title.setText(titles[position]);

				dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
				dots.get(position).setBackgroundResource(R.drawable.dot_focused);

				oldPosition = position;
				currentItem = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

		// ÿ��2�����л�һ��ͼƬ
		scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 2,
				3, TimeUnit.SECONDS);
	}
	// �л�ͼƬ
	private class ViewPagerTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			currentItem = (currentItem + 1) % imagesIds.length;
			// ���½���
			// handler.sendEmptyMessage(0);
			handler.obtainMessage().sendToTarget();
		}

	}
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			// ���õ�ǰҳ��
			mViewPager.setCurrentItem(currentItem);
		}

	};
	
	/**
	 * 
	 * @author Liuyong
	 *
	 */
	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return images.size();
		}

		// �Ƿ���ͬһ��ͼƬ
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup view, int position, Object object) {
			// TODO Auto-generated method stub
			// super.destroyItem(container, position, object);
			// view.removeViewAt(position);
			view.removeView(images.get(position));

		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			// TODO Auto-generated method stub
			view.addView(images.get(position));

			return images.get(position);
		}
	}

	
	@Override
	public void onClick(View arg0) {
		
		switch(arg0.getId()) {
		case R.id.img_search_home:
			Intent intent = new Intent(getActivity(),BookSearchActivity.class);
			startActivity(intent);
			break;
		case R.id.txt_search_home:
			Intent intent1 = new Intent(getActivity(),BookSearchActivity.class);
			startActivity(intent1);
		default:
			break;
		}
		
	}

	
	//OnPageChanged�����ǵķ���
	
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
}
