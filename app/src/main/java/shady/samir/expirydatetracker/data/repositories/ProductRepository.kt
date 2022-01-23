package shady.samir.expirydatetracker.data.repositories

import shady.samir.expirydatetracker.data.daos.ProductDao
import shady.samir.expirydatetracker.data.models.Product
import shady.samir.expirydatetracker.utils.Utils
import java.util.*

class ProductRepository(private val productDao: ProductDao) {
    /**
     * get All Product Expiry
     * @param toDay
     */
    fun getProductsExpiry() = productDao.getAllExpiry(Date())

    /**
     * get All Product not Expiry
     * @param toDay
     */
    fun getProductsNotExpiry() = productDao.getAllNotExpiry(Date())

    /**
     * get special Product
     * @param code
     */
    fun getProduct(code:String) = productDao.getProduct(code)

    /**
     * and or update porduct
     * @param product
     */
    suspend fun insertProduct(product: Product) = productDao.insert(product)

}