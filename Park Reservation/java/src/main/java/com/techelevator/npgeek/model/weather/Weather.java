package com.techelevator.npgeek.model.weather;

public class Weather {
	
	private String parkCode;
	private String forecast;
	private int dayOfForecast;
	private int low;
	private int lowCelsius;
	private int high;
	private int highCelsius;
	private String weatherImage;
	private String weatherMessage;

	
	
	//GETTERS AND SETTERS BELOW//
	
	
	
	/**
	 * @return the weatherImage
	 */
	public String getWeatherImage() {
		return weatherImage;
	}
	/**
	 * @return the weatherMessage
	 */
	public String getWeatherMessage() {
		return weatherMessage;
	}
	/**
	 * @param weatherMessage the weatherMessage to set
	 */
	public void setWeatherMessage(String weatherMessage) {
		this.weatherMessage = weatherMessage;
	}
	/**
	 * @param weatherImage the weatherImage to set
	 */
	public void setWeatherImage(String weatherImage) {
		this.weatherImage = weatherImage;
	}
	/**
	 * @return the lowCelsius
	 */
	public int getLowCelsius() {
		return lowCelsius = (int) ((low - 32) * 0.55);
	}
	/**
	 * @param lowCelsius the lowCelsius to set
	 */
	public void setLowCelsius(int lowCelsius) {
		this.lowCelsius = lowCelsius;
	}
	/**
	 * @return the highCelsius
	 */
	public int getHighCelsius() {
		return highCelsius = (int) ((high - 32) * 0.55);
	}
	/**
	 * @param highCelsius the highCelsius to set
	 */
	public void setHighCelsius(int highCelsius) {
		this.highCelsius = highCelsius;
	}
	
	/**
	 * @return the parkCode
	 */
	public String getParkCode() {
		return parkCode;
	}
	/**
	 * @param parkCode the parkCode to set
	 */
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	/**
	 * @return the dayOfForecast
	 */
	public int getDayOfForecast() {
		return dayOfForecast;
	}
	/**
	 * @param dayOfForecast the dayOfForecast to set
	 */
	public void setDayOfForecast(int dayOfForecast) {
		this.dayOfForecast = dayOfForecast;
	}
	/**
	 * @return the low
	 */
	public int getLow() {
		return low;
	}
	/**
	 * @param low the low to set
	 */
	public void setLow(int low) {
		this.low = low;
	}
	/**
	 * @return the high
	 */
	public int getHigh() {
		return high;
	}
	/**
	 * @param high the high to set
	 */
	public void setHigh(int high) {
		this.high = high;
	}
	/**
	 * @return the forecast
	 */
	public String getForecast() {
		return forecast;
	}
	/**
	 * @param forecast the forecast to set
	 */
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	
	

}
