package com.dp.parkingbot.bot.menus;

import com.dp.parkingbot.bot.Vehicle;

public class VehicleType extends BotMenu {
    private String vehicleNo;
    private String vehicleAction;

    public VehicleType(String vehicleNo,String vehicleAction) {
        this.vehicleNo = vehicleNo;
        this.vehicleAction = vehicleAction;
    }

    @Override
    public String display() {
        return "Enter Vehicle Type\n1.(T)ruck\n2.(C)ar\n3.(M)otor Bike";
    }

    @Override
    public BotMenu nextMenu(String message) {
        BotMenu botMenu;
        if (!isValidVehicleType(message)) {

            if (retry >=2) {
                retry = 0;
                botMenu = new ErrorMenu();
            } else {
                botMenu = new RetryMenu("Kindly enter valid type [T/C/M] (" + message + ") try again", this);
                retry++;
            }

        } else {
            botMenu =new Action(new Vehicle(vehicleNo,message,vehicleAction));
            retry = 0;
        }
        return botMenu;
    }

    private boolean isValidVehicleType(String message) {
        if (message.length()!=1) return false;
        return message.equalsIgnoreCase("T") || message.equalsIgnoreCase("C") || message.equalsIgnoreCase("M");
    }
}
