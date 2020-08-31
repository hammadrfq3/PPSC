package com.companyname.ppsc.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.companyname.ppsc.BuildConfig
import com.companyname.ppsc.model.Class
import com.companyname.ppsc.R
import com.companyname.ppsc.adapter.ClassAdapter
import com.facebook.ads.AdSize
import com.facebook.ads.AdView


class MainActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0
    private var backToast: Toast? = null
    private lateinit var mAdView: AdView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterClass: ClassAdapter
    private lateinit var arrayListClass: ArrayList<Class>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar);
        supportActionBar?.title ="Select Required Degree For Job"

        prepareList();

        mAdView = AdView(
            this,
            "2561807714083439_2561809617416582",
            AdSize.BANNER_HEIGHT_50
        )

        // Find the Ad Container
        val adContainer = findViewById<View>(R.id.banner_container_main) as LinearLayout

        // Add the ad view to your activity layout
        adContainer.addView(mAdView)

        // Request an ad
        mAdView.loadAd()

    }

    private fun prepareList() {

        var classObj: Class=Class()
        classObj.image="ic_baseline_school_24";
        classObj.title="Matric"

        var classObj1: Class=Class()
        classObj1.image="ic_baseline_school_24";
        classObj1.title="inter"

        var classObj2: Class=Class()
        classObj2.image="ic_baseline_school_24";
        classObj2.title="bachelor"

        var classObj3: Class=Class()
        classObj3.image="ic_baseline_school_24";
        classObj3.title="master"

        arrayListClass=ArrayList()
        arrayListClass.add(classObj)
        arrayListClass.add(classObj1)
        arrayListClass.add(classObj2)
        arrayListClass.add(classObj3)

        recyclerView=findViewById(R.id.rvClasses);
        var layoutManager: RecyclerView.LayoutManager=GridLayoutManager(this, 2)
        adapterClass= ClassAdapter(this, arrayListClass)
        recyclerView.adapter=adapterClass
        recyclerView.layoutManager=layoutManager
        adapterClass.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id:Int=item.itemId

        if(id==R.id.action_bar_share){
            shareApp()
        }

        return super.onOptionsItemSelected(item)
    }

    fun shareApp(){
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "PPSC me job k liye apna academic merit maloom karne ke liye is app ko download kren : https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
        )
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
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