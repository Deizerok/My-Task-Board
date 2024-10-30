package com.example.mytaskboard.taskboard.create.presentation

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytaskboard.R
import com.example.mytaskboard.databinding.FragmentCreateTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream


@AndroidEntryPoint
class CreateTaskFragment : Fragment() {

    private var _binding: FragmentCreateTaskBinding? = null
    private val binding: FragmentCreateTaskBinding get() = _binding!!
    private val viewModel: CreateTaskViewModel by viewModels()

    private var picture: ByteArray? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animationFadeIn = AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_in)
        val animationFadeOut = AnimationUtils.loadAnimation(requireActivity().applicationContext, R.anim.fade_out)
        binding.mainLayout.startAnimation(animationFadeIn)

        var iconChoose = 0
        whatIconChoose(iconChoose)
        val titleTextView = binding.nameTaskInputEditText
        val descriptionTextView = binding.descriptionInputEditText

        binding.iconOne.setOnClickListener {
            whatIconChoose(iconChoose)
            iconChoose = 1
            binding.whatIconChooseTextView.text = iconChoose.toString()
            picture = convertSVGToByteArray(R.drawable.task_1_icon)
            binding.iconOne.setBackgroundResource(R.drawable.selected_icon_for_task)
        }

        binding.iconTwo.setOnClickListener {
            whatIconChoose(iconChoose)
            iconChoose = 2
            binding.whatIconChooseTextView.text = iconChoose.toString()
            picture = convertSVGToByteArray(R.drawable.task_2_icon)
            binding.iconTwo.setBackgroundResource(R.drawable.selected_icon_for_task)
        }

        binding.iconThree.setOnClickListener {
            whatIconChoose(iconChoose)
            iconChoose = 3
            binding.whatIconChooseTextView.text = iconChoose.toString()
            picture = convertSVGToByteArray(R.drawable.task_3_icon)
            binding.iconThree.setBackgroundResource(R.drawable.selected_icon_for_task)
        }

        binding.iconFour.setOnClickListener {
            whatIconChoose(iconChoose)
            iconChoose = 4
            binding.whatIconChooseTextView.text = iconChoose.toString()
            picture = convertSVGToByteArray(R.drawable.task_4_icon)
            binding.iconFour.setBackgroundResource(R.drawable.selected_icon_for_task)
        }

        binding.iconFive.setOnClickListener {
            whatIconChoose(iconChoose)
            iconChoose = 5
            binding.whatIconChooseTextView.text = iconChoose.toString()
            picture = convertSVGToByteArray(R.drawable.task_5_icon)
            binding.iconFive.setBackgroundResource(R.drawable.selected_icon_for_task)
        }

        binding.createTask0Button.setOnClickListener {
            if (titleTextView.text.toString() == "" && descriptionTextView.text.toString() == "") {
                titleTextView.error = "There can be no emptiness here"
                descriptionTextView.error = "There can be no emptiness here"
            } else {
                if (picture == null) {
                    Toast.makeText(requireContext(), "Choose icon", Toast.LENGTH_LONG)
                        .show()
                } else {
                    viewModel.createTask(
                        title = titleTextView.text.toString(),
                        description = descriptionTextView.text.toString(),
                        picture = picture!!
                    )
                }
            }
        }

        binding.backCreateTaskButton.setOnClickListener {
            binding.mainLayout.startAnimation(animationFadeOut)
            viewModel.back()
        }
    }

    private fun whatIconChoose(idIcon: Int) {
        when (idIcon) {
            1 -> {
                binding.iconOne.setBackgroundResource(R.drawable.no_selected_icon_for_task)
            }
            2 -> {
                binding.iconTwo.setBackgroundResource(R.drawable.no_selected_icon_for_task)
            }
            3 -> {
                binding.iconThree.setBackgroundResource(R.drawable.no_selected_icon_for_task)
            }
            4 -> {
                binding.iconFour.setBackgroundResource(R.drawable.no_selected_icon_for_task)
            }
            5 -> {
                binding.iconFive.setBackgroundResource(R.drawable.no_selected_icon_for_task)
            }
            else -> {
                binding.iconOne.setBackgroundResource(R.drawable.no_selected_icon_for_task)
                binding.iconTwo.setBackgroundResource(R.drawable.no_selected_icon_for_task)
                binding.iconThree.setBackgroundResource(R.drawable.no_selected_icon_for_task)
                binding.iconFour.setBackgroundResource(R.drawable.no_selected_icon_for_task)
                binding.iconFive.setBackgroundResource(R.drawable.no_selected_icon_for_task)
            }
        }
    }

    private fun convertSVGToByteArray(resId: Int): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val drawable: Drawable? = ContextCompat.getDrawable(requireContext(), resId)
        drawable?.let {
            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap.let {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                bitmap.recycle()
            }
        }
        return byteArrayOutputStream.toByteArray()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}