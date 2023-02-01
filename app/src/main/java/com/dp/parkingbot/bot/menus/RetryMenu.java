package com.dp.parkingbot.bot.menus;

public class RetryMenu extends BotMenu {

    private String retryDisplay;
    private BotMenu nextMenu;

    public RetryMenu(String retryDisplay, BotMenu nextMenu) {
        this.retryDisplay = retryDisplay;
        this.nextMenu = nextMenu;
    }

    @Override
    public String display() {
        return nextMenu.display();
    }




    @Override
    public BotMenu nextMenu(String message) {
        return nextMenu;
    }

    public String getRetryDisplay() {
        return retryDisplay;
    }
}
