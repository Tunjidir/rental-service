package com.rentalservice.business;

import com.rentalservice.dto.RequestBookDto;
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

import java.util.Arrays;

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
public class CustomerHandlerTest {

  @Mock
  EntityManager manager;

  @InjectMocks
  CustomerHandler handler;

  @Mock
  TypedQuery query;

  @Mock
  Response response;

  @Before
  public void setUp() {
    final Book book = new Book();
    book.setName("MISSION IMPOSSIBLE");
    book.setIsbn("89900AB89");
    book.setAvailableCopies(10);

    final Book second = new Book();
    second.setName("LONG WALK TO FREEDOM");
    second.setIsbn("789007646AB");
    second.setAvailableCopies(10);

    final Book third = new Book();
    third.setName("THE GODFATHER");
    third.setIsbn("45908373CC");
    third.setAvailableCopies(10);

    when(manager.createQuery(any(String.class), eq(Book.class))).thenReturn(query);
    when(query.setParameter(any(String.class), any(String.class))).thenReturn(query);
    when(query.getSingleResult()).thenReturn(book, second, third);
  }

  @Test
  public void borrowMovie() {

    final RequestBookDto first = new RequestBookDto();
    first.setName("MISSION IMPOSSIBLE");
    first.setAmount(2);

    final RequestBookDto another = new RequestBookDto();
    another.setName("LONG WALK TO FREEDOM");
    another.setAmount(3);

    final RequestBookDto dto = new RequestBookDto();
    dto.setName("THE GODFATHER");
    dto.setAmount(4);

    response = handler.borrowBook(Arrays.asList(first, another, dto));
    verify(manager, times(3)).merge(any());
    assertEquals(200, response.getStatus());
  }

  @Test
  public void returnMovie() {
    final var first = new RequestBookDto();
    first.setName("MISSION IMPOSSIBLE");
    first.setAmount(2);

    final var another = new RequestBookDto();
    another.setName("LONG WALK TO FREEDOM");
    another.setAmount(3);

    final var dto = new RequestBookDto();
    dto.setName("THE GODFATHER");
    dto.setAmount(4);

    response = handler.returnBook(Arrays.asList(first, another, dto));
    assertEquals(200, response.getStatus());
  }
}
