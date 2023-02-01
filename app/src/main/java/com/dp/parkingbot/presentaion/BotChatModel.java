package com.dp.parkingbot.presentaion;

public class BotChatModel {

    private boolean isWrongInput = false;
    private String message;
    private boolean isSender;


    public BotChatModel(String message, boolean isSender) {
        this.message = message;
        this.isSender = isSender;
    }

    public boolean isSender() {
        return isSender;
    }

    public String getMessage() {
        return message;
    }

    public boolean isWrongInput() {
        return isWrongInput;
    }

    public void setWrongInput(boolean wrongInput) {
        isWrongInput = wrongInput;
    }
}
