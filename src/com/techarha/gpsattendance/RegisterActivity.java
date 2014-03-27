package com.techarha.gpsattendance;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class RegisterActivity extends Activity {

	ArrayList<String> course = new ArrayList<String>();
	ArrayList<String> classes = new ArrayList<String>();
	ArrayList<String> colleges = new ArrayList<String>();
	
	public RegisterActivity() {
		fetchData();
	}
	
	private void fetchData() {
		course.add("MCA");
		course.add("MBA");
		
		colleges.add("IBMR");
		colleges.add("ICS");
		
		classes.add("First Year");
		classes.add("Second Year");
		classes.add("Third Year");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}


}
