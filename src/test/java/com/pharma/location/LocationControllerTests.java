package com.pharma.location;

import com.google.gson.Gson;
import com.pharma.location.controllers.LocationController;
import com.pharma.location.models.Location;
import com.pharma.location.repositories.LocationRepository;
import com.pharma.location.services.LocationService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=LocationApplication.class)
@WebMvcTest(LocationController.class)
class LocationControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private LocationService locationService;
	@MockBean
	private LocationRepository locationRepository;

	private final Gson gson = new Gson();

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void contextLoads() {
		assertThat(locationService).isNotNull();
	}

	@Test
	public void getAllLocationsAPI()
			throws Exception {
		Location location1 = new Location((long)1, "Street", "HouseNumber", "Zipdcode", "City", "Country");
		Location location2 = new Location((long)2, "Street", "HouseNumber", "Zipdcode", "City", "Country");
		List<Location> allLocations = new ArrayList<>();
		allLocations.add(location1);
		allLocations.add(location2);

		given(locationService.getAll()).willReturn(allLocations);
		mvc.perform(MockMvcRequestBuilders
				.get("/location/all")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
	}

	@Test
	public void getLocationByIdAPI()
			throws Exception {
		Location location = new Location((long)1, "Street", "HouseNumber", "Zipdcode", "City", "Country");

		given(locationService.getById((long)1)).willReturn(java.util.Optional.of(location));
		mvc.perform(MockMvcRequestBuilders
				.get("/location/1")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
	}

/*
	@Test
	public void addLocationAPI()
			throws Exception {
		Location location = new Location((long)1, "Street", "HouseNumber", "Zipcode", "City", "Country");

		given(locationService.save(any(Location.class))).willReturn(location);
		mvc.perform(MockMvcRequestBuilders
				.get("/location/create", location)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
	}

	@Test
	public void addLocationNullAPI()
			throws Exception {
		Location location = new Location((long)1, "Street", "HouseNumber", "Zipcode", "City", "Country");

		given(locationService.save(any(Location.class))).willReturn(null);
		mvc.perform(MockMvcRequestBuilders
				.get("/location/create", location)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().string("Failed to create location"));
	}
	*/

	@Test
	public void updateLocationAPI()
			throws Exception {
		Location location = new Location((long)1, "Street", "HouseNumber", "Zipcode", "City", "Country");

		given(locationService.save(any(Location.class))).willReturn(location);
		mvc.perform(MockMvcRequestBuilders
				.put("/location/update", gson.toJson(location))
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
	}

	@Test
	public void deleteLocationAPI()
			throws Exception {
		mvc.perform(MockMvcRequestBuilders
				.delete("/location/delete/1")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Location deleted"));
	}
}
