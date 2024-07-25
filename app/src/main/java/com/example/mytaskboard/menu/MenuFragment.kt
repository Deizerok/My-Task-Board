package com.example.mytaskboard.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytaskboard.R
import com.example.mytaskboard.databinding.FragmentMainMenuBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MenuViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animationFadeIn = AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_in)
        val animationFadeOut = AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_out)
        binding.mainLayout.startAnimation(animationFadeIn)

        binding.BackButton.setOnClickListener {
            binding.mainLayout.startAnimation(animationFadeOut)
            viewModel.goToBackScreen()
        }

        binding.supportProjectButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Deizerok/My.Task.Board"))
            startActivity(browserIntent)
        }

        binding.githubSourceButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Deizerok/My.Task.Board"))
            startActivity(browserIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}