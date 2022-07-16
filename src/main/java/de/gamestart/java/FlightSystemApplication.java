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

    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;

    /**
     * main method to start server
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(FlightSystemApplication.class, args);
    }

    /**
     * Limits possible request origins to localhost 8080 and 4200
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080").allowedMethods("GET", "POST","PUT", "DELETE")
                        .allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST","PUT", "DELETE");
            }
        };
    }

    /**
     * starts Spring boot web server and loads data in database
     *
     * @param args incoming main method arguments - not used
     */
    @Override
    public void run(String... args) {
        initData();
    }

    /**
     * load database with demo data
     */
    private void initData() {
        //Cities
        City Munich = new City("Munich", 48.142265, 11.569977);
        City Hamburg = new City("Hamburg", 53.712586, 9.215304);
        City Berlin = new City("Berlin", 52.507208, 13.424755);
        City Paris = new City("Paris", 48.858884, 2.346941);
        City NewYork = new City("NewYork", 40.711874, -74.017639);
        City Seoul = new City("Seoul", 37.565092, 126.973851);

        //Airports
        Airport munich_airport = new Airport("Munich International Airport", "MUC", 48.348720, 11.819229, Munich);
        Airport hamburg_airport = new Airport("Hamburg Airport", "HAM", 53.6441255, 9.99542756, Hamburg);
        Airport berlin_airport = new Airport("Flughafen Berlin Brandenburg", "BER", 52.364717, 13.5110082, Berlin);
        Airport berlin_tegel_airport = new Airport("Berlin Tegel Airport", "TXL", 52.55896313, 13.28850177, Berlin);
        Airport paris_airport = new Airport("Paris Charles de Gaulle Airport", "CDG", 49.00826833, 2.5509443, Paris);
        Airport newYork_airport = new Airport("John F. Kennedy International Airport", "JFK", 40.642458, -73.779748, NewYork);
        Airport seoul_airport = new Airport("Incheon International Airport", "ICN", 37.558187, 126.834412, Seoul);

        //Flights
        Flight munich_to_hamburg = new Flight("6532", munich_airport, LocalDate.of(2022, 8, 4), LocalTime.of(13, 30), "C34", 1, hamburg_airport, LocalDate.of(2022, 8, 4), LocalTime.of(17, 30), "A32", 1, "Lufthansa");
        Flight munich_to_hamburg2 = new Flight("2376", munich_airport, LocalDate.of(2022, 8, 2), LocalTime.of(15, 0), "B21", 2, hamburg_airport, LocalDate.of(2022, 8, 2), LocalTime.of(19, 0), "B12", 2, "Lufthansa");
        Flight paris_to_berlinT = new Flight("136", paris_airport, LocalDate.of(2022, 8, 2), LocalTime.of(7, 45), "D32", 1, berlin_tegel_airport, LocalDate.of(2022, 8, 2), LocalTime.of(10, 30), "C56", 1, "Emirates");
        Flight berlinT_to_berlin = new Flight("1578", berlin_tegel_airport, LocalDate.of(2022, 8, 3), LocalTime.of(9, 30), "C21", 2, berlin_airport, LocalDate.of(2022, 8, 3), LocalTime.of(16, 30), "D3", 2, "Turkish Airline");
        //on day seoul -> munich
        Flight seoul_to_munich2 = new Flight("7632", seoul_airport, LocalDate.of(2022, 8, 21), LocalTime.of(4, 0), "A1", 1, munich_airport, LocalDate.of(2022, 8, 21), LocalTime.of(17, 10), "A94", 1, "Lufthansa");
        Flight seoul_to_munich3 = new Flight("6155", seoul_airport, LocalDate.of(2022, 8, 21), LocalTime.of(6, 25), "B2", 2, munich_airport, LocalDate.of(2022, 8, 21), LocalTime.of(19, 35), "B4", 2, "Emirates");
        Flight seoul_to_munich4 = new Flight("3652", seoul_airport, LocalDate.of(2022, 8, 21), LocalTime.of(11, 45), "A6", 1, munich_airport, LocalDate.of(2022, 8, 22), LocalTime.of(0, 10), "E9", 1, "Lufthansa");
        Flight seoul_to_munich5 = new Flight("1654", seoul_airport, LocalDate.of(2022, 8, 21), LocalTime.of(15, 10), "B11", 2, munich_airport, LocalDate.of(2022, 8, 22), LocalTime.of(3, 20), "A6", 1, "Turkish Airline");
        Flight seoul_to_munich6 = new Flight("4651", seoul_airport, LocalDate.of(2022, 8, 21), LocalTime.of(18, 0), "F17", 1, munich_airport, LocalDate.of(2022, 8, 22), LocalTime.of(6, 30), "A7", 2, "Eurowings");
        Flight seoul_to_munich7 = new Flight("4651", seoul_airport, LocalDate.of(2022, 8, 21), LocalTime.of(20, 30), "C21", 2, munich_airport, LocalDate.of(2022, 8, 22), LocalTime.of(9, 0), "B1", 1, "Lufthansa");
        Flight seoul_to_munich8 = new Flight("4651", seoul_airport, LocalDate.of(2022, 8, 21), LocalTime.of(23, 10), "D1", 1, munich_airport, LocalDate.of(2022, 8, 22), LocalTime.of(12, 20), "E2", 2, "Eurowings");


        //Trip
        Flight munich_to_paris = new Flight("2364", munich_airport, LocalDate.of(2022, 8, 10), LocalTime.of(19, 30), "A21", 1, paris_airport, LocalDate.of(2022, 8, 11), LocalTime.of(0, 30), "C54", 1, "Eurowings");
        Flight paris_to_newyork = new Flight("2534", paris_airport, LocalDate.of(2022, 8, 13), LocalTime.of(7, 15), "B24", 2, newYork_airport, LocalDate.of(2022, 8, 13), LocalTime.of(16, 30), "C24", 2, "Lufthansa");
        Flight newyork_to_seoul = new Flight("5343", newYork_airport, LocalDate.of(2022, 8, 17), LocalTime.of(8, 45), "D31", 2, seoul_airport, LocalDate.of(2022, 8, 17), LocalTime.of(17, 15), "B54", 1, "Eurowings");
        Flight seoul_to_munich = new Flight("7654", seoul_airport, LocalDate.of(2022, 8, 19), LocalTime.of(11, 0), "A1", 1, munich_airport, LocalDate.of(2022, 8, 19), LocalTime.of(15, 45), "A94", 2, "Lufthansa");

        //POIs Munich
        PointOfInterest TUM = new PointOfInterest("TUM Universität", "Europa", "Deutschland", Munich, 11.567917, 48.149435, "The Technical University of Munich is a public research university in Munich, with additional campuses in Garching, Freising, Heilbronn, Straubing, and Singapore.", "https://oia.ugm.ac.id/wp-content/uploads/sites/326/2021/04/TUMASIA-825x455.jpg",
                PointOfInterest.PoiType.PUBLIC_BUILDING);
        PointOfInterest frauenkirche = new PointOfInterest("Frauenkirche", "Europa", "Deutschland", Munich, 11.572799, 48.138569, "2 towers top this restored Gothic church, bombed in 1945, known for its legendary Devil's footprint.", "https://upload.wikimedia.org/wikipedia/commons/2/26/Frauenkirche_Munich_-_View_from_Peterskirche_Tower.jpg",
                PointOfInterest.PoiType.PUBLIC_BUILDING);
        PointOfInterest munich_hbh = new PointOfInterest("München Hauptbahnhof", "Europa", "Deutschland", Munich, 11.555363, 48.140654, "Airy mainline railway station with underground S- & U-Bahn platforms plus tram & bus connections.", "https://upload.wikimedia.org/wikipedia/commons/4/47/Hauptbahnhof_2014-08-02.JPG",
                PointOfInterest.PoiType.TRANSPORTATION);
        PointOfInterest englischer_garten = new PointOfInterest("Englischer Garten", "Europa", "Deutschland", Munich, 11.607589, 48.163827, "Expansive, 18th-century, urban park with 78km of cycling/jogging trails & a lakeside beer garden.", "https://cdn.muenchen-p.de/.imaging/stk/responsive/galleryLarge/dms/sw/c/lhm/lhm-e-garten-panorama/document/lhm-e-garten-panorama.jpg",
                PointOfInterest.PoiType.OTHER);

        //POIs Berlin
        PointOfInterest Bundestag = new PointOfInterest("Bundestag", "Europa", "Deutschland", Berlin, 13.375040, 52.518752, "Neo-Renaissance parliament building topped by a Norman Foster glass dome with 360-degree city views.", "https://www.bundestag.de/resource/image/462008/16x9/750/422/d89f0f8b9392effdeb7d18d27754a859/55F9E6C4EA0E758275AA3DB2A11B5D98/plenum_teaser_sitzungsverlauf_bild.jpg",
                PointOfInterest.PoiType.PUBLIC_BUILDING);
        PointOfInterest BerlinerMauer = new PointOfInterest("Berliner Mauer", "Europa", "Deutschland", Berlin, 13.377745, 52.515549, "Graffiti-covered remains of a Cold War barrier that divided the city, now a historical monument.", "https://images.reisereporter.de/YQgEixCsxcDJI97tFO1-D8XDMUp4odr8UI1Ji9VaVME/g:sm/rs:fill:1920:1080/ZWNkNGMxNDMtZGE/1OS00MGRlLWEzYW/ItMDBkOWM5OTRiM/GU5LmpwZw", PointOfInterest.PoiType.LANDMARK);

        //POIs Paris
        PointOfInterest Eifelturm = new PointOfInterest("Eifelturm", "Europa", "Frankreich", Paris, 2.295028, 48.857904, "Gustave Eiffel's iconic, wrought-iron 1889 tower, with steps and elevators to observation decks.", "https://i.pinimg.com/736x/ce/4b/c3/ce4bc376016d8202176bdb9a479f46aa.jpg",
                PointOfInterest.PoiType.LANDMARK);
        PointOfInterest NightClub = new PointOfInterest("Trivia Nightclub", "Europa", "Frankreich", Paris, 2.298498, 48.849107, "Vast, bilevel space for rollicking DJ parties, with a cocktail menu & go-go dancers.", "https://upload.wikimedia.org/wikipedia/commons/3/32/Wikipedia_space_ibiza%2803%29.jpg",
                PointOfInterest.PoiType.NIGHTLIFE);

        //POIs New York
        PointOfInterest StatueOfLiberty = new PointOfInterest("Statue of Liberty",
                "Nordamerica", "USA", NewYork, 40.6893958, -74.04451117,
                "Statue located on Liberty Island in New York Harbor, presented to the United States on the centennial of the signing of the American Declaration of Independence as a gift from France.",
                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Statue_of_Liberty%2C_NY.jpg",
                PointOfInterest.PoiType.LANDMARK);
        PointOfInterest EmpireStateBuilding = new PointOfInterest("Empire State Building",
                "Nordamerica", "USA", NewYork, 40.7486274, -73.9856536,
                "A 102-story skyscraper in Manhattan, New York City. It was designed by Shreve, Lamb & Harmon and built from 1930 to 1931",
                "https://upload.wikimedia.org/wikipedia/commons/1/10/Empire_State_Building_%28aerial_view%29.jpg",
                PointOfInterest.PoiType.NIGHTLIFE);

        List<City> cities = new ArrayList<>();
        cities.add(Munich);
        cities.add(Hamburg);
        cities.add(Berlin);
        cities.add(Paris);
        cities.add(NewYork);
        cities.add(Seoul);
        cityRepository.saveAllAndFlush(cities);

        List<Airport> airports = new ArrayList<>();
        airports.add(munich_airport);
        airports.add(hamburg_airport);
        airports.add(berlin_airport);
        airports.add(berlin_tegel_airport);
        airports.add(paris_airport);
        airports.add(newYork_airport);
        airports.add(seoul_airport);
        airportRepository.saveAllAndFlush(airports);

        List<Flight> flights = new ArrayList<>();
        flights.add(munich_to_hamburg);
        flights.add(munich_to_hamburg2);
        flights.add(paris_to_berlinT);
        flights.add(berlinT_to_berlin);
        flights.add(munich_to_paris);
        flights.add(paris_to_newyork);
        flights.add(newyork_to_seoul);
        flights.add(seoul_to_munich);
        flights.add(seoul_to_munich2);
        flights.add(seoul_to_munich3);
        flights.add(seoul_to_munich4);
        flights.add(seoul_to_munich5);
        flights.add(seoul_to_munich6);
        flights.add(seoul_to_munich7);
        flights.add(seoul_to_munich8);
        flightRepository.saveAllAndFlush(flights);


        List<PointOfInterest> pois = new ArrayList<>();
        pois.add(Bundestag);
        pois.add(BerlinerMauer);
        pois.add(TUM);
        pois.add(Eifelturm);
        pois.add(NightClub);
        pois.add(frauenkirche);
        pois.add(munich_hbh);
        pois.add(englischer_garten);
        pois.add(StatueOfLiberty);
        pois.add(EmpireStateBuilding);
        pointOfInterestRepository.saveAllAndFlush(pois);
    }
}
