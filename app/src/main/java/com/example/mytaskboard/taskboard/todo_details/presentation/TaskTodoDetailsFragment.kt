package com.example.mytaskboard.taskboard.todo_details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytaskboard.R
import com.example.mytaskboard.databinding.FragmentTaskDetailsBinding
import com.example.mytaskboard.taskboard.todo_details.presentation.adapter.TimeLogAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskTodoDetailsFragment : Fragment() {

    companion object {
        const val KEY_ID = "key_id"

        fun newInstance(id: Int) = TaskTodoDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_ID, id)
            }
        }
    }

    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding: FragmentTaskDetailsBinding
        get() = _binding!!
    private val viewModel: TaskTodoDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(KEY_ID)
        val animationFadeIn =
            AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_in)
        val animationFadeOut =
            AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_out)
        binding.mainDetailsLayout.startAnimation(animationFadeIn)

        val timeLogAdapter = TimeLogAdapter()
        binding.timeLogRecyclerView.adapter = timeLogAdapter

        binding.backToMainButton.setOnClickListener {
            binding.mainDetailsLayout.startAnimation(animationFadeOut)
            viewModel.back()
        }

        binding.actionButtonTextView.setOnClickListener {
            viewModel.stopwatchActionClick(id)
        }

        binding.finishTaskButton.setOnClickListener {
            viewModel.openBottomSheetFinish(id)
        }

        binding.deteleTaskButton.setOnClickListener {
            viewModel.openBottomSheetDelete(id)
        }

        viewModel.taskLiveData().observe(viewLifecycleOwner) { taskDetailsUiModel ->
            taskDetailsUiModel.show(binding)
        }

        viewModel.stopwatchLiveData().observe(viewLifecycleOwner) { stopwatchUiState ->
            stopwatchUiState.show(binding)
        }

        viewModel.messageLiveData().observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        viewModel.init(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}