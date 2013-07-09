package com.zipper.zipcloset;

import java.util.List;

import com.kinvey.KCSClient;
import com.kinvey.KinveySettings;
import com.kinvey.util.ListCallback;
import com.zipper.zipcloset.clothingEntity;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TagsHistoryActivity extends Activity {
	
	private static final String KINVEY_KEY = "kid_PVAtuuzi2f";
	private static final String KINVEY_SECRET_KEY = "2cab4a07424945e981478fcfc02341af";
	
	private KCSClient kinveyClient;
	private ListView tagsList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tags_history);
		tagsList = (ListView) findViewById(R.id.tags_listview);
        
		// Initialize Kinvey
        KinveySettings settings = new KinveySettings(KINVEY_KEY, KINVEY_SECRET_KEY);
        kinveyClient = KCSClient.getInstance(this.getApplicationContext(), settings);
        
        refreshList();
    }

    
    private void refreshList(){
    	final ProgressDialog pd = ProgressDialog.show(TagsHistoryActivity.this, 
    			"", "Loading...", true);
    	
        // Fetch tag history
    	
        kinveyClient.mappeddata("tags").fetch(clothingEntity.class, new ListCallback<clothingEntity>() {

			@Override
			public void onSuccess(final List<clothingEntity> results) {
				
				// Update the list
				tagsList.setAdapter(new ArrayAdapter<clothingEntity>(TagsHistoryActivity.this,
                        android.R.layout.simple_list_item_1, results){
					@Override
					public View getView(int pos, View convertview, ViewGroup parent){
						TextView text = new TextView(TagsHistoryActivity.this);
						text.setText(results.get(pos).getId());
						return text;
					}
				});
				pd.dismiss();
			}
			
			@Override
            public void onFailure(Throwable error) {
                Log.e("Tapped", "Received error response", error);
                pd.dismiss();
            }
		});
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tags_history, menu);
		return true;
	}

}
