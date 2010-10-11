package com.daschlo.icalsync.representation;

import com.android.icalsync.R;
import com.daschlo.icalsync.application.AccountTask;

import android.accounts.AccountAuthenticatorActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AccountAuthenticatorActivity {
	
	public Button mSendInput;
	
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.inputactivity);
	}
	
	public void onClick(View view)
	{
		// Get Button-Handle and de-activate button
		mSendInput = (Button) findViewById(R.id.ButtonSendInput);
		mSendInput.setEnabled(false);
		
		// Get information from the TextFields
		String accountName = ((EditText)findViewById(R.id.EditTextInputName)).getText().toString();
		String accountLink = ((EditText)findViewById(R.id.EditTextInputLink)).getText().toString();
		
		// and finally start task adding the account
		AccountTask task = new AccountTask(this);
		task.execute(accountName,accountLink);
	}

}
