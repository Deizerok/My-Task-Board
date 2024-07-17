package com.example.mytaskboard.taskboard.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytaskboard.R
import com.example.mytaskboard.databinding.FragmentTaskDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskDetailsFragment : Fragment() {

    companion object {
        const val KEY_ID = "key_id"

        fun newInstance(id: Int) = TaskDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_ID, id)
            }
        }
    }


    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding: FragmentTaskDetailsBinding
        get() = _binding!!
    private val viewModel: TaskDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(KEY_ID)
        val animation = AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_in)
        binding.mainDetailsLayout.startAnimation(animation)
        viewModel.init(id)


        binding.backToMainButton.setOnClickListener {
            viewModel.back()
        }

        binding.deleteTask0Button.setOnClickListener {
            viewModel.deleteTask(id)
        }

        binding.addTime0Button.setOnClickListener {
            BottomSheetDetailsFragment.newInstance(id).show(requireActivity().supportFragmentManager, "addTime")
        }

        viewModel.liveData().observe(viewLifecycleOwner) {
            it.show(binding)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}