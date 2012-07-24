package com.example.myproject;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class DashBoardActivity  extends Activity{

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		GridView gridview=(GridView)findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));
		 gridview.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	                //Toast.makeText(DashBoardActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	            switch (position) {
				case 0:
					Toast.makeText(getApplicationContext(), "Edit...", Toast.LENGTH_SHORT).show();
					break;
				case 1:
					Toast.makeText(getApplicationContext(), "Connect Facebook...", Toast.LENGTH_SHORT).show();
					break;
				case 2:
					Toast.makeText(getApplicationContext(), "Change Password...", Toast.LENGTH_SHORT).show();
					break;
				case 3:
					Toast.makeText(getApplicationContext(), "Tweeting...", Toast.LENGTH_SHORT).show();
					break;
				case 4:
					Toast.makeText(getApplicationContext(), "Delete...", Toast.LENGTH_SHORT).show();
					break;
				case 5:
					Toast.makeText(getApplicationContext(), "Log Off...", Toast.LENGTH_SHORT).show();
					break;

				default:
					
					break;
				}
	            
	            
	            }
	        });
	}
	
	
}
