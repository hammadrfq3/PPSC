package com.companyname.ppsc.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.companyname.ppsc.BuildConfig
import com.companyname.ppsc.model.GenericFunctions
import com.companyname.ppsc.R
import com.companyname.ppsc.viewModel.BachelorActivityViewModel
import com.facebook.ads.AdSize
import com.facebook.ads.AdView
import kotlinx.android.synthetic.main.activity_inter.*

class InterActivity : AppCompatActivity() {

    private lateinit var examType: String
    private var matricPercentage: Int=0
    private var matricEduType: String=""
    private var backToast: Toast? = null
    private lateinit var mAdView: AdView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var bachelorActivityViewModel: BachelorActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inter)

        initView()

    }

    private fun initView() {

        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar);
        supportActionBar?.title ="Enter Hssc Marks"

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mAdView = AdView(this, "2561807714083439_2561809617416582", AdSize.BANNER_HEIGHT_50)

        // Find the Ad Container
        val adContainer = findViewById<View>(R.id.banner_container_inter) as LinearLayout

        // Add the ad view to your activity layout
        adContainer.addView(mAdView)

        // Request an ad
        mAdView.loadAd()

        matricPercentage=intent.getIntExtra("MATRIC_PERCENTAGE",0)
        matricEduType=intent.getStringExtra("EDU_TYPE")
        examType=intent.getStringExtra("EXAM_TYPE")

        if(examType=="examtype2"){
            interBtn.setText("Submit")
        }else{
            interBtn.setText("Next")
        }

        //genericFunc= GenericFunctions()
        bachelorActivityViewModel=ViewModelProvider(this).get(BachelorActivityViewModel::class.java)

        interTotalTxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(interObtainedTxt.text.toString()!="" && interTotalTxt.text.toString()!="" &&
                    interTotalTxt.text.toString().toInt()<=interObtainedTxt.text.toString().toInt()){
                    interTotalTxt.setError("Total marks should be greater than obtained marks")
                    interBtn.isEnabled=false
                }else{
                    interBtn.isEnabled=true
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        interObtainedTxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(interTotalTxt.text.toString()!="" && interObtainedTxt.text.toString()!="" &&
                    interTotalTxt.text.toString().toInt()<=interObtainedTxt.text.toString().toInt()){
                    interObtainedTxt.setError("Obtained marks should be less than Total marks")
                    interBtn.isEnabled=false
                }else{
                    interBtn.isEnabled=true
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

    fun interBtnClicked(view: View){
        if(interDropDown.selectedItem.toString()=="---Select an education system---" || interObtainedTxt.text.toString()==""
            || interTotalTxt.text.toString()==""){
            backToast=Toast.makeText(this,"Fill the blank fields", Toast.LENGTH_SHORT)
            backToast?.show()
        }else{
            val percentage=bachelorActivityViewModel.calculatePercentage(interObtainedTxt.text.toString().toDouble(),
                                                                            interTotalTxt.text.toString().toDouble())
            if(examType=="examtype2"){
                val interMarks=bachelorActivityViewModel.getMarks(examType,"inter",interDropDown.selectedItem.toString(),percentage.toInt())
                val matricMarks=bachelorActivityViewModel.getMarks(examType,"matric",matricEduType,matricPercentage)
                val finalPageIntent=Intent(this,FinalPageActivity::class.java)
                finalPageIntent.putExtra("MATRIC_MARKS",matricMarks[0].toInt())
                finalPageIntent.putExtra("INTER_MARKS",interMarks[0].toInt())
                finalPageIntent.putExtra("TOTAL_MATRIC_MARKS",interMarks[1].toInt())
                finalPageIntent.putExtra("TOTAL_INTER_MARKS",interMarks[2].toInt())
                finalPageIntent.putExtra("EXAM_TYPE","inter")
                startActivity(finalPageIntent)
            }else{
                val interPageIntent=Intent(this,BachelorActivity::class.java)
                interPageIntent.putExtra("MATRIC_PERCENTAGE",matricPercentage)
                interPageIntent.putExtra("MATRIC_EDU_TYPE",matricEduType)
                interPageIntent.putExtra("INTER_PERCENTAGE",percentage.toInt())
                interPageIntent.putExtra("INTER_EDU_TYPE",interDropDown.selectedItem.toString())
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