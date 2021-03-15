package by.dma.explore.domain;

/**
 * Enumeration of the region of California.
 */
public enum Region {
  CENTRAL_COAST("Central Coast"),
  SOUTHERN_CALIFORNIA("Southern California"),
  NORTHERN_CALIFORNIA("Northern California"),
  VARIES("Varies");

  private String label;

  Region(String label) {
    this.label = label;
  }

  public static Region findByLabel(String byLabel) {
    for (Region value : Region.values()) {
      if (value.label.equalsIgnoreCase(byLabel)) {
        return value;
      }
    }
    return null;
  }

  public String getLabel() {
    return label;
  }
}
