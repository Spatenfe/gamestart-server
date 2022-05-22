package de.gamestart.java;

import de.gamestart.java.data.City;
import de.gamestart.java.data.Flight;
import de.gamestart.java.models.AirportModel;
import de.gamestart.java.models.CityModel;
import de.gamestart.java.models.FlightsModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class FlightSystemApplication {
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
