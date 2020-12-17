package mil.usaf.aircraftapi.aircraftmodel;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mil.usaf.aircraftapi.category.Category;
import mil.usaf.aircraftapi.manufacturer.Manufacturer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class AircraftModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank @Setter private String name;

  @ManyToOne
  @JoinColumn(nullable = false)
  @Setter
  @ToString.Exclude
  private Category category;

  @ManyToOne
  @JoinColumn(nullable = false)
  @Setter
  @ToString.Exclude
  private Manufacturer manufacturer;

  @Setter @ToString.Exclude private String wikipediaUrl;

  @Setter @ToString.Exclude private String imageUrl;

  @CreationTimestamp private Date createdAt;

  @UpdateTimestamp private Date updatedAt;
}
