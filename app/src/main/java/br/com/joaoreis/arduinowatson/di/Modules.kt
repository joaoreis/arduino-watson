package br.com.joaoreis.arduinowatson.di

import br.com.joaoreis.arduinowatson.ui.connection.ConnectionViewModel
import br.com.joaoreis.arduinowatson.ui.voiceactions.VoiceActionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { VoiceActionsViewModel() }
    viewModel { ConnectionViewModel() }
}