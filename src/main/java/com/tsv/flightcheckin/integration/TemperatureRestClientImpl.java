package com.tsv.flightcheckin.integration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tsv.flightcheckin.integration.dto.MainTemp;
@Component
public class TemperatureRestClientImpl implements TemperatureRestClient{

	@Override
	public Map receiveForecastByLocation(String location) {
		return requestForecast(location);
	}
	
	public Map requestForecast(String location) {
		Map<String, String> tempsMap = new HashMap<>();

		RestTemplate restTemplate = new RestTemplate();
		MainTemp temp = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/forecast?q=" + location
				+ "&APPID=a24b85481857913394404ce6d476ff31", MainTemp.class);
		for (int i = 0; i < temp.getList().length; i++) {
			tempsMap.put(temp.getList()[i].getDt_txt(), temp.getList()[i].getMain().getTemp());
		}
		for (Map.Entry<String, String> entry : tempsMap.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		return tempsMap;
	}
	
}
