package by.dma.securityjpa.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import by.dma.securityjpa.entity.User;

import static java.util.stream.Collectors.*;

@Service
public class AppUserDetails implements UserDetails {
//  public static final String PASSWORD = "admin#123";

  private String userName;
  private String password;
  private boolean active;
  private List<GrantedAuthority> authorities;

  public AppUserDetails(User user) {
    this.userName = user.getUserName();
    this.password = user.getPassword();
    this.active = user.isActive();
    this.authorities = Arrays.stream(user.getRoles().split(","))
                             .map(SimpleGrantedAuthority::new)
                             .collect(toList());
  }

  public AppUserDetails() {
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return active;
  }

  @Override
  public boolean isAccountNonLocked() {
    return active;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return active;
  }

  @Override
  public boolean isEnabled() {
    return active;
  }
}
