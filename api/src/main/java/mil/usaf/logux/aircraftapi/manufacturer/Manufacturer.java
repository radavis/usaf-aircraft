package mil.usaf.logux.aircraftapi.manufacturer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mil.usaf.logux.aircraftapi.aircraftmodel.AircraftModel;

import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import java.util.Set;

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

  @Setter
  private String location;

  @CreationTimestamp
  // @Column(name = "created_at")
  private Date createdAt;

  @OneToMany(mappedBy = "manufacturer")
  private Set<AircraftModel> aircraftModels;
}
