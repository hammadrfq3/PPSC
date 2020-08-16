package com.companyname.ppsc.Controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.companyname.ppsc.Model.GenericFunctions
import com.companyname.ppsc.R
import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_bachelor.*


class BachelorActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var genericFunc: GenericFunctions
    private lateinit var examType: String
    private var matricPercentage: Int=0
    private var interPercentage: Int=0
    private var bachelorPercentage: Int=0
    private var matricEduType: String=""
    private var interEduType: String=""
    private var backToast: Toast? = null
    private var context: Context =this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bachelor)

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        genericFunc= GenericFunctions()

        mAdView = AdView(this, "2561807714083439_2561809617416582", AdSize.BANNER_HEIGHT_50)

        // Find the Ad Container
        val adContainer = findViewById<View>(R.id.banner_container) as LinearLayout

        // Add the ad view to your activity layout
        adContainer.addView(mAdView)

        // Request an ad
        mAdView.loadAd()

        // Interstitial Ad
        mInterstitialAd = InterstitialAd(this, "2561807714083439_2561812784082932")

        // Set listeners for the Interstitial Ad
        mInterstitialAd.setAdListener(object : InterstitialAdListener {
            override fun onInterstitialDisplayed(ad: Ad) {
                // Interstitial ad displayed callback
            }

            override fun onInterstitialDismissed(ad: Ad) {
                // Interstitial dismissed callback

                if(examType=="examtype3" && checkBoxBS.isChecked==true){
                    examType="examtype4"
                    val matricMarks=genericFunc.getMarks(examType,"matric",matricEduType,matricPercentage)
                    val interMarks=genericFunc.getMarks(examType,"inter",interEduType,interPercentage)
                    val bachelorMarks=genericFunc.getMarks(examType,"bachelor",bachelorDropDown.selectedItem.toString(),bachelorPercentage)
                    val masterMarks=genericFunc.getMarks(examType,"master",bachelorDropDown.selectedItem.toString(),bachelorPercentage)
                    val finalPageIntent=Intent(context,FinalPageActivity::class.java)
                    finalPageIntent.putExtra("MATRIC_MARKS",matricMarks[0].toInt())
                    finalPageIntent.putExtra("INTER_MARKS",interMarks[0].toInt())
                    finalPageIntent.putExtra("BACHELOR_MARKS",bachelorMarks[0].toInt())
                    finalPageIntent.putExtra("MASTER_MARKS",masterMarks[0].toInt())
                    finalPageIntent.putExtra("TOTAL_MATRIC_MARKS",masterMarks[1].toInt())
                    finalPageIntent.putExtra("TOTAL_INTER_MARKS",masterMarks[2].toInt())
                    finalPageIntent.putExtra("TOTAL_BACHELOR_MARKS",masterMarks[3].toInt())
                    finalPageIntent.putExtra("TOTAL_MASTER_MARKS",masterMarks[4].toInt())
                    finalPageIntent.putExtra("EXAM_TYPE","master")
                    examType="examtype3"
                    startActivity(finalPageIntent)
                }else if(examType=="examtype3" && checkBoxBS.isChecked==false){
                    val matricMarks=genericFunc.getMarks(examType,"matric",matricEduType,matricPercentage)
                    val interMarks=genericFunc.getMarks(examType,"inter",interEduType,interPercentage)
                    val bachelorMarks=genericFunc.getMarks(examType,"bachelor",bachelorDropDown.selectedItem.toString(),bachelorPercentage)
                    val finalPageIntent=Intent(context,FinalPageActivity::class.java)
                    finalPageIntent.putExtra("MATRIC_MARKS",matricMarks[0].toInt())
                    finalPageIntent.putExtra("INTER_MARKS",interMarks[0].toInt())
                    finalPageIntent.putExtra("BACHELOR_MARKS",bachelorMarks[0].toInt())
                    finalPageIntent.putExtra("TOTAL_MATRIC_MARKS",bachelorMarks[1].toInt())
                    finalPageIntent.putExtra("TOTAL_INTER_MARKS",bachelorMarks[2].toInt())
                    finalPageIntent.putExtra("TOTAL_BACHELOR_MARKS",bachelorMarks[3].toInt())
                    finalPageIntent.putExtra("EXAM_TYPE","bachelor")
                    startActivity(finalPageIntent)
                }

                mInterstitialAd.loadAd()
            }

            override fun onError(ad: Ad, adError: AdError) {
                // Ad error callback
                //Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            override fun onAdLoaded(ad: Ad) {
                // Interstitial ad is loaded and ready to be displayed
                // Show the ad
            }

            override fun onAdClicked(ad: Ad) {
                // Ad clicked callback
            }

            override fun onLoggingImpression(ad: Ad) {
                // Ad impression logged callback
            }
        })

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        mInterstitialAd.loadAd()

        matricPercentage=intent.getIntExtra("MATRIC_PERCENTAGE",0)
        matricEduType=intent.getStringExtra("MATRIC_EDU_TYPE")
        interPercentage=intent.getIntExtra("INTER_PERCENTAGE",0)
        interEduType=intent.getStringExtra("INTER_EDU_TYPE")
        examType=intent.getStringExtra("EXAM_TYPE")

        if(examType=="examtype3"){
            bachelorBtn.setText("Submit")
        }else{
            bachelorBtn.setText("Next")
        }

        bachelorObtainedTxt.setVisibility(View.GONE)
        bachelorTotalTxt.setVisibility(View.GONE)
        bachelorPercentageTxt.setVisibility(View.GONE)
        checkBoxBS.setVisibility(View.GONE)

        bachelorDropDown.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                if(bachelorDropDown.selectedItem.toString()=="Annual"){
                    bachelorObtainedTxt.setVisibility(View.VISIBLE)
                    bachelorTotalTxt.setVisibility(View.VISIBLE)
                    bachelorPercentageTxt.setText("")
                    bachelorPercentageTxt.setVisibility(View.GONE)
                    checkBoxBS.isChecked=false
                    checkBoxBS.setVisibility(View.GONE)
                }else if(bachelorDropDown.selectedItem.toString()=="Semester"){
                    bachelorPercentageTxt.setVisibility(View.VISIBLE)
                    bachelorObtainedTxt.setText("")
                    bachelorTotalTxt.setText("")
                    bachelorObtainedTxt.setVisibility(View.GONE)
                    bachelorTotalTxt.setVisibility(View.GONE)
                    if(examType=="examtype3"){
                        checkBoxBS.setVisibility(View.VISIBLE)
                        checkBoxBS.isChecked=false
                    }
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        })

        bachelorPercentageTxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(bachelorPercentageTxt.text.toString()!="" && bachelorPercentageTxt.text.toString().toInt() <1 &&
                    bachelorPercentageTxt.text.toString().toInt()>100){
                    bachelorPercentageTxt.setError("Invalid Percentage")
                    bachelorBtn.isEnabled=false
                }else{
                    bachelorBtn.isEnabled=true
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        bachelorTotalTxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(bachelorObtainedTxt.text.toString()!="" && bachelorTotalTxt.text.toString()!="" &&
                    bachelorTotalTxt.text.toString().toInt()<=bachelorObtainedTxt.text.toString().toInt()){
                    bachelorTotalTxt.setError("Total marks should be greater than obtained marks")
                    bachelorBtn.isEnabled=false
                }else{
                    bachelorBtn.isEnabled=true
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        bachelorObtainedTxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(bachelorTotalTxt.text.toString()!="" && bachelorObtainedTxt.text.toString()!="" &&
                    bachelorTotalTxt.text.toString().toInt()<=bachelorObtainedTxt.text.toString().toInt()){
                    bachelorObtainedTxt.setError("Obtained marks should be less than Total marks")
                    bachelorBtn.isEnabled=false
                }else{
                    bachelorBtn.isEnabled=true
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    fun bachelorBtnClicked(view: View){

        if(bachelorDropDown.selectedItem.toString()=="Semester"){
            if (bachelorPercentageTxt.text.toString() == "")
            {
                backToast=Toast.makeText(this, "Fill blank fields", Toast.LENGTH_SHORT)
                backToast?.show()
                return;
            }
            else
            {
                bachelorPercentage = bachelorPercentageTxt.text.toString().toInt();
            }
        }else if(bachelorDropDown.selectedItem.toString()=="Annual"){
            if(bachelorObtainedTxt.text.toString()=="" || bachelorTotalTxt.text.toString()==""){
                backToast=Toast.makeText(this, "Fill blank fields", Toast.LENGTH_SHORT)
                backToast?.show()
            }else{
                bachelorPercentage=((bachelorObtainedTxt.text.toString().toDouble()/bachelorTotalTxt.text.toString().toDouble()) * 100).toInt()
            }
        }

        if(examType=="examtype3" && checkBoxBS.isChecked==true){
            if(bachelorDropDown.selectedItem.toString()=="Annual"){
                backToast=Toast.makeText(this, "Select Semester for BS Program", Toast.LENGTH_LONG)
                backToast?.show()
            }else{
                if(mInterstitialAd.isAdLoaded()){
                    mInterstitialAd.show()
                }else{
                    examType="examtype4"
                    val matricMarks=genericFunc.getMarks(examType,"matric",matricEduType,matricPercentage)
                    val interMarks=genericFunc.getMarks(examType,"inter",interEduType,interPercentage)
                    val bachelorMarks=genericFunc.getMarks(examType,"bachelor",bachelorDropDown.selectedItem.toString(),bachelorPercentage)
                    val masterMarks=genericFunc.getMarks(examType,"master",bachelorDropDown.selectedItem.toString(),bachelorPercentage)
                    val finalPageIntent=Intent(context,FinalPageActivity::class.java)
                    finalPageIntent.putExtra("MATRIC_MARKS",matricMarks[0].toInt())
                    finalPageIntent.putExtra("INTER_MARKS",interMarks[0].toInt())
                    finalPageIntent.putExtra("BACHELOR_MARKS",bachelorMarks[0].toInt())
                    finalPageIntent.putExtra("MASTER_MARKS",masterMarks[0].toInt())
                    finalPageIntent.putExtra("TOTAL_MATRIC_MARKS",masterMarks[1].toInt())
                    finalPageIntent.putExtra("TOTAL_INTER_MARKS",masterMarks[2].toInt())
                    finalPageIntent.putExtra("TOTAL_BACHELOR_MARKS",masterMarks[3].toInt())
                    finalPageIntent.putExtra("TOTAL_MASTER_MARKS",masterMarks[4].toInt())
                    finalPageIntent.putExtra("EXAM_TYPE","master")
                    examType="examtype3"
                    startActivity(finalPageIntent)
                }
            }
        }else if(examType=="examtype3" && checkBoxBS.isChecked==false){
            if(mInterstitialAd.isAdLoaded()){
                mInterstitialAd.show()
            }else{
                val matricMarks=genericFunc.getMarks(examType,"matric",matricEduType,matricPercentage)
                val interMarks=genericFunc.getMarks(examType,"inter",interEduType,interPercentage)
                val bachelorMarks=genericFunc.getMarks(examType,"bachelor",bachelorDropDown.selectedItem.toString(),bachelorPercentage)
                val finalPageIntent=Intent(context,FinalPageActivity::class.java)
                finalPageIntent.putExtra("MATRIC_MARKS",matricMarks[0].toInt())
                finalPageIntent.putExtra("INTER_MARKS",interMarks[0].toInt())
                finalPageIntent.putExtra("BACHELOR_MARKS",bachelorMarks[0].toInt())
                finalPageIntent.putExtra("TOTAL_MATRIC_MARKS",bachelorMarks[1].toInt())
                finalPageIntent.putExtra("TOTAL_INTER_MARKS",bachelorMarks[2].toInt())
                finalPageIntent.putExtra("TOTAL_BACHELOR_MARKS",bachelorMarks[3].toInt())
                finalPageIntent.putExtra("EXAM_TYPE","bachelor")
                startActivity(finalPageIntent)
            }
        }else{
            val masterPageIntent=Intent(this,MasterActivity::class.java)
            masterPageIntent.putExtra("MATRIC_PERCENTAGE",matricPercentage)
            masterPageIntent.putExtra("MATRIC_EDU_TYPE",matricEduType)
            masterPageIntent.putExtra("INTER_PERCENTAGE",interPercentage)
            masterPageIntent.putExtra("INTER_EDU_TYPE",interEduType)
            masterPageIntent.putExtra("BACHELOR_PERCENTAGE",bachelorPercentage)
            masterPageIntent.putExtra("BACHELOR_EDU_TYPE",bachelorDropDown.selectedItem.toString())
            masterPageIntent.putExtra("EXAM_TYPE",examType)
            startActivity(masterPageIntent)
        }
    }

    public override fun onPause() {
        backToast?.cancel()
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
        if (mInterstitialAd != null) {
            mInterstitialAd.destroy()
        }
        super.onDestroy()
    }
}