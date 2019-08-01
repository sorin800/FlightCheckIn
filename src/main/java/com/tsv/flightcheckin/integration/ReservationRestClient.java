package com.tsv.flightcheckin.integration;

import com.tsv.flightcheckin.integration.dto.Reservation;
import com.tsv.flightcheckin.integration.dto.ReservationUpdate;

public interface ReservationRestClient {
	
	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(ReservationUpdate request);
}
