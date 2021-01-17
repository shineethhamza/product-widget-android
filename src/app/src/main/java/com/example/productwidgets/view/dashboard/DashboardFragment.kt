package com.example.productwidgets.view.dashboard

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.productwidgets.databinding.DashboardFragmentBinding
import com.example.productwidgets.helpers.ProductListDecoration
import com.example.productwidgets.view.product_list.ProductListActivity
import com.squareup.picasso.Picasso

class DashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: DashboardFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DashboardFragmentBinding.inflate(inflater, container, false)

        val layoutManager = GridLayoutManager(activity, 2)
        binding.productCollection.layoutManager = layoutManager
        binding.productCollection.addItemDecoration(ProductListDecoration(1, 2))

        binding.buttonViewAllProduct.setOnClickListener {
            context?.let { ctx -> ProductListActivity.show(ctx) }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.carouselList.observe(viewLifecycleOwner, Observer {
            binding.carousel.addData(it)
        })

        viewModel.bannerList.observe(viewLifecycleOwner, Observer {
            binding.bannerLayout.removeAllViews()
            it.forEach { banner ->
                val imageView = ImageView(context)

                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(5, 5, 5, 5)
                imageView.layoutParams = layoutParams
                imageView.scaleType = ImageView.ScaleType.FIT_START
                imageView.adjustViewBounds = true
                imageView.setBackgroundColor(Color.parseColor("#ffffff"))
                binding.bannerLayout.addView(imageView)
                Picasso.get()
                    .load(banner.imageUrl)
                    .into(imageView)
            }
        })

        viewModel.offerProducts.observe(viewLifecycleOwner, Observer {
            binding.productCollection.adapter = OfferedProductCategoryAdaptor(it) { product ->
                Log.d("Product",product.toString())
            }
        })
    }

}