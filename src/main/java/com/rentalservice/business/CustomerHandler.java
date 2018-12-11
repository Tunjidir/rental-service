package com.rentalservice.business;

import com.rentalservice.dto.RequestBookDto;
import com.rentalservice.model.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author olatunji
 */
@Stateless
public class CustomerHandler {

  @PersistenceContext
  private EntityManager em;

  public Response borrowBook(List<RequestBookDto> borrow) {
    borrow.forEach(books -> {
        final Book book = findBookByName(books.getName());
        book.setAvailableCopies((book.getAvailableCopies()) - books.getAmount());
        em.merge(book);
    });

    return Response.ok(borrow).build();
  }

  public Response returnBook(List<RequestBookDto> returned) {
    returned.forEach(books -> {
        Book book = findBookByName(books.getName());
        book.setAvailableCopies((book.getAvailableCopies()) + books.getAmount());
        em.merge(book);
    });

    return Response.ok().build();
  }

  public Book findBookByName(String name) {
    return em.createQuery("SELECT m FROM Book m "
        + "WHERE m.name = :name", Book.class)
        .setParameter("name", name)
        .getSingleResult();
  }
}
