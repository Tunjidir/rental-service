package com.rentalservice.business;

import com.rentalservice.dto.BookDto;
import com.rentalservice.model.Book;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

/**
 * @author olatunji
 */
@Stateless
public class BookHandler {

  @PersistenceContext
  private EntityManager em;

  @TransactionAttribute(REQUIRES_NEW)
  public Response addBook(BookDto dto) {
    final Book book = new Book();

    if(dto == null) {
      return Response.noContent().build();
    }
    book.setName(dto.getName());
    book.setIsbn(dto.getIsbn());
    book.setAvailableCopies(dto.getAvailableCopies());

    em.persist(book);

    return Response.ok(dto).build();
  }

  public Response updateBook(BookDto dto) {
    final var book = findBookByName(dto.getName());

    if(book == null) {
      return Response.noContent().build();
    }

    book.setAvailableCopies(dto.getAvailableCopies());
    book.setIsbn(dto.getIsbn());
    em.merge(book);

    return Response.ok(dto).build();
  }

  public List<BookDto> getBooks() {
    return em.createNamedQuery("Book.findAll", Book.class)
        .getResultList()
        .stream()
        .map(BookHandler::toBookDto)
        .collect(toList());
  }

  public Response deleteBook(String name) {
    em.createQuery("DELETE FROM Book m "
        + "WHERE m.name = :name")
        .setParameter("name", name)
        .executeUpdate();
    return Response.ok().build();
  }

  public static BookDto toBookDto(Book book) {
    BookDto dto = new BookDto();
    dto.setName(book.getName());
    dto.setIsbn(book.getIsbn());
    dto.setAvailableCopies(book.getAvailableCopies());

    return dto;
  }

  public Book findBookByName(String name) {
    return em.createNamedQuery("Book.findByName", Book.class)
        .setParameter("name", name)
        .getSingleResult();
  }
}
