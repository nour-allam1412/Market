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
import androidx.recyclerview.widget.LinearLayoutManager
import com.fp.market.databinding.FragmentCheckOutBinding

class CheckOutFragment : Fragment() {
    lateinit var binding: FragmentCheckOutBinding
    lateinit var viewModel: SharedViewModel
    lateinit var adapter: CheckOutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_out, container, false)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        binding.selectedItemsRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.selectedItems.observe(viewLifecycleOwner) { selectedItems ->
            adapter = CheckOutAdapter(selectedItems)
            binding.selectedItemsRecycler.adapter = adapter
        }

        // Observe the total price and update the TextView
        viewModel.totalPrice.observe(viewLifecycleOwner) { totalPrice ->
            binding.totalPriceValue.text = buildString {
                append("Total: $")
                append(totalPrice)
            }
        }

        binding.btnConfirm.setOnClickListener {
            findNavController().navigate(CheckOutFragmentDirections.actionCheckOutFragmentToOperationSuccesfulFragment())
        }
        
        return binding.root
    }
}