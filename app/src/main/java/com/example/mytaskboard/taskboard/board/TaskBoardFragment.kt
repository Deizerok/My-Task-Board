package com.example.mytaskboard.taskboard.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.mytaskboard.R
import com.example.mytaskboard.databinding.FragmentTaskBoardBinding
import com.example.mytaskboard.main.Navigation
import com.example.mytaskboard.menu.MenuScreen
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TaskBoardFragment : Fragment() {

    private var _binding: FragmentTaskBoardBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var navigation: Navigation.Navigate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            navigation.updateUi(MenuScreen)
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