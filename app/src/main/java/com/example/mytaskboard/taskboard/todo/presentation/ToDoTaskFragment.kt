package com.example.mytaskboard.taskboard.todo.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytaskboard.R
import com.example.mytaskboard.databinding.FragmentToDoTaskBinding
import com.example.mytaskboard.taskboard.todo.presentation.adapter.TodoTasksAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ToDoTaskFragment : Fragment() {

    private var _binding: FragmentToDoTaskBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToDoTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tasksAdapter = TodoTasksAdapter(viewModel)
        binding.mainTaskRecyclerView.adapter = tasksAdapter
        val animation = AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_in)
        binding.mainLayout.startAnimation(animation)

        binding.floatingActionButton.setOnClickListener {
            viewModel.goToCreateTask()
        }


        viewModel.liveData().observe(viewLifecycleOwner) {
            it.show(adapter = tasksAdapter)
        }

        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}