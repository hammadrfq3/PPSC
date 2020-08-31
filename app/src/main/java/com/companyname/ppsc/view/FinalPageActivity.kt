package com.companyname.ppsc.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.companyname.ppsc.R
import com.facebook.ads.AdSize
import com.facebook.ads.AdView
import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.android.synthetic.main.activity_final_page.*


class FinalPageActivity : AppCompatActivity() {

    lateinit var adapter: ArrayAdapter<String>
    private var matricT: Int=0
    private var interT: Int=0
    private var bachelorT: Int=0
    private var masterT: Int=0
    lateinit var mDialog: Dialog
    private lateinit var mAdView: AdView
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_page)

        context=this
        mAdView = AdView(this, "2561807714083439_2561809617416582", AdSize.BANNER_HEIGHT_50)

        // Find the Ad Container
        val adContainer = findViewById<View>(R.id.banner_container_final) as LinearLayout

        // Add the ad view to your activity layout
        adContainer.addView(mAdView)

        // Request an ad
        mAdView.loadAd()

        mDialog= Dialog(this)

        val matricMarks=intent.getIntExtra("MATRIC_MARKS",0)
        val matricTotalMarks=intent.getIntExtra("TOTAL_MATRIC_MARKS",0)
        val interMarks=intent.getIntExtra("INTER_MARKS",0)
        val interTotalMarks=intent.getIntExtra("TOTAL_INTER_MARKS",0)
        val bachelorMarks=intent.getIntExtra("BACHELOR_MARKS",0)
        val bachelorTotalMarks=intent.getIntExtra("TOTAL_BACHELOR_MARKS",0)
        val masterMarks=intent.getIntExtra("MASTER_MARKS",0)
        val masterTotalMarks=intent.getIntExtra("TOTAL_MASTER_MARKS",0)
        val examType=intent.getStringExtra("EXAM_TYPE")

        var list= arrayListOf<String>()

        when(examType){
            "matric" -> list.add("Matric: " + matricMarks + "/40")
            "inter" -> {
                list.add("Matric: " + matricMarks + "/15")
                list.add("Intermediate: " + interMarks + "/25")
            }
            "bachelor" -> {
                list.add("Matric: " + matricMarks + "/9")
                list.add("Intermediate: " + interMarks + "/12")
                list.add("Bachelor: " + bachelorMarks + "/19")
            }
            "master" -> {
                list.add("Matric: " + matricMarks + "/5")
                list.add("Intermediate: " + interMarks + "/7")
                list.add("Bachelor: " + bachelorMarks + "/11")
                list.add("Master: " + masterMarks + "/17")
            }
        }


        var result: Int=matricMarks + interMarks + bachelorMarks + masterMarks

        adapter= ArrayAdapter<String>(this, R.layout.list_text, R.id.list_content, list)
        listView.adapter=adapter

        MainTitle.text="Your academic merit for $examType based jobs"
        MainTitle.isAllCaps=true
        resultTxt.text="$result/40"

        showInAppReview()
    }

    fun showInAppReview(){
        val manager = ReviewManagerFactory.create(context)
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                // We got the ReviewInfo object
                val reviewInfo = request.result
                val flow = manager.launchReviewFlow(context as Activity, reviewInfo)
                flow.addOnCompleteListener { _ ->
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                }
            } else {
                // There was some problem, continue regardless of the result.
            }
        }


    }

    fun noteBtnClicked(view: View){

        mDialog.setContentView(R.layout.pop_up_message)
        mDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        mDialog.show()
        //pw.dismiss()
    }

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