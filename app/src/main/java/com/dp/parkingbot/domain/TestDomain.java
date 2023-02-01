package com.dp.parkingbot.domain;

import androidx.annotation.MainThread;

import com.dp.parkingbot.domain.vehicles.CarVehicle;
import com.dp.parkingbot.domain.vehicles.Vehicles;

public class TestDomain {

    public static void main(String[] args) {

        ParkingLot parkingLot = new ParkingLot( new ParkingLotListener() {




            @Override
            public void onSuccess(String message, Vehicles vehicles) {

            }

            @Override
            public void onFail(String error) {

            }
        });
        Vehicles carVehicle1 = new CarVehicle(1234);
        parkingLot.parked(carVehicle1);

        Vehicles carVehicle2  = new CarVehicle(4567);
        parkingLot.parked(carVehicle2);

        Vehicles carVehicle3 = new CarVehicle(34567);
        parkingLot.parked(carVehicle3);

//        parkingLot.unParked(carVehicle1);
        parkingLot.parked(carVehicle3);

    }
}
