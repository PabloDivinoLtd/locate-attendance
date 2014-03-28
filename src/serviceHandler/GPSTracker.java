package serviceHandler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

public class GPSTracker implements LocationListener {
	
	private final Context mContext;
	 
    // flag for GPS status
    boolean isGPSEnabled = false;
 
    // flag for network status
    boolean isNetworkEnabled = false;
 
    boolean canGetLocation = false;
 
    Location location; // location
    double latitude; // latitude
    double longitude; // longitude
 
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
 
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
 
    // Declaring a Location Manager
    protected LocationManager locationManager;
 
    public GPSTracker(Context context) {
        this.mContext = context;
        getLocation();
    }
    
    
    

	private Location getLocation() {
		try {
			locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
			
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			
			isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			
			if(!isGPSEnabled && !isNetworkEnabled) {
				//no netwrok enabled
			}
			else {
				this.canGetLocation = true;
				
				if(isNetworkEnabled) {
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network");
					if (locationManager != null) {
						location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						
						if(location != null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}						
				}
				
				if(isGPSEnabled) {
					if(location == null) {
						locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPSEnabled", "GPSEnabled");
						if(locationManager!=null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;		
	}
	
	public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(GPSTracker.this);
        }       
    }
	
	public double getLatitude() {
		if(location != null){
			latitude = location.getLatitude();
		}
		return latitude;
	}
	
	public double getLongitude() {
		if(location != null){
			longitude = location.getLongitude();
		}
		return longitude;		
	}
	
	public boolean canGetLocation() {
		return this.canGetLocation;
	}
	
	public void showSettingsAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
		
		alertDialog.setTitle("GPS in settings");
		
		alertDialog.setMessage("GPS is not enabled, do u want to enable it?");
		
		alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
			}
		});
		
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });
  
        // Showing Alert Message
        alertDialog.show();
	}
	


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}