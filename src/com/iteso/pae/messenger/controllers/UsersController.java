package com.iteso.pae.messenger.controllers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.iteso.pae.messenger.beans.UserBean;
import com.iteso.pae.messenger.daos.UsersDao;

public class UsersController {
	private static UsersController ctrl;
	
	public static String hashPwd(String password){
		  String salt1 = "h@l@";
		  String salt2 = "ad3ies34";
		  String pass = salt1 + password + salt2;
		  byte[] bytesOfMessage;
			try {
				bytesOfMessage = pass.getBytes("UTF-8");
				MessageDigest md = MessageDigest.getInstance("MD5");
				  byte[] thedigest = md.digest(bytesOfMessage);
				  StringBuilder sb = new StringBuilder();
				  for(byte b : thedigest){
					  sb.append(b);
				  }
				  return sb.toString();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
	public UserBean save(UserBean bean){
		UsersDao dao = new UsersDao();
		bean.setPassword(hashPwd(bean.getPassword()));
		return dao.save(bean);
	}
	
	public UserBean update(UserBean bean){
		UsersDao dao = new UsersDao();
		bean.setPassword(hashPwd(bean.getPassword()));
		return dao.update(bean);
	}
	
	public List<UserBean> getList(){
		UsersDao dao = new UsersDao();
		return dao.getUsers();
	}
	
	public boolean delete(String id){
		UsersDao dao = new UsersDao();
		return dao.delete(id);
	}
	

	public static UsersController getController(){
		if(ctrl==null){
			ctrl = new UsersController();
		}
		return ctrl;
	}
}


