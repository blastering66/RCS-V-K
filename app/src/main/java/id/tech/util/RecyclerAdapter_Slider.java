package id.tech.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import com.pkmmte.view.CircularImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import id.tech.kalbe.Activity_Store;
import id.tech.kalbe.Activity_Store_Review;
import id.tech.kalbe.DialogLogout;
import id.tech.kalbe.R;

public class RecyclerAdapter_Slider extends RecyclerView.Adapter<RecyclerAdapter_Slider.ViewHolder>{
	private Context context;
	private SharedPreferences sh;
	private Activity activity;
	private String nama, target, achievement, url;
	private FragmentManager fm;
	private String total_visited_store;
	
	public RecyclerAdapter_Slider(Context context, Activity activity) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.activity = activity; 	
	}

	public RecyclerAdapter_Slider(String nama, String url,Context context, Activity activity,
								  FragmentManager fm, String total_visited_store, SharedPreferences sh) {
		this.nama = nama;
		this.url = url;
		this.context = context;
		this.activity = activity;
		this.fm = fm;
		this.total_visited_store = total_visited_store;
		this.sh = sh;
	}
	


	public class ViewHolder extends RecyclerView.ViewHolder{
		public TextView tv_nama, tv_target, tv_achivement, tv_logout, tv_summary_visit;
		public Button btn_create_newvisit, btn_summary_newvisit;
		public ImageView img;
		private CircularImageView circularImageView;
		private Activity activity;
		private Context context;


		public ViewHolder(View v, Activity activity) {
			super(v);
			tv_nama = (TextView) v.findViewById(R.id.tv_nama);
			tv_target = (TextView) v.findViewById(R.id.tv_target);
			tv_achivement = (TextView) v.findViewById(R.id.tv_achievement);
			tv_logout = (TextView)v.findViewById(R.id.tv_logout);

			tv_summary_visit= (TextView)v.findViewById(R.id.tv_summary_visit);

			img = (ImageView) v.findViewById(R.id.img);
			circularImageView = (CircularImageView)v.findViewById(R.id.img_profile);

			btn_create_newvisit= (Button)v.findViewById(R.id.btn_create_newvisit);
			btn_summary_newvisit= (Button)v.findViewById(R.id.btn_summary_newvisit);

			this.activity = activity;

		}


	}


	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	public Bitmap getBitmapFromURL(String src) {
	    try {
	        java.net.URL url = new java.net.URL(src);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
		// TODO Auto-generated method stub
		if(url.equals("")){
			arg0.circularImageView.setImageResource(R.drawable.img_profile_test);
		}else{
			Bitmap b = getBitmapFromURL(url);
			arg0.circularImageView.setImageBitmap(b);
		}
		arg0.tv_nama.setText(nama);
		arg0.tv_summary_visit.setText(total_visited_store);
		
		arg0.btn_create_newvisit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, Activity_Store.class);
				activity.startActivity(intent);
			}
		});

		arg0.btn_summary_newvisit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, Activity_Store_Review.class);
				activity.startActivity(intent);
			}
		});

		arg0.tv_logout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogLogout pDialog = new DialogLogout();
				pDialog.setContext(activity);
				pDialog.setSh(sh);
				pDialog.show(fm,"");
			}
		});
	}


	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		View v = LayoutInflater.from(arg0.getContext()).inflate(
				R.layout.layout_profile, arg0, false);
		ViewHolder viewholder = new ViewHolder(v, this.activity);
		return viewholder;
	}

	
}
