package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSpotTest {

    private ParkingSpot parkingSpot = new ParkingSpot(10, ParkingType.BIKE, true);


    @Test
    public void setIdTest(){
        parkingSpot.setId(15);
        assertEquals(15,parkingSpot.getId());
    }

    @Test
    public void setParkingTypeTest() {
        parkingSpot.setParkingType(ParkingType.CAR);
        assertEquals(ParkingType.CAR, parkingSpot.getParkingType());
    }

    @Test
    public void equalsTest() {
        boolean actualEquals = parkingSpot.equals(parkingSpot);
        boolean actualEqualsNull = parkingSpot.equals(null);
        assertTrue(actualEquals);
        assertFalse(actualEqualsNull);
    }

    @Test
    public void hashCodeTest(){
        parkingSpot.setId(10);
        assertEquals(10, parkingSpot.hashCode());

    }

}
