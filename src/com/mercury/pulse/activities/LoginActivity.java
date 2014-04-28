package com.mercury.pulse.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mercury.pulse.R;

public class LoginActivity extends Activity {
	MakeItRain makeItRain = new MakeItRain();
	ImageView logo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		init();
		makeItRain.execute();
		
	}
	
	private void init() {
		logo = (ImageView)findViewById(R.id.logo);
		logo.setBackgroundResource(R.anim.anim_logo);	
	}

	public void btn_login(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	public class MakeItRain extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... arg0) {
			AnimationDrawable progressAnimation = (AnimationDrawable) logo.getBackground();
			progressAnimation.start();
			return null;
		}
	}
	

}
