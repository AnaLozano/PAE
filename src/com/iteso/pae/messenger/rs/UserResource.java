package com.iteso.pae.messenger.rs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.iteso.pae.messenger.beans.MessageBean;
import com.iteso.pae.messenger.beans.UserBean;
import com.iteso.pae.messenger.controllers.MessagesController;
import com.iteso.pae.messenger.controllers.UsersController;

public class UserResource {
	@GET
	  @Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
	  public Response getUser(@PathParam("id") String id){
		  System.out.println("getting user with id["+id+"]");
		  
		  List<UserBean> list = UsersController.getController().getList();
		  for(UserBean b : list){
			  if(b.getId() == Integer.parseInt(id)){
				  return Response.ok(b).build();
			  }
		  }
		  return Response.noContent().build();
	  }

	  @PUT
	  @Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
	  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
	  public Response updateUser(@PathParam("id") String id,UserBean bean){
		  System.out.println("updating user with id["+id+"]");
		  bean.setId(Integer.parseInt(id));
		  bean = UsersController.getController().update(bean);
		  return Response.ok(bean).build();
	  }
	  
	  @DELETE
	  @Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
	  @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
	  public Response deleteMessage(@PathParam("id") String id){
		  System.out.println("deleting user with id["+id+"]");
		 if(UsersController.getController().delete(id)){
			 return Response.ok().build();
		 }
		  return Response.notModified().build();
	  }
}
