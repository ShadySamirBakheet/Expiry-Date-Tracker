package shady.samir.expirydatetracker.services

import android.app.NotificationManager

import android.app.NotificationChannel

import android.os.Build

import androidx.core.app.NotificationCompat

import android.app.PendingIntent

import android.content.Intent

import android.content.BroadcastReceiver
import android.content.Context
import android.util.Log
import shady.samir.expirydatetracker.R
import shady.samir.expirydatetracker.views.home.MainActivity


class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("Holy Bible Verssion", "Notify Start")
        showNotification(intent?.getStringExtra("name")!!, context, intent.getIntExtra("id",0))
    }

    private fun showNotification(message: String, context: Context, id: Int) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, "default")
            .setSmallIcon(R.drawable.icon)
            .setContentTitle(context.getString(R.string.app_name))
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setContentText(message)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent).setAutoCancel(false)
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(message)
            )
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId("myApp")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "myApp",
                "My App",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(id, builder.build())
    }
}
