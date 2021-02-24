package by.dma.securityjwt.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Secured implementatipon of {@link UserDetailsService}.
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
@Service
public class SecuredUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return new User("dma", "dma", new ArrayList<>());
  }
}
