package com.dp.parkingbot.domain;

import com.dp.parkingbot.domain.vehicles.Vehicles;

public interface ParkingLotListener {

    void onSuccess(String message, Vehicles vehicles);
    void onFail(String error);

}
