package com.example.mytaskboard.taskboard.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mytaskboard.databinding.BottomSheetRestoreTaskBinding
import com.example.mytaskboard.taskboard.done_details.TaskDoneDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetRestoreTaskFragment : BottomSheetDialogFragment() {

    companion object {
        private const val KEY_ID = "key_id"

        fun newInstance(id: Int) = BottomSheetRestoreTaskFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_ID, id)
            }
        }
    }

    private lateinit var binding: BottomSheetRestoreTaskBinding
    private val viewModel: TaskDoneDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetRestoreTaskBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(KEY_ID)
        var time: Int

        binding.finishTaskButton.setOnClickListener {
            viewModel.restoreTask(id)
            dismiss()
        }

        binding.cancelTaskButton.setOnClickListener {
            dismiss()
        }
    }
}