package com.tsv.flightcheckin.integration;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tsv.flightcheckin.integration.dto.Reservation;
import com.tsv.flightcheckin.integration.dto.ReservationUpdate;
@Component
public class ReservationRestClientImpl implements ReservationRestClient {

	private static final String RESERVATION_REST_URL = "http://localhost:8080/flightreservation/reservations/";
	//private static final String RESERVATION_REST_URL = "http://flightreservation-env.svmj5p8ppy.eu-central-1.elasticbeanstalk.com/flightreservation/reservations/";
	@Override
	public Reservation findReservation(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		Reservation reservation = restTemplate.getForObject(RESERVATION_REST_URL+id, Reservation.class);
		return reservation;
	}

	@Override
	public Reservation updateReservation(ReservationUpdate request) {
		RestTemplate restTemplate = new RestTemplate();
		Reservation reservation = restTemplate.postForObject(RESERVATION_REST_URL, request, Reservation.class);
		return null;
	}

}
