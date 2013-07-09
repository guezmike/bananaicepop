package com.zipper.zipcloset;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button loginButton = (Button) findViewById(R.id.facebookLogin);
		loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// The FB SDK has a bit of a delay in response
				final ProgressDialog progressDialog = ProgressDialog.show(
						MainActivity.this, "Connecting to Facebook",
						"Logging in with Facebook - just a moment");

				doFacebookSso(progressDialog);
			}
		});
		Button skipButton = (Button) findViewById(R.id.skipButton);
		skipButton.setOnClickListener(new OnClickListener() {
			
		@Override
		public void onClick(View v) {
			// Start the main activity
			Intent intent = new Intent(MainActivity.this, MainMenu.class);
			startActivity(intent);
			finish();
		}
	});
	}
    private void doFacebookSso(final ProgressDialog progressDialog){
    	
    	FacebookService.facebook.authorize(MainActivity.this, 
				new String[] { "publish_stream," , "publish_checkins" },
				new DialogListener() {
					@Override
					public void onComplete(Bundle values) {
						// Close the progress dialog and toast success to the user
						if (progressDialog != null && progressDialog.isShowing()) {
							progressDialog.dismiss();
						}
						Toast.makeText(MainActivity.this, "Logged in with Facebook.", 
								Toast.LENGTH_LONG).show();
						
						// Start the main activity
						Intent intent = new Intent(MainActivity.this, MainActivity.class);
						startActivity(intent);
						finish();
					}

					@Override
					public void onFacebookError(FacebookError error) {
						showFacebookError(progressDialog);
					}

					@Override
					public void onError(DialogError e) {
						showFacebookError(progressDialog);
					}

					@Override
					public void onCancel() {
						Toast.makeText(MainActivity.this, "FB login cancelled", 
								Toast.LENGTH_LONG).show();
					}
				});
    }
    
    private void showFacebookError(final ProgressDialog progressDialog){
    	if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
    	Toast.makeText(MainActivity.this, "Error logging in to Facebook. " +
    			"Please try again.", Toast.LENGTH_LONG).show();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
