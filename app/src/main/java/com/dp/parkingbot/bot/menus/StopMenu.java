package com.dp.parkingbot.bot.menus;


public class StopMenu extends BotMenu {

    @Override
    public String display() {
        return "Thank You for visiting!";
    }

    @Override
    public BotMenu nextMenu(String message) {
        return new StartMenu();
    }
}
