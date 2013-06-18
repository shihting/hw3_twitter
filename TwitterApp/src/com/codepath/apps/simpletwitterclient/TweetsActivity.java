package com.codepath.apps.simpletwitterclient;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ListView;

import com.codepath.apps.simpletwitterclient.models.Tweet;
import com.codepath.apps.simpletwitterclient.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TweetsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);
       // getUser();
     
        getHomeTimeLine();
      
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tweets, menu);
        
        
        MenuItem mRefresh = menu.findItem(R.id.mit_refresh);
        MenuItem mEdit = menu.findItem(R.id.mit_edit);
   
        
        mRefresh.setOnMenuItemClickListener(new OnMenuItemClickListener (){
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				getHomeTimeLine();
				return false;
			}
        });
        
        
        mEdit.setOnMenuItemClickListener(new OnMenuItemClickListener (){
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				Intent intent = new Intent();
				intent.setClass( getApplicationContext(), TweetsComposeActivity.class);
				startActivity(intent);
		    	//startActivityForResult(intent,0);
		    	//getHomeTimeLine();
				// TODO Auto-generated method stub
				return false;
			}
        });
        
        
        return true;

        
        
    }
    
    public void getHomeTimeLine() {
        
    	SimpleTwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
    		@Override
    		public void onSuccess(int arg0, JSONArray jsonTweets) {
    			Log.d("DEBUG", "#########" );
    			//Log.d("DEBUG", jsonTweets.toString());
    			ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
    		
    			TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
    			ListView lv = (ListView)findViewById(R.id.lvTweets);
    			lv.setAdapter(adapter);
    		}
    	});
    	
    }
    
 
    
}
