package com.fp.market

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.fp.market.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    lateinit var binding: FragmentMenuBinding
    private val args: MenuFragmentArgs by navArgs()
    private lateinit var viewModel: SharedViewModel
    private lateinit var adapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        binding.welcomeUser.text = buildString {
            append("Welcome,")
            append("\n")
            append(args.username)
        }

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // Example of adding an item to the cart (assuming you have a list of items to display)
        viewModel.items.observe(viewLifecycleOwner) { items ->
            adapter = MenuAdapter(items, viewModel)
            binding.itemsRecyclerView.adapter = adapter
        }

        binding.btnCheckout.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToCheckOutFragment())
        }

        return binding.root
    }
}