package com.daschlo.icalsync.util;

import android.util.Log;

public class ICallLog {
	static final String appphrase = "icalsync";
	
	static void postLogErr(String text, String location)
	{
		Log.e(appphrase, text + " at " + location);		
	}
	
	static void postLogInfo(String text, String location)
	{
		Log.i(appphrase, text + " at " + location);		
	}
}
