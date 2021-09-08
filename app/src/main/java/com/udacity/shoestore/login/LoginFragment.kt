package com.udacity.shoestore.login

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventShouldGoWelcomeScreen.observe(viewLifecycleOwner, Observer { shouldGo ->
            if (shouldGo) {
                onNavigateToWelcomeScreen()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    // Logout menu is not showing on this screen
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    private fun onNavigateToWelcomeScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        viewModel.onGoWelcomeScreenComplete()
    }

}