package com.rentalservice.rpc;

import com.rentalservice.business.CustomerHandler;
import com.rentalservice.dto.RequestBookDto;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

/**
 * @author olatunji
 */
@Path("customer")
public class CustomerEndpoint {

  @EJB
  private CustomerHandler customerHandler;

  @PUT
  @Path("books")
  @Consumes({APPLICATION_JSON, APPLICATION_XML})
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  public Response borrowBook(List<RequestBookDto> books) {

    return customerHandler.borrowBook(books);
  }

  @PUT
  @Path("books/return-books")
  @Consumes({APPLICATION_JSON, APPLICATION_XML})
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  public Response returnBook(List<RequestBookDto> books) {

    return customerHandler.returnBook(books);
  }

}
