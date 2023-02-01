package com.dp.parkingbot.bot.menus;

public class StartMenu extends BotMenu {
    @Override
    public String display() {
        return "Welcome! to Parking Bot.\n Do you want to park or un-park the car?\n Enter (Y)es or (N)o.";
    }

    @Override
    public BotMenu nextMenu(String message) {

        BotMenu menu;
        if (message.equalsIgnoreCase("Y")){
            menu = new ParkingYes();
        }else if (message.equalsIgnoreCase("N")){
            menu = new StopMenu();
        }else{
            menu = new ErrorMenu();
        }

        return menu;
    }




}
