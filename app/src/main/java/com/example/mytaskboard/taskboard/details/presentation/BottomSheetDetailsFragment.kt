package com.example.mytaskboard.taskboard.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.mytaskboard.databinding.BottomSheetLayoutBinding
import com.example.mytaskboard.databinding.FragmentTaskDetailsBinding
import com.example.mytaskboard.taskboard.details.presentation.TaskDetailsFragment.Companion
import com.example.mytaskboard.taskboard.details.presentation.TaskDetailsFragment.Companion.KEY_ID
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetDetailsFragment : BottomSheetDialogFragment() {

    companion object {
        private const val KEY_ID = "key_id"

        fun newInstance(id: Int) = BottomSheetDetailsFragment().apply {
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
        val inputText = binding.addTimeInputEditText.text.toString()
        var time = 0

        if (inputText.isNotEmpty() && inputText.matches("\\d+".toRegex())) {
            time = inputText.toInt()
        } else {
            binding.addTimeInputEditText.error = "Введіть коректне число"
        }

        binding.addTimeBottomSheetButton.setOnClickListener {
            viewModel.addTime(time,id)
            dismiss()
        }
    }
}