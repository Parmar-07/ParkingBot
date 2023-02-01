package com.dp.parkingbot.bot.menus;

public class CustomMenu extends BotMenu {

    private String customDisplay;
    private BotMenu nextMenu;

    public CustomMenu(String customDisplay, BotMenu nextMenu) {
        this.customDisplay = customDisplay;
        this.nextMenu = nextMenu;
    }

    @Override
    public String display() {
        return "";
    }




    @Override
    public BotMenu nextMenu(String message) {
        return nextMenu;
    }

    public String getCustomDisplay() {
        return customDisplay;
    }
}
