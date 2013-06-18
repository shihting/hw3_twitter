package com.codepath.apps.simpletwitterclient;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.codepath.apps.simpletwitterclient.models.Tweet;
import com.codepath.apps.simpletwitterclient.models.User;

import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class TweetsComposeActivity extends Activity{
	Intent intent;
	Bundle bundle;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweets_compose);
		
		intent = this.getIntent();
		bundle = intent.getExtras();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tweets_compose, menu);
		return true;
	}
	
	
	public void onCompose(View v){
		Log.d("DEBUG","onCompose" );
		TextView tv_compose = (TextView)findViewById(R.id.ev_compose);
		String compose_string = tv_compose.getText().toString();
		Log.d("DEBUG",compose_string );
		Log.d("DEBUG","aaaa" );
		try {
			SimpleTwitterApp.getRestClient().compose(compose_string, new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(int arg0, JSONArray jsonTweets) {
					ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
					TweetsAdapter adapter = new TweetsAdapter(getBaseContext(), tweets);
					ListView lv = (ListView)findViewById(R.id.lvTweets);
					lv.setAdapter(adapter);
				}
			});
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			Log.d("DEBUG", e.getMessage());
			e.printStackTrace();
			
		}
		
		Intent intent = new Intent();
		intent.setClass( getApplicationContext(), TweetsActivity.class);
    	startActivity(intent);
  // 	startActivityForResult(intent,0);
		  
     
    
        //TweetsComposeActivity.this.setResult(RESULT_OK, intent);
       TweetsComposeActivity.this.finish();
	}
	
	

	
	public void onCancel(View v){
		finish();
	}


}
