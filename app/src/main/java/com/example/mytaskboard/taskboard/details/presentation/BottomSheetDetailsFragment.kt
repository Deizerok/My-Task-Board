package com.example.mytaskboard.taskboard.details.presentation

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mytaskboard.databinding.BottomSheetLayoutBinding
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
        binding.addTimeTextInputLayout.editText!!.filters = arrayOf(InputFilter.LengthFilter(4))
        val id = requireArguments().getInt(KEY_ID)
        var time: Int

        binding.addTimeBottomSheetButton.setOnClickListener {
            if (binding.addTimeTextInputLayout.editText!!.text.toString() == "") {
                binding.addTimeTextInputLayout.editText!!.error = "Enter correct numbers"
            } else {
                time = Integer.parseInt(binding.addTimeTextInputLayout.editText!!.text.toString())
                viewModel.addTime(time, id)
                dismiss()
            }

        }
    }
}