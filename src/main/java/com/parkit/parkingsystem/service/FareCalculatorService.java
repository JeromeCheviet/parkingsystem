package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket) {
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
        }

        // Convert date to Unix Timestamp
        long unixInDate = ticket.getInTime().getTime();
        long unixOutDate = ticket.getOutTime().getTime();

        // Calcul difference between 2 date and have a result in millisecond. Convert milliseconds to minutes and divide by 60
        double duration = (double) (((unixOutDate - unixInDate) / 1000) / 60) / 60;

        ParkingType vehicleType = ticket.getParkingSpot().getParkingType();

        double price = calculatePrice(vehicleType, duration, ticket);
        ticket.setPrice(price);
    }

    private double calculatePrice(ParkingType vehicleType, double duration, Ticket ticket) {
        double price = 0;

        if (duration > 0.5) {
            switch (vehicleType) {
                case CAR: {
                    price = duration * Fare.CAR_RATE_PER_HOUR;
                    break;
                }
                case BIKE: {
                    price = duration * Fare.BIKE_RATE_PER_HOUR;
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unknown Parking Type");
            }

            if (ticket.getDiscount()) {
                price = price - (price * 0.05);
            }
        }
        return price;
    }
}