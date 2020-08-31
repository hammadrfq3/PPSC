package com.companyname.ppsc.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.ads.AudienceNetworkAds


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AudienceNetworkAds.initialize(this)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}