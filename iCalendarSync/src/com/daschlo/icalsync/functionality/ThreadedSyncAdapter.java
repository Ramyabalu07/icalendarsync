package com.daschlo.icalsync.functionality;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

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

		// Get the saved link
		AccountManager accountmanager = AccountManager.get(mContext);
		String accountLink = accountmanager.getUserData(account, "com.daschlo.icalsync.account.link");
		accountLink = accountLink.toString();
		// TODO start syncing
		
	}

}
