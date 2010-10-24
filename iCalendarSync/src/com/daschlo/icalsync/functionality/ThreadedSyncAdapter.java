package com.daschlo.icalsync.functionality;

import com.daschlo.icalsync.R;
import com.daschlo.icalsync.util.FunctionCollection;
import com.daschlo.icalsync.util.ICallLog;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SyncResult;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.Time;

public class ThreadedSyncAdapter extends AbstractThreadedSyncAdapter {

	private Context mContext;
	
	public ThreadedSyncAdapter(Context context) {
		super(context, true);
		
		
		// Save context for later use
		mContext = context;
	}

	@Override
	public void onPerformSync(Account account, Bundle extras, String authority,
			ContentProviderClient provider, SyncResult syncResult) {

		// Write log
		Time time = new Time();
		time.set(System.currentTimeMillis());
		String timestamp = time.format("%d.%m.%y %H:%M");
		ICallLog.postLogInfo("Starting sync at " + timestamp ,"ThreadedSyncAdapter.onPerformSync");
		
		// Get the saved link
		AccountManager accountmanager = AccountManager.get(mContext);
		String accountLink = accountmanager.getUserData(account, "com.daschlo.icalsync.account.link");
		accountLink = accountLink.toString();			
		
		try
		{
			
			
			// Save last sync time
			SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
			Editor editor = sharedpreferences.edit();
			editor.putString(mContext.getString(R.string.pref_timestamp_key), timestamp);
			editor.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ICallLog.postLogErr(e.getMessage(),"ThreadedSyncAdapter.onPerformSync");
		}
		
	}
	
	public void RenewFile(String link, String filename) throws Exception
	{
		
	}	

}
