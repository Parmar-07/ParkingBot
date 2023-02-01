package com.dp.parkingbot

import com.dp.parkingbot.domain.ParkingLot
import com.dp.parkingbot.domain.ParkingLotListener
import com.dp.parkingbot.domain.vehicles.CarVehicle
import com.dp.parkingbot.domain.vehicles.Vehicles
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testParkingLot(){
        val parkingLot = ParkingLot( object : ParkingLotListener{


            override fun onSuccess(message: String?, vehicles: Vehicles?) {
                TODO("Not yet implemented")
            }

            override fun onFail(error: String?) {
                TODO("Not yet implemented")
            }
        })
        var carVehicle: Vehicles = CarVehicle(1234)
        parkingLot.parked(carVehicle)


        carVehicle = CarVehicle(4567)
        parkingLot.parked(carVehicle)

        carVehicle = CarVehicle(34567)
        parkingLot.parked(carVehicle)


        carVehicle = CarVehicle(1234)
//        parkingLot.unParked(carVehicle)
    }

}