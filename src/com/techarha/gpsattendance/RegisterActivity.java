package com.techarha.gpsattendance;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends Activity {
		
	
	
	Button register;
	EditText name, rollNo, password, phoneNo;
	Spinner collegeName, className, courseName;
	ArrayAdapter<CharSequence> collegeAdapter, classAdapter, couserAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		collegeName = (Spinner) findViewById(R.id.collegeName);
		className = (Spinner) findViewById(R.id.className);
		courseName = (Spinner) findViewById(R.id.courseName);
		setArrayAdapterWithSpinners();		
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
}
