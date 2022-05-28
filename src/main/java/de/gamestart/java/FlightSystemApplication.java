package de.gamestart.java;

import de.gamestart.java.data.*;
import de.gamestart.java.models.AirportModel;
import de.gamestart.java.models.CityModel;
import de.gamestart.java.models.FlightsModel;
import de.gamestart.java.repository.AirportRepository;
import de.gamestart.java.repository.FarbeRepository;
import de.gamestart.java.repository.FlightRepository;
import de.gamestart.java.repository.HaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;


@SpringBootApplication
public class FlightSystemApplication implements CommandLineRunner {
	@Autowired
	public FlightRepository flightRepository;

	@Autowired
	public AirportRepository airportRepository;

	@Autowired
	public HaseRepository haseRepository;

	@Autowired
	public FarbeRepository farbeRepository;

	@Override
	public void run(String... args) {
		/*Airport t = new Airport(1, "felix sein zuhause", 34.2, 32.3, null);
		Airport t1 = new Airport(2, "felix sein zuhause", 34.2, 32.3, null);

		airportRepository.save(t);
		airportRepository.save(t1);
		flightRepository.save(new Flight(1, "ABC", t, new java.sql.Date(Calendar.getInstance().getTime().getTime()), t1, new java.sql.Date(Calendar.getInstance().getTime().getTime())));
		System.out.println(flightRepository.findAll().size());*/
		Hase h = new Hase();
		Farbe f = new Farbe();
		farbeRepository.save(f);
		h.setColor(f);
		haseRepository.save(h);
		System.out.println(haseRepository.findAll().size());
	}

	public static void main(String[] args) {
		Database.openConnect();
		Database.executeUpdate(CityModel.TABLE_CREATION);
		Database.executeUpdate(FlightsModel.TABLE_CREATION);
		Database.executeUpdate(AirportModel.TABLE_CREATION);
		/*
		CityModel.insert(1, "hase2", 0.3, 0.3);
		City model = CityModel.find("hase2");
		 */
		loadData();

		SpringApplication.run(FlightSystemApplication.class, args);
	}
	private static void loadData(){
		CityModel.deleteAll();
		CityModel.insert(2, "test2", 10, 20);
		CityModel.insert(3, "test3", 10, 20);
		CityModel.insert(4, "test4", 10, 20);
		CityModel.insert(5, "test5", 10, 20);
		CityModel.insert(6, "test6", 10, 20);
		CityModel.insert(7, "test7", 10, 20);
		CityModel.insert(8, "test8", 10, 20);
		CityModel.insert(9, "test9", 10, 20);

		boolean bool = AirportModel.insert(1, "München", 80.0, 70.1, 3);
		System.out.println(bool);
		/*
		FlightsModel.deleteAll();
		System.out.println(FlightsModel.insert(2, "F130", 2, 18, 7, 20));
		FlightsModel.insert(3, "F130", 3, 18, 6, 20);
		FlightsModel.insert(4, "F130", 4, 18,5, 20);
		FlightsModel.insert(5, "F136whw", 5, 18, 4, 20);
		FlightsModel.insert(6, "F140", 6, 18, 3, 20);
		FlightsModel.insert(7, "F150", 7, 18, 2, 20);

		 */
	}

}
