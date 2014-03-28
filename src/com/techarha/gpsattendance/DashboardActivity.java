package com.techarha.gpsattendance;

import serviceHandler.GPSTracker;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends Activity{
	
	TextView lat, lon;
	
	Button button;
	
	GPSTracker gpstracker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		gpstracker = new GPSTracker(DashboardActivity.this);
		lat = (TextView) findViewById(R.id.lat);
		lon = (TextView) findViewById(R.id.longi);
		
		getGPS();
		
		button = (Button) findViewById(R.id.attenbutton1);
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getGPS();
			}
		});
	}

	private void getGPS() {
		if(gpstracker.canGetLocation()) {
			double latD = gpstracker.getLatitude();
			double lonD = gpstracker.getLongitude();
			
			lat.setText("Latitude: " + latD);
			lon.setText("Longitude: " + lonD);
			
		}
		else {
			gpstracker.showSettingsAlert();
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
}
