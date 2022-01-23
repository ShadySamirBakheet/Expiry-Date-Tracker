package shady.samir.expirydatetracker.services

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import shady.samir.expirydatetracker.R
import shady.samir.expirydatetracker.data.models.Product
import java.util.*

class Manage {
    companion object{


        /**
         * set Notification
         * @param context
         * @param product
         */
         fun startNotify(context: Context,product: Product) {
            val calendar = Calendar.getInstance()
            calendar.time = product.expiryDate

            val intent = Intent(context, NotificationReceiver::class.java)
                .putExtra("name",product.name + ". " +context.getString(R.string.ExpiresToday)).putExtra("id",product.id)

            val pendingIntent =
                PendingIntent.getBroadcast(context, product.id, intent, PendingIntent.FLAG_CANCEL_CURRENT)
            val alarmManager = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )

        }
    }
}