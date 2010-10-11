package com.daschlo.icalsync.functionality;

import com.daschlo.icalsync.representation.InputActivity;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AccountAuthenticator extends AbstractAccountAuthenticator {

	private Context mContext;
	
	public AccountAuthenticator(Context context) {
		super(context);

		// Save the given context for later use
		mContext = context;
	}

	@Override
	public Bundle addAccount(AccountAuthenticatorResponse response,
			String accountType, String authTokenType,
			String[] requiredFeatures, Bundle options)
			throws NetworkErrorException {
		
		// Check the asked accountType
		if(accountType.equals("com.daschlo.icalsync.account"))
		{			
			// Create intent to ask the user for input and put the response with it
			Intent i = new Intent(mContext,InputActivity.class);
			i.putExtra(android.accounts.AccountManager.KEY_ACCOUNT_MANAGER_RESPONSE, response);
			i.setAction(  "android.intent.action.VIEW" );
			
			// Lace a Bundle to reply
			Bundle reply = new Bundle();
			reply.putParcelable(android.accounts.AccountManager.KEY_INTENT, i);
			
			// Giving back the answer
			return reply;
			
		}
		
		return null;
	}

	
	
	// NOT USED TIL HERE
	@Override
	public Bundle confirmCredentials(AccountAuthenticatorResponse response,
			Account account, Bundle options) throws NetworkErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bundle editProperties(AccountAuthenticatorResponse response,
			String accountType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bundle getAuthToken(AccountAuthenticatorResponse response,
			Account account, String authTokenType, Bundle options)
			throws NetworkErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthTokenLabel(String authTokenType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bundle hasFeatures(AccountAuthenticatorResponse response,
			Account account, String[] features) throws NetworkErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bundle updateCredentials(AccountAuthenticatorResponse response,
			Account account, String authTokenType, Bundle options)
			throws NetworkErrorException {
		// TODO Auto-generated method stub
		return null;
	}

}
