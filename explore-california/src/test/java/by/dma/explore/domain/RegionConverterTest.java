package by.dma.explore.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RegionConverterTest {
  private RegionConverter converter = new RegionConverter();

  @Test
  public void convertToDatabaseColumn() throws Exception {
    assertThat(converter.convertToDatabaseColumn(Region.CENTRAL_COAST),
               is(Region.CENTRAL_COAST.getLabel()));
  }

  @Test
  public void convertToEntityAttribute() throws Exception {
    assertThat(converter.convertToEntityAttribute(Region.CENTRAL_COAST.getLabel()),
               is(Region.CENTRAL_COAST));
  }

}
