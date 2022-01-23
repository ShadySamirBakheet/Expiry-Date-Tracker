package shady.samir.expirydatetracker.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import shady.samir.expirydatetracker.data.models.Product
import shady.samir.expirydatetracker.data.utils.TimeConverter
import java.util.*

@Dao
@TypeConverters(TimeConverter::class)
interface ProductDao {

    /**
     * get All Product Expiry
     * @param toDay
     */
    @Query("SELECT * FROM product WHERE expiryDate <= :toDay Order by expiryDate DESC")
    fun getAllExpiry(toDay: Date): LiveData<List<Product>>

    /**
     * get All Product not Expiry
     * @param toDay
     */
    @Query("SELECT * FROM product WHERE expiryDate > :toDay Order by expiryDate ASC")
    fun getAllNotExpiry(toDay: Date): LiveData<List<Product>>

    /**
     * get special Product
     * @param code
     */
    @Query("SELECT * FROM product WHERE code = :code")
    fun getProduct(code: String): LiveData<Product>

    /**
     * and or update porduct
     * @param product
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product):Long

}