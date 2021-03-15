package by.dma.explore.web;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import by.dma.explore.domain.Role;
import by.dma.explore.security.JwtProvider;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Helper class for creating HTTP Headers before invoking an API with TestRestClient.
 */
@Component
public class JwtRequestHelper {

  @Autowired
  private JwtProvider jwtProvider;

  /**
   * Generate the appropriate headers for JWT Authentication.
   *
   * @param roleName role identifier
   * @return Http Headers for Content-Type and Authorization
   */
  public HttpHeaders withRole(String roleName) {
    HttpHeaders headers = new HttpHeaders();
    Role role = new Role();
    role.setRoleName(roleName);
    String token = jwtProvider.createToken("anonymous",
                                           Collections.singletonList(role));
    headers.setContentType(APPLICATION_JSON);
    headers.add("Authorization", "Bearer " + token);
    return headers;
  }
}
