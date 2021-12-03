package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParkingSpotDAOTest {

    @Mock
    private static ParkingSpotDAO parkingSpotDAO;

    @BeforeAll
    private static void setUp() throws Exception {
        parkingSpotDAO = new ParkingSpotDAO();
    }

    @Test
    public void getNextAvailableSlotTest() {
        when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(1);

        ParkingType parkingType = ParkingType.CAR;
        int actualSlot = parkingSpotDAO.getNextAvailableSlot(parkingType);

        verify(parkingSpotDAO, Mockito.times(1)).getNextAvailableSlot(parkingType);
        assertEquals(1,actualSlot);
    }

    @Test
    public void updateParkingTest() {
        when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);

        ParkingSpot parkingSpot = new ParkingSpot(5,ParkingType.BIKE, true);
        boolean updated = parkingSpotDAO.updateParking(parkingSpot);

        verify(parkingSpotDAO, Mockito.times(1)).updateParking(parkingSpot);
        assertTrue(updated);
    }

    @Test
    public void countVehicleRegNumberTest() {
        when(parkingSpotDAO.countVehicleRegNumber(any())).thenReturn(1);

        String vehicleRegNumber = "ABCDEF";
        int actualCount = parkingSpotDAO.countVehicleRegNumber(vehicleRegNumber);

        verify(parkingSpotDAO, Mockito.times(1)).countVehicleRegNumber(vehicleRegNumber);
        assertEquals(1, actualCount);
    }
}