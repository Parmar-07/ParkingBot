package com.dp.parkingbot.presentaion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dp.parkingbot.R;
import com.dp.parkingbot.domain.spots.ParkingSlot;
import com.dp.parkingbot.domain.vehicles.Vehicles;

import org.jetbrains.annotations.NotNull;

public class ParkingViewFragment extends Fragment {


    private final SlotsAdapter slotsAdapter = new SlotsAdapter();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parking_view, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setAdapter(slotsAdapter);

    }

    public void update(@NonNull Vehicles vehicles, @NotNull String vAction) {
        slotsAdapter.updateSlot(vehicles,vAction.equalsIgnoreCase("Parking"));
    }
}
