package com.dp.parkingbot.bot.menus;

import com.dp.parkingbot.bot.ParkingBot;

public class ErrorMenu extends BotMenu {
    @Override
    public String display() {
        return "Sorry! not get you\nTry Again...";
    }

    @Override
    public  BotMenu nextMenu(String message) {
        return new StartMenu();
    }

    @Override
    public ParkingBot.MessageType getType() {
        return ParkingBot.MessageType.Error;
    }
}
