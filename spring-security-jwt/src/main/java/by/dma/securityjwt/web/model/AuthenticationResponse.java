package by.dma.securityjwt.web.model;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
public class AuthenticationResponse {
  private String token;

  public AuthenticationResponse() {
  }

  public AuthenticationResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
