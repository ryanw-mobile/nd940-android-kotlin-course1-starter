package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        viewModel = LoginViewModel()
        viewModel.eventShouldGoWelcomeScreen.observe(viewLifecycleOwner, Observer { shouldGo ->
            if (shouldGo) {
                onNavigateToWelcomeScreen()
            }
        })

        _binding!!.loginViewModel = viewModel

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    private fun onNavigateToWelcomeScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        viewModel.onGoWelcomeScreenComplete()
    }

}