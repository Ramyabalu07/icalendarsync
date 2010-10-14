package com.daschlo.icalsync.application;

import com.daschlo.icalsync.R;
import com.daschlo.icalsync.representation.InputActivity;
import com.daschlo.icalsync.util.ICallLog;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

public class AccountTask extends AsyncTask<String, Void, Boolean> {

	private Context mContext;
	private ProgressDialog mDialog;
	
	public AccountTask(Context context)
	{
		super();
		
		// Save context for later use
		mContext = context;
		
		// Show ProgressDialog
		mDialog = ProgressDialog.show(mContext, mContext.getString(R.string.accounttask_progressdialog_title),mContext.getString(R.string.accounttask_progressdialog_text) );
	}
	
	@Override
	protected Boolean doInBackground(String... arg0) {
		// Get the delivered information
		String accountName = arg0[0];
		String accountLink = arg0[1];
		
		// Validate
		// TODO Validation-Code for link?
		
		// Lace data-Bundle
		Bundle data = new Bundle();
		data.putString("com.daschlo.icalsync.account.link", accountLink);
		
		// Create an account and get the AccountManager
		Account account = new Account(accountName,"com.daschlo.icalsync.account");
		AccountManager accountmanager = AccountManager.get(mContext);
		
		try
		{
			// Add account and if success continue
			if(accountmanager.addAccountExplicitly(account, null, data))
			{
				ICallLog.postLogInfo("Account: " + accountName + " added", "AccountTask.doInBackground" );
				
				// Give back reply
				Bundle reply = new Bundle();
				reply.putString(android.accounts.AccountManager.KEY_ACCOUNT_NAME, account.name);
				reply.putString(android.accounts.AccountManager.KEY_ACCOUNT_TYPE, account.type);
				
				// Send it to the activity
				((InputActivity)mContext).setAccountAuthenticatorResult(reply);
				
				return new Boolean(true);
				
								
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ICallLog.postLogErr(e.getMessage(), "AccountTask.doInBackground");
		}
		
		return new Boolean(false);
	}
	
	@Override
	protected void onPostExecute(Boolean result)
	{
		((InputActivity)mContext).mSendInput.setEnabled(true);
		mDialog.dismiss();
		
		if(result)
		{
			((InputActivity)mContext).finish();
		}
		else
		{
			AlertDialog alert = new AlertDialog.Builder(mContext).create();
			alert.setTitle(mContext.getString(R.string.accounttask_unsuccessful_title));
			alert.setMessage(mContext.getString(R.string.accounttask_unsuccessful_text));
			alert.show();
		}
	}

}
