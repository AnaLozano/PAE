package com.iteso.pae.messenger.rs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.iteso.pae.messenger.beans.MessageBean;
import com.iteso.pae.messenger.beans.UserBean;
import com.iteso.pae.messenger.controllers.MessagesController;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/messages")
public class MessagesResource {
	@Context
    private HttpServletRequest request;
 
  @GET
  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
  /**
   * Returns a list of messages.
   * @return
   */
  public Response getMessages() {

	  System.out.println("Checking valid session...");
	  HttpSession session = request.getSession(false);
	  if(session != null){
		  Object bean = session.getAttribute("user");
		  UserBean user = (bean==null)? null : (UserBean)bean;
		  System.out.println("Logged user...["+user+"]");

		  System.out.println("Getting list of messages...");
		  List<MessageBean> list = MessagesController.getController().getList();
		  GenericEntity<List<MessageBean>> entity = new GenericEntity<List<MessageBean>>(list) {};
		  System.out.println(list);
		  if(list.isEmpty()){//check if there are no messages
			  return Response.noContent().build();
		  }else{
			  return Response.ok(entity).build();
		  }
	  }else{
		  //
		  System.out.println("Session invalid...");
		  
		  return Response.status(Response.Status.FORBIDDEN).build();
	  }
	  
  }

  
  
  @POST
  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
  @Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
  /**
   * Saves a message
   * @param msg
   * @return
   */
  public Response saveMessage(MessageBean msg){
	  System.out.println("Saving message...");
	  msg = MessagesController.getController().saveBean(msg);
	  return Response.ok(msg).build();
  }

  
  @Path("/{id}")
  public MessageResource getCustomer(@PathParam("id") String id) {
     MessageResource msg = new MessageResource(); // Find a customer object
     return msg;
  }
} 