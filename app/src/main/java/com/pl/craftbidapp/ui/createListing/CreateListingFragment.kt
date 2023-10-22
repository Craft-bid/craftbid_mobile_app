package com.pl.craftbidapp.ui.createListing;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pl.craftbidapp.databinding.FragmentCreateListingBinding

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

        val titleEditText = binding.titleEt
        val descriptionEditText = binding.descriptionEt

        val createListingBtn = binding.createListingBtn
        createListingBtn.setOnClickListener {
            createListingViewModel.createListing(titleEditText.text, descriptionEditText.text)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}