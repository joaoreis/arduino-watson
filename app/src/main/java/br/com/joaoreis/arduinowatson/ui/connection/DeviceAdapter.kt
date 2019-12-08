package br.com.joaoreis.arduinowatson.ui.connection

import android.app.Activity
import android.app.AlertDialog
import android.bluetooth.BluetoothDevice
import android.content.DialogInterface
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.joaoreis.arduinowatson.R
import br.com.joaoreis.arduinowatson.databinding.DeviceItemBinding
import br.com.joaoreis.arduinowatson.model.Device
import br.com.joaoreis.arduinowatson.ui.voiceactions.VoiceActionsFragment
import com.sirvar.bluetoothkit.BluetoothKit

class DeviceAdapter(
    private val devices: MutableList<Device>,
    private val bluetoothDevices: MutableList<BluetoothDevice>
) :
    RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {


    private val bluetoothKit = BluetoothKit()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DeviceItemBinding.inflate(layoutInflater, parent, false)
        return DeviceViewHolder(binding, bluetoothDevices)
    }

    override fun getItemCount() = devices.size

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devices[position]
        holder.bind(device)
    }

    class DeviceViewHolder(
        private val binding: DeviceItemBinding,
        private val bluetoothDevices: MutableList<BluetoothDevice>
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private val bluetoothKit = BluetoothKit()


        fun bind(device: Device) {
            binding.device = device
            binding.deviceLayout.setOnClickListener(this)
        }

        override fun onClick(item: View) {
            val device = bluetoothDevices[adapterPosition]
            Toast.makeText(item.context, "Conectando a ${device.name}", Toast.LENGTH_SHORT).show()

            Handler().postDelayed({
                val builder = AlertDialog.Builder(item.context)
                builder.setMessage("Conectado!")
                    .setPositiveButton("Ok") { dialogInterface, _ ->
                        dialogInterface.dismiss()
                    }
                    .show()
            },3000)
        }

    }
}


