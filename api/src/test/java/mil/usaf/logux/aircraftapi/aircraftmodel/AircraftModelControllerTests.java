package mil.usaf.logux.aircraftapi.aircraftmodel;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import mil.usaf.logux.aircraftapi.category.Category;
import mil.usaf.logux.aircraftapi.manufacturer.Manufacturer;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// https://mkyong.com/spring-boot/spring-rest-integration-test-example/

@WebMvcTest(AircraftModelController.class)
class AircraftModelControllerTests {

  @Autowired private MockMvc mockMvc;

  @MockBean private AircraftModelRepository repo;

  @BeforeEach
  void setup() {
    Manufacturer manufacturer = new Manufacturer();
    manufacturer.setName("ACME");

    Category category = new Category();
    category.setName("Catalog Item");

    AircraftModel rocket = new AircraftModel();
    rocket.setName("Rocket Sled");
    rocket.setManufacturer(manufacturer);
    rocket.setCategory(category);

    AircraftModel jet = new AircraftModel();
    jet.setName("Jet");
    jet.setManufacturer(manufacturer);
    jet.setCategory(category);

    given(repo.findAll()).willReturn(Lists.newArrayList(rocket, jet));
    given(repo.findById(1L)).willReturn(Optional.of(rocket));
  }

  @Test
  void test_index() throws Exception {
    mockMvc
        .perform(get("/aircraftModel"))
        .andDo(print())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].name", is("Rocket Sled")))
        .andExpect(jsonPath("$[0].manufacturer.name", is("ACME")))
        .andExpect(jsonPath("$[0].category.name", is("Catalog Item")))
        .andExpect(jsonPath("$[1].name", is("Jet")))
        .andExpect(jsonPath("$[1].manufacturer.name", is("ACME")))
        .andExpect(jsonPath("$[1].category.name", is("Catalog Item")));
  }

  @Test
  void test_show_ok() throws Exception {
    mockMvc
        .perform(get("/aircraftModel/1"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Rocket Sled")));
  }

  @Test
  void test_show_notFound() throws Exception {
    mockMvc.perform(get("/aircraftModel/404")).andExpect(status().isNotFound());
  }
}
