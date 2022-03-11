package com.example.myquizapp

data class Question(
    val id: Int,
    val question: String,
    val image: Int,
    val choices: ArrayList<Choice>,
    val correctAnswer: Int,
    )


data class Choice(
    val id: Int,
    val name: String,
)