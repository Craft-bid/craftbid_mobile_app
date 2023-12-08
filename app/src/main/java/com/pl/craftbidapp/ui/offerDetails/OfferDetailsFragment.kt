package com.pl.craftbidapp.ui.offerDetails

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.pl.craftbidapp.databinding.FragmentOfferBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OfferDetailsFragment : Fragment() {

    private var _binding: FragmentOfferBinding? = null
    private val binding get() = _binding!!

    private val offerDetailsViewModel: OfferDetailsViewModel by viewModels()

    private val bidViewModel: BidViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfferBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val offerId = arguments?.getLong(ARG_OFFER_ID, -1)
        if (offerId != null && offerId != -1L) {
            offerDetailsViewModel.fetchOfferDetails(offerId)
        }

        offerDetailsViewModel.OfferDetailsData.observe(viewLifecycleOwner) {
            binding.title.text = it.title
            binding.description.text = it.description
            binding.averageBidPriceValue.text = it.avgBid.toString()

            val photoAdapter = PhotoAdapter(it.photos)
            binding.photoViewPager.adapter = photoAdapter
            it.tags.forEach { tag ->
                val textView = TextView(context).apply {
                    text = tag.name
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    // Możesz tutaj ustawić inne właściwości TextView, jak potrzebujesz
                }
                binding.paramsContainer.addView(textView)
            }

            TabLayoutMediator(binding.tabDots, binding.photoViewPager) { tab, position ->
                // Tutaj możesz dostosować wygląd każdej zakładki, jeśli potrzebujesz
            }.attach()
        }

        initBidSection(offerId!!)

        return root
    }

    private fun initBidSection(offerId: Long) {
        val bidPriceEditText = binding.yourBidPrice
        val bidDescriptionEditText = binding.yourBidDescription
        val bidDaysToDeliverEditText = binding.yourBidDaysToDeliver
        val submitButton = binding.submitBidButton

        bidViewModel.listingId.value = offerId

        bidPriceEditText.addTextChangedListener {
            bidViewModel.bidPrice.value = it.toString()
        }

        bidDescriptionEditText.addTextChangedListener {
            bidViewModel.bidDescription.value = it.toString()
        }

        bidDaysToDeliverEditText.addTextChangedListener {
            bidViewModel.bidDaysToDeliver.value = it.toString()
        }

        submitButton.setOnClickListener {
            bidViewModel.submitBid()
        }

        bidViewModel.OfferDetailsData.observe(viewLifecycleOwner) {
            // clear fields
            bidPriceEditText.text.clear()
            bidDescriptionEditText.text.clear()
            bidDaysToDeliverEditText.text.clear()
            Toast.makeText(context, "Your bid has been added", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val ARG_OFFER_ID = "offer_id"

        fun newInstance(offerId: Long) = OfferDetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(ARG_OFFER_ID, offerId)
            }
        }
    }

}