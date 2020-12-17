package mil.usaf.aircraftapi.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.Set;
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
// @Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank @Setter private String name;

  @CreationTimestamp
  // @Column(name = "created_at")
  private Date createdAt;

  @JsonIgnore
  @OneToMany(mappedBy = "category")
  private Set<AircraftModel> aircraftModels;
}
