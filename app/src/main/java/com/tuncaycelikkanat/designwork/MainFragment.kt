package com.tuncaycelikkanat.designwork

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.tuncaycelikkanat.designwork.databinding.FragmentMainBinding
import androidx.navigation.findNavController

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            val product1 = Products(1,"TV")
            val gecis = MainFragmentDirections.actionMainFragmentToCartFragment(name = "Tuncay",age = 10,product = product1)

            it.findNavController().navigate(gecis)
        }

        binding.chip.setOnClickListener(::fake)
        binding.chip2.setOnClickListener(::fake)
        binding.chip3.setOnClickListener(::fake)
        binding.chip4.setOnClickListener(::fake)

        return binding.root
    }

    private fun fake(view: View){
        Toast.makeText(requireContext(),"It is fake!", Toast.LENGTH_SHORT).show()
    }
}