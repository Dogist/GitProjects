/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_todomanagement.resources;

import at.htlpinkafeld.rs_todomanagement.dao.TodoDAO;
import at.htlpinkafeld.rs_todomanagement.model.Todo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Martin Six
 */
@Path("/todos")
public class TodosResource {

  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Todo> getTodosBrowser() {
    List<Todo> todos = new ArrayList<>();
    todos.addAll(TodoDAO.getInstance().getModel().values());
    return todos;
  }


  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<Todo> getTodos() {
    List<Todo> todos = new ArrayList<>();
    todos.addAll(TodoDAO.getInstance().getModel().values());
    return todos;
  }


  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = TodoDAO.getInstance().getModel().size();
    return String.valueOf(count);
  }

  @POST
  @Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void newTodo(@FormParam("id") String id,
      @FormParam("summary") String summary,
      @FormParam("description") String description,
      @Context HttpServletResponse servletResponse) throws IOException {
    Todo todo = new Todo(id, summary,description);
    TodoDAO.getInstance().getModel().put(id, todo);

    servletResponse.sendRedirect("../");
  }

  @Path("{todo}")
  public TodoResource getTodo(@PathParam("todo") String id) {
    return new TodoResource(uriInfo, request, id);
  }
} 