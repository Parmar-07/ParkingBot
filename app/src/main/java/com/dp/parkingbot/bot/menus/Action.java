package com.dp.parkingbot.bot.menus;


public class Action extends BotMenu {

    private Object object;

    public Action(Object object) {
        this.object = object;
    }

    @Override
    public String display() {
        return null;
    }

    @Override
    public BotMenu nextMenu(String message) {
        return null;
    }

    public Object getObject() {
        return object;
    }
}
