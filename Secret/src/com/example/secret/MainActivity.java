package com.example.secret;

import com.example.secret.atys.AtyLogin;
import com.example.secret.atys.AtyTimeline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		String token=Config.getCachedToken(this);
		if(token!=null){
			Intent i=new Intent(this,AtyTimeline.class);
			i.putExtra(Config.KEY_TOKEN,token);
			startActivity(i);
		}else{
			startActivity(new Intent(this,AtyLogin.class));
		}
	}

}