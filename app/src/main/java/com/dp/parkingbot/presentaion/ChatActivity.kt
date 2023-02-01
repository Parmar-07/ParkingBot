package com.dp.parkingbot.presentaion

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import com.dp.parkingbot.R
import com.dp.parkingbot.bot.ParkingBot
import com.dp.parkingbot.bot.ParkingBotReceiver
import com.dp.parkingbot.bot.Vehicle
import com.dp.parkingbot.bot.menus.CustomMenu
import com.dp.parkingbot.bot.menus.NextMenu
import com.dp.parkingbot.bot.menus.StopMenu
import com.dp.parkingbot.bot.menus.ThankYou
import com.dp.parkingbot.domain.ParkingLot
import com.dp.parkingbot.domain.ParkingLotListener
import com.dp.parkingbot.domain.vehicles.*

class ChatActivity : AppCompatActivity(), ParkingBotReceiver, ParkingLotListener {


    private val botAdapter = BotAdapter()
    private val parkingLot = ParkingLot(this)
    private val parkingBot = ParkingBot(this)
    private val parkingViewFragment = ParkingViewFragment()
    private var parkingPreviewed = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragView : FragmentContainerView = findViewById(R.id.fragView)
        supportFragmentManager.beginTransaction()
             .add(R.id.fragView, parkingViewFragment, "parkingViewFragment")
            .commitNowAllowingStateLoss()
        findViewById<ImageView>(R.id.parkingView).setOnClickListener {
            if (fragView.visibility== View.GONE) {
                parkingPreviewed = true
                fragView.visibility = View.VISIBLE
            }else{
                parkingPreviewed = false
                fragView.visibility = View.GONE
            }
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.adapter = botAdapter
        val editText = findViewById<EditText>(R.id.editText)
        editText.setOnEditorActionListener { v, actionId, event ->


            if (actionId == EditorInfo.IME_ACTION_SEND
                || actionId == EditorInfo.IME_ACTION_DONE
            ) {
                botAdapter.sendFromChat(parkingBot, v.text.toString())
                recyclerView.postDelayed({
                    recyclerView.scrollToPosition(botAdapter.itemCount - 1)
                }, 1000)
                editText.text = null
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }


    }

    override fun onReceived(message: String?, messageType: ParkingBot.MessageType?) {
        botAdapter.sendFromBot(message, messageType)
    }

    override fun onAction(data: Any?) {
        if (data != null) {
            if (data is Vehicle) {
                val vNo: Int = (data.vehicleNo ?: "0").toInt()
                val vehicle: Vehicles = when (data.vehicleType ?: "") {
                    "c", "C" -> CarVehicle(vNo)
                    "t", "T" -> TruckVehicle(vNo)
                    else -> BikeVehicle(vNo)
                }
                if (data.action.equals("p", ignoreCase = true)) {
                    parkingLot.parked(vehicle)
                } else {
                    parkingLot.unParked(vNo)
                }


            }
        }
    }

    override fun onSuccess(output: String?, vehicles: Vehicles) {
        botAdapter.sendFromBot(output, ParkingBot.MessageType.Default)

        val vType = when (vehicles.vehicleTypes!!) {
            VehicleTypes.CAR -> "Car"
            VehicleTypes.TRUCK -> "Truck"
            VehicleTypes.BIKES -> "Motor Bike"
        }
        val vAction = if (!vehicles.parkingSlot.isFree) "Parking" else "Un-Parking"
        val message = "Thank you $vAction $vType(${vehicles.vehicleNo})"
        if (!parkingPreviewed){
            findViewById<ImageView>(R.id.parkingView).performClick()
        }
        parkingBot.setNextBotMenu(CustomMenu(message, ThankYou()))
        parkingViewFragment.update(vehicles,vAction)

    }

    override fun onFail(error: String?) {
        botAdapter.sendFromBot(error, ParkingBot.MessageType.Error)
        parkingBot.setNextBotMenu(NextMenu(CustomMenu("",StopMenu())))

    }


}