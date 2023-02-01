package com.dp.parkingbot.bot;

public class Vehicle {

    private String vehicleNo;
    private String vehicleType;
    private String action;

    public Vehicle(String vehicleNo, String vehicleType,String action) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.action = action;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getAction() {
        return action;
    }
}
