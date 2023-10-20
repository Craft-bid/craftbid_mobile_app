package com.example.craftbidkotlin.ui.offerList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.craftbidkotlin.databinding.ListPartBinding

class OfferListAdapter : RecyclerView.Adapter<OfferListAdapter.OfferViewHolder>() {

    private var offerListElements: List<OfferListElement> = emptyList() // Replace 'Offer' with your data model class

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListPartBinding.inflate(inflater, parent, false) // Create a layout for each item
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = offerListElements[position]
        holder.bind(offer)
    }

    override fun getItemCount(): Int {
        return offerListElements.size
    }

    fun submitList(newList: List<OfferListElement>) {
        offerListElements = newList
        notifyDataSetChanged()
    }

    inner class OfferViewHolder(private val binding: ListPartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(offerListElement: OfferListElement) {
            // Bind your data to the views in the layout
            binding.offerTitle.text = offerListElement.title
            binding.offerCreator.text = offerListElement.avgBid.toString()
            binding.offerPrice.text = offerListElement.bids.toString()
            // You can bind other views as well
        }
    }
}
