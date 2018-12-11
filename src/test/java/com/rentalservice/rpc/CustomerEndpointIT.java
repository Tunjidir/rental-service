package com.rentalservice.rpc;

import com.rentalservice.dto.RequestBookDto;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

import static java.util.Collections.singletonList;
import static javax.ws.rs.client.Entity.json;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.assertEquals;

/**
 * @author olatunji
 */
@RunWith(Arquillian.class)
public class CustomerEndpointIT extends AbstractIT {

  private final String baseUrl = "http://localhost:8000/rent/rpc/customer";

  @Test
  public void borrowBook() {
    final var requestBook = new RequestBookDto();
    requestBook.setName("FIFTY SHADES OF GREY");
    requestBook.setAmount(2);

    final var response = client.target(baseUrl + "/books")
        .request()
        .header("Content-Type", APPLICATION_JSON)
        .put(json(singletonList(requestBook)), Response.class);

    assertEquals(200, response.getStatus());
  }

  @Test
  public void returnBook() {
    final var requestBook = new RequestBookDto();
    requestBook.setName("FIFTY SHADES OF GREY");
    requestBook.setAmount(2);

    final var response = client.target(baseUrl + "/books")
        .request()
        .header("Content-Type", APPLICATION_JSON)
        .put(json(singletonList(requestBook)), Response.class);

    assertEquals(200, response.getStatus());

    final var returnBook = new RequestBookDto();
    returnBook.setName("FIFTY SHADES OF GREY");
    returnBook.setAmount(2);

    final var otherResponse = client.target(baseUrl + "/books/return-books")
        .request()
        .header("Content-Type", APPLICATION_JSON)
        .put(json(singletonList(returnBook)), Response.class);

    assertEquals(200, otherResponse.getStatus());
  }

}
