package br.com.joaoreis.arduinowatson.ui.connection

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothDevice.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.joaoreis.arduinowatson.databinding.DeviceItemBinding
import br.com.joaoreis.arduinowatson.model.Device
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

            when (device.bondState) {
                BOND_NONE -> bluetoothKit.connect(device)
                BOND_BONDING -> return
                BOND_BONDED ->  Toast.makeText(item.context, "Conectado!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}


