package shady.samir.expirydatetracker.views.expired

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import shady.samir.expirydatetracker.adapters.ItemAdapter
import shady.samir.expirydatetracker.databinding.ActivityExpiredItemsBinding
import shady.samir.expirydatetracker.viewmodels.ProductViewModel
import shady.samir.expirydatetracker.views.product.BarcodeScannerActivity

class ExpiredItemsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExpiredItemsBinding

    private lateinit var productViewModel: ProductViewModel

    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpiredItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        binding.barCodeBtn.setOnClickListener {
            startActivity(Intent(this, BarcodeScannerActivity::class.java))
            finish()
        }

        binding.homeBtn.setOnClickListener {
            finish()
        }

        binding.items.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ItemAdapter(context)
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
        productViewModel.getProductsExpiry().observe(this,{
            if (it.isNotEmpty()){
                itemAdapter.addData(it)
                binding.empty.visibility = View.GONE
            }else{
                binding.empty.visibility = VISIBLE
            }
        })
    }

}