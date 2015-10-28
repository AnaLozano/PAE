package com.iteso.pae.messenger.rs;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.iteso.pae.messenger.beans.UserBean;
import com.iteso.pae.messenger.controllers.UsersController;

@Path("/users")
public class UsersResource {

 
  @GET
  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
  /**
   * Returns a list of messages.
   * @return
   */
  public Response getMessages() {
	  System.out.println("Getting list of users...");
	  List<UserBean> list = UsersController.getController().getList();
	  GenericEntity<List<UserBean>> entity = new GenericEntity<List<UserBean>>(list) {};
	  System.out.println(list);
	  if(list.isEmpty()){//check if there are no messages
		  return Response.noContent().build();
	  }else{
		  return Response.ok(entity).build();
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
  public Response saveMessage(UserBean bean){
	  System.out.println("Saving user...");
	  
	  bean = UsersController.getController().save(bean);
	  System.out.println(bean);
	  return Response.ok(bean).build();
  }

  
  @Path("/{id}")
  public UserResource getCustomer(@PathParam("id") String id) {
	  UserResource msg = new UserResource(); // Find a customer object
     return msg;
  }
} 