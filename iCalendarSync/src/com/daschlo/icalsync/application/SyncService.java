package com.daschlo.icalsync.application;

import com.daschlo.icalsync.functionality.ThreadedSyncAdapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SyncService extends Service {

	private ThreadedSyncAdapter mTSyncAdapter;
	
	public SyncService()
	{
		super();
		
		// Create a new ThreadedSyncAdapter
		mTSyncAdapter = new ThreadedSyncAdapter(this);
		
		
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		
		// Return the IBinder of the created syncadapter
		return mTSyncAdapter.getSyncAdapterBinder();
	}

}
