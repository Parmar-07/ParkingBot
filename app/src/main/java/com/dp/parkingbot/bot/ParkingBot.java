package com.dp.parkingbot.bot;

import android.text.TextUtils;

import com.dp.parkingbot.bot.menus.Action;
import com.dp.parkingbot.bot.menus.BotMenu;
import com.dp.parkingbot.bot.menus.CustomMenu;
import com.dp.parkingbot.bot.menus.ErrorMenu;
import com.dp.parkingbot.bot.menus.RetryMenu;
import com.dp.parkingbot.bot.menus.StartMenu;

public class ParkingBot {


    public enum MessageType {
        Default, Error
    }

    private final String[] menus = new String[]{
            "Do you want to park or un-park the car?",
            "Yes or No",
            "Enter Car Number",
            "Thank You for the Car Number",
            "Enter 1 to Park or Enter 2 to Un-Park ",
    };


    private String lastMenu = "";

    private final ParkingBotReceiver parkingBotReceiver;

    public ParkingBot(ParkingBotReceiver parkingBotReceiver) {
        this.parkingBotReceiver = parkingBotReceiver;
        this.parkingBotReceiver.onReceived("Hey!", MessageType.Default);
    }

    BotMenu botMenu = null;
    BotMenu nextBotMenu = null;
    private boolean isInputTriggered = false;

    public void setInputTriggered(boolean inputTriggered) {
        isInputTriggered = inputTriggered;
    }

    public void setNextBotMenu(BotMenu botMenu) {
        this.botMenu = botMenu;
        this.nextBotMenu = botMenu;
        isInputTriggered = true;
        process(botMenu.display());
    }

    public void process(String message) {


        if (botMenu == null) {
            botMenu = new StartMenu();
            parkingBotReceiver.onReceived(botMenu.display(), botMenu.getType());
            isInputTriggered = false;
        }

        if (isInputTriggered) {
            nextBotMenu = botMenu.nextMenu(message);


            if (nextBotMenu instanceof Action) {
                botMenu = nextBotMenu;
                parkingBotReceiver.onAction(((Action) nextBotMenu).getObject());
                return;

            }

            String reply = "";
            if (!TextUtils.isEmpty(nextBotMenu.display())) {
                reply = nextBotMenu.display();
            }
            MessageType type = nextBotMenu.getType();
            if (nextBotMenu instanceof CustomMenu) {
                reply = ((CustomMenu) nextBotMenu).getCustomDisplay();
                type = nextBotMenu.getType();
            }
            if (nextBotMenu instanceof RetryMenu) {
                reply = ((RetryMenu) nextBotMenu).getRetryDisplay();
                type = nextBotMenu.getType();
            }


            if (nextBotMenu instanceof ErrorMenu) {
                type = nextBotMenu.getType();
            }
            parkingBotReceiver.onReceived(reply, type);

            if (nextBotMenu instanceof RetryMenu) {
                parkingBotReceiver.onReceived(nextBotMenu.display(), type);

            }

            if (!(nextBotMenu instanceof RetryMenu)) {
                botMenu = nextBotMenu;
            }

            if (nextBotMenu instanceof ErrorMenu) {
                if (BotMenu.retry == 0) {
                    botMenu = null;
                    nextBotMenu = null;
                }
            }

        }

        isInputTriggered = false;


    }

    public void process2(String message) {

        if (lastMenu.isEmpty()) {
            showMenu();
            return;
        }
        if (lastMenu.equals(menus[1])) {

            if (message.equalsIgnoreCase("Yes")) {
                showEnterCarNo();
            }
            if (message.equalsIgnoreCase("No")) {
                showEnterCarNo();
            } else {
                showError();
            }

            return;
        }

        if (lastMenu.equals(menus[2])) {
            if (!isValidVehicleNo(message)) {
                showError();
            } else {
                thankForCarNo(message);
            }

            return;
        }

        showMenu();
    }

    private void thankForCarNo(String message) {
        parkingBotReceiver.onReceived(menus[3], MessageType.Default);
        parkingBotReceiver.onReceived("We are going to process Parking!", MessageType.Default);
        lastMenu = menus[3];
    }

    private boolean isValidVehicleNo(String message) {
        if (message.length() != 4) return false;

        return TextUtils.isDigitsOnly(message);
    }

    private void showError() {
        parkingBotReceiver.onReceived("Sorry! not get you", MessageType.Error);
        parkingBotReceiver.onReceived("Try Again...", MessageType.Error);
        lastMenu = "";
    }

    private void showCustomError(String error) {
        parkingBotReceiver.onReceived(error, MessageType.Error);
    }

    private void showEnterCarNo() {
        parkingBotReceiver.onReceived(menus[2], MessageType.Default);
        lastMenu = menus[2];
    }

    private void showMenu() {
        parkingBotReceiver.onReceived(menus[0], MessageType.Default);
        parkingBotReceiver.onReceived(menus[1], MessageType.Default);
        lastMenu = menus[1];
    }
}
