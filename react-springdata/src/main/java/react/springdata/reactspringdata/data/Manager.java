package react.springdata.reactspringdata.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Data
@ToString(exclude = "password")
@Entity
public class Manager {

  public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @JsonIgnore
  private String password;

  @Override
  public String toString() {
    return "Manager{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", roles=" + Arrays.toString(roles) +
        '}';
  }

  public String getPassword() {
    return password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String[] getRoles() {
    return roles;
  }

  public void setRoles(String[] roles) {
    this.roles = roles;
  }

  private String[] roles;

  public void setPassword(String password) {
    this.password = PASSWORD_ENCODER.encode(password);
  }

  public Manager() {
  }

  public Manager(String name, String password, String... roles) {
    this.name = name;
    this.password = PASSWORD_ENCODER.encode(password);
    this.roles = roles;
  }
}
