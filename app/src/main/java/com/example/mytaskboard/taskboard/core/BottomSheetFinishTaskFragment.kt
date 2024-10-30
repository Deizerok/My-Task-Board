package com.example.mytaskboard.taskboard.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mytaskboard.databinding.BottomSheetLayoutBinding
import com.example.mytaskboard.taskboard.todo_details.presentation.TaskDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetFinishTaskFragment : BottomSheetDialogFragment() {

    companion object {
        private const val KEY_ID = "key_id"

        fun newInstance(id: Int) = BottomSheetFinishTaskFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_ID, id)
            }
        }
    }

    private lateinit var binding: BottomSheetLayoutBinding
    private val viewModel: TaskDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetLayoutBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(KEY_ID)

        binding.finishTaskButton.setOnClickListener {
            viewModel.finishTask(id)
            dismiss()
        }

        binding.cancelTaskButton.setOnClickListener {
            dismiss()
        }
    }
}