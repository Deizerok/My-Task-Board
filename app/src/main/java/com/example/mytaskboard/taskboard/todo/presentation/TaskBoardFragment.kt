package com.example.mytaskboard.taskboard.todo.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import com.example.mytaskboard.R
import com.example.mytaskboard.databinding.FragmentTaskBoardBinding
import com.example.mytaskboard.taskboard.todo.presentation.adapter.TasksAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskBoardFragment : Fragment() {

    private var _binding: FragmentTaskBoardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskBoardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tasksAdapter = TasksAdapter(viewModel)
        binding.mainTaskRecyclerView.adapter = tasksAdapter
        val animation = AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_in)
        binding.mainLayout.startAnimation(animation)

        binding.floatingActionButton.setOnClickListener {
            viewModel.goToCreateTask()
        }

        binding.mainMenuButton.setOnClickListener {
            viewModel.goToMenuScreen()
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