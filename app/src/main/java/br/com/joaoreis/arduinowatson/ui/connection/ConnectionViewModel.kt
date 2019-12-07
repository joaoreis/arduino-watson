package br.com.joaoreis.arduinowatson.ui.connection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConnectionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _isConnected = MutableLiveData<Boolean>()
    val isConnected = _isConnected



}