package com.udacity.shoestore.welcome

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        binding.welcomeViewModel = viewModel

        viewModel.eventShouldGoWelcomeScreen.observe(
            viewLifecycleOwner,
            { shouldGoWelcomeScreen ->
                if (shouldGoWelcomeScreen) {
                    onNavigateToInstructionScreen()
                }
            })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Logout menu is not showing on this screen
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    private fun onNavigateToInstructionScreen() {
        findNavController().navigate(R.id.action_welcomeFragment_to_instructionFragment)
        viewModel.onGoInstructionScreenComplete()
    }
}