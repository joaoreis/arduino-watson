package br.com.joaoreis.arduinowatson.ui.connection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.joaoreis.arduinowatson.databinding.DeviceItemBinding
import br.com.joaoreis.arduinowatson.model.Device

class DeviceAdapter(private val devices: MutableList<Device>) :
    RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DeviceItemBinding.inflate(layoutInflater, parent, false)
        return DeviceViewHolder(binding)
    }

    override fun getItemCount() = devices.size

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devices[position]
        holder.bind(device)
    }

    class DeviceViewHolder(private val binding: DeviceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(device: Device) {
            binding.device = device
        }
    }
}
