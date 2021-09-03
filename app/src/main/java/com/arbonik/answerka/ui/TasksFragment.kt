package com.arbonik.answerka.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arbonik.answerka.Repository
import com.arbonik.answerka.data.OurTask
import com.arbonik.answerka.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    lateinit var binding: FragmentTasksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksBinding.inflate(inflater)
        binding.players.text = Repository.checkedNames.joinToString()
        val task = Repository.getTask()
        if (task.isActive) {
            val t = OurTask(task.task!!.replace("name", Repository.names.random()))
            binding.tasks.text = t.task
        } else
            findNavController().navigateUp()

        binding.nextButton.setOnClickListener {
            Repository.checkedNames.clear()
            findNavController().navigateUp()
        }
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        Repository.checkedNames.clear()
    }
}