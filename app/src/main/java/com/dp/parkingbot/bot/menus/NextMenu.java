package com.dp.parkingbot.bot.menus;

public class NextMenu extends BotMenu {

     private CustomMenu customMenu;

    public NextMenu(CustomMenu customMenu) {
         this.customMenu = customMenu;
    }

    @Override
    public String display() {
        return "";
    }




    @Override
    public BotMenu nextMenu(String message) {
        return customMenu.nextMenu(message);
    }

 }
