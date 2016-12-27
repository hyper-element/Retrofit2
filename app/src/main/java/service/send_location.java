package service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import common.method.Method;
import interfaces.Webservices;
import common.utils.GPSTracker;
import model.M_locresponce;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class send_location extends Service {

    SimpleDateFormat dateFormat;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Log.e("Service started", "YES");
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sendLocation();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public void sendLocation() {
		final Handler handler = new Handler();
		Runnable runable = new Runnable() {
			@Override
			public void run() {
				try {
					Log.e("MainActivity", "I am Being Called");
					new AsyncTaskParseJson().execute();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					handler.postDelayed(this, 1000);
				}
			}
		};
		handler.postDelayed(runable, 1000);
	}

	public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			GPSTracker gpsTracker = new GPSTracker(getApplicationContext());
			Location loc = gpsTracker.getLocation();
			String loc_lat = String.valueOf(loc.getLatitude());
			String loc_long = String.valueOf(loc.getLongitude());
            String timestamp  = dateFormat.format(new Date());
            sendData("2",loc_lat,loc_long,timestamp);
			Log.e("Location : ", "location is : " + loc_lat + "," + loc_long);
		}

		@Override
		protected String doInBackground(String... arg0) {
			return null;
		}
	}

	public void sendData  (String trip_id,String lat,String lng,String timestamp){
		Map<String, String> map = new HashMap<>();
		map.put("username",trip_id);
		map.put("password",lat);
		map.put("email_address", lng);
        map.put("email_address", timestamp);

		Webservices webservices = Method.getRetrofit().create(Webservices.class);
		Call<M_locresponce> m_user_signup_form_resCall = webservices.signInMapForm(map);
		m_user_signup_form_resCall.enqueue(new Callback<M_locresponce>() {
			@Override
			public void onResponse(Call<M_locresponce> call, Response<M_locresponce> response) {
				Log.e("RES", response.body().getValidSave()+"...");
			}

			@Override
			public void onFailure(Call<M_locresponce> call, Throwable t) {
				Log.e("Error", t.toString());
			}
		});
	}
}
