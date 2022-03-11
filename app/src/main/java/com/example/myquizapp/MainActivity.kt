package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var mBtnStart: Button? = null
    var mEtName: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtnStart = findViewById(R.id.btnStart)
        mEtName = findViewById(R.id.etName)

        mBtnStart?.setOnClickListener {
            if(mEtName?.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
            }else {
              val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME, mEtName?.text.toString())
                startActivity(intent)
                finish()

            }
        }

    }
}