package com.rentalservice.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author olatunji
 */
@XmlRootElement
public class BookDto implements Serializable {

  private static final long serialVersionUID = 1L;

  public BookDto() {
  }

  private String name;
  private String isbn;
  private int availableCopies;

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public String getIsbn() { return isbn; }

  public void setIsbn(String isbn) { this.isbn = isbn; }

  public int getAvailableCopies() { return availableCopies; }

  public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }

  @Override
  public String toString() {
    return "BookDto[" +
        ", name = '" + name + '\''+
        ", Isbn = '" + isbn + '\''+
        ", AvailableCopies = '" + availableCopies + '\''+
        ']';
  }
}
