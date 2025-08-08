package com.tuncaycelikkanat.designwork

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.tuncaycelikkanat.designwork.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)

        binding.shareIcon.setOnClickListener {
            Snackbar.make(it,"Shared!", Snackbar.LENGTH_SHORT).show()
        }

        binding.copyIcon.setOnClickListener {
            Snackbar.make(it,"Copied!", Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }
}