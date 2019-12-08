package br.com.joaoreis.arduinowatson.ui.connection

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.joaoreis.arduinowatson.R
import br.com.joaoreis.arduinowatson.databinding.FragmentConnectionBinding
import br.com.joaoreis.arduinowatson.model.Device
import com.sirvar.bluetoothkit.BluetoothKit
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConnectionFragment : Fragment() {

    private val viewModel: ConnectionViewModel by viewModel()
    private val devices = mutableListOf<Device>()
    private val bluetoothDevices = mutableListOf<BluetoothDevice>()
    private val deviceAdapter = DeviceAdapter(devices, bluetoothDevices)

    private lateinit var binding: FragmentConnectionBinding
    private val bluetoothKit = BluetoothKit()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_connection,
            container,
            false
        )

        binding.viewModel = viewModel
        setupViews()
        setupBluetooth()
        setupListener(binding)
        setupPairedDevices()

        return binding.root
    }

    private fun setupPairedDevices() {
        bluetoothKit.pairedDevices.forEach {
            bluetoothDevices.add(it)
            devices.add(Device(it.name, it.address))

        }
    }

    private fun setupViews() {
        binding.rvDeviceList.devicesList.apply {
            adapter = deviceAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }

    }


    private fun setupBluetooth() {
        if (!bluetoothKit.isEnabled) {
            bluetoothKit.enable()
        }
    }

    private fun setupListener(binding: FragmentConnectionBinding) {
        binding.btPesquisar.setOnClickListener {
            bluetoothKit.bluetoothAdapter.startDiscovery()
        }
    }

}