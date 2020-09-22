package by.dma.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

/**
 * Yaml properties configuration.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
@Configuration
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:yaml-properties.yml", factory = YamlPropertySourceFactory.class)
@Getter
@Setter
public class YamlProperties {

  private String name;

  private List<String> aliases;
}
