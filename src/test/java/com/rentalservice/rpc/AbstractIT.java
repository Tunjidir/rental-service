package com.rentalservice.rpc;

import com.rentalservice.business.BookHandler;
import com.rentalservice.dto.BookDto;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * @author olatunji
 */
public class AbstractIT {

  protected final Client client = ClientBuilder.newClient();

  @Deployment
  public static WebArchive createDeployment() {
    return ShrinkWrap.create(WebArchive.class, "rent.war")
        .addPackages(true, "com.rentalservice")
        .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @PersistenceContext
  EntityManager em;

  @Inject
  UserTransaction utx;

  @Inject
  BookHandler handler;

  @Before
  public void init() {
    final var book = new BookDto();
    book.setName("FIFTY SHADES OF GREY");
    book.setIsbn("CC7890BAD");
    book.setAvailableCopies(10);

    handler.addBook(book);
  }

  @After
  public void clearDatabase() throws Exception {
    utx.begin();
    em.createQuery("DELETE FROM Book b").executeUpdate();
    utx.commit();
  }
}
