package com.example.myproject;

public class Register {
 
	int _id;
	String _fname,_lname,_email1,_pass,_cpass,_security1,_answer1,_security2,_answer2,_upload,_email2;
	
	public Register(String fname, String lname, String email1, String pass, String cpass)
	{
		this._fname=fname;
		this._lname=lname;
		this._email1=email1;
		this._pass=pass;
		this._cpass=cpass;
	}
	
	public  Register(int id,String security1, String answer1, String security2, String answer2, String email2)	
	{
		this._security1=security1;
		this._answer1=answer1;
		this._security2=security2;
		this._answer2=answer2;
		this._email2=email2;
		this._id=id;
	}
	public Register()
	{
		
	}
	
	public Register(String upload)
	{
		this._upload=upload;
	}
	
	public String getFName()
	{
		return this._fname;
	}
	
	public String getLName()
	{
		return this._lname;
	}
	
	public String getEmail1()
	{
		return this._email1;
	}
	
	public String getPass()
	{
		return this._pass;
	}
	
	public String getCPass()
	{
		return this._cpass;
	}
	public String getSecurity1()
	{
		return this._security1;
	}
	
	public String getAnswer1()
	{
		return this._answer1;
	}
	
	public String getSecurity2()
	{
		return this._security2;
	}
	public String getAnswer2()
	{
		return this._answer2;
	}
	public String getEmail2()
	{
		return this._email2;
	}
	public String getUpload()
	{
		return this._upload;
	}
	public int getId()
	{
		return this._id;
	}
	
	public void setFName(String fname)
	{
		this._fname=fname;
	}
	
	public void setLName(String lname)
	{
		this._lname=lname;
	}
	
	public void getEmail1(String email1)
	{
		this._email1=email1;
	}
	
	public void setPass(String pass)
	{
		this._pass=pass;
	}
	
	public void setCPass(String cpass)
	{
	     this._cpass=cpass;
	}
	public void  setSecurity1(String security1)
	{
		this._security1=security1;
	}
	
	public void setAnswer1(String answer1)
	{
		this._answer1=answer1;
	}
	
	public void setSecurity2(String security2)
	{
		this._security2=security2;
	}
	public void setAnswer2(String answer2)
	{
		this._answer2=answer2;
	}
	public void  setEmail2(String email2)
	{
		this._email2=email2;
	}
	public void setUpload(String upload)
	{
		this._upload=upload;
	}

}
