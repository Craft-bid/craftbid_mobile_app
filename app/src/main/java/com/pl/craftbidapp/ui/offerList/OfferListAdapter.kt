package com.pl.craftbidapp.ui.offerList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pl.craftbidapp.databinding.ListPartBinding
import com.squareup.picasso.Picasso


class OfferListAdapter : RecyclerView.Adapter<OfferListAdapter.OfferViewHolder>() {

    private var offerListElements: List<OfferListElement> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListPartBinding.inflate(inflater, parent, false)
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
            binding.offerTitle.text = offerListElement.title
            binding.offerCreator.text = offerListElement.avgBid.toString()
            binding.offerPrice.text = offerListElement.bids.toString()
            if(offerListElement.photo.isNotEmpty()){
                    Picasso.get().load(offerListElement.photo).into(binding.offerImage);
                }
        }
    }

}
