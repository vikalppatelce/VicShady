package com.examples.helloandroid.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.examples.helloandroid.HelloAndroidActivity;
public class HelloAndroidTest extends
		ActivityInstrumentationTestCase2<HelloAndroidActivity> {
	    private HelloAndroidActivity mActivity;
	    private TextView mView;
	    private String resourceString;
	
	public HelloAndroidTest() {
	      super("com.examples.helloandroid", HelloAndroidActivity.class);
	    }
	 @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	        mActivity = this.getActivity();
	        mView = (TextView) mActivity.findViewById(com.examples.helloandroid.R.id.textview);
	        resourceString = mActivity.getString(com.examples.helloandroid.R.string.hello);
	    }
	
	 public void testPreconditions() {
	      assertNotNull(mView);
	    }
	 
	 public void testText() {
	      assertEquals(resourceString,(String)mView.getText());
	    }
}
