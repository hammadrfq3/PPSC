package com.companyname.ppsc.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.companyname.ppsc.BuildConfig
import com.companyname.ppsc.R
import com.companyname.ppsc.viewModel.BachelorActivityViewModel
import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_bachelor.*


class BachelorActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var examType: String
    private var matricPercentage: Int=0
    private var interPercentage: Int=0
    private var bachelorPercentage: Int=0
    private var matricEduType: String=""
    private var interEduType: String=""
    private var backToast: Toast? = null
    private var context: Context =this
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var bachelorActivityViewModel: BachelorActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bachelor)

        initViews()
    }

    private fun initViews() {

        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar);
        supportActionBar?.title ="Enter Bachelor Marks"

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //genericFunc= GenericFunctions(application)

        bachelorActivityViewModel=ViewModelProvider(this).get(BachelorActivityViewModel::class.java)

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

                if(examType=="examtype3" && checkBoxBS.isChecked){
                    examType="examtype4"
                    val matricMarks=bachelorActivityViewModel.getMarks(examType,"matric",matricEduType,matricPercentage)
                    val interMarks=bachelorActivityViewModel.getMarks(examType,"inter",interEduType,interPercentage)
                    val bachelorMarks=bachelorActivityViewModel.getMarks(examType,"bachelor",bachelorDropDown.selectedItem.toString(),bachelorPercentage)
                    val masterMarks=bachelorActivityViewModel.getMarks(examType,"master",bachelorDropDown.selectedItem.toString(),bachelorPercentage)
                    val finalPageIntent=Intent(context,FinalPageActivity::class.java)
                    finalPageIntent.putExtra("MATRIC_MARKS", matricMarks[0])
                    finalPageIntent.putExtra("INTER_MARKS", interMarks[0])
                    finalPageIntent.putExtra("BACHELOR_MARKS", bachelorMarks[0])
                    finalPageIntent.putExtra("MASTER_MARKS", masterMarks[0])
                    finalPageIntent.putExtra("TOTAL_MATRIC_MARKS", masterMarks[1])
                    finalPageIntent.putExtra("TOTAL_INTER_MARKS", masterMarks[2])
                    finalPageIntent.putExtra("TOTAL_BACHELOR_MARKS", masterMarks[3])
                    finalPageIntent.putExtra("TOTAL_MASTER_MARKS", masterMarks[4])
                    finalPageIntent.putExtra("EXAM_TYPE","master")
                    examType="examtype3"
                    startActivity(finalPageIntent)
                }else if(examType=="examtype3" && !checkBoxBS.isChecked){
                    val matricMarks=bachelorActivityViewModel.getMarks(examType,"matric",matricEduType,matricPercentage)
                    val interMarks=bachelorActivityViewModel.getMarks(examType,"inter",interEduType,interPercentage)
                    val bachelorMarks=bachelorActivityViewModel.getMarks(examType,"bachelor",bachelorDropDown.selectedItem.toString(),bachelorPercentage)
                    val finalPageIntent=Intent(context,FinalPageActivity::class.java)
                    finalPageIntent.putExtra("MATRIC_MARKS", matricMarks[0])
                    finalPageIntent.putExtra("INTER_MARKS", interMarks[0])
                    finalPageIntent.putExtra("BACHELOR_MARKS", bachelorMarks[0])
                    finalPageIntent.putExtra("TOTAL_MATRIC_MARKS", bachelorMarks[1])
                    finalPageIntent.putExtra("TOTAL_INTER_MARKS", bachelorMarks[2])
                    finalPageIntent.putExtra("TOTAL_BACHELOR_MARKS", bachelorMarks[3])
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

        bachelorBtn.setOnClickListener{

            if(bachelorDropDown.selectedItem.toString()=="Semester"){
                if (bachelorPercentageTxt.text.toString() == "")
                {
                    backToast=Toast.makeText(this, "Fill blank fields", Toast.LENGTH_SHORT)
                    backToast?.show()
                    return@setOnClickListener
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
                    bachelorPercentage=bachelorActivityViewModel.calculatePercentage(bachelorObtainedTxt.text.toString().toDouble(),
                                                                                bachelorTotalTxt.text.toString().toDouble())
                }
            }

            if(examType=="examtype3" && checkBoxBS.isChecked){
                if(bachelorDropDown.selectedItem.toString()=="Annual"){
                    backToast=Toast.makeText(this, "Select Semester for BS Program", Toast.LENGTH_LONG)
                    backToast?.show()
                }else{
                    if(mInterstitialAd.isAdLoaded){
                        mInterstitialAd.show()
                    }else{
                        examType="examtype4"
                        val matricMarks=bachelorActivityViewModel.getMarks(examType,"matric",matricEduType,matricPercentage)
                        val interMarks=bachelorActivityViewModel.getMarks(examType,"inter",interEduType,interPercentage)
                        val bachelorMarks=bachelorActivityViewModel.getMarks(examType,"bachelor",bachelorDropDown.selectedItem.toString(),bachelorPercentage)
                        val masterMarks=bachelorActivityViewModel.getMarks(examType,"master",bachelorDropDown.selectedItem.toString(),bachelorPercentage)
                        val finalPageIntent=Intent(context,FinalPageActivity::class.java)
                        finalPageIntent.putExtra("MATRIC_MARKS", matricMarks[0])
                        finalPageIntent.putExtra("INTER_MARKS", interMarks[0])
                        finalPageIntent.putExtra("BACHELOR_MARKS", bachelorMarks[0])
                        finalPageIntent.putExtra("MASTER_MARKS", masterMarks[0])
                        finalPageIntent.putExtra("TOTAL_MATRIC_MARKS", masterMarks[1])
                        finalPageIntent.putExtra("TOTAL_INTER_MARKS", masterMarks[2])
                        finalPageIntent.putExtra("TOTAL_BACHELOR_MARKS", masterMarks[3])
                        finalPageIntent.putExtra("TOTAL_MASTER_MARKS", masterMarks[4])
                        finalPageIntent.putExtra("EXAM_TYPE","master")
                        examType="examtype3"
                        startActivity(finalPageIntent)
                    }
                }
            }else if(examType=="examtype3" && !checkBoxBS.isChecked){
                if(mInterstitialAd.isAdLoaded){
                    mInterstitialAd.show()
                }else{
                    val matricMarks=bachelorActivityViewModel.getMarks(examType,"matric",matricEduType,matricPercentage)
                    val interMarks=bachelorActivityViewModel.getMarks(examType,"inter",interEduType,interPercentage)
                    val bachelorMarks=bachelorActivityViewModel.getMarks(examType,"bachelor",bachelorDropDown.selectedItem.toString(),bachelorPercentage)
                    val finalPageIntent=Intent(context,FinalPageActivity::class.java)
                    finalPageIntent.putExtra("MATRIC_MARKS", matricMarks[0])
                    finalPageIntent.putExtra("INTER_MARKS", interMarks[0])
                    finalPageIntent.putExtra("BACHELOR_MARKS", bachelorMarks[0])
                    finalPageIntent.putExtra("TOTAL_MATRIC_MARKS", bachelorMarks[1])
                    finalPageIntent.putExtra("TOTAL_INTER_MARKS", bachelorMarks[2])
                    finalPageIntent.putExtra("TOTAL_BACHELOR_MARKS", bachelorMarks[3])
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

        bachelorObtainedTxt.visibility = View.GONE
        bachelorTotalTxt.visibility = View.GONE
        bachelorPercentageTxt.visibility = View.GONE
        bachelorObtainedTxtInputLayout.visibility = View.GONE
        bachelorTotalTxtInputLayout.visibility = View.GONE
        bachelorPercentageTextInputLayout.visibility = View.GONE
        checkBoxBS.visibility = View.GONE

        bachelorDropDown.onItemSelectedListener=object : OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(bachelorDropDown.selectedItem.toString()=="Annual"){
                    bachelorObtainedTxtInputLayout.visibility = View.VISIBLE
                    bachelorTotalTxtInputLayout.visibility = View.VISIBLE
                    bachelorObtainedTxt.visibility = View.VISIBLE
                    bachelorTotalTxt.visibility = View.VISIBLE
                    bachelorPercentageTxt.setText("")
                    bachelorPercentageTextInputLayout.visibility = View.GONE
                    bachelorPercentageTxt.visibility = View.GONE
                    checkBoxBS.isChecked=false
                    checkBoxBS.visibility = View.GONE
                }else if(bachelorDropDown.selectedItem.toString()=="Semester"){
                    bachelorPercentageTextInputLayout.visibility = View.VISIBLE
                    bachelorPercentageTxt.visibility = View.VISIBLE
                    bachelorObtainedTxt.setText("")
                    bachelorTotalTxt.setText("")
                    bachelorObtainedTxtInputLayout.visibility = View.GONE
                    bachelorTotalTxtInputLayout.visibility = View.GONE
                    bachelorObtainedTxt.visibility = View.GONE
                    bachelorTotalTxt.visibility = View.GONE
                    if(examType=="examtype3"){
                        checkBoxBS.visibility = View.VISIBLE
                        checkBoxBS.isChecked=false
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

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
        mAdView.destroy()
        mInterstitialAd.destroy()
        super.onDestroy()
    }
}