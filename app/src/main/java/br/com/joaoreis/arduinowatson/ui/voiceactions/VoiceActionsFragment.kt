package br.com.joaoreis.arduinowatson.ui.voiceactions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.joaoreis.arduinowatson.R
import br.com.joaoreis.arduinowatson.databinding.FragmentVoiceActionsBinding
import com.google.android.material.textview.MaterialTextView
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val SPEECH_REQUEST_CODE = 0

class VoiceActionsFragment : Fragment() {

    private val voiceActionsViewModel: VoiceActionsViewModel by viewModel()
    private lateinit var binding: FragmentVoiceActionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_voice_actions,
            container,
            false
        )
        setupObservers(binding.textDashboard)
        setupListener(binding.btGravar)

        return binding.root
    }

    private fun setupObservers(textDashboard: MaterialTextView) {
        voiceActionsViewModel.text.observe(this, Observer {
            textDashboard.text = it
        })
    }

    private fun setupListener(btGravar: ImageView) {
        btGravar.setOnClickListener {
            displaySpeechRecognizer()
        }
    }

    private fun displaySpeechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
        }
        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val spokenText =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results?.get(0)
                }
            // Do something with spokenText
            binding.textDashboard.text = spokenText
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}