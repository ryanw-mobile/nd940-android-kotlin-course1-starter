package com.udacity.shoestore.instruction

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {

    private var _binding: FragmentInstructionBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: InstructionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInstructionBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(InstructionViewModel::class.java)
        binding.instructionViewModel = viewModel

        viewModel.eventShouldGoShoeList.observe(viewLifecycleOwner, { shouldGoShoeList ->
            if (shouldGoShoeList) {
                onNavigateToShoeList()
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

    private fun onNavigateToShoeList() {
        findNavController().navigate(R.id.action_instructionFragment_to_shoeListFragment)
        viewModel.onGoShoeListComplete()
    }
}