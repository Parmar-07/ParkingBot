package com.dp.parkingbot.domain.vehicles;

import com.dp.parkingbot.domain.spots.ParkingSlot;
import com.dp.parkingbot.domain.spots.Slots;

public class Vehicles {

    private int vehicleNo;
    private ParkingSlot parkingSlot;
    private VehicleTypes vehicleTypes;


    public Vehicles(int vehicleNo, VehicleTypes vehicleTypes) {
        this.vehicleNo = vehicleNo;
        this.vehicleTypes = vehicleTypes;
    }




    public VehicleTypes getVehicleTypes() {
        return vehicleTypes;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public int getVehicleNo() {
        return vehicleNo;
    }
}
