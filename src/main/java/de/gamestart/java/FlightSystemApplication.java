package de.gamestart.java;

import de.gamestart.java.data.Airport;
import de.gamestart.java.data.City;
import de.gamestart.java.data.Flight;
import de.gamestart.java.data.PointOfInterest;
import de.gamestart.java.repository.AirportRepository;
import de.gamestart.java.repository.CityRepository;
import de.gamestart.java.repository.FlightRepository;
import de.gamestart.java.repository.PointOfInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class FlightSystemApplication implements CommandLineRunner {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:8080")
						.allowedOrigins("http://localhost:4200");
			}
		};
	}
  
	@Override
	public void run(String... args) {
		initData();
	}

	public static void main(String[] args) {
		SpringApplication.run(FlightSystemApplication.class, args);
	}

	@Autowired
	private AirportRepository airportRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private PointOfInterestRepository pointOfInterestRepository;

	private void initData() {
		City Munich = new City("Munich", 34.4, 78);
		City Hamburg = new City("Hamburg", 24.4, 44);
		City Dresden = new City("Dresden", 36.4, 23);
		City Berlin = new City("Berlin", 24.3, 25);
		City Paris = new City("Paris", 14.4, 3);

		Airport munich_airport = new Airport("Munich Airport", "MNX", 23.3, 23.2, Munich);
		Airport hamburg_airport = new Airport("Hamburg Airport", "HA", 23.3, 23.2, Hamburg);
		Airport dresden_airport = new Airport("Dresden Airport", "DA", 23.3, 23.2, Dresden);
		Airport berlin_airport = new Airport("Berlin Airport", "BTX", 23.3, 23.2, Berlin);
		Airport berlin_tegel_airport = new Airport("Berlin Tegel Airport", "BTA", 23.3, 23.2, Berlin);
		Airport paris_airport = new Airport("Paris Airport", "PA", 23.3, 23.2, Paris);

		Flight munich_to_hamburg = new Flight("ABC", munich_airport, LocalDate.of(2022, 6, 4), LocalTime.now(), 'C', 1, hamburg_airport, LocalDate.of(2022, 6, 4), LocalTime.now(),'C', 1,  "Lufthansa");
		Flight munich_to_hamburg2 = new Flight("ABCD", munich_airport, LocalDate.of(2022, 6, 2), LocalTime.now(), 'C', 1, hamburg_airport, LocalDate.of(2022, 6, 4), LocalTime.now(), 'C', 1, "Lufthansa");
		Flight paris_to_berlinT = new Flight("ABCII", paris_airport, LocalDate.of(2022, 6, 2), LocalTime.now(), 'C', 1, berlin_tegel_airport, LocalDate.of(2022, 6, 4), LocalTime.now(), 'C', 1, "Emirates");
		Flight berlinT_to_berlin = new Flight("ABC", berlin_tegel_airport, LocalDate.of(2022, 6, 3), LocalTime.now(), 'C', 1, berlin_airport, LocalDate.of(2022, 6, 4), LocalTime.now(), 'C', 1, "Turkish Airline");
		Flight munich_to_dresden = new Flight("ABC", munich_airport, LocalDate.of(2022, 6, 3), LocalTime.now(), 'C', 1, dresden_airport, LocalDate.of(2022, 6, 4), LocalTime.now(), 'C', 1, "Eurowings");

		PointOfInterest TestPoi = new PointOfInterest("poiName", "poiContinent", "poiCountry", Munich, 10.4, 11.4, "poiDescription", "shorturl.at/tzO48", PointOfInterest.PoiType.OTHER);
		PointOfInterest Bundestag = new PointOfInterest("Bundestag", "Europa", "Deutschland", Berlin, 1.4, 18.4, "Ist ziehmlich langweilig", "https://www.bundestag.de/resource/image/462008/16x9/750/422/d89f0f8b9392effdeb7d18d27754a859/55F9E6C4EA0E758275AA3DB2A11B5D98/plenum_teaser_sitzungsverlauf_bild.jpg",
				PointOfInterest.PoiType.LANDMARK);
		PointOfInterest BerlinerMauer = new PointOfInterest("Berliner Mauer", "Europa", "Deutschland", Berlin, 10.4, 11.4, "Ist ziehmlich lang", "https://images.reisereporter.de/YQgEixCsxcDJI97tFO1-D8XDMUp4odr8UI1Ji9VaVME/g:sm/rs:fill:1920:1080/ZWNkNGMxNDMtZGE/1OS00MGRlLWEzYW/ItMDBkOWM5OTRiM/GU5LmpwZw", PointOfInterest.PoiType.LANDMARK);
		PointOfInterest TUM = new PointOfInterest("TUM Universität", "Europa", "Deutschland", Munich, 13.4, 8.4, "Ist ziehmlich cool", "https://www.tum.de/fileadmin/_processed_/3/2/csm_logo-tum_35fd07f043.png",
				PointOfInterest.PoiType.PUBLIC_BUILDING);
		PointOfInterest Eifelturm = new PointOfInterest("Eifelturm", "Europa", "Frankreich", Paris, 7.2, 5.6, "Ist ziehmlich hoch - La baguette!!!", "https://i.pinimg.com/736x/ce/4b/c3/ce4bc376016d8202176bdb9a479f46aa.jpg",
				PointOfInterest.PoiType.LANDMARK);
		PointOfInterest NightClub = new PointOfInterest("Trivia", "Europa", "Frankreich", Dresden, 7.2, 5.6, "Ist ziehmlich laut", "https://upload.wikimedia.org/wikipedia/commons/3/32/Wikipedia_space_ibiza%2803%29.jpg",
				PointOfInterest.PoiType.NIGHTLIFE);

		List<City> cities = new ArrayList<>();
		cities.add(Munich);
		cities.add(Hamburg);
		cities.add(Dresden);
		cities.add(Berlin);
		cities.add(Paris);
		cityRepository.saveAllAndFlush(cities);

		List<Airport> airports = new ArrayList<>();
		airports.add(munich_airport);
		airports.add(hamburg_airport);
		airports.add(dresden_airport);
		airports.add(berlin_airport);
		airports.add(berlin_tegel_airport);
		airports.add(paris_airport);
		airportRepository.saveAllAndFlush(airports);

		List<Flight> flights = new ArrayList<>();
		flights.add(munich_to_hamburg);
		flights.add(munich_to_hamburg2);
		flights.add(paris_to_berlinT);
		flights.add(berlinT_to_berlin);
		flights.add(munich_to_dresden);
		flightRepository.saveAllAndFlush(flights);


        List<PointOfInterest> pois = new ArrayList<>();
        pois.add(TestPoi);
        pois.add(Bundestag);
        pois.add(BerlinerMauer);
        pois.add(TUM);
        pois.add(Eifelturm);
        pois.add(NightClub);
        pointOfInterestRepository.saveAllAndFlush(pois);


	}
}
