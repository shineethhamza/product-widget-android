package com.example.productwidgets.view.product_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productwidgets.Product
import com.example.productwidgets.R
import com.example.productwidgets.databinding.ProductItemLayoutBinding
import com.squareup.picasso.Picasso

class ProductListAdaptor (
    private val items: List<Product>,
    private val onItemClicked: (Product) -> Unit
): RecyclerView.Adapter<ProductListAdaptor.ProductViewHolder>() {

    class ProductViewHolder(private val binding: ProductItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            items: Product,
            onItemClicked: (Product) -> Unit
        ) {
            binding.productTitle.text = items.name
            ("Rs "+items.price).also { binding.productOffer.text = it }
            Picasso.get()
                .load(items.imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .into(binding.productImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemLayoutBinding.inflate(inflater)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position], onItemClicked)
    }

    override fun getItemCount(): Int = items.size
}