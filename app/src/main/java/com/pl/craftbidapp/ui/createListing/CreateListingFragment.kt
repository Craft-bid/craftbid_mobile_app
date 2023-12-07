package com.pl.craftbidapp.ui.createListing;

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pl.craftbidapp.databinding.FragmentCreateListingBinding
import com.pl.craftbidapp.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateListingFragment : Fragment() {
    private var _binding: FragmentCreateListingBinding? = null
    private val binding get() = _binding!!

    private val createListingViewModel: CreateListingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateListingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val imageView = binding.photoImageView
        imageView.setOnClickListener {
            addPhotoToImageView()
        }
        val imageTextView = binding.photoImageView

        val titleEditText = binding.titleEt
        val descriptionEditText = binding.descriptionEt

        val createListingBtn = binding.createListingBtn
        createListingBtn.setOnClickListener {
            createListingViewModel.createListing(
                titleEditText.text.toString(),
                descriptionEditText.text.toString()
            )
            createListingViewModel.addPhotoToListing()
        }

        return root
    }

    private fun addPhotoToImageView() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            val selectedImageUri = data?.data
            binding.photoImageView.setImageURI(selectedImageUri)
            binding.photoTextView.isInvisible = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}
