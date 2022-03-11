package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    private var mTxtUsername: TextView? = null
    private var mTxtScore: TextView? = null
    private var mBtnFinish: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        mTxtUsername = findViewById(R.id.txtUsername)
        mTxtScore = findViewById(R.id.txtScore)
        mBtnFinish = findViewById(R.id.btnFinish)
        mBtnFinish?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }



        val myTextScore = "${intent.getStringExtra(Constants.CORRECT_ANSWER)} of ${intent.getStringExtra(Constants.TOTAL_QUESTIONS)}"
        mTxtUsername?.append(intent.getStringExtra(Constants.USER_NAME))
        mTxtScore?.append(myTextScore)


    }
}