package com.daschlo.icalsync.representation;

import java.util.LinkedHashSet;

import com.daschlo.icalsync.R;
import com.daschlo.icalsync.util.FunctionCollection;
import com.daschlo.icalsync.util.GoogleCalendar;
import com.daschlo.icalsync.util.ICallLog;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.ArrayAdapter;

public class PrefActivity extends PreferenceActivity {
	
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		addPreferencesFromResource(R.xml.prefactivity);			
		
		// Get Handle on the ListPreference of the calendar-choice
		PreferenceScreen preferencescreen = getPreferenceScreen();
		ListPreference listpreference = (ListPreference)preferencescreen.findPreference(getString(R.string.prefactivity_cat_1_calendar_key));
		
		// Register Listner
		preferencescreen.getSharedPreferences().registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener(){

			@Override
			public void onSharedPreferenceChanged(
					SharedPreferences sharedPreferences, String key) {
				if(key.equals(getString(R.string.prefactivity_cat_2_interval_key)))
				{
					int value = sharedPreferences.getInt(key, 1440);
					AccountManager am = AccountManager.get(getApplicationContext());
					Account[] accounts = am.getAccountsByType("com.daschlo.icalsync.account");
					
					for(int i = 0; i<accounts.length;i++)
					{
						ContentResolver.addPeriodicSync(accounts[i], "com.android.calendar", null, value*3600);						
					}
					
				}
				
			}});
		
		// Fill in the LastSync
		SharedPreferences sharedpreferences = preferencescreen.getSharedPreferences();
		String timestamp = sharedpreferences.getString(getString(R.string.pref_timestamp_key), "-");
		Preference preference = preferencescreen.findPreference(getString(R.string.prefactivity_cat_2_timestamp_key));
		preference.setSummary(timestamp);
		
		try 
		{
			// Get Calendars and add them: name to entries | id to values
			ArrayAdapter<GoogleCalendar> arrayadapter = FunctionCollection.getCalendars(this);
			
			// Get number of records
			int j = arrayadapter.getCount();
			LinkedHashSet<String> names = new LinkedHashSet<String> ();
			LinkedHashSet<String> ids = new LinkedHashSet<String> ();
			
			// Loop to read out all
			for(int i = 0; i<j; i++)
			{
				GoogleCalendar googlecalendar = arrayadapter.getItem(i);
				names.add(googlecalendar.mName);
				ids.add(googlecalendar.mID);				
			} 
			
			// Add them to listpref
			listpreference.setEntries(names.toArray(new String[names.size()]));
			listpreference.setEntryValues(ids.toArray(new String[ids.size()]));
		}
		catch (Exception e) 
		{
			//UPSY
			e.printStackTrace();
			ICallLog.postLogErr(e.getMessage(), "PrefActivity.onCreate");
			finish();
		}
	}

}
