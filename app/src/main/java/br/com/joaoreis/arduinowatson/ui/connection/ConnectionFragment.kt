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
import c.tlgbltcn.library.BluetoothHelper
import c.tlgbltcn.library.BluetoothHelperListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConnectionFragment : Fragment(), BluetoothHelperListener {

    private val viewModel: ConnectionViewModel by viewModel()
    private val devices = mutableListOf<Device>()
    private val deviceAdapter = DeviceAdapter(devices)

    private lateinit var binding: FragmentConnectionBinding
    private lateinit var bluetoothHelper: BluetoothHelper

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
////        setupBluetooth()
//    }

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

        return binding.root
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
        devices.add(Device("1", "meumac"))
//        devices.add(Device("2", "meumac"))
//        devices.add(Device("3", "02:b3"))
//        devices.add(Device("4", "4"))
//        devices.add(Device("5", "5"))
//        devices.add(Device("6", "66666666"))
//        devices.add(Device("7", "77777777"))
    }


    private fun setupBluetooth() {
        bluetoothHelper = BluetoothHelper(requireContext(), this)
            .setPermissionRequired(true)
            .create()
    }

    private fun setupListener(binding: FragmentConnectionBinding) {
        binding.btPesquisar.setOnClickListener {
            //            onStartDiscovery()
//            bluetoothHelper.startDiscovery()
        }
    }

    override fun getBluetoothDeviceList(device: BluetoothDevice) {
        devices.add(Device(device.name, device.address))
        deviceAdapter.notifyDataSetChanged()
    }

    override fun onDisabledBluetooh() {
    }

    override fun onEnabledBluetooth() {
    }

    override fun onFinishDiscovery() {
    }

    override fun onStartDiscovery() {
        binding.textHome.visibility = View.VISIBLE
    }

//    override fun onResume() {
//        super.onResume()
//        bluetoothHelper.registerBluetoothStateChanged()
//    }
//    override fun onPause() {
//        super.onPause()
//        bluetoothHelper.unregisterBluetoothStateChanged()
//    }
}