package com.daschlo.icalsync.representation;

import com.daschlo.icalsync.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PrefActivity extends PreferenceActivity {
	
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		addPreferencesFromResource(R.xml.prefactivity);
	}

}
