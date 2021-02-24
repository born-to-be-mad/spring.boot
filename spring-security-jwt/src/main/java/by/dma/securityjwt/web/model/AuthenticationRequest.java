package by.dma.securityjwt.web.model;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
public class AuthenticationRequest {
  private String userName;
  private String password;

  public AuthenticationRequest() {
  }

  public AuthenticationRequest(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
