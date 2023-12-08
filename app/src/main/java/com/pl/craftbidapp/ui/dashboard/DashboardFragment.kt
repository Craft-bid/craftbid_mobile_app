package com.pl.craftbidapp.ui.dashboard

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pl.craftbidapp.data.CRAFT_BID_JWT_TOKEN
import com.pl.craftbidapp.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val dashboardViewModel: DashboardViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val fetchHelloWorldBtn = binding.fetchHelloWorldBtn
        fetchHelloWorldBtn.setOnClickListener {
            dashboardViewModel.fetchHelloWorld()
        }
        dashboardViewModel.helloWorldMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        val tokenTextView = binding.textTokenTV
        tokenTextView.text = sharedPreferences.getString(CRAFT_BID_JWT_TOKEN, "no token")

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}