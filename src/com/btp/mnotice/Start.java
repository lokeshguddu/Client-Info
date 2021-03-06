package com.btp.mnotice;

import java.lang.reflect.Method;

import com.btp.mnotice.R;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Start extends Activity
{

	EditText ssid, pass;
	Boolean isclient = false;
	int numb;
	String ssidet = null, passet = null;
	WifiManager wifi;
	WifiConfiguration configure = null;
	private static final String TAG = "WiFiDemo";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);

		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		try
		{

			Method method = wifi.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
			method.invoke(wifi, configure, false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			wifi.setWifiEnabled(false);
			int loopMax = 10;
			while (loopMax > 0 && wifi.getWifiState() != WifiManager.WIFI_STATE_ENABLED)
			{
				Log.d(TAG, "disable wifi: waiting, pass: " + (10 - loopMax));
				try
				{
					Thread.sleep(500);
					loopMax--;
				}
				catch (Exception e)
				{

				}
			}
			Log.d(TAG, "enable wifi: done, pass: " + (10 - loopMax));
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}

		Button btSer=(Button)findViewById(R.id.btSer);		
		btSer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v)
			{
				isclient = false;
				Intent intent;
				intent = new Intent("com.btp.mnotice.MAINACTIVITY");
				intent.putExtra("isclient", isclient);
				startActivity(intent);	
			}
		});	


		Button btCli=(Button)findViewById(R.id.btCli);		
		btCli.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v)
			{
				isclient = true;
				Intent intent;
				intent = new Intent("com.btp.mnotice.MAINACTIVITY");
				intent.putExtra("isclient", isclient);
				startActivity(intent);	
			}
		});			

	}

}
