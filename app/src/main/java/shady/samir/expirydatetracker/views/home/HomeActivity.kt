package shady.samir.expirydatetracker.views.home

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import shady.samir.expirydatetracker.adapters.ItemAdapter
import shady.samir.expirydatetracker.databinding.ActivityHomeBinding
import shady.samir.expirydatetracker.viewmodels.ProductViewModel
import shady.samir.expirydatetracker.views.expired.ExpiredItemsActivity
import shady.samir.expirydatetracker.views.product.BarcodeScannerActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var productViewModel: ProductViewModel

    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        binding.barCodeBtn.setOnClickListener {
            startActivity(Intent(this, BarcodeScannerActivity::class.java))
        }

        binding.expiryDateBtn.setOnClickListener {
            startActivity(Intent(this, ExpiredItemsActivity::class.java))
        }

        itemAdapter = ItemAdapter(this)

        binding.items.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter =itemAdapter
        }

        initData()

    }

    /**
     * get Data To View
     */
    private fun initData() {
        productViewModel.getProductsNotExpiry().observe(this,{
            if (it.isNotEmpty()){
                itemAdapter.addData(it)
                binding.empty.visibility = GONE
            }else{
                binding.empty.visibility = VISIBLE
                binding.empty.setOnClickListener {
                    startActivity(Intent(this, BarcodeScannerActivity::class.java))
                }
            }
        })
    }

}