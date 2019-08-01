package com.tsv.flightcheckin.integration;

import java.util.Map;

public interface TemperatureRestClient {

	public Map receiveForecastByLocation(String location);
	
}
