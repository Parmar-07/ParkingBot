package com.dp.parkingbot.bot.menus;

import com.dp.parkingbot.bot.ParkingBot;

public abstract class BotMenu {

    public static int retry =0;

   public abstract String display();

   public abstract BotMenu nextMenu(String message);

    public ParkingBot.MessageType getType() {
        return ParkingBot.MessageType.Default;
    }
}
