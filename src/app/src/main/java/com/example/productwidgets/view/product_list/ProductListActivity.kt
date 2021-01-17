package com.example.productwidgets.view.product_list

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.productwidgets.R
import com.example.productwidgets.databinding.ProductListActivityBinding
import com.example.productwidgets.view.product_list.ui.ProductListFragment

class ProductListActivity : AppCompatActivity() {

    companion object {
        fun show(context: Context) {
            context.startActivity(
                Intent(
                    context,
                    ProductListActivity::class.java
                )
            )
        }
    }

    private lateinit var binding: ProductListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductListActivityBinding.inflate(layoutInflater)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductListFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}