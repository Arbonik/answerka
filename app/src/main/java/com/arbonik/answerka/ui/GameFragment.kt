package com.arbonik.answerka.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arbonik.answerka.R
import com.arbonik.answerka.Repository
import com.arbonik.answerka.databinding.FragmentGameBinding
import com.google.android.material.chip.Chip

class GameFragment : Fragment() {
    lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater)
        binding.answerTextView.text = showStatement()
        createChips()
        binding.okButton.setOnClickListener {
            if (Repository.names.isNotEmpty())
                findNavController().navigate(R.id.action_gameFragment_to_tasksFragment)
        }
        return binding.root
    }

    private fun createChips() {
        Repository.names.forEach {
            binding.chipGroup.addView(
                Chip(requireContext()).apply {
                    text = it
                    isCheckable = true
                    setOnCheckedChangeListener { compoundButton, b ->
                        if (b)
                            Repository.checkedNames.add(it)
                        else
                            Repository.checkedNames.remove(it)
                    }
                    chipBackgroundColor =
                        ColorStateList.valueOf(resources.getColor(R.color.chip_color))
                }
            )
        }
    }

    fun showStatement(): String {
        return if (Repository.statements.isNotEmpty()) {
            val currentStatement = Repository.statements.random()
            if (Repository.names.isNotEmpty()) {
                var statement = currentStatement.replace("name", Repository.names.random())
                Repository.statements.remove(currentStatement)
                statement
            }
            else "Вы не ввели ни одного игрока :С"
        } else
            "Вы прошли игру!"
    }
}