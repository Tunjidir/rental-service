package com.rentalservice.rpc;

import com.rentalservice.dto.BookDto;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.client.Entity.json;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.assertEquals;

/**
 * @author olatunji
 */
@RunWith(Arquillian.class)
public class BookEndpointIT extends AbstractIT {

  private final String baseUrl = "http://localhost:8000/rent/rpc/manager";

  @Test
  public void addBook() {
    final var book = new BookDto();
    book.setName("JAMES BOND 007");
    book.setIsbn("12908ABC67899");
    book.setAvailableCopies(10);

    final Response response = client.target(baseUrl + "/books")
        .request()
        .header("Content-Type", APPLICATION_JSON)
        .post(json(book), Response.class);

    assertEquals(200, response.getStatus());
  }

  @Test
  public void updateBook() {
    final var book = new BookDto();
    book.setName("BLACK BOY");
    book.setIsbn("129838748ABC");
    book.setAvailableCopies(5);

    final var response = client.target(baseUrl + "/books")
        .request()
        .header("Content-Type", APPLICATION_JSON)
        .post(json(book), Response.class);

    assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());

    final var another = new BookDto();
    another.setName("BLACK BOY");
    another.setIsbn("129838748ABC");
    another.setAvailableCopies(3);

    final var otherResponse = client.target(baseUrl + "/update-books")
        .request()
        .header("Content-Type", APPLICATION_JSON)
        .put(json(another), Response.class);

    assertEquals(Response.Status.OK, otherResponse.getStatusInfo().toEnum());

  }

  @Test
  public void getBooks() {
    final var book = new BookDto();
    book.setName("JAMES BOND 007");
    book.setIsbn("12908ABC67899");
    book.setAvailableCopies(10);

    client.target(baseUrl + "/books")
        .request()
        .header("Content-Type", APPLICATION_JSON)
        .post(json(book), Response.class);

    final var response = client.target(baseUrl + "/books")
        .request()
        .header("Content-Type", APPLICATION_JSON)
        .get(new GenericType<List<BookDto>>() {});

    assertEquals(2, response.size());
    assertEquals("JAMES BOND 007", response.get(1).getName());

  }

  @Test
  public void deleteBooks() {
    final var book = new BookDto();
    book.setName("JAMES BOND 007");
    book.setIsbn("12908ABC67899");
    book.setAvailableCopies(10);

    final var response = client.target(baseUrl + "/books")
        .request()
        .header("Content-Type", APPLICATION_JSON)
        .post(json(book), Response.class);

    assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());

    final var otherResponse = client.target(baseUrl + "/books")
        .queryParam("name", "JAMES BOND 007")
        .request()
        .header("Content-Type", APPLICATION_JSON)
        .delete(Response.class);

    assertEquals(200, otherResponse.getStatus());
  }
}
