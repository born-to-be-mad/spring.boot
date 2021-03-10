package by.dma.securityjpa.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetails implements UserDetails {
  public static final String PASSWORD = "admin#123";

  private String userName;

  public AppUserDetails() {
  }

  public AppUserDetails(String userName) {
    this.userName = userName;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(
            new SimpleGrantedAuthority( "ROLE_USER" ) );
  }

  @Override
  public String getPassword() {
    return PASSWORD;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
