package com.dp.parkingbot.bot.menus;

import android.text.TextUtils;

public class ParkingYes extends BotMenu {


    @Override
    public String display() {
        return "Enter 4-digits Vehicle Number";
    }

    @Override
    public BotMenu nextMenu(String message) {
        BotMenu botMenu;
        if (!isValidVehicleNo(message)) {

            if (retry >= 2) {
                retry = 0;
                botMenu = new ErrorMenu();
            } else {
                botMenu = new RetryMenu("Kindly check your vehicle no (" + message + ") again?", this);
                retry++;
            }

        } else {
            botMenu = new AskFor(message);
            retry = 0;
        }

        return botMenu;
    }


    private boolean isValidVehicleNo(String message) {
        if (message.length() != 4) return false;

        return TextUtils.isDigitsOnly(message);
    }
}
