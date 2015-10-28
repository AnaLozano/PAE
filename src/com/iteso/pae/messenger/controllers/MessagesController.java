package com.iteso.pae.messenger.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iteso.pae.messenger.beans.MessageBean;
import com.iteso.pae.messenger.daos.MessagesDao;

/**
 * Singleton class to simulate a Data Base.
 * @author Administrator
 *
 */
public class MessagesController {
	private static MessagesController ctrl;
	private static int key = 1;
	
	private List<MessageBean> msgs ;
	
	private MessagesController(){
		msgs = new ArrayList<MessageBean>();
	}
	public MessageBean saveBean(MessageBean bean){
		MessagesDao dao = new MessagesDao();
		return dao.saveBean(bean);
	}
	public List<MessageBean> getList(){
		MessagesDao dao = new MessagesDao();
		return dao.getMessages();
	}
	
	public MessageBean updateMsg(MessageBean bean){
		MessagesDao dao = new MessagesDao();
		bean = dao.updateMessage(bean);
		return bean;
	}
	
	public boolean deleteMsg(String id){
		MessagesDao dao = new MessagesDao();
		return dao.deleteMessage(id);
		
	}
	
	public static String getFechaActual() {
	        Date ahora = new Date();
	        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
	        return formateador.format(ahora);
	    }
	
	public static MessagesController getController(){
		if(ctrl==null){
			ctrl = new MessagesController();
			MessageBean b = new MessageBean();
			b.setMessage("My default saved message!");
			ctrl.saveBean(b);
		}
		return ctrl;
	}
	
}
