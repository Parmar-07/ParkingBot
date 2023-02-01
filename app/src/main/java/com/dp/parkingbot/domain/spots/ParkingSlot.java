package com.dp.parkingbot.domain.spots;

import com.dp.parkingbot.domain.vehicles.Vehicles;

public class ParkingSlot {

    private Slots slots;
    private Vehicles vehicles;
    private boolean isFree=true;

    public ParkingSlot(Slots slots) {
        this.slots = slots;
    }

    public Slots getSlots() {
        return slots;
    }



    public Vehicles getVehicle() {
        return vehicles;
    }

    public void setVehicle(Vehicles vehicles) {
        this.vehicles = vehicles;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public void setSlots(Slots slots) {
        this.slots = slots;
    }
}
