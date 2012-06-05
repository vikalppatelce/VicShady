package com.examples.androidrssreaderapplication;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class RSSDatabaseHandler extends SQLiteOpenHelper {
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "rssReader";
 
    // Contacts table name
    private static final String TABLE_RSS = "websites";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_LINK = "link";
    private static final String KEY_RSS_LINK = "rss_link";
    private static final String KEY_DESCRIPTION = "description";
 
    public RSSDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RSS_TABLE = "CREATE TABLE " + TABLE_RSS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_LINK
                + " TEXT," + KEY_RSS_LINK + " TEXT," + KEY_DESCRIPTION
                + " TEXT" + ")";
        db.execSQL(CREATE_RSS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RSS);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * Adding a new website in websites table Function will check if a site
     * already existed in database. If existed will update the old one else
     * creates a new row
     * */
    public void addSite(WebSite site) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, site.getTitle()); // site title
        values.put(KEY_LINK, site.getLink()); // site url
        values.put(KEY_RSS_LINK, site.getRSSLink()); // rss link url
        values.put(KEY_DESCRIPTION, site.getDescription()); // site description
 
        // Check if row already existed in database
        if (!isSiteExists(db, site.getRSSLink())) {
            // site not existed, create a new row
            db.insert(TABLE_RSS, null, values);
            db.close();
        } else {
            // site already existed update the row
            updateSite(site);
            db.close();
        }
    }
 
    /**
     * Reading all rows from database
     * */
    public List<WebSite> getAllSites() {
        List<WebSite> siteList = new ArrayList<WebSite>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RSS
                + " ORDER BY id DESC";
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                WebSite site = new WebSite();
                site.setId(Integer.parseInt(cursor.getString(0)));
                site.setTitle(cursor.getString(1));
                site.setLink(cursor.getString(2));
                site.setRSSLink(cursor.getString(3));
                site.setDescription(cursor.getString(4));
                // Adding contact to list
                siteList.add(site);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
 
        // return contact list
        return siteList;
    }
 
    /**
     * Updating a single row row will be identified by rss link
     * */
    public int updateSite(WebSite site) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, site.getTitle());
        values.put(KEY_LINK, site.getLink());
        values.put(KEY_RSS_LINK, site.getRSSLink());
        values.put(KEY_DESCRIPTION, site.getDescription());
 
        // updating row return
        int update = db.update(TABLE_RSS, values, KEY_RSS_LINK + " = ?",
                new String[] { String.valueOf(site.getRSSLink()) });
        db.close();
        return update;
 
    }
 
    /**
     * Reading a row (website) row is identified by row id
     * */
    public WebSite getSite(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_RSS, new String[] { KEY_ID, KEY_TITLE,
                KEY_LINK, KEY_RSS_LINK, KEY_DESCRIPTION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        WebSite site = new WebSite(cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4));
 
        site.setId(Integer.parseInt(cursor.getString(0)));
        site.setTitle(cursor.getString(1));
        site.setLink(cursor.getString(2));
        site.setRSSLink(cursor.getString(3));
        site.setDescription(cursor.getString(4));
        cursor.close();
        db.close();
        return site;
    }
 
    /**
     * Deleting single row
     * */
    public void deleteSite(WebSite site) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RSS, KEY_ID + " = ?",
                new String[] { String.valueOf(site.getId())});
        db.close();
    }
 
    /**
     * Checking whether a site is already existed check is done by matching rss
     * link
     * */
    public boolean isSiteExists(SQLiteDatabase db, String rss_link) {
 
        Cursor cursor = db.rawQuery("SELECT 1 FROM " + TABLE_RSS
                + " WHERE rss_link = '" + rss_link + "'", new String[] {});
        boolean exists = (cursor.getCount() > 0);
        return exists;
    }
 
}
