package br.com.joaoreis.arduinowatson.ui.voiceactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.joaoreis.arduinowatson.R

class VoiceActionsFragment : Fragment() {

    private lateinit var voiceActionsViewModel: VoiceActionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        voiceActionsViewModel =
            ViewModelProviders.of(this).get(VoiceActionsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_voice_actions, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        voiceActionsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}