package shady.samir.expirydatetracker.views.product

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import shady.samir.expirydatetracker.R
import shady.samir.expirydatetracker.databinding.ActivityBarcodeScannerBinding
import shady.samir.expirydatetracker.views.expired.ExpiredItemsActivity

class BarcodeScannerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBarcodeScannerBinding

    private lateinit var svBarcode: SurfaceView
    private lateinit var detector: BarcodeDetector
    private lateinit var cameraSource: CameraSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarcodeScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        svBarcode = binding.svBarcode


        binding.expiryDateBtn.setOnClickListener {
            startActivity(Intent(this, ExpiredItemsActivity::class.java))
            finish()
        }

        binding.homeBtn.setOnClickListener {
            finish()
        }

        initFun()
    }

    private fun initFun() {

        val builder = AlertDialog.Builder(this)
        val taskHandler = Handler()
        val runnable = object:Runnable{
            override fun run() {
                cameraSource.stop()
                val alert = builder.create()
                alert.show()
                taskHandler.removeCallbacksAndMessages(null)
            }
        }

        detector = BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build()
        detector.setProcessor(object : Detector.Processor<Barcode> {

            override fun release() {}

            @SuppressLint("MissingPermission")
            override fun receiveDetections(barCode: Detector.Detections<Barcode>?) {
                val barcodes = barCode?.detectedItems
                if (barcodes!!.size() > 0) {

                    val first = barcodes.valueAt(0)
                    builder.setMessage(getString(R.string.view)+" " +first?.displayValue)

                    builder.setPositiveButton(getString(R.string.ok)) { dialog, which ->
                        startActivity(Intent(this@BarcodeScannerActivity,ProductDetailsActivity::class.java)
                            .putExtra("code",first?.displayValue))
                        finish()
                    }

                    taskHandler.post(runnable)
                }
            }
        })

        cameraSource = CameraSource.Builder(this, detector).setRequestedPreviewSize(1024, 768)
            .setRequestedFps(30f).setAutoFocusEnabled(true).build()
        svBarcode.holder.addCallback(object : SurfaceHolder.Callback2 {
            override fun surfaceRedrawNeeded(p0: SurfaceHolder) {
                showToast("1")
            }
            override fun surfaceChanged(p0: SurfaceHolder, format: Int, width: Int, height: Int) {
                showToast("2")
            }
            override fun surfaceDestroyed(p0: SurfaceHolder) {
                showToast("3")
                cameraSource.stop()
            }
            override fun surfaceCreated(p0: SurfaceHolder) {
                showToast("4")
                if (ContextCompat.checkSelfPermission(this@BarcodeScannerActivity, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    cameraSource.start(svBarcode.holder)
                else ActivityCompat.requestPermissions(this@BarcodeScannerActivity, arrayOf(android.Manifest.permission.CAMERA), 123)
            }

        })
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                cameraSource.start(svBarcode.holder)
            else Toast.makeText(this, "scanner", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        detector.release()
        cameraSource.stop()
        cameraSource.release()
    }


    fun showToast(msg: String){
        // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}