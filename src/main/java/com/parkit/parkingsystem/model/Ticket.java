package com.parkit.parkingsystem.model;

import java.util.Date;

public class Ticket {
    private int id;
    private ParkingSpot parkingSpot;
    private String vehicleRegNumber;
    private double price;
    private Date inTime;
    private Date outTime;
    private boolean discount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getInTime() {
        //return inTime;
        if (inTime != null) {
            return (Date) inTime.clone();
        }
        return null;
    }

    public void setInTime(Date inTime) {
        //this.inTime = inTime;
        if (inTime != null) {
            this.inTime = new Date(inTime.getTime());
        }
    }

    public Date getOutTime() {
        //return outTime;
        if (outTime != null) {
            return (Date) outTime.clone();
        }

        return null;
    }

    public void setOutTime(Date outTime) {
        //this.outTime = outTime;
        if (outTime != null) {
            this.outTime = new Date(outTime.getTime());
        }
    }

    public boolean getDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }
}
