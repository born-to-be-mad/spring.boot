package by.dma.explore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.List;

import by.dma.explore.domain.User;
import by.dma.explore.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/signin-no-token")
    public Authentication loginWithoutToken(@RequestBody @Valid LoginDto loginDto) {
      return userService.signInWithoutToken(loginDto.getUsername(), loginDto.getPassword());
    }

  @PostMapping("/signin")
  public String login(@RequestBody @Valid LoginDto loginDto) {
    return userService.signIn(loginDto.getUsername(), loginDto.getPassword())
                      .orElseThrow(()-> new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed"));
  }

  @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public User signup(@RequestBody @Valid LoginDto loginDto){
        return userService.signUp(loginDto.getUsername(), loginDto.getPassword(), loginDto.getFirstName(),
                                  loginDto.getLastName()).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST,"User already exists"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

  /**
   * Exception handler if NoSuchElementException is thrown in this Controller
   *
   * @param ex exception
   * @return Error message String.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(RuntimeException.class)
  public String return400(RuntimeException ex) {
    LOGGER.error("Unable to complete transaction", ex);
    return ex.getMessage();
  }

}
