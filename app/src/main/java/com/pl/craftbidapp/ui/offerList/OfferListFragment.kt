package com.pl.craftbidapp.ui.offerList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pl.craftbidapp.databinding.FragmentOfferListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OfferListFragment : Fragment() {

    private var _binding: FragmentOfferListBinding? = null

    private lateinit var offerListViewModel: OfferListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: OfferListAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        offerListViewModel =
            ViewModelProvider(this)[OfferListViewModel::class.java]

        _binding = FragmentOfferListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                offerListViewModel.search(query)
                return true
            }override fun onQueryTextChange(newText: String): Boolean {
                if(newText.length==0) {
                    offerListViewModel.search(null)
                }
                return true
            }

        })

        recyclerView = binding.list
        val navController = findNavController()
        adapter = OfferListAdapter(navController)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        offerListViewModel.offerListElementList.observe(viewLifecycleOwner) { offers ->
            adapter.submitList(offers)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}