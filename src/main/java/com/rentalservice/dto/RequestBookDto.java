package com.rentalservice.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author olatunji
 */
@XmlRootElement
public class RequestBookDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
  private int amount;

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public int getAmount() { return amount; }

  public void setAmount(int amount) { this.amount = amount; }

  @Override
  public String toString() {
    return "RequestedMovie[" +
        "name ='" + name + '\''+
        ", amount = '" + amount + '\''+
        ']';
  }
}
