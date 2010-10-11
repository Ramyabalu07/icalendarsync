package com.daschlo.icalsync.util;

import android.util.Log;

public class ICallLog {
	public static final String appphrase = "icalsync";
	
	public static void postLogErr(String text, String location)
	{
		Log.e(appphrase, text + " at " + location);		
	}
	
	public static void postLogInfo(String text, String location)
	{
		Log.i(appphrase, text + " at " + location);		
	}
}
