package com.dp.parkingbot.bot.menus;

import com.dp.parkingbot.bot.Vehicle;

public class VehicleMenu extends BotMenu {

    private String vehicleNo;
    private String vehicleType;


    public VehicleMenu(String vehicleNo, String vehicleType) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
    }

    @Override
    public String display() {
         String vehicle = "Car";
         if (vehicleType.equalsIgnoreCase("t")){
             vehicle = "Truck";
         }
        if (vehicleType.equalsIgnoreCase("m")){
            vehicle = "Motor Bike";
        }
        return vehicle + "("+vehicleNo+")"+".\n Do you want to\n1.(P)ark\n2.(U)n-Park";
    }

    @Override
    public BotMenu nextMenu(String message) {
        BotMenu botMenu;
        if (!isValidVehicleAction(message)) {

            if (retry >=2) {
                retry = 0;
                botMenu = new ErrorMenu();
            } else {
                botMenu = new RetryMenu("Kindly enter valid action [P or U] (" + message + ") try again", this);
                retry++;
            }

        } else {
            botMenu = new Action(new Vehicle(vehicleNo,vehicleType,message));
            retry = 0;
        }
        return botMenu;
     }

    private boolean isValidVehicleAction(String message) {
        if (message.length()!=1) return false;
        return message.equalsIgnoreCase("P") || message.equalsIgnoreCase("U");
    }
}
