package by.dma.explore.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import by.dma.explore.domain.User;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceIT {
  @Autowired
  private UserService service;

  @Test
  public void signUp() {
    Optional<User> user =
            service.signUp("dummyUsername", "dummypassword", "james", "bond");
    assertThat(user.get().getPassword(), not("dummypassword"));
    System.out.println("Encoded Password = " + user.get().getPassword());
  }
}
