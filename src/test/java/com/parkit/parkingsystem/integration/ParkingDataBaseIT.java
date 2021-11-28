package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParkingDataBaseIT {

    private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static ParkingSpotDAO parkingSpotDAO;
    private static TicketDAO ticketDAO;
    private static DataBasePrepareService dataBasePrepareService;

    @Mock
    private static InputReaderUtil inputReaderUtil;

    @BeforeAll
    private static void setUp() throws Exception {
        parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        ticketDAO = new TicketDAO();
        ticketDAO.dataBaseConfig = dataBaseTestConfig;
        dataBasePrepareService = new DataBasePrepareService();
    }

    @AfterAll
    private static void tearDown() {

    }

    @BeforeEach
    private void setUpPerTest() throws Exception {
        when(inputReaderUtil.readSelection()).thenReturn(1);
        when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
        dataBasePrepareService.clearDataBaseEntries();
    }

    @Test
    public void testParkingACar() {
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        parkingService.processIncomingVehicle();

        String actualReg = ticketDAO.getTicket("ABCDEF").getVehicleRegNumber();
        int expectParkingSpot = ticketDAO.getTicket("ABCDEF").getParkingSpot().getId();

        assertTrue(actualReg == "ABCDEF");
        assertNotEquals(expectParkingSpot, parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR));

    }

    @Test
    public void testParkingLotExit() throws Exception {
        testParkingACar();
        Thread.sleep(900);
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        parkingService.processExitingVehicle();

        Date actualOutTime = ticketDAO.getTicket("ABCDEF").getOutTime();
        double actualPrice = ticketDAO.getTicket("ABCDEF").getPrice();

        assertTrue(actualOutTime != null);
        assertNotNull(actualPrice);
    }

    @Test
    public void testParkingACarRegular() throws Exception {
        testParkingACar();
        testParkingLotExit();
        testParkingACar();

        int actualParkingSpot = ticketDAO.getTicket("ABCDEF").getParkingSpot().getId();
        System.out.println(actualParkingSpot);

        assertEquals(1, actualParkingSpot);
    }
}
