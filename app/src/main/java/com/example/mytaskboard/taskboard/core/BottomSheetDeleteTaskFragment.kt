package com.example.mytaskboard.taskboard.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mytaskboard.databinding.BottomSheetDeleteTaskBinding
import com.example.mytaskboard.taskboard.todo_details.presentation.TaskTodoDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetDeleteTaskFragment : BottomSheetDialogFragment() {

    companion object {
        private const val KEY_ID = "key_id"

        fun newInstance(id: Int) = BottomSheetDeleteTaskFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_ID, id)
            }
        }
    }

    private lateinit var binding: BottomSheetDeleteTaskBinding
    private val viewModel: TaskTodoDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDeleteTaskBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(KEY_ID)

        binding.finishTaskButton.setOnClickListener {
            viewModel.deleteTask(id)
            dismiss()
        }

        binding.cancelTaskButton.setOnClickListener {
            dismiss()
        }
    }
}