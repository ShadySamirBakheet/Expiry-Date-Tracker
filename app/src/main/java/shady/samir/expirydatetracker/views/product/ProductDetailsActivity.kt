package shady.samir.expirydatetracker.views.product

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import shady.samir.expirydatetracker.data.models.Product
import shady.samir.expirydatetracker.databinding.ActivityProductDetailsBinding
import shady.samir.expirydatetracker.utils.Utils
import shady.samir.expirydatetracker.viewmodels.ProductViewModel
import java.util.*

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var productViewModel: ProductViewModel

    var product: Product? = null

    var code = ""

    var date:Date= Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        code = intent.getStringExtra("code").toString()

        binding.code.text = code

        binding.date.text = Utils().format.format(date)

        initDate()

        initData()

        binding.btnSubmit.setOnClickListener {
            saveProdact()
        }

    }

    /**
     * save data from database
     */
    private fun saveProdact() {
        val name = binding.name.text.toString().trim()
        val category = binding.category.text.toString().trim()
        if (name.isNotEmpty() || category.isNotBlank()){
            if (product !=null){
                product?.name = name
                product?.category = category
                product?.expiryDate = date
            }else{
                product = Product(0,code,name,category, date,"Yes")
            }
            productViewModel.insertProduct(product!!)
            finish()
        }else{
            Toast.makeText(this, "Check Data Input", Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * get data from database if code is exist
     */
    private fun initData() {
        productViewModel.getProuct(code).observe(this, {
            if (it != null){
                product = it
                binding.name.setText(it.name)
                binding.category.setText(it.category)
                binding.date.setText( Utils().format.format(it.expiryDate))
            }
        })
    }

    /**
     * init to select date from Calendar
     */
    private fun initDate() {

        var cal = Calendar.getInstance()
        cal.time = date

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            
            date = cal.time


            binding.date.text =  Utils().format.format(cal.time)

        }

        binding.getDate.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}