package com.pl.craftbidapp.ui.myOfferList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pl.craftbidapp.databinding.FragmentMyofferListBinding
import com.pl.craftbidapp.ui.offerList.MyOfferViewModel
import com.pl.craftbidapp.ui.offerList.OfferListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyOfferListFragment : Fragment() {

    private var _binding: FragmentMyofferListBinding? = null

    private lateinit var  myOfferViewModel: MyOfferViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OfferListAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myOfferViewModel =
            ViewModelProvider(this)[MyOfferViewModel::class.java]

        _binding = FragmentMyofferListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.list
        adapter = OfferListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        myOfferViewModel.favListElementList.observe(viewLifecycleOwner) { favs ->
            adapter.submitList(favs)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}