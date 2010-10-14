package com.daschlo.icalsync.util;

import java.util.HashSet;

import com.daschlo.icalsync.R;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.widget.ArrayAdapter;

public class FunctionCollection {
	
	public static ArrayAdapter<GoogleCalendar> getCalendars(Context context) throws Exception
	{
		// Fill in the calendar in the spinner
		ICallLog.postLogInfo("Get calendars", "FunctionCollection.getCalendars");
		
		// Get ContentResolver and Cursor for reading out the calendars
		ContentResolver contentresolver = context.getContentResolver();
		final Cursor cursor = contentresolver.query(
				Uri.parse(context.getString(R.string.pref_calendaruri_calendars)), 
				context.getResources().getStringArray(R.array.pref_calendaruri_calendars_projection), 
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
				context,
				android.R.layout.simple_spinner_item,
				(GoogleCalendar [])calendars.toArray(new GoogleCalendar[calendars.size()]));
		arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		return arrayadapter;	
		
	}

}
