package com.rentalservice.model;

import jdk.jfr.Name;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author olatunji
 */
@Entity
@Table(name = "book")
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT m FROM Book m"),
    @NamedQuery(name = "Book.findByName", query = "SELECT m FROM Book m WHERE m.name = :name")
})
@XmlRootElement
@Cacheable
public class Book implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = IDENTITY)
  private long id;


  @NotNull(message = "name cannot be null")
  @Column(name = "name", nullable = false)
  private String name;

  @NotNull(message = "isbn cannot be null")
  @Column(name = "isbn", nullable = false)
  private String isbn;

  @NotNull(message = "name cannot be null")
  @Column(name = "available_copies", nullable = false)
  private int availableCopies;

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public String getIsbn() { return isbn; }

  public void setIsbn(String isbn) { this.isbn = isbn; }

  public int getAvailableCopies() { return availableCopies; }

  public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }

  @Override
  public String toString() {
    return "Book[" +
        "id=" + id +
        ", name = '" + name + '\''+
        ", Isbn = '" + isbn + '\''+
        ", AvailableCopies = '" + availableCopies + '\''+
        ']';
  }
}
