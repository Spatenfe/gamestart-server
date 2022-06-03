package de.gamestart.java;

import de.gamestart.java.data.Airport;
import de.gamestart.java.data.City;
import de.gamestart.java.data.Flight;
import de.gamestart.java.repository.AirportRepository;
import de.gamestart.java.repository.CityRepository;
import de.gamestart.java.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class FlightSystemApplication implements CommandLineRunner {

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

	private void initData() {
		City Munich = new City("Munich", 34.4, 78);
		City Hamburg = new City("Hamburg", 24.4, 44);
		City Dresden = new City("Dresden", 36.4, 23);
		City Berlin = new City("Berlin", 24.3, 25);
		City Paris = new City("Paris", 14.4, 3);

		Airport munich_airport = new Airport("Munich Airport", 23.3, 23.2, Munich);
		Airport hamburg_airport = new Airport("Hamburg Airport", 23.3, 23.2, Hamburg);
		Airport dresden_airport = new Airport("Dresden Airport", 23.3, 23.2, Dresden);
		Airport berlin_airport = new Airport("Berlin Airport", 23.3, 23.2, Berlin);
		Airport berlin_tegel_airport = new Airport("Berlin Tegel Airport", 23.3, 23.2, Berlin);
		Airport paris_airport = new Airport("Paris Airport", 23.3, 23.2, Paris);

		Flight munich_to_hamburg = new Flight("ABC", munich_airport, LocalDate.of(2022, 6, 4), LocalTime.now(), hamburg_airport, LocalDate.of(2022, 6, 4), LocalTime.now(), "Lufthansa");
		Flight munich_to_hamburg2 = new Flight("ABCD", munich_airport, LocalDate.of(2022, 6, 2), LocalTime.now(), hamburg_airport, LocalDate.of(2022, 6, 4), LocalTime.now(), "Lufthansa");
		Flight paris_to_berlinT = new Flight("ABCII", paris_airport, LocalDate.of(2022, 6, 2), LocalTime.now(), berlin_tegel_airport, LocalDate.of(2022, 6, 4), LocalTime.now(), "Emirates");
		Flight berlinT_to_berlin = new Flight("ABC", berlin_tegel_airport, LocalDate.of(2022, 6, 3), LocalTime.now(), berlin_airport, LocalDate.of(2022, 6, 4), LocalTime.now(), "Turkish Airline");
		Flight munich_to_dresden = new Flight("ABC", munich_airport, LocalDate.of(2022, 6, 3), LocalTime.now(), dresden_airport, LocalDate.of(2022, 6, 4), LocalTime.now(), "Eurowings");

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
	}
}
