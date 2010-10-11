package com.daschlo.icalsync.representation;

import com.android.icalsync.R;

import android.accounts.AccountAuthenticatorActivity;
import android.os.Bundle;

public class InputActivity extends AccountAuthenticatorActivity {
	
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.inputactivity);
	}

}
