package shady.samir.expirydatetracker.views.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import shady.samir.expirydatetracker.R
import shady.samir.expirydatetracker.databinding.ActivityMainBinding
import shady.samir.expirydatetracker.services.Manage
import java.util.*
import android.app.AlarmManager

import android.app.PendingIntent
import shady.samir.expirydatetracker.data.models.Product
import shady.samir.expirydatetracker.services.NotificationReceiver


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAnimation()
        Handler().postDelayed({
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        },2500)

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.MINUTE,1)

       /// Manage.startNotify(this, Product(0,"1","test","f00", calendar.time,"yes"))

    }

    /**
     * set Animation
     */
    private fun setAnimation() {
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.in_right)
        animation.duration = 2000
        binding.startImg.startAnimation(animation)
    }

}