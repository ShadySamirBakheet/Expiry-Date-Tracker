package shady.samir.expirydatetracker.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import shady.samir.expirydatetracker.data.utils.TimeConverter
import java.util.*

@Entity
@TypeConverters(TimeConverter::class)
data class Product (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id :Int,
    @ColumnInfo(name = "code") var code :String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "category") var category: String,
    @ColumnInfo(name = "expiryDate") var expiryDate: Date,
    @ColumnInfo(name = "scanned") var scanned: String?,
)