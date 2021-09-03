package com.arbonik.answerka.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arbonik.answerka.R
import com.arbonik.answerka.Repository
import com.arbonik.answerka.databinding.FragmentGameCreateBinding
import com.google.android.material.chip.Chip

class GameCreateFragment : Fragment() {
    lateinit var binding: FragmentGameCreateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameCreateBinding.inflate(inflater)
        binding.addNameButton.setOnClickListener {
            addPlayer()
        }
        binding.doneButton.setOnClickListener {
            findNavController().navigate(R.id.action_gameCreateFragment_to_gameFragment)
        }
        showNames()
        return binding.root
    }

    fun addPlayer() {
        var text = binding.nameEditText.text.toString()
        if (text.isEmpty())
            binding.nameEditText.error = "Введите имя"
        else {
            Repository.names.add(text)
            binding.nameEditText.text.clear()
            addName(text)
        }
    }

    private fun addName(name: String) {
        binding.playersChips.addView(
            Chip(requireContext()).apply {
                text = name
                isCloseIconVisible = true
                setOnCloseIconClickListener {
                    Repository.names.remove(name)
                    visibility = View.GONE
                }
                chipBackgroundColor =
                    ColorStateList.valueOf(resources.getColor(R.color.chip_color))
            }
        )
    }

    private fun showNames() {
        Repository.names.forEach { name ->
            addName(name)
        }
//        binding.playersNames.text = Repository.names.joinToString()
    }
}