package com.parkit.parkingsystem;

import com.parkit.parkingsystem.model.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketTest {

    @Test
    public void createATicket() {
        Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - ( 60 * 60 * 1000) ); // 1 hour parking
        Date outTime = new Date();

        Ticket ticket = new Ticket();
        ticket.setId(100);
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setPrice(1.5);
        ticket.setInTime(inTime);
        ticket.setOutTime(outTime);
        ticket.setDiscount(true);

        assertEquals(ticket.getId(), 100);
        assertEquals(ticket.getVehicleRegNumber(), "ABCDEF");
        assertEquals(ticket.getPrice(), 1.5);
        assertEquals(ticket.getInTime(), inTime);
        assertEquals(ticket.getOutTime(), outTime);
        assertEquals(ticket.getDiscount(), true);
    }
}
