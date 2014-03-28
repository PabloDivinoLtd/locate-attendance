package com.techarha.gpsattendance;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import serviceHandler.JSONParser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends Activity {
		
	
	
	Button register;
	EditText name, rollNo, password, phoneNo;
	Spinner collegeName, className, courseName;
	ArrayAdapter<CharSequence> collegeAdapter, classAdapter, couserAdapter;
	
	// Progress Dialog
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();

		// JSON Node names
		private static final String TAG_SUCCESS = "success";
		private static String url_loginUser = ("http://192.168.52.1/GpsAttendance/public/users");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		collegeName = (Spinner) findViewById(R.id.collegeName);
		className = (Spinner) findViewById(R.id.className);
		courseName = (Spinner) findViewById(R.id.courseName);
		setArrayAdapterWithSpinners();	
		
		
		name = (EditText) findViewById(R.id.nameR);
		rollNo = (EditText) findViewById(R.id.rollR);
		password = (EditText) findViewById(R.id.passR);
		phoneNo = (EditText) findViewById(R.id.phoneR);
	}

	private void setArrayAdapterWithSpinners() {
		collegeAdapter = ArrayAdapter.createFromResource(this, R.array.college_names, android.R.layout.simple_spinner_dropdown_item);
		collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		collegeName.setAdapter(collegeAdapter);
		
		classAdapter = ArrayAdapter.createFromResource(this, R.array.className, android.R.layout.simple_spinner_dropdown_item);
		classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		className.setAdapter(classAdapter);
		
		couserAdapter = ArrayAdapter.createFromResource(this, R.array.couserName, android.R.layout.simple_spinner_dropdown_item);
		couserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		courseName.setAdapter(couserAdapter);
	}
	
	class LoginUser extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(RegisterActivity.this);
			pDialog.setMessage("Logging In");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String phoneS = phoneNo.getText().toString();
			String nameS = name.getText().toString();
			String passS = password.getText().toString();
			String rollS = rollNo.getText().toString();
			
			
			Log.d("techhahn", "Values has been converted");

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("phone_no", phoneS));
			params.add(new BasicNameValuePair("name", nameS));
			params.add(new BasicNameValuePair("password", passS));
			params.add(new BasicNameValuePair("roll_no", rollS));
			

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
					Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
					startActivity(i);
					

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
