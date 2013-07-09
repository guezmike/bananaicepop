package com.zipper.zipcloset;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zipper.zipcloset.FacebookService.OnRequestErrorListener;
import com.zipper.zipcloset.FacebookService.OnRequestResultListener;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
	

		Button zipButton = (Button) findViewById(R.id.tagButton);
		zipButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainMenu.this, ZipActivity.class));
			}
		});
		
		Button tagHistoryButton = (Button) findViewById(R.id.allZipButton);
		tagHistoryButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainMenu.this, TagsHistoryActivity.class));
			}
		});

		final EditText facebookPostContent = (EditText) findViewById(R.id.facebookPost);

		Button facebookPostButton = (Button) findViewById(R.id.postToFacebook);
		facebookPostButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				postToFacebook(facebookPostContent.getText().toString());
			}
		});
	}
	
	//post string to facebook
		private void postToFacebook(String content) {

			Bundle params = new Bundle();
			params.putString("message", content);

			FacebookService.requestAsync("me/feed", params, FacebookService.RequestType.POST,
					new OnRequestResultListener() {

						@Override
						public void onRequestResult(String result) {
							facebookPostResult(result);
							System.out.println(result);
						}
					}, new OnRequestErrorListener() {

						@Override
						public void onRequestError() {
							facebookPostResult("Error");
						}
					});
		}

		private void facebookPostResult(final String result) {
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					Toast.makeText(MainMenu.this, "Facebook posted with result: " + result, 
							Toast.LENGTH_SHORT).show();
				}
			});
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

}
