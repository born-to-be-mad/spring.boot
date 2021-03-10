package by.dma.securityjpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.dma.securityjpa.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
  final UserRepository userRepository;

  public AppUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

    return userRepository.findByUserName(userName)
                         .map(AppUserDetails::new)
                         .orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
  }
}
