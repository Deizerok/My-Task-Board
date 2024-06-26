package com.example.mytaskboard.taskboard.create.presentation

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytaskboard.databinding.FragmentCreateTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


@AndroidEntryPoint
class CreateTaskFragment : Fragment() {

    private var _binding: FragmentCreateTaskBinding? = null
    private val binding: FragmentCreateTaskBinding get() = _binding!!
    private val viewModel: CreateTaskViewModel by viewModels()

    private val PERMISSION_REQUEST_CODE = 1001
    private val PICK_IMAGE_REQUEST = 1
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

        binding.addPictureImageViewLayout.setOnClickListener {
            binding.dotTextView.visibility = View.GONE
            binding.addPictureImageView.visibility = View.VISIBLE
            openGallery()
        }

        val titleTextView = binding.nameTaskInputEditText
        val descriptionTextView = binding.descriptionInputEditText
        val toastPicture = {
            if (picture == null)
            Toast.makeText(requireContext(), "Upload picture please", Toast.LENGTH_LONG).show()
        }

        binding.createTask0Button.setOnClickListener {
            if (titleTextView.text.toString() == "" && descriptionTextView.text.toString() == "") {

                titleTextView.error = "There can be no emptiness here"
                descriptionTextView.error = "There can be no emptiness here"
                toastPicture
            } else if (descriptionTextView.text.toString() == "") {

                descriptionTextView.error = "There can be no emptiness here"
                toastPicture
            } else {
                if (picture == null) {
                    Toast.makeText(requireContext(), "Upload picture please", Toast.LENGTH_LONG).show()
                } else {
                    viewModel.createTask(
                        title = titleTextView.text.toString(),
                        description = titleTextView.text.toString(),
                        time = 0,
                        picture = picture!!
                    )
                }

            }
        }

        binding.BackButton.setOnClickListener {
            viewModel.back()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri: Uri? = data.data
            imageUri?.let { saveImage(it) }
        }
    }

    private fun saveImage(imageUri: Uri) {
        try {
            val inputStream = requireContext().contentResolver.openInputStream(imageUri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val imageData = byteArrayOutputStream.toByteArray()

            binding.addPictureImageView.setImageBitmap(bitmap)

            picture = imageData
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Не вдалося зберегти зображення", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}