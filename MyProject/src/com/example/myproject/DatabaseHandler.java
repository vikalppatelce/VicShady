package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DatabaseHandler extends SQLiteOpenHelper {
	
	
	public static final String DATABASE_NAME="registerdb";
	public static final int  DATABASE_VERSION=1;
	public static final String TABLE="register";
	private static final String _ID="id";
	public static final String FIRST_NAME="firstname";
	public static final String LAST_NAME="lastname";
	public static final String EMAIL1="email1";
	public static final String PASSWORD="password";
	public static final String CONFIRM_PASSWORD="confirmpassword";
	public static final String SECURITY1="security1";
	public static final String ANSWER1="answer1";
	public static final String SECURITY2="security2";
	public static final String ANSWER2="answer2";
    public static final String EMAIL2="email2";
    public static final String UPLOAD="upload";
    public static final String[] allColumns={_ID,FIRST_NAME,LAST_NAME,EMAIL1,PASSWORD,CONFIRM_PASSWORD
    	,SECURITY1,ANSWER1,SECURITY2,ANSWER2,EMAIL2,UPLOAD};
    
    public DatabaseHandler(Context context)   
    {
    super(context,DATABASE_NAME,null,DATABASE_VERSION);	
    }
    
    
    public  void onCreate(SQLiteDatabase database)
    {
    	String DATABASE_CREATE = "create table "   
                + TABLE  
                +"(" + _ID +" " + "integer primary key autoincrement," 
                + FIRST_NAME+" " + "text not null,"
                + LAST_NAME +" "+ "text not null,"
                + EMAIL1 +" "+ "text not null,"
                + PASSWORD +" "+ "text not null,"
                + CONFIRM_PASSWORD +" "+ "text not null,"
                + SECURITY1 +" " +"text,"
                + ANSWER1 +" "+ "text ,"
                + SECURITY2 +" "+ "text,"
                + ANSWER2 +" "+ "text,"
                + EMAIL2 +" "+"text,"
                + UPLOAD + " "+"text"
                + ")";
                
    	
    	database.execSQL(DATABASE_CREATE);
    	Log.w("Create:","Creating Database...");
        
    }
	
	public  void onUpgrade(SQLiteDatabase database,int oldversion,int newversion)
	{
		Log.w(SignupActivity.class.getName(),"Upgrading Database Version"
	+ oldversion + "to" +newversion );
		//database.execSQL("DROP TABLE IF EXISTS"+ TABLE);
	    onCreate(database);
	}

	public void addregister(Register register)
	{
 
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(FIRST_NAME,register.getFName());
		values.put(LAST_NAME, register.getLName());
		values.put(EMAIL1,register.getEmail1());
		values.put(PASSWORD, register.getPass());
		values.put(CONFIRM_PASSWORD,register.getCPass());
		
		db.insert(TABLE, null, values);
		db.close();
		
	}
	public void addregister1(Register register)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		String sec,ans1,sec2,ans2,mail2;
		sec=register.getSecurity1();
		ans1=register.getAnswer1();
		sec2=register.getSecurity2();
		ans2=register.getAnswer2();
		mail2=register.getEmail2();
		String[] wid={Integer.toString(register.getId())};
		String sql="INSERT INTO  " + TABLE + "(" 
				+ SECURITY1 + ","
                + ANSWER1 + ","
                + SECURITY2 + ","
				+ ANSWER2 + ","
				+ EMAIL2 
                +") VALUES(" 
				+ sec + ","
				+ ans1 + ","
				+ sec2 + ","
				+ ans2 + ","
				+ mail2 
				+") "
                +"WHERE "+_ID +" = ?";
//		db.update(TABLE,values1, _ID +" = ?",new String[]{(String.valueOf(register.getId())) });
		/*db.execSQL("INSERT INTO  " + TABLE + "(" 
				+ SECURITY1 + ","
                + ANSWER1 + ","
                + SECURITY2 + ","
				+ ANSWER2 + ","
				+ EMAIL2 
                +") VALUES(" 
				+ sec + ","
				+ ans1 + ","
				+ sec2 + ","
				+ ans2 + ","
				+ mail2 
				+") "
                +"WHERE "+_ID +" = "+register.getId());*/
		
		db.rawQuery(sql, wid);
		db.close();
	}

	public int getId(Register register)
	{
		SQLiteDatabase db= this.getReadableDatabase();
		Cursor cursor=db.query(TABLE,allColumns, null, null, null, null, null);
		cursor.moveToLast();
		int i=Integer.parseInt(cursor.getString(0));
		return i;
	}
	
/*public void onUpdate(SQLiteDatabase database)
{
	database.execSQL("ALTER TABLE"+ TABLE+"ADD"
						+SECURITY1+ " " +"text,"
						+ ANSWER1 +" "+ "text ,"
		                + SECURITY2 +" "+ "text,"
		                + ANSWER2 +" "+ "text,"
		                + EMAIL2 +" "+"text,"
		                
			         );
}*/
	
}
