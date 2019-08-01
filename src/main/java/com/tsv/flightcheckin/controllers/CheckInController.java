package com.tsv.flightcheckin.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsv.flightcheckin.integration.ReservationRestClient;
import com.tsv.flightcheckin.integration.TemperatureRestClientImpl;
import com.tsv.flightcheckin.integration.dto.Reservation;
import com.tsv.flightcheckin.integration.dto.ReservationUpdate;

@Controller
public class CheckInController {
	@Autowired
	ReservationRestClient restClient;
	
	@Autowired
	TemperatureRestClientImpl temperatureClient;
	
	private int count;
	private double averageTemp;
	private boolean foundTemp;
	private boolean noCheckin;

	@RequestMapping("/")
	public String showStartCheckin() {
		return "startCheckIn";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/startCheckIn", method = RequestMethod.POST)
	public String giveForecast(@RequestParam("reservationId") Long reservationId, Model model) {
		try {
			Reservation reservation = restClient.findReservation(reservationId);
			
			//Temperature
			Map<String,String> listaVreme = new HashMap<>();
			listaVreme = temperatureClient.receiveForecastByLocation(reservation.getFlight().getArrivalCity());
			// Compare Dates
			LocalDate currentDate = LocalDate.now();
			long daysBetween = ChronoUnit.DAYS.between(currentDate, reservation.getFlight().getDateOfDeparture());
			System.out.println(daysBetween);
			//Check if the departure date is between 1 and 5 days
			if (daysBetween >= 0 && daysBetween <= 5) {
				//Iterate through Map and search if the key contains the departure date
				for (Entry<String,String> pair : listaVreme.entrySet()){
					if(pair.getKey().contains(reservation.getFlight().getDateOfDeparture().toString())) {
						count++;
			        	averageTemp += Double.parseDouble(pair.getValue().toString());
			        }
			    }
				
				double finalTempKelvin = averageTemp/count;
				double celsiusTemp = finalTempKelvin - 273.15;
				int formattedTemp = (int) Math.round(celsiusTemp);

				
				count = 0;
				averageTemp = 0;
				foundTemp = true;
				model.addAttribute("foundTemp", foundTemp);
				System.out.println(foundTemp);
				model.addAttribute("temp", formattedTemp);
				model.addAttribute("reservation", reservation);
				
				return "displayReservationDetails";
			}else {
				noCheckin = true;
				model.addAttribute("noCheckin",noCheckin);
				model.addAttribute("messageNoFlightsFound","You can only do the check in with a maximum of 5 days before the flight!");
				return "displayReservationDetails";
			}
		}catch(Exception e) {
			model.addAttribute("noReservationFound","There is no reservation with the provided id!");
			return "startCheckIn";
		}
	}

	@RequestMapping(value = "/completeCheckIn", method = RequestMethod.POST)
	public String completeCheckIn(@RequestParam("reservationId") Long reservationId,
			@RequestParam("numberOfBags") int numberOfBags) {
		ReservationUpdate reservationUpdateRequest = new ReservationUpdate();

		reservationUpdateRequest.setId(reservationId);
		reservationUpdateRequest.setCheckedIn(true);
		reservationUpdateRequest.setNumberOfBags(numberOfBags);

		restClient.updateReservation(reservationUpdateRequest);
		return "checkinConfirmation";
	}
}
