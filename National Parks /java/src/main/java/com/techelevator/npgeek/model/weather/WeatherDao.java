package com.techelevator.npgeek.model.weather;

import java.util.List;

public interface WeatherDao {
	
	public List<Weather> getWeatherForecastByParkCode (String parkCode); //should return a list of 5 Weather Objects


}
