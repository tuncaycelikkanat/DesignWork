package com.tuncaycelikkanat.designwork

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.tuncaycelikkanat.designwork.databinding.FragmentCartBinding
import androidx.navigation.findNavController

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        val bundle: CartFragmentArgs by navArgs()

        val inputName = bundle.name
        val inputAge = bundle.age
        val inputProductId = bundle.product.id
        val inputProductName = bundle.product.name

        binding.textView6.text = "$inputName $inputAge $inputProductId $inputProductName"

        //customize back button action
        val backPress = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Snackbar.make(binding.textView6,"Go Back?", Snackbar.LENGTH_SHORT)
                    .setAction("YES"){
                        isEnabled = false
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }
                    .show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,backPress)

        //BottomSheetDialog
        binding.button2.setOnClickListener {
            it.findNavController().navigate(R.id.action_cartFragment_to_bottomSheetFragment)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("Life Cycle","onCreate")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Life Cycle","onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Life Cycle","onResume")
    }
}