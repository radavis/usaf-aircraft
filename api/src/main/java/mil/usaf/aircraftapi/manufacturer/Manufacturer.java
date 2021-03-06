package mil.usaf.aircraftapi.manufacturer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mil.usaf.aircraftapi.aircraftmodel.AircraftModel;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor
@ToString
// @Table(name = "manufacturer")
public class Manufacturer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  @NotBlank
  @Setter
  private String name;

  @Setter private String location;

  @CreationTimestamp
  // @Column(name = "created_at")
  private Date createdAt;

  @JsonIgnore
  @OneToMany(mappedBy = "manufacturer")
  private Set<AircraftModel> aircraftModels;
}
