package by.dma.securityjwt.web;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.dma.securityjwt.services.JwtService;
import by.dma.securityjwt.services.SecuredUserDetailsService;
import by.dma.securityjwt.web.model.AuthenticationResponse;
import by.dma.securityjwt.web.model.AuthenticationRequest;

/**
 * Simple User Resource.
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
@RestController
public class UserResource {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private SecuredUserDetailsService userDetailsService;

  @Autowired
  private JwtService jwtService;

  @GetMapping("/hello")
  public String hello() {
    return ThreadLocalRandom.current().nextBoolean() ? "hello" : "hi";
  }

  @PostMapping("/authenticate")
  public ResponseEntity<?> authenticate(
          @RequestBody AuthenticationRequest authenticationRequest)
          throws Exception {
    String userName = authenticationRequest.getUserName();
    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(userName, authenticationRequest.getPassword())
      );
    } catch (AuthenticationException e) {
      throw new Exception("Incorrect user name or password", e);
    }

    final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
    String token = jwtService.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(token));
  }
}
