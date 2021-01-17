package com.example.productwidgets.view.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productwidgets.Product
import com.example.productwidgets.R
import com.example.productwidgets.databinding.ProductItemLayoutBinding
import com.squareup.picasso.Picasso

class OfferedProductCategoryAdaptor(
    private val items: List<Product>,
    private val onItemClicked: (Product) -> Unit
) :
    RecyclerView.Adapter<OfferedProductCategoryAdaptor.ProductCategoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OfferedProductCategoryAdaptor.ProductCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemLayoutBinding.inflate(inflater)
        return ProductCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: OfferedProductCategoryAdaptor.ProductCategoryViewHolder,
        position: Int
    ) {
        holder.bind(items[position], onItemClicked)
    }

    override fun getItemCount(): Int = items.size

    class ProductCategoryViewHolder(private val binding: ProductItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            items: Product,
            onItemClicked: (Product) -> Unit
        ) {
            binding.productTitle.text = items.category
            ("Up to "+items.offer+"% off").also { binding.productOffer.text = it }
            Picasso.get()
                .load(items.imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(binding.productImage)

        }
    }
}