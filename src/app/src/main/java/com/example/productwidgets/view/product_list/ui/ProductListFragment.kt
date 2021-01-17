package com.example.productwidgets.view.product_list.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.productwidgets.R
import com.example.productwidgets.databinding.ProductListFragmentBinding
import com.example.productwidgets.helpers.ProductListDecoration
import com.example.productwidgets.view.product_list.ProductListAdaptor

class ProductListFragment : Fragment() {

    companion object {
        fun newInstance() = ProductListFragment()
    }

    private lateinit var viewModel: ProductListViewModel
    private lateinit var binding: ProductListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductListFragmentBinding.inflate(inflater, container, false)

        val layoutManager = GridLayoutManager(activity, 2)
        binding.productCollection.layoutManager = layoutManager
        binding.productCollection.addItemDecoration(ProductListDecoration(1, 2))

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)

        viewModel.products.observe(viewLifecycleOwner, Observer {
            binding.productCollection.adapter = ProductListAdaptor(it) { product ->
                Log.d("Product",product.toString())
            }
        })
    }

}