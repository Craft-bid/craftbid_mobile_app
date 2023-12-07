package com.pl.craftbidapp.ui.createListing;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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

        val titleEditText = binding.titleEt
        val descriptionEditText = binding.descriptionEt

        val createListingBtn = binding.createListingBtn
        createListingBtn.setOnClickListener {
            createListingViewModel.createListing(titleEditText.text.toString(), descriptionEditText.text.toString())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}