package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import org.w3c.dom.Text
import android.widget.LinearLayout
import androidx.core.view.children


class QuizQuestionActivity : AppCompatActivity() {
    private var mUsername: String? = null
    private var mLayoutOptions: LinearLayout? = null
    private var mProgressBar: ProgressBar? = null
    private var mTxtProgress: TextView? = null
    private var mTxtQuestion: TextView? = null
    private var mIvImage: ImageView? = null
    private var mBtnSubmit: Button? = null
    private var questions: ArrayList<Question>? = null

    private var currentQuestion = 0
    private var selectedChoice = -1
    private var correctCount = 0
    private var isFinish = false
    private var isCheck = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        mUsername = intent.getStringExtra(Constants.USER_NAME)
        questions = Constants.getQuestions()
        mLayoutOptions = findViewById(R.id.layoutOptions)
        mProgressBar = findViewById(R.id.myProgressBar)
        mProgressBar?.max = questions!!.size
        mTxtProgress = findViewById(R.id.txtProgress)
        mTxtQuestion = findViewById(R.id.txtQuestion)
        mIvImage = findViewById(R.id.ivImage)
        mBtnSubmit = findViewById(R.id.btnSubmit)
        updateQuestion()
        updateProgress()
        updateChoiceText()


    }

    private fun onChoiceSelect(view: android.view.View) {
        selectedChoice = view.id
        for (i in 0 until mLayoutOptions!!.childCount - 1) {
            val choice = mLayoutOptions!!.getChildAt(i)
            choice.setBackgroundResource(R.drawable.default_option_border_bg)
        }
        view.setBackgroundResource(R.drawable.selected_option_border_bg)
        println("onChoiceSelect $selectedChoice")
    }


    fun onButtonSubmit(view: android.view.View) {
        if (isFinish) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(Constants.TOTAL_QUESTIONS, questions!!.size.toString())
            intent.putExtra(Constants.CORRECT_ANSWER, correctCount.toString())
            intent.putExtra(Constants.USER_NAME, mUsername.toString())
            startActivity(intent)
            finish()
        } else if (selectedChoice != -1) {
            if (isCheck) {
//                Check answer first
                isCheck = false
                mBtnSubmit?.text = "Next"
                val correctIndex = questions!![currentQuestion].correctAnswer
                println("Current Id =>> $selectedChoice, Correct Index $correctIndex")

                var wrong = false
                for (i in 0 until mLayoutOptions!!.childCount - 1) {
                    val choice = mLayoutOptions!!.getChildAt(i)
//                   check selected
                    if (correctIndex == selectedChoice && selectedChoice == i) {
                        choice.setBackgroundResource(R.drawable.correct_option_border_bg)
                        correctCount += 1
                    } else if (correctIndex != selectedChoice && selectedChoice == i) {
                        choice.setBackgroundResource(R.drawable.wrong_option_border_bg)
                        wrong = true
                    }
                }
                if (wrong) {
                    val correct = mLayoutOptions!!.getChildAt(correctIndex)
                    correct.setBackgroundColor(R.drawable.correct_option_border_bg)
                }


            } else {
//                Go next question
                isCheck = true
                mBtnSubmit?.text = "Check Answer"
                if (currentQuestion < questions!!.size - 1) {
                    currentQuestion += 1
                    selectedChoice = -1
                    updateQuestion()
                    updateChoiceText()
                    updateProgress()

                } else {
                    currentQuestion += 1
                    selectedChoice = -1
                    updateProgress()
                    isFinish = true
                    Toast.makeText(this, "YEAH", Toast.LENGTH_SHORT).show()
                    mBtnSubmit?.text = "RESTART"
                }

            }


        }
    }

    private fun updateQuestion() {
        val quest = questions!![currentQuestion]
        mIvImage?.setBackgroundResource(R.drawable.bg)
        mTxtQuestion?.text = quest.question

    }

    private fun updateProgress() {
        mTxtProgress?.text = "${currentQuestion}/${questions?.size}"
        mProgressBar?.progress = currentQuestion
    }

    private fun updateChoiceText() {
        mLayoutOptions?.removeAllViews()
        for ((index, choice) in questions!![currentQuestion].choices.withIndex()) {
            var tvChoice = TextView(this)
            tvChoice.text = choice.name
            tvChoice.setPadding(15)
            tvChoice.id = index
            tvChoice.setOnClickListener {
                onChoiceSelect(tvChoice)
            }

            val params: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            params.setMargins(10, 10, 10, 10)
            tvChoice.setLayoutParams(params)

            tvChoice.setBackgroundResource(R.drawable.default_option_border_bg)
            mLayoutOptions?.addView(tvChoice)
        }
    }


}