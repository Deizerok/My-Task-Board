package com.example.mytaskboard.taskboard.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytaskboard.R
import com.example.mytaskboard.databinding.FragmentTaskBoardBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject


@AndroidEntryPoint
class TaskBoardFragment : Fragment() {


    @Inject
    lateinit var languageStorage: LanguageStorage

    private var _binding: FragmentTaskBoardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskBoardViewModel by viewModels()

    private val languages = listOf(
        Language("UA", local = "uk", R.drawable.circle_ua_flag),
        Language("US", local = "en", R.drawable.circle_us_flag)
    )

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
            viewModel.goToMenu()
        }

        binding.languageImageButton.setOnClickListener {
            val inflater = LayoutInflater.from(requireContext())
            val dropdownView = inflater.inflate(R.layout.popup_language_dropdown, null)
            val popupWindow = PopupWindow(
                dropdownView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )

            val dropdownContainer =
                dropdownView.findViewById<LinearLayout>(R.id.popup_language_dropdown_container)
            languages.forEach { language ->

                val itemView =
                    inflater.inflate(R.layout.dropdown_language_item, dropdownContainer, false)
                val flagImageView = itemView.findViewById<ImageView>(R.id.flagImageView)
                val languageTextView = itemView.findViewById<TextView>(R.id.languageTextView)
                val checkImageView = itemView.findViewById<ImageView>(R.id.checkImageView)

                languageTextView.text = language.name
                flagImageView.setImageResource(language.iconResId)

                // Show check if this is the selected language
                if (viewModel.currentLanguage() == language) {
                    checkImageView.visibility = View.VISIBLE
                }

                itemView.setOnClickListener {
                    viewModel.changeLanguage(language)  // Replace with your actual flag resource
                    setLocale(Locale(language.local))
                    popupWindow.dismiss()
                }

                dropdownContainer.addView(itemView)
            }

            popupWindow.showAsDropDown(binding.languageImageButton)
        }

        viewPager.adapter = ViewPagerAdapter(requireActivity())
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.to_do)
                    tab.setIcon(R.drawable.bookmark_icon)
                }

                1 -> {
                    tab.text = getString(R.string.done)
                    tab.setIcon(R.drawable.done_icon)
                }
            }
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLocale(locale: Locale) {
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        requireActivity().recreate()
    }
}