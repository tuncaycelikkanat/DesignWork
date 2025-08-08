package com.tuncaycelikkanat.designwork

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.tuncaycelikkanat.designwork.databinding.FragmentSettingsBinding
import java.text.SimpleDateFormat
import java.util.Locale

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private var control = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.btnRead.setOnClickListener {
            val input = binding.editTextInput.text.toString()
            binding.textViewResult.text = input
        }

        binding.btnImage1.setOnClickListener {
            binding.imageView2.setImageResource(R.drawable.smile_icon)
        }

        binding.btnImage2.setOnClickListener {
            binding.imageView2.setImageResource(resources.getIdentifier("bad_icon","drawable","com.tuncaycelikkanat.designwork"))
        }

        binding.switch1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked){
                Toast.makeText(requireContext(),"Switch is on", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(),"Switch is off", Toast.LENGTH_SHORT).show()
            }
        }

        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            control = isChecked
            if (control){
                val selectedButton = requireView().findViewById<Button>(binding.toggleButton.checkedButtonId)
                val buttonText = selectedButton.text.toString()
                Toast.makeText(requireContext(),"$buttonText", Toast.LENGTH_SHORT).show()
            }
        }

        // Expose Dropdown Menu
        val countries = ArrayList<String>()
        countries.add("Türkiye")
        countries.add("Japonya")
        countries.add("Portekiz")

        val arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,countries)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        //Progress Bar
        binding.btnStart.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
        }
        binding.btnStop.setOnClickListener {
            binding.progressBar.visibility = View.INVISIBLE
        }

        //Slider
        binding.textViewSlider.text = binding.slider.progress.toString()
        binding.slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(
                p0: SeekBar?,
                p1: Int,
                p2: Boolean
            ) {
                binding.textViewSlider.text = p1.toString()
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        //Date and Time Picker
        binding.btnSaat.setOnClickListener {
            val tp = MaterialTimePicker.Builder()
                .setTitleText("Saat Seç")
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            tp.show(parentFragmentManager,"Saat")

            tp.addOnPositiveButtonClickListener {
                binding.editTextSaat.setText("${tp.hour}.${tp.minute}")
            }
        }

        binding.btnTarih.setOnClickListener {
            val dp = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Tarih Seç")
                .build()

            dp.show(parentFragmentManager,"Tarih")

            dp.addOnPositiveButtonClickListener {
                val df = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
                val tarih = df.format(it)
                binding.editTextTarih.setText(tarih)
            }


        }

        binding.btnShow.setOnClickListener {
            Snackbar.make(it,"${binding.switch1.isChecked}", Snackbar.LENGTH_SHORT).show()
            val country = binding.autoCompleteTextView.text.toString()
            Log.e("Sonuc","Ülke $country")
            Log.e("Sonuc","Slider : ${binding.slider.progress}")
        }


        return binding.root
    }
}