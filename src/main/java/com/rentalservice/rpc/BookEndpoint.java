package com.rentalservice.rpc;

import com.rentalservice.business.BookHandler;
import com.rentalservice.dto.BookDto;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

/**
 * @author olatunji
 */
@Path("manager")
public class BookEndpoint {

  @EJB
  private BookHandler bookHandler;

  @POST
  @Path("books")
  @Consumes({APPLICATION_JSON, APPLICATION_XML})
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  public Response addBook(BookDto dto) {

    return bookHandler.addBook(dto);
  }

  @PUT
  @Path("update-books")
  @Consumes({APPLICATION_JSON, APPLICATION_XML})
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  public Response updateBook(BookDto bookDto) {

    return bookHandler.updateBook(bookDto);
  }

  @GET
  @Path("books")
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  public List<BookDto> getBooks() {

    return bookHandler.getBooks();
  }

  @DELETE
  @Path("books")
  @Consumes({APPLICATION_JSON, APPLICATION_XML})
  public Response deleteBook(@QueryParam("name") String name) {

    return bookHandler.deleteBook(name);
  }
}
