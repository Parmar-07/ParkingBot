package com.dp.parkingbot.domain;

import com.dp.parkingbot.domain.spots.CompactSlot;
import com.dp.parkingbot.domain.spots.LargeSlot;
import com.dp.parkingbot.domain.spots.MotorBikeSlot;
import com.dp.parkingbot.domain.spots.ParkingSlot;
import com.dp.parkingbot.domain.spots.Slots;
import com.dp.parkingbot.domain.vehicles.BikeVehicle;
import com.dp.parkingbot.domain.vehicles.CarVehicle;
import com.dp.parkingbot.domain.vehicles.TruckVehicle;
import com.dp.parkingbot.domain.vehicles.Vehicles;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Checker {
    private ArrayList<CompactSlot> compactSpots = new ArrayList<>();
    private ArrayList<LargeSlot> largeSpots = new ArrayList<>();
    private ArrayList<MotorBikeSlot> motorBikeSpots = new ArrayList<>();


    private int availCompactSpots;
    private int availLargeSpots;
    private int availMotorBikeSpots;


    public Checker(int availCompactSpots, int availLargeSpots, int availMotorBikeSpots) {
        this.availCompactSpots = availCompactSpots;
        this.availLargeSpots = availLargeSpots;
        this.availMotorBikeSpots = availMotorBikeSpots;
    }


    public Slots getAvailableSlots(Vehicles vehicles) {



        if (vehicles instanceof TruckVehicle){
             if (availLargeSpots > 0){
                 return Slots.LARGE;
             }
        }


        if (vehicles instanceof CarVehicle){
            if (availCompactSpots >0) return Slots.COMPACT;
           else  if (availLargeSpots >0) return Slots.LARGE;

        }

        if (vehicles instanceof BikeVehicle){
            if (availMotorBikeSpots >0) return Slots.MOTOR_BIKE;
            else if (availCompactSpots >0) return Slots.COMPACT;
            else  if (availLargeSpots >0) return Slots.LARGE;
        }

        return Slots.NO_SLOTS;
    }


    public void park(Vehicles vehicles,Slots availSlot){

        if (availSlot == Slots.NO_SLOTS) return;

        parking(vehicles,availSlot);

    }



    private void parking(Vehicles vehicles,Slots availSlot) {

        if (availSlot == Slots.LARGE){
            parkVehicleInLargeSlot(vehicles);
            return;
        }

        if (availSlot == Slots.COMPACT){
            parkVehicleInCompactSlot(vehicles);
            return;
        }

        if (availSlot == Slots.MOTOR_BIKE){
            parkVehicleInBikeSlot(vehicles);
        }

    }

    private void parkVehicleInBikeSlot(Vehicles vehicles) {
        MotorBikeSlot slot = new MotorBikeSlot(Slots.MOTOR_BIKE);
        slot.setFree(false);
        slot.setVehicle(vehicles);
        vehicles.setParkingSlot(slot);
        motorBikeSpots.add(slot);
        availMotorBikeSpots--;
    }

    private void parkVehicleInCompactSlot(Vehicles vehicles) {
        CompactSlot slot = new CompactSlot(Slots.COMPACT);
        slot.setFree(false);
        slot.setVehicle(vehicles);
        vehicles.setParkingSlot(slot);
        compactSpots.add(slot);
        availCompactSpots--;
    }

    private void parkVehicleInLargeSlot(Vehicles vehicles) {
        LargeSlot slot = new LargeSlot(Slots.LARGE);
        slot.setFree(false);
        slot.setVehicle(vehicles);
        vehicles.setParkingSlot(slot);
        largeSpots.add(slot);
        availLargeSpots--;
    }

    public void unPark(Vehicles vehicles) {

        ParkingSlot parkingSlot = vehicles.getParkingSlot();
        Slots vehicleSlot = vehicles.getParkingSlot().getSlots();
        if (vehicleSlot == Slots.LARGE){
            unParkVehicleInLargeSlot(vehicles,parkingSlot);
            return;
        }

        if (vehicleSlot == Slots.COMPACT){
            unParkVehicleInCompactSlot(vehicles,parkingSlot);
            return;
        }

        if (vehicleSlot == Slots.MOTOR_BIKE){
            unParkVehicleInBikeSlot(vehicles,parkingSlot);
        }


    }

    private void unParkVehicleInBikeSlot(Vehicles vehicles,ParkingSlot parkingSlot) {
        motorBikeSpots.remove(parkingSlot);
        availMotorBikeSpots++;
        System.out.println("UnParked Vehicle :"+vehicles.getVehicleTypes()+ "No : "+vehicles.getVehicleNo()+"From "+parkingSlot.getSlots().name());

    }

    private void unParkVehicleInCompactSlot(Vehicles vehicles,ParkingSlot parkingSlot) {
        compactSpots.remove(parkingSlot);
        availCompactSpots++;
        System.out.println("UnParked Vehicle :"+vehicles.getVehicleTypes()+ "No : "+vehicles.getVehicleNo()+"From "+parkingSlot.getSlots().name());
    }

    private void unParkVehicleInLargeSlot(Vehicles vehicles,ParkingSlot parkingSlot) {
        largeSpots.remove(parkingSlot);
        availLargeSpots++;
        System.out.println("UnParked Vehicle :"+vehicles.getVehicleTypes()+ "No : "+vehicles.getVehicleNo()+"From "+parkingSlot.getSlots().name());

    }

    public Vehicles isAlreadyParked(int vehicleNo) {

        final Vehicles[] parkVehicle = {null};

        largeSpots.forEach(slot -> {
            if (slot.getVehicle().getVehicleNo() == vehicleNo){
                parkVehicle[0] = slot.getVehicle();
            }
        });

        compactSpots.forEach(slot -> {
            if (slot.getVehicle().getVehicleNo() == vehicleNo){
                parkVehicle[0] = slot.getVehicle();
            }
        });

        motorBikeSpots.forEach(slot -> {
            if (slot.getVehicle().getVehicleNo() == vehicleNo){
                parkVehicle[0] = slot.getVehicle();
            }
        });

        return parkVehicle[0];
    }
}
