package com.dp.parkingbot.bot;

public interface ParkingBotReceiver {
     void onReceived(String message, ParkingBot.MessageType messageType);
     void onAction(Object data);

}
