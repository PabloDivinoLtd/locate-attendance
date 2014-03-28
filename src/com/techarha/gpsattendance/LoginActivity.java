package com.techarha.gpsattendance;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	URL url;
	EditText phoneNo, pass;
	Button b;
	public LoginActivity() throws MalformedURLException {
		// TODO Auto-generated constructor stub
		url = new URL("http://192.168.176.1/GpsAttendance/public/");
	}
	
	 @SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		phoneNo = (EditText) findViewById(R.id.phoneNumber);
		pass = (EditText) findViewById(R.id.passw);
		b = (Button) findViewById(R.id.buttonLogin);
		
		ActionBar actionbar = getActionBar();
		actionbar.setHomeButtonEnabled(true);
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(emptyFields()){
					Toast.makeText(getApplicationContext(), "Empty Fields", Toast.LENGTH_LONG).show();
				} else {
					login();
				}
				
			}

			private void login() {
				// TODO Auto-generated method stub
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost("http://192.168.176.1/GpsAttendance/login");
				
				try {
					List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
					nameValuePair.add(new BasicNameValuePair("phone_no", phoneNo.getText().toString()));
					nameValuePair.add(new BasicNameValuePair("password", pass.getText().toString()));
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
					
					HttpResponse response = httpClient.execute(httpPost);
					
//					if(response.getFirstHeader() == 1) {
//						Intent i  = new Intent(LoginActivity.this, DashboardActivity.class);
//						startActivity(i);
//					}
//					Toast.makeText(getApplicationContext(), response + "", Toast.LENGTH_LONG).show();
					
					Log.d("FUCK", response.toString());
					
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "error:" + e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}


	private boolean emptyFields() {
		boolean et = false;
		if(phoneNo.getText().toString().length() == 0 || pass.getText().toString().length() == 0) {
			et = true;
		}
		else {
			et = false;
		}
		return et;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	 
	 

}
