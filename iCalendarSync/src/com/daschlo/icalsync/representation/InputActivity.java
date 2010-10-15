package com.daschlo.icalsync.representation;

import com.daschlo.icalsync.R;
import com.daschlo.icalsync.application.AccountTask;
import com.daschlo.icalsync.util.FunctionCollection;
import com.daschlo.icalsync.util.GoogleCalendar;
import com.daschlo.icalsync.util.ICallLog;

import android.accounts.AccountAuthenticatorActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InputActivity extends AccountAuthenticatorActivity {
	
	public Button mSendInput;
	
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.inputactivity);
		
		try
		{
			// Get Calendars in the spinner-item
			Spinner spinner = (Spinner)findViewById(R.id.SpinnerCalendar);
			spinner.setAdapter(FunctionCollection.getCalendars(this));
			
			// Set Listener to save changes
			spinner.setOnItemSelectedListener(new OnItemSelectedListener()
			{

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					// Get the selected item
					GoogleCalendar googlecalendar = (GoogleCalendar) arg0.getItemAtPosition(arg2);
					
					// Get apps Preferences-Editor
					SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
					SharedPreferences.Editor editor = sharedpreferences.edit();
					
					// Save the selection
					editor.putString(getString(R.string.prefactivity_cat_1_calendar_key), googlecalendar.mID);
					editor.commit();
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// If nothing is selected --> select something
					arg0.setSelection(0);
					
				}
				
			});
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ICallLog.postLogErr(e.getMessage(), "InputActivity.onCreate");
		}
	}
	
	public void onClick(View view)
	{
		// Get Button-Handle and de-activate button
		mSendInput = (Button) findViewById(R.id.ButtonSendInput);
		mSendInput.setEnabled(false);
		
		// Get information from the TextFields
		String accountName = ((EditText)findViewById(R.id.EditTextInputName)).getText().toString();
		String accountLink = ((EditText)findViewById(R.id.EditTextInputLink)).getText().toString();
		
		// and finally start task adding the account
		AccountTask task = new AccountTask(this);
		task.execute(accountName,accountLink);
	}
	


}
