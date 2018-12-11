package com.rentalservice.business;

import com.rentalservice.dto.BookDto;
import com.rentalservice.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author olatunji
 */
@RunWith(MockitoJUnitRunner.class)
public class BookHandlerTest {

  @Mock
  EntityManager manager;

  @InjectMocks
  BookHandler handler;

  private List<Book> dummyResult;

  @Mock
  Response response;

  @Mock
  TypedQuery query;


  @Before
  public void setup() {
    dummyResult = new ArrayList<>();

    final Book bookDto = new Book();
    bookDto.setName("STRAIGHT OUTTA COMPTON");
    bookDto.setIsbn("AB5690M");
    bookDto.setAvailableCopies(10);

    dummyResult.add(bookDto);
  }

  @Test
  public void addBook() {
    final BookDto movie = new BookDto();
    movie.setName("STRAIGHT OUTTA COMPTON");
    movie.setIsbn("AB5690M");
    movie.setAvailableCopies(10);

    response = handler.addBook(movie);
    verify(manager, times(1)).persist(any());
    assertEquals(200, response.getStatus());
  }

  @Test
  public void findBookByName() {
    final var name = "LONG WALK TO FREEDOM";

    final Book book = new Book();
    book.setName("LONG WALK TO FREEDOM");
    book.setIsbn("ACC78907");
    book.setAvailableCopies(10);

    when(manager.createNamedQuery(any(String.class), eq(Book.class))).thenReturn(query);
    when(query.setParameter(any(String.class), any(String.class))).thenReturn(query);
    when(query.getSingleResult()).thenReturn(book);

    final var mov = handler.findBookByName(name);
    verify(manager).createNamedQuery(any(String.class), eq(Book.class));
    verify(query).setParameter(any(String.class), any(String.class));
    verify(query).getSingleResult();

    assertEquals(book.getIsbn(), mov.getIsbn());
  }

  @Test
  public void updateBook() {
    final Book mozz = new Book();
    mozz.setName("STRIAGHT OUTTA COMPTON");
    mozz.setIsbn("1234ACDD");
    mozz.setAvailableCopies(9);

    when(manager.createNamedQuery(any(String.class), eq(Book.class))).thenReturn(query);
    when(query.setParameter(any(String.class), any(String.class))).thenReturn(query);
    when(query.getSingleResult()).thenReturn(mozz);

    final BookDto updatedMovie = new BookDto();
    updatedMovie.setName("STRAIGHT OUTTA COMPTON");
    updatedMovie.setIsbn("AC5770");
    updatedMovie.setAvailableCopies(3);

    response = handler.updateBook(updatedMovie);
    verify(manager).createNamedQuery(any(String.class), eq(Book.class));
    verify(manager, times(1)).merge(any());
    assertEquals(200, response.getStatus());
  }

  @Test
  public void deleteBook() {
    final String name = "STRAIGHT OUTTA COMPTON";

    when(manager.createQuery(any(String.class))).thenReturn(query);
    when(query.setParameter(any(String.class), any(String.class))).thenReturn(query);
    when(query.executeUpdate()).thenReturn(1);

    response = handler.deleteBook(name);
    verify(manager).createQuery(any(String.class));
    verify(query).setParameter(any(String.class), any(String.class));
    verify(query).executeUpdate();
    assertEquals(200, response.getStatus());
  }
}
