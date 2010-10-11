package com.daschlo.icalsync.application;

import com.daschlo.icalsync.functionality.AccountAuthenticator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AccountService extends Service {

	private AccountAuthenticator mAccountAuthenticator;
	
	public AccountService()
	{
		// Make a new AccountAuthenticator-instance and save it
		mAccountAuthenticator = new AccountAuthenticator(this);
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// Check if the intent is right
		if(arg0.getAction().equals(android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT))
		{
			// Return the IBinder-Communication of the AccountAuthenticator
			return mAccountAuthenticator.getIBinder();
			
		}
		
		
		return null;
	}

}
