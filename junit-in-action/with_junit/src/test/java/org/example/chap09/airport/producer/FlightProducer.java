package org.example.chap09.airport.producer;

import org.example.chap09.airport.Flight;
import org.example.chap09.airport.FlightBuilderUtil;

import javax.enterprise.inject.Produces;
import java.io.IOException;

public class FlightProducer {

	@Produces
	public Flight createFlight() throws IOException {
		return FlightBuilderUtil.buildFlightFromCsv();
	}
}
