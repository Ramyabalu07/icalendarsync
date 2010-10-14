package com.daschlo.icalsync.representation;

import java.util.HashSet;

import com.android.icalsync.R;
import com.daschlo.icalsync.application.AccountTask;
import com.daschlo.icalsync.util.GoogleCalendar;
import com.daschlo.icalsync.util.ICallLog;

import android.accounts.AccountAuthenticatorActivity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InputActivity extends AccountAuthenticatorActivity {
	
	public Button mSendInput;
	
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.inputactivity);
		
		// Fill in the calendar in the spinner
		ICallLog.postLogInfo("Get calendars for spinner", "InputActivity.onCreate");
		try
		{
			// Get ContentResolver and Cursor for reading out the calendars
			ContentResolver contentresolver = getContentResolver();
			final Cursor cursor = contentresolver.query(
					Uri.parse(getString(R.string.pref_calendaruri_calendars)), 
					getResources().getStringArray(R.array.pref_calendaruri_calendars_projection), 
					null, null, null);
			
			// Create HashSet to save Calendar-Data (Id and Name)
			HashSet<GoogleCalendar> calendars = new HashSet<GoogleCalendar>();
			
			// Go through all calendars and note name and id, if its selected and not readonly
			while(cursor.moveToNext())
			{
				if(!cursor.getString(2).equals("0")||Integer.valueOf(cursor.getString(3))>=500)
				{
					// Add the seperate Calendars and save their ids and names
					calendars.add(new GoogleCalendar(cursor.getString(0),cursor.getString(1)));							
				}
			}
			
			// Create an ArrayAdapter
			ArrayAdapter<GoogleCalendar> arrayadapter = new ArrayAdapter<GoogleCalendar>(
					this,
					android.R.layout.simple_spinner_item,
					(GoogleCalendar [])calendars.toArray(new GoogleCalendar[calendars.size()]));
			arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			Spinner spinner = (Spinner)findViewById(R.id.SpinnerCalendar);
			spinner.setAdapter(arrayadapter);
			
			GoogleCalendar googlecalendar = (GoogleCalendar)spinner.getSelectedItem();
			ProgressDialog dialog = ProgressDialog.show(this, googlecalendar.mID, googlecalendar.mName);
			Thread.sleep(5000);
			dialog.dismiss();
			
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
