package com.dp.parkingbot.presentaion;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dp.parkingbot.R;
import com.dp.parkingbot.domain.spots.CompactSlot;
import com.dp.parkingbot.domain.spots.LargeSlot;
import com.dp.parkingbot.domain.spots.MotorBikeSlot;
import com.dp.parkingbot.domain.spots.ParkingSlot;
import com.dp.parkingbot.domain.spots.Slots;
import com.dp.parkingbot.domain.vehicles.VehicleTypes;
import com.dp.parkingbot.domain.vehicles.Vehicles;

import java.util.ArrayList;

public class SlotsAdapter extends RecyclerView.Adapter<SlotsAdapter.SlotViewHolder> {

    private final ArrayList<ParkingSlot> slots = new ArrayList<>();


    public SlotsAdapter() {
        for (int i = 0; i < Config.AVAIL_BIKE_SLOT; i++) {
            slots.add(new CompactSlot(Slots.COMPACT));
        }
        for (int j = 0; j < Config.AVAIL_LARGE_SLOT; j++) {
            slots.add(new LargeSlot(Slots.LARGE));

        }
        for (int k = 0; k < Config.AVAIL_BIKE_SLOT; k++) {
            slots.add(new MotorBikeSlot(Slots.MOTOR_BIKE));
        }
    }

    public void updateSlot(Vehicles vehicles, boolean isForParking) {

        for (int i = 0; i < getItemCount(); i++) {
            ParkingSlot pSlot = slots.get(i);
            ParkingSlot vSlot = vehicles.getParkingSlot();

            if (pSlot.getSlots() == vSlot.getSlots()) {

                if (isForParking && pSlot.isFree()) {
                    slots.remove(pSlot);
                    slots.add(i, vSlot);
                    notifyItemChanged(i);
                    break;
                }

                if (!isForParking) {
                    vSlot.setFree(true);
                    slots.remove(pSlot);
                    slots.add(i, vSlot);
                    notifyItemChanged(i);
                    break;
                }

            }

//
//
//            if (!isForParking && !pSlot.isFree()) {
//                slots.remove(pSlot);
//                pSlot.setVehicle(vehicles);
//                pSlot.setFree(true);
//                pSlot.setSlots(vehicles.getParkingSlot().getSlots());
//                slots.add(i, pSlot);
//                notifyItemRangeChanged(i,getItemCount());
//                break;
//            }
        }

    }


    @NonNull
    @Override
    public SlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        // below code is to switch our
        // layout type along with view holder.
        switch (viewType) {
            case 2:
                // below line we are inflating user message layout.
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_large_slot, parent, false);
                return new SlotViewHolder(view);
            case 1:
                // below line we are inflating bot message layout.
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_compact_slot, parent, false);
                return new SlotViewHolder(view);

            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_small_slot, parent, false);
                return new SlotViewHolder(view);
        }
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull SlotViewHolder holder, int position) {

        ParkingSlot modal = slots.get(position);
        SlotViewHolder slotViewHolder = (SlotViewHolder) holder;
        String vNo = "0000";
        int sColor = Color.parseColor("#00BCD4");
        if (!modal.isFree()) {

            String vType;
            if (modal.getVehicle().getVehicleTypes() == VehicleTypes.TRUCK) {
                vType = "Truck";
            } else if (modal.getVehicle().getVehicleTypes() == VehicleTypes.CAR) {
                vType = "Car";
            } else {
                vType = "Bike";
            }

            vNo = String.format("%s(%d)", vType, modal.getVehicle().getVehicleNo());
            sColor = Color.parseColor("#FFD60101");
        }
        slotViewHolder.vehicleNoText.setText(vNo);
        slotViewHolder.vSpace.setBackgroundColor(sColor);
    }


    @Override
    public int getItemCount() {
        return slots.size();
    }

    @Override
    public int getItemViewType(int position) {
        // below line of code is to set position.
        switch (slots.get(position).getSlots()) {
            case LARGE:
                return 2;
            case COMPACT:
                return 1;
            default:
                return 0;
        }

    }

    public static class SlotViewHolder extends RecyclerView.ViewHolder {

        // creating a variable
        // for our text view.
        TextView vehicleNoText;
        ConstraintLayout vSpace;

        public SlotViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing with id.
            vehicleNoText = itemView.findViewById(R.id.vehicleNo);
            vSpace = itemView.findViewById(R.id.vSpace);
        }
    }

}
