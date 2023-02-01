package com.dp.parkingbot.presentaion;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dp.parkingbot.R;
import com.dp.parkingbot.bot.ParkingBot;

import java.util.ArrayList;

public class BotAdapter extends RecyclerView.Adapter<BotAdapter.BotViewHolder> {

    private final ArrayList<BotChatModel> chatModels = new ArrayList<>();


    public void sendFromChat(ParkingBot parkingBot, String message) {

        BotChatModel model = new BotChatModel(message, true);
        chatModels.add(model);
        if (getItemCount() >= 0)
            notifyItemInserted(chatModels.size() - 1);
        else
            notifyItemInserted(0);

        parkingBot.process(message);
        parkingBot.setInputTriggered(true);


    }


    public void sendFromBot(String message, ParkingBot.MessageType type) {

        BotChatModel model = new BotChatModel(message, false);
        model.setWrongInput(type == ParkingBot.MessageType.Error);
        chatModels.add(model);
        if (getItemCount() >= 0)
            notifyItemInserted(chatModels.size() - 1);
        else
            notifyItemInserted(0);


    }


    @NonNull
    @Override
    public BotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        // below code is to switch our
        // layout type along with view holder.
        switch (viewType) {
            case 0:
                // below line we are inflating user message layout.
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_send, parent, false);
                return new BotViewHolder(view);
            case 1:
                // below line we are inflating bot message layout.
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receive, parent, false);
                return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BotViewHolder holder, int position) {
        BotChatModel modal = chatModels.get(position);
        ((BotViewHolder) holder).chatText.setText(modal.getMessage());
        if (modal.isWrongInput()) {
            ((BotViewHolder) holder).chatText.setTextColor(Color.RED);
        } else {
            ((BotViewHolder) holder).chatText.setTextColor(Color.WHITE);
        }
    }


    @Override
    public int getItemCount() {
        return chatModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        // below line of code is to set position.
        if (chatModels.get(position).isSender()) {
            return 0;
        }
        return 1;

    }

    public static class BotViewHolder extends RecyclerView.ViewHolder {

        // creating a variable
        // for our text view.
        TextView chatText;

        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing with id.
            chatText = itemView.findViewById(R.id.chatText);
        }
    }

}
