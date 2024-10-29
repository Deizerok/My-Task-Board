package com.example.mytaskboard.taskboard.board.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import com.example.mytaskboard.R
import com.example.mytaskboard.databinding.FragmentTaskBoardBinding
import com.example.mytaskboard.taskboard.board.presentation.viewpageradapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
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
        val viewPager = binding.taskBoardViewPager
        val tabLayout = binding.taskBoardTabLayout
        val animation =
            AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_in)
        binding.mainLayout.startAnimation(animation)

        binding.mainMenuButton.setOnClickListener {
            viewModel.goToMenuScreen()
        }

        viewPager.adapter = ViewPagerAdapter(requireActivity())
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "To do"
                    tab.setIcon(R.drawable.bookmark_icon)
                }

                1 -> {
                    tab.text = "Done"
                    tab.setIcon(R.drawable.done_icon)
                }
            }
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}