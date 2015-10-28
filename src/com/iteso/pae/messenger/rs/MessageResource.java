package com.iteso.pae.messenger.rs;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;

import com.iteso.pae.messenger.beans.MessageBean;
import com.iteso.pae.messenger.controllers.MessagesController;

public class MessageResource {
	@Context
    private HttpServletRequest request;
	
	
		@GET
	  @Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
	  	
	  public Response getMessage(@PathParam("id") String id){	
		HttpSession session = request.getSession(false);
		if(session != null){
		  System.out.println("getting message with id["+id+"]");
		  List<MessageBean> list = MessagesController.getController().getList();
		  for(MessageBean b : list){
			  if(b.getId().equalsIgnoreCase(id)){
				  return Response.ok(b).build();
			  }
		  }
		  return Response.noContent().build();
		}else{
			System.out.println("Session invalid...");
			return Response.status(Response.Status.FORBIDDEN).build();	
		}
	  }
      
	  @PUT
	  @Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
	  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
	  public Response updateMessage(@PathParam("id") String id,MessageBean bean){
		HttpSession session = request.getSession(false);
		if(session != null){	  
		  System.out.println("updating message with id["+id+"]");
		  bean.setId(id);
		  bean = MessagesController.getController().updateMsg(bean);
		  return Response.ok(bean).build();
		}else{
			return Response.noContent().build();
		}
	  }
	  
	  @POST
	  @Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
	  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
	  public Response createMessage(@PathParam("id") String id,MessageBean bean){
		HttpSession session = request.getSession(false);
		if(session != null){	  
		  System.out.println("updating message with id["+id+"]");
		  bean.setId(id);
		  bean.setMessage("Nuevo mensaje");
		  bean.setCreateDate(MessagesController.getController().getFechaActual());
		  bean = MessagesController.getController().saveBean(bean);
		  return Response.ok(bean).build();
		}else{
			return Response.noContent().build();
		}
	  }
	  
	  @DELETE
	  @Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
	  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
	  public Response deleteMessage(@PathParam("id") String id){
		HttpSession session = request.getSession(false);
		if (session != null){
		  System.out.println("deleting message with id["+id+"]");
		 if(MessagesController.getController().deleteMsg(id)){
			 return Response.ok().build();
		 }
		  return Response.notModified().build();
		}else{
			return Response.notModified().build();
		}
	  }
}
