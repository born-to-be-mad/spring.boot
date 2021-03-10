package by.dma.securityjpa.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return new by.dma.securityjpa.config.AppUserDetails( userName);
  }
}
