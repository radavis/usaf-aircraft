package mil.usaf.logux.aircraftapi.aircraftmodel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
  "Aircraft",
  "Origin",
  "Type",
  "Variant",
  "In service",
  "Notes",
  "Category",
  "Wikipedia URL",
  "Title",
  "Manufacturer",
  "Image URL"
})
public class AircraftModelCsvRow {

  public String name;
  public String origin;
  public String type;
  public String variant;
  public Integer inService;
  public String notes;
  public String category;
  public String wikipediaUrl;
  public String title;
  public String manufacturer;
  public String imageUrl;
}
