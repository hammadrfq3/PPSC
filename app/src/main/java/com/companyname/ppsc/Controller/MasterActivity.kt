package com.companyname.ppsc.Controller

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import com.companyname.ppsc.Model.GenericFunctions
import com.companyname.ppsc.R
import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_bachelor.*
import kotlinx.android.synthetic.main.activity_master.*
import java.util.*


class MasterActivity : AppCompatActivity() {
    private lateinit var genericFunc: GenericFunctions
    private lateinit var examType: String
    private var matricPercentage: Int=0
    private var interPercentage: Int=0
    private var bachelorPercentage: Int=0
    private var masterPercentage: Int=0
    private var matricEduType: String=""
    private var interEduType: String=""
    private var bachelorEduType: String=""
    private var backToast: Toast? = null
    private var context: Context=this
    private lateinit var mAdView: AdView
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {



        try {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_master)

            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

            mAdView = AdView(this, "2561807714083439_2561809617416582", AdSize.BANNER_HEIGHT_50)

            // Find the Ad Container
            val adContainer = findViewById<View>(R.id.banner_container_master) as LinearLayout

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

                    val matricMarks=genericFunc.getMarks(examType,"matric",matricEduType,matricPercentage)
                    val interMarks=genericFunc.getMarks(examType,"inter",interEduType,interPercentage)
                    val bachelorMarks=genericFunc.getMarks(examType,"bachelor",bachelorEduType,bachelorPercentage)
                    val masterMarks=genericFunc.getMarks(examType,"master",masterDropDown.selectedItem.toString(),masterPercentage)
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
                    startActivity(finalPageIntent)

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

            genericFunc= GenericFunctions()

            matricPercentage=intent.getIntExtra("MATRIC_PERCENTAGE",0)
            matricEduType=intent.getStringExtra("MATRIC_EDU_TYPE")
            interPercentage=intent.getIntExtra("INTER_PERCENTAGE",0)
            interEduType=intent.getStringExtra("INTER_EDU_TYPE")
            bachelorPercentage=intent.getIntExtra("BACHELOR_PERCENTAGE",0)
            bachelorEduType=intent.getStringExtra("BACHELOR_EDU_TYPE")
            examType=intent.getStringExtra("EXAM_TYPE")

            masterBtn.setText("Submit")

            masterPercentageTxt.setVisibility(View.GONE)
            masterObtainedTxt.setVisibility(View.GONE)
            masterTotalTxt.setVisibility(View.GONE)

            masterDropDown.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                    if(masterDropDown.selectedItem.toString()=="Annual"){
                        masterObtainedTxt.setVisibility(View.VISIBLE)
                        masterTotalTxt.setVisibility(View.VISIBLE)
                        masterPercentageTxt.setText("")
                        masterPercentageTxt.setVisibility(View.GONE)
                    }else if(masterDropDown.selectedItem.toString()=="Semester"){
                        masterPercentageTxt.setVisibility(View.VISIBLE)
                        masterObtainedTxt.setText("")
                        masterTotalTxt.setText("")
                        masterObtainedTxt.setVisibility(View.GONE)
                        masterTotalTxt.setVisibility(View.GONE)
                    }
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {
                    // your code here
                }
            })

            masterPercentageTxt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if(masterPercentageTxt.text.toString()!="" && masterPercentageTxt.text.toString().toInt() <1 &&
                        masterPercentageTxt.text.toString().toInt()>100){
                        masterPercentageTxt.setError("Invalid Percentage")
                        masterBtn.isEnabled=false
                    }else{
                        masterBtn.isEnabled=true
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })

            masterTotalTxt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if(masterObtainedTxt.text.toString()!="" && masterTotalTxt.text.toString()!="" &&
                        masterTotalTxt.text.toString().toInt()<=masterObtainedTxt.text.toString().toInt()){
                        masterTotalTxt.setError("Total marks should be greater than obtained marks")
                        masterBtn.isEnabled=false
                    }else{
                        masterBtn.isEnabled=true
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })

            masterObtainedTxt.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if(masterTotalTxt.text.toString()!="" && masterObtainedTxt.text.toString()!="" &&
                        masterTotalTxt.text.toString().toInt()<=masterObtainedTxt.text.toString().toInt()){
                        masterObtainedTxt.setError("Obtained marks should be less than Total marks")
                        masterBtn.isEnabled=false
                    }else{
                        masterBtn.isEnabled=true
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })

        }catch (ex: Exception){
            throw java.lang.Exception(ex.message)
        }

    }

    fun masterBtnClicked(view: View){
        if(masterDropDown.selectedItem.toString()=="Semester"){
            if (masterPercentageTxt.text.toString() == "")
            {
                backToast=Toast.makeText(this, "Fill blank fields", Toast.LENGTH_SHORT)
                backToast?.show()
                return
            }
            else
            {
                masterPercentage = masterPercentageTxt.text.toString().toInt();
            }
        }else if(masterDropDown.selectedItem.toString()=="Annual"){
            if(masterObtainedTxt.text.toString()=="" || masterTotalTxt.text.toString()==""){
                backToast=Toast.makeText(this, "Fill blank fields", Toast.LENGTH_SHORT)
                backToast?.show()
                return
            }else{
                masterPercentage=((masterObtainedTxt.text.toString().toDouble()/masterTotalTxt.text.toString().toDouble()) * 100).toInt()
            }
        }

        if (mInterstitialAd.isAdLoaded) {
            mInterstitialAd.show()
        } else {
            val matricMarks=genericFunc.getMarks(examType,"matric",matricEduType,matricPercentage)
            val interMarks=genericFunc.getMarks(examType,"inter",interEduType,interPercentage)
            val bachelorMarks=genericFunc.getMarks(examType,"bachelor",bachelorEduType,bachelorPercentage)
            val masterMarks=genericFunc.getMarks(examType,"master",masterDropDown.selectedItem.toString(),masterPercentage)
            val finalPageIntent=Intent(this,FinalPageActivity::class.java)
            finalPageIntent.putExtra("MATRIC_MARKS",matricMarks[0].toInt())
            finalPageIntent.putExtra("INTER_MARKS",interMarks[0].toInt())
            finalPageIntent.putExtra("BACHELOR_MARKS",bachelorMarks[0].toInt())
            finalPageIntent.putExtra("MASTER_MARKS",masterMarks[0].toInt())
            finalPageIntent.putExtra("TOTAL_MATRIC_MARKS",masterMarks[1].toInt())
            finalPageIntent.putExtra("TOTAL_INTER_MARKS",masterMarks[2].toInt())
            finalPageIntent.putExtra("TOTAL_BACHELOR_MARKS",masterMarks[3].toInt())
            finalPageIntent.putExtra("TOTAL_MASTER_MARKS",masterMarks[4].toInt())
            finalPageIntent.putExtra("EXAM_TYPE","master")
            startActivity(finalPageIntent)
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
        super.onDestroy()
    }
}