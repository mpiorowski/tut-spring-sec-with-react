package react.springdata.reactspringdata.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Employee {

  private @Id
  @GeneratedValue
  Long id;

  private String firstName;
  private String lastName;
  private String description;

  @Version
  @JsonIgnore
  private Long version;

  @ManyToOne
  private Manager manager;

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", description='" + description + '\'' +
        ", manager=" + manager +
        '}';
  }


  public Employee(String firstName, String lastName, String description, Manager manager) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.description = description;
    this.manager = manager;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Manager getManager() {
    return manager;
  }

  public void setManager(Manager manager) {
    this.manager = manager;
  }

  private Employee() {
  }

}
