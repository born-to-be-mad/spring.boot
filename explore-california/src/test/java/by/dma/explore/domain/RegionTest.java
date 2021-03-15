package by.dma.explore.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RegionTest {
  @Test
  public void findByLabel() throws Exception {
    assertThat(Region.CENTRAL_COAST, is(Region.findByLabel("Central Coast")));
    assertThat(Region.NORTHERN_CALIFORNIA,
               is(Region.findByLabel("Northern California")));
    assertThat(Region.SOUTHERN_CALIFORNIA,
               is(Region.findByLabel("Southern California")));
    assertThat(Region.VARIES, is(Region.findByLabel("Varies")));
  }

  @Test
  public void getLabel() throws Exception {
    assertThat(Region.CENTRAL_COAST.getLabel(), is("Central Coast"));
    assertThat(Region.NORTHERN_CALIFORNIA.getLabel(),
               is("Northern California"));
    assertThat(Region.SOUTHERN_CALIFORNIA.getLabel(),
               is("Southern California"));
    assertThat(Region.VARIES.getLabel(), is("Varies"));
  }

}
