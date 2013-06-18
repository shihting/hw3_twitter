package com.codepath.apps.simpletwitterclient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.FlickrApi;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    /*public static final String REST_URL = "https://api.twitter.com/1.1/"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "zyTH34PSVK2mIKZ7jsNEeQ";       // Change this
    public static final String REST_CONSUMER_SECRET = "OJ9Zs3U4FbsI3A75rs4CcaTt36ASqb5vSIOfpGOANk"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://simpletwitter"; // Change this (here and in manifest)
    */
  
   public static final String REST_URL = "https://api.twitter.com/1.1/"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "ee6u7DjsTJ3PRSLhNjK9Q";       // Change this
    public static final String REST_CONSUMER_SECRET = "vZRju4M9WE1er8NF4i9JD1WMyXdVJ1RO42qUJeZstgY"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://simpletwitter"; // Change this (here and in manifest)
    
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
      
    }
    
    public void getHomeTimeline(AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("statuses/home_timeline.json?count=25");
    	//RequestParams params = new RequestParams();
    	//params.put("count", "25");
    	//params.put("format", "json");
  
    	client.get(url, null, handler);
    }
    
    
    public void getVerifyCredentials(AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("account/settings.json");
    	//statuses/home_timeline.json
    	//String url = getApiUrl("account/verify_credentials.json");
    	client.get(url, null, handler);
    }
    
  /*  
    public void getusers_profile_banner(AsyncHttpResponseHandler handler) {
    	String url = getApiUrl("/users/profile_banner.json");
    	//statuses/home_timeline.json
    	//String url = getApiUrl("account/verify_credentials.json");
    	client.get(url, null, handler);
    }*/
    
    // CHANGE THIS
    // DEFINE METHODS for different API endpoints here
    public void getInterestingnessList(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList");
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        params.put("format", "json");
        client.get(apiUrl, params, handler);
    }
    
    
    
 // CHANGE THIS
    // DEFINE METHODS for different API endpoints here
    public void compose(String composeString, AsyncHttpResponseHandler handler) throws UnsupportedEncodingException {
        String apiUrl = getApiUrl("statuses/update.json");
        // Can specify query string params directly or through RequestParams.
        RequestParams params = new RequestParams();
        //params.put("format", "json");
        Log.d("DEBUG", URLEncoder.encode(composeString, "UTF-8"));
        params.put("status", URLEncoder.encode(composeString, "UTF-8"));
        
        
        client.post(apiUrl, params, handler);
     
    }
    
    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
     * 2. Define the parameters to pass to the request (query or body)
     *    i.e RequestParams params = new RequestParams("foo", "bar");
     * 3. Define the request method and make a call to the client
     *    i.e client.get(apiUrl, params, handler);
     *    i.e client.post(apiUrl, params, handler);
     */
}