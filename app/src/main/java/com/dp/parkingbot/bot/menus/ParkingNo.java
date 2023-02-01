package com.dp.parkingbot.bot.menus;

public class ParkingNo extends BotMenu{

    @Override
    public String display() {
        return "Not Agreed";
    }

    @Override
    public BotMenu nextMenu(String message) {
        return new StopMenu();
    }
}
