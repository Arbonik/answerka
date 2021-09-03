package com.arbonik.answerka.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arbonik.answerka.R
import com.arbonik.answerka.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    lateinit var binding: FragmentStartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater)
        binding.newGameButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_gameCreateFragment)
        }
        return binding.root
    }
}