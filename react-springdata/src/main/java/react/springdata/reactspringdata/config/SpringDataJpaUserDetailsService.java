package react.springdata.reactspringdata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import react.springdata.reactspringdata.data.Manager;
import react.springdata.reactspringdata.data.ManagerRepository;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

  private final ManagerRepository repository;

  @Autowired
  public SpringDataJpaUserDetailsService(ManagerRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String name) {
    Manager manager = this.repository.findByName(name);
    return new User(manager.getName(), manager.getPassword(),
        AuthorityUtils.createAuthorityList(manager.getRoles()));
  }

}
