package com.dp.parkingbot.bot.menus;


public class ThankYou extends BotMenu {



    @Override
    public String display() {
        return "Thank You for parking Vehicle!";
    }

    @Override
    public BotMenu nextMenu(String message) {
        return new StartMenu();
    }
}
