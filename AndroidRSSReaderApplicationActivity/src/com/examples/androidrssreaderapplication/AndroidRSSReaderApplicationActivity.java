package com.examples.androidrssreaderapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
 
public class AndroidRSSReaderApplicationActivity extends Activity {
    // Progress Dialog
    private ProgressDialog pDialog;
 
    // Array list for list view
    ArrayList<HashMap<String, String>> rssFeedList;
 
    RSSParser rssParser = new RSSParser();
 
    RSSFeed rssFeed;
 
    // button add new website
    ImageButton btnAddSite;
 
    // array to trace sqlite ids
    String[] sqliteIds;
 
    public static String TAG_ID = "id";
    public static String TAG_TITLE = "title";
    public static String TAG_LINK = "link";
 
    // List view
    ListView lv;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_list);
 
        btnAddSite = (ImageButton) findViewById(R.id.btnAddSite);
 
        // Hashmap for ListView
        rssFeedList = new ArrayList<HashMap<String, String>>();
 
        /**
         * Calling a background thread which will load
         * web sites stored in SQLite database
         * */
        new loadStoreSites().execute();
 
        // selecting single ListView item
        lv = (ListView) findViewById(R.id.list); 
 
        // Launching new screen on Selecting Single ListItem
        lv.setOnItemClickListener(new OnItemClickListener() {
 
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                String sqlite_id = ((TextView) view.findViewById(R.id.sqlite_id)).getText().toString();
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), ListRSSItemsActivity.class);
                // passing sqlite row id
                in.putExtra(TAG_ID, sqlite_id);
                startActivity(in);
            }
        });
 
        /**
         * Add new website button click event listener
         * */
        btnAddSite.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddNewSiteActivity.class);
                // starting new activity and expecting some response back
                // depending on the result will decide whether new website is
                // added to SQLite database or not
                startActivityForResult(i, 100);
            }
        });
    }
 
    /**
     * Response from AddNewSiteActivity.java
     * if response is 100 means new site is added to sqlite
     * reload this activity again to show
     * newly added website in listview
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }
 
    /**
     * Building a context menu for listview
     * Long press on List row to see context menu
     * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
        ContextMenuInfo menuInfo) {
      if (v.getId()==R.id.list) {
        menu.setHeaderTitle("Delete");
            menu.add(Menu.NONE, 0, 0, "Delete Feed");
      }
    }
 
    /**
     * Responding to context menu selected option
     * */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
      AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
      int menuItemIndex = item.getItemId();
      // check for selected option
      if(menuItemIndex == 0){
          // user selected delete
          // delete the feed
          RSSDatabaseHandler rssDb = new RSSDatabaseHandler(getApplicationContext());
          WebSite site = new WebSite();
          site.setId(Integer.parseInt(sqliteIds[info.position]));
          rssDb.deleteSite(site);
          //reloading same activity again
          Intent intent = getIntent();
          finish();
          startActivity(intent);
      }
 
      return true;
    }
 
    /**
     * Background Async Task to get RSS data from URL
     * */
    class loadStoreSites extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(
                    AndroidRSSReaderApplicationActivity.this);
            pDialog.setMessage("Loading websites ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        /**
         * getting all stored website from SQLite
         * */
        @Override
        protected String doInBackground(String... args) {
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    RSSDatabaseHandler rssDb = new RSSDatabaseHandler(
                            getApplicationContext());
 
                    // listing all websites from SQLite
                    List<WebSite> siteList = rssDb.getAllSites();
 
                    sqliteIds = new String[siteList.size()];
 
                    // loop through each website
                    for (int i = 0; i < siteList.size(); i++) {
 
                        WebSite s = siteList.get(i);
 
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
 
                        // adding each child node to HashMap key => value
                        map.put(TAG_ID, s.getId().toString());
                        map.put(TAG_TITLE, s.getTitle());
                        map.put(TAG_LINK, s.getLink());
 
                        // adding HashList to ArrayList
                        rssFeedList.add(map);
 
                        // add sqlite id to array
                        // used when deleting a website from sqlite
                        sqliteIds[i] = s.getId().toString();
                    }
                    /**
                     * Updating list view with websites
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            AndroidRSSReaderApplicationActivity.this,
                            rssFeedList, R.layout.site_list_row,
                            new String[] { TAG_ID, TAG_TITLE, TAG_LINK },
                            new int[] { R.id.sqlite_id, R.id.title, R.id.link });
                    // updating listview
                    lv.setAdapter(adapter);
                    registerForContextMenu(lv);
                }
            });
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String args) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
        }
 
    }
}