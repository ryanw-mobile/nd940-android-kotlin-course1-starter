package com.udacity.shoestore.shoedetail

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        binding.cancelButton.setOnClickListener { onNavigateBack() }
        binding.saveButton.setOnClickListener {
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
            saveShoe(shoe)
        }

        // Additional feature: click on black space to close keyboard
        binding.shoedetailConstraintlayout.setOnClickListener {
            val inputMethodManager =
                requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    // Logout menu is not showing on this screen
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    private fun onNavigateBack() {
        // We could use navigate but calling navigateUp will always back to the previous screen
        findNavController().navigateUp()
    }

    private fun saveShoe(shoe: Shoe) {
        mainViewModel.insertShoe(shoe)
        onNavigateBack()
    }
}