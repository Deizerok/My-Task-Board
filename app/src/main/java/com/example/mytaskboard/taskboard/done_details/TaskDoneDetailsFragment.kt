package com.example.mytaskboard.taskboard.done_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytaskboard.R
import com.example.mytaskboard.databinding.FragmentDoneTaskBinding
import com.example.mytaskboard.databinding.FragmentDoneTaskDetailsBinding
import com.example.mytaskboard.databinding.FragmentTaskDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TaskDoneDetailsFragment : Fragment() {

    companion object {
        const val KEY_ID = "key_id"

        fun newInstance(id: Int) = TaskDoneDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_ID, id)
            }
        }
    }


    private var _binding: FragmentDoneTaskDetailsBinding? = null
    private val binding: FragmentDoneTaskDetailsBinding
        get() = _binding!!
    private val viewModel: TaskDoneDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneTaskDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt(KEY_ID)
        val animationFadeIn = AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_in)
        val animationFadeOut = AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_out)
        binding.mainDetailsLayout.startAnimation(animationFadeIn)
        viewModel.init(id)


        binding.backToMainButton.setOnClickListener {
            binding.mainDetailsLayout.startAnimation(animationFadeOut)
            viewModel.back()
        }

        binding.finishTaskButton.setOnClickListener {
            BottomSheetRestoreTaskFragment.newInstance(id).show(requireActivity().supportFragmentManager, "addTime")

        }

        binding.deteleTaskButton.setOnClickListener {
            viewModel.deleteTask(id)
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