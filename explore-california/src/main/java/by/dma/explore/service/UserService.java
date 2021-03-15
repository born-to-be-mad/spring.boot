package by.dma.explore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import by.dma.explore.domain.Role;
import by.dma.explore.domain.User;
import by.dma.explore.repo.RoleRepository;
import by.dma.explore.repo.UserRepository;
import by.dma.explore.security.JwtProvider;

@Service
public class UserService {

  private static final Logger LOGGER =
          LoggerFactory.getLogger(UserService.class);

  private UserRepository userRepository;

  private AuthenticationManager authenticationManager;

  private RoleRepository roleRepository;

  private PasswordEncoder passwordEncoder;

  private JwtProvider jwtProvider;

  @Autowired
  public UserService(UserRepository userRepository,
                     AuthenticationManager authenticationManager,
                     RoleRepository roleRepository,
                     PasswordEncoder passwordEncoder,
                     JwtProvider jwtProvider) {
    this.userRepository = userRepository;
    this.authenticationManager = authenticationManager;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtProvider = jwtProvider;
  }

  public Authentication signInWithoutToken(String username, String password) {
    return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
  }

  /**
   * Sign in a user into the application, with JWT-enabled authentication
   *
   * @param username username
   * @param password password
   * @return Optional of the Java Web Token, empty otherwise
   */
  public Optional<String> signIn(String username, String password) {
    LOGGER.info("New user attempting to sign in");
    Optional<String> token = Optional.empty();
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent()) {
      try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username,
                password));
        token = Optional.of(jwtProvider.createToken(username,
                                                    user.get().getRoles()));
      } catch (AuthenticationException e) {
        LOGGER.info("Log in failed for user {}", username);
      }
    }
    return token;
  }

  /**
   * Create a new user in the database.
   *
   * @param username  username
   * @param password  password
   * @param firstName first name
   * @param lastName  last name
   * @return Optional of user, empty if the user already exists.
   */
  public Optional<User> signUp(String username, String password,
                               String firstName, String lastName) {
    LOGGER.info("New user attempting to sign in");
    Optional<User> user = Optional.empty();
    if (userRepository.findByUsername(username).isEmpty()) {
      Optional<Role> role = roleRepository.findByRoleName("ROLE_CSR");
      user = Optional.of(userRepository.save(
              new User(username, passwordEncoder.encode(password),
                       role.get(), firstName, lastName)));
    }
    return user;
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }
}
