package com.companyname.ppsc.Controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.companyname.ppsc.R
import com.facebook.ads.AdSize
import com.facebook.ads.AdView

class MainActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0
    private var backToast: Toast? = null
    private lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdView = AdView(this, "2561807714083439_2561809617416582", AdSize.BANNER_HEIGHT_50)

        // Find the Ad Container
        val adContainer = findViewById<View>(R.id.banner_container_main) as LinearLayout

        // Add the ad view to your activity layout
        adContainer.addView(mAdView)

        // Request an ad
        mAdView.loadAd()

    }

    fun matricMainBtnClicked(view: View){
        val matricIntent=Intent(this, MatricActivity::class.java)
        matricIntent.putExtra("EXAM_TYPE","examtype1")
        startActivity(matricIntent)
    }

    fun interMainBtnClicked(view: View){
        val matricIntent=Intent(this, MatricActivity::class.java)
        matricIntent.putExtra("EXAM_TYPE","examtype2")
        startActivity(matricIntent)
    }

    fun bachelorMainBtnClicked(view: View){
        val matricIntent=Intent(this, MatricActivity::class.java)
        matricIntent.putExtra("EXAM_TYPE","examtype3")
        startActivity(matricIntent)
    }

    fun masterMainBtnClicked(view: View){
        val matricIntent=Intent(this, MatricActivity::class.java)
        matricIntent.putExtra("EXAM_TYPE","examtype4")
        startActivity(matricIntent)
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
            finish()
        } else {
            backToast = Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT)
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    // Called when leaving the activity
    public override fun onPause() {
        super.onPause()
    }

    // Called when returning to the activity
    public override fun onResume() {
        super.onResume()
    }

    // Called before the activity is destroyed
    public override fun onDestroy() {
        if (mAdView != null) {
            mAdView.destroy()
        }
        super.onDestroy()
    }
}