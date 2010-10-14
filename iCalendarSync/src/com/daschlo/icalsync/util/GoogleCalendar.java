package com.daschlo.icalsync.util;

public class GoogleCalendar {
	public String mID, mName;
	
	public GoogleCalendar(String calendarid, String calendarname)
	{
		mID = calendarid;
		mName = calendarname;			
	}
	
	public String toString()
	{
		return mName;
	}
	
	
	

}
