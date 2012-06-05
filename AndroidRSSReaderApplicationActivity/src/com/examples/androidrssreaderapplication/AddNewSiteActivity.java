package com.examples.androidrssreaderapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
 
public class AddNewSiteActivity extends Activity {
 
    Button btnSubmit;
    Button btnCancel;
    EditText txtUrl;
    TextView lblMessage;
 
    RSSParser rssParser = new RSSParser();
 
    RSSFeed rssFeed;
 
    // Progress Dialog
    private ProgressDialog pDialog;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_site);
 
        // buttons
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        txtUrl = (EditText) findViewById(R.id.txtUrl);
        lblMessage = (TextView) findViewById(R.id.lblMessage);
 
        // Submit button click event
        btnSubmit.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                String url = txtUrl.getText().toString();
 
                // Validation url
                Log.d("URL Length", "" + url.length());
                // check if user entered any data in EditText
                if (url.length() > 0) {
                    lblMessage.setText("");
                    String urlPattern = "^http(s{0,1})://[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*";
                    if (url.matches(urlPattern)) {
                        // valid url
                        new loadRSSFeed().execute(url);
                    } else {
                        // URL not valid
                        lblMessage.setText("Please enter a valid url");
                    }
                } else {
                    // Please enter url
                    lblMessage.setText("Please enter website url");
                }
 
            }
        });
 
        // Cancel button click event
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
 
    /**
     * Background Async Task to get RSS data from URL
     * */
    class loadRSSFeed extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddNewSiteActivity.this);
            pDialog.setMessage("Fetching RSS Information ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        /**
         * getting Inbox JSON
         * */
        @Override
        protected String doInBackground(String... args) {
            String url = args[0];
            rssFeed = rssParser.getRSSFeed(url);
            Log.d("rssFeed", " "+ rssFeed);
            if (rssFeed != null) {
                Log.e("RSS URL",
                        rssFeed.getTitle() + "" + rssFeed.getLink() + ""
                                + rssFeed.getDescription() + ""
                                + rssFeed.getLanguage());
                RSSDatabaseHandler rssDb = new RSSDatabaseHandler(
                        getApplicationContext());
                WebSite site = new WebSite(rssFeed.getTitle(), rssFeed.getLink(), rssFeed.getRSSLink(),
                        rssFeed.getDescription());
                rssDb.addSite(site);
                Intent i = getIntent();
                // send result code 100 to notify about product update
                setResult(100, i);
                finish();
            } else {
                // updating UI from Background Thread
                runOnUiThread(new Runnable() {
                    public void run() {
                        lblMessage.setText("Rss url not found. Please check the url or try again");
                    }
                });
            }
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String args) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    if (rssFeed != null) {
 
                    }
 
                }
            });
 
        }
 
    }
}