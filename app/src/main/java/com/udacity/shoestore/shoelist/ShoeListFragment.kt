package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShoeListViewModel
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        binding.shoeListViewModel = viewModel
        binding.lifecycleOwner = this

        // The fab button will call the ViewModel method and trigger this directly
        viewModel.eventShouldGoShoeDetailScreen.observe(viewLifecycleOwner,
            { shouldGoDetailScreen ->
                if (shouldGoDetailScreen) {
                    onNavigateToShoeDetail()
                }
            })

        viewModel.eventShouldGoLoginScreen.observe(viewLifecycleOwner,
            { shouldGoLoginScreen ->
                if (shouldGoLoginScreen) {
                    onNavigateToLogin()
                }
            })

        mainViewModel.shoeList.observe(viewLifecycleOwner, {
            refreshList(it)
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    // Since only on this screen we would show the menu, the event is handled here
    // This can make use of the ViewModel and navigation
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            viewModel.logout()
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onNavigateToShoeDetail() {
        findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        viewModel.onGoShoeDetailScreenComplete()
    }

    private fun onNavigateToLogin() {
        // Clear shoe list first
        mainViewModel.clear()
        findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
        viewModel.onGoLoginScreenComplete()
    }

    /**
     * We do not have a recyclerview, so using a simpler way - clear all then regenerate all
     */
    private fun refreshList(shoeList: MutableList<Shoe>) {
        binding.shoelistLinearlayout.removeAllViews();

        for (shoe in shoeList) {
            val view = LayoutInflater.from(context).inflate(R.layout.listitem_shoe, null, false)
            view.findViewById<TextView>(R.id.listitem_shoename).text =
                getString(R.string.listitem_shoe_name, shoe.name)
            view.findViewById<TextView>(R.id.listitem_company).text =
                getString(R.string.listitem_company, shoe.company)
            view.findViewById<TextView>(R.id.listitem_shoesize).text =
                getString(R.string.listitem_shoe_size, shoe.size)
            view.findViewById<TextView>(R.id.listitem_description).text =
                getString(R.string.listitem_description, shoe.description)

            binding.shoelistLinearlayout.addView(view)
        }
    }
}