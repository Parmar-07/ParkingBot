package com.dp.parkingbot.domain;

import androidx.annotation.NonNull;

import com.dp.parkingbot.domain.spots.Slots;
import com.dp.parkingbot.domain.vehicles.VehicleTypes;
import com.dp.parkingbot.domain.vehicles.Vehicles;
import com.dp.parkingbot.presentaion.Config;

public class ParkingLot {


    private final Checker checker;
    private final ParkingLotListener parkingLotListener;


    public ParkingLot( ParkingLotListener parkingLotListener) {
        this.parkingLotListener = parkingLotListener;
        checker = new Checker(Config.AVAIL_COMPACT_SLOT, Config.AVAIL_LARGE_SLOT, Config.AVAIL_BIKE_SLOT);
    }

    public void parked(@NonNull Vehicles vehicles) {
        System.out.println("Parking Vehicle :" + vehicles.getVehicleTypes() + "No : " + vehicles.getVehicleNo());
        Vehicles parkedVehicle = checker.isAlreadyParked(vehicles.getVehicleNo());
        if (parkedVehicle != null) {

            String vType = "Car";
            if (parkedVehicle.getVehicleTypes().equals(VehicleTypes.TRUCK)) {
                vType = "Truck";
            }
            if (parkedVehicle.getVehicleTypes().equals(VehicleTypes.BIKES)) {
                vType = "Bike";
            }
            String slot = "Compact";
            if (parkedVehicle.getParkingSlot().getSlots().equals(Slots.LARGE)) {
                slot = "Large";
            }
            if (parkedVehicle.getParkingSlot().getSlots().equals(Slots.MOTOR_BIKE)) {
                slot = "Motor Bike";
            }
            notifyError("Your " + vType + "(" + parkedVehicle.getVehicleNo() + ") is already in " + slot + " slot");
            return;
        }

        Slots slot = checker.getAvailableSlots(vehicles);
        if (slot == Slots.NO_SLOTS) {
            notifyError("Slot is not available for " + vehicles.getVehicleTypes());
            return;
        }
        System.out.println("Slot " + slot + " is available for Vehicle :" + vehicles.getVehicleTypes() + "No : " + vehicles.getVehicleNo());
        checker.park(vehicles,slot);
        notifySuccess(vehicles.getVehicleTypes() + "(" + vehicles.getVehicleNo() + ") is parked in slot " + slot, vehicles);

    }


    public void unParked(int vehicles_no) {
        System.out.println("UnParking Vehicle : No : " + vehicles_no);

        Vehicles parkedVehicle = checker.isAlreadyParked(vehicles_no);

        if (parkedVehicle == null) {

            notifyError("No Vehicle("+vehicles_no+") parked in slot");
            return;
        }
        parkedVehicle.getParkingSlot().setFree(true);
        checker.unPark(parkedVehicle);
        notifySuccess( parkedVehicle.getVehicleTypes() + "(" + parkedVehicle.getVehicleNo() + ") is un-parked from slot " + parkedVehicle.getParkingSlot().getSlots(), parkedVehicle);

    }

    private void notifySuccess(String message, Vehicles vehicles) {
        System.out.println(message);
        parkingLotListener.onSuccess(message,vehicles);

    }

    private void notifyError( String message ) {
        System.out.println(message);
        parkingLotListener.onFail(message);

    }




}
