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
import org.json.JSONException;
import org.json.JSONObject;

import serviceHandler.JSONParser;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
	

	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static String url_loginUser = ("http://192.168.52.1/GpsAttendance/public/login");
	
	
	public LoginActivity() throws MalformedURLException {
		// TODO Auto-generated constructor stub
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
				Log.d("techhahn", "Button clicked");
				//new LoginUser().execute();
				Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
				startActivity(i);
				Log.d("techhahn", "tas");			
			}

		});
		
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
	 
	
	class LoginUser extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(LoginActivity.this);
			pDialog.setMessage("Logging In");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String phone = phoneNo.getText().toString();
			String passwd = pass.getText().toString();
			Log.d("techhahn", "Values has been converted");

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("phone_no", phone));
			params.add(new BasicNameValuePair("password", passwd));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_loginUser,
					"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				Log.d("techhahn", "Sending request");

				if (success == 1) {
					// successfully created product
//					Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
//					startActivity(i);
					
					Log.d("techhahn", "Sucess");
					

					Log.d("techhahn", "successful = 1");
					
					// closing this screen
					finish();
				} else {
					// failed to create product
					Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}

	 

}
