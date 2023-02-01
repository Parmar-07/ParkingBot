package com.dp.parkingbot.bot.menus;

import com.dp.parkingbot.bot.Vehicle;

public class AskFor extends BotMenu {

    private String vehicleNo;


    public AskFor(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    @Override
    public String display() {
        return " Do you want to\n1.(P)ark\n2.(U)n-Park";
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

            if (message.equalsIgnoreCase("u")){
                botMenu =new Action(new Vehicle(vehicleNo,message,""));
            }else{
                botMenu = new VehicleType(vehicleNo,message);
            }

            retry = 0;
        }
        return botMenu;
     }

    private boolean isValidVehicleAction(String message) {
        if (message.length()!=1) return false;
        return message.equalsIgnoreCase("P") || message.equalsIgnoreCase("U");
    }
}
