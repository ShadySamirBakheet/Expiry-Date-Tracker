package shady.samir.expirydatetracker.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import shady.samir.expirydatetracker.data.database.DataBase
import shady.samir.expirydatetracker.data.models.Product
import shady.samir.expirydatetracker.data.repositories.ProductRepository

class ProductViewModel(application: Application):AndroidViewModel(application)  {

    private var repository : ProductRepository

    init {
        val dataBase = DataBase.getDatabase(application.applicationContext)
        val productDao = dataBase.productDao()

        repository = ProductRepository(productDao)
    }

    fun getProductsExpiry() = repository.getProductsExpiry()

    fun getProductsNotExpiry() = repository.getProductsNotExpiry()

    fun getProuct(code:String) = repository.getProduct(code)

    fun insertProduct(product: Product) = viewModelScope.launch {
        repository.insertProduct(product)
    }
}