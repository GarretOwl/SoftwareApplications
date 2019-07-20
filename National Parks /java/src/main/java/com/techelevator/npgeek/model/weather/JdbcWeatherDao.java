package com.techelevator.npgeek.model.weather;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWeatherDao implements WeatherDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getWeatherForecastByParkCode(String parkCode) {

		List<Weather> weatherListByPark = new ArrayList<>();
		String SqlQuery = "SELECT * FROM weather WHERE parkcode=?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(SqlQuery, parkCode);

		while (result.next()) {

			weatherListByPark.add(mapRowSetToWeather(result));
		}

		return weatherListByPark;
	}

	private Weather mapRowSetToWeather(SqlRowSet result) {

		Weather weather = new Weather();
		weather.setParkCode(result.getString("parkcode"));
		weather.setDayOfForecast(result.getInt("fivedayforecastvalue"));
		weather.setLow(result.getInt("low"));
		weather.setHigh(result.getInt("high"));
		weather.setForecast(result.getString("forecast"));

		if (!(weather.getForecast().equals("partly cloudy"))) {
			weather.setWeatherImage(result.getString("forecast"));
		} else {
			weather.setWeatherImage("partlyCloudy");
		}

		if (weather.getForecast().equals("snow")) {
			weather.setWeatherMessage("Pack snowshoes! \n");
		}
		
		if (weather.getForecast().equals("rain")) {

			weather.setWeatherMessage("Pack rain gear and wear waterproof shoes! \n");
		}
		
		if (weather.getForecast().equals("thunderstorms")) {
			weather.setWeatherMessage("Seek shelter and avoid hiking on exposed ridges! \n");
		}
		
		if (weather.getForecast().equals("sunny")) {
			weather.setWeatherMessage("Pack sunblock! \n");
		}

		if (weather.getHigh() > 75) {
			weather.setWeatherMessage(weather.getWeatherMessage() + "Bring an extra gallon of water!");

		}

		if (weather.getLow() < 20) {
			weather.setWeatherMessage(weather.getWeatherMessage() + "Be wary of exposure to the cold!");

		}

		if ((weather.getHigh() - weather.getLow()) > 20) {
			weather.setWeatherMessage(weather.getWeatherMessage() + "Wear breathable layers!");

		}
		if (weather.getForecast().equals("cloudy") || weather.getForecast().equals("partly cloudy")) {

			weather.setWeatherMessage("Looks like a beautiful day!");

		}
		return weather;
	}
}
