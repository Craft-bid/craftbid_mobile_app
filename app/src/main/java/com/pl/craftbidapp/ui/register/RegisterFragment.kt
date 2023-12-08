package com.pl.craftbidapp.ui.register

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pl.craftbidapp.OnSwitchListener
import com.pl.craftbidapp.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.text
        registerViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val switchButton = binding.login
        switchButton.setOnClickListener {
            switchListener?.onSwitch()
        }

        binding.name.addTextChangedListener {
            registerViewModel._name.value = it.toString()
        }

        binding.username.addTextChangedListener {
            registerViewModel._email.value = it.toString()
        }

        binding.password.addTextChangedListener {
            registerViewModel._password.value = it.toString()
        }

        binding.register.setOnClickListener {
            registerViewModel.submitBid()
        }

        registerViewModel.OfferDetailsData.observe(viewLifecycleOwner) {
            // clear fields
            Toast.makeText(context, "You have been registered", Toast.LENGTH_LONG).show()
            switchListener?.onSwitch()
        }

        return root
    }

    private var switchListener: OnSwitchListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        switchListener = context as? OnSwitchListener
            ?: throw ClassCastException("$context must implement OnSwitchListener")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}