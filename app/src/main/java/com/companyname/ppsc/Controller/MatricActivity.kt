package com.companyname.ppsc.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import com.companyname.ppsc.Model.GenericFunctions
import com.companyname.ppsc.R
import com.facebook.ads.AdSize
import com.facebook.ads.AdView
import kotlinx.android.synthetic.main.activity_matric.*

class MatricActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView
    private lateinit var genericFunc: GenericFunctions
    private lateinit var examType: String
    private var backToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matric)


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mAdView = AdView(this, "2561807714083439_2561809617416582", AdSize.BANNER_HEIGHT_50)

        // Find the Ad Container
        val adContainer = findViewById<View>(R.id.banner_container_matric) as LinearLayout

        // Add the ad view to your activity layout
        adContainer.addView(mAdView)

        // Request an ad
        mAdView.loadAd()

        examType=intent.getStringExtra("EXAM_TYPE")

        if(examType=="examtype1"){
            Btn.setText("Submit")
        }else{
            Btn.setText("Next")
        }

        genericFunc= GenericFunctions()

        matricTotalTxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(matricObtainedTxt.text.toString()!="" && matricTotalTxt.text.toString()!="" && matricTotalTxt.text.toString().toInt()<=matricObtainedTxt.text.toString().toInt()){
                    matricTotalTxt.setError("Total marks should be greater than obtained marks")
                    Btn.isEnabled=false
                }else{
                    Btn.isEnabled=true
                    matricTotalTxt.error
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        matricObtainedTxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(matricTotalTxt.text.toString()!="" && matricObtainedTxt.text.toString()!="" && matricTotalTxt.text.toString().toInt()<=matricObtainedTxt.text.toString().toInt()){
                    matricObtainedTxt.setError("Obtained marks should be less than Total marks")
                    Btn.isEnabled=false
                }else{
                    Btn.isEnabled=true
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    fun sscBtnClicked(view: View){
        if(dropDown.selectedItem.toString()=="---Select an education system---" || matricObtainedTxt.text.toString()==""
            || matricTotalTxt.text.toString()==""){
            backToast=Toast.makeText(this,"Fill the blank fields",Toast.LENGTH_SHORT)
            backToast?.show()
        }else{
            val percentage=(matricObtainedTxt.text.toString().toDouble()/matricTotalTxt.text.toString().toDouble()) * 100
            if(examType=="examtype1"){
                val matricMarks=genericFunc.getMarks(examType,"matric",dropDown.selectedItem.toString(),percentage.toInt())
                val finalPageIntent=Intent(this,FinalPageActivity::class.java)
                finalPageIntent.putExtra("MATRIC_MARKS",matricMarks[0].toInt())
                finalPageIntent.putExtra("TOTAL_MATRIC_MARKS",matricMarks[1].toInt())
                finalPageIntent.putExtra("EXAM_TYPE","matric")
                startActivity(finalPageIntent)
            }else{
                val interPageIntent=Intent(this,InterActivity::class.java)
                interPageIntent.putExtra("MATRIC_PERCENTAGE",percentage.toInt())
                interPageIntent.putExtra("EDU_TYPE",dropDown.selectedItem.toString())
                interPageIntent.putExtra("EXAM_TYPE",examType)
                startActivity(interPageIntent)
            }
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