package com.udacity.shoestore.shoedetail

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private var _binding: FragmentShoeDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShoeDetailViewModel
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoeDetailBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ShoeDetailViewModel::class.java)
        binding.shoeDetailViewModel = viewModel

        viewModel.eventShouldGoShoeList.observe(viewLifecycleOwner, { shouldGoShoeList ->
            if (shouldGoShoeList) {
                onNavigateToShoeList()
            }
        })

        binding.saveButton.setOnClickListener { saveShoe() }

        // Additional feature: click on black space to close keyboard
        binding.shoedetailConstraintlayout.setOnClickListener {
            val inputMethodManager =
                requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
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
        findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        viewModel.onGoShoeListComplete()
    }

    private fun saveShoe() {
        // Retrieve the values from the XML and pass it to the view model
        // as in this project we do not perform form validation,
        // if size is empty we put zero
        val shoeSizeString = binding.shoesizeEdittext.text.toString()

        val shoe = Shoe(
            binding.shoenameEdittext.text.toString(),
            if (shoeSizeString.isEmpty()) 0.0 else shoeSizeString.toDouble(),
            binding.companyEdittext.text.toString(),
            binding.descriptionEdittext.text.toString()
        )
        mainViewModel.insertShoe(shoe)
        viewModel.onGoShoeList()
    }
}