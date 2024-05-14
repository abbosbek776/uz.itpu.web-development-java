package s19springmvcboot.webdev.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Product {
  @Id
  private Integer id;
  @Size(min = 10, message = "Name should be at least 10")
  private String name;

  private String description;

  @NotBlank
  @Max(1000000L)
  private Double price;

  private Boolean available;

  public Product(Integer id, String name, String description, Double price, Boolean available) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.available = available;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Boolean getAvailable() {
    return available;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
  }
}
