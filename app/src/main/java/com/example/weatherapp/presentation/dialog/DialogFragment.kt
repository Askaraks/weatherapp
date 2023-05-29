package com.example.weatherapp.presentation.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DialogFragment(private val onClick: Result) : BottomSheetDialogFragment() {

    private val binding: FragmentDialogBinding by lazy {
        FragmentDialogBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSearch.setOnClickListener {
            val name = binding.edName.text.toString()
            onClick.click(name)
            onDestroyView()
        }
    }


    interface Result {
        fun click(name: String)
    }

}