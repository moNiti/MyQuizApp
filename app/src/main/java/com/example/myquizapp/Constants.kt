package com.example.myquizapp

object Constants {
    const val USER_NAME = "user_name"
    const val TOTAL_QUESTIONS = "total_questions"
    const val CORRECT_ANSWER = "correct_answer"


    fun getQuestions(): ArrayList<Question> {
        val questions = ArrayList<Question>()
        val q1 = Question(
            1,
            "Which one is color?",
            R.drawable.bg,
            arrayListOf(Choice(0, "red"), Choice(0, "c2"), Choice(0, "c3")),
            0
        )
        val q2 = Question(
            2,
            "Which one is animal?",
            R.drawable.bg,
            arrayListOf(Choice(0, "c1"), Choice(0, "cat"), Choice(0, "c3")),
            1
        )
        val q3 = Question(
            1,
            "Which one is animal 2?",
            R.drawable.bg,
            arrayListOf(Choice(0, "dog"), Choice(0, "c2"), Choice(0, "c3")),
            0
        )
        questions.add(q1)
        questions.add(q2)
        questions.add(q3)
        return questions
    }
}