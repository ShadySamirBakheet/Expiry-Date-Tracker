package shady.samir.expirydatetracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import shady.samir.expirydatetracker.data.daos.ProductDao
import shady.samir.expirydatetracker.data.models.Product
import shady.samir.expirydatetracker.data.utils.TimeConverter

@Database(entities = arrayOf(Product::class), version = 1)

abstract class DataBase: RoomDatabase() {


    /**
     * object of  ProductDao
     */
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(
            context: Context
        ): DataBase {
            if (INSTANCE == null){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "db"
                ).fallbackToDestructiveMigration()
                    .addTypeConverter(TimeConverter())
                    .build()
                INSTANCE = instance
            }
            return INSTANCE!!
        }

    }

}
