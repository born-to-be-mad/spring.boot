package by.dma.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link YamlProperties}.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class YamlPropertiesTest {

  @Autowired
  private YamlProperties yamlProperties;

  @Test
  public void whenFactoryProvidedThenYamlPropertiesInjected() {
    assertThat(yamlProperties.getName()).isEqualTo("james");
    assertThat(yamlProperties.getAliases()).containsExactly("agent007", "bond");
  }
}
